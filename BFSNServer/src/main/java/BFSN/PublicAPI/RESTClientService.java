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
    private static Users users_rest;
    
    public RESTClientService(){
        if(users_rest == null){
            users_rest = new Users();
        }
    };  
    
    /**
     * 
     * @param user usuario a subscrivir
     * @return <b>code: <code>200</code> </b> Successful
     */
    @POST
    @Path("/subscrive")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response subscrive(User user){
        if(user.getPhoneNumber() == "" || user.getTelegramToken() == ""){
            throw new WebApplicationException(
            Response.status(Response.Status.CONFLICT)
                .entity("The phone number and telegram token should be specified")
                .type(MediaType.TEXT_PLAIN)
                .build()
            );
        }else{
            //TODO si los id no perteneces a ninguna station error
            System.out.println("Subscrive request of user "+user.getPhoneNumber());
            users_rest.addUser(user);
            return Response.status(200).build();
        }
        
    }
    
    @GET
    @Path("/getClients")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClients(){
        System.out.println("Request to get all clients" + users_rest.getUsers().size());
        return Response.status(200).entity(RESTClientService.getStaticUsers()).build();
    }
    
    @GET
    @Path("/notifySlots/{phonenumber}")
    public Response notifySlots(@PathParam("phonenumber") String phone){
        System.out.println("Request to send slots by phone to "+phone);
        User user = users_rest.findByPhone(phone);
        if(user != null){
            String mesage = "This are the free slots in your subscrived bicing stations:\n ";
            Stations stations = RESTStationsService.getStaticStations();
            for(String userStationId : user.getStationIds()){
                Station station = stations.getStationById(userStationId);
                System.out.println("station value  "+station.getId());
                if(station != null)mesage += "Station " + station.getId() + " has " + station.getSlots() + " free slots\n";
            }       
            TelegramInterface.sendMesage(user.getTelegramToken(), mesage);//TODO Maibe change to tokenId
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

    private static Users getStaticUsers(){
        return RESTClientService.users_rest;
    }
}
