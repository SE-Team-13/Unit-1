import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    public int totalPins = 0;
    private Timer timer;
    private int delay = 8;
    private int ballposX = 50;
    private int ballposy = 550;
    private int ballXdir = 0;
    private int ballYdir = -1;
    public SkittleGenerator sg;

    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
        sg = new SkittleGenerator();

    }

    public Gameplay(int pins[]) {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
        sg = new SkittleGenerator(pins);
    }

    @Override
    public void paint(Graphics g) {
        // background
        g.setColor(Color.gray);
        g.fillRect(1, 1, 100, 600);

        // Skittles
        sg.draw((Graphics2D) g);
        // Ball
        g.setColor(Color.blue);
        g.fillOval(ballposX, ballposy, 40, 40);

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        timer.start();
        if (play) {
            ballposX += ballXdir;
            ballposy += ballYdir;
        }
        for (int i = 0; i < 10; i++) {
            if (sg.skittleUp[i]) {
                Rectangle pinRect = new Rectangle(sg.skittleposition[2 * i], sg.skittleposition[2 * i + 1], 20, 20);
                Rectangle ballRect = new Rectangle(ballposX, ballposy, 40, 40);
                if (ballRect.intersects(pinRect)) {
                    sg.dropSkittle(i);
                    totalPins++;
                    if (totalPins == 10) {
                        play = false;
                    }
                }
            }
        }
        if (ballposy <= 0) {
            return;
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            ballposX = ballposX <= 0 ? 0 : moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            ballposX = ballposX >= 100 ? 100 : moveRight();
        }

    }

    public int moveRight() {
        play = true;
        return ballposX + 20;
    }

    public int moveLeft() {
        play = true;
        return ballposX - 20;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
