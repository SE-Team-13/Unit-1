
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.util.*;

public class EndGameReport implements ActionListener, ListSelectionListener {

	private final JFrame win;
	private final JButton printButton;
	private final JButton finished;
	private final JList memberList;
	private final Vector retVal;

	private int result;

	private String selectedMember;

	public EndGameReport( String partyName, Party party ) {
	
		result =0;
		retVal = new Vector();
		win = new JFrame("End Game Report for " + partyName + "?" );
		win.getContentPane().setLayout(new BorderLayout());
		((JPanel) win.getContentPane()).setOpaque(false);

		JPanel colPanel = new JPanel();
		colPanel.setLayout(new GridLayout( 1, 2 ));

		JPanel partyPanel = new JPanel();
		partyPanel.setLayout(new FlowLayout());
		partyPanel.setBorder(new TitledBorder("Party Members"));

		Vector myVector = new Vector();
		Iterator iter = (party.getMembers()).iterator();
		while (iter.hasNext()){
			myVector.add(((Bowler)iter.next()).getNickName());
		}	
		memberList = new JList(myVector);
		memberList.setFixedCellWidth(120);
		memberList.setVisibleRowCount(5);
		memberList.addListSelectionListener(this);
		JScrollPane partyPane = new JScrollPane(memberList);
		partyPanel.add(partyPane);

		partyPanel.add( memberList );

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, 1));

		printButton = new JButton("Print Report");
		JPanel printButtonPanel = new JPanel();
		printButtonPanel.setLayout(new FlowLayout());
		printButton.addActionListener(this);
		printButtonPanel.add(printButton);

		finished = new JButton("Finished");
		JPanel finishedPanel = new JPanel();
		finishedPanel.setLayout(new FlowLayout());
		finished.addActionListener(this);
		finishedPanel.add(finished);

		buttonPanel.add(printButton);
		buttonPanel.add(finished);
		colPanel.add(partyPanel);
		colPanel.add(buttonPanel);

		win.getContentPane().add("Center", colPanel);

		win.pack();
		Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
		win.setLocation(
			((screenSize.width) / 2) - ((win.getSize().width) / 2),
			((screenSize.height) / 2) - ((win.getSize().height) / 2));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(printButton)) 
			retVal.add(selectedMember);
		if (e.getSource().equals(finished))
			result = 1;
	}

	public void valueChanged(ListSelectionEvent e) {
		selectedMember =
			((String) ((JList) e.getSource()).getSelectedValue());
	}


	public Vector getResult() {
		while ( result == 0 ) {
			try {
				Thread.sleep(10);
			} catch ( InterruptedException e ) {
				System.err.println( "Interrupted" );
			}
		}
		return retVal;	
	}
}

