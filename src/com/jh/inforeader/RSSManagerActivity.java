package com.jh.inforeader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jh.db.RSSDBHelper;
import com.jh.db.RSSDatabase;
import com.jh.util.Utils;
import com.jh.xml.OPML;
import com.jh.xml.OPMLParser;

/**
 * 解析 OPML 文件，载入RSS 源列表
 * 
 * @author jh
 * @version 1.0
 */
public class RSSManagerActivity extends ListActivity {

	protected static final String TAG = "RSSManagerActivity";

	private List<HashMap<String, Object>> data = null; //

	private ListView listView;
	private CheckableSimpleAdapter adapter;
	
	private SlidingMenu slidingMenu ;

	

	/**
	 * 对应与数据源中 Item 的选中状态
	 */
	private boolean[] selectedState;

	private RSSDBHelper dbHelper;
	
	
	public SlidingMenu getSlidingMenu() {
		return slidingMenu;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_rssmanager);
		
		setContentView(R.layout.activity_rssmanager);		// set the content view
		setupActionBar();
		
		addSlidingMenu();									// configure the SlidingMenu
	
		if (0 == Utils.useTimeCount(this)) {
			Utils.showToast(this, "first time open the app");
		}
		checkNetWork();
		loadChannelList();
		setListViewListener();

		dbHelper = new RSSDBHelper(this);					// initial the database helper object
	}

	
	private void checkNetWork() {
		int networkStatus = Utils.checkNetworkState(this);
		
		switch (networkStatus) {
		case Utils.NO_NETWORK_AVAILABLE:
			Utils.showToast(this, "网络不可用");
			break;
		case Utils.MOBLE_AVAILABLE:
			Utils.showToast(this, "使用数据流量中，小心话费");
			break;
		
		default:
			Utils.showToast(this, "使用WIFI,畅享资讯");
			break;
		}
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	@SuppressLint("NewApi")
	private void setupActionBar() {
		
		ActionBar actionbar = getActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		actionbar.setIcon(R.drawable.ic_launcher);
		actionbar.setTitle(R.string.all_subscribe);
		
	}
	
	
	/**
	 *  添加SlidingMenu 
	 */
	private void addSlidingMenu() {
		
		setTitle(R.string.attach);
		slidingMenu = new SlidingMenu(this);
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		slidingMenu.setShadowDrawable(R.drawable.shadow);
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setMenu(R.layout.menu);
	}

	/**
	 * 开启AsyncTask 解析 opml 的xmlRSS channel 列表，并在完成时通知UI线程更新UI
	 */
	private void loadChannelList() {
		LoaderRSSChannelTask loadTask = new LoaderRSSChannelTask();
		loadTask.execute("help");
	}

	@SuppressLint("ResourceAsColor")
	private void setListViewListener() {

		listView = getListView();
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(  Utils.hasNetWork(getApplicationContext())  ){
					Utils.showToast(
							getApplicationContext(),
							data.get(position).get(OPML.TITLE) + " \n "
									+ data.get(position).get(OPML.LINK));
	
					startArticleListActivity(position);
				}else{
					Utils.showToast(
							getApplicationContext(),
							" 网络不可用... 休息一下吧");
				}
			}

			/**
			 * @param position
			 *            选中的 channel 在channel 列表中的位置
			 */
			private void startArticleListActivity(int position) {
				Bundle args = new Bundle();
				args.putString(OPML.LINK,
						(String) data.get(position).get(OPML.LINK));
				args.putString(OPML.TITLE,
						(String) data.get(position).get(OPML.TITLE));
				Intent intent = new Intent(getApplicationContext(),
						InfoTitleListActivity.class);
				intent.putExtras(args);
				startActivity(intent);
			}
		});

		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {

			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				return false;
			}

			/**
			 * 左边的 勾 图标
			 * 
			 * @see
			 * android.view.ActionMode.Callback#onDestroyActionMode(android.
			 * view.ActionMode)
			 */
			@Override
			public void onDestroyActionMode(ActionMode mode) {
				Log.v(TAG, "onDestroyActionMode");
				releaseAll();

			}

			/**
			 * 载入菜单-ActionBar item
			 * 
			 * @see
			 * android.view.ActionMode.Callback#onCreateActionMode(android.view
			 * .ActionMode, android.view.Menu)
			 */
			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				MenuInflater inflater = mode.getMenuInflater();
				inflater.inflate(R.menu.channel_action_mode, menu);

				return true;
			}

			/**
			 * 选中ActionBar 中Item， 取消则在onDestoryActionBar 回调方法中
			 */
			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				int id = item.getItemId();
				if (id == R.id.subscribe_channel) {

					
					insertChannel();

					releaseAll();
					return true;
				} else {
					return false;
				}

			}

			/**
			 * 插入订阅到数据库
			 * Insert the information of channel that user want to subscribe
			 * into DB ,before inserting ,check if the channel does exists in DB
			 * 
			 */
			@SuppressWarnings("unchecked")
			private void insertChannel() {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				Cursor result = null;
				
				HashMap<String, Object> tMap = new HashMap<String, Object>();

				for (int i = 0; i < selectedState.length; i++) {
					if ( selectedState[i] ) {
						
						tMap = (HashMap<String, Object>) adapter.getItem(i);
						String channelTitle = (String) tMap.get(OPML.TITLE);
						
						result = db.query(
								RSSDatabase.RSSChannels.TABLE_NAME_RSSCHANNELS, // table
								new String[] { 									// columns
								RSSDatabase.RSSChannels.TITLE,
										RSSDatabase.RSSChannels.LINK, },
								RSSDatabase.RSSChannels.TITLE + " = ?", 		// selection
								new String[] { 									// selectionargs
								channelTitle }, null, null, null);
						
						// if the record does not xists, insert it.
						if (result.getCount() == 0) { 
							values.put(RSSDatabase.RSSChannels.TITLE,
									(String) tMap.get(OPML.TITLE));
							values.put(RSSDatabase.RSSChannels.LINK,
									(String) tMap.get(OPML.LINK));

							db.insert(
									RSSDatabase.RSSChannels.TABLE_NAME_RSSCHANNELS,
									null, values);
						}
					}
				}
				db.close(); // close the Database when not use
			}

			/**
			 * 释放已经选中的 Listview item （在 点击ActionBar 或者取消时）
			 * 
			 */
			private void releaseAll() {
				for (int i = 0; i < selectedState.length; i++) {
					if (selectedState[i]) {
						adapter.setCheckedState(i, false);
						listView.setItemChecked(i, false);
					}
				}
				adapter.notifyDataSetChanged();
			}

			/**
			 * Item状态改变时触发， 在此设置checkBox 的 checked 状态。
			 * 
			 * @see android.widget.AbsListView.MultiChoiceModeListener#onItemCheckedStateChanged(android.view.ActionMode,
			 *      int, long, boolean)
			 */
			@Override
			public void onItemCheckedStateChanged(ActionMode mode,
					int position, long id, boolean checked) {
				selectedState[position] = checked;
				Log.v(TAG, "onItemCheckedStateChanged " + checked);
				adapter.setCheckedState(position, checked);
				adapter.notifyDataSetChanged();

			}
		});
	}

	/** 
	 * 后台载入channel 列表
	 * 
	 * 
	 */
	private class LoaderRSSChannelTask extends
			AsyncTask<String, Integer, Integer> {

		private static final String TAG = "LoaderRSSTask";

		
		@Override
		protected Integer doInBackground(String... params) {

			Log.v(TAG, "doInBackground");

			// setupActionBar();
			InputStream stream = null;
			OPMLParser opmlParser = new OPMLParser(getApplication());
			List<OPML> entries = null;

			try {
				stream = getAssets().open(
						getString(R.string.rss_list_xml).toString());
				entries = opmlParser.parse(stream);
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (stream != null) {
					try {
						stream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			int i = 0;
			data = new ArrayList<HashMap<String, Object>>();
			for (; i < entries.size(); i++) {

				Map<String, Object> map = new HashMap<String, Object>();
				map.put(OPML.TITLE, entries.get(i).getTitle());
				map.put(OPML.LINK, entries.get(i).getXmlUrl());
				map.put(OPML.CHECKED, false);
				data.add((HashMap<String, Object>) map);

				if (i % 10 == 0) {
					publishProgress(i % 10);
				}

			}
			// 一个数组对应Adapter 中 CheckBox 的选中状态，由于需要知道其大小，故在此。
			selectedState = new boolean[i];

			return i % 10;
		}

		/**
		 * 解析完成时载入channel 列表数据，并通知主线程更新UI
		 * 
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(Integer result) {
			Log.v(TAG, "onPostExecute");
			adapter = new CheckableSimpleAdapter(
					getApplication(),
					data,
					R.layout.rss_simple_list_item_2_with_checkbox,
					new String[] { OPML.TITLE, OPML.LINK, OPML.CHECKED },
					new int[] { R.id.text1, R.id.text2, R.id.checked_indicator });

			setListAdapter(adapter);
			// getListView().
			super.onPostExecute(result);
		}

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rssmanager, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			break;
		/*
		 * 从数据库中读出订阅条目
		 */
		case R.id.action_my_subscribe:
			Log.v(TAG, "选中 订阅 条目");
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			Cursor result =	db.query(RSSDatabase.RSSChannels.TABLE_NAME_RSSCHANNELS, 
					new String[] {
					RSSDatabase.RSSChannels.TITLE,
					RSSDatabase.RSSChannels.LINK
					},
					null, // all rows of selected table
					null,
					null, 
					null,
					null);
			data.clear();
			while( result.moveToNext() ){
				HashMap<String, Object> map = new HashMap<String , Object>();
				map.put(OPML.TITLE,  (String)result.getString(0) );
				Log.v(TAG, (String)result.getString(1));
				map.put(OPML.LINK,  (String)result.getString(1) );
				map.put(OPML.CHECKED, false   );
				data.add(map);
			}
			getActionBar().setTitle(R.string.my_subscribe);
			adapter.notifyDataSetChanged();
			
			break;
			
			/*
			 * 列出 opml 文件中的所有条目
			 */
		case R.id.action_all_subscribe:
			
			loadChannelList();
			adapter.notifyDataSetChanged();
			
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
