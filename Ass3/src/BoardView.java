//package ass3;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;
@SuppressWarnings("serial")

public class BoardView extends JFrame{

	private int AISetting = 1;
	private static boolean colourBlindMode = false;
	private Board gameState;
	private ArrayList<ColumnView> ColumnList;
	private JPanel menu;
	private JPanel test = new JPanel();
	private JButton singlePlayer = new JButton("Single Player");
	private JButton multiPlayer = new JButton("MultiPlayer");
	private JPanel easy = new JPanel();
	private JPanel medium = new JPanel();
	private JPanel hard = new JPanel();
	private JButton backButton = new JButton("Back");
	private JButton colourBlind = new JButton("Colourblind mode");
	private Container Columns;
	private Timer AIDelay;
	
	public BoardView(){
		Columns = getContentPane();
		Columns.setLayout(new BoxLayout(Columns, BoxLayout.X_AXIS));
		
		initButtons();
		initMenuScreen();
		setTitle("Connect 4");
        setSize(1250, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void initBView(boolean AIGame, int numPlayers){
		gameState = new Board();
		gameState.toggleAI(AIGame);
		gameState.setAI(AISetting);
		gameState.toggleGameState(true);
		ColumnList = new ArrayList<ColumnView>();
		
		for(int i = 0; i < 7;i++){
			ColumnList.add(new ColumnView(i, gameState, colourBlindMode));
			ColumnList.get(i).setBackground(new Color(180,230,255));
			ColumnList.get(i).setPreferredSize(new Dimension(150,800));
			ColumnList.get(i).setMaximumSize(new Dimension(150,800));
			ColumnList.get(i).addMouseListener(new MouseAdapter(){
				public void mouseReleased(MouseEvent e){
					if(gameState.hasWon() || gameState.checkDraw()){
						gameState.toggleGameState(false);
						for(int i = 0; i < 7; i++){
							ColumnList.get(i).paintWinPieces();
						}
					}
					if(gameState.isAI() && gameState.isRunning() && gameState.getPlayer() == 1){
						gameState.makeAITurn();
						AIDelay.start();
					}
					
				}
			});
		}
		AIDelay = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
					ColumnList.get(gameState.getAITurn()).paintAITurn();
					if(gameState.hasWon() || gameState.checkDraw()){
						gameState.toggleGameState(false);
						for(int i = 0; i < 7; i++){
							ColumnList.get(i).paintWinPieces();
						}
					}
				
				AIDelay.stop();
			}
			
		});
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 7; i++){
					ColumnList.get(i).resetBoard();
					gameState.resetGame();
				}
			}
			
		});
		
		menu = new JPanel();
		resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu.add(resetButton);
        menu.add(backButton);
		menu.setPreferredSize(new Dimension(200,800));
		menu.setMaximumSize(new Dimension(200,800));
		menu.setBackground(Color.LIGHT_GRAY);
		Columns = getContentPane();
		Columns.setLayout(new BoxLayout(Columns, BoxLayout.X_AXIS));
		for(int i = 0;i < 7; i++){
			Columns.add(ColumnList.get(i));
		}
		Columns.add(menu);
		
		setTitle("Connect 4");
        setSize(1250, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
        
            @Override
            public void run() {
                BoardView ex = new BoardView();
               
                ex.setVisible(true);
                ex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
	
	public void initMenuScreen(){
		
		test = new MenuView(singlePlayer, multiPlayer, colourBlind, easy, medium, hard);
		Columns.add(test);

	}
	public void initSinglePlayerGame(){
		
		test.setVisible(false);
		initBView(true, 2);
	
	}
	
	public void initMultiPlayerGame(){
		
		test.setVisible(false);
		initBView(false, 2);
		
	}
	public void initButtons(){
		
		singlePlayer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				initSinglePlayerGame();			
			}
		});
		
		multiPlayer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				initMultiPlayerGame();
			}	
		});
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				returnToMenu();
			}
		});
		colourBlind.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!colourBlindMode){
					colourBlindMode = true;
				} else {
					colourBlindMode = false;
				}
			}
		});
		
		//easy.setAlignmentX(LEFT_ALIGNMENT);
		easy.addMouseListener(new MouseListener(){
			

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(AISetting!= 0){
					easy.setBackground(Color.BLUE);
				}
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(AISetting == 0){
					easy.setBackground(new Color(0,0,200));
				} else {
					easy.setBackground(Color.RED);
				}
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				AISetting = 0;
				easy.setBackground(new Color(0,0,200));
				medium.setBackground(Color.RED);
				hard.setBackground(Color.RED);
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		medium.addMouseListener(new MouseListener(){
			

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(AISetting!= 1){
					medium.setBackground(Color.BLUE);
				}
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(AISetting == 1){
					medium.setBackground(new Color(0,0,200));
				} else {
					medium.setBackground(Color.RED);
				}
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				AISetting = 1;
				easy.setBackground(Color.RED);
				medium.setBackground(new Color(0,0,200));
				hard.setBackground(Color.RED);
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		hard.addMouseListener(new MouseListener(){
			

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(AISetting!= 2){
					hard.setBackground(Color.BLUE);
				}
				
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(AISetting == 2){
					hard.setBackground(new Color(0,0,200));
				} else {
					hard.setBackground(Color.RED);
				}
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				AISetting = 2;
				easy.setBackground(Color.RED);
				medium.setBackground(Color.RED);
				hard.setBackground(Color.BLUE);
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	public void returnToMenu(){
		for(int i = 0;i < 7; i++){
			ColumnList.get(i).setVisible(false);
		}
		menu.setVisible(false);
		test.setVisible(true);
	}
	
}
