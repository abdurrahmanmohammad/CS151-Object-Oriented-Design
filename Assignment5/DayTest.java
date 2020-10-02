package day;

import junit.framework.*;

public class DayTest extends TestCase {

	public void test1dayFrom() {
		Day day = new Day(2019, 10, 6); /** The date is October 6, 2019 */
		Day day1 = new Day(2019, 10, 21); /** The date is October 21, 2019 */
		assertEquals(day.daysFrom(day1), 15); /** The difference is 15 days */
	}

	public void test2dayFrom() {
		Day day = new Day(1582, 10, 4); /** Date is 10/4/1582 */
		Day day1 = new Day(1582, 10, 15); /** Date is 10/15/1582 */
		assertEquals(day.daysFrom(day1), 1); /** The difference is be 1 day */
	}

	public void test3dayFrom() {
		Day day = new Day(2019, 10, 6); /** The date is October 6, 2019 */
		Day day1 = new Day(2019, 10, 1); /** The date is October 1, 2019 */
		assertEquals(day.daysFrom(day1), 5); /** The difference is 5 days (negative direction) */
	}

	public void addDays1() {
		Day day = new Day(2019, 10, 6); /** The date is October 6, 2019 */
		Day day1 = day.addDays(50); /** 50 days after 10/6/2019 is 11/25/2019 */
		assertEquals(day, day1); /** They should be the same objects */

	}

	public void addDays2() {
		Day day = new Day(1582, 10, 4); /** Date is 10/4/1582 */
		Day day1 = day.addDays(1); /** 1 day after 10/4/1582 is 10/15/1582 */
		assertEquals(day1.getDate(), "October 15, 1582"); /** Date should be 10/15/1582 */
	}

	public void getDate() { /** Test the getDate function */
		Day day = new Day(2019, 10, 6); /** The date is "October 6, 2019" */
		assertEquals(day.getDate(), "October 6, 2019");
	}

	public void getMonth() {
		Day day = new Day(2019, 10, 6); /** The month is 10 (October) */
		assertTrue(day.getMonth() == 10);
	}

	public void getDay() {
		Day day = new Day(2019, 10, 6); /** The date is "6"  */
		assertTrue(day.getDay() == 6);

	}

	public void getYear() {
		Day day = new Day(2019, 10, 6); /** The year is "2019" */
		assertTrue(day.getYear() == 2019);
	}

}
