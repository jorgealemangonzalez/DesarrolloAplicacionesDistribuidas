package TwitterPublisher.Beans;




/**
 *Object with all the information about the Statistics of the Stations
 * stored in our system
 * @author jorgeAleman
 */
public class StationsStatistics {
    //Objeto con la informacion de las estadisticas que hagamos
    private String totalFreeSlots ;
    private String totalOcupiedSlots;
    private String totalNumberStations;
    private String averageAltitude;
    private String stationsWithoutSlots ="";
    
    public StationsStatistics(){}
    
    public String getStationsWithoutSlots() {
        return stationsWithoutSlots;
    }

    public void setStationsWithoutSlots(String stationsWithoutSlots) {
        this.stationsWithoutSlots = stationsWithoutSlots;
    }

    
    public String getTotalFreeSlots() {
        return totalFreeSlots;
    }

    public void setTotalFreeSlots(String totalFreeSlots) {
        this.totalFreeSlots = totalFreeSlots;
    }

    public String getTotalOcupiedSlots() {
        return totalOcupiedSlots;
    }

    public void setTotalOcupiedSlots(String totalOcupiedSlots) {
        this.totalOcupiedSlots = totalOcupiedSlots;
    }

    public String getTotalNumberStations() {
        return totalNumberStations;
    }

    public void setTotalNumberStations(String totalNumberStations) {
        this.totalNumberStations = totalNumberStations;
    }

    public String getAverageAltitude() {
        return averageAltitude;
    }

    public void setAverageAltitude(String averageAltitude) {
        this.averageAltitude = averageAltitude;
    }
    
    @Override
    public String toString() {
        return "StationsStatics{" + "totalFreeSlots=" + totalFreeSlots + ", totalOcupiedSlots=" + totalOcupiedSlots + ", totalNumberStations=" + totalNumberStations + ", averageAltitude=" + averageAltitude + ", emptySlots by id="+ stationsWithoutSlots +'}';
    }
    
     
}