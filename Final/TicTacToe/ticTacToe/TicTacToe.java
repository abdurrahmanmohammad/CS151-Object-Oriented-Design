package ticTacToe;

import ai.AI;
import ai.AIEasy;
import ai.AIHard;
import ai.AIMedium;
import game.Game;
import game.GameInterface;
import gui.Board;

public class TicTacToe {
	// ********** Instance Variables **********
	/** Make a GUI for the Tic Tac Toe game */
	Board board = new Board();

	// ********** Constructor **********
	/**
	 * Constructs a Tic Tac Toe game
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public TicTacToe() {
	}

	/**
	 * Method to initiate the game
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void initiate() {
		int button = 0; /** Controls the loop */
		do {
			Game game = new Game(); /** Create a Tic Tac Toe game board storage */
			board.clearBoard(); /** Clear the GUI board */
			board.printString("Welcome to Tic Tac Toe!"); /** Greet the user */
			int selection = getGameSelection(); /** Get user's game mode */
			board.printString("Do you wish to load an existing game?");
			board.setButton(1, "Yes"); /** Set button1 to get input selection */
			board.setButton(2, "No"); /** Set button2 to not get input selection */
			if (board.getButton() == 1) {
				board.clearBoard(); /** Clear the GUI board before starting a game */
				board.printString("Enter filename ending with '.txt'");
				try {
					game = GameInterface.loadGame(board.getString()); /** Get filename and load the board */
				} catch (Exception e) { /** Catch the exception */
					board.printString("Invalid filename!");
					board.printString("Starting new game ...");
				}
			}
			board.clearBoard(); /** Clear the GUI board before starting a game */
			if (selection == 1)
				game = playAI(game, AIsetup(1)); /** Play an easy difficulty game */
			if (selection == 2)
				game = playAI(game, AIsetup(2)); /** Play a medium difficulty game */
			if (selection == 3)
				game = playAI(game, AIsetup(3)); /** Play a hard difficulty game */
			if (selection == 4)
				playMultiplayer(game); /** Play player vs. player game */
			saveGame(game); /** Let the user save the game if he wished */
			board.printString("Play again?"); /** Ask user to play again */
			board.setButton(1, "Yes"); /** Set button1 to get input selection */
			board.setButton(2, "No"); /** Press button2 or any other button to select No */
			button = board.getButton(); /** Get input from the user */
			if (button == 2)
				System.exit(0);
		} while (button == 1); /** If user wants to play again, the game is repeated */
	}

	/**
	 * Method to save a game
	 * 
	 * @param game is the game to save
	 * @precondition none
	 * @postcondition none
	 */
	private void saveGame(Game game) {
		board.printString("Do you wish to save the game?");
		board.setButton(1, "Yes"); /** Set button1 to to select Yes */
		board.setButton(2, "No");
		if (board.getButton() == 1) {
			board.clearBoard(); /** Clear the board */
			board.printString("Enter filename ending with '.txt'");
			try {
				GameInterface.saveGame(board.getString(), game); /** Get filename and save the board */
				board.printString("Game successfully saved!"); /** Display success message */
			} catch (Exception e) {
				board.printString("Invalid filename!"); /** Inform user of error */
				board.printString("Failed to save game!"); /** Inform user of error */
			}
		}
		board.clearBoard(); /** Clear the board */
	}

	/**
	 * Lets user pick who starts the game
	 * 
	 * @return 'X' = AI starts, 'O' = User starts
	 * @precondition none
	 * @postcondition none
	 */
	private char playFirst() {
		board.printString("Do you wish to start first?");
		board.setButton(1, "Yes");
		board.setButton(2, "No");
		int button = board.getButton(); /** Get user's selection */
		while (button != 1 && button != 2) { /** If something else was clicked */
			board.printString("Wrong selection!");
			board.printString("Do you wish to start first?");
			button = board.getButton(); /** Get user's selection */
		}
		board.clearBoard(); /** Clear the board */
		if (button == 1) {
			board.printString("User starts first!");
			return 'O'; /** User starts first. This is the AI's piece */
		} else { /** button == 2 */
			board.printString("AI starts first!");
			return 'X'; /** AI starts first. This is the user's piece */
		}
	}

	/**
	 * Method to get the game mode/type: Easy, medium, player vs. player
	 * 
	 * @return 1 = easy, 2 = medium, 3 = player vs. player
	 * @precondition none
	 * @postcondition none
	 */
	private int getGameSelection() {
		board.printString("Please select game mode: ");
		board.setButton(1, "Easy");
		board.setButton(2, "Medium");
		board.setButton(3, "Hard");
		board.setButton(4, "Player vs. Player");
		int selection = board.getButton(); /** Get the user selection */
		while (selection > 4) { /** If the user clicks a different button, ask for correct button */
			board.printString("Wrong selection!");
			board.printString("Please select game mode: 1 = easy, 2 = medium, 3 = hard, 4 = player vs. player");
			selection = board.getButton(); /** Get the user selection */
		}
		board.clearBoard(); /** Clear the GUI board */
		return selection; /** Return the user's selection */
	}

	/**
	 * Method for player to make his move
	 * 
	 * @param game  is the game to play
	 * @param piece the user's piece
	 * @return the game with the player's valid move
	 * @precondition game must not be finished
	 * @postcondition none
	 */
	private Game playerMove(Game game, char piece) {
		assert AI.checkGame(game) == 'C' : "Player cannot play: Game has finished"; /** Game cannot be finished */
		int button = board.getButton(); /** Get the button pressed */
		int row = (button - 1) / 3; /** Calculate the row of the user's move */
		int col = (button - 1) % 3; /** Calculate the column of the user's move */
		while (game.readMove(row, col) != ' ') { /** Choose a valid spot on the game board (spot has to equal ' ') */
			board.printString("Invalid move! Try again:"); /** Inform the user of his error */
			button = board.getButton(); /** Get the button pressed */
			row = (button - 1) / 3; /** Calculate the new row of the user's move */
			col = (button - 1) % 3; /** Calculate the new column of the user's move */
		}
		game.saveMove(piece, row, col); /** Save the valid move */
		return game; /** Return the game with the user's move */
	}

	/**
	 * Prints the winner of the game
	 * 
	 * @param game is the game to check
	 * @precondition the game should be finished
	 * @postcondition none
	 */
	private void printWinner(Game game) {
		char winner = AI.checkGame(game);
		assert winner == 'C' : "Cannot print winner: Game not finished";
		if (winner == 'X')
			board.printString("Player X won!");
		if (winner == 'O')
			board.printString("Player O won!");
		if (winner == 'D')
			board.printString("Draw game!");
	}

	/**
	 * Method to play Tic Tac Toe with another player
	 * 
	 * @param game is the game to play
	 * @return the completed game
	 * @precondition game must not be finished
	 * @postcondition none
	 */
	private Game playMultiplayer(Game game) {
		assert AI.checkGame(game) == 'C' : "Cannot do multiplayer: Game is already finished";
		board.printString("Player 1 is X. Player 2 is O."); /** Inform the user of the 2 players */
		while (AI.checkGame(game) == 'C') { /** While the game is not finished */
			board.printString("Player 1's turn"); /** Notify player 1 of his turn */
			playerMove(game, 'X'); /** Player 1's turn */
			board.printGame(game); /** Print the game onto the GUI (board) */
			if (AI.checkGame(game) != 'C')
				break; /** Quit if somebody wins */
			board.printString("Player 2's turn"); /** Notify player 2 of his turn */
			playerMove(game, 'O'); /** Player 2's turn */
			board.printGame(game); /** Print the game onto the GUI (board) */
		}
		printWinner(game); /** Print the winner */
		board.clearBoard(); /** Clear the board */
		return game; /** return the finished game */
	}

	/**
	 * Determines which AI to use and returns it
	 * 
	 * @param AIselection is the AI's number: 1 = AIEasy, 2 = AIMedium
	 * @return the created AI to use based on AIselection with its pice set
	 * @precondition AIselection == 1 || AIselection == 2
	 */
	private AIEasy AIsetup(int AIselection) {
		assert AIselection == 1 || AIselection == 2
				|| AIselection == 3 : "Wrong AI selection"; /** Assert wrong AI selection */
		char AIpiece = playFirst(); /** Get AI's piece (X = start first, O = start second) */
		if (AIselection == 1) { /** Return the chosen AI */
			return new AIEasy(AIpiece); /** Create easy AI and set its piece */
		} else if (AIselection == 2) { /** AIselection == 2 */
			return new AIMedium(AIpiece); /** Create medium AI and set its piece */
		} else {
			return new AIHard(AIpiece); /** Create hard AI and set its piece */
		}
	}

	/**
	 * User plays with the chosen AI
	 * 
	 * @param game        is the game that is played
	 * @param AIselection 1 = easy AI, 2 = medium AI
	 * @return the completed game
	 * @precondition game must not be completed
	 * @postcondition none
	 */
	private Game playAI(Game game, AIEasy AIobject) {
		assert AI.checkGame(game) == 'C' : "AI cannot play: Game has finished"; /** Game cannot be finished */
		char userPiece = (AIobject.getPiece() == 'X') ? 'O' : 'X'; /** Get user's piece */
		if (AIobject.getPiece() == 'X' && AI.checkGame(game) == 'C') { /** AI starts first */
			board.printString("AI's turn!");
			AIobject.makeMove(game); /** AI makes first move if its piece is 'X' and loaded game is not finished */
			board.printGame(game); /** Print the game onto the GUI (board) */
		}
		while (AI.checkGame(game) == 'C') { /** While the game is not finished */
			board.printString("User's turn"); /** Inform player of his turn */
			game = playerMove(game, userPiece); /** Let the player make his move */
			board.printGame(game); /** Print the game onto the GUI (board) */
			if (AI.checkGame(game) != 'C') {
				board.printString("Game Over!");
				break; /** Quit if somebody wins */
			}
			board.printString("AI's turn!");
			AIobject.makeMove(game); /** AI then makes its move */
			board.printGame(game); /** Print the game onto the GUI (board) */
		}
		printWinner(game); /** Print the winner */
		board.clearBoard(); /** Clear the board */
		return game; /** Return the completed game */
	}
}
