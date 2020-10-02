
public class EmployeeTest {

	public static void main(String[] args) {
		/** Test the Employee class */
		Employee employee = new Employee("Jeff");
		employee.setSalary(100);
		System.out.println("Employee's name: " + employee.getName());
		System.out.println("Employee's salary: " + employee.getSalary());

		/** Test the HourlyEmployee class */
		HourlyEmployee hourlyEmployee = new HourlyEmployee("Bob", 20);
		System.out.println("\nEmployee's name: " + hourlyEmployee.getName());
		System.out.println("Employee's hourly salary: " + hourlyEmployee.getSalary());
		System.out.println("Employee's weekly salary: " + hourlyEmployee.getWeeklySalary()); /** 40 * 20 = 800 */

		/** Test the SalariedEmployee class */
		SalariedEmployee salariedEmployee = new SalariedEmployee("Bob", 5200);
		System.out.println("\nEmployee's name: " + salariedEmployee.getName());
		System.out.println("Employee's annual salary: " + salariedEmployee.getSalary());
		System.out.println("Employee's weekly salary: " + salariedEmployee.getWeeklySalary()); /** 5200 * 52 = 100 */
	}

}
