
package TwitterPublisher;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import TwitterPublisher.Jobs.TwitterPublisherBFSNClient;

/**
 * Publisher in tiwtter.
 * It creates a client who obtain the stations
 * statics from BFSN servlet and publish them
 * on twitter.
 */
public class TwitterPublisher {
    public static void main(String[] args){
        
        JobDetail job = JobBuilder.newJob(TwitterPublisherBFSNClient.class)
            .withIdentity("twitter").build();
        
        Trigger trigger = TriggerBuilder.newTrigger()
            .withSchedule(SimpleScheduleBuilder.simpleSchedule()
            .withIntervalInSeconds(60).repeatForever())
            .build();
        
        SchedulerFactory sf = new StdSchedulerFactory();
        try {
            Scheduler sched = sf.getScheduler();
            sched.start();
            sched.scheduleJob(job, trigger);
        } catch (SchedulerException ex) {
            Logger.getLogger(TwitterPublisher.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}
