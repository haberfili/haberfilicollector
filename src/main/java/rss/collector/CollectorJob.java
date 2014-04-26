package rss.collector;

import models.News;
import mongo.DBConnector;

import com.google.code.morphia.Datastore;

public class CollectorJob {
	public void parseFeed(RSSFeedParser parser) {
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
				if(message.getDescription().contains(">")){
					message.setDescription("");
				}
				if(message.getDescription().length()>150){
					message.setDescription(message.getDescription().substring(0,150)+"...");
				}
				news.detail = message.getDescription();
				news.source=message.getSource();
				news.createDate = System.currentTimeMillis();
				datasource.save(news);
			}

		}
	}
}
