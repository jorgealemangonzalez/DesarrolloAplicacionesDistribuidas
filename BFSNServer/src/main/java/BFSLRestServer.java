import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
/**
 *
 * @author jorgeAleman
 */
public class BFSLRestServer {
    public static void main(String[] args) throws IOException {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(15000).build();
        Set< Class<?> > services = new HashSet< Class<?> > ();
        services.add(RESTClientService.class);
        services.add(RESTStationsService.class);
        ResourceConfig config = new ResourceConfig(services);//TODO see if that works
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
        System.out.println("Server started...");
        
        System.out.println("Now creating scheduled bicing information retrieval...");
        
        // Specify the job' s details..
        JobDetail job = JobBuilder.newJob(ScheduledBicingRetrieval.class)
            .withIdentity("BFSL").build();
        // Specify the running period of the job
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
            Logger.getLogger(BFSLRestServer.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
