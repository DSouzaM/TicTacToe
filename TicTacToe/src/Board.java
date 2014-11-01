import java.util.Random;

public class Board {
	private int winner;
	private int[] cells;

	public Board() {
		winner = 0;
		cells = new int[9];
	}

	/*
	 * public static void main(String[] args) { //testing code Board b = new
	 * Board(); System.out.println(b.getWinner()); Random r = new Random(); int
	 * nextCell; int player = 1; while (b.getWinner() == 0 && !b.isFull()) {
	 * nextCell = r.nextInt(9); if (b.isEmpty(nextCell)) { b.setCell(nextCell,
	 * player); player = (player == 1) ? 2 : 1; for (int i = 0; i < 9; i++) {
	 * System.out.print(b.getCell(i) + " "); if ((i + 1) % 3 == 0)
	 * System.out.println(); } System.out.println(); } }
	 * System.out.println(b.getWinner()); }
	 */

	int getWinner() {
		if (winner == 0) {
			for (int first = 0; first + 2 <= 8; first += 3) { // horizontal
				if (cells[first] != 0 && cells[first] == cells[first + 1]
						&& cells[first] == cells[first + 2]) {
					winner = cells[first];
					break;
				}
			}
			for (int first = 0; first < 3; first++) { // vertical
				if (cells[first] != 0 && cells[first] == cells[first + 3]
						&& cells[first] == cells[first + 6]) {
					winner = cells[first];
					break;
				}
			}
			if (cells[0] != 0 && cells[0] == cells[4] && cells[0] == cells[8]) { // diagonal
				winner = cells[0];
			} else if (cells[2] != 0 && cells[2] == cells[4]
					&& cells[2] == cells[6]) {
				winner = cells[2];
			}
		}

		return winner;
	}

	int getCell(int cell) {
		return cells[cell];
	}

	void setCell(int cell, int val) {
		cells[cell] = val;
	}

	boolean isEmpty(int cell) {
		if (cells[cell] == 0) {
			return true;
		} else {
			return false;
		}
	}

	boolean isFull() {
		for (int num : cells) {
			if (num == 0) {
				return false;
			}
		}
		return true;
	}

}
