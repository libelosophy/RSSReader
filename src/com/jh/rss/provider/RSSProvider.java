package com.jh.rss.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class RSSProvider extends ContentProvider {
/*
 * 1. 实现内容提供器接口
 * 2. 定义数据URI
 * 3. 定义数据列
 * 4. 实现 方法
 * 		query()
 * 		
 *  
 * 
 * */
	
	//1.  定义数据uri
	public static final Uri CONTENT_URI = 
			Uri.parse("com.jh.rss.provider.RSSProvider");
	
	//
	
	
	
	
	
	
	public RSSProvider() {
	}

	@Override
	public boolean onCreate() {
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		return null;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

}
