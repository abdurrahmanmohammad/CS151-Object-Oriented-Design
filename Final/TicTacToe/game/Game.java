package game;

import java.util.Comparator;

/**
 * Stores the Tic Tac Toe game
 * 
 * @author abdurrahman
 *
 */
public class Game {
	// ********** Instance Variable **********
	/** Array to represent and store the Tic Tac Toe game board */
	/** Initialize the board with character ' ' (' ' = unselected space) */
	private char[][] game = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };

	// ********** Constructor **********
	/**
	 * Constructor to construct an abstract game board for game storage
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public Game() {
	}

	// ********** Save and Read Moves **********
	/**
	 * Method to store a move in the Tic Tac Toe game
	 * 
	 * @param move is the letter of the move
	 * @param row  is the row of the move on the board
	 * @param col  is the column of the move on the board
	 * @precondition move == 'X' || move == 'O'
	 * @precondition 0 <= row && row < 3
	 * @precondition 0 <= col && col < 3
	 * @postcondition none
	 */
	public void saveMove(char move, int row, int col) {
		assert move == 'X' || move == 'O' : "Invalid move: invalid char";
		assert 0 <= row && row < 3 : "Invalid move: invalid row";
		assert 0 <= col && col < 3 : "Invalid move: invalid move";
		game[row][col] = move; /** Save the move */
	}

	/**
	 * Read a move on the game board
	 * 
	 * @param row is the row of the move
	 * @param col is the column of the move
	 * @return the move (located at game[row][col])
	 * @precondition 0 <= row && row < 3
	 * @precondition 0 <= col && col < 3
	 * @postcondition none
	 */
	public char readMove(int row, int col) {
		assert 0 <= row && row < 3 : "Invalid move: invalid row";
		assert 0 <= col && col < 3 : "Invalid move: invalid move";
		return game[row][col]; /** return the corresponding move */
	}

	// ********** Comparator **********
	/**
	 * Compare by how filled a board is (compare by fill)
	 * 
	 * @return a Comparator that compares game boards by the number of moves stored
	 * @precondition none
	 * @postcondition none
	 */
	public static Comparator<Game> comparatorByFill() { /** Compare by how filled a board is */
		return new Comparator<Game>() {
			public int compare(Game game1, Game game2) { /** Make object of anonymous class */
				int game1Fill = 0; /** Stores the number of filled spots in game1 */
				int game2Fill = 0; /** Stores the number of filled spots in game2 */
				for (int i = 0; i < 3; i++) { /** Traverse rows */
					for (int j = 0; j < 3; j++) { /** Traverse columns */
						if (game1.game[i][j] != ' ') { /** If the spot is not empty */
							game1Fill++; /** Increment count */
						}
						if (game2.game[i][j] != ' ') { /** If the spot is not empty */
							game2Fill++; /** Increment count */
						}
					}
				}
				return Integer.compare(game1Fill, game2Fill); /** Compare the number of moves in each board */
			}
		};
	}

	/**
	 * Compare by first move
	 * 
	 * @return a Comparator that compares game boards by the number of moves stored
	 * @precondition none
	 * @postcondition none
	 */
	public static Comparator<Game> comparatorByFirst() { /** Compare by first move */
		return new Comparator<Game>() {
			public int compare(Game game1, Game game2) { /** Make object of anonymous class */
				return Character.compare(game1.readMove(0, 0),
						game2.readMove(0, 0)); /** Compare the value of the first move */
			}
		};
	}

	/**
	 * Compare by how many X's are in the board
	 * 
	 * @return a Comparator that compares game boards by the number X's
	 * @precondition none
	 * @postcondition none
	 */
	public static Comparator<Game> comparatorByX() { /** Compare by how filled a board is */
		return new Comparator<Game>() {
			public int compare(Game game1, Game game2) { /** Make object of anonymous class */
				int game1Count = 0; /** Stores the number of filled spots in game1 */
				int game2Count = 0; /** Stores the number of filled spots in game2 */
				for (int i = 0; i < 3; i++) { /** Traverse rows */
					for (int j = 0; j < 3; j++) { /** Traverse columns */
						if (game1.game[i][j] != 'X') { /** If the spot is X */
							game1Count++; /** Increment count */
						}
						if (game2.game[i][j] != 'X') { /** If the spot is X */
							game2Count++; /** Increment count */
						}
					}
				}
				return Integer.compare(game1Count, game2Count); /** Compare the number of moves in each board */
			}
		};
	}

	/**
	 * Compare by how many O's are in the board
	 * 
	 * @return a Comparator that compares game boards by the number O's
	 * @precondition none
	 * @postcondition none
	 */
	public static Comparator<Game> comparatorByO() { /** Compare by how filled a board is */
		return new Comparator<Game>() {
			public int compare(Game game1, Game game2) { /** Make object of anonymous class */
				int game1Count = 0; /** Stores the number of filled spots in game1 */
				int game2Count = 0; /** Stores the number of filled spots in game2 */
				for (int i = 0; i < 3; i++) { /** Traverse rows */
					for (int j = 0; j < 3; j++) { /** Traverse columns */
						if (game1.game[i][j] != 'O') { /** If the spot is O */
							game1Count++; /** Increment count */
						}
						if (game2.game[i][j] != 'O') { /** If the spot is O */
							game2Count++; /** Increment count */
						}
					}
				}
				return Integer.compare(game1Count, game2Count); /** Compare the number of moves in each board */
			}
		};
	}

	/**
	 * Converts a game object to a string
	 * 
	 * @return the game storage as a string
	 */
	public String toString() {
		return getClass().getName() + "\n" + readMove(0, 0) + '|' + readMove(0, 1) + "|" + readMove(0, 2) + "\n- - -\n"
				+ readMove(1, 0) + "|" + readMove(1, 1) + "|" + readMove(1, 2) + "\n- - -\n" + readMove(2, 0) + "|"
				+ readMove(2, 1) + "|" + readMove(2, 2) + "\n";

	}

	/**
	 * Determines if 2 games are equal
	 * 
	 * @return true if equal
	 */
	public boolean equals(Object otherObject) {
		Game other = (Game) otherObject;
		if (otherObject == null) {
			return false; /** This block is not needed, but my console shows null for false */
		}
		int count = 0; /** Counts the number of identical moves */
		for (int i = 0; i < 3; i++) { /** Traverse rows */
			for (int j = 0; j < 3; j++) { /** Traverse columns */
				if (other.readMove(i, j) == readMove(i, j)) {
					count++; /** Increment count */
				}
			}
		}
		return 9 == count; /** If objects are equal, count should be 9. */
	}

	/**
	 * Hashes the game
	 * 
	 * @return a hashed game
	 */
	public int hashCode() {
		return 19 * readMove(0, 0) + 13 * readMove(0, 1) + 29 * readMove(0, 2) + 51 * readMove(1, 0)
				+ 37 * readMove(1, 1) + 43 * readMove(1, 2) + 17 * readMove(2, 0) + 7 * readMove(2, 1)
				+ 37 * readMove(2, 2);
	}
}
