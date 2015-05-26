//package ass3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	private final int DIFFICULTY_HARD = 2;

	//Create AI player with default easy difficulty
	public Player(){
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

	//Create AI player with give difficulty setting
	public Player(int difficulty){
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

	public Player(String name){
		playerNum++;
		this.name = name;
		this.player = playerNum;

		if (player == 1) {
			colour = DEFAULT_PLAYER_ONE_COLOUR;
		} else {
			colour = DEFAULT_PLAYER_TWO_COLOUR;
		}
	}

	public int getNextAction(int board[][]){
		//Prompt player for move
		if (difficulty == DIFFICULTY_EASY) {
			if(board[0][3] == 0){
				return 3;
			} else if(board[0][2] == 0){
				return 2;
			}
			Random rand = new Random();
			int i = rand.nextInt(7);
			while(board[5][i] != 0){
				i = rand.nextInt(7);
			}
			return i;
		//Return medium AI move
		} else if (difficulty == DIFFICULTY_MEDIUM) {
			//todo need to add code for more complex decisions
			if(board[0][3] == 0){
				return 3;
			} else if(board[0][2] == 0){
				return 2;
			}
			int i = findPiece(board, 2);
			if (i != -1){
				return i;
			}
			i = findPiece(board, 1);
			if( i != -1){
				return i;
			}
			Random rand = new Random();
			i = rand.nextInt(7);
			while(board[5][i] != 0){
				i = rand.nextInt(7);
			}
			return i;
		//Return hard AI move
		} else {
			if(board[0][3] == 0){
				return 3;
			} else if(board[0][2] == 0){
				return 2;
			}
			int i = findPiece(board, 2);
			if (i != -1){
				return i;
			}
			i = findPiece(board, 1);
			if( i != -1){
				return i;
			}
			int[] checkPieces = new int[7];
			for(int j = 0; j < 7; j++){
				checkPieces[j] = 0;
				if(badPiece(board,j,1)){
					checkPieces[j] = 2;
				}
			}
			for(int j = 0; j < 7; j++){
				if(badPiece(board,j,2) && checkPieces[j] != 2){
					checkPieces[j] = 1;
				}
			}
			boolean checkForLegal = false;
			int k = 0;
			for(int j = 0; j < 7; j++){
				if(checkPieces[j] == 0 && board[5][j] == 0){
					checkForLegal = true;
				}
			}
			if(!checkForLegal){
				k++;
				for(int j = 0; j < 7; j++){
					if(checkPieces[j] <= 1 && board[5][j] == 0){
						checkForLegal = true;
					}
				}
			}
			if(!checkForLegal){
				k++;
			}
			System.out.printf("%d\n", k);
			Random rand = new Random();
			i = rand.nextInt(6);
			while(checkPieces[i] > k || (checkPieces[i] <= k && board[5][i] != 0)){
				i = rand.nextInt(7);
			}
			return i;
		}
	}

	public int getPlayerNum() { return playerNum; }

	public int getPlayer(){
		return player;
	}

	public String getColour(){
		return colour;
	}

	public String getName() { return name; }
	
	public int getDifficulty() {
		return difficulty;
	}

	public void setPlayerNum(int newPlayerNum) { playerNum = newPlayerNum; }

	public void setPlayer(int player) { this.player = player; }

	public void setColour(String colour) { this.colour = colour; }

	public void setName(String name) { this.name = name; }
	
	public void setDifficulty(int newDifficulty) {this.difficulty = newDifficulty; }
	
	public int findPiece(int testBoard[][], int player){
		int i;
		int j;
		boolean foundCheck = false;
		for(i = 0;i < 7; i++){
			if(testBoard[5][i] == 0){
				for(j = 0; j < 6 && !foundCheck;j++){
					if(testBoard[j][i] == 0){
						
						foundCheck = true;
						testBoard[j][i] = player;
						if(testForWin(testBoard,player)){
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
	
	public boolean badPiece(int board[][], int column, int player){
		int i;
		boolean foundCheck = false;
		for(i = 0; i < 5 && !foundCheck;i++){
			if(board[i][column] == 0){
				foundCheck = true;
				board[i+1][column] = 1;
				if(testForWin(board, 1)){
					board[i+1][column] = 0;
					return true;
				} else {
					board[i+1][column] = 0;
				}
			}
		}
		return false;
	}
	public boolean testForWin(int board[][], int player){
		int i;
		int j;
		int winCount;
		//Horizontal check
		for(i = 0 ; i < 6 ; i++ ){
			winCount = 0;
			for(j = 0; j < 7; j++){
				if(board[i][j] == player){
					winCount++;

				} else {
					winCount = 0;
				}
				if(winCount == 4){
					return true;
				}
			}
		}
		//Vertical check
		for(i = 0 ; i < 7 ; i++ ){
			winCount = 0;
			for(j = 0; j < 6 ; j++){
				if(board[j][i] == player){
					winCount++;
				} else {
					winCount = 0;
				}
				if(winCount == 4){
					return true;
				}
			}
		}
		
		int tempi = 0;
		int tempj = 0;
		i = 0; 
		j = 0; 
		while (tempi<3) {
			i = tempi;
			tempj = 0;
			while (tempj<4) {
				j = tempj;
				winCount = 0;

				while (winCount<4 && i < 6 && j < 7) {
					if(board[i][j] == player){
						winCount++;
						i++;
						j++;	
					} else {
						break;
					}
					if(winCount == 4){
						return true;
					}
				}
				tempj++;
				i = tempi;
			}
			tempi++;				
		}
		
		tempi = 3; i = 3; tempj = 0; j = 0;
		while (tempi<6) {
			i = tempi;
			tempj = 0;
			while (tempj<4) {
				j = tempj;
				winCount = 0;
				while (winCount<4 && i >= 0) {
					if(board[i][j] == player){
						winCount++;
						i--;
						j++;	
					} else {
						break;
					}
					if(winCount == 4){
						return true;
					}
				}
				tempj++;
				i = tempi;
			}
			tempi++;				
		}		
		return false;
	}
}