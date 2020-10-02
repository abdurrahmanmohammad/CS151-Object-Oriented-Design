package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import message.Message;

/**
 * Makes a GUI of a phone and allows users to enter input and see output.
 * 
 * @author Abdurrahman
 *
 */
public class GUI {
	// ********** Constants **********
	/** The width of the text field */
	private final int FIELD_WIDTH = 20;
	// ********** Instance Variables **********
	/** Stores the numeric input from the keypad. Updates at every key press. */
	private int keyInput = 0;
	/** Make a text field for input/output */
	private JTextField textField = new JTextField(FIELD_WIDTH);

	// ********** Constructor **********
	/** Constructs and initializes the GUI */
	public GUI() {
		/** Steps to make and initialize the GUI frame: */
		JFrame frame = new JFrame(); /** Create a new frame display */
		frame.pack(); /** Pack the frame */
		frame.setTitle("Telephone Keypad"); /** Set the title of the window */
		int FRAME_WIDTH = 500; /** Width of frame */
		int FRAME_HEIGHT = 500; /** Height of frame */
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT); /** Set the size of frame */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /** Exit on close */
		frame.setLayout(new GridLayout(2, 1)); /** Give the frame a grid layout */
		frame.setVisible(true); /** Make the frame visible */

		/** Steps to make a panel for the keypad, hang up key, input, and output: */
		JPanel panel = new JPanel(new GridLayout(5, 3)); /** Make a panel */
		/** Make the buttons */
		JButton button1 = new JButton("1");
		JButton button2 = new JButton("2");
		JButton button3 = new JButton("3");
		JButton button4 = new JButton("4");
		JButton button5 = new JButton("5");
		JButton button6 = new JButton("6");
		JButton button7 = new JButton("7");
		JButton button8 = new JButton("8");
		JButton button9 = new JButton("9");
		JButton buttonStar = new JButton("*");
		JButton button0 = new JButton("0");
		JButton buttonPound = new JButton("#");
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
		panel.add(buttonStar); /** Not used */
		panel.add(button0);
		panel.add(buttonPound); /** Not used */
		/** Add the hang up key and 2 blank buttons to the bottom row of the panel: */
		JButton hangUp = new JButton("Hang Up"); /** Make the hang up key */
		JButton blank1 = new JButton();
		JButton blank2 = new JButton();
		panel.add(blank1);
		panel.add(hangUp);
		panel.add(blank2);

		/** Initialize input/output text field */
		textField.setText("Input/Output: ");

		/** Add the text field and panel to the GUI frame */
		frame.add(textField);
		frame.add(panel);

		/** Action listeners */
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				keyInput = 1;
			}
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				keyInput = 2;
			}
		});
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				keyInput = 3;
			}
		});
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				keyInput = 4;
			}
		});
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				keyInput = 5;
			}
		});
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				keyInput = 6;
			}
		});
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				keyInput = 7;
			}
		});
		button8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				keyInput = 8;
			}
		});
		button9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				keyInput = 9;
			}
		});
		button0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				keyInput = 0;
			}
		});
		hangUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Button action goes here */
				textField.setText("Hang Up");
				System.exit(0); /** Hang up the phone and close the system */
			}
		});
		frame.setVisible(true); /** Make the frame visible */
	}

	/**
	 * @return get a key from the user and return it as an int
	 */
	public int getKey() {
		keyInput = -1; /** Clear the current value of keyInput */
		long startTime = System.nanoTime(); /** Get the starting time of input request */
		while (keyInput == -1) { /** Keep waiting if no key is pressed */
			textField.setText("Press a Key: ");/** Prompt user to enter a key */
			if (System.nanoTime() - startTime == 10) { /** If the user takes too long (10+ seconds) , hang up */
				printString("User inactivity detected. Hanging up ..."); /** Notify the user */
				System.exit(0); /** Hang up */
			}
		}
		textField.setText("Pressed: " + keyInput); /** Show the entered key */
		startTime = System.nanoTime(); /** Reset startTime to control the print below */
		while (((System.nanoTime() - startTime) / 1000000000) < 1) { /** Display message for 1 seconds */
		}
		textField.setText(""); /** Clear the display */

		return keyInput; /** Return the entered key */
	}

	/**
	 * @return get 3 keys from the user and return them as an int
	 */
	public int get3Keys() {
		int key1 = 100 * getKey(); /** First digit of the output */
		int key2 = 10 * getKey(); /** Second digit of the output */
		int key3 = getKey(); /** Third digit of the output */
		return key1 + key2 + key3; /** Combine the 3 keys and return */
	}

	/**
	 * Prints out a message object onto the GUI
	 * 
	 * @param message is the message to print out
	 * @precondition message cannot be null
	 */
	public void printMessage(Message message) {
		assert message != null : "Cannot print null message";
		printString(message.getMessage()); /** Print the message */
	}

	/**
	 * Prints Strings objects onto the GUI
	 * 
	 * @param output is the String to print
	 * @precondition output should not be null
	 */
	public void printString(String output) {
		assert output != null : "Cannot print null String";
		textField.setText(output); /** Print the output text */
		long startTime = System.nanoTime();
		while (((System.nanoTime() - startTime) / 1000000000) < 3) { /** Display message for 3 seconds */
		}
	}

	/**
	 * Gets a message from the user
	 * 
	 * @return the message from the user
	 */
	public Message getMessage() {
		printString("Speak the message: ");/** Prompt user to 'speak' into the GUI */
		long startTime = System.nanoTime();
		textField.setText(""); /** Clear the text field for input */
		while (((System.nanoTime() - startTime) / 1000000000) < 5) { /** Wait max 5 seconds for input */
			if (System.nanoTime() - startTime == 10) { /** If the user takes too long, hang up */
				printString("User inactivity detected. Hanging up ...");
				System.exit(0);
			}
		}
		Message message = new Message(textField.getText()); /** Get the input and put it in a message */
		printString("Message comeplete!"); /** Indicate the end of the message */
		return message; /** Return the message */
	}

	/**
	 * Simulates speaking
	 * 
	 * @return the text of the user when calling
	 */
	public String speak() {
		printString("Call connected");/** Inform the user of the connection */
		printString("Speak: ");/** Prompt user to 'speak' into the GUI */
		long startTime = System.nanoTime();
		while (((System.nanoTime() - startTime) / 1000000000) < 6000) { /** Call lasts for 100 minutes */
			if (System.nanoTime() - startTime == 10) { /** If the user takes too long, hang up */
				printString("User inactivity detected. Hanging up ...");
				System.exit(0);
			}
		}
		return textField.getText(); /** Return the user's speech */
	}
}
