//package ass3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
@SuppressWarnings("serial")
public class ColumnView extends JPanel{
	private int colNum;
	private int[] row;
	private int l = -1;
	private int yFall = 570;
	private Board gameState;
	private boolean mouseClicked = false;
	private boolean mouseEntered = false;
	private boolean colourBlindMode;
	private boolean pieceFalling = false;
	private Timer timeThing;
	//private ArrayList<Dimensions> CircleSpots = new ArrayList<Dimensions>();
	public ColumnView(int colNum, Board State, boolean colourBlind){
		super();
		
		timeThing = new Timer(5,new ActionListener(){
			public void actionPerformed(ActionEvent e){
				yFall = yFall + 4;
				if(yFall > 670 - 110*l){

					pieceFalling = false;
					gameState.pieceFalling();
				}

				timeThing.stop();
				repaint();
			}
		});
		this.colNum = colNum;
		this.gameState = State;
		colourBlindMode = colourBlind;
		this.row = new int[6];
		for(int k = 0;k < 6;k++){
			row[k] = 0;
		}
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){	
			}
			public void mousePressed(MouseEvent e){
				if(!gameState.ifAITurn()){
					mouseClicked = true;
					paintPiece();
				}
			}
			public void mouseReleased(MouseEvent e){
				mouseClicked = false;
				if(!pieceFalling){
					repaint();
				}
			}
			public void mouseEntered(MouseEvent e){
				mouseEntered = true;
				if(!pieceFalling){
					repaint();
				}
			}
			public void mouseExited(MouseEvent e){
				mouseEntered = false;
				if(!pieceFalling){
					repaint();
				}
			}
		});
		
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		boolean isWinPiece;
		boolean gameFinished = false;
		/*if(mouseClicked && gameState.isRunning()){
			setBackground(new Color(0,0,150));
		} else if(mouseEntered && gameState.isRunning()){
			setBackground(Color.BLUE);
		} else {
			setBackground(new Color(0,0,200));
		}*/
		if(!gameState.isRunning()){
			gameFinished = true;
			//setBackground(new Color(0,0,200));
		}
		for(int i = 0; i < 6; i++){
			isWinPiece = false;
			if(gameState.isWinPiece(colNum, i)){
				isWinPiece = true;
			}
			/*if(i == l && pieceFalling){
				
				g.setColor(Color.WHITE);
				g.fillOval(30, 670-110*i, 90, 90);
					
				g.setColor(Color.BLACK);
				g.drawOval(30, 670-110*i, 90, 90);
				
			}*/
			ImageIcon newPiece = null;
			if(row[i] == 1){
				if(!colourBlindMode){
					if(!isWinPiece && gameFinished){
						newPiece = new ImageIcon(getClass().getResource("Art/CoinY.png"));
					} else {
						newPiece = new ImageIcon(getClass().getResource("Art/CoinY.png"));
					}
				} else {
					if(!isWinPiece && gameFinished){
						g.setColor(new Color(0,175,0));
					} else {
						g.setColor(Color.GREEN);
					}
					
				}
			} else if (row[i] == 2){
				if(!isWinPiece && gameFinished){
					newPiece = new ImageIcon(getClass().getResource("Art/CoinR.png"));
				} else {
					newPiece = new ImageIcon(getClass().getResource("Art/CoinR.png"));
				}
			}  else if ((i == 0 || row[i-1] != 0) && !gameFinished && mouseEntered){
				if(gameState.isAI() || gameState.getPlayer() == 2){
					if(!colourBlindMode){
						g.setColor(new Color(255,255,100));
					} else {
						g.setColor(new Color(100,255,100));
					}
				} else {
					g.setColor(new Color(255,100,100));
				}
			} else {
				
			}
			if(i == l && pieceFalling){
				
				newPiece.paintIcon(this, g, 30, yFall);
				
			} else {
				if(row[i] != 0){
					newPiece.paintIcon(this, g, 30, 670-110*i);
				}
			}
			
			
			
		}
		ImageIcon column = new ImageIcon(getClass().getResource("Art/board.png"));
		column.paintIcon(this,g,0,100);
		if(pieceFalling){
			timeThing.start();
		}
	}
	public int getCol(){
		return colNum;
	}
	public void paintAITurn(){
		paintPiece();
	}
	public void paintPiece(){
		if(gameState.addPiece(getCol())){
			boolean painted = false;
			yFall = 20;
			pieceFalling = true;
			gameState.pieceFalling();
			for(int i = 0; i < 6 && !painted;i++){
				if(row[i] == 0){
					l = i;
					row[i] = gameState.getPlayer();
					painted = true;
					repaint();
				}
			}
		}
	}
	public void resetBoard(){
		for(int i = 0;i < 6; i++){
			row[i] = 0;
		}
		repaint();
	}
	public void paintWinPieces(){
		repaint();
	}
}