
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jorgeAleman
 */
@Path("/stations")
public class RESTStationsService {
    
    //Es una cache que se actualiza cada 60s
    private static Stations stations;
    
    
    @GET
    @Path("/getStations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStations(){
        return Response.status(200).entity(stations).build();
    }
    
    @GET
    @Path("/getStationsStatics")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStationsStatics(){
        
        return Response.status(200).entity(new StationsStatics(stations)).build();
    }
    
    
}
