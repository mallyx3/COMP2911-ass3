import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ColumnView extends JPanel {
    private static final int ROW_SIZE = 6;
    private int colNum;
    private int[] row;
    //todo what does l represent? can we rename it to something more meaningful?
    private int l = -1;
    private int yFall = 570;
    private Board gameState;
    private boolean mouseClicked = false;
    private boolean mouseEntered = false;
    private boolean colourBlindMode;
    private boolean pieceFalling = false;
    private Timer timeThing;

    /**
     * Constructs ColumnView with the given column number, board state and
     * colour blind flag
     *
     * @param colNum      the number of the column
     * @param State       the state of the board
     * @param colourBlind the flag for if colour blind or not
     */
    public ColumnView(int colNum, Board State, boolean colourBlind) {
        super();
        int k;

        timeThing = new Timer(5, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                yFall = yFall + 6;
                if (yFall > 670 - 110 * l) {

                    pieceFalling = false;
                    gameState.pieceFalling();
                }

                timeThing.stop();
                repaint();
            }
        });
        this.colNum = colNum;
        this.gameState = State;
        colourBlindMode = colourBlind;
        this.row = new int[ROW_SIZE];
        for (k = 0; k < ROW_SIZE; k++) {
            row[k] = 0;
        }
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if (!gameState.isAITurn() && gameState.isRunning()) {
                    mouseClicked = true;
                    paintPiece();
                }
            }

            public void mouseReleased(MouseEvent e) {
                mouseClicked = false;
                if (!pieceFalling) {
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
     * @return the column number
     */
    public int getCol() {
        return colNum;
    }

    /**
     * @return the row array
     */
    public int[] getRow() {
        return row;
    }
    //todo - write javadoc. Not sure what this field does

    /**
     * @return
     */
    public int getYFall() {
        return yFall;
    }

    /**
     * @return the state of the board
     */
    public Board getGameState() {
        return gameState;
    }

    /**
     * @return <code>true</code> if mouse is being clicked, or otherwise
     * <code>false</code>
     */
    public boolean isMouseClicked() {
        return mouseClicked;
    }
    //todo - write javadoc. Not sure what this field does

    /**
     * @return
     */
    public boolean isMouseEntered() {
        return mouseEntered;
    }

    /**
     * @return <code>true</code> if game is in colour blind mode, or otherwise
     * <code>false</code>
     */
    public boolean isColourBlindMode() {
        return colourBlindMode;
    }

    /**
     * @return <code>true</code> if game piece is falling, or otherwise
     * <code>false</code>
     */
    public boolean isPieceFalling() {
        return pieceFalling;
    }
    //todo - write javadoc. Not sure what this field does

    /**
     * @return
     */
    public Timer getTimeThing() {
        return timeThing;
    }
    //todo - write javadoc. Not sure what this field does

    /**
     * @return
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        boolean isWinPiece;
        boolean gameFinished = false;

        if (!gameState.isRunning()) {
            gameFinished = true;
        }
        for (int i = 0; i < ROW_SIZE; i++) {
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
                        newPiece = new ImageIcon(getClass().getResource("/Art/CoinR.png"));
                    } else {
                        newPiece = new ImageIcon(getClass().getResource("/Art/CoinR.png"));
                    }
                } else {
                    if (!isWinPiece && gameFinished) {
                        newPiece = new ImageIcon(getClass().getResource("/Art/CoinRColourBlind.png"));
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
                if ((gameState.isAI() || (gameState.getPlayer() == 2 && gameState.getNumPlayers() == 2)) || gameState.getPlayer() == 3) {
                    g.setColor(new Color(255, 255, 100));
                } else if (gameState.getPlayer() == 2 && gameState.getNumPlayers() == 3) {
                    g.setColor(new Color(100, 255, 100));
                } else {
                    g.setColor(new Color(255, 100, 100));
                }
                g.fillOval(30, 670 - 110 * i, 90, 90);
            }
            if (i == l && pieceFalling) {
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
     * Calls the drop game piece animation on behalf of the AI
     */
    public void paintAITurn() {
        paintPiece();
    }

    /**
     * Starts the drop game piece animation
     */
    public void paintPiece() {
        if (gameState.addPiece(getCol())) {
            int i;
            boolean painted = false;
            yFall = 20;
            pieceFalling = true;
            gameState.pieceFalling();

            for (i = 0; i < ROW_SIZE && !painted; i++) {
                if (row[i] == 0) {
                    l = i;
                    row[i] = gameState.getPlayer();
                    painted = true;
                    repaint();
                }
            }
        }
    }

    /**
     * Changes the colours of all the game pieces to indicate game over.
     */
    public void paintWinPieces() {
        repaint();
    }

    /**
     * Resets the board
     */
    public void resetBoard() {
        int i;

        for (i = 0; i < ROW_SIZE; i++) {
            row[i] = 0;
        }
        repaint();
    }
}