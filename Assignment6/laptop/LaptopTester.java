package laptop;

import java.util.ArrayList;
import java.util.Collections;

public class LaptopTester {
	// This is my personal testing class
	public static void main(String[] args) {

		// Create an ArrayList
		ArrayList<Laptop> laptopList = new ArrayList<Laptop>();

		// Create many laptop objects
		Laptop laptop1 = new Laptop(3.7, 8, 800, 5, 2); // 3.7 GHz laptop, GPU rate 8, $800, 5hr battery, Overpriced
														// Laptop
		Laptop laptop2 = new Laptop(2.4, 6, 400, 7, 0); // 2.4 GHz laptop, GPU rate 6, $400, 7hr battery, Very serious
														// laptop
		Laptop laptop3 = new Laptop(3.2, 4, 600, 3, 4); // 3.2 GHz laptop, GPU rate 4, $600, 3hr battery, Indestructible
														// laptop
		Laptop laptop4 = new Laptop(3.5, 5, 700, 4, 3); // 3.5 GHz laptop, GPU rate 5, $700, 4hr battery, Featherweight
														// laptop
		Laptop laptop5 = new Laptop(3.8, 8, 900, 9, 1); // 3.8 GHz laptop, GPU rate 8, $900, 9hr battery, Gaming laptop
		Laptop laptop6 = new Laptop(3.1, 7, 900, 7, 2); // 3.1 GHz laptop, GPU rate 7, $900, 7hr battery, Overpriced
														// laptop
		Laptop laptop7 = new Laptop(3.4, 6, 700, 9, 0); // 3.4 GHz laptop, GPU rate 6, $700, 9hr battery, Very serious
														// laptop
		Laptop laptop8 = new Laptop(3.5, 3, 600, 6, 4); // 3.5 GHz laptop, GPU rate 3, $600, 6hr battery, Indestructible
														// laptop
		Laptop laptop9 = new Laptop(3.6, 6, 800, 6, 3); // 3.6 GHz laptop, GPU rate 6, $800, 6hr battery, Featherweight
														// laptop
		Laptop laptop10 = new Laptop(3.9, 9, 1200, 12, 1); // 3.9 GHz laptop, GPU rate 9, $1200, 12hr battery, Gaming
															// laptop

		// Add laptop objects to the ArrayList
		laptopList.add(laptop1);
		laptopList.add(laptop2);
		laptopList.add(laptop3);
		laptopList.add(laptop4);
		laptopList.add(laptop5);
		laptopList.add(laptop6);
		laptopList.add(laptop7);
		laptopList.add(laptop8);
		laptopList.add(laptop9);
		laptopList.add(laptop10);

		// Sort the ArrayList by CPU_Benchmark_Results
		Collections.sort(laptopList, Laptop.comparatorByCPU_Benchmark_Results());
		System.out.println("\nSort by CPU speed:");
		printLaptopList(laptopList); // Print the list

		// Sort the ArrayList by GPU_Benchmark_Results
		Collections.sort(laptopList, Laptop.comparatorByGPU_Benchmark_Results());
		System.out.println("\nSort by GPU rating:");
		printLaptopList(laptopList); // Print the list

		// Sort the ArrayList by price
		Collections.sort(laptopList, Laptop.comparatorByPrice());
		System.out.println("\nSort by price:");
		printLaptopList(laptopList); // Print the list

		// Sort the ArrayList by battery life
		Collections.sort(laptopList, Laptop.comparatorByBatteryLife());
		System.out.println("\nSort by battery life:");
		printLaptopList(laptopList); // Print the list

		// Sort the ArrayList by category
		Collections.sort(laptopList, Laptop.comparatorByCategory());
		System.out.println("\nSort by category:");
		printLaptopList(laptopList); // Print the list
	}

	// Method to print an ArrayList of laptops
	public static void printLaptopList(ArrayList<Laptop> laptopList) { // Pass in an ArrayList of laptops
		// Print the list
		for (int i = 0; i < laptopList.size(); i++) {
			// Print out a laptop
			System.out.printf("Laptop %d: ", i); // Laptop i
			System.out.printf("CPU speed %.2f GHz, ", laptopList.get(i).getCPU_Benchmark_Results());
			System.out.printf("GPU rate %d/10, ", laptopList.get(i).getGPU_Benchmark_Results()); // GPU rate (from 1 to
																									// 10)
			System.out.printf("Price $%d, ", laptopList.get(i).getPrice()); // Price
			System.out.printf("Battery Life %dhr, ", laptopList.get(i).getBatteryLife()); // Battery life
			System.out.printf("%s\n", laptopList.get(i).getCategory()); // Category
		}
	}
}
