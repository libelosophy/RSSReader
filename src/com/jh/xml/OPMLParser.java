package com.jh.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

/*
 *  XmlPullParser
 *  http://developer.android.com/training/basics/network-ops/xml.html#instantiate
 */
/**
 * OPML 文件解析器驱动
 * @author jh
 *
 */
public class OPMLParser  {

	private static final String TAG = "OPMLParser";

	// do not use namespace
	private static final String ns = null;

	private Context context;

	public OPMLParser(Context context) {
		this.context = context;
	}

	/**
	 * 解析
	 * @param in OPML的XML文件的输入流
	 * @return 一个包含该OPML 文件中所有channel 条目的 ArrayList<OPML>
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	// Instantiate the Parser
	public List<OPML> parse(InputStream in) throws XmlPullParserException,
			IOException {
		Log.v(TAG, "parse");

		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(in, null);
			parser.nextTag();
			System.out.println(parser.getAttributeName(0));
			return readFeed(parser);
		} finally {
			in.close();
		}

	}

	/**
	 * 解析Channel 条目
	 * @param parser xmlPullParser 解析器
	 * @return Channel 列表
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	// Read the Feed
	private List<OPML> readFeed(XmlPullParser parser) throws XmlPullParserException,
			IOException {
		Log.v(TAG, "readFeed");

		List<OPML> entries = new ArrayList<OPML>();
		OPML currentOpml = null;
		
		// set according to the return of parser.isEmptyElementTag() 
		// 		if it is true ,the element is the item, otherwise, category.
		boolean isItem = false;
		// String head = null;
		String category = null; // outline without xmlUrl & htmlUrl
		String title = null;
		String xmlUrl = null;

		parser.require(XmlPullParser.START_TAG, ns, "opml");
		//System.out.println(parser.getName());
		int count = 0;
		int state = parser.next();
		while (state != XmlPullParser.END_DOCUMENT) {
			state = parser.next();
			//System.out.println(count++);
			if (state == XmlPullParser.TEXT) {
				//System.out.println(parser.getText());
				continue;
			}
			if (state == XmlPullParser.START_TAG
					&& parser.getName().equals("outline")) {
				
				if ( parser.isEmptyElementTag()) {
					isItem = true;
					title = parser.getAttributeValue(null, "title");
					xmlUrl = parser.getAttributeValue(null, "xmlUrl");
				} else {
					isItem = false;
					category = parser.getAttributeValue(null, "title");
				}
				System.out.println(category + " " + title + " " + xmlUrl);
				continue;
			}
			if (state == XmlPullParser.END_TAG
					&& parser.getName().equals("outline") && isItem) {
				
				isItem  = false;
				
				currentOpml = new OPML(category, title, xmlUrl);
				entries.add(currentOpml);
				continue;
			}
		}
		return entries;

	}

	// not need to skip
	/*private void skip(XmlPullParser parser) throws XmlPullParserException,
			IOException {
		// TODO Auto-generated method stub

		Log.v(TAG, "skip");

		if (parser.getEventType() != XmlPullParser.START_TAG) {
			throw new IllegalStateException();
		}
		int depth = 1;
		while (depth != 0) {
			switch (parser.next()) {
			case XmlPullParser.END_TAG:
				depth--;
				break;
			case XmlPullParser.START_TAG:
				depth++;
				break;
			}
		}
	}*/

	
}