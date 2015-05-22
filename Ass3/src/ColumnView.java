

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
@SuppressWarnings("serial")
public class ColumnView extends JPanel{
	private int colNum;
	private Integer[] row;
	private Board gameState;
	private boolean backgroundCheck = false;
	//private ArrayList<Dimensions> CircleSpots = new ArrayList<Dimensions>();
	public ColumnView(int colNum, Board gameState){
		super();
		this.colNum = colNum;
		this.gameState = gameState;
		this.row = new Integer[6];
		for(int k = 0;k < 6;k++){
			row[k] = 0;
		}
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){	
			}
			public void mousePressed(MouseEvent e){
				backgroundCheck = true;
				if(gameState.isRunning()){
					setBackground(new Color(0,0,150));
				}
				paintPiece();
			}
			public void mouseReleased(MouseEvent e){
				if(gameState.isRunning() && backgroundCheck){
					setBackground(Color.BLUE);
				}
			}
			public void mouseEntered(MouseEvent e){
				if(gameState.isRunning()){
					setBackground(Color.BLUE);
				}
			}
			public void mouseExited(MouseEvent e){
				backgroundCheck = false;
				setBackground(new Color(0,0,200));
			}
		});
		
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		ArrayList<Coordinates> winningPieces = gameState.getWinningPieces();
		boolean isWinPiece;
		boolean gameFinished = false;
		if(gameState.checkDraw()){
			gameFinished = true;
			setBackground(new Color(0,0,200));
		}
		for(int i = 0; i < 6; i++){
			isWinPiece = false;
			if(winningPieces.size() == 4){
				gameFinished = true;
				setBackground(new Color(0,0,200));
				for(int j = 0; j < 4; j++){
					if(winningPieces.get(j).getCol() == colNum && winningPieces.get(j).getRow() == i){
						isWinPiece = true;
					}
				}
			} 
			
			if(row[i] == 1){
				if(!isWinPiece && gameFinished){
					g.setColor(new Color(175,175,0));
				} else {
					g.setColor(Color.YELLOW);
				}
			} else if (row[i] == 2){
				if(!isWinPiece && gameFinished){
					g.setColor(new Color(175,0,0));
				} else {
					g.setColor(Color.RED);
				}
			} else if (row[i] == 3){
				if(!isWinPiece && gameFinished){
					g.setColor(new Color(0,175,0));
				} else {
					g.setColor(Color.GREEN);
				}
			} else {
				g.setColor(Color.WHITE);
			}
			
			g.fillOval(30,560-(110*i),90,90);
			
			g.setColor(Color.BLACK);
			g.drawOval(30,560-(110*i),90,90);
			
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
			for(int i = 0; i < 6 && !painted;i++){
				if(row[i] == 0){
					row[i] = gameState.getPlayer();
					painted = true;
					repaint(30,560-(110*i),90,90);
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