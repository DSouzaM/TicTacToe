public class Game {

	public static void main(String[] args) {
		Board board = new Board();
		Player p1 = new Player("Player 1");
		Player p2 = new Player("Player 2");
		printBoard(board);
		byte turn = 0;
		int selection;
		Player cPlayer = p1;
		while (board.getWinner() == 0 && !board.isFull()) {
			if (turn%2 == 0) { // 0, 2, 4, etc is P1. 1, 3, 5, etc is P2.
				cPlayer = p1;
			} else {
				cPlayer = p2;				
			}
			System.out
					.println(cPlayer.getName() + ", please enter a position.");
			selection = cPlayer.getMove();
			while (selection < 0 || selection > 8 || !board.isEmpty(selection)) {
				System.out.println("Invalid input. Try again.");
				selection = cPlayer.getMove();
			}
			board.setCell(selection, (turn%2)+1);
			printBoard(board);
			turn++;
		}
		int winner = board.getWinner();
		if (winner == 0) {
			System.out.println("Draw! Do both of you win... or neither?");
		} else {
			if (winner == 1) {
				System.out.println("Congratulations " + p1.getName() + ", you win!");
			} else if (winner == 2){
				System.out.println("Congratulations " + p2.getName() + ", you win!");
			}
		}
		
	}

	static void printBoard(Board b) { // temporary until GUI implemented
		System.out.print(b.getCell(0) + " | " + b.getCell(1) + " | "
				+ b.getCell(2) + "\n" + b.getCell(3) + " | " + b.getCell(4)
				+ " | " + b.getCell(5) + "\n" + b.getCell(6) + " | "
				+ b.getCell(7) + " | " + b.getCell(8) + "\n\n");
	}
}
