package gui;

/**
 * Tests the Tic Tac Toe game board GUI
 * 
 * @author abdurrahman
 *
 */
public class BoardTest {
	public static void main(String[] args) {
		Board board = new Board(); /** Create a new board to test */
		for (int i = 0; i < 9; i++) { /** Test all the buttons, getButon(), and printString(), and setButton() */
			int button = board.getButton(); /** Get the button number pressed */
			board.setButton(button, Integer.toString(i + 1)); /** Set the button's text to (i + 1) */
			board.printString("Get Button: " + button); /** Print the button onto the GUI */
		}
		String input = board.getString(); /** Test the getString() method */
		board.printString("You have entered: " + input); /** Print that string onto the GUI */
		board.printString("Test Complete!"); /** Inform the user of the completion of the test */
	}
}
