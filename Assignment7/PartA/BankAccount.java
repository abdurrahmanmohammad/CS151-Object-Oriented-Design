public class BankAccount {
	/** Keeps track of the balance in the back account */
	private double balance;

	/**
	 * Method to deposit an amount from the bank account
	 * 
	 * @param amount the amount to deposit
	 */
	public void deposit(double amount) {
		balance += amount;
	}

	/**
	 * Method to withdraw an amount from the bank account
	 * 
	 * @param amount the amount to withdraw
	 */
	public void withdraw(double amount) {
		balance -= amount;
	}

	/**
	 * Method to get the balance
	 * 
	 * @return the balance of the bank account
	 */
	public double getBalance() {
		return balance;
	}
}
