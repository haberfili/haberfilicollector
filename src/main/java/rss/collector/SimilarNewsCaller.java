package rss.collector;

public class SimilarNewsCaller implements Runnable {
	
	private String id;
	public SimilarNewsCaller(String id){
		this.id=id;
	}

	@Override
	public void run() {
		SimilarNews similarNews= new SimilarNews();
		similarNews.getSimilarNews(id);
		
	}

}
