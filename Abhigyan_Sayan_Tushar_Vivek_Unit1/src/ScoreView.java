
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class ScoreView {
    private final JFrame win;
    private JList bowlerList;
    private final ControlDeskView controlDesk;
    private Vector bowlerdb;

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

        try {
            bowlerdb = new Vector(ScoreHistoryFile.getAllScores());
        } catch (Exception e) {
            System.err.println("File Error");
            bowlerdb = new Vector();
        }

        Vector<String> columnNames = new Vector<String>(3);
        columnNames.add("Name");
        columnNames.add("Date");
        columnNames.add("Score");

        JTable table = new JTable(bowlerdb, columnNames);
        table.setAutoCreateRowSorter(true);
        // bowlerList = new JList(bowlerdb);
        // bowlerList.setVisibleRowCount(8);
        // bowlerList.setFixedCellWidth(200);

        JScrollPane bowlerPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        bowlerPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        bowlerPanel.add(bowlerPane);

        colPanel.add(bowlerPanel);

        win.getContentPane().add("Center", colPanel);
        win.pack();

        // Center Window on Screen
        Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
        win.setLocation(((screenSize.width) / 2) - ((win.getSize().width) / 2),
                ((screenSize.height) / 2) - ((win.getSize().height) / 2));
        win.show();
    }

}
