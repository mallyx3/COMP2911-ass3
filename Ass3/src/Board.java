import java.util.ArrayList;

public class Board {
    private static final int DEFAULT_ROW_SIZE = 6;
    private static final int DEFAULT_COL_SIZE = 7;
    private static int NUM_OF_CONNECTED_PIECES = 4;
    private int[][] boardState;
    private int colSize;
    private int rowSize;
    private int player;
    private int numPlayers = 2;
    private Player AI;
    private static int numPieces;
    private boolean AIGame = true;
    private boolean gameRunning = false;
    private boolean pieceFalling = false;
    private boolean isAITurn = false;
    private ArrayList<Coordinates> winningPieces = new ArrayList<Coordinates>();

    /**
     * Constructs the board class with the given number of players
     */
    public Board(int numPlayers) {
        this.boardState = new int[DEFAULT_ROW_SIZE][DEFAULT_COL_SIZE];
        this.colSize = DEFAULT_COL_SIZE;
        this.rowSize = DEFAULT_ROW_SIZE;
        this.player = 0;
        numPieces = 0;
        this.AI = new Player(0);
        this.numPlayers = numPlayers;
        int i;
        int j;

        if (numPlayers == 3) {
            NUM_OF_CONNECTED_PIECES = 3;
        } else if (numPlayers == 2) {
            NUM_OF_CONNECTED_PIECES = 4;
        }
        for (i = 0; i < rowSize; i++) {
            for (j = 0; j < colSize; j++) {
                this.boardState[i][j] = 0;
            }
        }
    }

    /**
     * Constructs the board class, optionally with the numbers of rows and columns
     */
    public Board(int rowSize, int colSize) {
        this.boardState = new int[rowSize][colSize];
        this.colSize = colSize;
        this.rowSize = rowSize;
        numPieces = 0;
        int i;
        int j;

        for (i = 0; i < rowSize; i++) {
            for (j = 0; j < colSize; j++) {
                this.boardState[i][j] = 0;
            }
        }
    }

    /**
     * @return the current state of the board
     */
    public int[][] getBoardState() {
        return boardState;
    }

    /**
     * @return the number of columns
     */
    public int getColSize() {
        return colSize;
    }

    /**
     * @return the number of rows
     */
    public int getRowSize() {
        return rowSize;
    }

    /**
     * @return the number of pieces on the board
     */
    public int getNumPieces() {
        return numPieces;
    }

    /**
     * @return the player number
     */
    public int getPlayer() {
        return player;
    }

    /**
     * @return <code>true</code> if the player is an AI, or <code>false</code> if the player is not an AI
     */
    public boolean isAI() {
        return AIGame;
    }

    /**
     * @return whether the game is running or not
     */
    public boolean isRunning() {
        return gameRunning;
    }

    /**
     * @return the winning pieces array list coordinates
     */
    public ArrayList<Coordinates> getWinningPieces() {
        return winningPieces;
    }

    /**
     * @return <code>true</code> if it's the AI's turn, or otherwise <code>false</code>
     */
    public boolean isAITurn() {
        return isAITurn;
    }

    /**
     * @return the number of players in the game
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * @return the AI player
     */
    public Player getAI() {
        return AI;
    }

    /**
     * @return <code>true</code> if a game piece is falling, or otherwise <code>false</code>
     */
    public boolean isPieceFalling() {
        return pieceFalling;
    }

    /**
     * @return the whether AI's turn was made
     */
    public int getAITurn() {
        isAITurn = false;
        return AI.makeMove(boardState);
    }

    /**
     * Sets the board state
     *
     * @param boardState the replacement board state
     */
    public void setBoardState(int[][] boardState) {
        this.boardState = boardState;
    }

    /**
     * Sets the number of columns to be used in the game
     *
     * @param colSize the number of columns
     */
    public void setColSize(int colSize) {
        this.colSize = colSize;
    }

    /**
     * Sets the number of rows to be used in the game
     *
     * @param rowSize the number of rows
     */
    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    /**
     * Sets the number of pieces currently on the board
     *
     * @param newNumPieces the current number of pieces in game
     */
    public void setNumPieces(int newNumPieces) {
        numPieces = newNumPieces;
    }

    /**
     * Sets the player number
     *
     * @param playerNum the number of players
     */
    public void setPlayerNum(int playerNum) {
        numPlayers = playerNum;
    }

    /**
     * Set the difficulty of the game
     *
     * @param difficulty the difficulty of the game
     */
    public void setAI(int difficulty) {
        AI.setDifficulty(difficulty);
    }

    /**
     * Sets the player ID number
     *
     * @param player the ID number of the player
     */
    public void setPlayer(int player) {
        this.player = player;
    }

    /**
     * Sets the player AI
     *
     * @param AI the player AI
     */
    public void setAI(Player AI) {
        this.AI = AI;
    }

    /**
     * Sets if the game is with an AI or not
     *
     * @param AIGame whether the player is an AI or not
     */
    public void setAIGame(boolean AIGame) {
        this.AIGame = AIGame;
    }

    /**
     * Sets the game to be running
     *
     * @param gameRunning whether the game is running or not
     */
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    /**
     * Sets whether the piece is falling or not
     *
     * @param pieceFalling whether the piece is falling
     */
    public void setPieceFalling(boolean pieceFalling) {
        this.pieceFalling = pieceFalling;
    }

    /**
     * Sets whether it is the AI's turn or not
     *
     * @param isAITurn whether it's the AI's turn
     */
    public void setAITurn(boolean isAITurn) {
        this.isAITurn = isAITurn;
    }

    /**
     * Sets the winningPieces array list
     *
     * @param winningPieces the winning pieces array list
     */
    public void setWinningPieces(ArrayList<Coordinates> winningPieces) {
        this.winningPieces = winningPieces;
    }

    /**
     * Sets the isAiTurn field to true
     */
    public void makeAITurn() {
        isAITurn = true;

    }

    /**
     * Adds a game piece to a column. Will return false if addition is unsuccessful,
     * otherwise true
     *
     * @param column the column number to place game piece
     * @return <code>true</code> if the insertion is successful, or <code>false</code>
     * if the column is already full.
     */
    public boolean addPiece(int column) {
        if (!isRunning()) {
            return false;
        } else if (column < 0 || column >= colSize) {
            return false;
        }
        for (int i = 0; i < rowSize; i++) {
            if (boardState[i][column] == 0) {
                if (player == 1) {
                    player = 2;
                } else if (player == 2 && numPlayers == 3) {
                    player = 3;
                } else {
                    player = 1;
                }
                System.out.printf("%d\n", player);
                boardState[i][column] = player;
                this.print();
                numPieces++;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a player has won the game.
     *
     * @return <code>true</code> if a player has won, or <code>false</code> if no one has won
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
                if (winCount == NUM_OF_CONNECTED_PIECES) {
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
                if (winCount == NUM_OF_CONNECTED_PIECES) {
                    return true;
                }
            }
        }
        int tempI = 0;
        int tempJ = 0;
        i = 0;
        j = 0;
        //Diagonal check
        while (tempI < rowSize - (NUM_OF_CONNECTED_PIECES - 1)) {
            i = tempI;
            tempJ = 0;
            while (tempJ < colSize - (NUM_OF_CONNECTED_PIECES - 1)) {
                j = tempJ;
                winCount = 0;
                winningPieces.clear();
                while (winCount < NUM_OF_CONNECTED_PIECES && i < rowSize && j < colSize) {
                    if (boardState[i][j] == player) {
                        winCount++;
                        winningPieces.add(new Coordinates(i, j));
                        i++;
                        j++;
                    } else {
                        break;
                    }
                    if (winCount == NUM_OF_CONNECTED_PIECES) {
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
            while (tempJ < colSize - (NUM_OF_CONNECTED_PIECES - 1)) {
                j = tempJ;
                winCount = 0;
                winningPieces.clear();
                while (winCount < NUM_OF_CONNECTED_PIECES && i >= 0 && j < colSize) {
                    if (boardState[i][j] == player) {
                        winCount++;
                        winningPieces.add(new Coordinates(i, j));
                        i--;
                        j++;
                    } else {
                        break;
                    }
                    if (winCount == NUM_OF_CONNECTED_PIECES) {
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
     * Checks if all the game pieces have been used
     *
     * @return <code>true</code> if all the pieces have been used, or otherwise
     * <code>false</code>.
     */
    //todo might have to change number for 3 players
    public boolean checkDraw() {
        if (numPieces == 42) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Prints the current board state to terminal
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
     * Toggles whether the player is vs an AI or not.
     *
     * @param AItoggle whether the player vs AI
     */
    public void toggleAI(boolean AItoggle) {
        if (!gameRunning) {
            AIGame = AItoggle;
        }
    }

    /**
     * Toggles whether the game is running or not.
     *
     * @param running whether the game is running or not
     */
    public void toggleGameState(boolean running) {
        player = numPlayers;
        gameRunning = running;
    }

    /**
     * Reset the current game by wiping the board and clearing the pieces.
     */
    public void resetGame() {
        gameRunning = true;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                boardState[i][j] = 0;
            }
        }
        winningPieces.clear();
        numPieces = 0;
        player = 0;
    }

    /**
     * Prints the difficulty level to terminal.
     */
    public void printDiff() {
        System.out.printf("%d\n", AI.getDifficulty());
    }

    /**
     * Checks the array list of winning pieces for a win.
     *
     * @param column the column number to check
     * @param row    the row number to check
     * @return <code>true</code> if this position contains the winning piece, or otherwise
     * <code>false</code>.
     */
    public boolean isWinPiece(int column, int row) {
        if (winningPieces.size() == 0) {
            return false;
        }
        for (int i = 0; i < NUM_OF_CONNECTED_PIECES; i++) {
            if (winningPieces.get(i).getCol() == column && winningPieces.get(i).getRow() == row) {
                return true;
            }
        }
        return false;
    }

    /**
     * Toggles the piece falling field
     */
    public void pieceFalling() {
        pieceFalling = !pieceFalling;
    }
}

