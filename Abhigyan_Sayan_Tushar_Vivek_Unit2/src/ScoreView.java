
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;

class helper {
    ArrayList<String> getMaxScorer(HashMap<Integer, ArrayList<String>> scoretoname, PriorityQueue<Integer> maxheap) {
        ArrayList<String> maxscore = new ArrayList<String>();
        maxscore = scoretoname.get(maxheap.peek());
        return maxscore;
    }

    ArrayList<String> getMinScorer(HashMap<Integer, ArrayList<String>> scoretoname, PriorityQueue<Integer> minheap) {
        ArrayList<String> minscore = new ArrayList<String>();
        minscore = scoretoname.get(minheap.peek());
        return minscore;
    }

    ArrayList<String> getMax3scorer(HashMap<Integer, ArrayList<String>> scoretoname, PriorityQueue<Integer> maxheap) {
        ArrayList<String> max3score = new ArrayList<String>();
        Iterator<Integer> itr = maxheap.iterator();
        ArrayList<Integer> top3 = new ArrayList<Integer>();
        int counter = 1;
        while (itr.hasNext() && counter < 4) {
            top3.add(itr.next());
            counter++;
        }
        for (int i = 0; i < top3.size(); i++) {
            int temp = top3.get(i);
            max3score.addAll(scoretoname.get(temp));
        }
        return max3score;
    }

    ArrayList<String> getSortedList(HashMap<Integer, ArrayList<String>> scoretoname, PriorityQueue<Integer> maxheap) {
        ArrayList<String> sortedlist = new ArrayList<String>();
        Iterator<Integer> itr = maxheap.iterator();
        ArrayList<Integer> allnames = new ArrayList<Integer>();
        while (itr.hasNext()) {
            allnames.add(itr.next());
        }
        for (int i = 0; i < allnames.size(); i++) {
            int temp = allnames.get(i);
            sortedlist.addAll(scoretoname.get(temp));
        }
        return sortedlist;
    }
}

public class ScoreView implements ActionListener {
    private final JFrame win;
    private JList bowlerList;
    private final ControlDeskView controlDesk;
    private JButton maxScore, minScore, top3Players, scores;
    private JLabel bowlerLabel;
    String st;
    PriorityQueue<Integer> maxheap = new PriorityQueue<Integer>(Collections.reverseOrder());
    PriorityQueue<Integer> minheap = new PriorityQueue<Integer>();
    HashMap<Integer, ArrayList<String>> scoretoname = new HashMap<Integer, ArrayList<String>>();

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

        JPanel buttonPanel = new JPanel();
        bowlerLabel = new JLabel("Output:");

        maxScore = new JButton("Max score");
        JPanel maxScorePanel = new JPanel();
        maxScorePanel.setLayout(new FlowLayout());
        maxScore.addActionListener(this);
        maxScorePanel.add(maxScore);
        // bowlerPanel.add(maxScorePanel);

        minScore = new JButton("Min score");
        JPanel minScorePanel = new JPanel();
        minScorePanel.setLayout(new FlowLayout());
        minScore.addActionListener(this);
        minScorePanel.add(minScore);
        // bowlerPanel.add(minScorePanel);

        top3Players = new JButton("Top 3 players");
        JPanel top3Panel = new JPanel();
        top3Panel.setLayout(new FlowLayout());
        top3Players.addActionListener(this);
        top3Panel.add(top3Players);
        // bowlerPanel.add(top3Panel);

        scores = new JButton("Scores");
        JPanel sortedScores = new JPanel();
        sortedScores.setLayout(new FlowLayout());
        scores.addActionListener(this);
        sortedScores.add(scores);
        // bowlerPanel.add(sortedScores);
        buttonPanel.setLayout(new GridLayout(4, 1));
        buttonPanel.add(maxScorePanel);
        buttonPanel.add(minScorePanel);
        // buttonPanel.add(sortedScores);
        buttonPanel.add(top3Panel);
        buttonPanel.add(bowlerLabel);
        bowlerPanel.add(buttonPanel);

        win.getContentPane().add("Center", colPanel);
        win.pack();

        // Center Window on Screen
        Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
        win.setLocation(((screenSize.width) / 2) - ((win.getSize().width) / 2),
                ((screenSize.height) / 2) - ((win.getSize().height) / 2));
        win.show();

        try {
            File file = new File("SCOREHISTORY.DAT"); // Change file name
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((st = br.readLine()) != null) {
                // System.out.println(st);
                StringTokenizer stt = new StringTokenizer(st, "\t");
                String name = stt.nextToken();
                String datetime = stt.nextToken();
                String score = stt.nextToken();
                // System.out.println(name);
                // System.out.println(datetime);
                // System.out.println(score);
                int intscore = Integer.parseInt(score);
                // System.out.print(apair.getKey());
                // System.out.println(apair.getValue());
                maxheap.add(intscore);
                minheap.add(intscore);
                if (scoretoname.get(intscore) == null) {
                    ArrayList<String> temp = new ArrayList<String>();
                    temp.add(name);
                    scoretoname.put(intscore, temp);
                } else {
                    scoretoname.get(intscore).add(name);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void actionPerformed(ActionEvent e) {
        String outpuString = "Output:";
        if (e.getSource().equals(maxScore)) {
            helper help = new helper();
            ArrayList<String> max = new ArrayList<String>();
            max = help.getMaxScorer(scoretoname, maxheap);
            System.out.print("Max Score: ");
            for (int i = 0; i < max.size(); i++) {
                System.out.print(max.get(i));
                outpuString = outpuString.concat(max.get(i));
            }
            System.out.println();
        }
        if (e.getSource().equals(minScore)) {
            helper help = new helper();
            ArrayList<String> min = new ArrayList<String>();
            min = help.getMinScorer(scoretoname, minheap);
            System.out.print("Min Score: ");
            for (int i = 0; i < min.size(); i++) {
                System.out.print(min.get(i));
                outpuString = outpuString.concat(min.get(i));

            }
            System.out.println();
        }
        if (e.getSource().equals(top3Players)) {
            helper help = new helper();
            ArrayList<String> max3score = new ArrayList<String>();
            max3score = help.getMax3scorer(scoretoname, maxheap);
            System.out.print("Top 3 players are: ");
            for (int i = 0; i < max3score.size(); i++) {
                if (i == max3score.size() - 1) {
                    System.out.print(max3score.get(i));
                    outpuString = outpuString.concat(max3score.get(i));
                } else {
                    System.out.print(max3score.get(i) + ",");
                    outpuString = outpuString.concat(max3score.get(i)).concat(",");
                }
            }
            System.out.println();

        }
        if (e.getSource().equals(scores)) {
            helper help = new helper();
            ArrayList<String> sorted_scores = new ArrayList<String>();
            sorted_scores = help.getSortedList(scoretoname, maxheap);
            System.out.print("Players according to theirs scores are: ");
            for (int i = 0; i < sorted_scores.size(); i++) {
                if (i == sorted_scores.size() - 1) {
                    System.out.print(sorted_scores.get(i));
                    outpuString = outpuString.concat(sorted_scores.get(i));
                } else {
                    System.out.print(sorted_scores.get(i) + ",");
                    outpuString = outpuString.concat(sorted_scores.get(i));
                }
            }
            System.out.println();
        }
        bowlerLabel.setText(outpuString);
        win.revalidate();
        win.pack();

    }

}

class displayBoweler extends JPanel {
    public displayBoweler() {
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

    }
}