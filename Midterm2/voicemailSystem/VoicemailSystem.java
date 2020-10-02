package voicemailSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import GUI.GUI;
import mailbox.Mailbox;

/**
 * This simulates a real-life voicemail system and implements various voicemail
 * tasks.
 * 
 * @author Abdurrahman
 *
 */
public class VoicemailSystem {
	// ********** Instance Variables **********
	/** A voicemail system has a list of mailboxes */
	private ArrayList<Mailbox> mailboxes = new ArrayList<Mailbox>();
	/** Default admin password */
	private int adminPassword = 789;
	/** Create GUI for input and output */
	GUI gui = new GUI();

	// ********** Constructor **********
	/** Construct a Voicemail System */
	public VoicemailSystem() {
		Mailbox.setGui(gui); /** Reuse the GUI in the Mailbox class */
	}

	/** Method to initiate the system */
	public void initiate() {
		while (true) { /** Keep running the voicemail system until user hangs up */
			gui.printString(
					"Welcome to the voicemail system!\n" + "Please enter the extension you are trying to reach");
			int extNum = gui.get3Keys();
			if (extNum == 000) { /** If extension is 000, proceed to admin menu */
				adminMenu(); /** Proceed to admin menu */
			} else { /** Find the mailbox and call it */
				Mailbox extMailbox = findMailbox(extNum); /** Get extension number and see if mailbox exists */
				if (extMailbox != null) { /** If the mailbox exists, call it */
					gui.printString("Dialing ...");
					callExtension(findMailbox(extNum)); /** Find the mailbox and call */
				} else { /** If the mailbox doesn't exist, play an error message */
					gui.printString("Sorry, that extention doesn't exist!");
				}

			}
			gui.printString("Good Bye!");
		}
	}

	/**
	 * Method to call a mailbox and speak to the mailbox owner. If owner is not
	 * present, the caller may leave a message. If the owner calls his own mailbox,
	 * he can manage his messages and greetings.
	 * 
	 * @param ext
	 * @precondition the mailbox ext must not be null
	 */
	private void callExtension(Mailbox ext) {
		assert ext != null : "Null mailbox"; /** Assert null mailboxes */
		if (ext.isPresent()) { /** If owner is there to pick up the call */
			gui.speak(); /** Call commences */
		} else if (!ext.isPresent()) { /** If owner is NOT there to pick up the call */
			gui.printString("Press 1 to leave a message. " + "Press 2 to manage mailbox. ");
			int option = gui.getKey(); /** Get the user's option */
			if (option == 1) {
				ext.leaveMessage();
			} else if (option == 2) {
				gui.printString("Enter mailbox password"); /** Request the mailbox password */
				if (ext.passwordCheck(gui.get3Keys())) { /** Get and check the mailbox password */
					mailboxManager(ext); /** If the password is correct, go to mailbox owner's menu */
				}
			} /** For invalid option, do nothing */
		}
	}

	/**
	 * Manage the messages (unread and saved) and the greeting object of a mailbox
	 * 
	 * @param ext is the mailbox to manage
	 * @precondition the mailbox ext must not be null
	 */
	private void mailboxManager(Mailbox ext) {
		assert ext != null : "Null mailbox"; /** Assert null mailboxes */
		gui.printString("Welcome to mailbox management. " + "Press 1 for messages. " + "Press 2 for greeting. ");
		int option = gui.getKey();/** Get the option from the user */
		if (option == 1) {
			gui.printString("Press 1 for unread messages. " + "Press 2 for saved messages. ");
			int messageOption = gui.getKey();
			if (messageOption == 1) {
				ext.unreadMessageManagement();
			} else if (messageOption == 2) {
				ext.savedMessageManagement();
			} /** For invalid message option number, do nothing */
		} else if (option == 2) {
			ext.greetingManagement(); /** Manage greetings */
		} /** For invalid option number, do nothing */
	}

	/** Admin menu which manages the mailboxes */
	private void adminMenu() {
		gui.printString("Please enter the voicemail system password");
		if (gui.get3Keys() == adminPassword) { /** If the password is correct, proceed */
			gui.printString("Welcome to the admin menu!"); /** Greet the user/admin */
			gui.printString("Enter 1 to add a mailbox\n" + "Enter 2 to remove a mailbox\n"
					+ "Enter 3 to change a mailbox password\n"
					+ "Enter 4 to reset voicemail system\n"); /** State options */
			int option = gui.getKey(); /** Get the option number */
			if (option == 1) {
				gui.printString("Enter the mailbox number to manage"); /** Ask for a mailbox to manage */
				int phoneNumber = gui.get3Keys(); /** Get the mailbox phone number */
				addMailbox(phoneNumber); /** Add a mailbox */
			} else if (option == 2) {
				gui.printString("Enter the mailbox number to manage"); /** Ask for a mailbox to manage */
				int phoneNumber = gui.get3Keys(); /** Get the mailbox phone number */
				removeMailbox(phoneNumber); /** Remove a mailbox */
			} else if (option == 3) { /** Change a password */
				gui.printString("Enter the mailbox number to manage"); /** Ask for a mailbox to manage */
				int phoneNumber = gui.get3Keys(); /** Get the mailbox phone number */
				gui.printString("Enter the new password");
				int pswd = gui.get3Keys(); /** Get a password */
				findMailbox(phoneNumber).setPassword(pswd); /** Change the password */
				gui.printString("Mailbox password successfully changed!");
			} else if (option == 4) { /** Deletes all the mailboxes/Resets the system */
				mailboxes.clear(); /** Remove all mailboxes */
				gui.printString("System has been reset!");
			}
		} else { /** For invalid option number, do nothing */
			gui.printString("Incorrect password");
		}
	}

	/**
	 * The administrator should be able to add multiple users to the system, each
	 * with their personal mailbox, password and extension number. The administrator
	 * should be able to set, reset and change all the users passwords.
	 * 
	 * @param phoneNumber is the phone number of the mailbox to add
	 * @precondition 000 <= phoneNumber <= 999
	 */
	private void addMailbox(int phoneNumber) { /** Add user if mailbox doesn't exist */
		assert 0 <= phoneNumber && phoneNumber <= 999 : "Invalid phone number"; /** phone number must be in range */
		if (findMailbox(phoneNumber) == null) { /** See if the mailbox exists. If it doesn't create it */
			gui.printString("Enter a new password for this mailbox");
			int password = gui.get3Keys(); /** Get a password for this new mailbox */
			Mailbox newMailbox = new Mailbox(phoneNumber, password); /** If mailbox doesn't exist, make it */
			mailboxes.add(newMailbox); /** Add mailbox to the list of mailboxes */
			Collections.sort(mailboxes, Mailbox.comparatorByPhoneNumber()); /** Sort the mailboxes in list */
			gui.printString("Mailbox successfully created and added!");
		} else { /** If the mailbox already exists */
			gui.printString("Error! Mailbox already exists!");
		}
	}

	/**
	 * Searches the ArrayList of mailboxes and removes a mailbox
	 * 
	 * @param phoneNumber is the phone number of the mailbox to remove
	 * @precondition 000 <= phoneNumber <= 999
	 */
	private void removeMailbox(int phoneNumber) { /** Remove mailbox */
		assert 0 <= phoneNumber && phoneNumber <= 999 : "Invalid phone number"; /** phone number must be in range */
		for (int i = 0; i < mailboxes.size(); i++) { /** Search mailboxes */
			if (mailboxes.get(i).getPhoneNumber() == phoneNumber) { /** If the phone number matches ext */
				mailboxes.remove(i); /** Remove the mailbox with phone number 'ext' at index i */
				Collections.sort(mailboxes, Mailbox.comparatorByPhoneNumber()); /** Sort the mailboxes in list */
			}
		}
		gui.printString("Mailbox successfully removed!");
	}

	/**
	 * Finds and returns the mailbox with a given phone number
	 * 
	 * @param ext is the phone number of the mailbox
	 * @return the mailbox with the phone number 'ext' or null
	 * @precondition 000 <= ext <= 999
	 * @postcondition Returns the mailbox if it exists. Otherwise returns null.
	 */
	private Mailbox findMailbox(int ext) {
		for (int i = 0; i < mailboxes.size(); i++) { /** Search all the mailboxes */
			if (mailboxes.get(i).getPhoneNumber() == ext) { /** If the phone number matches ext */
				return mailboxes.get(i); /** Return the mailbox with phone number 'ext' */
			}
		}
		return null; /** If the mailbox doesn't exist, return null */
	}

	/**
	 * 
	 * @return a comparator to sort based on the number of mailboxes
	 */
	public static Comparator<VoicemailSystem> comparatorByMailboxes() {
		return new Comparator<VoicemailSystem>() { // Make object of anonymous class
			public int compare(VoicemailSystem voicemailSystem1, VoicemailSystem voicemailSystem2) {
				return Integer.compare(voicemailSystem1.mailboxes.size(), voicemailSystem2.mailboxes.size());
			}
		};
	}
}