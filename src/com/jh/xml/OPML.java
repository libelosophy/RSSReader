package com.jh.xml;



/**
 * OPML 数据结构
 * 
 * @author jh
 * @version 1.0
 */
public class OPML {

	/**
	 * {@value TITLE} 取出和放入map的key
	 */
	public static final String TITLE = "channel_title";
	public static final String LINK = "channel_xmlsource";
	public static final String CHECKED = "checked";
	
	// XmlPullParser
	// private String head;
	/**
	 * 
	 */
	private String category; // outline without xmlUrl & htmlUrl
	private String title;
	private String xmlUrl;

	// private String htmlUrl;

	private OPML() {

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
	 * @return the xmlUrl
	 */
	public String getXmlUrl() {
		return xmlUrl;
	}

	/**
	 * @param xmlUrl the xmlUrl to set
	 */
	public void setXmlUrl(String xmlUrl) {
		this.xmlUrl = xmlUrl;
	}

	/**
	 * 构造函数
	 * @param category  channel 分类
	 * @param title		channel 标题
	 * @param xmlUrl    channel xmlUrl
	 */
	public OPML(String category, String title, String xmlUrl) {

		// this.head = head;
		this.category = category;
		this.title = title;
		this.xmlUrl = xmlUrl;
		// this.htmlUrl = htmlUrl;
	}

}
