
public class HourlyEmployee extends Employee {

	/**
	 * Constructs an HourlyEmployee
	 * 
	 * @param aName          is the name of the employee
	 * @param anHourlySalary is the hourly salary of the employee
	 */
	public HourlyEmployee(String aName, double anHourlySalary) {
		super(aName);
		super.setSalary(anHourlySalary);
	}

	/**
	 * @return the weekly salary of the employee
	 */
	public double getWeeklySalary() {
		double hoursPerWeek = 40; /** Assume that hourly employees work 40 hours per week */
		return super.getSalary() * hoursPerWeek; /** weeklySalary = anHourlySalary x hoursPerWeek */

	}
}
