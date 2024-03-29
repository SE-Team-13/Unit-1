import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.util.*;
import java.text.*;

public class EndGamePrompt implements ActionListener {

	private final JFrame win;
	private final JButton yesButton;
	private final JButton noButton;

	private int result;

	private String selectedNick, selectedMember;

	public EndGamePrompt( String partyName ) {

		result =0;
		
		win = new JFrame("Another Game for " + partyName + "?" );
		win.getContentPane().setLayout(new BorderLayout());
		((JPanel) win.getContentPane()).setOpaque(false);

		JPanel colPanel = new JPanel();
		colPanel.setLayout(new GridLayout( 2, 1 ));

		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new FlowLayout());
		
		JLabel message = new JLabel( "Party " + partyName 
			+ " has finished bowling.\nWould they like to bowl another game?" );

		labelPanel.add( message );

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2));

		Insets buttonMargin = new Insets(4, 4, 4, 4);

		yesButton = new JButton("Yes");
		JPanel yesButtonPanel = new JPanel();
		yesButtonPanel.setLayout(new FlowLayout());
		yesButton.addActionListener(this);
		yesButtonPanel.add(yesButton);

		noButton = new JButton("No");
		JPanel noButtonPanel = new JPanel();
		noButtonPanel.setLayout(new FlowLayout());
		noButton.addActionListener(this);
		noButtonPanel.add(noButton);

		buttonPanel.add(yesButton);
		buttonPanel.add(noButton);

		colPanel.add(labelPanel);
		colPanel.add(buttonPanel);

		win.getContentPane().add("Center", colPanel);

		win.pack();

		Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
		win.setLocation(
			((screenSize.width) / 2) - ((win.getSize().width) / 2),
			((screenSize.height) / 2) - ((win.getSize().height) / 2));

		win.show();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(yesButton)) {		
			result=1;
		}
		if (e.getSource().equals(noButton)) {		
			result=2;
		}

	}

	public int getResult() {
		while ( result == 0 ) {
			try {
				Thread.sleep(10);
			} catch ( InterruptedException e ) {
				System.err.println( "Interrupted" );
			}
		}
		return result;	
	}

	public void destroy() {
		win.hide();
	}

}

