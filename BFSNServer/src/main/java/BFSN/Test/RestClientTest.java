package BFSN.Test;

import BFSN.Beans.Users;
import BFSN.Beans.StationsStatistics;
import BFSN.Beans.User;
import BFSN.Beans.Stations;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author jorgeAleman
 */
public class RestClientTest {

    public static void main(String[] args) {
              
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:15000");
        
        System.out.println();
        System.out.println("Retrieving stations...");
        Stations stations = target.path("stations/getStations").request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType< Stations >(){});
        System.out.println("Stations obtained: "+stations);
        
        
        
        System.out.println();
        System.out.println("Retrieving stations statistics...");
        StationsStatistics ss = target.path("stations/getStationsStatistics").request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType< StationsStatistics >(){});
        System.out.println("StationsStatistics obtained: "+ss);
        
        
        
        List<String> jorgeStationIds = new LinkedList<String>(Arrays.asList("1","2","18","9","20","11","100"));
        User jorge = new User("677166464",jorgeStationIds,"136502577");
        List<String> arnauStationIds = new LinkedList<String>(Arrays.asList("99","78","18","9","123","77","89"));
        User arnau = new User("677166464",arnauStationIds,"239602143");
        User addjorge = target.path("client/subscrive").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(jorge, MediaType.APPLICATION_JSON), User.class);
        User addarnau = target.path("client/subscrive").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(arnau, MediaType.APPLICATION_JSON), User.class);
        
        System.out.println();
        System.out.println("Subscriving clients...");
        Users listOfUsers = target.path("client/getClients").request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType< Users >(){});
        System.out.println("Clients obtained: "+listOfUsers);

        
        
    }
}
