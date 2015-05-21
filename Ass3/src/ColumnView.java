

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
@SuppressWarnings("serial")
public class ColumnView extends JPanel{
	private int colNum;
	private Board gameState;
	//private ArrayList<Dimensions> CircleSpots = new ArrayList<Dimensions>();
	public ColumnView(int colNum, Board gameState){
		super();
		this.colNum = colNum;
		this.gameState = gameState;
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				int i = gameState.addPiece(getCol());
				if(i >= 0){
					repaint(30,560-(110*i),90,90);
				}
				
				
			}
			public void mouseEntered(MouseEvent e){
				//setBackground(Color.CYAN);
			}
			public void mouseExited(MouseEvent e){
				//setBackground(Color.BLUE);
			}
		});
		
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(gameState.getPlayer() == 1){
			g.setColor(Color.YELLOW);
		} else if(gameState.getPlayer() == 2){
			g.setColor(Color.RED);
		}else {
			g.setColor(Color.WHITE);
		}
		
		
		// offset offset x size y size
		g.fillOval(30, 10, 90, 90);
		g.fillOval(30, 120, 90, 90);
		g.fillOval(30, 230, 90, 90);
		g.fillOval(30, 340, 90, 90);
		g.fillOval(30, 450, 90, 90);
		g.fillOval(30, 560, 90, 90);
		
		
	}
	public int getCol(){
		return colNum;
	}
	public void setCol(int col){
		colNum = col;
	}
}