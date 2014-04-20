package rss.collector;

import java.util.Date;

import models.News;
import mongo.DBConnector;

import com.google.code.morphia.Datastore;

public class Collector {

	public static void run() throws Exception{
		main(null);
	}
	public static void main(String[] args) throws Exception {

		RSSFeedParser parser = new RSSFeedParser("http://www.ntvmsnbc.com/id/3032091/device/rss/rss.xml");
		Feed feed = parser.readFeed();
		System.out.println(feed);
		Datastore datasource = DBConnector.getDatasource();
		for (FeedMessage message : feed.getMessages()) {
			if(datasource.find(News.class).filter("link", message.getLink()).countAll()==0){
				News news= new News();
				news.title=message.getTitle();
				news.image=message.getPicture();
				news.link=message.getLink();
				news.detail=message.getDescription();
				news.createDate=System.currentTimeMillis();
				datasource.save(news);	
			}
			
		}

	}

}
