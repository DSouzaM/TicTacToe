import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;

public class Game implements org.newdawn.slick.Game {
	Board board;
	Player p1, p2;
	Random rand;
	ArrayList<Color> colors = new ArrayList<Color>();
	List<Board> smallBoards;

	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(new Game());
			container.setDisplayMode(1000, 700, false);
			container.setShowFPS(false);
			container.setVSync(true);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}

		/*
		 * byte turn = 0; int selection; Player cPlayer = p1; while
		 * (board.getWinner() == 0 && !board.isFull()) { if (turn%2 == 0) { //
		 * 0, 2, 4, etc is P1. 1, 3, 5, etc is P2. cPlayer = p1; } else {
		 * cPlayer = p2; } System.out .println(cPlayer.getName() +
		 * ", please enter a position."); selection = cPlayer.getMove(); while
		 * (selection < 0 || selection > 8 || !board.isEmpty(selection)) {
		 * System.out.println("Invalid input. Try again."); selection =
		 * cPlayer.getMove(); } board.setCell(selection, (turn%2)+1);
		 * printBoard(board); turn++; } int winner = board.getWinner(); if
		 * (winner == 0) {
		 * System.out.println("Draw! Do both of you win... or neither?"); } else
		 * { if (winner == 1) { System.out.println("Congratulations " +
		 * p1.getName() + ", you win!"); } else if (winner == 2){
		 * System.out.println("Congratulations " + p2.getName() + ", you win!");
		 * } }
		 */

	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		colors.add(new Color(218, 226, 230));
		colors.add(new Color(173, 0, 20));
		colors.add(new Color(0, 32, 173));

		board = new Board();
		p1 = new Player("Player 1");
		p2 = new Player("Player 2");
		smallBoards = new ArrayList<Board>(9);
		for (int i = 0; i < 9; i++) {
			smallBoards.add(new Board());
		}
		gc.getGraphics().setBackground(colors.get(0));

	}

	static void printBoard(Board b) { // temporary until GUI implemented
		System.out.print(b.getCell(0) + " | " + b.getCell(1) + " | "
				+ b.getCell(2) + "\n" + b.getCell(3) + " | " + b.getCell(4)
				+ " | " + b.getCell(5) + "\n" + b.getCell(6) + " | "
				+ b.getCell(7) + " | " + b.getCell(8) + "\n\n");
	}

	@Override
	public boolean closeRequested() {
		return true;
	}

	@Override
	public String getTitle() {
		return "Ultimate TicTacToe";
	}

	@Override
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		g.setColor(Color.black);
		g.drawLine(50, 250, 650, 250);
		g.drawLine(50, 450, 650, 450);
		g.drawLine(250, 50, 250, 650);
		g.drawLine(450, 50, 450, 650);
		int i = 0;
		for (int y = 50; y <= 450; y += 200) {
			for (int x = 50; x <= 450; x += 200) {
				Board currentBoard = smallBoards.get(i);
				if (currentBoard.getWinner() == 0) {
					g.setColor(Color.gray);
					g.drawLine(x + 25, y + 75, x + 175, y + 75);
					g.drawLine(x + 25, y + 125, x + 175, y + 125);
					g.drawLine(x + 75, y + 25, x + 75, y + 175);
					g.drawLine(x+125, y+25, x+125, y+175);
				} else if (currentBoard.getWinner() == 1) {
					g.setColor(colors.get(1));
					g.drawLine(x+25, y+25, x+175, y+175);
					g.drawLine(x+25,y+175,x+175,y+25);
				} else if (currentBoard.getWinner() == 2) {
					g.setColor(colors.get(2));
					g.drawOval(x+25, y+25, 150, 150);
				}
				i++;
			}
		}

	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		smallBoards.get(1).setCell(0, 2);
		smallBoards.get(1).setCell(1, 2);
		smallBoards.get(1).setCell(2, 2);
	}

	private class MouseInputListener implements MouseListener {

		@Override
		public void inputEnded() {
			// TODO Auto-generated method stub

		}

		@Override
		public void inputStarted() {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean isAcceptingInput() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setInput(Input arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(int btn, int mx, int my) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(int arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseWheelMoved(int arg0) {
			// TODO Auto-generated method stub

		}

	}
}
