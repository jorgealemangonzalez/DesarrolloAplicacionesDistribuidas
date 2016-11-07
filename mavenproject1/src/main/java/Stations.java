
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jorgeAleman
 */
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
