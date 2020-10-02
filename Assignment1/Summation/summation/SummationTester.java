package summation;

public class SummationTester {

	public static void main(String[] args) {
		Summation sum = new Summation(); // Create a Summation object
		int sumFrom1To100 = 5050; // The sum from 1 to 100 is 5050
		int sumFrom100To1000 = 495550; // The sum from 1 to 100 is 495550

		// Test 1: Test the get method of the class Summation for sum1To100
		if (sum.getSum1To100() == sumFrom1To100) { // If both are equal, test passed
			System.out.printf("Test 1: Passed\n");
		} else {
			System.out.printf("Test 1: Failed\n");
		}
		// Test 2: Test the get method of the class Summation for sum1To100
		if (sum.getSum100To1000() == sumFrom100To1000) { // If both are equal, test passed
			System.out.printf("Test 2: Passed\n");
		} else {
			System.out.printf("Test 2: Failed\n");
		}
		// Test 3: Test the get method of the class Summation for sum1To100
		if (Summation.getSum1To100() == sumFrom1To100) { // If both are equal, test passed
			System.out.printf("Test 3: Passed\n");
		} else {
			System.out.printf("Test 3: Failed\n");
		}
		// Test 4: Test the get method of the class Summation for sum1To100
		if (Summation.getSum100To1000() == sumFrom100To1000) { // If both are equal, test passed
			System.out.printf("Test 4: Passed\n");
		} else {
			System.out.printf("Test 4: Failed\n");
		}
		// Test 5: Test the print method of the class Summation for sum1To100
		Summation.printSum1To100();
		System.out.printf(" - Test 5: Passed\n"); // If the program reaches here, method works
		// Test 6: Test the print method of the class Summation for sum100To1000
		Summation.printSum100To1000();
		System.out.printf(" - Test 6: Passed\n"); // If the program reaches here, method works
	}

}
