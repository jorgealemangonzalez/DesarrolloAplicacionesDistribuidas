package BFSN.Connexions;


import BFSN.Beans.Stations;
import java.io.PrintWriter;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jorgeAleman
 */
public class BicingInterface {
    private static Stations stations ;
    
    public static Stations getStations(){
        System.out.println("Downloading bicing stations...");
        Client client = ClientBuilder.newClient();
        WebTarget targetGet = client.target("http://wservice.viabicing.cat/v2/stations");
        stations = targetGet.request(
                MediaType.APPLICATION_JSON_TYPE).get(new GenericType< Stations >() {
                });
        //TESTEAR SI COGE BIEN
        try{
            PrintWriter prueva = new PrintWriter("stations.txt", "UTF-8");
            prueva.println(stations.toString());
            prueva.close(); 
        }catch(Exception e){

        }
        return stations;
    }
}
