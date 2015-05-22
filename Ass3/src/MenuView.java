
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
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
	JButton resetButton;
	JRadioButton easy;
	JRadioButton medium;
	JRadioButton hard;
	ButtonGroup Difficulties;
	public MenuView(Board gameState){
		super();
		this.gameState = gameState;
	}
	//private ArrayList<Dimensions> CircleSpots = new ArrayList<Dimensions>();
	public void addContainer(JButton reset){
			
		Container layout = new Container();
		Container starterButtons = new Container();
		Container playerOptions = new Container();
		Container AIDifficulties = new Container();
		
		layout.setLayout(new BoxLayout(layout, BoxLayout.Y_AXIS));
		starterButtons.setLayout(new BoxLayout(starterButtons,BoxLayout.X_AXIS));
		playerOptions.setLayout(new BoxLayout(playerOptions,BoxLayout.X_AXIS));
		AIDifficulties.setLayout(new BoxLayout(AIDifficulties,BoxLayout.X_AXIS));
		
		easy = new JRadioButton("Easy");
	    easy.setActionCommand("Easy Mode");
	    easy.setSelected(true);
	    
	    medium = new JRadioButton("Medium");
	    medium.setActionCommand("Medium Mode");
	    
		hard = new JRadioButton("Hard");
		hard.setActionCommand("Hard Mode");
		
		Difficulties = new ButtonGroup();
		Difficulties.add(easy);
		Difficulties.add(medium);
		Difficulties.add(hard);
		
		easy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			gameState.setAI(0);
			}
		});
		
		medium.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			gameState.setAI(1);
			}
		});
		
		hard.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			gameState.setAI(2);
			}
		});
		
		
		
		
		
		firstButton = new JRadioButton("One");
		firstButton.setActionCommand("One Player");
	    firstButton.setSelected(true);

	    secondButton = new JRadioButton("Two");
	    secondButton.setActionCommand("Two Player");
	    
	    thirdButton = new JRadioButton("Three");
	    thirdButton.setActionCommand("Three Player");
	    
	    // Group the radio buttons.
	    group = new ButtonGroup();
	    group.add(firstButton);
	    group.add(secondButton);
	    group.add(thirdButton);

	    // Register a listener for the radio buttons.
	    //RadioListener myListener = new RadioListener();
	    firstButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		gameState.toggleAI(true);
	    		gameState.setPlayerNum(2);
	    		easy.setEnabled(true);
	    		medium.setEnabled(true);
	    		hard.setEnabled(true);
	    	}
	    });
	   // firstButton.addChangeListener(myListener);
	  //  firstButton.addItemListener(myListener);
	    secondButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		gameState.toggleAI(false);
	    		gameState.setPlayerNum(2);
	    		easy.setEnabled(false);
	    		medium.setEnabled(false);
	    		hard.setEnabled(false);
	    	}
	    });
	    thirdButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		gameState.toggleAI(false);
	    		gameState.setPlayerNum(3);
	    		easy.setEnabled(false);
	    		medium.setEnabled(false);
	    		hard.setEnabled(false);
	    	}
	    });
	  //  secondButton.addChangeListener(myListener);
	  //  secondButton.addItemListener(myListener);
	  
	    startButton = new JButton("Start");
	    startButton.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
                gameState.toggleGameState(true);
                System.out.println("You clicked the button");
            }
        });   
	    
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetButton = reset;
        addReset();
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel title = new JLabel("Number of Players"); 
        JLabel title2 = new JLabel("AI Difficulty Setting");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
    	starterButtons.add(startButton);
    	starterButtons.add(Box.createRigidArea(new Dimension(5,0)));
    	starterButtons.add(resetButton);
    	
    	playerOptions.add(firstButton);
    	playerOptions.add(Box.createRigidArea(new Dimension(5,0)));
    	playerOptions.add(secondButton);
    	playerOptions.add(Box.createRigidArea(new Dimension(5,0)));
    	playerOptions.add(thirdButton);
    	
    	AIDifficulties.add(easy);
    	AIDifficulties.add(Box.createRigidArea(new Dimension(5,0)));
    	AIDifficulties.add(medium);
    	AIDifficulties.add(Box.createRigidArea(new Dimension(5,0)));
    	AIDifficulties.add(hard);
    	
    	layout.add(starterButtons);
    	layout.add(Box.createRigidArea(new Dimension(0, 10)));
    	layout.add(title);
    	layout.add(Box.createRigidArea(new Dimension(0, 5)));
    	layout.add(playerOptions);
    	layout.add(Box.createRigidArea(new Dimension(0, 5)));
    	layout.add(title2);
    	layout.add(Box.createRigidArea(new Dimension(0, 5)));
    	layout.add(AIDifficulties);
    	
    	
        add(layout);
        
		
		
		
	}
	public void addReset(){
		resetButton.setEnabled(false);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				resetButton.setEnabled(false);
				firstButton.setEnabled(true);
				secondButton.setEnabled(true);
				thirdButton.setEnabled(true);
				startButton.setEnabled(true);
				if(gameState.isAI()){
					easy.setEnabled(true);
					medium.setEnabled(true);
					hard.setEnabled(true);	
				}
			}
		});
		startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				resetButton.setEnabled(true);
				firstButton.setEnabled(false);
				secondButton.setEnabled(false);
				thirdButton.setEnabled(false);
				startButton.setEnabled(false);
				easy.setEnabled(false);
				medium.setEnabled(false);
				hard.setEnabled(false);
			}
		});
	}
}
