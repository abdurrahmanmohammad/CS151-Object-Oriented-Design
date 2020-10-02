package ai;

import game.Game;

/**
 * Interface to implement an AI for Tic Tac Toe
 * 
 * @author abdurrahman
 *
 */
public interface AI {
	/** Method for the AI to make a move in a game */
	public Game makeMove(Game game);

	// ********** Static methods **********
	/**
	 * Method to check if the game is over
	 * 
	 * @param board
	 * @return 'C' = continue game, 'X' = X won, 'O' = O won, 'D' = draw game
	 * @precondition none
	 * @postcondition none
	 */
	public static char checkGame(Game game) {
		if (checkRows(game) != 'C')
			return checkRows(game); /** If someone wins by rows, return piece */
		if (checkColumns(game) != 'C')
			return checkColumns(game); /** If someone wins by columns, return piece */
		if (checkDiagonals(game) != 'C')
			return checkDiagonals(game); /** If someone wins by diagonals, return piece */
		if (checkDraw(game) == 'D')
			return 'D'; /** If the game is a draw, return 'D' */
		return 'C'; /** If no one wins and no draw, return 'C' to indicate to continue */

	}

	/**
	 * Check if the game is a draw (no spots with ' ')
	 * 
	 * @param game is the game to check
	 * @return 'D' = game is a draw, 'C' = game is not a draw
	 * @precondition none
	 * @postcondition none
	 */
	private static char checkDraw(Game game) { /** Check if there is an ' ' to signify that the game continues */
		for (int i = 0; i < 3; i++) { /** Traverse rows */
			for (int j = 0; j < 3; j++) { /** Traverse columns */
				if (game.readMove(i, j) == ' ')
					return 'C'; /** If any spot has ' ', game is not a draw */
			}
		}
		return 'D'; /** If no ' 's in board, the game is a draw since all spots are filled */
	}

	/**
	 * Check to see if anyone has 3 in a row and return the winner. Otherwise return
	 * 'C' to indicate that no one has won with 3 in a row. Not dependent on an
	 * instance.
	 * 
	 * @param game is the game to check
	 * @return 'C' = no win based on rows, 'X' = X won, 'O' = O won
	 * @precondition none
	 * @postcondition none
	 */
	private static char checkRows(Game game) { /** Check to see if anyone has 3 in a row */
		for (int i = 0; i < 3; i++) { /** Check the rows */
			if (game.readMove(i, 0) == game.readMove(i, 1) && game.readMove(i, 1) == game.readMove(i, 2)) {
				if (game.readMove(i, 0) == 'X')
					return 'X'; /** Player X won */
				if (game.readMove(i, 0) == 'O')
					return 'O'; /** Player O won */
			}
		}
		return 'C'; /** If no 3 consecutive rows are equal, no win by row */
	}

	/**
	 * Check to see if anyone has 3 in a column and return the winner. Otherwise
	 * return 'C' to indicate that no one has won with 3 in a column.
	 * 
	 * @param game is the game to check
	 * @return 'C' = no win based on columns, 'X' = X won, 'O' = O won
	 * @precondition none
	 * @postcondition none
	 */
	private static char checkColumns(Game game) { /** Check to see if anyone has 3 in a column */
		for (int i = 0; i < 3; i++) { /** Check the columns */
			if (game.readMove(0, i) == game.readMove(1, i) && game.readMove(1, i) == game.readMove(2, i)) {
				if (game.readMove(0, i) == 'X')
					return 'X'; /** Player X won */
				if (game.readMove(0, i) == 'O')
					return 'O'; /** Player O won */
			}
		}
		return 'C'; /** If no 3 consecutive columns are equal, no win by column */
	}

	/**
	 * Check to see if anyone has 3 in a diagonal and return the winner. Otherwise
	 * return 'C' to indicate that no one has won with 3 in a diagonal.
	 * 
	 * @param game is the game to check
	 * @return 'C' = no win based on diagonals, 'X' = X won, 'O' = O won
	 * @precondition none
	 * @postcondition none
	 */
	private static char checkDiagonals(Game game) { /** Check to see if anyone has 3 in a diagonal */
		if (game.readMove(0, 0) == game.readMove(1, 1)
				&& game.readMove(1, 1) == game.readMove(2, 2)) { /** Check diagonal 1 */
			if (game.readMove(0, 0) == 'X')
				return 'X'; /** Player X won */
			if (game.readMove(0, 0) == 'O')
				return 'O'; /** Player O won */
		}
		if (game.readMove(0, 2) == game.readMove(1, 1)
				&& game.readMove(1, 1) == game.readMove(2, 0)) { /** Check diagonal 2 */
			if (game.readMove(0, 2) == 'X')
				return 'X'; /** Player X won */
			if (game.readMove(0, 2) == 'O')
				return 'O'; /** Player O won */
		}
		return 'C'; /** If no 3 consecutive diagonal entries are equal, no win by diagonal */
	}
}
