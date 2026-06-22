package model;
import java.util.*;

public class MetroSystem {
    private Map<String,MetroLine> lines = new LinkedHashMap<>();
    private Map<String,Station>   stations = new LinkedHashMap<>();

    public MetroSystem() { initData(); }

    private void initData() {
        addLine("L1","1","#E3051B","05:00","00:00","05:00","02:00",3,6, new String[][]{
                {"L1_01","Hospital de Bellvitge","1","true"},{"L1_02","Bellvitge","1","true"},
                {"L1_03","Av. Carrilet","1","false"},{"L1_04","Rambla Just Oliveras","1","false"},
                {"L1_05","Can Serra","1","false"},{"L1_06","Florida","1","false"},
                {"L1_07","Torrassa","1","true"},{"L1_08","Santa Eulalia","1","true"},
                {"L1_09","Mercat Nou","1","false"},{"L1_10","Hostafrancs","1","false"},
                {"L1_11","Placa de Sants","1","true"},{"L1_12","Tarragona","1","false"},
                {"L1_13","Rocafort","1","false"},{"L1_14","Urgell","1","true"},
                {"L1_15","Universitat","1","true"},{"L1_16","Catalunya","1","true"},
                {"L1_17","Urquinaona","1","true"},{"L1_18","Arc de Triomf","1","true"},
                {"L1_19","Marina","1","true"},{"L1_20","Glories","1","false"},
                {"L1_21","Clot","1","true"},{"L1_22","Navas","1","false"},
                {"L1_23","La Sagrera","1","true"},{"L1_24","Fabra i Puig","1","false"},
                {"L1_25","Sant Andreu","1","false"},{"L1_26","Torras i Bages","1","false"},
                {"L1_27","Trinitat Vella","1","true"},{"L1_28","Baro de Viver","1","false"},
                {"L1_29","Santa Coloma","1","false"},{"L1_30","Fondo","1","true"}
        });
        addLine("L2","2","#8B1E8B","05:00","00:00","05:00","02:00",4,7, new String[][]{
                {"L2_01","Parallel","1","true"},{"L2_02","Sant Antoni","1","true"},
                {"L2_03","Universitat","1","true"},{"L2_04","Passeig de Gracia","1","true"},
                {"L2_05","Tetuan","1","false"},{"L2_06","Monumental","1","false"},
                {"L2_07","Sagrada Familia","1","true"},{"L2_08","Encants","1","false"},
                {"L2_09","Clot","1","true"},{"L2_10","Bac de Roda","1","false"},
                {"L2_11","Sant Marti","1","false"},{"L2_12","La Pau","1","true"},
                {"L2_13","Verneda","1","false"},{"L2_14","Artigues Sant Adria","1","false"},
                {"L2_15","Sant Roc","1","false"},{"L2_16","Gorg","1","true"},
                {"L2_17","Pep Ventura","1","false"},{"L2_18","Badalona Pompeu Fabra","1","true"}
        });
        addLine("L3","3","#007B3E","05:00","00:00","05:00","02:00",3,6, new String[][]{
                {"L3_01","Zona Universitaria","1","true"},{"L3_02","Palau Reial","1","false"},
                {"L3_03","Maria Cristina","1","true"},{"L3_04","Les Corts","1","false"},
                {"L3_05","Placa del Centre","1","false"},{"L3_06","Sants Estacio","1","true"},
                {"L3_07","Tarragona","1","false"},{"L3_08","Espanya","1","true"},
                {"L3_09","Parallel","1","true"},{"L3_10","Drassanes","1","true"},
                {"L3_11","Liceu","1","true"},{"L3_12","Catalunya","1","true"},
                {"L3_13","Passeig de Gracia","1","true"},{"L3_14","Diagonal","1","true"},
                {"L3_15","Fontana","1","false"},{"L3_16","Gracia","1","false"},
                {"L3_17","Lesseps","1","false"},{"L3_18","Vallcarca","1","false"},
                {"L3_19","Penitents","1","false"},{"L3_20","Vall d Hebron","1","true"},
                {"L3_21","Montbau","1","false"},{"L3_22","Mundet","1","false"},
                {"L3_23","Valldaura","1","false"},{"L3_24","Canyelles","1","false"},
                {"L3_25","Roquetes","1","false"},{"L3_26","Trinitat Nova","1","true"}
        });
        addLine("L4","4","#F0B612","05:00","00:00","05:00","02:00",4,7, new String[][]{
                {"L4_01","Trinitat Nova","1","true"},{"L4_02","Via Julia","1","false"},
                {"L4_03","Llucmajor","1","false"},{"L4_04","Maragall","1","true"},
                {"L4_05","Guinardo","1","true"},{"L4_06","Alfons X","1","false"},
                {"L4_07","Joanic","1","false"},{"L4_08","Verdaguer","1","true"},
                {"L4_09","Girona","1","false"},{"L4_10","Passeig de Gracia","1","true"},
                {"L4_11","Urquinaona","1","true"},{"L4_12","Jaume I","1","true"},
                {"L4_13","Barceloneta","1","true"},{"L4_14","Ciutadella Vila Olimpica","1","true"},
                {"L4_15","Bogatell","1","false"},{"L4_16","Llacuna","1","false"},
                {"L4_17","Poblenou","1","false"},{"L4_18","Selva de Mar","1","false"},
                {"L4_19","El Maresme Forum","1","true"},{"L4_20","Besos Mar","1","false"},
                {"L4_21","La Pau","1","true"}
        });
        addLine("L5","5","#0059A7","05:00","00:00","05:00","02:00",4,8, new String[][]{
                {"L5_01","Cornella Centre","2","true"},{"L5_02","Sant Ildefons","2","false"},
                {"L5_03","Can Boixeres","2","false"},{"L5_04","Can Vidalet","2","false"},
                {"L5_05","Pubilla Cases","2","true"},{"L5_06","Collblanc","1","true"},
                {"L5_07","Badal","1","false"},{"L5_08","Placa de Sants","1","true"},
                {"L5_09","Sants Estacio","1","true"},{"L5_10","Entenca","1","false"},
                {"L5_11","Hospital Clinic","1","true"},{"L5_12","Diagonal","1","true"},
                {"L5_13","Verdaguer","1","true"},{"L5_14","Sagrada Familia","1","true"},
                {"L5_15","Sant Pau Dos de Maig","1","false"},{"L5_16","Camp de l Arpa","1","false"},
                {"L5_17","La Sagrera","1","true"},{"L5_18","Congres","1","false"},
                {"L5_19","Maragall","1","true"},{"L5_20","Virrei Amat","1","false"},
                {"L5_21","Vilapicina","1","false"},{"L5_22","Horta","1","true"},
                {"L5_23","El Carmel","1","false"},{"L5_24","El Coll La Teixonera","1","false"},
                {"L5_25","Vall d Hebron","1","true"}
        });
        markInterchanges();
    }

    private void addLine(String id,String name,String color,String fw,String lw,String fwe,String lwe,
                         int fp,int fo,String[][] data) {
        MetroLine line = new MetroLine(id,name,color,fw,lw,fwe,lwe,fp,fo);
        for (String[] s : data) {
            Station st = new Station(s[0],s[1],s[2],Boolean.parseBoolean(s[3]));
            stations.put(s[0],st);
            line.addStation(st);
        }
        lines.put(id,line);
    }

    private void markInterchanges() {
        Map<String,List<String>> nameToLines = new HashMap<>();
        for (MetroLine line : lines.values())
            for (Station st : line.getStations())
                nameToLines.computeIfAbsent(st.getName(), k->new ArrayList<>()).add(line.getName());
        for (MetroLine line : lines.values())
            for (Station st : line.getStations()) {
                List<String> ls = nameToLines.get(st.getName());
                if (ls!=null && ls.size()>1)
                    for (String l : ls)
                        if (!l.equals(line.getName())) st.addConnection("L"+l);
            }
    }

    public Collection<MetroLine> getAllLines()    { return lines.values(); }
    public MetroLine getLine(String id)           { return lines.get(id); }
    public int getTotalStations()                 { return stations.size(); }

    public List<Station> searchStations(String q) {
        List<Station> res = new ArrayList<>();
        String query = q.toLowerCase();
        for (Station st : stations.values())
            if (st.getName().toLowerCase().contains(query)) res.add(st);
        return res;
    }

    public List<MetroLine> getLinesForStation(String name) {
        List<MetroLine> res = new ArrayList<>();
        for (MetroLine line : lines.values())
            for (Station st : line.getStations())
                if (st.getName().equalsIgnoreCase(name)) { res.add(line); break; }
        return res;
    }
}
