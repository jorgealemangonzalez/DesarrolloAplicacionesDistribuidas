package BFSN.Test;

import BFSN.Beans.Users;
import BFSN.Beans.StationsStatistics;
import BFSN.Beans.User;
import BFSN.Beans.Stations;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.ClientErrorException;
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

    private static Scanner scanner;

	public static void main(String[] args) {
              
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:15000");
        scanner = new Scanner(System.in);
        System.out.println("PRESS ENTER TO CONTINUE WITH NEXT TEST");
        
        /* TESTING STATIONS SERVICE*/  
        scanner.nextLine();
        System.out.println();
        System.out.println("Retrieving stations...");
        Stations stations = target.path("stations/getStations").request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType< Stations >(){});
        System.out.println("Stations obtained: "+stations);
        
        scanner.nextLine();
        System.out.println();
        System.out.println("Retrieving stations statistics...");
        StationsStatistics ss = target.path("stations/getStationsStatistics").request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType< StationsStatistics >(){});
        System.out.println("StationsStatistics obtained: "+ss);
        
        /* TESTING OF USERS SERVICE */
        scanner.nextLine();
        System.out.println("Subscriving clients...");
        List<String> jorgeStationIds = new LinkedList<String>(Arrays.asList("1","2","18","9","20","11","100"));
        User jorge = new User("677166464",jorgeStationIds,"136502577");
        List<String> arnauStationIds = new LinkedList<String>(Arrays.asList("99","78","18","9","123","89"));
        User arnau = new User("11",arnauStationIds,"239602143");
        try{
	        target.path("client/subscrive").request(MediaType.APPLICATION_JSON_TYPE)
	                .post(Entity.entity(jorge, MediaType.APPLICATION_JSON), User.class);
	        target.path("client/subscrive").request(MediaType.APPLICATION_JSON_TYPE)
	                .post(Entity.entity(arnau, MediaType.APPLICATION_JSON), User.class);
	        System.out.println("Clients succesfully subscrived");
        }catch(Exception e){
        	
        	System.out.println("An unexpected exception has been thrown: "+e.getMessage());
        }
        
        
        scanner.nextLine();
        Users listOfUsers = target.path("client/getClients").request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType< Users >(){});
        System.out.println("Clients obtained: "+listOfUsers);
      
        
        //Error exceptions
        scanner.nextLine();
	    System.out.println();
	    System.out.println("Trying to subscrive user with bad info");
	    List<String> badUserStationIds = new LinkedList<String>(Arrays.asList("1","2","894576345"));//StationId doesn't exists
	    User badUser = new User("2345674",badUserStationIds,"8956734");
	      
	    try{
	    	target.path("client/subscrive").request(MediaType.APPLICATION_JSON_TYPE)
	        .post(Entity.entity(badUser, MediaType.APPLICATION_JSON), User.class);
	    	
	    	System.out.println("An exception should be throwed , ERROR");
	      	
	    }catch(ClientErrorException e){
	      	if(e.getClass().equals(ClientErrorException.class) && 
	      			e.getMessage().contains("409")){
	      		System.out.println("The system said "+e.getMessage());
	      	}else{
	      		System.out.println("Unexpected throw: ");
	      		e.printStackTrace();
	      	}
	    }
	      
        /* TESTING TELEGRAM MESSAGES */
	    scanner.nextLine();
	    System.out.println("Sending telegram message to jorge...");
        target.path("client/notifySlots/"+jorge.getPhoneNumber()).request().get();
	    System.out.println("Telegram message sended");
        
	    scanner.nextLine();
	    System.out.println("End of test");
    }
}
