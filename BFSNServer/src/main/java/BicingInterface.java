
import java.io.PrintWriter;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jorgeAleman
 */
public class BicingInterface {
    private static Stations stations ;
    
    static Stations getStations(){
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
