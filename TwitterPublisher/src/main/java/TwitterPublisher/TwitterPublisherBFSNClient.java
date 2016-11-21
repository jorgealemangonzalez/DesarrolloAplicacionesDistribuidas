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
 * Client who connect to BFSN servlet and
 * relies the message to publish on twitter
 * to TwitterInterface.
 * @author jorgeAleman
 */
public class TwitterPublisherBFSNClient implements Job{

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        
        Client client = ClientBuilder.newClient();
        WebTarget BFSNpublicAPI = client.target("http://localhost:15000").path("stations/getStationsStatistics");
        // get statistics
        StationsStatistics statistics =  BFSNpublicAPI.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<StationsStatistics>(){});
        //Parse them to obtain a plain text message
        String message = this.parseStatics(statistics);
        
        try {
            TwitterInterface.publish(message);
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterPublisherBFSNClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Create a plain text description of the statistics of stations.
     * @param stationsStatistics
     * @return the parsed statistics inside a string object
     */
    public String parseStatics(StationsStatistics stationsStatistics){
        String parsed = "Stations statistics:\n";
        parsed+= "Average altitude: "+stationsStatistics.getAverageAltitude()+"\n";
        parsed+= "Total free slots: "+stationsStatistics.getTotalFreeSlots()+"\n";
        parsed+= "Total number of stations: "+stationsStatistics.getTotalNumberStations()+"\n";
        parsed+= "Total ocupied slots: "+stationsStatistics.getTotalOcupiedSlots()+"\n";
        parsed+= "Total stations without bikes: "+stationsStatistics.getStationsWithoutSlots()+"\n";
        
        return parsed;
    }
    
}
