
/**
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

import java.util.*;
import java.io.*;

public class ScoreHistoryFile {

	private static final String SCOREHISTORY_DAT = "SCOREHISTORY.DAT";
	// private static final String SCOREHISTORY_DAT =
	// "/home/viviek/Desktop/Sem_4/SE/UNIT2Q3/src/SCOREHISTORY.DAT"; //Change file
	// name
	// private static final String TEMPSCORE_DAT =
	// "/home/viviek/Desktop/Sem_4/SE/UNIT2Q3/src/TEMP.DAT"; //Change file name

	public static void addScore(String nick, String date, String score) throws IOException {

		String data = nick + "\t" + date + "\t" + score + "\n";

		RandomAccessFile out = new RandomAccessFile(SCOREHISTORY_DAT, "rw");
		// RandomAccessFile out_temp = new RandomAccessFile(TEMPSCORE_DAT, "rw");

		out.skipBytes((int) out.length());
		out.writeBytes(data);
		out.close();

		out_temp.skipBytes((int) out_temp.length());
		out_temp.writeBytes(data);
		out_temp.close();
	}

	public static Vector getAllScores() throws IOException {
		Vector scores = new Vector();
		BufferedReader in = new BufferedReader(new FileReader(SCOREHISTORY_DAT));
		String data;
		while ((data = in.readLine()) != null) {
			String[] scoredata = data.split("\t");
			Vector row = new Vector<String>();
			for (int i = 0; i < 3; i++) {
				row.addElement(scoredata[i]);
			}
			scores.addElement(row);
		}
		return scores;
	}

	public static Vector getScores(String nick) throws IOException {
		Vector scores = new Vector();

		BufferedReader in = new BufferedReader(new FileReader(SCOREHISTORY_DAT));
		String data;
		while ((data = in.readLine()) != null) {
			// File format is nick\tfname\te-mail
			String[] scoredata = data.split("\t");
			// "Nick: scoredata[0] Date: scoredata[1] Score: scoredata[2]
			if (nick.equals(scoredata[0])) {
				scores.add(new Score(scoredata[0], scoredata[1], scoredata[2]));
			}
		}
		return scores;
	}

}
