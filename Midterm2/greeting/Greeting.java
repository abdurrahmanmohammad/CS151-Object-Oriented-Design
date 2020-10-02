package greeting;

import java.util.Comparator;
import message.Message;

/**
 * This class contains 3 messages which serve as greetings. You can store up to
 * 3 greetings: Greeting 1, Greeting 2, and Greeting 3. You can set a default
 * greeting.
 * 
 * @author Abdurrahman
 *
 */
public class Greeting {
	// ********** Instance Variables **********
	/** An array to store 3 greetings as Message objects */
	private Message[] greetings = { new Message(""), new Message(""), new Message("") };
	/** The index of the default greeting */
	private int defaultGreeting = 0; // By default, it is Greeting 0

	// ********** Constructor **********
	/** Constructor constructs a new Greeting object */
	public Greeting() {
	}

	// ********** Methods **********
	/**
	 * This method adds a greeting to the array of greetings.
	 * 
	 * @param i       is the index of Greeting i
	 * @param message is the message to save
	 * @precondition 0 <= i <= 3
	 */
	public void addGreeting(int i, Message message) {
		assert !(0 <= i && i <= 3) : "Index of greeting is out of bounds"; /** Assert invalid index */
		greetings[i] = message; /** Store greeting at index i */
	}

	/**
	 * This method removes a stored greeting at index i.
	 * 
	 * @param i is the index of Greeting i
	 * @precondition 0 <= i <= 3
	 */
	public void removeGreeting(int i) {
		assert !(0 <= i && i <= 3) : "Index of greeting is out of bounds"; /** Assert invalid index */
		greetings[i] = new Message(""); /** Replace greeting at index i with a blank message object */
	}

	/**
	 * Method to get greeting at index i. The greeting can be null.
	 * 
	 * @param i is the index of Greeting i
	 * @precondition 0 <= i <= 3
	 */
	public Message getGreeting(int i) {
		assert !(0 <= i && i <= 3) : "Index of greeting is out of bounds"; /** Assert invalid index */
		return greetings[i];
	}

	/**
	 * This method sets a default greeting from the list of greetings
	 * 
	 * @param i is the index of Greeting i which will become the default greeting
	 * @precondition 0 <= i <= 3
	 */
	public void setDefault(int i) {
		assert !(0 <= i && i <= 3) : "Index of greeting is out of bounds"; /** Assert invalid index */
		defaultGreeting = i; /** Set the index of defaultGreeting to i */
	}

	/**
	 * Method to get the default greeting.
	 * 
	 * @return the default greeting
	 */
	public Message getDefaultGreeting() {
		return greetings[defaultGreeting]; /** Return the default greeting */
	}

	/**
	 * 
	 * @return a comparator to sort greetings based on the default greetings
	 *         alphabetical order
	 */
	public static Comparator<Greeting> comparatorByDefaultGreeting() {
		return new Comparator<Greeting>() { // Make object of anonymous class
			public int compare(Greeting greeting1, Greeting greeting2) {
				return greeting1.getDefaultGreeting().compare(greeting1.getDefaultGreeting(),
						greeting2.getDefaultGreeting()); /** Compare the two greetings */
			}
		};
	}
}
