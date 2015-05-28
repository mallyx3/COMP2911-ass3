//package ass3;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
@SuppressWarnings("serial")
public class MenuView extends JPanel{

	public MenuView(JButton singlePlayer, JButton multiPlayer, JButton threePlayer, JButton colourBlind, JButton easy, JButton medium, JButton hard){
		super();
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(1250,800));
		setMaximumSize(new Dimension(1250,800));
		Container startButtons = new Container();
		Container diffButtons = new Container();
		startButtons.setLayout(new BoxLayout(startButtons, BoxLayout.X_AXIS));
		diffButtons.setLayout(new BoxLayout(diffButtons, BoxLayout.X_AXIS));
		
		singlePlayer.setBorder(BorderFactory.createEmptyBorder());
		singlePlayer.setAlignmentX(CENTER_ALIGNMENT);
		
		multiPlayer.setBorder(BorderFactory.createEmptyBorder());
		multiPlayer.setAlignmentX(CENTER_ALIGNMENT);
		
		threePlayer.setBorder(BorderFactory.createEmptyBorder());
		threePlayer.setAlignmentX(CENTER_ALIGNMENT);
		
		startButtons.add(singlePlayer);
		startButtons.add(Box.createRigidArea(new Dimension(15,0)));
		startButtons.add(multiPlayer);
		startButtons.add(Box.createRigidArea(new Dimension(15,0)));
		startButtons.add(threePlayer);
		
		easy.setBorder(BorderFactory.createEmptyBorder());
		easy.setAlignmentX(CENTER_ALIGNMENT);
		
		medium.setBorder(BorderFactory.createEmptyBorder());
		medium.setAlignmentX(CENTER_ALIGNMENT);
		
		hard.setBorder(BorderFactory.createEmptyBorder());
		hard.setAlignmentX(CENTER_ALIGNMENT);
		
		colourBlind.setBorder(BorderFactory.createEmptyBorder());
		colourBlind.setAlignmentX(CENTER_ALIGNMENT);
		
		diffButtons.add(easy);
		diffButtons.add(Box.createRigidArea(new Dimension(10,0)));
		diffButtons.add(medium);
		diffButtons.add(Box.createRigidArea(new Dimension(10,0)));
		diffButtons.add(hard);
		diffButtons.add(Box.createRigidArea(new Dimension(100,0)));
		diffButtons.add(colourBlind);
		
		BoxLayout menuSetup = (new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(menuSetup);
		
		add(Box.createRigidArea(new Dimension(0,200)));
		add(startButtons);
		add(Box.createRigidArea(new Dimension(0,100)));
		add(diffButtons);
		add(Box.createRigidArea(new Dimension(0,100)));

		revalidate();
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		ImageIcon bg = new ImageIcon(getClass().getResource("/Art/bg.png"));
		bg.paintIcon(this,g,0,0);
	}
}