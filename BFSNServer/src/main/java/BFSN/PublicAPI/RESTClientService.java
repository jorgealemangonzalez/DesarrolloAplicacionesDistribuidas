package BFSN.PublicAPI;


import BFSN.Beans.Users;
import BFSN.Beans.Station;
import BFSN.Beans.User;
import BFSN.Beans.Stations;
import BFSN.Connexions.TelegramInterface;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author jorgeAleman
 */
@Path("/client")
public class RESTClientService {
    private static Users users_rest = new Users();
    
    public RESTClientService(){
    	
    };  
        
    /**
     * Subscrive a user to the system
     * @param user usuario a subscrivir
     * @return <b>code: <code>200</code> </b> Successful
     * @throws <b>ERROR: error on input information</b>
     */
    @POST
    @Path("/subscrive")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response subscrive(User user){
        System.out.println("Subscrive request of user "+user.getPhoneNumber());
        if(user.getPhoneNumber() == "" || user.getTelegramToken() == ""){
            throw new WebApplicationException(
            Response.status(Response.Status.CONFLICT)
                .entity("The phone number and telegram token should be specified")
                .type(MediaType.TEXT_PLAIN)
                .build()
            );
        }else if(RESTClientService.getStaticUsers().findByPhone(user.getPhoneNumber()) != null){
            System.out.println("movil existente");
            throw new WebApplicationException(
            Response.status(Response.Status.CONFLICT)
                .entity("The phone number is already in use")
                .type(MediaType.TEXT_PLAIN)
                .build()
            );     
        }else{
        	
        	for(String stationId : user.getStationIds()){
        		if(RESTStationsService.getStaticStations().getStationById(stationId) == null){
        			throw new WebApplicationException(
        		            Response.status(Response.Status.CONFLICT)
        		                .entity("The station id "+stationId+" does not exist. Please specify stations that exist.")
        		                .type(MediaType.TEXT_PLAIN)
        		                .build()
		            );
        		}
        	}
        	
            RESTClientService.getStaticUsers().addUser(user);
            return Response.status(200).build();
        }
        
    }
    /**
     * 
     * Make a request of all the current clients in the system
     * @return <b>coide: <code>200</code> </b> Correct list of clients
     * @throws <b>ERROR: No clients in the system </b>
     */
    @GET
    @Path("/getClients")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClients(){
        Users users = RESTClientService.getStaticUsers();
        System.out.println("Get clients request");
        if(!users.getUsers().isEmpty()){
            return Response.status(200).entity(users).build();
        }else{
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                    .entity("There are no clients available")
                    .type(MediaType.TEXT_HTML_TYPE).build());
        }
    }
     /**
     * 
     * Makes a request to notify a specific user of his subscrived slots(by his phone number) via Telegram
     * @param phone telefono usuario a notidicar
     * @return <b>code succesfull: <code>200</code> </b> Message sent
     * @throws <b>ERROR: no user found with that phone number</b> 
     */
    @GET
    @Path("/notifySlots/{phonenumber}")
    public Response notifySlots(@PathParam("phonenumber") String phone){
        System.out.println("Request to send slots by phone to "+phone);
        User user = RESTClientService.getStaticUsers().findByPhone(phone);
        if(user != null){
            String mesage = "This are the free slots in your subscrived bicing stations:\n ";
            Stations stations = RESTStationsService.getStaticStations();
            for(String userStationId : user.getStationIds()){
                Station station = stations.getStationById(userStationId);
                if(station != null)mesage += "Station " + station.getId() + " has " + station.getSlots() + " free slots\n";
            
            }       
            TelegramInterface.sendMesage(user.getTelegramToken(), mesage);
            return Response.status(200).build();
            
        }else{
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                    .entity("There is no user with the requested phonenumber")
                    .type(MediaType.TEXT_PLAIN)
                    .build()
            );
        }
        
    }
    
     /**
     * 
     * private getter of the variable users_rest
     * @return Users
     */
    private static Users getStaticUsers(){
        return RESTClientService.users_rest;
    }
}
