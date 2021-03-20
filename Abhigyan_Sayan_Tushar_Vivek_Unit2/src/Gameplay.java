import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    public int totalPins = 0;
    private Timer timer;
    private int delay = 8;
    private double ballposX = 40;
    private double ballposy = 550;
    private double ballXdir = 0;
    private double ballYdir = -1;
    public SkittleGenerator sg;
    public boolean gameFinished = false;

    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
        gameFinished = false;
        sg = new SkittleGenerator();

    }

    public Gameplay(int pins[]) {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        gameFinished = false;
        timer = new Timer(delay, this);
        timer.start();
        sg = new SkittleGenerator(pins);
    }

    @Override
    public void paint(Graphics g) {
        // background
        g.setColor(Color.gray);
        g.fillRect(40, 0, 100, 600);
        // Gutter
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 40, 600);
        g.fillRect(140, 0, 40, 600);
        // Skittles
        sg.draw((Graphics2D) g);
        // Ball
        g.setColor(Color.blue);
        g.fillOval((int) ballposX, (int) ballposy, 40, 40);
        // Turn indicator
        if (!play) {
            g.setColor(Color.RED);
            g.drawLine((int) ballposX + 20, (int) ballposy + 20, (int) (ballposX + 20 + ballXdir * 20),
                    (int) (ballposy + 20 + ballYdir * 20));
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        timer.start();
        if (play) {
            if (ballposX > 0 && ballposX < 140)
                ballposX += ballXdir;
            ballposy += ballYdir;
        }
        for (int i = 0; i < 10; i++) {
            if (sg.skittleUp[i]) {
                Rectangle pinRect = new Rectangle(sg.skittleposition[2 * i], sg.skittleposition[2 * i + 1], 22, 22);
                Rectangle ballRect = new Rectangle((int) ballposX, (int) ballposy, 40, 40);
                if (ballRect.intersects(pinRect)) {
                    sg.dropSkittle(i);
                    totalPins++;
                    if (totalPins == 10) {
                        reset();
                        return;
                    }
                }
            }
        }
        if (ballposy <= 0) {
            reset();
            return;
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public void reset() {
        play = false;
        gameFinished = true;
        ballposy = 550;
        ballposX = 40;
        ballXdir = 0;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (play == true)
            return;

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            ballposX = ballposX <= 0 ? 0 : moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            ballposX = ballposX >= 140 ? 140 : moveRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            ballXdir = ballXdir >= 5 ? 5 : ballXdir + 0.02;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            ballXdir = ballXdir <= -5 ? -5 : ballXdir - 0.02;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            play = true;
        }

    }

    public double moveRight() {
        return ballposX + 20;
    }

    public double moveLeft() {
        return ballposX - 20;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
