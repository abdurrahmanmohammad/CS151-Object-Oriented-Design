package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game.Game;
import game.GameInterface;

/**
 * Makes a GUI for the Tic Tac Toe game
 * 
 * @author abdurrahman
 *
 */
public class Board {
	// ********** Instance Variables **********
	/** Stores the most recent button clicked */
	private int click = 0;
	/** Stores the last printed game */
	private Game game;
	/** Make a text field for input/output. field width = 20 */
	private JTextField textField = new JTextField(20);
	/** Make the buttons */
	/** Represents spot (0, 0) on the tic tac toe GUI */
	private JButton button1 = new JButton();
	/** Represents spot (0, 1) on the tic tac toe GUI */
	private JButton button2 = new JButton();
	/** Represents spot (0, 2) on the tic tac toe GUI */
	private JButton button3 = new JButton();
	/** Represents spot (1, 0) on the tic tac toe GUI */
	private JButton button4 = new JButton();
	/** Represents spot (1, 1) on the tic tac toe GUI */
	private JButton button5 = new JButton();
	/** Represents spot (1, 2) on the tic tac toe GUI */
	private JButton button6 = new JButton();
	/** Represents spot (2, 0) on the tic tac toe GUI */
	private JButton button7 = new JButton();
	/** Represents spot (2, 1) on the tic tac toe GUI */
	private JButton button8 = new JButton();
	/** Represents spot (2, 2) on the tic tac toe GUI */
	private JButton button9 = new JButton();

	// ********** Constructor **********
	/** Constructs and initializes the GUI */
	public Board() {
		/** Steps to make and initialize the GUI frame: */
		JFrame frame = new JFrame(); /** Create a new frame display */
		frame.pack(); /** Pack the frame */
		frame.setTitle("Tic Tac Toe"); /** Set the title of the window */
		int FRAME_WIDTH = 500; /** Width of frame */
		int FRAME_HEIGHT = 500; /** Height of frame */
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT); /** Set the size of frame */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /** Exit on close */
		frame.setLayout(new GridLayout(2, 1)); /** Give the frame a grid layout */
		/** Make a panel for the game (3x3 layout of buttons) */
		JPanel panel = new JPanel(new GridLayout(4, 3)); /** Make a panel */
		/** Add the buttons to the panel */
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		panel.add(button5);
		panel.add(button6);
		panel.add(button7);
		panel.add(button8);
		panel.add(button9);
		JButton button10 = new JButton();
		JButton button11 = new JButton("Save and Stop");
		JButton button12 = new JButton();
		panel.add(button10);
		panel.add(button11);
		panel.add(button12);
		/** Add action listeners */
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				click = 1;
			}
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				click = 2;
			}
		});
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				click = 3;
			}
		});
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				click = 4;
			}
		});
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				click = 5;
			}
		});
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				click = 6;
			}
		});
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				click = 7;
			}
		});
		button8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				click = 8;
			}
		});
		button9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				click = 9;
			}
		});
		button11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				click = 11;
			}
		});
		frame.add(textField); /** Add the text field and panel to the GUI frame */
		frame.add(panel); /** Add the panel of buttons to the GUI frame */
		frame.setVisible(true); /** Make the frame visible */
	}

	// ********** Methods **********
	/**
	 * Method to get the button clicked by the user
	 * 
	 * @return get a key from the user and return it as an int
	 * @precondition none
	 * @postcondition none
	 */
	public int getButton() {
		click = 0; /** Clear the current value of click */
		long startTime = System.nanoTime(); /** Get the starting time of input request */
		while (click == 0) { /** Keep waiting if no button is pressed */
			textField.setText("Make a selection: ");/** Prompt user to click a button */
			if (System.nanoTime() - startTime == 10) { /** If the user takes too long (10+ seconds), shut down */
				printString("User inactivity detected. Shutting down ..."); /** Notify the user of shut down */
				System.exit(0); /** Shut down */
			}
		}
		textField.setText(""); /** Clear the text field display (clear the previously displayed prompt) */
		if (click == 11)
			saveGame(); /** Save game and exit if you click 'save and stop */
		return click; /** Return the entered key */
	}

	/**
	 * Sets the text of a button
	 * 
	 * @param num   is the number of the button
	 * @param input is the string of the move in the Tic Tac Toe game
	 * @precondition 0 <= num <= 9
	 * @precondition input should be "X", "O", and " " only
	 * @postcondition none
	 */
	public void setButton(int num, String input) {
		assert 0 <= num && num <= 9 : "Invalid number";
		assert input == "X" || input == "O" || input == " " : "Cannot display invalid game piece";
		if (num == 1)
			button1.setText(input);
		if (num == 2)
			button2.setText(input);
		if (num == 3)
			button3.setText(input);
		if (num == 4)
			button4.setText(input);
		if (num == 5)
			button5.setText(input);
		if (num == 6)
			button6.setText(input);
		if (num == 7)
			button7.setText(input);
		if (num == 8)
			button8.setText(input);
		if (num == 9)
			button9.setText(input);
	}

	/**
	 * Prints string onto the GUI
	 * 
	 * @param output is the string to print
	 * @precondition output should not be null
	 * @postcondition none
	 */
	public void printString(String output) {
		assert output != null : "Cannot print null String";
		textField.setText(output); /** Print the output text */
		long startTime = System.nanoTime();
		while (((System.nanoTime() - startTime) / 1000000000) < 1) { /** Display message for 1 second */
		}
	}

	/**
	 * Get a string from the text field
	 * 
	 * @return the text of the user from the text field
	 * @precondition none
	 * @postcondition none
	 */
	public String getString() {
		printString("Enter text after this message");/** Prompt user to type into the GUI */
		textField.setText(""); /** Clear the text field */
		long startTime = System.nanoTime();
		while (((System.nanoTime() - startTime) / 1000000000) < 5) { /** Wait for 5 seconds */
			if (System.nanoTime() - startTime == 10) { /** If the user takes too long, shut down */
				printString("User inactivity detected. Shutting down ...");
				System.exit(0);
			}
		}
		return textField.getText(); /** Return the user's input */
	}

	/**
	 * Clear the string of the buttons in the GUI (board)
	 * 
	 * @param board is the board whose buttons will be cleared
	 * @precondition none
	 * @postcondition none
	 */
	public void clearBoard() {
		for (int i = 1; i < 10; i++) {
			setButton(i, " "); /** Set all button text to " " */
		}
	}

	/**
	 * Prints the game onto the GUI (board)
	 * 
	 * @param game is the game to print onto the GUI board
	 * @precondition none
	 * @postcondition none
	 */
	public void printGame(Game game) {
		this.game = game; /** Save this game */
		for (int i = 0; i < 3; i++) { /** Traverse rows */
			for (int j = 0; j < 3; j++) { /** Traverse columns */
				setButton(3 * i + (j + 1), String.valueOf(game.readMove(i, j)));
			}
		}
	}

	/**
	 * Saves the last printed game (last game)
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	private void saveGame() {
		printString("Enter filename ending with '.txt'"); /** Ask for filename */
		try {
			GameInterface.saveGame(getString(), game); /** Get filename and save the board */
			printString("Game successfully saved!"); /** Display success message */
		} catch (Exception error) {
			printString("Invalid filename!"); /** Inform user of error */
			printString("Failed to save game!"); /** Inform user of error */
		}
		System.exit(0); /** Exit the game */
	}
}
