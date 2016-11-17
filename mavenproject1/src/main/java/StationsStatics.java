
/**
 *
 * @author jorgeAleman
 */
public class StationsStatics {
    //Objeto con la informacion de las estadisticas que hagamos
    private int totalFreeSlots ;
    private int totalOcupiedSlots;
    private final int totalNumberStations;
    private final int averageAltitude;
    
    public StationsStatics(Stations stations) {
        totalFreeSlots = 0;
        totalOcupiedSlots = 0;
        totalNumberStations = stations.getStations().size();
        int cumAltitude = 0;
        for(Station station : stations.getStations()){
            totalFreeSlots += Integer.getInteger(station.getSlots());
            totalOcupiedSlots += Integer.getInteger(station.getBikes());
            cumAltitude += Integer.getInteger(station.getAltitude());
        }
        
        averageAltitude = cumAltitude / totalNumberStations ;
        
    }

    @Override
    public String toString() {
        return "StationsStatics{" + "totalFreeSlots=" + totalFreeSlots + ", totalOcupiedSlots=" + totalOcupiedSlots + ", totalNumberStations=" + totalNumberStations + ", averageAltitude=" + averageAltitude + '}';
    }
    
     
}
