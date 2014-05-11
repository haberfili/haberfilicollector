package rss.collector;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.repeatSecondlyForever;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import rss.collector.parser.HurriyetRSSFeedParser;
import rss.collector.parser.NTVRSSFeedParser;
import rss.collector.parser.RadikalRSSFeedParser;

public class Collector {

	public static void run() throws Exception {
		main(null);
	}

	public static void main(String[] args) throws Exception {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.start();

		JobDetail jobDetailNTV = newJob(NTVCollectorJob.class).build();
		Trigger triggerNTV = newTrigger().startNow()
				.withSchedule(repeatSecondlyForever(2)).build();
		scheduler.scheduleJob(jobDetailNTV, triggerNTV);
		
		JobDetail jobDetailRadikal = newJob(RadikalCollectorJob.class).build();
		Trigger triggerRadikal= newTrigger().startNow()
				.withSchedule(repeatSecondlyForever(2)).build();
		scheduler.scheduleJob(jobDetailRadikal, triggerRadikal);
		
//		JobDetail jobDetailHurriyet = newJob(HurriyetCollectorJob.class).build();
//		Trigger triggerHurriyet= newTrigger().startNow()
//				.withSchedule(repeatSecondlyForever(2)).build();
//		scheduler.scheduleJob(jobDetailHurriyet, triggerHurriyet);
		
	}

	public static class NTVCollectorJob implements Job {

		@Override
		public void execute(JobExecutionContext jobExecutionContext)
				throws JobExecutionException {

			NTVRSSFeedParser parser = new NTVRSSFeedParser(
					"http://www.ntvmsnbc.com/id/3032091/device/rss/rss.xml");
			CollectorJob collectorJob= new CollectorJob();
			collectorJob.parseFeed(parser);

		}


	}
	public static class RadikalCollectorJob implements Job {

		@Override
		public void execute(JobExecutionContext jobExecutionContext)
				throws JobExecutionException {

			RadikalRSSFeedParser parser = new RadikalRSSFeedParser(
					"http://www.radikal.com.tr/d/rss/RssSD.xml");
			CollectorJob collectorJob= new CollectorJob();
			collectorJob.parseFeed(parser);

		}


	}
	public static class HurriyetCollectorJob implements Job {

		@Override
		public void execute(JobExecutionContext jobExecutionContext)
				throws JobExecutionException {

			HurriyetRSSFeedParser parser = new HurriyetRSSFeedParser(
					"http://rss.hurriyet.com.tr/rss.aspx?sectionId=1");
			CollectorJob collectorJob= new CollectorJob();
			collectorJob.parseFeed(parser);

		}


	}
}
