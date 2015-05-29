import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SideView extends JPanel {
    private Board gameState;
    private boolean colourBlind;

    /**
     * Constructs SideView with given board state and colour blind flag
     *
     * @param gameState
     * @param colourBlind
     */
    public SideView(Board gameState, boolean colourBlind) {
        this.gameState = gameState;
        this.colourBlind = colourBlind;
    }

    //todo - write javadoc. Not sure what this method does
    /**
     * Paints whose turn it is in the side bar
     * 
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon bg = new ImageIcon(getClass().getResource("/Art/bg.png"));
        bg.paintIcon(this, g, 0, 0);
        ImageIcon playerLabel = null;
        ImageIcon playerPiece = null;
        if (gameState.getPlayer() == 1 && gameState.isAI()) {
            playerLabel = new ImageIcon(getClass().getResource("/Art/Computer.png"));
            if (colourBlind) {
                playerPiece = new ImageIcon(getClass().getResource("/Art/CoinRColourBlind.png"));
            } else {
                playerPiece = new ImageIcon(getClass().getResource("/Art/CoinR.png"));
            }
        } else if (gameState.getPlayer() == 1) {
            playerLabel = new ImageIcon(getClass().getResource("/Art/PlayerTWO.png"));
            if (colourBlind) {
                playerPiece = new ImageIcon(getClass().getResource("/Art/CoinRColourBlind.png"));
            } else {
                playerPiece = new ImageIcon(getClass().getResource("/Art/CoinR.png"));
            }
        } else if (gameState.getNumPlayers() == 3 && gameState.getPlayer() == 2) {
            playerLabel = new ImageIcon(getClass().getResource("/Art/PlayerThree.png"));
            if (colourBlind) {
                playerPiece = new ImageIcon(getClass().getResource("/Art/CoinGColourBlind.png"));
            } else {
                playerPiece = new ImageIcon(getClass().getResource("/Art/CoinG.png"));
            }
        } else {
            playerLabel = new ImageIcon(getClass().getResource("/Art/PlayerOne.png"));
            if (colourBlind) {
                playerPiece = new ImageIcon(getClass().getResource("/Art/CoinYColourBlind.png"));
            } else {
                playerPiece = new ImageIcon(getClass().getResource("/Art/CoinY.png"));
            }
        }
        playerLabel.paintIcon(this, g, 0, 300);
        playerPiece.paintIcon(this, g, 55, 375);
    }
}