package mailbox;

import java.util.Comparator;
import GUI.GUI;
import greeting.Greeting;
import messageQueue.MessageQueue;

/**
 * This is a Mailbox to store
 * 
 * @author Abdurrahman
 *
 */
public class Mailbox implements MailboxInterface {
	// ********** Instance Variables **********
	/** The assigned phone number */
	private final int phoneNumber;
	/** The password of the mailbox */
	private int password;
	/** Greeting of the mailbox */
	private Greeting greeting = new Greeting();
	/** Is the user present to pickup the phone */
	private boolean isPresent = false;
	/** Queue of unread messages */
	private MessageQueue unreadMessages = new MessageQueue();
	/** Queue of saved messages */
	private MessageQueue savedMessages = new MessageQueue();
	/** GUI for input and output */
	private static GUI gui = null;

	// ********** Constructor **********
	/**
	 * Constructor to construct an empty mailbox
	 * 
	 * @param phoneNumber the phone number of this mailbox
	 * @param password    the password of this mailbox
	 * @precondition 000 <= phoneNumber <= 999
	 * @precondition 000 <= password <= 999
	 */
	public Mailbox(int phoneNumber, int password) {
		assert 0 <= phoneNumber && phoneNumber <= 999 : "Invalid phone number!";
		assert 0 <= password && password <= 999 : "Invalid password!";
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

	/**
	 * Play a greeting and let the user leave a message
	 * 
	 * @param message is the message to enqueue
	 */
	public void leaveMessage() {
		gui.printString("Playing greeting: ");
		if (greeting.getDefaultGreeting().getMessage() == null) {
			/** Play a generic greeting if default greeting is not set */
		} else {
			gui.printMessage(greeting.getDefaultGreeting()); /** Play the default greeting if not null */
		}
		unreadMessages.enqueue(gui.getMessage()); /** Get the message form the caller and enqueue it */
		gui.printString("Message saved!");
	}

	/**
	 * Checks the password too see if the inputed password is correct
	 * 
	 * @param pswd is a password attempt
	 * @return true if the passwords match
	 */
	public boolean passwordCheck(int pswd) {
		return pswd == password; // Password size won't matter
	}

	// ********** Queue Management **********
	/** Plays unread messages in queue and lets user save or delete messages */
	public void unreadMessageManagement() {
		gui.printString("Welcome to unread messages management! " + "Playing messages ...");
		for (int i = 0; i < unreadMessages.size(); i++) { /** Manage messages */
			gui.printMessage(unreadMessages.playMessage(i)); /** Play message at index i */
			gui.printString("To delete this message, press 1. " + "To delete all, press 2. "
					+ "Press any other key for next message. " + "You may hangup any time. ");
			int option = gui.getKey(); /** Get input for option */
			if (option == 1) {
				unreadMessages.dequeue(i); /** Remove message at i */
			} else if (option == 2) {
				unreadMessages.eraseQueue(); /** Wipe out all messages */
			} else { /** For other input, add message to saved messages and go to next message */
				savedMessages.enqueue(unreadMessages.playMessage(i)); /** Save played message */
				unreadMessages.dequeue(i); /** Remove played message from unread messages */
				i--; /** Since ArrayList condensed, adjust index i */
			}
		}
		gui.printString("End of messages!");
	}

	// ********** Queue Management **********
	/** Plays saved messages in queue and lets user save or delete messages */
	public void savedMessageManagement() {
		gui.printString("Welcome to read messages management!\n" + "Playing messages ...");
		for (int i = 0; i < savedMessages.size(); i++) { /** Manage messages */
			gui.printMessage(savedMessages.playMessage(i)); /** Play message at index i */
			gui.printString("To delete this message, press 1. " + "To delete all, press 2. "
					+ "Press any other key for next message. " + "You may hangup any time. ");
			int option = gui.getKey(); /** Get input for option */
			if (option == 1) {
				savedMessages.dequeue(i); /** Remove message at i */
			} else if (option == 2) {
				savedMessages.eraseQueue(); /** Wipe out all messages */
			} /** Any other input means the message is saved and next message is played */
		}
		gui.printString("End of messages!");
	}

	/**
	 * Method to manage the mailbox greeting object. You may add a greeting, remove
	 * a greeting, set a default greeting, and play a greeting
	 */
	public void greetingManagement() {
		gui.printString("Welcome to greeting management");
		gui.printString("Press 1 to set default greeting. " + "Press 2 to add a greeting. "
				+ "Press 3 to remove a greeting. " + "Press 4 to play a greeting. " + "Press any key to exit. ");
		int option = gui.getKey(); /** Get input */
		gui.printString("Enter the greeting to set (Either 0, 1, or 2): ");
		int index = gui.getKey(); /** Get the greeting index to access/modify */
		if (0 <= index && index <= 2) { /** Indices range from 0 to 2 */
			if (option == 1) { /** Set default greeting */
				greeting.setDefault(index); /** Set default greeting */
			} else if (option == 2) { /** Add a greeting */
				gui.printString("Recording greeting ... ");
				greeting.addGreeting(index, gui.getMessage()); /** Record greeting and add/replace greeting at index */
			} else if (option == 3) { /** Remove a greeting */
				greeting.removeGreeting(index); /** Remove greeting at index */
			} else if (option == 4) { /** Play a greeting */
				gui.printMessage(greeting.getGreeting(index)); /** Display selected greeting at index */
			} else { /** For invalid option input, no operations are performed */
				gui.printString("Sorry, your option was invalid. Please call again.");
			}
		} else { /** For invalid index input, no operations are performed */
			gui.printString("Sorry, your input for greeting number was invalid. Please call again.");
		}
		gui.printString("End of greeting management!");
	}

	// ********** Comparator Methods **********
	/**
	 * 
	 * @return a comparator to sort based on phone numbers
	 */
	public static Comparator<Mailbox> comparatorByPhoneNumber() {
		return new Comparator<Mailbox>() { // Make object of anonymous class
			public int compare(Mailbox mailbox1, Mailbox mailbox2) {
				return Integer.compare(mailbox1.getPhoneNumber(), mailbox2.getPhoneNumber());
			}
		};
	}

	/**
	 * 
	 * @return a comparator to sort based on the number of unread messages
	 */
	public static Comparator<Mailbox> comparatorByUnreadMessages() {
		return new Comparator<Mailbox>() { // Make object of anonymous class
			public int compare(Mailbox mailbox1, Mailbox mailbox2) {
				return Integer.compare(mailbox1.unreadMessages.size(), mailbox2.unreadMessages.size());
			}
		};
	}

	/**
	 * 
	 * @return a comparator to sort based on the number of unread messages
	 */
	public static Comparator<Mailbox> comparatorBySavedMessages() {
		return new Comparator<Mailbox>() { // Make object of anonymous class
			public int compare(Mailbox mailbox1, Mailbox mailbox2) {
				return Integer.compare(mailbox1.savedMessages.size(), mailbox2.savedMessages.size());
			}
		};
	}

	// ********** Getters **********
	/**
	 * @return the phoneNumber
	 */
	public int getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return true if the user is present (for calling purposes)
	 */
	public boolean isPresent() {
		return isPresent;
	}

	// ********** Setters **********

	/**
	 * Method to set the password of the mailbox
	 * 
	 * @param pswd is a 4 digit integer
	 * @precondition 000 <= pswd <= 999
	 */
	public void setPassword(int pswd) {
		assert 0 <= pswd && pswd <= 999 : "Invalid password"; /** Password must be in range */
		password = pswd;
	}

	/**
	 * Pass in the GUI from the voicemail system to reuse and keep a steady flow
	 * 
	 * @param gui the GUI to use to control all Mailbox objects
	 */
	public static void setGui(GUI gui) {
		Mailbox.gui = gui;
	}

}
