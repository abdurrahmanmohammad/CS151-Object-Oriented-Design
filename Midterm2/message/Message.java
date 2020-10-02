package message;

import java.util.Comparator;

/**
 * The purpose of the Message object is to store a message. It does so by
 * storing a String. This class is immutable.
 * 
 * @author Abdurrahman
 *
 */
public class Message implements Comparator<Message> {
	// ********** Instance Variable **********
	/** This variable stores the message as a String */
	private final String message;

	// ********** Constructor **********
	/**
	 * The constructor of the Message class creates a new Message object
	 * 
	 * @param message is a String which will be stored
	 */
	public Message(String message) {
		this.message = message;
	}

	// ********** Comparator **********
	/**
	 * A method to compare two Message objects
	 * 
	 * @return string1 > string2: 1, string1 == string2: 0, string1 < string2: -1
	 */
	@Override
	public int compare(Message o1, Message o2) {
		return o1.getMessage().compareTo(o2.getMessage());
	}

	/**
	 * 
	 * @return a Comparator that compares messages in alphabetical order
	 */
	public static Comparator<Message> comparatorMessage() {
		return new Comparator<Message>() { // Make object of anonymous class
			public int compare(Message message1, Message message2) {
				return message1.getMessage().compareTo(message2.getMessage());
			}
		};
	}

	// ********** Getter **********
	/**
	 * This method returns the saved message as a String
	 * 
	 * @return a message is returned as a String
	 */
	public String getMessage() {
		return message;
	}
}