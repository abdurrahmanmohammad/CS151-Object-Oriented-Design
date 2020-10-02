package day;
import junit.framework.*;
public class DayTester extends TestCase {

	public static void main(String[] args) throws DayException {
		// Assertions are enabled for this class
		// These are my personal test cases. I have used JUnit in DayTest
		Day day = new Day(2019, 10, 6);
		System.out.println("Todays date is: " + day.getDate()); // Prints out the current date
		Day day1 = new Day(2019, 10, 21);
		// The difference between 10/6/2019 and 10/21/2019. Should be 15 days.
		System.out.println("The difference between " + day.getDate() + " and " + day1.getDate() + " is "
				+ day.daysFrom(day1) + " days");
		Day day2 = day.addDays(50);
		// 50 days after 10/6/2019 is 11/25/2019
		System.out.println("50 days after " + day.getDate() + " is " + day2.getDate());
		Day endOfYear = new Day(2020, 1, 1);
		// It should print out 87 days. There are 87 days between 10/6/2019 and 1/1/2020
		System.out.println("Until the end of this year, we have: " + day.daysFrom(endOfYear) + " days");
		// The difference between 10/21/2019 and 10/6/2019. Should also be 15 days (backwards).
		System.out.println("The difference between " + day1.getDate() + " and " + day.getDate() + " is "
				+ day.daysFrom(day1) + " days");
		
		Day day3 = new Day(-3, 1, 1);
		Day day4 = new Day(1, 1 ,1);
		// Difference should be: 365 * 3 = 1095
		System.out.println("The difference between " + day3.getDate() + " and " + day4.getDate() + " is "
				+ day3.daysFrom(day4) + " days");
		
		Day day5 = new Day(1582, 10, 4); // Date is 10/4/1582
		Day day6 = day5.addDays(1); // 1 day after 10/4/1582
		System.out.println(day6.getDate()); // Date should be 10/15/1582

	}

}
