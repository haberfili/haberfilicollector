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
}
