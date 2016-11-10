
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
public class Stations implements java.lang.Iterable{
    private HashMap<String,Station> stations;   //id to Station

    public Stations(){
        stations = new HashMap<String,Station>();
    }
    public Stations(HashMap<String,Station> stations) {
        this.stations = stations;
    }

    public HashMap<String,Station> getStations() {
        return stations;
    }

    public void setStations(HashMap<String,Station> stations) {
        this.stations = stations;
    }
    
    public Station getStationById(String id){
        return stations.get(id);
    }
    
    @Override
    public String toString() {
        String Sstations = "[ ";
        Station[] stationsA = (Station[]) stations.entrySet().toArray();
        for(int i = 0 ; i < stationsA.length ; ++i){
            Sstations += stationsA[i].toString();
            if(i != stationsA.length - 1)
                Sstations += " , ";
        }
        Sstations += " ]";
        return "Stations{" + "stations=" + Sstations + '}';
    }
    
    //Maybe not needed , if nobody iterate it
    public Iterator iterator() {
        return stations.entrySet().iterator();
    }
}
