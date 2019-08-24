import java.util.List;
import java.util.Map;

public class CustomTuple {
    private String serverRegion;
    private String cost;
    private Map<String, List<String>> servers;

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

    public void setServers(List<String> servers) {
        this.servers.put("servers",servers);
    }
}
