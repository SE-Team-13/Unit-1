
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class ScoreView implements ActionListener {
    private final JFrame win;
    private final JButton Alphabetically;
    private final JButton highScoreFirst;
    private JList bowlerList;
    private final ControlDeskView controlDesk;

    public ScoreView(ControlDeskView controlDesk) {
        this.controlDesk = controlDesk;
        win = new JFrame("View Score");
        win.getContentPane().setLayout(new BorderLayout());
        ((JPanel) win.getContentPane()).setOpaque(false);

        JPanel colPanel = new JPanel();
        colPanel.setLayout(new GridLayout(1, 2));

        JPanel bowlerPanel = new JPanel();
        bowlerPanel.setLayout(new FlowLayout());
        bowlerPanel.setBorder(new TitledBorder("Bowler Database"));
        
        try{
            bowlerdb = new Vector(ScoreHistoryFile.getScores())
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));

        Insets buttonMargin = new Insets(4, 4, 4, 4);

        Alphabetically = new JButton("Alphabetically");
        JPanel alphabeticallyPanel = new JPanel();
        alphabeticallyPanel.setLayout(new FlowLayout());
        Alphabetically.addActionListener(this);
        alphabeticallyPanel.add(Alphabetically);

        highScoreFirst = new JButton("Finished");
        JPanel highScoreFirstPanel = new JPanel();
        highScoreFirstPanel.setLayout(new FlowLayout());
        highScoreFirst.addActionListener(this);
        highScoreFirstPanel.add(highScoreFirst);

        buttonPanel.add(alphabeticallyPanel);
        buttonPanel.add(highScoreFirstPanel);

        colPanel.add(bowlerPanel);
        colPanel.add(buttonPanel);

        win.getContentPane().add("Center", colPanel);
        win.pack();

        // Center Window on Screen
        Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
        win.setLocation(((screenSize.width) / 2) - ((win.getSize().width) / 2),
                ((screenSize.height) / 2) - ((win.getSize().height) / 2));
        win.show();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(Alphabetically)) {

        } else if (e.getSource().equals(highScoreFirst)) {

        }
    }
}
