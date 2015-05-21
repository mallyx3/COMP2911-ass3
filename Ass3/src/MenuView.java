
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
@SuppressWarnings("serial")
public class MenuView extends JPanel{
	private Board gameState;
	public MenuView(Board gameState){
		super();
		this.gameState = gameState;
	}
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
	   
	    
	    JButton start = new JButton("Start");
	    start.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
                
                System.out.println("You clicked the button");
            }
        });   
	    
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton reset = new JButton("Reset");
	    start.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
                
                System.out.println("You clicked the reset");
            }
        });   
	    
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        JLabel title = new JLabel("cunnukt fore"); 
        add(title);
        add(firstButton);
    	add(secondButton);
    	add(start);
    	add(reset);
    	JLabel footer = new JLabel("we promice it wurks gud");
    	JLabel version = new JLabel("beeter 9.7");
    	add(footer);
    	add(version);
    	
        
		
		return menu;
		
	}
	
	
	
	
	
}
