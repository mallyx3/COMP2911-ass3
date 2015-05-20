import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

 /** Listens to the radio buttons. */
    class RadioListener implements ActionListener, //only one event type needed
				   ChangeListener, //for curiosity only
				   ItemListener {  //for curiosity only
	public void actionPerformed(ActionEvent e) {
	    System.out.print("ActionEvent received: ");
	    if (e.getActionCommand() == "One Player") {
		System.out.println("One Player" + " pressed.");
	    } else {
		System.out.println("Two Player" + " pressed.");
	    }
	}

	public void itemStateChanged(ItemEvent e) {
	    System.out.println("ItemEvent received: " 
			       + e.getItem()
			       + " is now "
			       + ((e.getStateChange() == ItemEvent.SELECTED)?
				   "selected.":"unselected"));
	}

	public void stateChanged(ChangeEvent e) {
	    System.out.println("ChangeEvent received from: "
			       + e.getSource());
	}

    }