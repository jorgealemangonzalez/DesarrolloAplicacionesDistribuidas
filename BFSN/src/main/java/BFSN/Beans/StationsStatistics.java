package BFSN.Beans;


/**
 *
 * @author jorgeAleman
 */
public class StationsStatistics {
    
    private String totalFreeSlots ;
    private String totalOcupiedSlots;
    private String totalNumberStations;
    private String averageAltitude;
    private String stationsWithoutSlots ="";
    
    public StationsStatistics(){}
    
    /**
     * Obtains the different statics from an stations object.
     * This statics will be saved on the different atributes.
     * @param stations 
     */
   
    public StationsStatistics(Stations stations) {
        System.out.println("Creating StationsStatistics object");
        Integer totalFreeSlotsLocal = 0;
        Integer totalOcupiedSlotsLocal = 0;
        Integer totalNumberStationsLocal = stations.getStations().size();
        Integer cumAltitude = 0;
        //System.out.println("Extracting stations");
        for(Station station : stations.getStations()){
            //System.out.println("Extracting station: "+station.toString());
            if(station.getSlots().equals("0")){
                stationsWithoutSlots += station.getId() + " ";
            }
            totalFreeSlotsLocal += Integer.parseInt(station.getSlots());
            //System.out.println(totalFreeSlotsLocal);
            totalOcupiedSlotsLocal += Integer.parseInt(station.getBikes());
            //System.out.println(totalOcupiedSlotsLocal);
            cumAltitude += Integer.parseInt(station.getAltitude());
            //System.out.println(cumAltitude);
        }
        //System.out.println("Extraction successfull");
        Double averageAltitudeLocal;
        if(totalNumberStationsLocal == 0){
        	 averageAltitudeLocal = (double)0;
        }else{
        	averageAltitudeLocal = 
                (double)cumAltitude / (double)totalNumberStationsLocal ;
        }
        this.averageAltitude = averageAltitudeLocal.toString();
        this.totalFreeSlots = totalFreeSlotsLocal.toString();
        this.totalOcupiedSlots = totalOcupiedSlotsLocal.toString();
        this.totalNumberStations = totalNumberStationsLocal.toString();
        
        //System.out.println(this.toString());
    }

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
        return "StationsStatics{" + 
                "totalFreeSlots=" + totalFreeSlots + 
                ", totalOcupiedSlots=" + totalOcupiedSlots + 
                ", totalNumberStations=" + totalNumberStations + 
                ", averageAltitude=" + averageAltitude + 
                ", emptySlots by id="+ stationsWithoutSlots +'}';
    }
    
     
}