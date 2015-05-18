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
<<<<<<< HEAD
		g.fillOval(0, 0, 30, 30);
		
		
	}
}
=======
		g.fillOval(30, 15, 90, 90);
		g.fillOval(30, 165, 90, 90);
		g.fillOval(30, 315, 90, 90);
		g.fillOval(30, 465, 90, 90);
		g.fillOval(30, 615, 90, 90);
		g.fillOval(30, 765, 90, 90);
		
		
	}
	
	
}
>>>>>>> b2229438e2eb01033792f0197e00479e6f74d083
