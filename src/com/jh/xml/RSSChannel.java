package com.jh.xml;


/**
 * Reference ： <a href="http://cyber.law.harvard.edu/rss/rss.html#whatIsRss"> RSS reference </a>
 *  
 *   RSS Channel 数据结构
 *   包含规范里的所有条目，不提供构造函数
 *   @author jh
 *   @version 1.0
 */
public class RSSChannel {

	//  
	
	/* elements
	 * description
	 * example
	 */
	
	// Required channel elements
	
	/* title
	 * The name of the channel. It's how people refer to your service. If you have an HTML website that contains the same information as your RSS file, the title of your channel should be the same as the title of your website.
	 */
	private String title;
	
	
	/* link
	 * The URL to the HTML website corresponding to the channel.
	 * http://www.goupstate.com/
	 */
	private String link;
	
	
	/* description
	 * Phrase or sentence describing the channel.
	 * The latest news from GoUpstate.com, a Spartanburg Herald-Journal Web site.
	 */
	private String description;
	
	
	
	// Optional Channel elements
	
	
	/* language
	 * The language the channel is written in. This allows aggregators to group all Italian language sites, for example, on a single page. A list of allowable values for this element, as provided by Netscape, is here. You may also use values defined by the W3C.
	 * en-us
	 */
	private String language;
	
	/* copyright
	 * Copyright notice for content in the channel.
	 * Copyright 2002, Spartanburg Herald-Journal
	 */
	private String copyright;
	
	
	/* managingEditor
	 * Email address for person responsible for editorial content.
	 * geo@herald.com (George Matesky)
	 */
	private String managingEditor;
	
	
	/* webMaster
	 * Email address for person responsible for technical issues relating to channel.
	 * betty@herald.com (Betty Guernsey)
	 */
	private String webMaster;
	
	
	/* pubDate
	 * The publication date for the content in the channel. For example, the New York Times publishes on a daily basis, the publication date flips once every 24 hours. That's when the pubDate of the channel changes. All date-times in RSS conform to the Date and Time Specification of RFC 822, with the exception that the year may be expressed with two characters or four characters (four preferred).
	 * Sat, 07 Sep 2002 00:00:01 GMT
	 */
	private String pubDate;
	
	 
	/* lastBuildDate
	 * The last time the content of the channel changed.
	 * Sat, 07 Sep 2002 09:42:31 GMT
	 */
	private String lastBuildDate;
	
	
	/* category
	 * Specify one or more categories that the channel belongs to. Follows the same rules as the <item>-level category element. More info.
	 * <category>Newspapers</category>
	 */
	private String category;
	
	
	/* generator
	 * A string indicating the program used to generate the channel.
	 * MightyInHouse Content System v2.3
	 */
	private String generator;
	
	
	/* docs
	 * A URL that points to the documentation for the format used in the RSS file. It's probably a pointer to this page. It's for people who might stumble across an RSS file on a Web server 25 years from now and wonder what it is.
	 * http://blogs.law.harvard.edu/tech/rss
	 */
	private String docs;
	
	
	/* cloud
	 * Allows processes to register with a cloud to be notified of updates to the channel, implementing a lightweight publish-subscribe protocol for RSS feeds. More info here.
	 */
	private String cloud;
	
	
	/* ttl
	 * ttl stands for time to live. It's a number of minutes that indicates how long a channel can be cached before refreshing from the source. More info here.
	 *<ttl>60</ttl>
	 */
	private String ttl;
	
	
	/* image
	 * Specifies a GIF, JPEG or PNG image that can be displayed with the channel. More info here.
	 */
	private String image;
	
	
	/* rating
	 * The PICS rating for the channel.
	 */
	private String rating;
	
	
	/* textInput
	 * Specifies a text input box that can be displayed with the channel. More info here.
	 */
	private String textInput;
	
	
	/* skipHours
	 * A hint for aggregators telling them which hours they can skip. More info here.
	 */
	private String skipHours;
	
	
	/* skipDays
	 *  A hint for aggregators telling them which days they can skip. More info here.
	 */
	private String skipDays;
	
	
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
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
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
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}



	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}



	/**
	 * @return the copyright
	 */
	public String getCopyright() {
		return copyright;
	}



	/**
	 * @param copyright the copyright to set
	 */
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}



	/**
	 * @return the managingEditor
	 */
	public String getManagingEditor() {
		return managingEditor;
	}



	/**
	 * @param managingEditor the managingEditor to set
	 */
	public void setManagingEditor(String managingEditor) {
		this.managingEditor = managingEditor;
	}



	/**
	 * @return the webMaster
	 */
	public String getWebMaster() {
		return webMaster;
	}



	/**
	 * @param webMaster the webMaster to set
	 */
	public void setWebMaster(String webMaster) {
		this.webMaster = webMaster;
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
	 * @return the lastBuildDate
	 */
	public String getLastBuildDate() {
		return lastBuildDate;
	}



	/**
	 * @param lastBuildDate the lastBuildDate to set
	 */
	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
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
	 * @return the generator
	 */
	public String getGenerator() {
		return generator;
	}



	/**
	 * @param generator the generator to set
	 */
	public void setGenerator(String generator) {
		this.generator = generator;
	}



	/**
	 * @return the docs
	 */
	public String getDocs() {
		return docs;
	}



	/**
	 * @param docs the docs to set
	 */
	public void setDocs(String docs) {
		this.docs = docs;
	}



	/**
	 * @return the cloud
	 */
	public String getCloud() {
		return cloud;
	}



	/**
	 * @param cloud the cloud to set
	 */
	public void setCloud(String cloud) {
		this.cloud = cloud;
	}



	/**
	 * @return the ttl
	 */
	public String getTtl() {
		return ttl;
	}



	/**
	 * @param ttl the ttl to set
	 */
	public void setTtl(String ttl) {
		this.ttl = ttl;
	}



	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}



	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}



	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}



	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}



	/**
	 * @return the textInput
	 */
	public String getTextInput() {
		return textInput;
	}



	/**
	 * @param textInput the textInput to set
	 */
	public void setTextInput(String textInput) {
		this.textInput = textInput;
	}



	/**
	 * @return the skipHours
	 */
	public String getSkipHours() {
		return skipHours;
	}



	/**
	 * @param skipHours the skipHours to set
	 */
	public void setSkipHours(String skipHours) {
		this.skipHours = skipHours;
	}



	/**
	 * @return the skipDays
	 */
	public String getSkipDays() {
		return skipDays;
	}



	/**
	 * @param skipDays the skipDays to set
	 */
	public void setSkipDays(String skipDays) {
		this.skipDays = skipDays;
	}




	
	
	public RSSChannel() {
		
	}

}
