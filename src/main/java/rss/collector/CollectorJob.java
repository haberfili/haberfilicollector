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
				if(message.getDescription().contains(">")){
					message.setDescription("");
				}
				if(message.getDescription().length()>140){
					String more=message.getDescription().substring(140);
					message.setDescriptionMore(more);
					message.setDescription(message.getDescription().substring(0,140)+"...");
				}
				if(message.title.contains("Hürriyet ANASAYFA")){
					continue;
				}
				if("http://media.ntvmsnbc.com/j/NTVMSNBC/Components/ArtAndPhoto-Fronts/Sections-StoryLevel/Arsiv/140425-corridor.thumb.jpg".equals(message.getLink())){
					message.setLink("");
				}
				if(message.getDescription()==null || message.getDescription().length()<15){
					continue;
				}
				if(message.getTitle()==null || message.getTitle().length()<5){
					continue;
				}
				News news = new News();
				news.title = message.getTitle();
				news.image = message.getPicture();
				news.link = message.getLink();
				news.detail = message.getDescription();
				news.detailMore=message.getDescriptionMore();
				news.source=message.getSource();
				news.category=message.getCategory();
				news.createDate = System.currentTimeMillis();
				datasource.save(news);
				Runnable r= new SimilarNewsCaller(news.id.toString());
				new Thread(r).start();
			}

		}
	}
}
