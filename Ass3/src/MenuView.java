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
	JRadioButton firstButton;
	JRadioButton secondButton;
	JRadioButton thirdButton;
	ButtonGroup group;
	JButton startButton;
	public MenuView(Board gameState){
		super();
		this.gameState = gameState;
	}
	//private ArrayList<Dimensions> CircleSpots = new ArrayList<Dimensions>();
	public Container newContainer(){
			
		Container menu = new Container();
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		firstButton = new JRadioButton("One Player");
		firstButton.setActionCommand("One Player");
	    firstButton.setSelected(true);

	    secondButton = new JRadioButton("Two Player");
	    secondButton.setActionCommand("Two Player");
	    
	    thirdButton = new JRadioButton("Three Player");
	    // Group the radio buttons.
	    group = new ButtonGroup();
	    group.add(firstButton);
	    group.add(secondButton);
	    group.add(thirdButton);

	    // Register a listener for the radio buttons.
	    //RadioListener myListener = new RadioListener();
	    firstButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		gameState.turnOnAI();
	    		gameState.twoPlayer();
	    	}
	    });
	   // firstButton.addChangeListener(myListener);
	  //  firstButton.addItemListener(myListener);
	    secondButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		gameState.turnOffAI();
	    		gameState.twoPlayer();
	    	}
	    });
	    thirdButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		gameState.turnOffAI();
	    		gameState.threePlayer();
	    	}
	    });
	  //  secondButton.addChangeListener(myListener);
	  //  secondButton.addItemListener(myListener);
	  
	    
	    startButton = new JButton("Start");
	    startButton.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
                gameState.startGame();
                System.out.println("You clicked the button");
            }
        });   
	    
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        

        
        
        JLabel title = new JLabel("cunnukt fore"); 
        add(title);
        add(firstButton);
    	add(secondButton);
    	add(thirdButton);
    	add(startButton);
    	
    	
        
		
		return menu;
		
	}
	public void addReset(JButton resetButton){
		resetButton.setEnabled(false);
		add(resetButton);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				resetButton.setEnabled(false);
				firstButton.setEnabled(true);
				secondButton.setEnabled(true);
				thirdButton.setEnabled(true);
				startButton.setEnabled(true);
			}
		});
		startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				resetButton.setEnabled(true);
				firstButton.setEnabled(false);
				secondButton.setEnabled(false);
				thirdButton.setEnabled(false);
				startButton.setEnabled(false);
			}
		});
	}
}

