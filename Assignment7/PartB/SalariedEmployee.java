
public class SalariedEmployee extends Employee {

	/**
	 * Constructs a SalariedEmployee
	 * 
	 * @param aName          is the name of the employee
	 * @param anAnnualSalary is the annual salary of the employee
	 */
	public SalariedEmployee(String aName, double anAnnualSalary) {
		super(aName);
		super.setSalary(anAnnualSalary);
	}

	/**
	 * @return the weekly salary of the employee
	 */
	public double getWeeklySalary() {
		/** Salaried employees are paid 1/52 of their annual salary every week */
		return super.getSalary() / 52; /** weeklySalary = anAnnualSalary x 1/52 */

	}
}
