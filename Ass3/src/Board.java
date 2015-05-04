

public class Board {
	private int[][] boardState;
	private int colSize;
	private int rowSize;
	
	public Board(int colSize, int rowSize){
		this.boardState = new int[colSize][rowSize];
		this.colSize = colSize;
		this.rowSize = rowSize;
	}
	public Boolean addPiece(int column, int player){
		if(column < 0 || column >= rowSize){ // shouldn't be necessary in final product but just in case
			return false;
		}
		for(int i = 0; i < rowSize;i++){
			if(boardState[column][i] == 0){
				boardState[column][i] = player;
				return true;
			}
		}
		return false;
	}

	public boolean hasWon(int player){
		int i;
		int j;
		int winCount;
		for(i = 0 ; i < rowSize ; i++ ){
			winCount = 0;
			for(j = 0; j < colSize; j++){
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
		for(i = 0 ; i < colSize ; i++ ){
			winCount = 0;
			for(j = 0; j < rowSize ; j++){
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
}