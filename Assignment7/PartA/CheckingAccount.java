import java.util.Calendar;

public class CheckingAccount extends BankAccount {
	// ********** Instance Variables **********
	/** Counts the number of transactions before deducting fees */
	private int transactionCount;
	/** Records the date of the last transaction */
	private Calendar lastTransaction;

	// ********** Constructor **********
	/**
	 * Constructs a checking account
	 * 
	 * @param initialBalance is the initial balance of the account
	 */
	CheckingAccount(double initialBalance) {
		lastTransaction = Calendar.getInstance(); /** Update lastTransaction date with current value */
		super.deposit(initialBalance); /** Deposit this amount into the bank account */
	}

	// ********** Methods **********
	/**
	 * Method to deduct fees and reset transaction count after 5 free transactions
	 * per month are used up. If not, increment transaction count and last
	 * transaction date.
	 */
	private void deductFees() {
		Calendar currentDate = Calendar.getInstance(); /** get the current date */
		/**
		 * If the transactions in the same month of the same year add up to 5 or more,
		 * deduct the fee of $1 per transaction
		 */
		if ((transactionCount >= 5) && (lastTransaction.get(Calendar.MONTH)) == currentDate.get(Calendar.MONTH)
				&& (lastTransaction.get(Calendar.YEAR)) == currentDate.get(Calendar.YEAR)) {
			super.withdraw(1); /** Deduct the fees (fees = $1) */
		} else if ((transactionCount >= 5) && (lastTransaction.get(Calendar.MONTH)) != currentDate
				.get(Calendar.MONTH)) { /** If the transactions total 5 or more but the month has passed */
			transactionCount = 0; /** Reset the transaction count */
			lastTransaction = currentDate; /** Update the date of the last transaction */
		}
		transactionCount++; /** Increment transaction count after the transaction */
	}

	/**
	 * Method to deposit an amount
	 */
	public void deposit(double amount) {
		super.deposit(amount);
		deductFees(); /** Check if you need to deduct fees */
	}

	/**
	 * Method to withdraw an amount
	 */
	public void withdraw(double amount) {
		super.withdraw(amount);
		deductFees(); /** Check if you need to deduct fees */
	}

	// ********** Getter **********
	/**
	 * Method to return the balance of the bank account
	 * 
	 * @return the balance of the bank account
	 */
	public double getBalance() {
		return super.getBalance();
	}

}
