/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterPublisher;

import TwitterPublisher.Beans.StationsStatistics;
import TwitterPublisher.Connexions.TwitterInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import twitter4j.TwitterException;

/**
 *
 * @author jorgeAleman
 */
public class TwitterPublisherBFSNClient implements Job{

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        
        Client client = ClientBuilder.newClient();
        WebTarget BFSNpublicAPI = client.target("http://localhost:15000").path("stations/getStationsStatistics");
// Add items
        StationsStatistics statistics =  BFSNpublicAPI.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<StationsStatistics>(){});
        
        String message = this.parseStatics(statistics);
        
        try {
            TwitterInterface.publish(message);
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterPublisherBFSNClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //TODO a lo mejor cojer por getters
    public String parseStatics(StationsStatistics ss){
        String parsed = "Stations statistics:\n";
        parsed+= "Average altitude: "+ss.getAverageAltitude()+"\n";
        parsed+= "Total free slots: "+ss.getTotalFreeSlots()+"\n";
        parsed+= "Total number of stations: "+ss.getTotalNumberStations()+"\n";
        parsed+= "Total ocupied slots: "+ss.getTotalOcupiedSlots()+"\n";
        
        return parsed;
    }
    
}
