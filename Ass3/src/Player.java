import java.util.Random;

public class Player {
    private static final int DIFFICULTY_EASY = 0;
    private static final int DIFFICULTY_MEDIUM = 1;
    private final int ROW_SIZE = 6;
    private final int COL_SIZE = 7;
    private final int EMPTY_SPACE = 0;
    private int difficulty;
    private int player = 2;

    /**
     * Construct an AI player with the given difficulty level.
     */
    public Player(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @return the ID number of the player
     */
    public int getPlayer() {
        return player;
    }

    /**
     * @return the difficulty of the game
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the ID number of the player
     *
     * @param player the player's ID number
     */
    public void setPlayer(int player) {
        this.player = player;
    }

    /**
     * Sets the difficulty level of the AI
     *
     * @param difficulty the difficulty level of the AI
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Gets the AI's move
     *
     * @param board the state of the board
     * @return the AI's column number choice.
     */
    public int makeMove(int board[][]) {
        int i;
        int j;

        //Make easy difficulty AI move
        if (difficulty == DIFFICULTY_EASY) {
            if (board[0][3] == EMPTY_SPACE) {
                return 3;
            }
            for (j = 0; j < COL_SIZE; j++) {
                if (findPiece(board, j, 2, 0)) {
                    return j;
                }
            }
            for (j = 0; j < COL_SIZE; j++) {
                if (findPiece(board, j, 1, 0)) {
                    return j;
                }
            }
            Random rand = new Random();
            i = rand.nextInt(COL_SIZE);
            while (board[5][i] != EMPTY_SPACE) {
                i = rand.nextInt(COL_SIZE);
            }
            return i;
            //Make medium difficulty AI move
        } else if (difficulty == DIFFICULTY_MEDIUM) {
            if (board[0][3] == EMPTY_SPACE) {
                return 3;
            }
            for (j = 0; j < COL_SIZE; j++) {
                if (findPiece(board, j, 2, 0)) {
                    return j;
                }
            }
            for (j = 0; j < COL_SIZE; j++) {
                if (findPiece(board, j, 1, 0)) {
                    return j;
                }
            }
            int[] checkPieces = new int[COL_SIZE];
            for (j = 0; j < COL_SIZE; j++) {
                checkPieces[j] = 0;
                if (testPiece(board, j, 1, 0)) {
                    checkPieces[j] = 2;
                }
            }
            for (j = 0; j < COL_SIZE; j++) {
                if (testPiece(board, j, 2, 0) && checkPieces[j] != 2) {
                    checkPieces[j] = 1;
                }
            }
            boolean checkForLegal = false;
            int k = 0;
            for (j = 0; j < COL_SIZE; j++) {
                if (checkPieces[j] == 0 && board[5][j] == EMPTY_SPACE) {
                    checkForLegal = true;
                }
            }
            if (!checkForLegal) {
                k++;
                for (j = 0; j < COL_SIZE; j++) {
                    if (checkPieces[j] <= 1 && board[5][j] == EMPTY_SPACE) {
                        checkForLegal = true;
                    }
                }
            }
            if (!checkForLegal) {
                k++;
            }
            System.out.printf("%d\n", k);
            Random rand = new Random();
            i = rand.nextInt(COL_SIZE);
            while (checkPieces[i] > k || (checkPieces[i] <= k && board[5][i] != EMPTY_SPACE)) {
                i = rand.nextInt(COL_SIZE);
            }
            return i;

            //Make hard difficulty AI move
        } else {
            if (board[0][3] == EMPTY_SPACE) {
                return 3;
            }
            for (j = 0; j < COL_SIZE; j++) {
                if (findPiece(board, j, 2, 0)) {
                    return j;
                }
            }
            for (j = 0; j < COL_SIZE; j++) {
                if (findPiece(board, j, 1, 0)) {
                    return j;
                }
            }
            int[] checkPieces = new int[COL_SIZE];
            for (j = 0; j < COL_SIZE; j++) {
                checkPieces[j] = EMPTY_SPACE;
                if (testPiece(board, j, 1, 0)) {
                    checkPieces[j] = 2;
                }
            }
            for (j = 0; j < COL_SIZE; j++) {
                if (testPiece(board, j, 2, 0) && checkPieces[j] != 2) {
                    checkPieces[j] = 1;
                }
            }
            for (j = 0; j < COL_SIZE; j++) {
                if (findPiece(board, j, 1, 1) && checkPieces[j] == EMPTY_SPACE) {
                    return j;
                }
            }

            for (j = 0; j < COL_SIZE; j++) {
                if (testPiece(board, j, 2, 1) && checkPieces[j] == EMPTY_SPACE) {
                    return j;
                }
            }
            for (j = 0; j < COL_SIZE; j++) {
                if (findPiece(board, j, 2, 1) && checkPieces[j] < 2) {
                    return j;
                }
            }
            for (j = 0; j < COL_SIZE; j++) {
                if (testPiece(board, j, 2, 1) && checkPieces[j] < 2) {
                    return j;
                }
            }
            boolean checkForLegal = false;
            int k = 0;
            for (j = 0; j < COL_SIZE; j++) {
                if (checkPieces[j] == EMPTY_SPACE && board[5][j] == EMPTY_SPACE) {
                    checkForLegal = true;
                }
            }
            if (!checkForLegal) {
                k++;
                for (j = EMPTY_SPACE; j < COL_SIZE; j++) {
                    if (checkPieces[j] <= 1 && board[5][j] == EMPTY_SPACE) {
                        checkForLegal = true;
                    }
                }
            }
            if (!checkForLegal) {
                k++;
            }
            System.out.printf("%d\n", k);
            Random rand = new Random();
            i = rand.nextInt(COL_SIZE);
            while (checkPieces[i] > k || (checkPieces[i] <= k && board[5][i] != EMPTY_SPACE)) {
                i = rand.nextInt(COL_SIZE);
            }
            return i;
        }
    }

    //todo - write javadoc. Not sure what this method does

    /**
     * @param testBoard
     * @param column
     * @param player
     * @param blankPieces
     * @return
     */
    public boolean findPiece(int testBoard[][], int column, int player, int blankPieces) {
        int i;
        boolean foundCheck = false;

        if (testBoard[5][column] == EMPTY_SPACE) {
            for (i = 0; i < ROW_SIZE && !foundCheck; i++) {
                if (testBoard[i][column] == EMPTY_SPACE) {
                    foundCheck = true;
                    testBoard[i][column] = player;
                    if (testForWin(testBoard, player, blankPieces)) {
                        testBoard[i][column] = EMPTY_SPACE;
                        return true;
                    } else {
                        testBoard[i][column] = EMPTY_SPACE;
                    }
                }
            }
            foundCheck = false;
        }
        return false;
    }

    //todo - write javadoc. Not sure what this method does

    /**
     * @param board
     * @param column
     * @param player
     * @param blankPieces
     * @return
     */
    public boolean testPiece(int board[][], int column, int player, int blankPieces) {
        int i;
        boolean foundCheck = false;

        for (i = 0; i < 5 && !foundCheck; i++) {
            if (board[i][column] == EMPTY_SPACE) {
                foundCheck = true;
                board[i + 1][column] = player;
                if (testForWin(board, player, blankPieces)) {
                    board[i + 1][column] = EMPTY_SPACE;
                    return true;
                } else {
                    board[i + 1][column] = EMPTY_SPACE;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the given player has won
     *
     * @param board  the state of the board
     * @param player the player ID number
     * @return <code>true</code> if the player has won, or otherwise
     * <code>false</code>
     */
    public boolean testForWin(int board[][], int player, int blankPieces) {
        int i;
        int j;
        int winCount;
        int blanks = 0;
        int tempI = 0;
        int tempJ = 0;

        //Horizontal check
        for (tempI = 0; tempI < ROW_SIZE; tempI++) {
            i = tempI;
            for (tempJ = 0; tempJ < 4; tempJ++) {
                j = tempJ;
                for (i = 0; i < ROW_SIZE; i++) {
                    winCount = 0;
                    for (j = 0; j < COL_SIZE; j++) {
                        if (board[i][j] == player) {
                            winCount++;

                        } else if (board[i][j] == EMPTY_SPACE && blankPieces != 0 && blanks < blankPieces) {
                            winCount++;
                            blanks++;
                        } else {
                            winCount = 0;
                            blanks = 0;
                        }
                        if (winCount == 4) {
                            return true;
                        }
                    }
                }
            }
        }
        //Vertical check
        for (i = 0; i < COL_SIZE; i++) {
            winCount = 0;
            for (j = 0; j < ROW_SIZE; j++) {
                if (board[j][i] == player) {
                    winCount++;
                } else {
                    winCount = 0;
                    blanks = 0;
                }
                if (winCount == 4) {
                    return true;
                }
            }
        }
        i = 0;
        j = 0;
        //Diagonal check
        while (tempI < 3) {
            i = tempI;
            tempJ = 0;
            while (tempJ < 4) {
                j = tempJ;
                winCount = 0;
                while (winCount < 4 && i < ROW_SIZE && j < COL_SIZE) {
                    if (board[i][j] == player) {
                        winCount++;
                        i++;
                        j++;
                    } else if (board[i][j] == EMPTY_SPACE && blankPieces != 0 &&
                            blanks < blankPieces) {
                        winCount++;
                        blanks++;
                        i++;
                        j++;
                    } else {
                        blanks = 0;
                        break;
                    }
                    if (winCount == 4) {
                        return true;
                    }
                }
                tempJ++;
                i = tempI;
            }
            tempI++;
        }
        j = 0;
        i = 3;
        tempI = 3;
        tempJ = 0;
        while (tempI < ROW_SIZE) {
            i = tempI;
            tempJ = 0;
            while (tempJ < 4) {
                j = tempJ;
                winCount = 0;
                while (winCount < 4 && i >= 0 && j <= COL_SIZE) {
                    if (board[i][j] == player) {
                        winCount++;
                        i--;
                        j++;
                    } else if (board[i][j] == EMPTY_SPACE && blankPieces != 0 &&
                            blanks < blankPieces) {
                        winCount++;
                        blanks++;
                        i--;
                        j++;
                    } else {
                        blanks = 0;
                        break;
                    }
                    if (winCount == 4) {
                        return true;
                    }
                }
                tempJ++;
                i = tempI;
            }
            tempI++;
        }
        return false;
    }
}