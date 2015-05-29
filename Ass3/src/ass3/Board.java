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

	public Board(int numPlayers){
		this.boardState = new int[DEFAULT_ROW_SIZE][DEFAULT_COL_SIZE];
		this.colSize = DEFAULT_COL_SIZE;
		this.rowSize = DEFAULT_ROW_SIZE;
		this.player = 0;
		numPieces = 0;
		int i;
		int j;
		this.AI = new Player(0);
		this.numPlayers = numPlayers;
		if(numPlayers == 3){
			winRequired = 3;
		} else if (numPlayers == 2){
			winRequired = 4;
		}
		

		for (i = 0; i < DEFAULT_ROW_SIZE; i++) {
			for (j = 0; j < DEFAULT_COL_SIZE; j++) {
				this.boardState[i][j] = 0;
			}
		}
	}




	//need to check if numPieces is < total num of pieces (42 for default 7x6 board)
	public boolean addPiece(int column){
		if(!isRunning()){
			return false;
		}
		if(column < 0 || column >= colSize){ // shouldn't be necessary in final product but just in case
			return false;
		}
		for(int i = 0; i < rowSize;i++){
			if(boardState[i][column] == 0){
				if (player == 1){
					player = 2;
				} else if (player == 2 && numPlayers == 3){
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
		//this.print();
		return false;
	}

	public boolean hasWon(){
		winningPieces = new ArrayList<Coordinates>();
		int i;
		int j;
		int winCount;

		//Horizontal check
		for(i = 0 ; i < rowSize ; i++ ){
			winCount = 0;
			for(j = 0; j < colSize; j++){
				if(boardState[i][j] == player){
					winCount++;
					winningPieces.add(new Coordinates(i,j));
				} else {
					winCount = 0;
					winningPieces.clear();
				}
				if(winCount == winRequired){
					return true;
				}
			}
		}
		//Vertical check
		for(i = 0 ; i < colSize ; i++ ){
			winCount = 0;
			for(j = 0; j < rowSize ; j++){
				if(boardState[j][i] == player){
					winCount++;
					winningPieces.add(new Coordinates(j,i));
				} else {
					winCount = 0;
					winningPieces.clear();
				}
				if(winCount == winRequired){
					return true;
				}
			}
		}
		
		int tempi = 0;
		int tempj = 0;
		i = 0; 
		j = 0; 
		while (tempi<rowSize-(winRequired-1)) {
			i = tempi;
			tempj = 0;
			while (tempj<colSize-(winRequired-1)) {
				j = tempj;
				winCount = 0;
				winningPieces.clear();
				while (winCount<winRequired && i < rowSize && j < colSize) {
					if(boardState[i][j] == player){
						winCount++;
						winningPieces.add(new Coordinates(i,j));
						i++;
						j++;	
					} else {
						break;
					}
					if(winCount == winRequired){
						return true;
					}
				}
				tempj++;
				i = tempi;
			}
			tempi++;				
		}
		
		tempi = 3; i = 3; tempj = 0; j = 0;
		while (tempi<rowSize) {
			i = tempi;
			tempj = 0;
			while (tempj<colSize-(winRequired-1)) {
				j = tempj;
				winCount = 0;
				winningPieces.clear();
				while (winCount<winRequired && i >= 0 && j < 7) {
					if(boardState[i][j] == player){
						winCount++;
						winningPieces.add(new Coordinates(i,j));
						i--;
						j++;	
					} else {
						break;
					}
					if(winCount == winRequired){
						return true;
					}
				}
				tempj++;
				i = tempi;
			}
			tempi++;				
		}		
		winningPieces.clear();
		return false;
	}
	public boolean checkDraw(){
		if(numPieces == 42){
			return true;
		} else {
			return false;
		}
	}
	public void print(){
		int i;
		int j;
		System.out.println("Current Board:");
		for(i = 0 ; i < rowSize ; i++ ){
			System.out.print("|");
			for(j = 0; j < colSize ; j++){
				System.out.print(boardState[i][j] + "|");
			}
		System.out.println("");
		}
	}
	public int getPlayer(){
		return player;
	}

	public boolean isAI(){
		return AIGame;
	}
	public int getAITurn(){
		isAITurn = false;
		return AI.makeMove(boardState);
	}
	public void toggleAI(boolean AItoggle){
		if(!gameRunning){
			AIGame = AItoggle;
		}
	}
	public void toggleGameState(boolean running){
		
		player = numPlayers;
		gameRunning = running;
		
	}
	
	public void resetGame(){
		gameRunning = true;
		for (int i = 0; i < rowSize; i++){
			for(int j = 0; j < colSize; j++){
				boardState[i][j] = 0;
			}
		}
		winningPieces.clear();
		numPieces = 0;
		player = 0;
	}
	public boolean isRunning(){
		return gameRunning;
	}
	public int getNumPlayers(){ return numPlayers; }
	

	public void setAI(int difficulty){
		AI.setDifficulty(difficulty);
	}
	public boolean isWinPiece(int column, int row){
		if(winningPieces.size() == 0){
			return false;
		} 
		for(int i = 0; i < winRequired; i++){
			if(winningPieces.get(i).getCol() == column && winningPieces.get(i).getRow() == row){
				return true;
			}
		}
		return false;
	}
	public void pieceFalling(){
		if(pieceFalling){
			pieceFalling = false;
		} else{
			pieceFalling = true;
		}
	}
	public void makeAITurn(){
		isAITurn = true;			
	}
	public boolean ifAITurn(){
		return isAITurn;
	}
	public boolean ifFalling(){
		return pieceFalling;
	}
}

