
import java.util.ArrayList;


public class Board {
	private int[][] boardState;
	private int colSize;
	private int rowSize;
	private int player;
	private int numPlayers = 2;
	private Player AI;
	private static int numPieces;
	private static int DEFAULT_ROW_SIZE = 6;
	private static int DEFAULT_COL_SIZE = 7;
	private boolean AIGame = true;
	private boolean gameRunning = false;
	private ArrayList<Coordinates> winningPieces = new ArrayList<Coordinates>();

	public Board(){
		this.boardState = new int[DEFAULT_ROW_SIZE][DEFAULT_COL_SIZE];
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

	public int[][] getBoardState (){ return boardState; }

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
					winningPieces.add(new Coordinates(j,i));
				} else {
					winCount = 0;
					winningPieces.clear();
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
				winningPieces.clear();
				while (winCount<4 && i < rowSize && j < colSize) {
					if(boardState[i][j] == player){
						winCount++;
						winningPieces.add(new Coordinates(i,j));
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
		while (tempi<rowSize) {
			i = tempi;
			tempj = 0;
			while (tempj<colSize-3) {
				j = tempj;
				winCount = 0;
				winningPieces.clear();
				while (winCount<4 && i >= 0) {
					if(boardState[i][j] == player){
						winCount++;
						winningPieces.add(new Coordinates(i,j));
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
	public void setPlayer(){
		player = 1;
	}
	public boolean isAI(){
		return AIGame;
	}
	public int getAITurn(){
		return AI.getNextAction(boardState);
	}
	public void toggleAI(boolean AItoggle){
		if(!gameRunning){
			AIGame = AItoggle;
		}
	}
	public void toggleGameState(boolean running){
		if(running){
			winningPieces.clear();
		}
		gameRunning = running;
	}
	
	public void resetGame(){
		gameRunning = false;
		for (int i = 0; i < rowSize; i++){
			for(int j = 0; j < colSize; j++){
				boardState[i][j] = 0;
			}
		}
		numPieces = 0;
		player = 0;
	}
	public boolean isRunning(){
		return gameRunning;
	}
	public void setPlayerNum(int players){
		numPlayers = players;
	}
	public void setAI(int difficulty){
		AI.setDifficulty(difficulty);
	}
	public ArrayList<Coordinates> getWinningPieces(){
		return winningPieces;
	}
	public void printDiff(){
		System.out.printf("%d\n", AI.getDifficulty());
	}
}
