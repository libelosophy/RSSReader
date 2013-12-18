package com.jh.xml;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;





// Reference : http://cyber.law.harvard.edu/rss/rss.html#whatIsRss

public class RSSItem implements Parcelable{

	public static final String ITEM_TITLE = "item_title";
	public static final String ITEM_CONTENT = "item_content";
	public static final String ITEM_LINK = "item_link";
	public static final String ITEM_DESCRIPTION = "item_desciption";
	public static final String TITLE = "item_title";
	
	
	/*
	 * The title of the item.
	 * Venice Film Festival Tries to Quit Sinking
	 */
	private String title;
	
	
	/*
	 * The URL of the item.
	 * http://nytimes.com/2004/12/07FEST.html
	 */
	private String link;
	
	
	/*
	 * The item synopsis.
	 * Some of the most heated chatter at the Venice Film Festival this week was about the way that the arrival of the stars at the Palazzo del Cinema was being staged.
	 */
	private String description;     		

	/*
	 * Email address of the author of the item.More.	
	 * oprah\@oxygen.net
	 */
	private String author;
	
	
	/*
	 * Includes the item in one or more categories. More.
	 */
	private String category;	
	
	
	/*
	 * URL of a page for comments relating to the item.More.	
	 * http://www.myblog.org/cgi-local/mt/mt-comments.cgi?entry_id=290
	 */
	private String comments;
	
	
	/*
	 * Describes a media object that is attached to the item. More.
	 */
	private String enclosure;
	
	
	/*
	 * A string that uniquely identifies the item. More.
	 * http://inessential.com/2002/09/01.php#a2
	 */
	private String guid;
	
	
	/*
	 * Indicates when the item was published. More.
	 * Sun, 19 May 2002 15:21:36 GMT
	 */
	private String pubDate;	
	
	
	/*
	 * The RSS channel that the item came from. More.
	 */
	private RSSChannel sourceChannel;			
	
	
	private String content;
	
	
	public RSSItem() {
		
	}



	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}



	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}



	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}



	/**
	 * @param localName the link to set
	 */
	public void setLink(String localName) {
		this.link = localName;
	}



	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}



	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}



	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}



	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}



	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}



	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}



	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}



	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}



	/**
	 * @return the enclosure
	 */
	public String getEnclosure() {
		return enclosure;
	}



	/**
	 * @param enclosure the enclosure to set
	 */
	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}



	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}



	/**
	 * @param guid the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}



	/**
	 * @return the pubDate
	 */
	public String getPubDate() {
		return pubDate;
	}



	/**
	 * @param pubDate the pubDate to set
	 */
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}



	/**
	 * @return the source
	 */
	public RSSChannel getSource() {
		return sourceChannel;
	}



	/**
	 * @param source the source to set
	 */
	public void setSource(RSSChannel source) {
		this.sourceChannel = source;
	}



	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
		
	}

	
	
	
	
	
	public RSSItem(Parcel in){
		
	}
	
	
	
	static Creator CREATOR = new Parcelable.Creator() {

		@Override
		public Object createFromParcel(Parcel source) {
			return null;
		}

		@Override
		public Object[] newArray(int size) {
			return null;
		}
	};
	
	
	
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}

	
}
