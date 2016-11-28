package BFSN.Connexions;


import BFSN.Beans.Stations;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * Middleware para conectar con Bicing y poder 
 * descargar la información sobre sus "stations"
 * @author jorgeAleman
 */
public class BicingInterface {
    private static Stations stations ;
    
     /**
     * Coger las características de todas las staciones
     * a través de JSON.
     * @return <b> Obtained station information</b>  
     * @throws <b>ERROR: Can't connect to the Bicing API</b> 
     */
    public static Stations getStations(){
        System.out.println("Downloading bicing stations...");
        Client client = ClientBuilder.newClient();
        WebTarget targetGet = client.target("http://wservice.viabicing.cat/v2/stations");
        try{
            stations = targetGet.request(
                MediaType.APPLICATION_JSON_TYPE).get(new GenericType< Stations >() {
                });
        }catch(BadRequestException e){
            e.getMessage();
        } 
        return stations;
    }
}
