import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomTuple {
    private String serverRegion;
    private String cost;
    private Map<String, List<String>> servers=new HashMap<String,List<String>>();

    public String getServerRegion() {
        return serverRegion.split(":")[1];
    }

    public void setServerRegion(String serverRegion) {
        this.serverRegion = "region:"+serverRegion;
    }

    public double getCost() {
        return Double.parseDouble(cost.split(":")[1]);
    }

    public void setCost(double cost) {
        this.cost = "total_cost:"+cost;
    }

    public List<String> getServers() {
        return servers.get("servers");
    }

    public void setServers(Map<String,Integer> servers) {
        List<String> serverList = new ArrayList<String>();
        for(String serverNames:Util.cpuNames) {
            if(servers.containsKey(serverNames)) {
                serverList.add("("+serverNames+","+servers.get(serverNames)+")");
            }
        }
        this.servers.put("servers",serverList);
    }

    @Override
    public String toString() {
        return "{" +
                "serverRegion='" + serverRegion + '\'' +
                ", cost='" + cost + '\'' +
                ", servers=" + servers +
                "}\n";
    }
}
