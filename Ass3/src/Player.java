import java.util.Random;

public class Player {
    private int difficulty;
    boolean isAI = false;
    private static int playerNum = 0;
    private int player;
    private String colour;
    private String name;
    private final String DEFAULT_PLAYER_ONE_COLOUR = "Red";
    private final String DEFAULT_PLAYER_TWO_COLOUR = "Yellow";
    private final int DIFFICULTY_EASY = 0;
    private final int DIFFICULTY_MEDIUM = 1;
    private final int ROW_SIZE = 6;
    private final int COL_SIZE = 7;

    /**
     * Construct an AI player. Defaults difficulty level to easy.
     */
    public Player() {
        playerNum++;
        this.name = "AI";
        this.player = playerNum;
        this.isAI = true;
        this.difficulty = DIFFICULTY_EASY;

        if (player == 1) {
            colour = DEFAULT_PLAYER_ONE_COLOUR;
        } else {
            colour = DEFAULT_PLAYER_TWO_COLOUR;
        }
    }

    /**
     * Construct an AI player, optionally sets difficulty level.
     * Defaults difficulty setting to easy.
     */
    public Player(int difficulty) {
        playerNum++;
        this.name = "AI";
        this.player = playerNum;
        this.isAI = true;
        this.difficulty = difficulty;

        if (player == 1) {
            colour = DEFAULT_PLAYER_ONE_COLOUR;
        } else {
            colour = DEFAULT_PLAYER_TWO_COLOUR;
        }
    }

    /**
     * Construct a player with given name.
     */
    public Player(String name) {
        playerNum++;
        this.name = name;
        this.player = playerNum;

        if (player == 1) {
            colour = DEFAULT_PLAYER_ONE_COLOUR;
        } else {
            colour = DEFAULT_PLAYER_TWO_COLOUR;
        }
    }

    /**
     * Gets the AI's move
     *
     * @param board the state of the board
     * @return the AI's column number choice.
     */
    public int makeMove(int board[][]) {
        //Prompt player for move
        if (difficulty == DIFFICULTY_EASY) {
            if (board[0][3] == 0) {
                return 3;
            } else if (board[0][2] == 0) {
                return 2;
            }
            Random rand = new Random();
            int i = rand.nextInt(COL_SIZE);
            while (board[ROW_SIZE - 1][i] != 0) {
                i = rand.nextInt(COL_SIZE);
            }
            return i;
            //Return medium AI move
        } else if (difficulty == DIFFICULTY_MEDIUM) {
            //todo need to add code for more complex decisions
            if (board[0][3] == 0) {
                return 3;
            } else if (board[0][2] == 0) {
                return 2;
            }
            int i = findPiece(board, 2);
            if (i != -1) {
                return i;
            }
            i = findPiece(board, 1);
            if (i != -1) {
                return i;
            }
            Random rand = new Random();
            i = rand.nextInt(COL_SIZE);
            while (board[ROW_SIZE - 1][i] != 0) {
                i = rand.nextInt(COL_SIZE);
            }
            return i;
            //Return hard AI move
        } else {
            if (board[0][3] == 0) {
                return 3;
            } else if (board[0][2] == 0) {
                return 2;
            }
            int i = findPiece(board, 2);
            if (i != -1) {
                return i;
            }
            i = findPiece(board, 1);
            if (i != -1) {
                return i;
            }
            int[] checkPieces = new int[COL_SIZE];
            for (int j = 0; j < COL_SIZE; j++) {
                checkPieces[j] = 0;
                if (badPiece(board, j)) {
                    checkPieces[j] = 2;
                }
            }
            for (int j = 0; j < COL_SIZE; j++) {
                if (badPiece(board, j) && checkPieces[j] != 2) {
                    checkPieces[j] = 1;
                }
            }
            boolean checkForLegal = false;
            int k = 0;
            for (int j = 0; j < COL_SIZE; j++) {
                if (checkPieces[j] == 0 && board[ROW_SIZE - 1][j] == 0) {
                    checkForLegal = true;
                }
            }
            if (!checkForLegal) {
                k++;
                for (int j = 0; j < COL_SIZE; j++) {
                    if (checkPieces[j] <= 1 && board[ROW_SIZE - 1][j] == 0) {
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
            while (checkPieces[i] > k || (checkPieces[i] <= k && board[ROW_SIZE - 1][i] != 0)) {
                i = rand.nextInt(COL_SIZE);
            }
            return i;
        }
    }

    /**
     * @return the number of players in the game
     */
    public int getPlayerNum() {
        return playerNum;
    }

    /**
     * @return the ID number of the player
     */
    public int getPlayer() {
        return player;
    }

    /**
     * @return the colour of the player's game pieces
     */
    public String getColour() {
        return colour;
    }

    /**
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * @return the difficulty of the game
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the number of players in the game
     * @param playerNum the number of players
     */
    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
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
     * Sets the colour of the player's game pieces
     *
     * @param colour the colour of the player's sgame pieces
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     * Sets the name of the player
     *
     * @param name the name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the difficulty level of the AI
     *
     * @param difficulty the difficulty level of the AI
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    //todo - write javadoc. Not sure what this method does
    /**
     *
     *
     * @param testBoard
     * @param player
     * @return
     */
    public int findPiece(int testBoard[][], int player) {
        int i;
        int j;
        boolean foundCheck = false;

        for (i = 0; i < COL_SIZE; i++) {
            if (testBoard[ROW_SIZE-1][i] == 0) {
                for (j = 0; j < ROW_SIZE && !foundCheck; j++) {
                    if (testBoard[j][i] == 0) {
                        foundCheck = true;
                        testBoard[j][i] = player;
                        if (testForWin(testBoard, player)) {
                            testBoard[j][i] = 0;
                            return i;
                        } else {
                            testBoard[j][i] = 0;
                        }
                    }
                }
                foundCheck = false;
            }
        }

        return -1;
    }

    //todo - write javadoc. Not sure what this method does
    /**
     *
     *
     * @param board
     * @param column
     * @return
     */
    public boolean badPiece(int board[][], int column) {
        int i;
        boolean foundCheck = false;

        for (i = 0; i < ROW_SIZE - 1 && !foundCheck; i++) {
            if (board[i][column] == 0) {
                foundCheck = true;
                board[i + 1][column] = 1;
                if (testForWin(board, 1)) {
                    board[i + 1][column] = 0;
                    return true;
                } else {
                    board[i + 1][column] = 0;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the given player has won
     *
     * @param board the state of the board
     * @param player the player ID number
     * @return <code>true</code> if the player has won, or otherwise
     * <code>false</code>
     */
    public boolean testForWin(int board[][], int player) {
        int i;
        int j;
        int winCount;
        //Horizontal check
        for (i = 0; i < ROW_SIZE; i++) {
            winCount = 0;
            for (j = 0; j < COL_SIZE; j++) {
                if (board[i][j] == player) {
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
                if (board[j][i] == player) {
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
                    if (board[i][j] == player) {
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
                while (winCount < 4 && i >= 0) {
                    if (board[i][j] == player) {
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
}