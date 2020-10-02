package summation;

/**
 * A class that computes, prints, and returns the sum of integers from 1 to 100
 * and the sum of integers from 100 to 1000.
 * 
 * @author abdurrahman
 *
 */
public class Summation {
	// -------------------- Instance Variables --------------------
	/** Stores the sum of integers from 1 to 100 inclusive */
	private static int sum1To100;
	/** Stores the sum of integers from 100 to 1000 inclusive */
	private static int sum100To1000;

	/**
	 * Constructs a Summation object and initializes the instance variables
	 * sum1To100 and sum100To1000. It computes and stores the sum of integers from 1
	 * to 100 in sum1To100 and it computes and stores the sum of integers from 100
	 * to 1000 in sum100To1000.
	 */
	public Summation() {
		for (int i = 1; i < 101; i++) { // Run the loop from numbers 1 to 100 inclusive
			sum1To100 += i; // Keep on adding the iterations to store sum
		}
		for (int i = 100; i < 1001; i++) { // Run the loop from numbers 100 to 1000 inclusive
			sum100To1000 += i; // Keep on adding the iterations to store sum
		}
	}
	// -------------------- Print Methods --------------------
	/** Prints the sum of integers from 1 to 100 inclusive */
	public static void printSum1To100() {
		System.out.printf("%d", sum1To100);
	}
	/** Prints the sum of integers from 100 to 1000 inclusive */
	public static void printSum100To1000() {
		System.out.printf("%d", sum100To1000);
	}
	
	// -------------------- Getters --------------------
	// It is not right to include setters for this program.
	/**
	 * Returns the sum of integers from 1 to 100 inclusive
	 * 
	 * @return the integer sum of integers from 1 to 100 inclusive
	 */
	public static int getSum1To100() {
		return sum1To100;
	}

	/**
	 * Returns the sum of integers from 100 to 1000 inclusive
	 * 
	 * @return the integer sum of integers from 100 to 1000 inclusive
	 */
	public static int getSum100To1000() {
		return sum100To1000;
	}
}
