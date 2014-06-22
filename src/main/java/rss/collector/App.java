package rss.collector;

import org.quartz.JobExecutionException;

import rss.collector.Collector.HurriyetSporCollectorJob;
import rss.collector.Collector.HurriyetTeknolojiCollectorJob;
import rss.collector.Collector.NTVTeknolojiCollectorJob;
import rss.collector.Collector.RadikalSporCollectorJob;
import rss.collector.Collector.RadikalTeknolojiCollectorJob;
import rss.collector.parser.sport.HurriyetSporRSSFeedParser;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JobExecutionException
    {
    	HurriyetTeknolojiCollectorJob job= new HurriyetTeknolojiCollectorJob();
    	job.execute(null);
    }
}
