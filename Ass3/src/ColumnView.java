import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
@SuppressWarnings("serial")
public class ColumnView extends JPanel{
	
	//private ArrayList<Dimensions> CircleSpots = new ArrayList<Dimensions>();
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillOval(30, 15, 90, 90);
		g.fillOval(30, 165, 90, 90);
		g.fillOval(30, 315, 90, 90);
		g.fillOval(30, 465, 90, 90);
		g.fillOval(30, 615, 90, 90);
		g.fillOval(30, 765, 90, 90);
		
		
	}
	
	
}
