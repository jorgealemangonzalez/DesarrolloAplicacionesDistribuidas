package BFSN.PublicAPI;


import BFSN.Beans.StationsStatistics;
import BFSN.Beans.Stations;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jorgeAleman
 */
@Path("/stations")
public class RESTStationsService {
    
    private static Stations stations;
    
     /**
     * 
     * Makes a request to get all the stations in our system
     * @return <b>code succesfull: <code>200</code> </b> Return all stations
     * @throws <b>ERROR: no stations were found</b> 
     */
    @GET
    @Path("/getStations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStations(){
        System.out.println("Get stations request...");
        Stations stations = RESTStationsService.getStaticStations();
        if(stations != null){
            return Response.status(200).entity(stations).build();
        }else{
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                    .entity("There are no stations available")
                    .type(MediaType.TEXT_HTML_TYPE).build());
        }
    }
    
    
     /**
     * 
     * Makes a request to get all the statistics about the system stations
     * @return <b>code succesfull: <code>200</code> </b> Return all statistics of Stations
     * @throws <b>ERROR: no statistics were found</b> 
     */
    @GET
    @Path("/getStationsStatistics")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStationsStatics(){
        System.out.println("Get station statistics request...");
        StationsStatistics stationStats = new StationsStatistics(stations);
        return Response.status(200).entity(stationStats).build();
    }
    
    static public Stations getStaticStations(){
        return stations;
    }
    
    static public void uploadStaticStations(Stations stationsU){
        stations = stationsU;
    }
}
