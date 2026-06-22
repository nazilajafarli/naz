package model;
import java.util.*;

public class MetroLine implements Displayable {
    private String id, name, color;
    private String firstWeekday, lastWeekday, firstWeekend, lastWeekend;
    private int freqPeak, freqOffPeak;
    private List<Station> stations = new ArrayList<>();

    public MetroLine(String id, String name, String color,
                     String fw, String lw, String fwe, String lwe, int fp, int fo) {
        this.id=id; this.name=name; this.color=color;
        this.firstWeekday=fw; this.lastWeekday=lw;
        this.firstWeekend=fwe; this.lastWeekend=lwe;
        this.freqPeak=fp; this.freqOffPeak=fo;
    }
    public void addStation(Station s) { stations.add(s); }
    public String getId()             { return id; }
    public String getName()           { return name; }
    public String getColor()          { return color; }
    public String getFirstWeekday()   { return firstWeekday; }
    public String getLastWeekday()    { return lastWeekday; }
    public String getFirstWeekend()   { return firstWeekend; }
    public String getLastWeekend()    { return lastWeekend; }
    public int getFrequencyPeak()     { return freqPeak; }
    public int getFrequencyOffPeak()  { return freqOffPeak; }
    public List<Station> getStations(){ return stations; }

    public List<String> generateDepartures(boolean weekend) {
        List<String> times = new ArrayList<>();
        String first = weekend ? firstWeekend : firstWeekday;
        String last  = weekend ? lastWeekend  : lastWeekday;
        int start = toMinutes(first);
        int end   = toMinutes(last);

        if (end == 0) end = 24 * 60;
        if (weekend && end <= 180) end += 24 * 60;

        int cur = start;
        while (cur <= end) {
            int h = (cur % (24*60)) / 60;
            int m = cur % 60;
            times.add(String.format("%02d:%02d", h, m));
            boolean peak = !weekend && ((h>=7 && h<9) || (h>=17 && h<19));
            cur += peak ? freqPeak : freqOffPeak;
        }
        return times;
    }

    private int toMinutes(String time) {
        String[] p = time.split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }

    public String getDisplayInfo() {
        return String.format("Line L%s | Weekdays %s-%s | Weekends %s-%s | Peak %dmin | Off-peak %dmin | Stations: %d",
                name,firstWeekday,lastWeekday,firstWeekend,lastWeekend,freqPeak,freqOffPeak,stations.size());
    }
    public String toString() { return "L"+name; }
}
