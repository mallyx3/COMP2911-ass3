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
		// offset offset x size y size
		g.fillOval(30, 10, 90, 90);
		g.fillOval(30, 110, 90, 90);
		g.fillOval(30, 220, 90, 90);
		g.fillOval(30, 330, 90, 90);
		g.fillOval(30, 440, 90, 90);
		g.fillOval(30, 550, 90, 90);
		
		
	}
	
	
}
