package com.jh.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.jh.db.RSSDatabase.RSSChannels;
import com.jh.db.RSSDatabase.RSSItems;



/**
 * <h1> 数据库辅助操作类</h1>
 * <p>实现数据库的创建/更新方法。数据库数据插入、删除、更新等等还没实现</p>
 * 
 * @author jh
 *	@version 1.0
 */
public class RSSDBHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "rss.db";
	private static final int DATABASE_VERSION = 1;	
	
	private static final String COMMA_SEP = ",";
	private static final String SPACE = " ";
	private static final String TEXT_TYPE = " TEXT";
	
	private static final String SQL_CREATE_CHANNELS_TABLE = 
			"CREATE TABLE " + RSSDatabase.RSSChannels.TABLE_NAME_RSSCHANNELS 
			+ "("
			+ RSSChannels._ID + SPACE 			+ "INTEGER PRIMARY KEY AUTOINCREMENT " + COMMA_SEP
			+ RSSChannels.TITLE + SPACE 			+ "TEXT " + COMMA_SEP
			+ RSSChannels.DESCRIPTION + SPACE 	+ "TEXT " + COMMA_SEP
			+ RSSChannels.LINK + SPACE 			+ "TEXT " + COMMA_SEP 
			+ RSSChannels.IMAGE + SPACE 		+ "BINARY " + COMMA_SEP
			+ RSSChannels.SKIPDAYS + SPACE 		+ "INTEGER "
			+ ")";
	
	private static final String SQL_CREATE_ITEMS_TABLE = 
			"CREATE TABLE  " + RSSDatabase.RSSItems.TABLE_NAME_RSSITEMS
			+ "("
			+ RSSItems._ID + SPACE + "INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP 
			+ RSSItems.CHANNEL_NAME + SPACE + " TEXT" + COMMA_SEP
			+ RSSItems.FEED_ID 	+ SPACE 	+ " INTEGER "   + COMMA_SEP
			+ RSSItems.TIME  + SPACE 		+ " TEXT" + COMMA_SEP
			+ RSSItems.TITLE + SPACE 		+ " TEXT" + COMMA_SEP
			+ RSSItems.IS_READ + SPACE 		+ " INTEGER" + COMMA_SEP 
			+ RSSItems.IS_FAVOR + SPACE 	+ " INTEGER " + COMMA_SEP
			+ RSSItems.LINK + SPACE 		+ " TEXT" + COMMA_SEP
			+ RSSItems.CONTENT + SPACE 		+ " TEXT" + COMMA_SEP
			+ RSSItems.DESCRIPTION +  SPACE + " TEXT " + COMMA_SEP
			+ RSSItems.GUID + SPACE 		+ " TEXT " 
			+ ")" ;	
	private static final String  SQL_DELETE_CHANNELS_TABLE = "DROP IF EXISTS " 
			+ RSSDatabase.RSSChannels.TABLE_NAME_RSSCHANNELS;
	private static final String  SQL_DELETE_ITEMS_TABLE = "DROP IF EXISTS " 
			+ RSSDatabase.RSSItems.TABLE_NAME_RSSITEMS;
	
	/**
	 * 构造函数
	 * 
	 * @param context 上下文对象，调用该方法的上下文
	 */
	public RSSDBHelper(Context context) {
		//What is The use of CursorFactory ? 
		this(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	

	/**
	 * 构造函数
	 * 
	 * @param context  调用的上下文对象
	 * @param name     数据库名
	 * @param factory  游标工厂
	 * @param version  数据库版本
	 */
	public RSSDBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}


	/**
	 * 创建rss_item数据库中的表
	 * 包括 ： table_article 、 table_items
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL(SQL_CREATE_CHANNELS_TABLE);
		db.execSQL(SQL_CREATE_ITEMS_TABLE);
	}


	/**
	 * 更新数据库
	 * 删除并重新创建数据库
	 * ？ 数据会丢失不？？？
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL(SQL_DELETE_CHANNELS_TABLE);
		db.execSQL(SQL_DELETE_ITEMS_TABLE);
		onCreate(db);
		
	}
	
	/**
	 * 打开数据库
	 */
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}
	
	
	/**
	 * 插入数据
	 * 没有实现
	 */
	public void insert() {

	}
	
	/**
	 * 插入数据
	 * 没有实现
	 */
	public void query() {

	}
	
	/**
	 * 插入数据
	 * 没有实现
	 */
	public void delete() {

	}
	

}
