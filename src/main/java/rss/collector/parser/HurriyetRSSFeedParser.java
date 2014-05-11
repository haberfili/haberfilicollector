package rss.collector.parser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import rss.collector.Feed;
import rss.collector.FeedMessage;
import rss.collector.RSSFeedParser;

public class HurriyetRSSFeedParser implements RSSFeedParser{
	  static final String TITLE = "title";
	  static final String DESCRIPTION = "description";
	  static final String CHANNEL = "channel";
	  static final String LANGUAGE = "language";
	  static final String COPYRIGHT = "copyright";
	  static final String LINK = "link";
	  static final String AUTHOR = "author";
	  static final String ITEM = "item";
	  static final String PUB_DATE = "pubDate";
	  static final String GUID = "guid";

	  final URL url;

	  public HurriyetRSSFeedParser(String feedUrl) {
	    try {
	      this.url = new URL(feedUrl);
	    } catch (MalformedURLException e) {
	      throw new RuntimeException(e);
	    }
	  }

	  public Feed readFeed() {
	    Feed feed = null;
	    try {
	      boolean isFeedHeader = true;
	      // Set header values intial to the empty string
	      String description = "";
	      String title = "";
	      String link = "";
	      String language = "";
	      String copyright = "";
	      String author = "";
	      String pubdate = "";
	      String guid = "";
	      String picture="";

	      // First create a new XMLInputFactory
	      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
	      // Setup a new eventReader
	      InputStream in = read();
	      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
	      // read the XML document
	      while (eventReader.hasNext()) {
	        XMLEvent event = eventReader.nextEvent();
	        if (event.isStartElement()) {
	          String localPart = event.asStartElement().getName()
	              .getLocalPart();
	          switch (localPart) {
	          case ITEM:
	            if (isFeedHeader) {
	              isFeedHeader = false;
	              feed = new Feed(title, link, description, language,
	                  copyright, pubdate);
	            }
	            event = eventReader.nextEvent();
	            break;
	          case TITLE:
	            title = getCharacterData(event, eventReader);
	            break;
	          case DESCRIPTION:
	            description = getCharacterData(event, eventReader);
	            event = eventReader.nextEvent();
	            if (event instanceof Characters) {
		            description= event.asCharacters().getData();
		            if(description.contains("description")){
		            	break;
		            }
		            description=description.substring(description.indexOf("http:"));
	            }else{
	            	continue;
	            }
	            event = eventReader.nextEvent();
	            if (event instanceof Characters) {
		            description= event.asCharacters().getData();
		            if(description.contains("description")){
		            	break;
		            }
	            }else{
	            	continue;
	            }
	            event = eventReader.nextEvent();
	            if (event instanceof Characters) {
		            description= event.asCharacters().getData();
		            if(description.contains("description")){
		            	break;
		            }
	            }else{
	            	continue;
	            }
	            event = eventReader.nextEvent();
	            if (event instanceof Characters) {
		            description= event.asCharacters().getData();
		            if(description.contains("description")){
		            	break;
		            }
		            if(description.indexOf("src=")!=-1){
			            description=description.substring(description.indexOf("src=")+5);
			            picture=description.substring(description.indexOf("http:"),description.indexOf("\""));
		            }
	            }else{
	            	continue;
	            }
	            event = eventReader.nextEvent();
	            if (event instanceof Characters) {
		            description= event.asCharacters().getData();
		            if(description.contains("description")){
		            	break;
		            }
	            }else{
	            	continue;
	            }
	            if(description.indexOf("src=")!=-1){
		            description=description.substring(description.indexOf("src=")+5);
		            picture=description.substring(description.indexOf("http:"),description.indexOf("\""));
		            description=description.substring(description.indexOf("&gt;&lt;/a&gt;")+14);
		            description=description.substring(0,description.indexOf("<"));
	            }
	            break;
	          case LINK:
	            link = getCharacterData(event, eventReader);
	            break;
	          case GUID:
	            guid = getCharacterData(event, eventReader);
	            break;
	          case LANGUAGE:
	            language = getCharacterData(event, eventReader);
	            break;
	          case AUTHOR:
	            author = getCharacterData(event, eventReader);
	            break;
	          case PUB_DATE:
	            pubdate = getCharacterData(event, eventReader);
	            break;
	          case COPYRIGHT:
	            copyright = getCharacterData(event, eventReader);
	            break;
	          }
	        } else if (event.isEndElement()) {
	          if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
	            FeedMessage message = new FeedMessage();
	            message.setAuthor(author);
	            if(description.contains(">")){
	            	description="";
	            }
	            message.setDescription(description);
	            message.setGuid(guid);
	            message.setLink(link);
	            message.setTitle(title);
	            message.setPicture(picture);
	            message.setSource("hurriyet.com.tr");
	            if(!"Hürriyet ANASAYFA".equals(title)){
	            	feed.getMessages().add(message);
	            }
	            event = eventReader.nextEvent();
	            continue;
	          }
	        }
	      }
	    } catch (XMLStreamException e) {
	      throw new RuntimeException(e);
	    }
	    return feed;
	  }

	  private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
	      throws XMLStreamException {
	    String result = "";
	    event = eventReader.nextEvent();
	    if (event instanceof Characters) {
	      result = event.asCharacters().getData();
	    }
	    return result;
	  }

	  private InputStream read() {
	    try {
	      return url.openStream();
	    } catch (IOException e) {
	      throw new RuntimeException(e);
	    }
	  }
	} 
