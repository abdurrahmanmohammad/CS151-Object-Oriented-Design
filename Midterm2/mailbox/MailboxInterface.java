package mailbox;

/**
 * Interface to implement a basic phone line's mailbox
 * 
 * @author Abdurrahman
 */
public interface MailboxInterface {
	/** Lets the user leave a message */
	public void leaveMessage();

	/** Lets the user check the unread messages */
	public void unreadMessageManagement();

	/** Lets the user check the saved messages */
	public void savedMessageManagement();

	/** Lets the user add, remove, change, and set default mailbox greeting */
	public void greetingManagement();

	/** Returns the mailbox's phone number */
	public int getPhoneNumber();

	/** Lets the user check the password before accessing the mailbox */
	public boolean passwordCheck(int pswd);

	/** Sets the mailbox's password */
	public void setPassword(int pswd); /** Password format, range, and length is determined by the user */
}
