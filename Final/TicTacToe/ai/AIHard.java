package ai;

import game.Game;

public class AIHard extends AIMedium {

	/**
	 * Constructs a Tic Tac Toe AI for Tic Tac Toe with hard difficulty
	 * 
	 * @param piece is the AI's playing piece ('X or 'O')
	 * @precondition piece should be either 'X' or 'O'
	 * @postcondition none
	 */
	public AIHard(char piece) {
		super(piece); /** Piece checked in parent class */
	}

	/**
	 * This method makes the AI select its move on the game board
	 * 
	 * @param game is the game for the AI to play
	 * @return the game with the AI's move on it
	 * @precondition the game should not have ended (checkGame(game) == 'C' to play)
	 * @postcondition returns game with AI's move
	 */
	public Game makeMove(Game game) {
		assert AI.checkGame(game) == 'C' : "AI can't play. Game has already ended"; /** If the game has already ended */
		int count = 0; /** Keeps track of the moves on the game board storage */
		for (int i = 0; i < 3; i++) { /** Traverse rows */
			for (int j = 0; j < 3; j++) { /** Traverse columns */
				if (game.readMove(i, j) != ' ')
					count++;/** If spot is not empty, increase count */
			}
		}
		if (super.getPiece() == 'X') { /** If AI's piece is X */
			return moveX(game, count);
		} else {
			return moveO(game, count);
		}
	}

	/** https://www.wikihow.com/Win-at-Tic-Tac-Toe */
	/**
	 * count == 0 && super.getPiece() == 'X': Play your first X in a corner. Most
	 * experienced tic tac toe players put the first "X" in a corner when they get
	 * to play first. This gives the opponent the most opportunities to make a
	 * mistake. If your opponent responds by putting an O anywhere besides the
	 * center, you can guarantee a win.
	 */
	/**
	 * count == 2 && super.getPiece() == 'X' && game.readMove(1, 1) == 'O': Place
	 * your second X in the opposite corner from your first, so there's a line going
	 * "X O X" diagonally across the board. If they respond with an O in one of the
	 * other corners, you can win! Place your third X in the last empty corner, and
	 * your opponent won't be able to block you from winning with your fourth X.
	 */
	/**
	 * count == 2 && super.getPiece() == 'X' && game.readMove(1, 1) != 'O':Win
	 * automatically if your opponent plays his first O in any square besides the
	 * center. If your opponent puts his first O in any square besides the center,
	 * you can win. Respond by putting your second X in any other corner, with an
	 * empty space in between the two X's
	 */
	/**
	 * Place your third X so you have two possible winning moves. Most of the time,
	 * your opponent will see that you have two X's in a row and block you. (If not,
	 * just win by making a row of three X's.) After this happens, there should be
	 * an empty square that is in line with both your first and your second X, with
	 * no enemy O's blocking that line. Put your third X in this square.
	 */
	/**
	 * Win with your fourth X. After your third X, there are two empty squares that
	 * will win you the game if an X goes into one of them. Since your opponent can
	 * only make one move, he can only block one of those squares. Write your fourth
	 * X into the square he didn't block, and you've won the game!
	 */

	/**
	 * Tries to win or draw the game if the AI goes first
	 * 
	 * @param game  is the game to play
	 * @param count is the number of moves on the game board
	 * @return the game board with AI's move
	 * @precondition piece can be either 'X' or 'O'
	 * @postcondition returns game with AI's move
	 */
	private Game moveX(Game game, int count) {
		assert super.getPiece() != 'X' : "Incorrect piece for AI"; /** Assert invalid piece */
		if (count == 0 || count == 2) { /** First and second moves for AI = X */
			return takeCorner(game); /** Picks corners in desired order */
		} else if (count == 4) { /** Third move for AI = X */
			if (game.readMove(1, 1) == 'O') { /** If opponent picked middle and a corner, take remaining corner */
				if (game.readMove(0, 2) == 'O' || game.readMove(2, 0) == 'O') { /** Take remaining corner */
					return takeCorner(game); /** Picks corners in desired order. Takes last corner. */
				} else { /** If opponent picks middle + edge, prevent loss */
					return super.makeMove(game); /** Parent method will prevent a loss */
				}
			}
		} else if (count == 6 || count == 8) { /** If the opponent did not take the middle spot for his first move */
			return super.makeMove(game); /** Parent method will win or prevent a loss */
		} else {
			return super.makeMove(game); /** If out of cases, do a random move */
		}
		return game; /** Returns the game with AI's move */
	}

	/**
	 * Force a draw if the opponent starts in the corner. If the opponent plays
	 * first and starts with an X in a corner, always put your first O in the
	 * center. Your second O should be placed on an edge, not a corner, unless you
	 * need to block your opponent from getting three in a row. Using this strategy,
	 * every game should be a draw.
	 */
	/**
	 * Force a draw when the opponent starts in the center. When your opponent
	 * starts by putting down an O in the center, place your first X in a corner.
	 * After that, just keep blocking your opponent from scoring and the game will
	 * be a draw. There is essentially no way for you to win from this position,
	 * unless your opponent stops trying to win or stop you from winning!
	 */
	/**
	 * Try to win if the opponent starts at the edge. Put your first O in the
	 * center. If your opponent puts the second O on the opposite edge, making a row
	 * or column that reads X-O-X, put your second O in a corner. Then, if your
	 * opponent puts the third X in the edge that is adjacent to your O, making a
	 * line that reads X-O-X, put your third O in the empty square to block their
	 * row of two O's. From here, you can always win with your fourth 0
	 */

	/**
	 * Tries to win or draw the game if the AI goes second
	 * 
	 * @param game  is the game to play
	 * @param count is the number of moves on the game board
	 * @return the game board with AI's move
	 * @precondition the game should not have ended (checkGame(game) == 'C' to play)
	 * @precondition piece can be either 'X' or 'O'
	 * @postcondition returns game with AI's move
	 */
	private Game moveO(Game game, int count) {
		assert AI.checkGame(game) == 'C' : "AI can't play. Game has already ended"; /** If the game has already ended */
		assert super.getPiece() != 'O' : "Incorrect piece for AI"; /** Assert invalid piece */
		if (count == 1) {
			if (game.readMove(1, 1) == 'X') { /** Opponent starts in the center */
				return takeCorner(game); /** Occupy a corner */
			} else { /** Opponent starts in the corner or edge */
				game.saveMove(super.getPiece(), 1, 1); /** Occupy center */
				return game;
			}
		} else if (count == 3) {
			if ((game.readMove(0, 1) == 'X' && game.readMove(2, 1) == 'X')
					|| (game.readMove(1, 0) == 'X' && game.readMove(1, 2) == 'X')) { /** Edge XOX */
				return takeCorner(game); /** Take a corner */
			} else { /** Block opponent */
				return super.makeMove(game); /** Block opponent */
			}
		} else if (count == 5 || count == 7 || count == 9) {
			return super.makeMove(game); /** Prevent a loss or win */
		} else {
			return super.makeMove(game); /** If out of cases, do a random move */
		}
	}

	/**
	 * Lets the AI select the corners and marks them in the game board storage
	 * 
	 * @param game is the game to play
	 * @return the modified game with AI's move
	 * @precondition the game should not have ended (checkGame(game) == 'C' to play)
	 * @postcondition none
	 */
	private Game takeCorner(Game game) {
		assert AI.checkGame(game) == 'C' : "AI can't play. Game has already ended"; /** If the game has already ended */
		if (game.readMove(0, 0) == ' ') { /** Pick top left corner if not already taken */
			game.saveMove(super.getPiece(), 0, 0);
		} else if (game.readMove(2, 2) == ' ') { /** Pick bottom right corner if not already taken */
			game.saveMove(super.getPiece(), 2, 2);
		} else if (game.readMove(0, 2) == ' ') { /** Pick top right corner if not already taken */
			game.saveMove(super.getPiece(), 0, 2);
		} else if (game.readMove(2, 0) == ' ') { /** Pick bottom left if not already taken */
			game.saveMove(super.getPiece(), 2, 0);
		}
		return game;/** Returns the game with AI's move */
	}
}