import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


	/**
	 * @param args
	 *
	 */
	
	/*
	 
	------------------------------------------------
	No.:			1 <---
	Task:			Generate blank board
	Difficulty:		1
	Time:			1
	Dependent on:	9
	Comment:		Create a 2d array, null, p1, p2
	Assigned to:	Tom
	Note: 			In Main
	------------------------------------------------
	
	*/
public class Main{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
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
	      
	     //Now the board array is created.
		Integer[][] board = new Integer[x][y];
		
		
	}
	
	public Boolean addPiece(Integer row, Integer x, Integer y, Integer[][] board){
		Boolean flag = new Boolean(false);
		//flag will be swapped once piece is placed.
		//point will be null if no piece, 1 if p1, 2 if p2
		int xC = 0;
		int yC = 0;
		
		while (!x.equals(row)){
			while(y > 0){
				
			}
		}
		
		
		
		return flag;
	}
}




