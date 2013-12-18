package com.jh.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class RSSDBOperator {

	private SQLiteDatabase db ;
	
	public RSSDBOperator(SQLiteDatabase db ) {
		this.db = db;
	}
	

	
	private void insertChannel(ContentValues values) {
			db.insert(RSSDatabase.RSSChannels.TABLE_NAME_RSSCHANNELS,
					null, values);		
	}
	
	
	
	
}
