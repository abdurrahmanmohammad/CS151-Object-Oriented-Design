package ai;

import java.util.Comparator;
import java.util.Random;

import game.Game;

public class AIEasy implements AI {
	/** The AI's playing piece ('X or 'O') */
	private final char piece;

	/**
	 * Constructs a Tic Tac Toe AI for Tic Tac Toe with easy difficulty
	 * 
	 * @param piece is the AI's playing piece ('X or 'O')
	 * @precondition piece should be either 'X' or 'O'
	 * @postcondition none
	 */
	public AIEasy(char piece) {
		assert piece == 'X' || piece == 'O' : "Invalid piece for AI"; /** Assert invalid piece */
		this.piece = piece;
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
		Random rand = new Random(); /** Make a random object to get random integers */
		int row = rand.nextInt(3); /** Generate a random row */
		int col = rand.nextInt(3); /** Generate a random columns */
		while (game.readMove(row, col) != ' ') { /** Choose a valid spot on the game board (spot has to equal ' ') */
			/** While the game continues and game[row][col] is already taken */
			row = rand.nextInt(3); /** Generate a new random row */
			col = rand.nextInt(3); /** Generate a new random columns */
		}
		game.saveMove(piece, row, col); /** Make the move */
		return game; /** Return the updated game */
	}

	/**
	 * Getter for the AI's playing piece
	 * 
	 * @return the piece
	 * @precondition none
	 * @postcondition none
	 */
	public char getPiece() {
		return piece;
	}

	/**
	 * Compare multiple AI by playing piece
	 * 
	 * @return a comparator to compare AIEasy by playing piece
	 */
	public static Comparator<AIEasy> comparatorByPiece() {
		return new Comparator<AIEasy>() {
			public int compare(AIEasy AIEasy1, AIEasy AIEasy2) { /** Make object of anonymous class */
				return Character.compare(AIEasy1.piece, AIEasy2.piece); /** Compare by game piece */
			}
		};
	}
}
