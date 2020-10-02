package messageQueue;

import java.util.ArrayList;
import java.util.Comparator;
import message.Message;

/**
 * This class uses the Message objects and creates a queue of messages. You can
 * add, delete, and view the stored messages. You play a message at index i.
 * When you delete a message, the array adjusts itself.
 * 
 * @author Abdurrahman
 *
 */
public class MessageQueue {

	// ********** Instance Variables **********
	/** An array list of messages stored in the queue */
	private ArrayList<Message> messages = new ArrayList<Message>();

	// ********** Constructor **********
	/** Construct an empty queue of Messages */
	public MessageQueue() {
	}

	// ********** Methods **********
	/**
	 * Play message at index i
	 * 
	 * @param i is the index where the message is located
	 * @return the message at index i
	 * @precondition i must be greater than 0 and below the actual size of the queue
	 */
	public Message playMessage(int i) {
		assert 0 <= i && i < size() : "Invalid queue index"; /** Assert invalid index number */
		return messages.get(i); /** Return Message at index i */
	}

	/**
	 * Method to enqueue a message in queue.
	 * 
	 * @param item is a Message which will be enqueued
	 */
	public void enqueue(Message message) {
		messages.add(message); /** Add the message to queue */
	}

	/**
	 * Method to dequeue an item from the queue. It deletes the item at index i and
	 * reconstructs the queue t fill in any gaps.
	 * 
	 * @param i is the index where the message to be deleted is stored
	 * @precondition i must be greater than 0 and below the actual size of the queue
	 */
	public void dequeue(int i) {
		assert 0 <= i && i < size() : "Invalid queue index"; /** Assert invalid index number */
		messages.remove(i); /** Removes message at index i */
	}

	/**
	 * Erases the queue
	 */
	public void eraseQueue() {
		messages.clear(); /** Wipe out the ArrayList */
	}

	/** Sort the messages in queue in alphabetical order */
	public void AlphabeticalSort() {
		messages.sort(Message.comparatorMessage());
		// Collections.sort(messages, Message.comparatorMessage()); // Does the same
	}

	/**
	 * 
	 * @return a comparator to sort queues based on the number of messages
	 */
	public static Comparator<MessageQueue> comparatorByNumberOfMessages() {
		return new Comparator<MessageQueue>() { // Make object of anonymous class
			public int compare(MessageQueue messageQueue1, MessageQueue messageQueue2) {
				return Integer.compare(messageQueue1.size(), messageQueue2.size());
			}
		};
	}

	/**
	 * Returns the size of queue
	 * 
	 * @return the size for the queue as an int
	 */
	public int size() {
		return messages.size();
	}
}
