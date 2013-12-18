/**
 * 
 */
package com.jh.inforeader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jh.xml.OPML;
import com.jh.xml.RSSHandler;
import com.jh.xml.RSSItem;

/**
 * @author jh
 * 
 */
public class InfoTitleFragment extends ListFragment {

	private static final String TAG = "BlogTitleFragment";


	private List<RSSItem> ItemList;
	private List<HashMap<String, String>> data;
	private RSSServiceTask loadDataTask ;

	private String link;
	
	private onTitleSelectedListener mListener;

	// container activity must implements this interface
	public interface onTitleSelectedListener {

		public void onTitleSelected(int position, Bundle data);

	}

	
	public InfoTitleFragment() {
		Log.v(TAG, " constructor");
	}

	/**
	 * onAttach 的重载，    1.在其中检测宿主Activity 是否实现了 ListView 的Item点击 事件的监听接口
	 * 					2.因为Fragment是静态的写在Xml layout中，由于Fragment 和 Activity 的生命周期函数的调用顺序
	 * 	调用activity 的onCreate 之后，调用 Fragment 的 onAttach onCreate onCreateView onViewCreated，
	 * 	故不等点击Listview 就执行了这一系列回调方法，而创建View时 channel 的xmlUrl还没有传递过来。
	 * @see android.app.Fragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach(Activity activity) {
		Log.v(TAG, "  onAttach");
		
		
		link = activity.getIntent().getExtras().getString(OPML.LINK);
		
		// 检查Activity 是否实现了onTitleSelectedListerner
		try {
			mListener = (onTitleSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(
					activity.toString()
							+ "You Activity must implements the onTitleSelectedListener interface.");
		}
		activity.getActionBar().setTitle(activity.getIntent().getExtras().getString(OPML.TITLE));
		super.onAttach(activity);
	}

	/*@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, " onCreate");
		//link = getArguments().getString(RSSManagerActivity.LINK);
		super.onCreate(savedInstanceState);

	}*/

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v(TAG, "onCreateView ");
		
		return inflater.inflate(R.layout.info_title_list, container, true);
		// return super.onCreateView(inflater, container, savedInstanceState);
	}

	/**
	 * 设置数据源及相关
	 */
	@SuppressLint("NewApi")
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		Log.v(TAG, " onViewCreated");
		
		super.onViewCreated(view, savedInstanceState);
		setListShown(true);// 不要显示ListFragment 默认布局自带的ProgressBar（无法控制）

		loadDataTask = new RSSServiceTask();
		
		loadDataTask.executeOnExecutor(
				AsyncTask.THREAD_POOL_EXECUTOR,	link);

		
	}

	
	@Override
	public void onResume() {
		Log.v(TAG, "onResume");
	
		super.onResume();
	}

	@Override
	public void onStop() {
		Log.v(TAG, "onStop");
		super.onStop();
	}
	@Override
	public void onPause() {
		Log.v(TAG, "onPause");
		
		loadDataTask.cancel(true);
		//loadDataTask = null;
		
		super.onPause();
		
	}

	
	@Override
	public void onDestroyView() {
		Log.v(TAG, "onDestroyView");
		
		super.onDestroyView();
		
	}

	// listView 条目点击事件监听回调函数
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Log.v(TAG, "onItemClick ononononono");

		Bundle data = new Bundle();
		data.putString(RSSItem.ITEM_TITLE, ItemList.get(position).getTitle());
		data.putString(RSSItem.ITEM_DESCRIPTION, ItemList.get(position).getDescription());
		data.putString(RSSItem.ITEM_LINK, ItemList.get(position).getLink());
		data.putString(RSSItem.ITEM_CONTENT, ItemList.get(position).getContent());
		
		mListener.onTitleSelected(position, data);
		super.onListItemClick(l, v, position, id);

	}

	
	// 在下面的onViewCreated 中被调用
	private class RSSServiceTask extends AsyncTask<String, Integer, Integer> {

		View mView = getView().findViewById(R.id.progressContainer);

		ProgressBar mProgress = (ProgressBar) getView().findViewById(
				R.id.progress_item_load);
		TextView mProgressText = (TextView) getView().findViewById(
				R.id.progress_text);
		int totalProgress = 0;

		public RSSServiceTask( ) {
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			if (values[0] == -1) {
				Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
			}if(values[0] == -2){
				Toast.makeText(getActivity(), "RSS 源有点小问题哦", Toast.LENGTH_SHORT).show();
				cancel(true);
			}
			else {
				mProgress.setProgress(values[0]);
				mProgressText.setText("Load " + values + "/" + totalProgress);
			}
			super.onProgressUpdate(values);

		}

		
		private void checkURL(String urlStr) throws IOException {
			if(!urlStr.startsWith("http://") || !urlStr.startsWith("https://") ){
				urlStr += "http://";
			}
			
			URLConnection uc = new URL(urlStr).openConnection();
			uc.connect();
		}
		
		/*
		 * 由InfoTitleFragment 传入params（URL），然后由这个异步任务解析
		 *  如果RSS 地址不可用？ 如果
		 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
		 */
		@Override
		protected Integer doInBackground(String... params) {
			Log.v(TAG, "RSSServiceTask - doInBackground");
			
			URL xmlSource = null;
			BufferedInputStream bis = null;

			RSSHandler handler = new RSSHandler();
			SAXParserFactory spf = SAXParserFactory.newInstance();
			
			// 支持namespace，如果不支持，localName(without prefix)为null
			spf.setNamespaceAware(true); 
			
				try {
					xmlSource = new URL(params[0]);
					bis = new BufferedInputStream(xmlSource.openStream());
					SAXParser saxParser = spf.newSAXParser();
					saxParser.parse(bis, handler);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// xmlSource.openStream() throw
					e.printStackTrace();
					// xml 解析出错，取消任务。
					publishProgress(-2);
					
					//return -1;
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				}
				
			
			ItemList = handler.getItemList();
			totalProgress = ItemList.size();
			data = new ArrayList<HashMap<String, String>>();
			int i = 0;
			for (; i < totalProgress; i++) {
				// Item 列表
				
				HashMap<String, String> map = new HashMap<String, String>();
				
				
				
				map.put(RSSItem.ITEM_TITLE, ItemList.get(i).getTitle());
				Log.v(TAG, "title title " + ItemList.get(i).getTitle());
				String des = ItemList.get(i).getDescription();
				if(des != null){
					des = des.length() > 200  ? des.substring(0, 150) + "..." : des;
				}
				
				map.put(RSSItem.ITEM_DESCRIPTION, des+ "\n"
						+ ItemList.get(i).getLink() + "......");
				map.put(RSSItem.ITEM_CONTENT, ItemList.get(i).getContent());
				map.put(RSSItem.ITEM_LINK, ItemList.get(i).getLink());
				data.add(map);
				publishProgress(i);// 是这个函数 ，不是on。。。
			}
			return i;
		}

		/*
		 * 数据加载完成时，将其bind 到 ListFragment 的Listview 并隐藏进度条
		 * 
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(Integer result) {
			Log.v(TAG, "RSSServiceTask - onPostExecute");
			
			super.onPostExecute(result);
			mProgressText.setText("Load finish.");
			mView.setVisibility(View.GONE); 
			//setListShown(true);

			setListAdapter(new SimpleAdapter(getActivity(), data,
					R.layout.rss_simple_list_item_2,
					new String[] { RSSItem.ITEM_TITLE, RSSItem.ITEM_DESCRIPTION}, new int[] {
							R.id.text1, R.id.text2 }));// 为什么用这个setListAdapter
																		// 可以，用getListview().setAdapter
			// 就不行。

		}

		@Override
		protected void onCancelled(Integer result) {
			Log.v(TAG, "RSSServiceTask -onCancelled ");
			//Toast.makeText(getActivity(), "RSS 源有点问题哦", Toast.LENGTH_SHORT).show();
			super.onCancelled(result);
			mView.setVisibility(View.GONE); 
			
		}
	}

}
