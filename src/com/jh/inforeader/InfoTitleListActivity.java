package com.jh.inforeader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.jh.db.RSSDBHelper;
import com.jh.inforeader.InfoTitleFragment.onTitleSelectedListener;
import com.jh.util.Utils;
import com.jh.xml.OPML;

public class InfoTitleListActivity extends Activity implements
		onTitleSelectedListener {

	private static final String TAG = "BlogTitleListActivity";
	private RSSDBHelper dbHelper ;
	private Bundle args;
	private FragmentManager mFragmentManager;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "onCreate");
		setupActionBar();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blog_list);
		
		
		mFragmentManager = getFragmentManager();
		args = getIntent().getExtras();
		
		System.out.println(args.get(OPML.LINK));
		
		
		//dbHelper = new RSSDBHelper();
		//dbHelper.createDataBase(this, null, 0, null);
		//dbHelper.createTable();
		//dbHelper.closeDatabase();
	}
	
	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	@SuppressLint("NewApi")
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setIcon(R.drawable.ic_launcher);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.blog_list, menu);
		return true;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		
	}
	
	/**
	 * @author jh
	 * 点击标题后被调用，如果当前布局为Protrait 则开启另一个Activity-- BlogDetailsActivity， 
	 * 否则将一个BlogDetailsFragment 添加到Framlayout中来以显示文章内容。
	 */
	@Override
	public void onTitleSelected(int position, Bundle data) {
		Log.v(TAG, "onTitleSelected");
		
		Utils.showToast(this, "onclicked");
		
		FragmentTransaction transaction = mFragmentManager.beginTransaction();

		//发送给infoDetailsFragment 的数据
		
		if (findViewById(R.id.details) != null) {
			// landscape
			Log.v(TAG, "framlayout do exists.");
			Fragment fragment = new InfoDetailsFragment();
			
			fragment.setArguments(data); // 传递给fragment 的参数怎么处理
			transaction.replace(R.id.details, fragment, InfoDetailsFragment.TAG);
			//transaction.addToBackStack(null);
			transaction.commit();

		} else {
			// protrait
			Log.v(TAG, "framelayout do not exists");

			Intent intent = new Intent(this, InfoDetailsActivity.class);
			intent.putExtras(data);  // 传递给activity 
			startActivity(intent);

		}
	}

}
