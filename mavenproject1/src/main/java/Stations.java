
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

 
public class Stations {
    
    private List<Station> stations;

    public Stations(){
        stations = new ArrayList<Station>();
    }
    public Stations(List<Station> stations) {
        this.stations = stations;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
    
    public Station getStationById(String id){
        for(Station s : stations){
            if(s.getId().equals(id)){
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String Sstations = "[ ";
        for(int i = 0 ; i < stations.size() ; ++i){
            Sstations += stations.get(i).toString();
            if(i != stations.size()-1)
                Sstations += " , ";
        }
        Sstations += " ]";
        return "Stations{" + "stations=" + Sstations + '}';
    }

    
}

    
    
    
