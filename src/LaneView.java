/*
 *  constructs a prototype Lane View
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class LaneView implements LaneObserver, ActionListener {

	private int roll;
	private boolean initDone = true;

	private int numplayers;
	int final_score[] = new int[6];  //max no. of players (i.e, bowlers) allowed=6
	

	JFrame frame;
	Container cpanel;
	Vector bowlers;
	int cur;
	Iterator bowlIt;

	JPanel[][] balls;
	JLabel[][] ballLabel;
	JPanel[][] scores;
	JLabel[][] scoreLabel;
	JPanel[][] ballGrid;
	JPanel[] pins;

	JButton maintenance;
	Lane lane;

	public LaneView(Lane lane, int laneNum) {

		this.lane = lane;

		initDone = true;
		frame = new JFrame("Lane " + laneNum + ":");
		cpanel = frame.getContentPane();
		cpanel.setLayout(new BorderLayout());

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				frame.hide();
			}
		});

		cpanel.add(new JPanel());

	}

	public void show() {
		frame.show();
	}

	public void hide() {
		frame.hide();
	}

	private JPanel makeFrame(Party party, JPanel[][] panels, JPanel[][] panels2) {  //making the layout and setting selected bowlers name

		initDone = false;
		bowlers = party.getMembers();
		int numBowlers = bowlers.size();
		
		numplayers = numBowlers;

		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(0, 1));

		balls = new JPanel[numBowlers][25];
		ballLabel = new JLabel[numBowlers][25];
		scores = new JPanel[numBowlers][11];
		scoreLabel = new JLabel[numBowlers][11];
		ballGrid = new JPanel[numBowlers][11];
		pins = new JPanel[numBowlers];

		for (int i = 0; i != numBowlers; i++) {
			for (int j = 0; j != 23; j++) {
				ballLabel[i][j] = new JLabel(" ");
				balls[i][j] = new JPanel();
				balls[i][j].setBorder(BorderFactory.createLineBorder(Color.RED));
				balls[i][j].add(ballLabel[i][j]);
			}

			//created for showing rank,emoticon
			for (int j = 23; j != 25; j++) {  
				ballLabel[i][j] = new JLabel(" ");
				balls[i][j] = new JPanel();
				balls[i][j].setBorder(BorderFactory.createLineBorder(Color.RED));
				balls[i][j].add(ballLabel[i][j]);
			}

			for (int j = 0; j != 9; j++) {
				ballGrid[i][j] = new JPanel();
				ballGrid[i][j].setLayout(new GridLayout(0, 3));
				ballGrid[i][j].add(new JLabel(" "), BorderLayout.EAST);
				ballGrid[i][j].add(balls[i][2 * j], BorderLayout.EAST);
				ballGrid[i][j].add(balls[i][2 * j + 1], BorderLayout.EAST);
			}
 

			//created for showing rank,emoticon
			for (int j = 10; j != 11; j++) {
				ballGrid[i][j] = new JPanel();
				ballGrid[i][j].setLayout(new GridLayout(0, 3));
				ballGrid[i][j].add(new JLabel("Rank: "), BorderLayout.EAST);
				ballGrid[i][j].add(balls[i][2 * j], BorderLayout.EAST);
				ballGrid[i][j].add(balls[i][2 * j + 1], BorderLayout.EAST);
			}

			int j = 9;  //originally, 9
			ballGrid[i][j] = new JPanel();
			ballGrid[i][j].setLayout(new GridLayout(0, 3));
			ballGrid[i][j].add(balls[i][2 * j]);
			ballGrid[i][j].add(balls[i][2 * j + 1]);
			ballGrid[i][j].add(balls[i][2 * j + 2]);
           
            

			pins[i] = new JPanel();
			pins[i].setBorder(BorderFactory.createTitledBorder(((Bowler) bowlers.get(i)).getNickName()));  //writing player name
			pins[i].setLayout(new GridLayout(0, 11));
			for (int k = 0; k != 10; k++) {
				scores[i][k] = new JPanel();
				scoreLabel[i][k] = new JLabel(" ", SwingConstants.CENTER);
				scores[i][k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				scores[i][k].setLayout(new GridLayout(0, 1));
				scores[i][k].add(ballGrid[i][k], BorderLayout.EAST);



				scores[i][k].add(scoreLabel[i][k], BorderLayout.SOUTH);  //giving score

				pins[i].add(scores[i][k], BorderLayout.CENTER);
			}


			//created for showing rank,emoticon
			for (int k = 10; k != 11; k++) {
				scores[i][k] = new JPanel();
				scoreLabel[i][k] = new JLabel("emoji", SwingConstants.CENTER);
				scores[i][k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				scores[i][k].setLayout(new GridLayout(0, 1));
				scores[i][k].add(ballGrid[i][k], BorderLayout.CENTER);



				scores[i][k].add(scoreLabel[i][k], BorderLayout.SOUTH);  //giving score

				pins[i].add(scores[i][k], BorderLayout.EAST);
			}


			panel.add(pins[i]);
			
		}
        

		initDone = true;
		return panel;
	}

	public void receiveLaneEvent(LaneEvent le) {    //calculating and printing scores
		if (lane.isPartyAssigned()) {
			int numBowlers = le.getParty().getMembers().size();
			while (!initDone) {
				// System.out.println("chillin' here.");
				try {
					Thread.sleep(1);
				} catch (Exception e) {
				}
			}

			if (le.getdata(2) == 1 && le.getdata(4) == 0 && le.getdata(3) == 0) {
				System.out.println("Making the frame.");
				cpanel.removeAll();
				cpanel.add(makeFrame(le.getParty(), scores, scores), "Center");

				// Button Panel
				JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new FlowLayout());

				Insets buttonMargin = new Insets(4, 4, 4, 4);

				maintenance = new JButton("Maintenance Call");
				JPanel maintenancePanel = new JPanel();
				maintenancePanel.setLayout(new FlowLayout());
				maintenance.addActionListener(this);
				maintenancePanel.add(maintenance);

				buttonPanel.add(maintenancePanel);

				cpanel.add(buttonPanel, "South");

				frame.pack();

			}

			int[][] lescores = le.getCumulScore();
			for (int k = 0; k < numBowlers; k++) {

				for (int i = 0; i <= le.getdata(2) - 1; i++) {
					if (lescores[k][i] != 0)
					{
						scoreLabel[k][i].setText((new Integer(lescores[k][i])).toString()); //lescores[k][9]=final score of kth player

						if(i==9)
						{
							final_score[k]=lescores[k][i];
						}
					}
				}
				for (int i = 0; i < 21; i++) {
					if (((int[]) le.getScore().get(bowlers.get(k)))[i] != -1)
						if (((int[]) le.getScore().get(bowlers.get(k)))[i] == 10 && (i % 2 == 0 || i == 19))
							ballLabel[k][i].setText("X");
						else if (i > 0 && ((int[]) le.getScore().get(bowlers.get(k)))[i]
								+ ((int[]) le.getScore().get(bowlers.get(k)))[i - 1] == 10 && i % 2 == 1)
							ballLabel[k][i].setText("/");
						else if (((int[]) le.getScore().get(bowlers.get(k)))[i] == -2) {

							ballLabel[k][i].setText("F");
						} else
							ballLabel[k][i]
									.setText((new Integer(((int[]) le.getScore().get(bowlers.get(k)))[i])).toString());

				}

				//created for showing rank,emoticon
				int temp_arr[] = new int[6];
				int p,q,player_rank;
				for(p=0; p<6; p++)
				temp_arr[p]=final_score[p];
		
				Arrays.sort(temp_arr);
				
				for(p=5;p>=(6-numplayers);p--)
				{
					if(final_score[k]==temp_arr[p])
					{
						player_rank=6-p;
						ballLabel[k][21].setText((new Integer(player_rank)).toString()); //this player's rank=6-p
						if(player_rank==1)
						scoreLabel[k][10].setText("\uD83D\uDE0E");  //for rank 1
						else if(player_rank==numplayers)
						scoreLabel[k][10].setText("\uD83D\uDE2D"); //for last rank
						else
						scoreLabel[k][10].setText("\uD83D\uDE42");  //for others
						break;
					}

				} 
				
			}
	

		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(maintenance)) {
			lane.pauseGame();
		}
	}


	

}
