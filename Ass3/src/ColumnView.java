import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ColumnView extends JPanel{
	//private ArrayList<Dimensions> CircleSpots = new ArrayList<Dimensions>();
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillOval(0, 0, 30, 30);
		
		
	}
}