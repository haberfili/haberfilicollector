package rss.collector;

import models.News;
import mongo.DBConnector;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.repeatSecondlyForever;
import static org.quartz.TriggerBuilder.newTrigger;

import com.google.code.morphia.Datastore;

public class Collector {

	public static void run() throws Exception {
		main(null);
	}

	public static void main(String[] args) throws Exception {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		scheduler.start();

		JobDetail jobDetail = newJob(CollectorJob.class).build();

		Trigger trigger = newTrigger().startNow()
				.withSchedule(repeatSecondlyForever(2)).build();

		scheduler.scheduleJob(jobDetail, trigger);
	}

	public static class CollectorJob implements Job {

		@Override
		public void execute(JobExecutionContext jobExecutionContext)
				throws JobExecutionException {

			RSSFeedParser parser = new RSSFeedParser(
					"http://www.ntvmsnbc.com/id/3032091/device/rss/rss.xml");
			Feed feed = parser.readFeed();
			System.out.println(feed);
			Datastore datasource = null;
			try {
				datasource = DBConnector.getDatasource();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (FeedMessage message : feed.getMessages()) {
				if (datasource.find(News.class)
						.filter("link", message.getLink()).countAll() == 0) {
					News news = new News();
					news.title = message.getTitle();
					news.image = message.getPicture();
					news.link = message.getLink();
					news.detail = message.getDescription();
					news.createDate = System.currentTimeMillis();
					datasource.save(news);
				}

			}

		}
	}
}
