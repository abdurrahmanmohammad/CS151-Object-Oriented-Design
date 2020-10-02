package laptop;
import java.util.Comparator;

public class Laptop {

	/** ---------- Instance Variables ---------- */
	/** CPU speed in gigahertz */
	private final double CPU_Benchmark_Results;
	/** GPU is rated on a scale from 1 (bad) to 10 (good) */
	private final int GPU_Benchmark_Results;
	/** Price is in USD */
	private final int price;
	/** Battery life is in hours */
	private final int batteryLife;
	/** Corresponds to the string at index in the array 'categories' form 0 to 4 */
	private final int category;

	/** ---------- Constants ---------- */
	private static final String[] categories = { "Very serious laptop", "Gaming laptop", "Overpriced laptop",
			"Featherweight laptop", "Indestructible laptop" };

	/** ---------- Constructor ---------- */
	/**
	 * Constructs a Laptop object
	 * 
	 * @param CPU_Benchmark_Results
	 * @param GPU_Benchmark_Results
	 * @param price
	 * @param batteryLife
	 * @param category
	 * @precondition 0 < CPU_Benchmark_Results <= 10
	 * @precondition 0 < GPU_Benchmark_Results <= 10
	 * @precondition 0 <= price
	 * @precondition 0 <= batteryLife
	 * @precondition 0 <= category <= 5
	 */
	public Laptop(double CPU_Benchmark_Results, int GPU_Benchmark_Results, int price, int batteryLife, int category) {
		/** Check for invalid input */
		assert ((0 < CPU_Benchmark_Results) && (CPU_Benchmark_Results <= 10)) : "CPU_Benchmark_Results is out of range";
		assert ((0 < GPU_Benchmark_Results) && (GPU_Benchmark_Results <= 10)) : "GPU_Benchmark_Results is out of range";
		assert (0 <= price) : "price is negative";
		assert ((0 <= batteryLife)) : "batteryLife is less that 0 hours";
		assert ((0 <= category) && (category <= 4)) : "category is out of range";
		/** Assign valid input to the instance variables */
		this.CPU_Benchmark_Results = CPU_Benchmark_Results;
		this.GPU_Benchmark_Results = GPU_Benchmark_Results;
		this.price = price;
		this.batteryLife = batteryLife;
		this.category = category;
	}

	/** ---------- Compare method ---------- */
	/**
	 * 
	 * @return a Comparator that compares laptops by CPU_Benchmark_Results
	 */
	public static Comparator<Laptop> comparatorByCPU_Benchmark_Results() {
		return new Comparator<Laptop>() { // Make object of anonymous class
			public int compare(Laptop laptop1, Laptop laptop2) {
				return Double.compare(laptop1.getCPU_Benchmark_Results(), laptop2.getCPU_Benchmark_Results());
			}
		};
	}

	/**
	 * 
	 * @return a Comparator that compares laptops by GPU_Benchmark_Results
	 */
	public static Comparator<Laptop> comparatorByGPU_Benchmark_Results() {
		return new Comparator<Laptop>() { // Make object of anonymous class
			public int compare(Laptop laptop1, Laptop laptop2) {
				return Integer.compare(laptop1.getGPU_Benchmark_Results(), laptop2.getGPU_Benchmark_Results());
			}
		};
	}

	/**
	 * 
	 * @return a Comparator that compares laptops by price
	 */
	public static Comparator<Laptop> comparatorByPrice() {
		return new Comparator<Laptop>() { // Make object of anonymous class
			public int compare(Laptop laptop1, Laptop laptop2) {
				return Integer.compare(laptop1.getPrice(), laptop2.getPrice());
			}
		};
	}

	/**
	 * 
	 * @return a Comparator that compares laptops by battery life
	 */
	public static Comparator<Laptop> comparatorByBatteryLife() {
		return new Comparator<Laptop>() { // Make object of anonymous class
			public int compare(Laptop laptop1, Laptop laptop2) {
				return Integer.compare(laptop1.getBatteryLife(), laptop2.getBatteryLife());
			}
		};
	}

	/**
	 * 
	 * @return a Comparator that compares laptops by category
	 */
	public static Comparator<Laptop> comparatorByCategory() {
		return new Comparator<Laptop>() { // Make object of anonymous class
			public int compare(Laptop laptop1, Laptop laptop2) {
				return laptop1.getCategory().compareTo(laptop2.getCategory());
			}
		};
	}

	/** ---------- Getters ---------- */
	/**
	 * @return the CPU_Benchmark_Results
	 */
	public double getCPU_Benchmark_Results() {
		return CPU_Benchmark_Results;
	}

	/**
	 * @return the GPU_Benchmark_Results
	 */
	public int getGPU_Benchmark_Results() {
		return GPU_Benchmark_Results;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @return the batteryLife
	 */
	public int getBatteryLife() {
		return batteryLife;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return categories[category];
	}
}
