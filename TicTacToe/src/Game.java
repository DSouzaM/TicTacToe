import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import net.java.games.input.Mouse;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;

public class Game implements org.newdawn.slick.Game {
	Board board;
	Player p1, p2, cPlayer;
	Random rand;
	ArrayList<Color> colors = new ArrayList<Color>();
	List<Board> smallBoards;
	String message;
	int turn, nextCell;
	int mx, my;

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
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		colors.add(new Color(218, 226, 230));
		colors.add(new Color(173, 0, 20));
		colors.add(new Color(0, 32, 173));
		colors.add(new Color(10, 242, 64));
		p1 = new Player("Player 1");
		p2 = new Player("Player 2");
		gc.getGraphics().setBackground(colors.get(0));
		mx = 0;
		my = 0;
		newGame();

	}

	public void newGame() {
		nextCell = 9;
		message = " ";
		board = new Board();
		turn = 1;
		cPlayer = p1;
		smallBoards = new ArrayList<Board>(9);
		for (int i = 0; i < 9; i++) {
			smallBoards.add(new Board());
		}
		nextCell = 9;
		message = " ";
		board = new Board();
		turn = 1;
		cPlayer = p1;
		smallBoards = new ArrayList<Board>(9);
		for (int i = 0; i < 9; i++) {
			smallBoards.add(new Board());
		}
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
		g.setColor(colors.get(turn));
		g.drawString(message, 700, 50);
		int bigI = 0;
		g.setColor(colors.get(3));
		if (nextCell == 9) {
			g.drawRect(50, 50, 600, 600);
		} else {
			g.drawRect(60 + (nextCell % 3) * 200, 60 + (nextCell / 3) * 200,
					180, 180);
		}
		g.setColor(Color.red);
		g.fillRect(700, 620, 80, 30);
		g.setColor(Color.white);
		g.drawString("RESET", 715, 625);

		for (int y = 50; y <= 450; y += 200) {
			for (int x = 50; x <= 450; x += 200) {
				Board currentBoard = smallBoards.get(bigI);
				if (currentBoard.getWinner() == 0) {
					g.setColor(Color.gray);
					g.drawLine(x + 25, y + 75, x + 175, y + 75);
					g.drawLine(x + 25, y + 125, x + 175, y + 125);
					g.drawLine(x + 75, y + 25, x + 75, y + 175);
					g.drawLine(x + 125, y + 25, x + 125, y + 175);
					int i = 0;
					for (int sy = 25; sy <= 125; sy += 50) {
						for (int sx = 25; sx <= 125; sx += 50) {
							if (currentBoard.getCell(i) == 1) {
								g.setColor(colors.get(1));
								g.drawLine(x + sx + 10, y + sy + 10, x + sx
										+ 40, y + sy + 40);
								g.drawLine(x + sx + 10, y + sy + 40, x + sx
										+ 40, y + sy + 10);
							} else if (currentBoard.getCell(i) == 2) {
								g.setColor(colors.get(2));
								g.drawOval(x + sx + 10, y + sy + 10, 30, 30);
							}
							i++;
						}
					}

				} else if (currentBoard.getWinner() == 1) {
					g.setColor(colors.get(1));
					g.drawLine(x + 25, y + 25, x + 175, y + 175);
					g.drawLine(x + 25, y + 175, x + 175, y + 25);
				} else if (currentBoard.getWinner() == 2) {
					g.setColor(colors.get(2));
					g.drawOval(x + 25, y + 25, 150, 150);
				}
				bigI++;
			}
		}

	}

	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		Input input = gc.getInput();
		mx = input.getMouseX();
		my = input.getMouseY();
		if (mx > 700 && mx < 780 && my > 620 && my < 650 && input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {
			newGame();
		}
		else if (board.getWinner() == 0) {
			cPlayer = (turn == 1) ? p1 : p2;
			message = cPlayer.getName() + "'s turn.";
			if (input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {

				if (mx > 50 && mx < 650 && my > 50 && my < 650) { // in grid

					int bigI = (mx - 50) / 200 + 3 * ((my - 50) / 200); // bigarrayindex
					if (bigI == nextCell || nextCell == 9) {
						int xOffset = 75 + (bigI % 3) * 200;
						int yOffset = 75 + (bigI / 3) * 200;
						if (mx > xOffset && mx < xOffset + 150 && my > yOffset
								&& my < yOffset + 150) {
							int i = (mx - xOffset) / 50 + 3
									* ((my - yOffset) / 50);
							Board currentBoard = smallBoards.get(bigI);
							if (currentBoard.getWinner() == 0
									&& currentBoard.isEmpty(i)) {
								currentBoard.setCell(i, turn);
								turn = turn % 2 + 1;
								nextCell = i;
								if (smallBoards.get(nextCell).getWinner() != 0) {
									nextCell = 9;
								}
								if (currentBoard.getWinner() != 0) {
									board.setCell(bigI,
											currentBoard.getWinner());
								}
							}

						}
					}

				}

			}
		} else if (board.getWinner() == 1) {
			turn = 1; // necessary for colours
			message = p1.getName() + " wins!\nClick restart to play again.";
		} else if (board.getWinner() == 2) {
			turn = 2;
			message = p2.getName() + " wins!\nClick restart to play again.";
		}

	}

}
