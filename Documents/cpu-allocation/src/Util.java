import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.*;

@SuppressWarnings("serial")
public class Util {
	static List<String> cpuNames = new ArrayList<String>();
	static Map<String, Integer> instanceNames;
	static Map<Integer, String> allNames;
	static List<CustomTuple> results = new ArrayList<CustomTuple>();
	static {
		instanceNames = new HashMap<String, Integer>() {
			{
				put("large", 1);
				put("xlarge", 2);
				put("2xlarge", 4);
				put("4xlarge", 8);
				put("8xlarge", 16);
				put("10xlarge", 32);
			}
		};
		allNames = new HashMap<Integer, String>() {
			{
				put(1, "large");
				put(2, "xlarge");
				put(4, "2xlarge");
				put(8, "4xlarge");
				put(16, "8xlarge");
				put(32, "10xlarge");
			}
		};
		Collections.addAll(cpuNames, "large", "xlarge", "2xlarge", "4xlarge", "8xlarge", "10xlarge");
	}

	public static Map<String, Map<String, Double>> insertIntoMap() {
		Map<String, Map<String, Double>> cost = new HashMap<String, Map<String, Double>>();

		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("enter 1 to insert a datacenter 2 to stop");
			int temp = scanner.nextInt();
			scanner.nextLine();
			if (temp == 2) {
				break;
			} else {
				System.out.println("Enter the name of the data center");
				String centreName = scanner.nextLine();
				cost.put(centreName, new HashMap<String, Double>());
				for (String names : cpuNames) {
					System.out.println("Enter the cost of " + names + " if available or else press -1");
					Double cpuCost = scanner.nextDouble();
					if (cpuCost > 0) {
						cost.get(centreName).put(names, cpuCost);
					}
				}
			}
		}
		return cost;
	}

}
