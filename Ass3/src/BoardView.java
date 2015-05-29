//package ass3;



import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
@SuppressWarnings("serial")

public class BoardView extends JFrame{

	private int AISetting = 1;
	private static boolean colourBlindMode = false;
	private Board gameState;
	private ArrayList<ColumnView> ColumnList;
	private SideView menu;
	private JPanel test;
	private JButton singlePlayer = new JButton(new ImageIcon(getClass().getResource("/Art/singleplayer.png")));
	private JButton multiPlayer = new JButton(new ImageIcon(getClass().getResource("/Art/twoplayer.png")));
	private JButton threePlayer = new JButton(new ImageIcon(getClass().getResource("/Art/threeplayer.png")));
	private JButton easy = new JButton(new ImageIcon(getClass().getResource("/Art/easy.png")));
	private JButton medium = new JButton(new ImageIcon(getClass().getResource("/Art/mediumpressed.png")));
	private JButton hard = new JButton(new ImageIcon(getClass().getResource("/Art/hard.png")));
	private JButton backButton = new JButton("Back");
	private JButton colourBlind = new JButton(new ImageIcon(getClass().getResource("/Art/colourblind.png")));
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
		gameState = new Board(numPlayers);
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
					
					menu.repaint();
					if(gameState.hasWon() || gameState.checkDraw()){
						gameState.toggleGameState(false);
						for(int i = 0; i < 7; i++){
							ColumnList.get(i).repaint();
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
				
					ColumnList.get(gameState.getAITurn()).paintPiece();
					if(gameState.hasWon() || gameState.checkDraw()){
						gameState.toggleGameState(false);
						for(int i = 0; i < 7; i++){
							ColumnList.get(i).repaint();
						}
					}
					menu.repaint();
				AIDelay.stop();
			}
			
		});
		JButton resetButton = new JButton(new ImageIcon(getClass().getResource("/Art/Start.png")));
		resetButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(!gameState.ifFalling()){
					for(int i = 0; i < 7; i++){
						ColumnList.get(i).resetBoard();
						gameState.resetGame();
					}
				}
			}
			
		});
		
		menu = new SideView(gameState, colourBlindMode);
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		
		resetButton.setBorder(BorderFactory.createEmptyBorder());
		resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu.add(Box.createRigidArea(new Dimension(0,100)));
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
		
		test = new MenuView(singlePlayer, multiPlayer, threePlayer, colourBlind, easy, medium, hard);
		Columns.add(test);

	}
	public void initSinglePlayerGame(){
		
		test.setVisible(false);
		initBView(true, 2);
	
	}
	
	public void initMultiPlayerGame(int numPlayers){
		
		test.setVisible(false);
		initBView(false, numPlayers);
		
	}
	public void initButtons(){
		
		singlePlayer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				initSinglePlayerGame();			
			}
		});
		
		multiPlayer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				initMultiPlayerGame(2);
			}	
		});
		threePlayer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				initMultiPlayerGame(3);
			}
			
		});
		easy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(AISetting == 1){
					AISetting = 0;
					easy.setIcon(new ImageIcon(getClass().getResource("/Art/easypressed.png")));
					medium.setIcon(new ImageIcon(getClass().getResource("/Art/medium.png")));
				} else if(AISetting == 2){
					AISetting = 0;
					easy.setIcon(new ImageIcon(getClass().getResource("/Art/easypressed.png")));
					hard.setIcon(new ImageIcon(getClass().getResource("/Art/hard.png")));
				}
			}
		});
		medium.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(AISetting == 0){
					AISetting = 1;
					medium.setIcon(new ImageIcon(getClass().getResource("/Art/mediumpressed.png")));
					easy.setIcon(new ImageIcon(getClass().getResource("/Art/easy.png")));
				} else if(AISetting == 2){
					AISetting = 1;
					medium.setIcon(new ImageIcon(getClass().getResource("/Art/mediumpressed.png")));
					hard.setIcon(new ImageIcon(getClass().getResource("/Art/hard.png")));
				}
			}
		});
		hard.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(AISetting == 0){
					AISetting = 2;
					hard.setIcon(new ImageIcon(getClass().getResource("/Art/hardpressed.png")));
					easy.setIcon(new ImageIcon(getClass().getResource("/Art/easy.png")));
				} else if(AISetting == 1){
					AISetting = 2;
					hard.setIcon(new ImageIcon(getClass().getResource("/Art/hardpressed.png")));
					medium.setIcon(new ImageIcon(getClass().getResource("/Art/medium.png")));
				}
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
					colourBlind.setIcon(new ImageIcon(getClass().getResource("/Art/colourblindpressed.png")));
				} else {
					colourBlindMode = false;
					colourBlind.setIcon(new ImageIcon(getClass().getResource("/Art/colourblind.png")));
				}
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
