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
//    	Runnable r= new SimilarNewsCaller("53c1bd0284829717c2bbde32");
//		new Thread(r).start();
    }
}
