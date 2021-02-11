import java.util.*;
import java.io.*;

class ControlDeskSub extends Thread {

	/* The collection of subscribers */
    private Vector sub;

    /*  Constructor for subscribers */
    public ControlDeskSub() {
        sub = new Vector();
    }

    /**
     * Allows objects to subscribe as observers
     * 
     * @param adding the ControlDeskObserver that will be subscribed
     *
     */

    public void subscribe(ControlDeskObserver adding) {
		sub.add(adding);
	}

	/**
     * Broadcast an event to subscribing objects.
     * 
     * @param event	the ControlDeskEvent to broadcast
     *
     */

	public void publish(ControlDeskEvent event) {
		Iterator eventIterator = sub.iterator();
		while (eventIterator.hasNext()) {
			(
				(ControlDeskObserver) eventIterator
					.next())
					.receiveControlDeskEvent(event);
		}
	}


}