package rss.collector;

public class FeedMessage {

	  String title;
	  String description;
	  String descriptionMore;
	  String link;
	  String author;
	  String guid;
	  String picture;
	  private String source;
	  private String category;

	  public String getTitle() {
	    return title;
	  }

	  public void setTitle(String title) {
	    this.title = title;
	  }

	  public String getDescription() {
	    return description;
	  }

	  public void setDescription(String description) {
	    this.description = description;
	  }

	  public String getLink() {
	    return link;
	  }

	  public void setLink(String link) {
	    this.link = link;
	  }

	  public String getAuthor() {
	    return author;
	  }

	  public void setAuthor(String author) {
	    this.author = author;
	  }

	  public String getGuid() {
	    return guid;
	  }

	  public void setGuid(String guid) {
	    this.guid = guid;
	  }

	  @Override
	  public String toString() {
	    return "FeedMessage [title=" + title + ", description=" + description
	        + ", link=" + link + ", author=" + author + ", guid=" + guid
	        + "]";
	  }

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDescriptionMore() {
		return descriptionMore;
	}

	public void setDescriptionMore(String descriptionMore) {
		this.descriptionMore = descriptionMore;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
