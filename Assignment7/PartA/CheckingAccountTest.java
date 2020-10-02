
public class CheckingAccountTest {

	public static void main(String[] args) {
		/** Create a checking account with an initial balance of $100 */
		CheckingAccount checkingAccount = new CheckingAccount(100);
		for (int i = 0; i < 5; i++) { /** Deposit $5 5 times */
			checkingAccount.deposit(5);
			System.out.println("Value after $5 deposit: $" + checkingAccount.getBalance());
		}
		/** Print current balance: should be $125 */
		System.out.println("Value after 5 free transactions (deposit of $5): " + checkingAccount.getBalance());
		for (int i = 0; i < 5; i++) { /** Withdraw $5 5 times */
			checkingAccount.withdraw(5); /** Deposit $5 5 times */
			System.out.println("Value after $5 withdrawl and $1 transaction fee: $" + checkingAccount.getBalance());
		}
		/** After an additional 5 transactions with fees, the total should be $95 */
		System.out.println("Value after 5 transactions with fees (withdrawl of $5): " + checkingAccount.getBalance());
	}

}
