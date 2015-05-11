
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Player {
	private static int playerNum = 0;
	private int player;
	private String colour;
	private String name;
	private final String DEFAULT_PLAYER_ONE_COLOUR = "Red";
	private final String DEFAULT_PLAYER_TWO_COLOUR = "Yellow";

	public Player(String name){
		playerNum++;
		this.name = name;
		this.player = playerNum;

		if (player == 1) {
			colour = DEFAULT_PLAYER_ONE_COLOUR;
		} else {
			colour = DEFAULT_PLAYER_TWO_COLOUR;
		}
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

	public int getPlayerNum() { return playerNum; }

	public int getPlayer(){
		return player;
	}

	public String getColour(){
		return colour;
	}

	public String getName() { return name; }

	public void setPlayerNum(int newPlayerNum) { playerNum = newPlayerNum; }

	public void setPlayer(int player) { this.player = player; }

	public void setColour(String colour) { this.colour = colour; }

	public void setName(String name) { this.name = name; }

}
