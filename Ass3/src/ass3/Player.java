package ass3;

import java.util.Random;

public class Player {
    private int difficulty;
    private final int DIFFICULTY_EASY = 0;
    private final int DIFFICULTY_MEDIUM = 1;
    private final int ROW_SIZE = 6;
    private final int COL_SIZE = 7;
    private final int EMPTY_SPACE = 0;

    /**
     * Constructs a player with the provided difficulty setting
     * Assumes difficulty provided is >= 0 and <= 2
     *
     * @param difficulty is the difficulty setting
     */
    public Player(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Given a board by the main Back End, will find an
     * optimal move based on the difficulty setting of the AI
     *
     * @param board is the board used to find move
     * @return the column AI chooses
     */
    public int makeMove(int board[][]) {
        int i;
        int j;

        if (difficulty == DIFFICULTY_EASY) {
            //Attempts to get centre piece
            if (board[0][3] == EMPTY_SPACE) {
                return 3;
            } else if (board[0][2] == EMPTY_SPACE && board[0][3] != 2) {
                return 2;
            }
            //Checks for any potential winning moves
            for (j = 0; j < COL_SIZE; j++) {
                if (findPiece(board, j, 2, 0)) {
                    return j;
                }
            }
            //Checks for any blocking moves
            for (j = 0; j < COL_SIZE; j++) {
                if (findPiece(board, j, 1, 0)) {

                    return j;
                }
            }
            //Creates a list of moves which will result in a loss next turn
            int[] checkPieces = new int[COL_SIZE];
            for (j = 0; j < COL_SIZE; j++) {
                checkPieces[j] = 0;
                if (testPiece(board, j, 1, 0)) {
                    checkPieces[j] = 2;
                }
            }
            //Adds to list any moves that can result in a win being blocked next turn
            for (j = 0; j < COL_SIZE; j++) {
                if (testPiece(board, j, 2, 0) && checkPieces[j] < 3) {
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
            Random rand = new Random();
            i = rand.nextInt(COL_SIZE);
            while (checkPieces[i] > k || (checkPieces[i] <= k && board[5][i] != EMPTY_SPACE)) {
                i = rand.nextInt(COL_SIZE);
            }
            return i;
        } else if (difficulty == DIFFICULTY_MEDIUM) {
            //Attempts to get centre piece
            if (board[0][3] == EMPTY_SPACE) {
                return 3;
            } else if (board[0][2] == EMPTY_SPACE && board[0][3] != 2) {
                return 2;
            }
            //Checks for any potential winning moves
            for (j = 0; j < COL_SIZE; j++) {
                if (findPiece(board, j, 2, 0)) {
                    return j;
                }
            }
            //Checks for any blocking moves
            for (j = 0; j < COL_SIZE; j++) {
                if (findPiece(board, j, 1, 0)) {

                    return j;
                }
            }
            //Creates a list of moves which will result in a loss next turn
            int[] checkPieces = new int[COL_SIZE];
            for (j = 0; j < COL_SIZE; j++) {
                checkPieces[j] = 0;
                if (testPiece(board, j, 1, 0)) {
                    checkPieces[j] = 2;
                }
            }
            //Adds to list any moves that can result in a win being blocked next turn
            for (j = 0; j < COL_SIZE; j++) {
                if (testPiece(board, j, 2, 0) && checkPieces[j] < 3) {
                    checkPieces[j] = 1;
                }
            }
            //Using blank spaces, attempts to block any attempts to setup double win condition
            for (j = 0; j < COL_SIZE; j++) {
                if (findPiece(board, j, 1, 1) && checkPieces[j] == 0) {
                    return j;
                }
            }
            //Uses blank spaces to help setup potential win moves
            for (j = 0; j < COL_SIZE; j++) {
                if (findPiece(board, j, 2, 1) && checkPieces[j] < 1) {
                    return j;
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
            Random rand = new Random();
            i = rand.nextInt(COL_SIZE);
            while (checkPieces[i] > k || (checkPieces[i] <= k && board[5][i] != EMPTY_SPACE)) {
                i = rand.nextInt(COL_SIZE);
            }
            return i;
            //Return hard AI move
        } else {
            //Attempts to get centre piece
            if (board[0][3] == EMPTY_SPACE) {
                return 3;
            } else if (board[0][2] == EMPTY_SPACE && board[0][3] != 2) {
                return 2;
            }
            //Checks for any potential winning moves
            for (j = 0; j < COL_SIZE; j++) {
                if (findPiece(board, j, 2, 0)) {
                    return j;
                }
            }
            //Checks for any blocking moves
            for (j = 0; j < COL_SIZE; j++) {
                if (findPiece(board, j, 1, 0)) {
                    System.out.print("Did I win???\n");
                    return j;
                }
            }
            //Creates a list of moves which will result in a loss next turn
            int[] checkPieces = new int[COL_SIZE];
            for (j = 0; j < COL_SIZE; j++) {
                checkPieces[j] = 0;
                if (testPiece(board, j, 1, 0)) {
                    checkPieces[j] = 3;
                }
            }
            //Adds to list any moves that can result in a win being blocked next turn
            for (j = 0; j < COL_SIZE; j++) {
                if (testPiece(board, j, 2, 0) && checkPieces[j] < 3) {
                    checkPieces[j] = 2;
                }
            }
            //Using blank spaces, attempts to block any attempts to setup double win condition
            for (j = 0; j < COL_SIZE; j++) {
                if (findPiece(board, j, 1, 1) && checkPieces[j] == 0) {
                    return j;
                }
            }
            //Uses blank spaces to help setup potential win moves
            for (j = 0; j < COL_SIZE; j++) {
                if (findPiece(board, j, 2, 1) && checkPieces[j] < 2) {
                    return j;
                }
            }
            for (j = 0; j < COL_SIZE; j++) {
                if (testPiece(board, j, 1, 1)) {
                    checkPieces[j] = 1;
                }
            }
            for (j = 0; j < COL_SIZE; j++) {
                if (testPiece(board, j, 2, 1) && checkPieces[j] < 2) {
                    return j;
                }
            }
            for (j = 0; j < COL_SIZE; j++) {
                if (findPiece(board, j, 2, 2) && checkPieces[j] == 0) {
                    return j;
                }
            }
            for (j = 0; j < COL_SIZE; j++) {
                if (findPiece(board, j, 1, 2) && checkPieces[j] == 0) {
                    return j;
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
                for (j = 0; j < COL_SIZE; j++) {
                    if (checkPieces[j] <= 2 && board[5][j] == EMPTY_SPACE) {
                        checkForLegal = true;
                    }
                }
            }
            if (!checkForLegal) {
                k++;
            }
            Random rand = new Random();
            i = rand.nextInt(COL_SIZE);
            while (checkPieces[i] > k || (checkPieces[i] <= k && board[5][i] != EMPTY_SPACE)) {
                i = rand.nextInt(COL_SIZE);
            }
            return i;
        }
    }

    /**
     * Updates the difficulty
     *
     * @param newDifficulty
     */
    public void setDifficulty(int newDifficulty) {
        this.difficulty = newDifficulty;
    }

    /**
     * Tests whether a move made by the given player will result
     * in a victory, with potential blank spaces for testing
     * a player can get 3/4 pieces in a row
     *
     * @param testBoard   the board used to test
     * @param column      the tested move being made
     * @param player      the player which is being tested for victory
     * @param blankPieces the number of blank spaces allowed
     * @return <code>true</code> if a victory would occur,
     * <code>false</code> if no victory.
     */
    public boolean findPiece(int testBoard[][], int column, int player, int blankPieces) {
        int j;
        boolean foundCheck = false;

        if (testBoard[5][column] == EMPTY_SPACE) {
            for (j = 0; j < ROW_SIZE && !foundCheck; j++) {
                if (testBoard[j][column] == EMPTY_SPACE) {
                    foundCheck = true;
                    testBoard[j][column] = player;
                    if (blankPieces > 0) {
                        if (testForWin(testBoard, player, blankPieces)) {
                            testBoard[j][column] = EMPTY_SPACE;
                            if (!testForWin(testBoard, player, blankPieces)) {
                                return true;
                            }
                        } else {
                            testBoard[j][column] = EMPTY_SPACE;
                        }
                    } else {
                        if (hasWon(testBoard, player)) {
                            testBoard[j][column] = EMPTY_SPACE;
                            return true;
                        } else {
                            testBoard[j][column] = EMPTY_SPACE;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Tests whether a player will win if a piece is placed above the next row
     * or less with blank pieces
     *
     * @param board       the board used to test
     * @param column      the tested move being made
     * @param player      the player which is being tested for victory
     * @param blankPieces the number of blank spaces allowed
     * @return <code>true</code> if a victory would occur,
     * <code>false</code> if no victory.
     */
    public boolean testPiece(int board[][], int column, int player, int blankPieces) {
        int i;
        boolean foundCheck = false;

        for (i = 0; i < 5 && !foundCheck; i++) {
            if (board[i][column] == EMPTY_SPACE) {
                foundCheck = true;
                board[i + 1][column] = player;
                if (blankPieces > 0) {
                    if (testForWin(board, player, blankPieces)) {
                        board[i + 1][column] = EMPTY_SPACE;
                        if (!testForWin(board, player, blankPieces)) {
                            return true;
                        }
                    } else {
                        board[i + 1][column] = EMPTY_SPACE;
                    }
                } else {
                    if (hasWon(board, player)) {
                        board[i + 1][column] = EMPTY_SPACE;
                        return true;
                    } else {
                        board[i + 1][column] = EMPTY_SPACE;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks for victory with a testing board for a player
     *
     * @param boardState the test board
     * @param player     the player being tested
     * @return <code>true</code> if a player would win,
     * <code>false</code> if they don't
     */
    public boolean hasWon(int[][] boardState, int player) {
        int i;
        int j;
        int winCount;

        //Horizontal check
        for (i = 0; i < ROW_SIZE; i++) {
            winCount = 0;
            for (j = 0; j < COL_SIZE; j++) {
                if (boardState[i][j] == player) {
                    winCount++;
                } else {
                    winCount = 0;
                }
                if (winCount == 4) {
                    return true;
                }
            }
        }
        //Vertical check
        for (i = 0; i < COL_SIZE; i++) {
            winCount = 0;
            for (j = 0; j < ROW_SIZE; j++) {
                if (boardState[j][i] == player) {
                    winCount++;
                } else {
                    winCount = 0;
                }
                if (winCount == 4) {
                    return true;
                }
            }
        }
        int tempI = 0;
        int tempJ = 0;
        i = 0;
        j = 0;
        while (tempI < 3) {
            i = tempI;
            tempJ = 0;
            while (tempJ < 4) {
                j = tempJ;
                winCount = 0;
                while (winCount < 4 && i < ROW_SIZE && j < COL_SIZE) {
                    if (boardState[i][j] == player) {
                        winCount++;
                        i++;
                        j++;
                    } else {
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
        tempI = 3;
        i = 3;
        tempJ = 0;
        j = 0;
        while (tempI < ROW_SIZE) {
            i = tempI;
            tempJ = 0;
            while (tempJ < 4) {
                j = tempJ;
                winCount = 0;
                while (winCount < 4 && i >= 0 && j < COL_SIZE) {
                    if (boardState[i][j] == player) {
                        winCount++;
                        i--;
                        j++;
                    } else {
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

    /**
     * Tests for a victory for a player with permitted blank spaces,
     * used to stop 3/4 pieces or any double win condition strategies
     *
     * @param board       the test board
     * @param player      the player being tested
     * @param blankPieces the number of permitted blank spaces
     * @return <code>true</code> if a victory is found,
     * <code>false</code> if no victory found
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
                } else if (board[j][i] == EMPTY_SPACE && blanks < blankPieces && blankPieces < 2) {
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
        i = 0;
        j = 0;
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
        tempI = 3;
        i = 3;
        tempJ = 0;
        j = 0;
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