package BFSN.Beans;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Object with a HashMap storing all the stations
 * identified by an unique id
 * @author jorgeAleman
 */
public class Stations {
    
    private HashMap<String,Station> stations;

    public Stations(){
        stations = new HashMap<String,Station>();
    }
    public Stations(List<Station> stations) {
        this();
        this.setStations(stations);
    }

    public List<Station> getStations() {
        return new ArrayList<Station>(this.stations.values());
    }

    public void setStations(List<Station> stations) {
        this.stations.clear();
        for(Station station : stations){
            this.stations.put(station.getId(),station);
        }
    }
    
    /**
     * 
     * @param id
     * @return Station or null if didn't found
     */
    public Station getStationById(String id) {
        return this.stations.get(id);
    }

    @Override
    public String toString() {
        String Sstations = "[ ";
        int i = 0;
        for(Station station : stations.values()){
            Sstations += station.toString();
            if(i != stations.size()-1)
                Sstations += " , ";
            i++;
        }
        Sstations += " ]";
        return "Stations{" + "stations=" + Sstations + '}';
    }

    
}

    
    
    
