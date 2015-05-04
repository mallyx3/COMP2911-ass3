
/* import java.io.BufferedReader;
 * import java.io.IOException;
 * import java.io.InputStreamReader;
 */
public class Main {

	public static void main(String[] args) {
		int turnNum = 0;
		int nextMove;
		int currPlayer;
		boolean gameComplete = false;
		//boolean multiplayer;  will be used to create AI games later
		Board gameState = new Board(7, 6); // use standard board size
		Player PlayerOne = new Player(1);
		Player PlayerTwo = new Player(2);
		turnNum++;
		while(!gameComplete){
			if(turnNum % 2 == 1){
				currPlayer = 1;
				nextMove = PlayerOne.getNextAction();
			} else { // Will contain if statement to use either AI or human player function later
				currPlayer = 2;
				nextMove = PlayerTwo.getNextAction(); 
			}
			if(gameState.addPiece(nextMove, currPlayer)){ //Check for victory then go to next turn
				if(gameState.hasWon(currPlayer)){
					gameComplete = true;
					System.out.printf("Player %d has won!\n", currPlayer);
				}
				turnNum++;
			} else {
				System.out.print("Invalid move, not enough space\n");
			}
		}
		/* TODO Auto-generated method stub
		Kept for use when boards can have different sizes
		
		//Ask for the x and y sizes
		//Modified from code found at http://www.java2s.com/Code/Java/File-Input-Output/ReadastringfromconsoleusingaBufferedReader.htm
		String prompt = "How wide do you want the board?";
		System.out.print(prompt);
		 
	      //  open up standard input
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 
	      String xSize = null;
	 
	      //  read the username from the command-line; need to use try/catch with the
	      //  readLine() method
	      try {
	         xSize = br.readLine();
	      } catch (IOException ioe) {
	         System.out.println("IO error trying to get size!");
	         System.exit(1);
	      }
	      
		Integer x = Integer.parseInt(xSize);
		
		prompt = "How tall do you want the board?";
		System.out.print(prompt);
		 
	      //  open up standard input
	      br = new BufferedReader(new InputStreamReader(System.in));
	 
	      xSize = null;
	 
	      //  read the username from the command-line; need to use try/catch with the
	      //  readLine() method
	      try {
	         xSize = br.readLine();
	      } catch (IOException ioe) {
	         System.out.println("IO error trying to get size!");
	         System.exit(1);
	      }
		
		Integer y = Integer.parseInt(xSize);
	    Board gameState = new Board(x, y);  	
	    */
	}
}
