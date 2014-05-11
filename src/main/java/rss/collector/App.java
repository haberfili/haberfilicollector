package rss.collector;

import org.quartz.JobExecutionException;

import rss.collector.Collector.HurriyetCollectorJob;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JobExecutionException
    {
    	HurriyetCollectorJob job= new HurriyetCollectorJob();
    	job.execute(null);
    }
}
