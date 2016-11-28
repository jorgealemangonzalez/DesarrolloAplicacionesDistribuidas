package TwitterPublisher.Jobs;

import TwitterPublisher.Beans.StationsStatistics;
import TwitterPublisher.Connexions.TwitterInterface;

import java.util.LinkedList;
import java.util.List;
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
        String fullMsg = this.parseStatics(statistics);
        
        List<String> messages = new LinkedList<String>();
        Integer beginId = 0;
        //Split message into 140 characters messages or less
        while(beginId + 139 < fullMsg.length()){
        	String message = fullMsg.substring(beginId, 139 + beginId);
        	if(message.contains("\n")){
        		Integer lastLineJump = message.lastIndexOf('\n');
            	message = message.substring(beginId,lastLineJump);
            	beginId = lastLineJump+1;
            	messages.add(message);
        	}else{
        		System.out.println("Some of the statistics are longer than 140 characters or have no endLine character");
        		break;
        	}
        	
        }
        if(beginId != fullMsg.length()){
        	messages.add(fullMsg.substring(beginId));
        }
        
        try {
        	for(String message : messages)
        		TwitterInterface.publish(message);
        	System.out.println("The statistics has been published");
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
        parsed+= "Stations without bikes: "+stationsStatistics.getStationsWithoutSlots()+"\n";
        
        return parsed;
    }
    
}
