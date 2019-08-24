import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.*;
public class Util {
    static List<String> cpuNames = new ArrayList<String>();
    static Map<String,Integer> instanceNames = new HashMap<String,Integer>();
    static Map<Integer,String> allNames = new HashMap<Integer,String>();
    static List<CustomTuple> results = new ArrayList<CustomTuple>();
    static {
        cpuNames.add("large");
        cpuNames.add("xlarge");
        cpuNames.add("2xlarge");
        cpuNames.add("4xlarge");
        cpuNames.add("8xlarge");
        cpuNames.add("10xlarge");
        instanceNames.put("large",1);
        instanceNames.put("xlarge",2);
        instanceNames.put("2xlarge",4);
        instanceNames.put("4xlarge",8);
        instanceNames.put("8xlarge",16);
        instanceNames.put("10xlarge",32);
        allNames.put(1,"large");
        allNames.put(2,"xlarge");
        allNames.put(4,"2xlarge");
        allNames.put(8,"4xlarge");
        allNames.put(16,"8xlarge");
        allNames.put(32,"10xlarge");
    }
  public static Map<String,Map<String,Double>> insertIntoMap() {
      Map<String,Map<String,Double>> cost = new HashMap<String,Map<String,Double>>();

    Scanner sc = new Scanner(System.in);
    while(true) {
        System.out.println("enter 1 to insert a datacenter 2 to stop");
        int temp = sc.nextInt();
        sc.nextLine();
        if(temp==2) {
            break;
        } else {
            System.out.println("Enter the name of the data center");
            String centreName=sc.nextLine();
            cost.put(centreName,new HashMap<String, Double>());
            for (String names:cpuNames) {
                System.out.println("Enter the cost of "+names+" if available or else press -1");
                Double cpuCost=sc.nextDouble();
                if(cpuCost>0) {
                    cost.get(centreName).put(names,cpuCost);
                }
            }
        }
    }
    return cost;
  }

}
