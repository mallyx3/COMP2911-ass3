

public class Board {
	private int[][] boardState;
	private int colSize;
	private int rowSize;
	private static int numPieces;
	
	public Board(int rowSize, int colSize){
		this.boardState = new int[rowSize][colSize];
		this.colSize = colSize;
		this.rowSize = rowSize;
		this.numPieces = 0;
	}
	//need to check if numPieces is < total num of pieces (42 for 7x6 board)
	public Boolean addPiece(int column, int player){
		if(column < 0 || column >= colSize){ // shouldn't be necessary in final product but just in case
			return false;
		}
		for(int i = rowSize-1; i >= 0;i--){
			if(boardState[i][column] == 0){
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
		for(i = 0 ; i < rowSize ; i++ ){
			winCount = 0;
			for(j = 0; j < rowSize-1; j++){
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
		for(i = 0 ; i < rowSize ; i++ ){
			winCount = 0;
			for(j = 0; j < colSize ; j++){
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
		return false;
	}
	public void print(){
		int i = 0;
		int j = 0;
		System.out.println("Current Board:");
		for(i = 0 ; i < rowSize ; i++ ){
			System.out.print("|");
			for(j = 0; j < colSize ; j++){
				System.out.print(boardState[i][j] + "|");
			}
		System.out.println("");
		}
	}
}
