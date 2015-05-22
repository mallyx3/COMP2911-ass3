

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
@SuppressWarnings("serial")

public class BoardView extends JFrame{

	private Board gameState;
	public BoardView(){
		initBView();
	}
	public void initBView(){
		gameState = new Board();
		ArrayList<ColumnView> ColumnList = new ArrayList<ColumnView>();
		
		for(int i = 0; i < 7;i++){
			ColumnList.add(new ColumnView(i, gameState));
			ColumnList.get(i).setBackground(Color.BLUE);
			ColumnList.get(i).setPreferredSize(new Dimension(150,700));
			ColumnList.get(i).setMaximumSize(new Dimension(150,700));
			ColumnList.get(i).addMouseListener(new MouseAdapter(){
				public void mouseReleased(MouseEvent e){
					if(gameState.isAI()){
						ColumnList.get(gameState.getAITurn()).paintAITurn();
					}
				}
			});
		}
		Container Columns = getContentPane();
		Columns.setLayout(new BoxLayout(Columns, BoxLayout.X_AXIS));
		for(int i = 0;i < 7; i++){
			Columns.add(ColumnList.get(i));
		}
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 7; i++){
					ColumnList.get(i).resetBoard();
					gameState.resetGame();
				}
			}
			
		});
		
		MenuView menu = new MenuView(gameState);
		menu.setPreferredSize(new Dimension(150,700));
		menu.setMaximumSize(new Dimension(150,700));
		menu.setBackground(Color.GREEN);
		menu.newContainer();
		menu.addReset(resetButton);
		Columns.add(menu);
		
		
		
        

		
		
		setTitle("Connect 4");
        setSize(1200, 700);
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

}
