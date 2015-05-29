package ass3;

import java.util.ArrayList;

public class Board {
    private int[][] boardState;
    private int colSize;
    private int rowSize;
    private int player;
    private int numPlayers = 2;
    private int winRequired = 4;
    private Player AI;
    private static int numPieces;
    private static int DEFAULT_ROW_SIZE = 6;
    private static int DEFAULT_COL_SIZE = 7;
    private boolean AIGame = true;
    private boolean gameRunning = false;
    private boolean pieceFalling = false;
    private boolean isAITurn = false;
    private ArrayList<Coordinates> winningPieces = new ArrayList<Coordinates>();

    /**
     * Sets up the back end for the board game, including
     * board grid, and the AI with default medium difficulty
     *
     * @param numPlayers
     */
    public Board(int numPlayers) {
        this.boardState = new int[DEFAULT_ROW_SIZE][DEFAULT_COL_SIZE];
        this.colSize = DEFAULT_COL_SIZE;
        this.rowSize = DEFAULT_ROW_SIZE;
        this.player = 0;
        numPieces = 0;
        this.AI = new Player(1);
        this.numPlayers = numPlayers;
        int i;
        int j;

        if (numPlayers == 3) {
            winRequired = 3;
        } else if (numPlayers == 2) {
            winRequired = 4;
        }
        for (i = 0; i < DEFAULT_ROW_SIZE; i++) {
            for (j = 0; j < DEFAULT_COL_SIZE; j++) {
                this.boardState[i][j] = 0;
            }
        }
    }

    /**
     * Attempts to add piece to requested column
     *
     * @param column
     * @return <code>true</code> if piece was added, or
     * <code>false</code> if addition failed
     */
    public boolean addPiece(int column) {
        int i;

        if (!isRunning()) {
            return false;
        }
        if (column < 0 || column >= colSize) { // shouldn't be necessary in final product but just in case
            return false;
        }
        for (i = 0; i < rowSize; i++) {
            if (boardState[i][column] == 0) {
                if (player == 1) {
                    player = 2;
                } else if (player == 2 && numPlayers == 3) {
                    player = 3;
                } else {
                    player = 1;
                }
                boardState[i][column] = player;
                this.print();
                numPieces++;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the current player has won.
     * Will fill winningPieces with the 4 in a row
     * if they won.
     *
     * @return <code>true</code> if player has won,
     * <code>false</code> if they haven't
     */
    public boolean hasWon() {
        winningPieces = new ArrayList<Coordinates>();
        int i;
        int j;
        int winCount;

        //Horizontal check
        for (i = 0; i < rowSize; i++) {
            winCount = 0;
            for (j = 0; j < colSize; j++) {
                if (boardState[i][j] == player) {
                    winCount++;
                    winningPieces.add(new Coordinates(i, j));
                } else {
                    winCount = 0;
                    winningPieces.clear();
                }
                if (winCount == winRequired) {
                    return true;
                }
            }
        }
        //Vertical check
        for (i = 0; i < colSize; i++) {
            winCount = 0;
            for (j = 0; j < rowSize; j++) {
                if (boardState[j][i] == player) {
                    winCount++;
                    winningPieces.add(new Coordinates(j, i));
                } else {
                    winCount = 0;
                    winningPieces.clear();
                }
                if (winCount == winRequired) {
                    return true;
                }
            }
        }
        int tempI = 0;
        int tempJ = 0;
        i = 0;
        j = 0;
        while (tempI < rowSize - (winRequired - 1)) {
            i = tempI;
            tempJ = 0;
            while (tempJ < colSize - (winRequired - 1)) {
                j = tempJ;
                winCount = 0;
                winningPieces.clear();
                while (winCount < winRequired && i < rowSize && j < colSize) {
                    if (boardState[i][j] == player) {
                        winCount++;
                        winningPieces.add(new Coordinates(i, j));
                        i++;
                        j++;
                    } else {
                        break;
                    }
                    if (winCount == winRequired) {
                        return true;
                    }
                }
                tempJ++;
                i = tempI;
            }
            tempI++;
        }
        tempI = 3;
        i = 3;
        tempJ = 0;
        j = 0;
        while (tempI < rowSize) {
            i = tempI;
            tempJ = 0;
            while (tempJ < colSize - (winRequired - 1)) {
                j = tempJ;
                winCount = 0;
                winningPieces.clear();
                while (winCount < winRequired && i >= 0 && j < 7) {
                    if (boardState[i][j] == player) {
                        winCount++;
                        winningPieces.add(new Coordinates(i, j));
                        i--;
                        j++;
                    } else {
                        break;
                    }
                    if (winCount == winRequired) {
                        return true;
                    }
                }
                tempJ++;
                i = tempI;
            }
            tempI++;
        }
        winningPieces.clear();
        return false;
    }

    /**
     * Will check if board is full and no moves left
     *
     * @return <code>true</code> if no moves left,
     * <code>false</code> if moves are available.
     */
    public boolean checkDraw() {
        if (numPieces == 42) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Prints out the board to terminal
     */
    public void print() {
        int i;
        int j;
        System.out.println("Current Board:");
        for (i = 0; i < rowSize; i++) {
            System.out.print("|");
            for (j = 0; j < colSize; j++) {
                System.out.print(boardState[i][j] + "|");
            }
            System.out.println("");
        }
    }

    /**
     * @return current player
     */
    public int getPlayer() {
        return player;
    }

    /**
     * @return <code>true</code> if single player game,
     * <code>false</code> if multiplayer game.
     */
    public boolean isAIGame() {
        return AIGame;
    }

    /**
     * Completes AI move and turns off isAITurn flag
     *
     * @return move made by the AI
     */
    public int getAITurn() {
        isAITurn = false;
        return AI.makeMove(boardState);
    }

    /**
     * Toggles between an AIGame and multiplayer game
     * Only works if game is not running (ie. in setup menu)
     *
     * @param AItoggle sets flag for AIGame
     */
    public void toggleAIGame(boolean AItoggle) {
        if (!gameRunning) {
            AIGame = AItoggle;
        }
    }

    /**
     * Starts and stops the game
     * Also resets the player turn counter
     *
     * @param running starts or stops the game
     */
    public void toggleGameState(boolean running) {

        player = 0;
        gameRunning = running;

    }

    /**
     * Resets the game with a cleared board
     */
    public void resetGame() {
        gameRunning = true;
        int i;

        for (i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                boardState[i][j] = 0;
            }
        }
        winningPieces.clear();
        numPieces = 0;
        player = 0;
    }

    /**
     * @return <code>true</code> if game is running,
     * <code>false</code> if its stopped
     */
    public boolean isRunning() {
        return gameRunning;
    }

    /**
     * @return number of players in the game
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * Sets the difficulty of the AI
     *
     * @param difficulty the setting for the difficulty
     */
    public void setAI(int difficulty) {
        AI.setDifficulty(difficulty);
    }

    /**
     * Checks a piece to see if its one of the winning pieces
     *
     * @param column the column number
     * @param row    the row number
     * @return <code>true</code> if a winning piece,
     * <code>false</code> if not or game hasn't finished
     */
    public boolean isWinPiece(int column, int row) {
        int i;

        if (winningPieces.size() == 0) {
            return false;
        }
        for (i = 0; i < winRequired; i++) {
            if (winningPieces.get(i).getCol() == column && winningPieces.get(i).getRow() == row) {
                return true;
            }
        }
        return false;
    }

    /**
     * Toggles whether a piece is falling in any column
     */
    public void setpieceFalling(boolean falling) {
        pieceFalling = falling;
    }

    /**
     * Sets gameState to the AI's turn
     */
    public void makeAITurn() {
        isAITurn = true;
    }

    /**
     * @return <code>true</code> if AI's turn,
     * <code>false</code> if it isn't
     */
    public boolean ifAITurn() {
        return isAITurn;
    }

    /**
     * @return <code>true</code> if piece falling,
     * <code>false</code> if no piece falling.
     */
    public boolean getPieceFalling() {
        return pieceFalling;
    }
}