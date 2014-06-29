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
import rss.collector.parser.MynetRSSFeedParser;
import rss.collector.parser.NTVRSSFeedParser;
import rss.collector.parser.RadikalRSSFeedParser;
import rss.collector.parser.sport.HurriyetSporRSSFeedParser;
import rss.collector.parser.sport.MynetSporRSSFeedParser;
import rss.collector.parser.sport.RadikalSporRSSFeedParser;
import rss.collector.parser.technology.HurriyetTeknolojiRSSFeedParser;
import rss.collector.parser.technology.MynetTeknolojiRSSFeedParser;
import rss.collector.parser.technology.NTVTeknolojiRSSFeedParser;
import rss.collector.parser.technology.RadikalTeknolojiRSSFeedParser;

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
		
		JobDetail jobDetailHurriyet = newJob(HurriyetCollectorJob.class).build();
		Trigger triggerHurriyet= newTrigger().startNow()
				.withSchedule(repeatSecondlyForever(2)).build();
		scheduler.scheduleJob(jobDetailHurriyet, triggerHurriyet);
		
		JobDetail jobDetailRadikalSpor = newJob(RadikalSporCollectorJob.class).build();
		Trigger triggerRadikalSpor= newTrigger().startNow()
				.withSchedule(repeatSecondlyForever(2)).build();
		scheduler.scheduleJob(jobDetailRadikalSpor, triggerRadikalSpor);
		
		JobDetail jobDetailRadikalTeknoloji = newJob(RadikalTeknolojiCollectorJob.class).build();
		Trigger triggerRadikalTeknoloji= newTrigger().startNow()
				.withSchedule(repeatSecondlyForever(2)).build();
		scheduler.scheduleJob(jobDetailRadikalTeknoloji, triggerRadikalTeknoloji);
		
		JobDetail jobDetailNTVTeknoloji = newJob(NTVTeknolojiCollectorJob.class).build();
		Trigger triggerNTVTeknoloji = newTrigger().startNow()
				.withSchedule(repeatSecondlyForever(2)).build();
		scheduler.scheduleJob(jobDetailNTVTeknoloji, triggerNTVTeknoloji);
		
		JobDetail jobDetailHurriyetSpor = newJob(HurriyetSporCollectorJob.class).build();
		Trigger triggerHurriyetSpor= newTrigger().startNow()
				.withSchedule(repeatSecondlyForever(2)).build();
		scheduler.scheduleJob(jobDetailHurriyetSpor, triggerHurriyetSpor);
		
		JobDetail jobDetailHurriyetTeknoloji = newJob(HurriyetTeknolojiCollectorJob.class).build();
		Trigger triggerHurriyetTeknoloji= newTrigger().startNow()
				.withSchedule(repeatSecondlyForever(2)).build();
		scheduler.scheduleJob(jobDetailHurriyetTeknoloji, triggerHurriyetTeknoloji);
		
		JobDetail jobDetailMynet= newJob(MynetCollectorJob.class).build();
		Trigger triggerMynet= newTrigger().startNow()
				.withSchedule(repeatSecondlyForever(2)).build();
		scheduler.scheduleJob(jobDetailMynet, triggerMynet);
		
		JobDetail jobDetailMynetTeknoloji= newJob(MynetTeknolojiCollectorJob.class).build();
		Trigger triggerMynetTeknoloji= newTrigger().startNow()
				.withSchedule(repeatSecondlyForever(2)).build();
		scheduler.scheduleJob(jobDetailMynetTeknoloji, triggerMynetTeknoloji);
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
	
	public static class RadikalSporCollectorJob implements Job {

		@Override
		public void execute(JobExecutionContext jobExecutionContext)
				throws JobExecutionException {

			RadikalSporRSSFeedParser parser = new RadikalSporRSSFeedParser(
					"http://www.radikal.com.tr/d/rss/Rss_84.xml");
			CollectorJob collectorJob= new CollectorJob();
			collectorJob.parseFeed(parser);

		}


	}
	
	public static class RadikalTeknolojiCollectorJob implements Job {

		@Override
		public void execute(JobExecutionContext jobExecutionContext)
				throws JobExecutionException {

			RadikalTeknolojiRSSFeedParser parser = new RadikalTeknolojiRSSFeedParser(
					"http://www.radikal.com.tr/d/rss/Rss_117.xml");
			CollectorJob collectorJob= new CollectorJob();
			collectorJob.parseFeed(parser);

		}


	}
	public static class NTVTeknolojiCollectorJob implements Job {

		@Override
		public void execute(JobExecutionContext jobExecutionContext)
				throws JobExecutionException {

			NTVTeknolojiRSSFeedParser parser = new NTVTeknolojiRSSFeedParser(
					"http://www.ntvmsnbc.com/id/24927532/device/rss/rss.xml");
			CollectorJob collectorJob= new CollectorJob();
			collectorJob.parseFeed(parser);

		}


	}
	
	public static class HurriyetSporCollectorJob implements Job {

		@Override
		public void execute(JobExecutionContext jobExecutionContext)
				throws JobExecutionException {

			HurriyetSporRSSFeedParser parser = new HurriyetSporRSSFeedParser(
					"http://rss.hurriyet.com.tr/rss.aspx?sectionId=14");
			CollectorJob collectorJob= new CollectorJob();
			collectorJob.parseFeed(parser);

		}


	}
	
	public static class HurriyetTeknolojiCollectorJob implements Job {

		@Override
		public void execute(JobExecutionContext jobExecutionContext)
				throws JobExecutionException {

			HurriyetTeknolojiRSSFeedParser parser = new HurriyetTeknolojiRSSFeedParser(
					"http://rss.hurriyet.com.tr/rss.aspx?sectionId=2158");
			CollectorJob collectorJob= new CollectorJob();
			collectorJob.parseFeed(parser);

		}


	}
	
	public static class MynetCollectorJob implements Job {

		@Override
		public void execute(JobExecutionContext jobExecutionContext)
				throws JobExecutionException {

			MynetRSSFeedParser parser = new MynetRSSFeedParser(
					"http://www.mynet.com/haber/rss/kategori/guncel");
			CollectorJob collectorJob= new CollectorJob();
			collectorJob.parseFeed(parser);
		}
	}
	public static class MynetSporCollectorJob implements Job {

		@Override
		public void execute(JobExecutionContext jobExecutionContext)
				throws JobExecutionException {

			MynetSporRSSFeedParser parser = new MynetSporRSSFeedParser(
					"http://spor.mynet.com/rss/");
			CollectorJob collectorJob= new CollectorJob();
			collectorJob.parseFeed(parser);
		}
	}
	
	public static class MynetTeknolojiCollectorJob implements Job {

		@Override
		public void execute(JobExecutionContext jobExecutionContext)
				throws JobExecutionException {

			MynetTeknolojiRSSFeedParser parser = new MynetTeknolojiRSSFeedParser(
					"http://www.mynet.com/haber/rss/kategori/teknoloji");
			CollectorJob collectorJob= new CollectorJob();
			collectorJob.parseFeed(parser);
		}
	}
	
}
