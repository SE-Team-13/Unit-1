import java.util.Vector;
import java.io.*;

public class drive {

	public static void main(String[] args) {

		int numLanes = 3;
		int maxPatronsPerParty = 6;

		ControlDesk controlDesk = new ControlDesk(numLanes);

		ControlDeskView cdv = new ControlDeskView(controlDesk, maxPatronsPerParty);
		controlDesk.subscribe(cdv);

	}
}
