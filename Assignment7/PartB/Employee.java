
/** Copy of the Employee class in lecture 13 slide 3 */
public class Employee {
	// ********** Instance Variables **********
	/** Stores the name of the employee */
	private String name;
	/** Stores the salary of the emplyee */
	private double salary;

	// ********** Constructor **********
	/**
	 * Constructs an Employee object
	 * 
	 * @param aName   is the name of the employee
	 * @param aSalary is the salary of the employee
	 */
	public Employee(String aName) {
		name = aName;
	}

	// ********** Getters and Setters **********
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the salary
	 */
	public double getSalary() {
		return salary;
	}

	public void setSalary(double aSalary) {
		salary = aSalary;
	}
}
