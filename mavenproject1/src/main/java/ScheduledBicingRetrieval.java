
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jorgeAleman
 */
public class ScheduledBicingRetrieval implements Job{
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Retriving bicing stations at " + new Date());
        RESTStationsService.uploadStaticStations(BicingFakeInterface.getStations());
    }
}