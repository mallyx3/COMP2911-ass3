
public class Board {
	private Integer[][] boardState;
	private int colSize;
	private int rowSize;
	private int player;
	private Player AI;
	private static int numPieces;
	private static int DEFAULT_ROW_SIZE = 6;
	private static int DEFAULT_COL_SIZE = 7;
	private boolean AIGame = true;
	private boolean gameRunning = false;

	public Board(){
		this.boardState = new Integer[DEFAULT_ROW_SIZE][DEFAULT_COL_SIZE];
		this.colSize = DEFAULT_COL_SIZE;
		this.rowSize = DEFAULT_ROW_SIZE;
		this.player = 0;
		numPieces = 0;
		int i;
		int j;
		this.AI = new Player(0);
		

		for (i = 0; i < DEFAULT_ROW_SIZE; i++) {
			for (j = 0; j < DEFAULT_COL_SIZE; j++) {
				this.boardState[i][j] = 0;
			}
		}
	}

	public Board(int rowSize, int colSize){
		this.boardState = new Integer[rowSize][colSize];
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

	public Integer[][] getBoardState (){ return boardState; }

	public int getColSize (){ return colSize; }

	public int getRowSize (){ return rowSize; }

	public int getNumPieces (){ return numPieces; }

	//Should we have this? Seems dangerous if were not updating the boardState at the same time
	public void setColSize (int colSize) { this.colSize = colSize; }
	//Should we have this? Seems dangerous if were not updating the boardState at the same time
	public void setRowSize (int rowSize) { this.rowSize = rowSize; }

	public void setNumPieces (int newNumPieces) { numPieces = newNumPieces; }

	//need to check if numPieces is < total num of pieces (42 for default 7x6 board)
	public boolean addPiece(int column){
		if(!gameRunning){
			return false;
		}
		if(column < 0 || column >= colSize){ // shouldn't be necessary in final product but just in case
			return false;
		}
		for(int i = 0; i < colSize;i++){
			if(boardState[i][column] == 0){
				if(player == 1){
					player = 2;
				} else {
					player = 1;
				}
				boardState[i][column] = player;
				this.print();
				numPieces++;
				return true;
			}
		}
		this.print();
		return false;
	}

	public boolean hasWon(int player){
		int i;
		int j;
		int winCount;

		//Horizontal check
		for(i = 0 ; i < rowSize ; i++ ){
			winCount = 0;
			for(j = 0; j < colSize; j++){
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
		for(i = 0 ; i < colSize ; i++ ){
			winCount = 0;
			for(j = 0; j < rowSize ; j++){
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
		while (tempi<rowSize-3) {
			i = tempi;
			tempj = 0;
			while (tempj<colSize-3) {
				j = tempj;
				winCount = 0;
				while (winCount<4) {
					if(boardState[j][i] == player){
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
			}
			tempi++;				
		}
		
		tempi = 3; i = 3; tempj = 0; j = 0;
		while (tempi<rowSize) {
			i = tempi;
			tempj = 0;
			while (tempj<colSize-3) {
				j = tempj;
				winCount = 0;
				while (winCount<4) {
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
			}
			tempi++;				
		}		
		return false;
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
	public void setPlayer(){
		player = 1;
	}
	public boolean isAI(){
		return AIGame;
	}
	public int getAITurn(){
		return AI.getNextAction();
	}
	public void toggleAI(){
		if(AIGame){
			AIGame = false;
		} else{
			AIGame = true;
		}
	}
	public void startGame(){
		gameRunning = true;
	}
	public void resetGame(){
		gameRunning = false;
		for (int i = 0; i < rowSize; i++){
			for(int j = 0; j < colSize; j++){
				boardState[i][j] = 0;
			}
		}
		player = 0;
	}
	public boolean isRunning(){
		return gameRunning;
	}
}