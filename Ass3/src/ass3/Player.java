package ass3;
import java.util.Random;

public class Player {
	private int difficulty;

	

	private final int DIFFICULTY_EASY = 0;
	private final int DIFFICULTY_MEDIUM = 1;

	//Create AI player with give difficulty setting
	public Player(int difficulty){
		
		this.difficulty = difficulty;

		
	}

	

	public int makeMove(int board[][]){
		if (difficulty == DIFFICULTY_EASY) {
			//Attempts to get centre piece
			if(board[0][3] == 0){
				return 3;
			} else if(board[0][2] == 0 && board[0][3] != 2){
				return 2;
			}
			
			//Checks for any potential winning moves
			for(int j = 0; j < 7;j++){
				if(findPiece(board,j, 2, 0)){
				return j;
				}
			}
			//Checks for any blocking moves
			for(int j = 0; j < 7;j++){
				if(findPiece(board,j, 1, 0)){

				return j;
				}
			}
			//Creates a list of moves which will result in a loss next turn
			int[] checkPieces = new int[7];
			for(int j = 0; j < 7; j++){
				checkPieces[j] = 0;
				if(testPiece(board,j,1,0)){
					checkPieces[j] = 2;
				}
			}
			//Adds to list any moves that can result in a win being blocked next turn
			for(int j = 0; j < 7; j++){
				if(testPiece(board,j,2,0) && checkPieces[j] < 3){
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
			
			Random rand = new Random();
			int i = rand.nextInt(7);
			while(checkPieces[i] > k || (checkPieces[i] <= k && board[5][i] != 0)){
				i = rand.nextInt(7);
			}
			return i;
		} else if (difficulty == DIFFICULTY_MEDIUM) {
			//Attempts to get centre piece
			if(board[0][3] == 0){
				return 3;
			} else if(board[0][2] == 0 && board[0][3] != 2){
				return 2;
			}
			
			//Checks for any potential winning moves
			for(int j = 0; j < 7;j++){
				if(findPiece(board,j, 2, 0)){
				return j;
				}
			}
			//Checks for any blocking moves
			for(int j = 0; j < 7;j++){
				if(findPiece(board,j, 1, 0)){

				return j;
				}
			}
			//Creates a list of moves which will result in a loss next turn
			int[] checkPieces = new int[7];
			for(int j = 0; j < 7; j++){
				checkPieces[j] = 0;
				if(testPiece(board,j,1,0)){
					checkPieces[j] = 2;
				}
			}
			//Adds to list any moves that can result in a win being blocked next turn
			for(int j = 0; j < 7; j++){
				if(testPiece(board,j,2,0) && checkPieces[j] < 3){
					checkPieces[j] = 1;
				}
			}
			
			//Using blank spaces, attempts to block any attempts to setup double win condition
			for(int j = 0; j < 7;j++){
				if(findPiece(board,j, 1, 1) && checkPieces[j] == 0){
				return j;
				}
			}
			//Uses blank spaces to help setup potential win moves
			for(int j = 0; j < 7;j++){
				if(findPiece(board,j,2,1) && checkPieces[j] < 1){
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

			Random rand = new Random();
			int i = rand.nextInt(7);
			while(checkPieces[i] > k || (checkPieces[i] <= k && board[5][i] != 0)){
				i = rand.nextInt(7);
			}
			return i;
		
		//Return hard AI move
		} else {
			//Attempts to get centre piece
			if(board[0][3] == 0){
				return 3;
			} else if(board[0][2] == 0 && board[0][3] != 2){
				return 2;
			}
			
			//Checks for any potential winning moves
			for(int j = 0; j < 7;j++){
				if(findPiece(board,j, 2, 0)){
				return j;
				}
			}
			//Checks for any blocking moves
			for(int j = 0; j < 7;j++){
				if(findPiece(board,j, 1, 0)){
					System.out.print("Did I win???\n");
				return j;
				}
			}
			//Creates a list of moves which will result in a loss next turn
			int[] checkPieces = new int[7];
			for(int j = 0; j < 7; j++){
				checkPieces[j] = 0;
				if(testPiece(board,j,1,0)){
					checkPieces[j] = 3;
				}
			}
			//Adds to list any moves that can result in a win being blocked next turn
			for(int j = 0; j < 7; j++){
				if(testPiece(board,j,2,0) && checkPieces[j] < 3){
					checkPieces[j] = 2;
				}
			}
			
			//Using blank spaces, attempts to block any attempts to setup double win condition
			for(int j = 0; j < 7;j++){
				if(findPiece(board,j, 1, 1) && checkPieces[j] == 0){
				return j;
				}
			}
			//Uses blank spaces to help setup potential win moves
			for(int j = 0; j < 7;j++){
				if(findPiece(board,j,2,1) && checkPieces[j] < 2){
					return j;
				}
			}
			for(int j = 0; j < 7;j++){
				if(testPiece(board,j, 1, 1)){
					checkPieces[j] = 1;
				}
			}
			for(int j = 0; j < 7; j++){
				if(testPiece(board,j,2,1) && checkPieces[j] < 2){
					return j;
				}
			}
			for(int j = 0; j < 7;j++){
				if(findPiece(board,j, 2, 2) && checkPieces[j] == 0){
				return j;
				}
			}
			for(int j = 0; j < 7;j++){
				if(findPiece(board,j, 1, 2) && checkPieces[j] == 0){
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
				for(int j = 0; j < 7; j++){
					if(checkPieces[j] <= 2 && board[5][j] == 0){
						checkForLegal = true;
					}
				}
			}
			if(!checkForLegal){
				k++;
			}

			Random rand = new Random();
			int i = rand.nextInt(7);
			while(checkPieces[i] > k || (checkPieces[i] <= k && board[5][i] != 0)){
				i = rand.nextInt(7);
			}
			return i;
		}
	}


	

	
	



	
	
	public void setDifficulty(int newDifficulty) {this.difficulty = newDifficulty; }
	
	public boolean findPiece(int testBoard[][],int column, int player, int blankPieces){

		int j;
		boolean foundCheck = false;
			if(testBoard[5][column] == 0){
				for(j = 0; j < 6 && !foundCheck;j++){
					if(testBoard[j][column] == 0){
						foundCheck = true;
						testBoard[j][column] = player;
						if(blankPieces > 0){
							if(testForWin(testBoard,player,blankPieces)){
								testBoard[j][column] = 0;
								if(!testForWin(testBoard,player,blankPieces)){
									return true;
								}
							} else {
								testBoard[j][column] = 0;
							}
						} else {
							if(hasWon(testBoard,player)){
								testBoard[j][column] = 0;
								return true;
							} else {
								testBoard[j][column] = 0;
							}
						}
					}
				}
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
				if(blankPieces > 0){
					if(testForWin(board, player, blankPieces)){
						board[i+1][column] = 0;
						if(!testForWin(board,player,blankPieces)){
							return true;
						}
					} else {
						board[i+1][column] = 0;
					}		
				} else {
					if(hasWon(board,player)){
						board[i+1][column] = 0;
						return true;
					} else {
						board[i+1][column] = 0;
					}
				}
			}
		}
		return false;
	}
	public boolean hasWon(int[][] boardState, int player){

		int i;
		int j;
		int winCount;

		//Horizontal check
		for(i = 0 ; i < 6 ; i++ ){
			winCount = 0;
			for(j = 0; j < 7; j++){
				if(boardState[i][j] == player){
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
				if(boardState[j][i] == player){
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
					if(boardState[i][j] == player){
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
				while (winCount<4 && i >= 0 && j < 7) {
					if(boardState[i][j] == player){
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
				} else if(board[j][i] == 0 && blanks < blankPieces && blankPieces < 2){
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