package ass3;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SideView extends JPanel{
	private Board gameState;
	private boolean colourBlind;
	/**
	 * Creates and updates the side menu in the game screen
	 * @param newState Holds the game state for the game
	 * @param colourBlind Flag for painting in ColourBlind mode
	 */
	public SideView(Board newState, boolean colourBlind){
		gameState = newState;
		this.colourBlind = colourBlind;
	}
	/**
	 * Paints background of the side menu, and will update 
	 * a visual guide for whose turn it is in the side menu
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		ImageIcon bg = new ImageIcon(getClass().getResource("/Art/bglighter.png"));
		bg.paintIcon(this, g, 0, 0);
		ImageIcon connectTitle = null;
		ImageIcon playerLabel = null;
		ImageIcon playerPiece = null;
		if(gameState.getNumPlayers() == 2){
			connectTitle = new ImageIcon(getClass().getResource("/Art/Connect4.png"));
		} else {
			connectTitle = new ImageIcon(getClass().getResource("/Art/Connect3.png"));
		}
		//System.out.printf("%d\n", gameState.getPlayer());
		if(gameState.getPlayer() == 1 && gameState.isAIGame()){
			playerLabel = new ImageIcon(getClass().getResource("/Art/Computer.png"));
			if(colourBlind){
				playerPiece = new ImageIcon(getClass().getResource("/Art/CoinRColourBlind.png"));
			} else {
				playerPiece = new ImageIcon(getClass().getResource("/Art/CoinR.png"));
			}
		} else if (gameState.getPlayer() == 1) {
			playerLabel = new ImageIcon(getClass().getResource("/Art/PlayerTWO.png"));
			if(colourBlind){
				playerPiece = new ImageIcon(getClass().getResource("/Art/CoinRColourBlind.png"));
			} else {
				playerPiece = new ImageIcon(getClass().getResource("/Art/CoinR.png"));
			}
		} else if (gameState.getNumPlayers() == 3 && gameState.getPlayer() == 2){
			playerLabel = new ImageIcon(getClass().getResource("/Art/PlayerThree.png"));
			if(colourBlind){
				playerPiece = new ImageIcon(getClass().getResource("/Art/CoinGColourBlind.png"));
			} else {
				playerPiece = new ImageIcon(getClass().getResource("/Art/CoinG.png"));
			}
		} else {
			playerLabel = new ImageIcon(getClass().getResource("/Art/PlayerOne.png"));
			if(colourBlind){
				playerPiece = new ImageIcon(getClass().getResource("/Art/CoinYColourBlind.png"));
			} else {
				playerPiece = new ImageIcon(getClass().getResource("/Art/CoinY.png"));
			}
		}
		connectTitle.paintIcon(this, g, 10, 0);
		playerLabel.paintIcon(this, g, 0, 300);
		playerPiece.paintIcon(this, g, 55, 375);
	}
}
