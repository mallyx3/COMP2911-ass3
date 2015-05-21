

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Player {
	private int difficulty;
	boolean isAI = false;
	private static int playerNum = 0;
	private int player;
	private String colour;
	private String name;
	private final String DEFAULT_PLAYER_ONE_COLOUR = "Red";
	private final String DEFAULT_PLAYER_TWO_COLOUR = "Yellow";
	private final int DIFFICULTY_EASY = 0;
	private final int DIFFICULTY_MEDIUM = 1;
	private final int DIFFICULTY_HARD = 2;

	//Create AI player with default easy difficulty
	public Player(){
		playerNum++;
		this.name = "AI";
		this.player = playerNum;
		this.isAI = true;
		this.difficulty = DIFFICULTY_EASY;

		if (player == 1) {
			colour = DEFAULT_PLAYER_ONE_COLOUR;
		} else {
			colour = DEFAULT_PLAYER_TWO_COLOUR;
		}
	}

	//Create AI player with give difficulty setting
	public Player(int difficulty){
		playerNum++;
		this.name = "AI";
		this.player = playerNum;
		this.isAI = true;
		this.difficulty = difficulty;

		if (player == 1) {
			colour = DEFAULT_PLAYER_ONE_COLOUR;
		} else {
			colour = DEFAULT_PLAYER_TWO_COLOUR;
		}
	}

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

	public int getNextAction( ){
		//Prompt player for move
		if (!this.isAI) {
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
		//Return easy AI move
		} else if (difficulty == DIFFICULTY_EASY) {
			Random rand = new Random();
			return rand.nextInt(6);
		//Return medium AI move
		} else if (difficulty == DIFFICULTY_MEDIUM) {
			//todo need to add code for more complex decisions
			return 1;
		//Return hard AI move
		} else {
			//todo need to add code for more complex decisions
			return 2;
		}
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