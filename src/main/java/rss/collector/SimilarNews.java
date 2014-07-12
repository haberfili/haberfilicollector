package rss.collector;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class SimilarNews {

	public void getSimilarNews(String id) {
		try {

			Client client = Client.create();
			String url = "http://neofilihaberlucene.herokuapp.com/haberfililucene/SimilarNews/findSimilarNews/" + id;
			WebResource webResource = client.resource(url);
			webResource.accept(MediaType.WILDCARD).get(ClientResponse.class);

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
