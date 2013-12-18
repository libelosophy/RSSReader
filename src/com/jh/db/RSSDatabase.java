package com.jh.db;

import android.provider.BaseColumns;

/*
 * 数据库模式
 * database schema
 */
public class RSSDatabase {
	
	// make construct private to prevent others  instancing this class
	private  RSSDatabase() {
	}

	//public static final String RSS_DATABASE_NAME = "rss.db";
	

	/*
	 * see com.jh.rss.RSSChannel
	 */
	public static final class RSSChannels implements BaseColumns {
		private RSSChannels() {
		}

		public static final String TABLE_NAME_RSSCHANNELS = "table_rsschannels";
		public static final String TITLE = "channel_name";
		public static final String DESCRIPTION = "channel_desription";
		public static final String LINK = "channel_link";
		public static final String IMAGE = "channel_image"; 
		public static final String SKIPDAYS = "channel_skipdays";
		
		public static final String DEFAULT_SORTORDER = TITLE + " DESC ";
	}
	
	
	/*
	 * see com.jh.rss.RSSItem
	 */
	public static final class RSSItems implements BaseColumns{
		public RSSItems() {}
		
		public static final String TABLE_NAME_RSSITEMS = "table_rssitems";
		
		public static final String CHANNEL_NAME = "channel_name"; 
		public static final String FEED_ID = "feed_id";
		public static final String TIME = "time"; 		//pubDate
		public static final String TITLE = "title";
		public static final String LINK = "link";
		public static final String CONTENT = "content"; // content:encoded
		public static final String DESCRIPTION = "description";
		public static final String IS_READ = "is_read";
		public static final String IS_FAVOR = "is_favor";
		public static final String GUID = "guid";
		
		public static final String DEFAULT_SORTORDER = TITLE + " DESC ";
		
	}

}
