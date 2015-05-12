
/* import java.io.BufferedReader;
 * import java.io.IOException;
 * import java.io.InputStreamReader;
 */
public class Main {

	public static void main(String[] args) {
		int turnNum = 0;
		int nextMove;
		Player currPlayer;
		boolean gameComplete = false;
		//boolean multiplayer;  will be used to create AI games later
		Board gameState = new Board(); // use standard board size
		Player playerOne = new Player("John");
		Player playerTwo = new Player();
		turnNum++;
		while(!gameComplete){
			if(turnNum % 2 == 1){
				currPlayer = playerOne;
				nextMove = playerOne.getNextAction();
			} else { // Will contain if statement to use either AI or human player function later
				currPlayer = playerTwo;
				nextMove = playerTwo.getNextAction();
			}
			if(gameState.addPiece(nextMove, currPlayer.getPlayer())){ //Check for victory then go to next turn
				if(gameState.hasWon(currPlayer.getPlayer())){
					gameComplete = true;
					System.out.printf("%s has won!\n", currPlayer.getName());
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
