package rss.collector;

import org.quartz.JobExecutionException;

import rss.collector.Collector.NTVCollectorJob;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JobExecutionException
    {
    	NTVCollectorJob job= new NTVCollectorJob();
    	job.execute(null);
    }
}
