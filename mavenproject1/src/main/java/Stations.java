
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
        String Sstations = "";
        for(Station station : stations){
            Sstations += station.toString() + " , ";
        }
        return "Stations{" + "stations=" + Sstations + '}';
    }
}
