import java.awt.EventQueue;
import javax.swing.JFrame;

import ass3.BoardView;

public class Main {
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
