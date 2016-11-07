
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
/**
 * 
 */
public class RESTClientService {
    private Users users;
    
    @POST
    @Path("/subscrive")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response subscrive(User user){
        users.addUser(user);
        return Response.status(200).build();//TODO hacer control de errores ??
    }
    
    @GET
    @Path("/getClients")
    @Produces(MediaType.APPLICATION_JSON)
    public Users getClients(){
        return users;
    }
    
    @GET
    @Path("/notifySlots/{phonenumber}")
    public Response notifySlots(@PathParam("phonenumber") String phone){
        //Send telegram message
        User user = users.findByPhone(phone);
        if(user != null){
            //TODO send by telegrarm mesage con el token de telegram
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
}
