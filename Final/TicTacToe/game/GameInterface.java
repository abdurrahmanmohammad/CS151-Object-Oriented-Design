package game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface to implement a game storage for Tic Tac Toe
 * 
 * @author abdurrahman
 */
public interface GameInterface {
	/** Method to save a move to the game storage */
	public void saveMove(char move, int row, int col);

	/** Method to read a move from the game storage */
	public char readMove(int row, int col);

	// ********** Static methods **********
	/**
	 * Method to save game to a file
	 * 
	 * @param filename is the file to write
	 * @throws IOException
	 * @precondition file must not already exist
	 * @precondition the file must end with ".txt"
	 * @postcondition none
	 */
	public static void saveGame(String filename, Game game) throws IOException {
		assert !(new File(filename).exists()) : "File aleady exists: Cannot save game";
		assert filename.endsWith(".txt") : "File does not end with \".txt\""; /** File must end with ".txt" */
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename)); /** Create a writer */
		for (int i = 0; i < 3; i++) { /** Traverse rows */
			for (int j = 0; j < 3; j++) { /** Traverse columns */
				writer.write(game.readMove(i, j)); /** Write the move into the file */
			}
		}
		writer.close(); /** Close the stream after finishing */
	}

	/**
	 * Method to load a game from an existing file
	 * 
	 * @param filename is the name of the file to load
	 * @return the corresponding game board contained in the file
	 * @throws FileNotFoundException
	 * @precondition the file must exist
	 * @precondition the file must end with ".txt"
	 * @precondition the file must contain exactly 9 chars
	 * @precondition the file must contain only 'X', 'O', and ' '
	 * @postcondition none
	 */
	public static Game loadGame(String filename) throws FileNotFoundException {
		assert (new File(filename).exists()) : "File does not exists"; /** Throw assertion if the file doesn't exist */
		assert filename.endsWith(".txt") : "File does not end with '.txt'"; /** File must end with ".txt" */
		Game gameboard = new Game(); /** Make a new game board to load and return */
		Scanner sc = new Scanner(new File(filename)); /** Create a Scanner for reading */
		String input = sc.next(); /** Get the whole chunk of string from the file */
		assert input.length() == 9 : "Invalid input in file"; /** Throw assertion if there aren't 9 chars */
		for (int i = 0; i < 3; i++) { /** Traverse rows */
			for (int j = 0; j < 3; j++) { /** Traverse columns */
				int stringIndex = 3 * i + j; /** 3 * i because there are 3 columns in a row */
				char move = input.charAt(stringIndex); /** Extract and store the char */
				assert move == 'X' || move == 'O' || move == ' ' : "Invalid move in file";
				gameboard.saveMove(move, i, j); /** Store the char in the game */
			}
		}
		sc.close(); /** Close the stream after finishing */
		return gameboard; /** Return the now filled game board */
	}

}
