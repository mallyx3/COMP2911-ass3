
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Player {
	private int player;
	//private String Colour;
	//private String Name;

	public Player(int player){
		this.player = player;
	}
		//
	public int getNextAction(){
		System.out.print("What is your next action?");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 
		String turnInput = null;
		 
		//  read the username from the command-line; need to use try/catch with the
		//  readLine() method
		try {
			turnInput = br.readLine();
		} catch (IOException ioe) {
		    System.out.println("IO error trying to get size!");
		    System.exit(1);
		}
		      
		return Integer.parseInt(turnInput);	      
	}
	public int getPlayer(){
		return player;
	}
}
