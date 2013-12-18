package com.jh.inforeader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;

public class InfoDetailsActivity extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		
		setContentView(R.layout.activity_blog_details);
		getActionBar().setIcon(R.drawable.ic_launcher);
		hideActionBar();
		// 如是横屏
		if ((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)) {
			finish();
			return;
		}

		
		if (savedInstanceState == null) {

			Fragment detailsFragment = new InfoDetailsFragment();
			detailsFragment.setArguments(getIntent().getExtras());
			getFragmentManager().beginTransaction()
					.add(R.id.details_standalone, detailsFragment,
								InfoDetailsFragment.TAG)
					.commit();
			// transaction.addToBackStack("Initial");
		}
		
		
		

	}

	private void hideActionBar() {
		// TODO Auto-generated method stub
		getActionBar().hide();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.blog_details, menu);
		return true;
	}

}
