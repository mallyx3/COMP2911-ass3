package ass3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")

public class ColumnView extends JPanel {
    private int colNum;
    private int[] row;
    private int fallingPoint = -1;
    private int yFall = 570;
    private Board gameState;
    private boolean mouseClicked = false;
    private boolean mouseEntered = false;
    private boolean colourBlindMode;
    private boolean pieceFalling = false;
    private Timer timeThing;

    /**
     * Paints and updates a column in the gamescreen
     *
     * @param colNum      designates which column
     * @param State       holds the Board gameState
     * @param colourBlind sets the flag to paint in ColourBlind mode
     */
    public ColumnView(int colNum, Board State, boolean colourBlind) {
        super();
        int k;

        timeThing = new Timer(5, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                yFall = yFall + 6;
                gameState.setpieceFalling(true);
                if (yFall > 670 - 110 * fallingPoint) {

                    pieceFalling = false;
                    gameState.setpieceFalling(false);
                }

                timeThing.stop();
                repaint();
            }
        });
        this.colNum = colNum;
        this.gameState = State;
        colourBlindMode = colourBlind;
        this.row = new int[6];
        for (k = 0; k < 6; k++) {
            row[k] = 0;
        }
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if (!gameState.ifAITurn() && gameState.isRunning()) {
                    mouseClicked = true;
                    paintPiece();
                }
            }

            public void mouseReleased(MouseEvent e) {
                mouseClicked = false;
                if (!pieceFalling && gameState.isRunning()) {
                    repaint();
                }
            }

            public void mouseEntered(MouseEvent e) {
                mouseEntered = true;
                if (!pieceFalling && gameState.isRunning()) {
                    repaint();
                }
            }

            public void mouseExited(MouseEvent e) {
                mouseEntered = false;
                if (!pieceFalling && gameState.isRunning()) {
                    repaint();
                }
            }
        });
    }

    /**
     * Updates the column with current pieces
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        boolean isWinPiece;
        boolean gameFinished = false;
        int i;

        if (!gameState.isRunning()) {
            gameFinished = true;
        }
        if (gameState.checkDraw()) {
        }
        for (i = 0; i < 6; i++) {
            isWinPiece = false;
            if (gameState.isWinPiece(colNum, i)) {
                isWinPiece = true;
            }
            ImageIcon newPiece = null;
            if (row[i] == 1) {
                if (!colourBlindMode) {
                    if (!isWinPiece && gameFinished) {
                        newPiece = new ImageIcon(getClass().getResource("/Art/CoinYDark.png"));
                    } else {
                        newPiece = new ImageIcon(getClass().getResource("/Art/CoinY.png"));
                    }
                } else {
                    if (!isWinPiece && gameFinished) {
                        newPiece = new ImageIcon(getClass().getResource("/Art/CoinYDarkColourBlind.png"));
                    } else {
                        newPiece = new ImageIcon(getClass().getResource("/Art/CoinYColourBLind.png"));
                    }
                }
            } else if (row[i] == 2) {
                if (!colourBlindMode) {
                    if (!isWinPiece && gameFinished) {
                        newPiece = new ImageIcon(getClass().getResource("/Art/CoinRDark.png"));
                    } else {
                        newPiece = new ImageIcon(getClass().getResource("/Art/CoinR.png"));
                    }
                } else {
                    if (!isWinPiece && gameFinished) {
                        newPiece = new ImageIcon(getClass().getResource("/Art/CoinRDarkColourBlind.png"));
                    } else {
                        newPiece = new ImageIcon(getClass().getResource("/Art/CoinRColourBlind.png"));
                    }
                }
            } else if (row[i] == 3) {
                if (!colourBlindMode) {
                    if (!isWinPiece && gameFinished) {
                        newPiece = new ImageIcon(getClass().getResource("/Art/CoinGDark.png"));
                    } else {
                        newPiece = new ImageIcon(getClass().getResource("/Art/CoinG.png"));
                    }
                } else {
                    if (!isWinPiece && gameFinished) {
                        newPiece = new ImageIcon(getClass().getResource("/Art/CoinGColourBlind.png"));
                    } else {
                        newPiece = new ImageIcon(getClass().getResource("/Art/CoinGColourBlind.png"));
                    }
                }
            } else if ((i == 0 || row[i - 1] != 0) && !gameFinished && mouseEntered) {
                if ((gameState.isAIGame() || (gameState.getPlayer() == 2 && gameState.getNumPlayers() == 2)) || gameState.getPlayer() == 3) {
                    g.setColor(new Color(255, 255, 200));
                } else if (gameState.getPlayer() == 2 && gameState.getNumPlayers() == 3) {
                    g.setColor(new Color(200, 255, 200));
                } else {
                    g.setColor(new Color(255, 200, 200));
                }
                g.fillOval(30, 670 - 110 * i, 90, 90);
            } else {
            }
            if (i == fallingPoint && pieceFalling) {
                newPiece.paintIcon(this, g, 30, yFall);
            } else {
                if (row[i] != 0) {
                    newPiece.paintIcon(this, g, 30, 670 - 110 * i);
                }
            }
        }
        ImageIcon column = null;
        if (mouseClicked && gameState.isRunning()) {
            column = new ImageIcon(getClass().getResource("/Art/boardpress.png"));
        } else if (mouseEntered && gameState.isRunning()) {
            column = new ImageIcon(getClass().getResource("/Art/boardhover.png"));
        } else {
            column = new ImageIcon(getClass().getResource("/Art/board.png"));
        }
        column.paintIcon(this, g, 0, 100);
        if (pieceFalling) {
            timeThing.start();
        }
    }

    /**
     * When called, will request the backend Board to
     * add a piece to this column, will then repaint column
     * if successful
     */
    public void paintPiece() {
        int i;

        if (gameState.addPiece(colNum)) {
            boolean painted = false;
            yFall = 20;
            pieceFalling = true;
            gameState.setpieceFalling(true);
            for (i = 0; i < 6 && !painted; i++) {
                if (row[i] == 0) {
                    fallingPoint = i;
                    row[i] = gameState.getPlayer();
                    painted = true;
                    repaint();
                }
            }
        }
    }

    /**
     * Resets column to an empty column
     */
    public void resetBoard() {
        int i;
        for (i = 0; i < 6; i++) {
            row[i] = 0;
        }
        repaint();
    }
}