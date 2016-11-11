
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
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
@Path("/client")
public class RESTClientService {
    private static Users users_rest;
    
    public RESTClientService(){
        if(users_rest == null){
            users_rest = new Users();
        }
    };    
    @POST
    @Path("/subscrive")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response subscrive(User user){
        System.out.println("Subscrive request of user "+user.getPhoneNumber());
        users_rest.addUser(user);
        return Response.status(200).build();//TODO hacer control de errores ??
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
                mesage += "Station " + station.getId() + " has " + station.getSlots() + " free slots\n";
            }
            
            TelegramFakeInterface.sendMesage(user.getTelegramToken(), mesage);//TODO Maibe change to tokenId
            
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
