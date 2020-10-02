package ticTacToe;

import java.util.Random;

import java.util.Scanner;

/**
 * A class for creating and simulating a tic tac toe game.
 * 
 * @author abdurrahman
 *
 */
public class TicTacToeBoard {
	// -------------------- Instance Variables --------------------
	/** Stores a 3 x 3 array of char values (filled with 'x' , '0' , or " ") */
	private char[][] board = new char[3][3];

	/**
	 * Char symbol of user (X or O). X if user starts first. O if user starts second
	 */
	private char userChar = 'X'; // Initially X

	/**
	 * Char symbol of computer (X or O). X if computer starts first. O if user
	 * starts second
	 */
	private char compChar = 'O'; // Initially O

	/** Scanner object for input */
	Scanner sc = new Scanner(System.in); // Create a Scanner object for input
	// Use Scanner as instance variable to close its stream after use without error

	// -------------------- Constructor --------------------
	/** Constructs a tic tac toe game board and fills it with spaces */
	public TicTacToeBoard() {
		// Fill the board with spaces
		for (int i = 0; i < 3; i++) { // Traverse rows
			for (int j = 0; j < 3; j++) { // Traverse columns
				board[i][j] = ' ';
			}
		}
	}

	/** Method to commence the game */
	public void simulate() {
		System.out.println("Welcome to Tic Tac Toe!"); // Greeting message
		System.out.println("To select a box, enter its row and column numbers (0, 1, 2)."); // Explains how to play
		boolean userStarts = isUserBegins(); // Checks who starts first
		if (userStarts) {
			System.out.println("User starts first.");
			// If user starts, don't switch chars
		} else {
			System.out.println("Computer starts first.");
			// If computer starts, chars need to be switched since X goes first
			compChar = 'X'; // X starts first
			userChar = 'O'; // O starts second
			compSelect(); // Let the computer start first
		}
		while (!isGameOver()) {
			// Do until either someone wins or draw game.
			userSelect();
			compSelect();
		}
		System.out.println("Good game!"); // Message to indicate end of game
		sc.close(); // Close the Scanner stream
	}

	/**
	 * Method to randomly choose who begins
	 * 
	 * @return a boolean value. True is user begins first. False if computer begins
	 *         first.
	 */
	public boolean isUserBegins() {
		Random rand = new Random(); // Use this to generate a random boolean
		return rand.nextBoolean(); // Return a random boolean value
	}

	/** Prints the game board */
	private void printBoard() {
		System.out.printf(" %c | %c | %c \n", board[0][0], board[0][1], board[0][2]); // Print row 1
		System.out.printf("____________\n");
		System.out.printf(" %c | %c | %c \n", board[1][0], board[1][1], board[1][2]); // Print row 2
		System.out.printf("____________\n");
		System.out.printf(" %c | %c | %c \n\n\n", board[2][0], board[2][1], board[2][2]); // Print row 3
	}

	/** Asks user for a valid move and stores it */
	public void userSelect() {
		if (!isGameOver()) { // Do while game is not over
			int row, column; // Create variables for row and column selection
			System.out.println("Your turn!"); // Inform user of his turn
			do {
				System.out.printf("Enter row #: "); // Ask for row
				row = sc.nextInt(); // Store row selection
				System.out.printf("Enter column #: "); // Ask for column
				column = sc.nextInt(); // Store column selection
			} while (!isValidMove(row, column)); // Repeat if input is invalid
			board[row][column] = userChar; // Store the valid move on the game board
			printBoard(); // Print the board
		}
	}

	/** Computer randomly makes a move */
	public void compSelect() {
		if (!isGameOver()) { // Do while game is not over
			Random rand = new Random(); // Use this for random move
			int row, column; // Create variables for row and column selection
			System.out.println("Computer playing ..."); // Inform user of computer's turn
			do {
				row = rand.nextInt(3); // Set a random number for row [0 - 2]
				column = rand.nextInt(3); // Set a random number for column [0 - 2]
			} while (!isValidMove(row, column)); // Repeat if input is invalid
			board[row][column] = compChar; // Store the valid move on the game board
			System.out.printf("Computer chooses %d x %d\n", row, column); // State computer's move
			printBoard(); // Print the board
		}
	}

	/**
	 * Method to determine if move made is valid
	 * 
	 * @param row    the row of the move
	 * @param column the column of the move
	 * @return true if the move is valid, otherwise false
	 */
	public boolean isValidMove(int row, int column) {
		if ((row >= 0 && row <= 2) && (column >= 0 && column <= 2)) { // Numbers must be in range
			if (board[row][column] == ' ') { // The spot must not be chosen
				return true; // If all the conditions are met, the move is valid
			}
		}
		return false; // If any one of the conditions not met, move is invalid
	}

	/**
	 * Method checks to see if the game is over. If the game is over, it prints the
	 * winner
	 * 
	 * @return returns true if game is over. Returns false if not.
	 */
	public boolean isGameOver() {
		// Check all 8 possibilities of winning. 3 in a row must be the same and not a
		// space character.
		if (board[0][0] == board[0][1] && board[0][0] == board[0][2] && board[0][0] != ' ') { // Check row1
			if (board[0][0] == userChar) { // Check who is the player of these moves
				System.out.println("User wins in row 1!"); // Print out winner
			} else {
				System.out.println("Computer wins in row 1!"); // Print out winner
			}
			return true; // Game is over
		}
		if (board[1][0] == board[1][1] && board[1][0] == board[1][2] && board[1][0] != ' ') { // Check row 2
			if (board[1][0] == userChar) { // Check who is the player of these moves
				System.out.println("User wins in row 2!"); // Print out winner
			} else {
				System.out.println("Computer wins in row 2!"); // Print out winner
			}
			return true; // Game is over
		}
		if (board[2][0] == board[2][1] && board[2][0] == board[2][2] && board[2][0] != ' ') { // Check row 3
			if (board[2][0] == userChar) { // Check who is the player of these moves
				System.out.println("User wins in row 3!"); // Print out winner
			} else {
				System.out.println("Computer wins in row 3!"); // Print out winner
			}
			return true; // Game is over
		}
		if (board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[0][0] != ' ') { // Check column 1
			if (board[0][0] == userChar) { // Check who is the player of these moves
				System.out.println("User wins in column 1!"); // Print out winner
			} else {
				System.out.println("Computer wins in column 1!"); // Print out winner
			}
			return true; // Game is over
		}
		if (board[0][1] == board[1][1] && board[0][1] == board[2][1] && board[0][2] != ' ') { // Check column 2
			if (board[0][2] == userChar) { // Check who is the player of these moves
				System.out.println("User wins in column 2!"); // Print out winner
			} else {
				System.out.println("Computer wins in column 2!"); // Print out winner
			}
			return true; // Game is over
		}
		if (board[0][2] == board[1][2] && board[0][2] == board[2][2] && board[0][2] != ' ') { // Check column 3
			if (board[0][2] == userChar) { // Check who is the player of these moves
				System.out.println("User wins in column 3!"); // Print out winner
			} else {
				System.out.println("Computer wins in column 3!"); // Print out winner
			}
			return true; // Game is over
		}
		if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != ' ') { // Check cross 1
			if (board[0][0] == userChar) { // Check who is the player of these moves
				System.out.println("User wins in cross left to right!"); // Print out winner
			} else {
				System.out.println("Computer wins in cross left to right!"); // Print out winner
			}
			return true; // Game is over
		}
		if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != ' ') { // Check cross 2
			if (board[0][2] == userChar) { // Check who is the player of these moves
				System.out.println("User wins in cross right to left!"); // Print out winner
			} else {
				System.out.println("Computer wins in cross right to left!"); // Print out winner
			}
			return true; // Game is over
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') { // If space character present
					return false; // Game not over
				}

			}
		}
		System.out.println("Draw Game!");
		return true; // If all plays are played
	}

	/**
	 * Method to ask who starts first
	 * 
	 * @return a boolean value. True if user starts first. False if computer starts
	 *         first.
	 */
	public boolean isStartFirst() { // This method is currently not used
		String input; // Create a variable to store input
		System.out.println("Do you wish to start first? (Enter Y or y):"); // Ask user
		input = sc.nextLine(); // Store input
		if (input.contentEquals("Y") || input.contentEquals("y")) { // Check if user indicates yes
			return true; // Is user indicates yes by entering 'Y' or 'y', return true
		} else {
			userChar = 'O'; // Change if computer starts first
			compChar = 'X'; // Change if computer starts first
			return false; // Else return false
		}
	}
}
