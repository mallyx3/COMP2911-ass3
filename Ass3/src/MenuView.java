//package ass3;



import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
@SuppressWarnings("serial")
public class MenuView extends JPanel{

	public MenuView(JButton singlePlayer, JButton multiPlayer, JButton colourBlind, JPanel easy, JPanel medium, JPanel hard){
		super();
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(1250,800));
		setMaximumSize(new Dimension(1250,800));
		
		singlePlayer.setBorder(BorderFactory.createEmptyBorder());
		singlePlayer.setAlignmentX(CENTER_ALIGNMENT);
		
		multiPlayer.setBorder(BorderFactory.createEmptyBorder());
		multiPlayer.setAlignmentX(CENTER_ALIGNMENT);
		
		easy.setBackground(Color.RED);
		easy.setPreferredSize(new Dimension(300,250));
		easy.setMaximumSize(new Dimension(300,250));
		
		medium.setBackground(new Color(0,0,200));
		medium.setPreferredSize(new Dimension(300,250));
		medium.setMaximumSize(new Dimension(300,250));
		
		hard.setBackground(Color.RED);
		hard.setPreferredSize(new Dimension(300,250));
		hard.setMaximumSize(new Dimension(300,250));
		
		GroupLayout menuLayout = new GroupLayout(this);
		setLayout(menuLayout);
		menuLayout.setAutoCreateGaps(true);
		menuLayout.setAutoCreateContainerGaps(true);
		menuLayout.setHorizontalGroup(
				menuLayout.createSequentialGroup()
					.addGroup(menuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(easy)
						.addComponent(medium)
						.addComponent(hard))
					.addGroup(menuLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(singlePlayer)
						.addComponent(multiPlayer)
						.addComponent(colourBlind))
		);
		menuLayout.setVerticalGroup(
				menuLayout.createSequentialGroup()
					.addGroup(menuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(easy)
						.addComponent(singlePlayer))
					.addGroup(menuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(medium)
						.addComponent(multiPlayer))
					.addGroup(menuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(hard)
						.addComponent(colourBlind))
		);
		add(singlePlayer);
		add(multiPlayer);
		add(easy);
		add(medium);
		add(hard);
		revalidate();
	}
	//private ArrayList<Dimensions> CircleSpots = new ArrayList<Dimensions>();
	public void addContainer(JButton reset, JButton back){
			
		Container layout = new Container();
		
		
		layout.setLayout(new BoxLayout(layout, BoxLayout.Y_AXIS));
		
		
		
		
		
		
		
		
		
	    
	    // Group the radio buttons.
	    

	    // Register a listener for the radio buttons.
	    //RadioListener myListener = new RadioListener();
	   
	   // firstButton.addChangeListener(myListener);
	  //  firstButton.addItemListener(myListener);
	    
	  //  secondButton.addChangeListener(myListener);
	  //  secondButton.addItemListener(myListener);
	  
	    
	   
        
      
        
    
    	
        
		
		
		
	}
	public void initButtons(){
		
	}
}
