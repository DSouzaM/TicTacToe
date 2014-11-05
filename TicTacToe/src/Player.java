import java.util.Scanner;

public class Player {
	static Scanner input = new Scanner(System.in);
	private String name;

	public Player(String str) {
		name = str;
	}

	public Player() {
		name = "Player";
	}

	int getMove() { // for now this is unnecessary for both , but the goal is to make this
					// work past stdin input.
		return input.nextInt();
	}
	String getName(){
		return name;
	}
}
