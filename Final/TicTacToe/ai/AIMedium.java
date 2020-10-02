package ai;

import java.util.Comparator;

import game.Game;

public class AIMedium extends AIEasy {
	// ********** Instance Variables **********
	/** Keeps track of the opponent's piece. Piece is final */
	private final char opponentPiece;
	/** Keeps track if move has been made */
	private boolean isMove;

	// ********** Constructor **********
	/**
	 * Constructs a Tic Tac Toe AI for Tic Tac Toe with medium difficulty
	 * 
	 * @param piece is the AI's piece
	 * @precondition piece == 'X' || piece == 'O'
	 */
	public AIMedium(char piece) {
		super(piece); /** Parent asserts invalid pieces */
		opponentPiece = (super.getPiece() == 'X') ? 'O' : 'X';/** Get the opponent's piece */
	}

	/**
	 * This method makes the AI select its move on the game board
	 * 
	 * @param game is the game for the AI to play
	 * @return the game with the AI's move on it
	 * @precondition the game should not have ended (checkGame(game) == 'C' to play)
	 * @postcondition none
	 */
	public Game makeMove(Game game) {
		assert AI.checkGame(game) == 'C' : "AI can't play. Game has already ended"; /** If the game has already ended */
		isMove = false; /** Set isMove to false to make current move */
		/** Try to win */
		analyzeRows(game, super.getPiece());
		if (isMove == true)
			return game;
		analyzeCols(game, super.getPiece());
		if (isMove == true)
			return game;
		analyzeDiagonal1(game, super.getPiece());
		if (isMove == true)
			return game;
		analyzeDiagonal2(game, super.getPiece());
		if (isMove == true)
			return game;

		/** Try to prevent loss */
		analyzeRows(game, opponentPiece);
		if (isMove == true)
			return game;
		analyzeCols(game, opponentPiece);
		if (isMove == true)
			return game;
		analyzeDiagonal1(game, opponentPiece);
		if (isMove == true)
			return game;
		analyzeDiagonal2(game, opponentPiece);
		if (isMove == true)
			return game;

		/** Else make a random move */
		return super.makeMove(game);
	}

	/**
	 * Analyzes the rows and attains a win by rows or prevents a loss by rows based
	 * on the piece passed in.
	 * 
	 * @param game  is the game to edit
	 * @param piece are the pieces to analyze (O to prevent loss, X to attain win)
	 * @return the game storage with the AI's piece with isMove = true or the same
	 *         board with isMove = false
	 */
	private Game analyzeRows(Game game, char piece) {
		/** Check the rows */
		for (int i = 0; i < 3; i++) { /** If 2 adjacent row moves belong to AI and the 3rd is not taken: X|X|N */
			if (game.readMove(i, 0) == piece && game.readMove(i, 1) == piece && game.readMove(i, 2) == ' ') {
				game.saveMove(super.getPiece(), i, 2); /** Take that last space */
				isMove = true;
				return game;
			}
		}
		for (int i = 0; i < 3; i++) { /** If 2 adjacent row moves belong to AI and the 3rd is not taken: N|X|X */
			if (game.readMove(i, 1) == piece && game.readMove(i, 2) == piece && game.readMove(i, 0) == ' ') {
				game.saveMove(super.getPiece(), i, 0); /** Take that last space */
				isMove = true;
				return game;
			}
		}
		for (int i = 0; i < 3; i++) { /** If 2 corner row moves belong to AI and the middle is not taken: X|N|X */
			if (game.readMove(i, 0) == piece && game.readMove(i, 2) == piece && game.readMove(i, 1) == ' ') {
				game.saveMove(super.getPiece(), i, 1);/** Take that last space */
				isMove = true;
				return game;
			}
		}
		isMove = false;
		return game;
	}

	/**
	 * Analyzes the columns and attains a win by rows or prevents a loss by columns
	 * based on the piece passed in.
	 * 
	 * @param game  is the game to edit
	 * @param piece are the pieces to analyze (O to prevent loss, X to attain win)
	 * @return the game storage with the AI's piece with isMove = true or the same
	 *         board with isMove = false
	 */
	private Game analyzeCols(Game game, char piece) {
		/** Check the columns */
		for (int i = 0; i < 3; i++) { /** If 2 adjacent column moves belong to AI and the 3rd is not taken: X/X/N */
			if (game.readMove(0, i) == piece && game.readMove(1, i) == piece && game.readMove(2, i) == ' ') {
				game.saveMove(super.getPiece(), 2, i); /** Take that last space */
				isMove = true;
				return game;
			}
		}
		for (int i = 0; i < 3; i++) { /** If 2 adjacent column moves belong to AI and the 3rd is not taken: N/X/X */
			if (game.readMove(1, i) == piece && game.readMove(2, i) == piece && game.readMove(0, i) == ' ') {
				game.saveMove(super.getPiece(), 0, i); /** Take that last space */
				isMove = true;
				return game;
			}
		}
		for (int i = 0; i < 3; i++) { /** If 2 column corner moves belong to AI and the middle is not taken: X/N/X */
			if (game.readMove(0, i) == piece && game.readMove(2, i) == piece && game.readMove(1, i) == ' ') {
				game.saveMove(super.getPiece(), 1, i);/** Take that last space */
				isMove = true;
				return game;
			}
		}
		isMove = false;
		return game;
	}

	/**
	 * Analyzes the diagonal from top left to bottom right and attains a win by this
	 * diagonal or prevents a loss by this diagonal based on the piece passed in.
	 * 
	 * @param game  is the game to edit
	 * @param piece are the pieces to analyze (O to prevent loss, X to attain win)
	 * @return the game storage with the AI's piece with isMove = true or the same
	 *         board with isMove = false
	 */
	private Game analyzeDiagonal1(Game game, char piece) {
		/** Diagonal top left to bottom right */
		if (game.readMove(0, 0) == game.readMove(1, 1) && game.readMove(1, 1) == piece
				&& game.readMove(2, 2) == ' ') { /** X/X/N */
			game.saveMove(super.getPiece(), 2, 2); /** Take that last space */
			isMove = true;
			return game;
		}
		if (game.readMove(0, 0) == game.readMove(2, 2) && game.readMove(2, 2) == piece
				&& game.readMove(1, 1) == ' ') { /** X/N/X */
			game.saveMove(super.getPiece(), 1, 1); /** Take that last space */
			isMove = true;
			return game;
		}
		if (game.readMove(1, 1) == game.readMove(2, 2) && game.readMove(2, 2) == piece
				&& game.readMove(0, 0) == ' ') { /** N/X/X */
			game.saveMove(super.getPiece(), 0, 0); /** Take that last space */
			isMove = true;
			return game;
		}
		isMove = false;
		return game;
	}

	/**
	 * Analyzes the diagonal from bottom left to top right and attains a win by this
	 * diagonal or prevents a loss by this diagonal based on the piece passed in.
	 * 
	 * @param game  is the game to edit
	 * @param piece are the pieces to analyze (O to prevent loss, X to attain win)
	 * @return the game storage with the AI's piece with isMove = true or the same
	 *         board with isMove = false
	 */
	private Game analyzeDiagonal2(Game game, char piece) {
		/** Diagonal bottom left to top tight */
		if (game.readMove(0, 2) == game.readMove(1, 1) && game.readMove(1, 1) == piece
				&& game.readMove(2, 0) == ' ') { /** N/X/X */
			game.saveMove(super.getPiece(), 2, 0); /** Take that last space */
			isMove = true;
			return game;
		}
		if (game.readMove(2, 0) == game.readMove(1, 1) && game.readMove(1, 1) == piece
				&& game.readMove(0, 2) == ' ') { /** X/X/N */
			game.saveMove(super.getPiece(), 0, 2); /** Take that last space */
			isMove = true;
			return game;
		}
		if (game.readMove(2, 0) == game.readMove(0, 2) && game.readMove(0, 2) == piece
				&& game.readMove(1, 1) == ' ') { /** X/N/X */
			game.saveMove(super.getPiece(), 1, 1); /** Take that last space */
			isMove = true;
			return game;
		}
		isMove = false;
		return game;
	}

	/**
	 * Compare multiple AI by opponent's piece
	 * 
	 * @return a comparator for oppponent's piece based on char value
	 */
	public static Comparator<AIMedium> comparatorByOpponentPiece() { /** Compare by AI's opponent's piece */
		return new Comparator<AIMedium>() {
			public int compare(AIMedium AIMedium1, AIMedium AIMedium2) { /** Make object of anonymous class */
				return Character.compare(AIMedium1.opponentPiece, AIMedium2.opponentPiece); /** Compare by game piece */
			}
		};
	}
}
