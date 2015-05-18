package ass3;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SpringLayout;

public class BoardView extends JFrame{
	private Board GameState;
	public BoardView(){
		initBView();
	}
	public void initBView(){
		ArrayList<ColumnView> ColumnList = new ArrayList<ColumnView>();
		GameState = new Board();
		
		for(int i = 0; i < 7;i++){
			ColumnList.add(new ColumnView());
			ColumnList.get(i).setBackground(Color.BLUE);
			ColumnList.get(i).setPreferredSize(new Dimension(150,900));
			ColumnList.get(i).setMaximumSize(new Dimension(150,900));
			//ColumnList.get(i).addMouseListener();
		}
		Container Columns = getContentPane();
		Columns.setLayout(new BoxLayout(Columns, BoxLayout.X_AXIS));
		for(int i = 0;i < 7; i++){
			Columns.add(ColumnList.get(i));
		}

		
		
		setTitle("Connect 4");
        setSize(1350, 900);
        setLocationRelativeTo(null);
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
}
