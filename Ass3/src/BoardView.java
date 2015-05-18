package ass3;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class BoardView extends JFrame{
	private Board GameState;
	public BoardView(){
		initBView();
	}
	public void initBView(){
		GameState = new Board(7,6);
		ArrayList<ColumnView> ColumnPanels = new ArrayList<ColumnView>();
		for(int i = 0; i < 7; i++){
			ColumnPanels.add(new ColumnView(i));
		}
		/*for(int i = 0; i < 7; i++){
			JButton colButton = new JButton();
			System.out.printf("%d\n", i);
		
		colButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				System.out.print("0\n");
				GameState.addPiece(0,1);
				if(GameState.hasWon(1)){
					System.out.print("Goodjob\n");
				}
				
	        }
			
		}
		);
			createLayout(colButton);
		}*/
		//BoardColoumn colButton0 = new BoardColoumn();
		/*colButton0.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				System.out.print("0\n");
				GameState.addPiece(0,1);
				if(GameState.hasWon(1)){
					System.out.print("Goodjob\n");
				}
				
	        }
		}
		);
		JButton colButton1 = new JButton();
		colButton1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				System.out.print("1\n");
				GameState.addPiece(1,1);
				if(GameState.hasWon(1)){
					System.out.print("Goodjob\n");
				}
				
	        }
		}
		);*/
		Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                //.addComponent(colButton0)
              //  .addComponent(colButton1)
                .addGap(20)
        );

        gl.setVerticalGroup(gl.createParallelGroup()
              //  .addComponent(colButton0)
               // .addComponent(colButton1)
                .addGap(20)
        );
		
		
		setTitle("Connect 4");
        setSize(1250, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	private void createLayout(JComponent... arg){
		Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );
	}
	public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
        
            @Override
            public void run() {
                BoardView ex = new BoardView();
               
                ex.setVisible(true);
            }
        });
    }
}
