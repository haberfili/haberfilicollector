package rss.collector;

import org.quartz.JobExecutionException;

import rss.collector.Collector.MynetCollectorJob;
import rss.collector.Collector.MynetSporCollectorJob;
import rss.collector.Collector.MynetTeknolojiCollectorJob;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JobExecutionException
    {
    	MynetSporCollectorJob job= new MynetSporCollectorJob();
    	job.execute(null);
    }
}
