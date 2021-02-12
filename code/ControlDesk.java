import java.util.*;
import java.io.*;

class ControlDesk extends Thread {

	/** The collection of Lanes */
	private HashSet lanes;

	/** The party wait queue */
	private Queue partyQueue;

	/** The number of lanes represented */
	private int numLanes;

	/** The collection of subscribers */
	// private Vector subscribers;
	private ControlDeskSub sub;

	/**
	 * Constructor for the ControlDesk class
	 *
	 * param numlanes the numbler of lanes to be represented
	 *
	 */

	public ControlDesk(int numLanes) {
		this.numLanes = numLanes;
		lanes = new HashSet(numLanes);
		partyQueue = new Queue();

		sub = new ControlDeskSub();

		for (int i = 0; i < numLanes; i++) {
			lanes.add(new Lane());
		}

		this.start();

	}

	/**
	 * Main loop for ControlDesk's thread
	 *
	 */
	public void run() {
		while (true) {

			assignLane();

			try {
				sleep(250);
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Iterate through the available lanes and assign the paties in the wait queue
	 * if lanes are available.
	 *
	 */

	public void assignLane() {
		Iterator it = lanes.iterator();

		while (it.hasNext() && partyQueue.hasMoreElements()) {
			Lane curLane = (Lane) it.next();

			if (curLane.isPartyAssigned() == false) {
				System.out.println("ok... assigning this party");
				curLane.assignParty(((Party) partyQueue.next()));
			}
		}
		sub.publish(new ControlDeskEvent(getPartyQueue()));
	}

	/**
	 * Creates a party from a Vector of nickNAmes and adds them to the wait queue.
	 *
	 * @param partyNicks A Vector of NickNames
	 *
	 */

	public void addPartyQueue(Vector partyNicks) {
		partyQueue.add(partyNicks);
		sub.publish(new ControlDeskEvent(getPartyQueue()));
	}

	/**
	 * Returns a Vector of party names to be displayed in the GUI representation of
	 * the wait queue.
	 *
	 * @return a Vecotr of Strings
	 *
	 */

	public Vector getPartyQueue() {
		return partyQueue.getPartyQueue();
	}

	/**
	 * Accessor for the number of lanes represented by the ControlDesk
	 *
	 * @return an int containing the number of lanes represented
	 *
	 */

	public int getNumLanes() {
		return numLanes;
	}

	/**
	 * Allows objects to subscribe as observers
	 *
	 * @param adding the ControlDeskObserver that will be subscribed
	 *
	 */

	public void subscribe(ControlDeskObserver adding) {
		sub.subscribe(adding);
	}

	/**
	 * Accessor method for lanes
	 *
	 * @return a HashSet of Lanes
	 *
	 */

	public HashSet getLanes() {
		return lanes;
	}
}
