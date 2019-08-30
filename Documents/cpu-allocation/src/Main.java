import java.util.*;

public class Main {
	static Map<String, Map<String, Double>> costMap = new HashMap<String, Map<String, Double>>();

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number of hours you want to use");
		int hours = scanner.nextInt();
		System.out.println("Enter the number of cpu that you are gonna use");
		int numberOfCpu = scanner.nextInt();
		System.out.println("Enter the maximum amount you can spend");
		double price = scanner.nextDouble();
		Map<String, Map<String, Double>> instances = Util.insertIntoMap();
		System.out.println(instances);
		System.out.println(get_costs(instances, numberOfCpu, hours, price));
	}

	public static String get_costs(Map<String, Map<String, Double>> instances, int cpu, int hours, double price) {
		List<CustomTuple> tupleList = null;
		if((cpu>0)&&(hours>0)&&(price>0)) {
			tupleList=getInstanceForGivenCPUH(instances, cpu, hours, price);
		} else if((cpu>0)&&(hours>0)) {
			tupleList=getCostGivenCPUH(instances, cpu, hours);
		} else if((hours>0)&&(price>0)) {
			tupleList=getCPUGivenCostH(instances, hours, price);
		} else {
			return("Enter valid system Inputs");
		}
		if(tupleList==null||tupleList.size()==0) {
			return("No CPU match given criteria");
		} else {
			return (tupleList.toString());
		}
	}

	public static List<CustomTuple> getCPUGivenCostH(Map<String, Map<String, Double>> instances, int hours,
			double price) {
		for (String name : instances.keySet()) {
			for (String pricePerCpu : instances.get(name).keySet()) {
				Double temp = instances.get(name).get(pricePerCpu);
				instances.get(name).put(pricePerCpu, temp * hours);
			}
		}
		Map<String, Map<Double, Integer>> costPerCpu = new HashMap<String, Map<Double, Integer>>();
		Map<String, Map<String, Integer>> totalCost = new HashMap<String, Map<String, Integer>>();
		for (String name : instances.keySet()) {
			List<Double> tempList = new ArrayList<Double>();
			Map<Double, String> tempMap = new HashMap<Double, String>();
			costPerCpu.put(name, new HashMap<Double, Integer>());
			for (String instanceName : instances.get(name).keySet()) {
				int cpuCount = Util.instanceNames.get(instanceName);
				Double temp = instances.get(name).get(instanceName);
				costPerCpu.get(name).put((temp / cpuCount), cpuCount);
				tempList.add(temp/cpuCount);
				tempMap.put(temp, instanceName);
			}
			
			totalCost.put(name, new HashMap<String, Integer>());
			Collections.sort(tempList);
			CustomTuple tupule = new CustomTuple();
			tupule.setServerRegion(name);
			Double tempPrice = price;
			double totalServerCost = 0;
			for (Double instancePrice : tempList) {
				int currentCpuCount = costPerCpu.get(name).get(instancePrice);
				if (tempPrice > 0) {
					String instanceName = Util.allNames.get(currentCpuCount);
					int numberOfInstances = (int) (tempPrice / instances.get(name).get(instanceName));
					tempPrice = tempPrice % instances.get(name).get(instanceName);
					totalCost.get(name).put(instanceName, numberOfInstances);
					totalServerCost = totalServerCost + (numberOfInstances * instances.get(name).get(instanceName));
				}
			}
			tupule.setCost(totalServerCost);
			tupule.setServers(totalCost.get(name));
			Util.results.add(tupule);
		}
		return Util.results;
	}

	public static List<CustomTuple> getCostGivenCPUH(Map<String, Map<String, Double>> instances, int cpu, int hours) {
		for (String name : instances.keySet()) {
			for (String pricePerCpu : instances.get(name).keySet()) {
				Double temp = instances.get(name).get(pricePerCpu);
				instances.get(name).put(pricePerCpu, temp * hours);
			}
		}

		Map<String, Map<Double, Integer>> costPerCpu = new HashMap<String, Map<Double, Integer>>();
		Map<String, Map<String, Integer>> totalCost = new HashMap<String, Map<String, Integer>>();

		for (String name : instances.keySet()) {

			costPerCpu.put(name, new HashMap<Double, Integer>());
			List<Double> tempList = new ArrayList<Double>();

			for (String instanceName : instances.get(name).keySet()) {
				Double temp = instances.get(name).get(instanceName);
				int cpuCount = Util.instanceNames.get(instanceName);
				costPerCpu.get(name).put((temp / cpuCount), cpuCount);
				tempList.add(temp / cpuCount);
			}

			Collections.sort(tempList);
			int tempCpu = cpu;
			totalCost.put(name, new HashMap<String, Integer>());
			CustomTuple tuple = new CustomTuple();
			tuple.setServerRegion(name);
			double totalServerCost = 0;
			for (Double costly : tempList) {
				int currentCpuCount = costPerCpu.get(name).get(costly);
				if (tempCpu > 0) {
					if (currentCpuCount <= tempCpu) {
						String currentCpuName = Util.allNames.get(currentCpuCount);
						int number = tempCpu / currentCpuCount;
						totalServerCost = totalServerCost + (instances.get(name).get(currentCpuName) * number);
						int remainingCpu = tempCpu % currentCpuCount;
						tempCpu = remainingCpu;
						totalCost.get(name).put(currentCpuName, number);
					}
				}
			}

			tuple.setCost(totalServerCost);
			tuple.setServers(totalCost.get(name));
			Util.results.add(tuple);

		}
		Collections.sort(Util.results);
		return Util.results;
	}

	public static List<CustomTuple> getInstanceForGivenCPUH(Map<String, Map<String, Double>> instances, int cpu,
			int hours, double price) {
		for (String name : instances.keySet()) {
			for (String pricePerCpu : instances.get(name).keySet()) {
				Double temp = instances.get(name).get(pricePerCpu);
				instances.get(name).put(pricePerCpu, temp * hours);
			}
		}

		Map<String, Map<Double, Integer>> costPerCpu = new HashMap<String, Map<Double, Integer>>();
		Map<String, Map<String, Integer>> totalCost = new HashMap<String, Map<String, Integer>>();

		for (String name : instances.keySet()) {

			costPerCpu.put(name, new HashMap<Double, Integer>());
			List<Double> tempList = new ArrayList<Double>();

			for (String instanceName : instances.get(name).keySet()) {
				Double temp = instances.get(name).get(instanceName);
				int cpuCount = Util.instanceNames.get(instanceName);
				costPerCpu.get(name).put((temp / cpuCount), cpuCount);
				tempList.add(temp / cpuCount);
			}

			Collections.sort(tempList);
			int tempCpu = cpu;
			totalCost.put(name, new HashMap<String, Integer>());
			CustomTuple tuple = new CustomTuple();
			tuple.setServerRegion(name);
			double totalServerCost = 0;
			for (Double costly : tempList) {
				int currentCpuCount = costPerCpu.get(name).get(costly);
				if (tempCpu > 0) {
					if (currentCpuCount <= tempCpu) {
						String currentCpuName = Util.allNames.get(currentCpuCount);
						int number = tempCpu / currentCpuCount;
						totalServerCost = totalServerCost + (instances.get(name).get(currentCpuName) * number);
						int remainingCpu = tempCpu % currentCpuCount;
						tempCpu = remainingCpu;
						totalCost.get(name).put(currentCpuName, number);
					}
				}
			}

			tuple.setCost(totalServerCost);
			tuple.setServers(totalCost.get(name));
			if (totalServerCost <= price) {
				Util.results.add(tuple);
			}

		}
		Collections.sort(Util.results);
		return Util.results;
	}

}
