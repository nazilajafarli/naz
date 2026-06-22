package model;
import java.util.*;

public class Station implements Displayable {
    private String id, name, zone;
    private boolean accessible;
    private List<String> connections = new ArrayList<>();

    public Station(String id, String name, String zone, boolean accessible) {
        this.id = id; this.name = name; this.zone = zone; this.accessible = accessible;
    }
    public void addConnection(String line) { if (!connections.contains(line)) connections.add(line); }
    public String getId()          { return id; }
    public String getName()        { return name; }
    public String getZone()        { return zone; }
    public boolean isAccessible()  { return accessible; }
    public List<String> getConnections() { return connections; }
    public String getDisplayInfo() {
        return String.format("  %-30s Zone:%s  Access:%-3s  Connects:%s",
                name, zone, accessible ? "Yes" : "No",
                connections.isEmpty() ? "-" : String.join(",", connections));
    }
    public String toString() { return name; }
}
