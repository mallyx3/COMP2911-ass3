//package ass3;

import java.util.Random;

public class Player {
	private int difficulty;

	private int player = 2;
	

	private final int DIFFICULTY_EASY = 0;
	private final int DIFFICULTY_MEDIUM = 1;
	private final int DIFFICULTY_HARD = 2;

	//Create AI player with default easy difficulty

	//Create AI player with give difficulty setting
	public Player(int difficulty){
		
		this.difficulty = difficulty;

		
	}

	

	public int getNextAction(int board[][]){
		if (difficulty == DIFFICULTY_EASY) {
			if(board[0][3] == 0){
				return 3;
			} 
			for(int j = 0; j < 7;j++){
				if(findPiece(board,j, 2, 0)){
				return j;
				}
			}
			for(int j = 0; j < 7;j++){
				if(findPiece(board,j, 1, 0)){
				return j;
				}
			}
			Random rand = new Random();
			int i = rand.nextInt(7);
			while(board[5][i] != 0){
				i = rand.nextInt(7);
			}
			return i;
		} else if (difficulty == DIFFICULTY_MEDIUM) {
			if(board[0][3] == 0){
				return 3;
			} 
			for(int j = 0; j < 7;j++){
				if(findPiece(board,j, 2, 0)){
				return j;
				}
			}
			for(int j = 0; j < 7;j++){
				if(findPiece(board,j, 1, 0)){
				return j;
				}
			}
			int[] checkPieces = new int[7];
			for(int j = 0; j < 7; j++){
				checkPieces[j] = 0;
				if(testPiece(board,j,1,0)){
					checkPieces[j] = 2;
				}
			}
			for(int j = 0; j < 7; j++){
				if(testPiece(board,j,2,0) && checkPieces[j] != 2){
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
			int i = rand.nextInt(7);
			while(checkPieces[i] > k || (checkPieces[i] <= k && board[5][i] != 0)){
				i = rand.nextInt(7);
			}
			return i;
		
		//Return hard AI move
		} else {
			if(board[0][3] == 0){
				return 3;
			} 
			for(int j = 0; j < 7;j++){
				if(findPiece(board,j, 2, 0)){
				return j;
				}
			}
			for(int j = 0; j < 7;j++){
				if(findPiece(board,j, 1, 0)){
				return j;
				}
			}
			int[] checkPieces = new int[7];
			for(int j = 0; j < 7; j++){
				checkPieces[j] = 0;
				if(testPiece(board,j,1,0)){
					checkPieces[j] = 2;
				}
			}
			for(int j = 0; j < 7; j++){
				if(testPiece(board,j,2,0) && checkPieces[j] != 2){
					checkPieces[j] = 1;
				}
			}
			for(int j = 0; j < 7;j++){
				if(findPiece(board,j, 1, 1) && checkPieces[j] == 0){
				return j;
				}
			}
			
			for(int j = 0; j < 7;j++){
				if(testPiece(board,j,2,1) && checkPieces[j] == 0){
					return j;
				}
			}
			for(int j = 0; j < 7;j++){
				if(findPiece(board,j, 2, 1) && checkPieces[j] < 2){
				return j;
				}
			}
			for(int j = 0; j < 7; j++){
				if(testPiece(board,j,2,1) && checkPieces[j] < 2){
					return j;
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
			int i = rand.nextInt(7);
			while(checkPieces[i] > k || (checkPieces[i] <= k && board[5][i] != 0)){
				i = rand.nextInt(7);
			}
			return i;
		}
	}


	public int getPlayer(){
		return player;
	}

	
	public int getDifficulty() {
		return difficulty;
	}


	public void setPlayer(int player) { this.player = player; }

	
	
	public void setDifficulty(int newDifficulty) {this.difficulty = newDifficulty; }
	
	public boolean findPiece(int testBoard[][],int column, int player, int blankPieces){
		int i;
		int j;
		boolean foundCheck = false;
		
			if(testBoard[5][column] == 0){
				for(j = 0; j < 6 && !foundCheck;j++){
					if(testBoard[j][column] == 0){
						
						foundCheck = true;
						testBoard[j][column] = player;
						if(testForWin(testBoard,player,blankPieces)){
							testBoard[j][column] = 0;
							return true;
						} else {
							testBoard[j][column] = 0;
						}
					}
				}
				foundCheck = false;
			}
		
		
		return false;
	}
	
	public boolean testPiece(int board[][], int column, int player, int blankPieces){
		int i;
		boolean foundCheck = false;
		for(i = 0; i < 5 && !foundCheck;i++){
			if(board[i][column] == 0){
				foundCheck = true;
				board[i+1][column] = player;
				if(testForWin(board, player, blankPieces)){
					board[i+1][column] = 0;
					return true;
				} else {
					board[i+1][column] = 0;
				}
			}
		}
		return false;
	}
	public boolean testForWin(int board[][], int player, int blankPieces){
		int i;
		int j;
		int winCount;
		int blanks = 0;
		int tempi = 0;
		int tempj = 0;
		//Horizontal check
		for(tempi = 0;tempi < 6;tempi++){
			i = tempi;
			for(tempj = 0; tempj < 4; tempj++){
				j = tempj;
				for(i = 0 ; i < 6 ; i++ ){
					winCount = 0;
					for(j = 0; j < 7; j++){
						if(board[i][j] == player){
							winCount++;
		
						} else if(board[i][j] == 0 && blankPieces != 0 && blanks < blankPieces){
							winCount++;
							blanks++;
						} else {
							winCount = 0;
							blanks = 0;
						}
						if(winCount == 4){
							return true;
						}
					}
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
					blanks = 0;
				}
				if(winCount == 4){
					return true;
				}
			}
		}
		
		
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
					} else if(board[i][j] == 0 && blankPieces != 0 && 
							  blanks < blankPieces){
						winCount++;
						blanks++;
						i++;
						j++;
					} else {
						blanks = 0;
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
				while (winCount<4 && i >= 0 && j <= 7) {
					if(board[i][j] == player){
						winCount++;
						i--;
						j++;	
					} else if(board[i][j] == 0 && blankPieces != 0 && 
							  blanks < blankPieces){
						winCount++;
						blanks++;
						i--;
						j++;	
					} else {
						blanks = 0;
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