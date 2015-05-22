

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
@SuppressWarnings("serial")
public class ColumnView extends JPanel{
	private int colNum;
	private Integer[] row;
	private Board gameState;
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
				//int i = gameState.addPiece(getCol());
				//if(i >= 0){
				//	repaint(30,560-(110*i),90,90);
				//}
				
				
			}
			public void mousePressed(MouseEvent e){
				if(gameState.addPiece(getCol())){
					boolean painted = false;
					for(int i = 0; i < 6 && !painted;i++){
						if(row[i] == 0){
							row[i] = gameState.getPlayer();
							painted = true;
							repaint();
						}
					}
				}
			}
			public void mouseEntered(MouseEvent e){
				setBackground(Color.CYAN);
			}
			public void mouseExited(MouseEvent e){
				setBackground(Color.BLUE);
			}
		});
		
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i = 0; i < 6; i++){
			if(row[i] == 1){
				g.setColor(Color.YELLOW);
			} else if (row[i] == 2){
				g.setColor(Color.RED);
			}else {
				g.setColor(Color.WHITE);
			}
			g.fillOval(30,560-(110*i),90,90);
			
		}	
	}
	public int getCol(){
		return colNum;
	}
}