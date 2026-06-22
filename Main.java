import model.*;

import java.io.PrintStream;
import java.util.*;
public class Main {

    static MetroSystem metro = new MetroSystem();
    static Scanner scanner  = new Scanner(System.in);

    public static void main(String[] args) {
        printBanner();
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> showAllLines();
                case "2" -> showLineStations();
                case "3" -> showTimetable();
                case "4" -> searchStation();
                case "5" -> showInterchanges();
                case "0" -> { running = false; System.out.println("\nGoodbye! Bona sort!"); }
                default  -> System.out.println("  Invalid option. Try again.");
            }
        }
    }

    static void printBanner() {
        System.out.println("==============================================================");
        System.out.println("       BARCELONA METRO TIMETABLE SYSTEM                      ");
        System.out.println("       Transports Metropolitans de Barcelona (TMB)            ");
        System.out.println("       Course Paper #13  |  CSA1D1                       ");
        System.out.println("==============================================================");
        PrintStream printf = System.out.printf("  Total lines: 5     Total stations: %d%n%n",
                metro.getTotalStations());
    }

    static void printMenu() {
        System.out.println("+-----------------------------+");
        System.out.println("|         MAIN MENU           |");
        System.out.println("+-----------------------------+");
        System.out.println("|  1. Show all lines          |");
        System.out.println("|  2. Show stations on line   |");
        System.out.println("|  3. Show timetable          |");
        System.out.println("|  4. Search station          |");
        System.out.println("|  5. Show interchanges       |");
        System.out.println("|  0. Exit                    |");
        System.out.println("+-----------------------------+");
        System.out.print("  Enter choice: ");
    }
    static void showAllLines() {
        System.out.println("\n== ALL METRO LINES =====================================================");
        System.out.println("-".repeat(85));
        System.out.printf("| %-4s | %-13s | %-11s | %-13s | %-11s | %-16s |%n",
                "LINE","WEEKDAY START","WEEKDAY END","WEEKEND START","WEEKEND END","FREQUENCY");
        System.out.println("-".repeat(85));
        System.out.println("-".repeat(80));
        for (MetroLine line : metro.getAllLines()) {
            System.out.println("-".repeat(85));
            System.out.printf(" L%-3s  %-13s  %-11s  %-13s  %-11s  %-16s %n",
                    line.getName(),
                    line.getFirstWeekday(), line.getLastWeekday(),
                    line.getFirstWeekend(), line.getLastWeekend(),
                    "every " + line.getFrequencyPeak() + "/" + line.getFrequencyOffPeak() + " min");
        }
        System.out.println();
    }

    static void showLineStations() {
        System.out.print("\n  Enter line number (1-5): ");
        String input = scanner.nextLine().trim();
        MetroLine line = metro.getLine("L" + input);
        if (line == null) { System.out.println("  Line not found.\n"); return;
        }

        System.out.println("\n== LINE L" + line.getName() + " STATIONS =============================================");
        System.out.println("  - ACCESS Yes [A] = Wheelchair accessible (lift/ramp available)");
        System.out.println("  - ACCESS No      = No wheelchair access");
        System.out.println("  - ZONE 1         = City centre (standard fare)");
        System.out.println("  - ZONE 2         = Suburban area (higher fare)");
        System.out.println("  - INTERCHANGE    = Transfer available to another line");
        System.out.println("-".repeat(74));
        System.out.printf("%-4s %-32s %-6s %-10s %-20s%n","#","STATION","ZONE","ACCESS","INTERCHANGE");
        System.out.println("-".repeat(74));
        int i = 1;
        for (Station st : line.getStations()) {
            String conn = st.getConnections().isEmpty() ? "-" : String.join(",", st.getConnections());
            System.out.println("-".repeat(74));
            System.out.printf("  %-3d %-32s %-6s %-10s %-20s%n",
                    i++, st.getName(), st.getZone(),
                    st.isAccessible() ? "Yes [A]" : "No", conn);
        }
        System.out.printf("  Total: %d stations%n%n", line.getStations().size());
    }

    static void showTimetable() {
        System.out.print("\n  Enter line number (1-5): ");
        String input = scanner.nextLine().trim();
        MetroLine line = metro.getLine("L" + input);
        if (line == null) { System.out.println("  Line not found.\n"); return; }

        boolean weekend = false;
        while (true) {
            System.out.print("  Day type - enter W (Weekday) or E (Weekend): ");
            String day = scanner.nextLine().trim().toUpperCase();
            if (day.equals("W")) { weekend = false; break; }
            else if (day.equals("E")) { weekend = true; break; }
            else System.out.println("  Invalid input! Please enter W or E only.");
            }

        List<String> times = line.generateDepartures(weekend);
        List<Station> sts  = line.getStations();

        System.out.println("\n== TIMETABLE  Line L" + line.getName() +
                "  [" + (weekend ? "Weekend/Holiday" : "Weekday") + "] ==");
        System.out.println("  Route: " + sts.get(0).getName() +
                "  -->  " + sts.get(sts.size()-1).getName());
        System.out.println("  Total departures: " + times.size());
        int currentHour = -1;
        int col = 0;
        for (String t : times) {
            int hour = Integer.parseInt(t.split(":")[0]);
            if (hour != currentHour) {
                if (currentHour != -1) System.out.println();
                System.out.println("-".repeat(70));
                System.out.printf("  %02d:00  |  ", hour);
                currentHour = hour;
                col = 0;
            }
            System.out.printf("%s  ", t);
            col++;
            if (col % 10 == 0) System.out.printf("%n|");
        }
        System.out.println();
        System.out.println("-".repeat(70));
    }

    static void searchStation() {
        System.out.print("\n  Enter station name (or part of name): ");
        String query = scanner.nextLine().trim();
        List<Station> results = metro.searchStations(query);
// Remove duplicates by name
        Map<String, Station> uniqueMap = new LinkedHashMap<>();
        for (Station st : results) {
            if (!uniqueMap.containsKey(st.getName())) {
                uniqueMap.put(st.getName(), st);
            }
        }
        List<Station> unique = new ArrayList<>(uniqueMap.values());
        results = unique;

        if (results.isEmpty()) {
            System.out.println("  No stations found for: \"" + query + "\"\n");
            return;
        }
        System.out.println("\n== SEARCH RESULTS: \"" + query + "\" ================================");
        for (Station st : results) {
            List<MetroLine> lines = metro.getLinesForStation(st.getName());
            System.out.println("  Station    : " + st.getName());
            System.out.println("  Zone       : " + st.getZone());
            System.out.println("  Accessible : " + (st.isAccessible() ? "Yes" : "No"));
            System.out.print ("  Lines      : ");
            for (MetroLine ml : lines) System.out.print("L" + ml.getName() + "  ");
            System.out.println();
            List<MetroLine> allLines = metro.getLinesForStation(st.getName());
            List<String> interchanges = new ArrayList<>();
            for (MetroLine ml : allLines) interchanges.add("L" + ml.getName());
            System.out.println("  Interchange : " + (interchanges.size() > 1 ? String.join(", ", interchanges) : "-"));
            System.out.println("  " + "-".repeat(48));
        }
        System.out.println();
    }

    static void showInterchanges() {
        System.out.println("\n== INTERCHANGE STATIONS ==========================================");
        System.out.println("-".repeat(60));
        System.out.printf(" %-30s  %-20s 5%n", "STATION", "LINES");
        System.out.println("-".repeat(60));

        Set<String> seen = new LinkedHashSet<>();
        for (MetroLine line : metro.getAllLines()) {
            for (Station st : line.getStations()) {
                if (!st.getConnections().isEmpty() && !seen.contains(st.getName())) {
                    seen.add(st.getName());
                    List<MetroLine> lines = metro.getLinesForStation(st.getName());
                    StringBuilder lb = new StringBuilder();
                    for (MetroLine ml : lines) lb.append("L").append(ml.getName()).append("  ");
                    System.out.println("-".repeat(60));
                    System.out.printf(" %-30s  %-20s %n", st.getName(), lb.toString().trim());
                }
            }
        }
        System.out.println("-".repeat(60));
        System.out.println();
    }
}
