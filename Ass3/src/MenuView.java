import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
@SuppressWarnings("serial")
public class MenuView extends JPanel{
	
	//private ArrayList<Dimensions> CircleSpots = new ArrayList<Dimensions>();
	public Container newContainer(){
			
		Container menu = new Container();
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		JRadioButton firstButton = new JRadioButton("One Player");
	    firstButton.setActionCommand("One Player");
	    firstButton.setSelected(true);

	    JRadioButton secondButton = new JRadioButton("Two Player");
	    secondButton.setActionCommand("Two Player");

	    // Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(firstButton);
	    group.add(secondButton);

	    // Register a listener for the radio buttons.
	    RadioListener myListener = new RadioListener();
	    firstButton.addActionListener(myListener);
	    firstButton.addChangeListener(myListener);
	    firstButton.addItemListener(myListener);
	    secondButton.addActionListener(myListener);
	    secondButton.addChangeListener(myListener);
	    secondButton.addItemListener(myListener);
	    JButton button = new JButton("Start");
	    button.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the button");
            }
        });   
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(firstButton);
    	add(secondButton);
    	add(button);
    	
        
		
		return menu;
		
	}
	
	
	
	
	
}
