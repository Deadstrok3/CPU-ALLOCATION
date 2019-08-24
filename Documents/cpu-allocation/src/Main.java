import java.util.*;

public class Main {
    static Map<String,Map<String, Double>> costMap = new HashMap<String,Map<String, Double>>();

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of hours you want to use");
        int hours = sc.nextInt();
        System.out.println("Enter the number of cpu that you are gonna use");
        int numberOfCpu=sc.nextInt();
        Map<String,Map<String,Double>> instances = Util.insertIntoMap();
        System.out.println(instances);
        get_costs(instances,numberOfCpu,hours);
    }
    public static void get_costs(Map<String,Map<String,Double>> instances,int cpu,int price) {
        for(String name: instances.keySet()) {
            for (String pricePerCpu: instances.get(name).keySet()) {
                Double temp = instances.get(name).get(pricePerCpu);
                instances.get(name).put(pricePerCpu,temp*price);
            }
        }
        Map<String,Map<Double,Integer>> costPerCpu = new HashMap<String,Map<Double,Integer>>();
        Map<String,Map<String,Integer>> totalCost = new HashMap<String,Map<String,Integer>>();
        for(String name: instances.keySet()) {
            costPerCpu.put(name,new HashMap<Double,Integer>());
            List<Double> tempList= new ArrayList<Double>();
            for (String cpuName: instances.get(name).keySet()) {
                Double temp = instances.get(name).get(cpuName);
                int cpuCount = Util.instanceNames.get(cpuName);
                costPerCpu.get(name).put((temp/cpuCount),cpuCount);
                tempList.add(temp/cpuCount);
            }
            Collections.sort(tempList);
            int tempCpu=cpu;
            totalCost.put(name,new HashMap<String, Integer>());
            for(Double costly:tempList) {
                int currentCpuCount = costPerCpu.get(name).get(costly);
                if(tempCpu>0) {
                    if (currentCpuCount <= tempCpu) {
                        String currentCpuName = Util.allNames.get(currentCpuCount);
                        int number = tempCpu / currentCpuCount;
                        int remainingCpu = tempCpu % currentCpuCount;
                        tempCpu=remainingCpu;
                        totalCost.get(name).put(currentCpuName,number);
                    }
                }
            }
        }
        System.out.println(instances);
        System.out.println(costPerCpu);
        System.out.println(totalCost);
    }

}
