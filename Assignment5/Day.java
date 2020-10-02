package day;

import java.lang.Math; // Absolute value is used

public class Day {
	/** A list of constants */
	/** There are 365 days in a non leap year */
	private static final int daysInYear = 365;
	/** There are 365 days in a leap year */
	private static final int daysInLeapYear = 366;
	/** Stores the string of month names */
	private static final String[] monthNames = { "January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December" };
	/** Number of days in each month for a non leap year */
	private static final int[] daysOfYear = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	/** Number of days in each month for a leap year */
	private static final int[] daysOfLeapYear = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	/** Immutable Instance variables */
	private final int month;
	private final int day;
	private final int year;

	/**
	 * Constructs a new Day object if the date is valid
	 * 
	 * @param year  an integer specifying the year
	 * @param month an integer specifying the month
	 * @param day   an integer specifying the day
	 * 
	 * @precondition The year must not be 0
	 * @precondition The month should be between 1 and 12 (corresponding to Jan -
	 *               Dec)
	 * @precondition The day should in range of its month (28, 29, 30, or 31
	 *               depending on the month)
	 * @precondition February 29 can only be created on a leap year (leap year
	 *               calculated according to the year and type of calendar)
	 * @precondition You cannot have a date after 10/4/1582 AND before 10/15/1582.
	 * @postcondition none
	 */
	public Day(int year, int month, int day) {
		assert !(year == 0) : "The year cannot be 0";
		assert checkMonth(month) : "The month should be between 1 and 12 (corresponding to Jan - Dec)";
		assert checkDay(year, month, day) : "The day is out of range of its month";
		assert !(!isLeapYear(year) && month == 2 && day > 28) : "The date is Feb 29 on non leap year";
		assert !(year == 1582 && month == 10 && (day > 4
				&& day < 15)) : "The date is between 10/4/1582 and 10/15/1582. Invalid date for the Gregorian calendar";

		/** If everything seems fine, proceed to create the object */
		this.year = year;
		this.month = month;
		this.day = day;
	}

	/**
	 * Calculates the difference between two dates
	 * 
	 * @param aDay is another date object with a valid date
	 * @return the difference between current date and another date
	 * @precondition none
	 * @postcondition none
	 */
	public int daysFrom(Day aDay) {
		int daysFromToday = 0; /** Records the number of days between 2 dates */
		Day selfDay = new Day(this.year, this.month, this.day); /** Convert self into a Day object */

		int date1 = convertToDays(aDay); /** Convert the target date into days */
		int date2 = convertToDays(selfDay); /** Convert self into days */

		/**
		 * Adjust for 1582. We need to add 10 days if the year is before 10/15/1582 to
		 * get correct the subtraction result
		 */
		// For the projected date
		if (aDay.getYear() < 1582) { /** If the year is before 1852 */
			date1 += 10;
		}
		if (aDay.getYear() == 1582 && aDay.getMonth() < 10) { /** If the year is 1852 and its before October */
			date1 += 10;
		}
		if (aDay.getYear() == 1582 && aDay.getMonth() == 10 && aDay.getDay() < 15) {
			/** If the year is 1852 and its before October 15 */
			date1 += 10;
		}
		// For the self date
		if (selfDay.getYear() < 1582) { /** If the year is before 1852 */
			date2 += 10;
		}
		if (selfDay.getYear() == 1582 && aDay.getMonth() < 10) { /** If the year is 1852 and its before October */
			date2 += 10;
		}
		if (selfDay.getYear() == 1582 && aDay.getMonth() == 10 && aDay.getDay() < 15) {
			/** If the year is 1852 and its before October 15 */
			date2 += 10;
		}

		/** Obtain the absolute value of (target - current) */
		daysFromToday = Math.abs(date1 - date2);
		/** If input is negative, we must adjust for negative year values */
		if (selfDay.getYear() < 0) {
			daysFromToday += 365;
		}

		return daysFromToday; // Return the difference
	}

	/**
	 * Returns the date after addX days from current date
	 * 
	 * @param addX is the number of days after or before this day
	 * @return the Day object with the date addX days from this date
	 * @precondition none
	 * @postcondition returns a valid date object
	 */
	public Day addDays(int addX) {
		Day selfDay = new Day(this.year, this.month, this.day); /** Convert self into a Day object */
		int selfDays = convertToDays(selfDay); /** Convert self into days */
		
		selfDays += addX; /** Add the days to self */

		/** Convert days into a proper date */
		int years = 1; /** Starts from year 1 */
		int months = 1; /** Starts from year 1 */
		int days = 1; /** Starts from day 1 */

		/**
		 * Extract years. We cannot be sure if the last day is the last the last day of
		 * the leap year, so 366
		 */
		while (selfDays > daysInLeapYear) {
			if (isLeapYear(years)) { /** If this year is leap year */
				selfDays -= daysInLeapYear;
				years++;
			} else { /** If this year is not leap year */
				selfDays -= daysInYear;
				years++;
			}
		}

		/** If 366 days are left over and it is a leap year */
		if (selfDays == daysInLeapYear && isLeapYear(years + 1)) {
			years++;
			return new Day(years, 1, 1);
		}
		/** If 365 days are left over and it is not a leap year */
		if (selfDays == daysInYear && !isLeapYear(years + 1)) {
			years++;
			return new Day(years, 1, 1);
		}

		/** Adjust for years after 1582. 10 days were skipped */
		if (years > 1852) {
			selfDays += 10;
		}

		/** Extract months */
		if (isLeapYear(years)) { /** If the current year is a leap year */
			while (selfDays > daysOfLeapYear[months - 1]) { // Month 0 is January
				selfDays -= daysOfLeapYear[months - 1];
				months++;
			}
		} else { /** If the current year is NOT a leap year */
			while (selfDays > daysOfYear[months - 1]) { // Month 0 is January
				selfDays -= daysOfYear[months - 1];
				months++;
			}
		}

		/** Adjust for months after October 1582. 10 days were skipped */
		if (years == 1852 && months > 10) {
			selfDays += 10;
		}

		/** Extract days */
		days = selfDays;

		/** Adjust for days after 4th October 1582. 10 days were skipped */
		if (years == 1582 && months == 10 && days > 4) {
			days += 10; /** Add 1 days to dates after 10/4/1582 */
		}
		
		//months = Math.abs(months); // Months and days cannot be negative
		//System.out.println(months);
		//months = Math.abs(days);

		return new Day(years, months, days); // Returning this object is okay since it is not part of the state
	}

	/**
	 * Converts a date into its corresponding number of days
	 * 
	 * @param aDay is the date as a Day object
	 * @return the date converted into its number of days
	 * @precondition none
	 * @postcondition none
	 */
	private int convertToDays(Day aDay) { // Takes care of negative years.
		/** Goal: Convert a date into days */
		int currentDate = 0; /** Stores the number of days */

		/** 1) Convert years to days. Stop at current year. */
		for (int i = 1; i < Math.abs(aDay.getYear()); i++) { /** Convert years to days. Stop at current year. */
			if (isLeapYear(i)) { /** If the year is a leap year */
				currentDate += daysInLeapYear; /** Add the number of days in a leap year */
			} else { /** If the year is not a leap year */
				currentDate += daysInYear; /** Add the number of days in a non leap year */
			}
		}

		/** 2) Convert months to days. Stop at current month. */
		if (isLeapYear(year)) { /** If current year is a leap year */
			for (int i = 1; i < aDay.getMonth(); i++) { /** Convert months to days. Stop at current month. */
				currentDate += daysOfLeapYear[i - 1]; /** Add the days in months of a leap year */
			}
		} else { /** If current year is not a leap year */
			for (int i = 1; i < aDay.getMonth(); i++) { /** Convert months to days. Stop at current month. */
				currentDate += daysOfYear[i - 1]; /** Add the days in months of a non leap year */
			}
		}

		/** Add the number of days */
		currentDate += aDay.getDay(); /** Add remaining days */

		/**
		 * 4) We added extra by considering leap year before 10/15/1852. Since 10 days
		 * were skipped, we must adjust for these 10 days.
		 */
		if (aDay.getYear() > 1582) { /** If the year is after 1852 */
			currentDate -= 10; /** 10 days were skipped on 10/4/1582 */
		}
		if (aDay.getYear() == 1582 && aDay.getMonth() > 10) { /** If the year is 1852 and its after October */
			currentDate -= 10; /** 10 days were skipped on 10/4/1582 */
		}
		if (aDay.getYear() == 1582 && aDay.getMonth() == 10 && aDay.getDay() > 4) {
			/** If the year is 1852 and its after October 4 */
			currentDate -= 10; /** 10 days were skipped on 10/4/1582 */
		}
		return currentDate;
	}

	/**
	 * Method to check if the month is valid. Helper method.
	 * 
	 * @param aMonth the month of the date to check
	 * @return a boolean value stating if the month is a valid month
	 * @precondition none
	 * @postcondition none
	 */
	private boolean checkMonth(int aMonth) {
		/** The month must be between 1 and 12, corresponding to the 12 months */
		if (aMonth >= 1 && aMonth <= 12) {
			return true;
		}
		return false; /** If the month is out of range, return false */
	}

	/**
	 * Method to check if the chosen day is valid. Helper method.
	 * 
	 * @param aMonth the month of the day
	 * @param aDay   the day to be checked
	 * @return a boolean value stating if the month is a valid month
	 * @precondition none
	 * @postcondition none
	 */
	private boolean checkDay(int year, int aMonth, int aDay) {
		/** Check to see if the day is in range of its month */
		if (isLeapYear(year)) { /** If the year is a leap year */
			if (daysOfLeapYear[aMonth - 1] >= aDay && aDay > 0) { // January at index 0
				return true;
			}
		} else {/** If the year is NOT a leap year */
			if (daysOfYear[aMonth - 1] >= aDay && aDay > 0) { // January at index 0
				return true;
			}
		}
		/**
		 * If the day is out of bounds (greater than the maximum days of its month or
		 * less than 0) or a leap year issue occurs
		 */
		return false;
	}

	/**
	 * Method to determine if a year is a leap year. Helper method.
	 * 
	 * @param aYear is the year to check to see if it is a leap year
	 * @return a boolean value stating if the year is a leap year
	 * @precondition none
	 * @postcondition none
	 */
	private boolean isLeapYear(int aYear) {
		if (aYear % 4 == 0) { /** Leap years are divisible by 4 */
			if (aYear < 1582) { /** If the year was before 1582 and divisible by 4, it is a leap year */
				return true;
			} else if (!(aYear % 100 == 0) || (aYear % 400 == 0)) {
				/**
				 * If the year was after 1582, the leap year must not be divisible by 100. If it
				 * divisible by 400, it is a leap year.
				 */
				return true;
			}
		}
		return false; /** If the year does not meet the criteria, return false */
	}

	// -------------------- Getters --------------------
	/**
	 * Returns the year, month, day as a string: month day, year
	 * 
	 * @return the date as a string
	 * @precondition none
	 * @postcondition none
	 */
	public String getDate() {
		return (monthNames[month - 1] + " " + day + ", " + year);
	}

	/**
	 * Returns the month of the date
	 * 
	 * @return the month of the date
	 * @precondition none
	 * @postcondition none
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Returns the day of the date
	 * 
	 * @return the day of the date
	 * @precondition none
	 * @postcondition none
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Returns the year of the date
	 * 
	 * @return the year of the date
	 * @precondition none
	 * @postcondition none
	 */
	public int getYear() {
		return year;
	}

}
