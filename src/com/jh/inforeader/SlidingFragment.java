package com.jh.inforeader;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class SlidingFragment extends ListFragment {

	private List<String> menu;
	private RSSManagerActivity mActivity;
	
	public SlidingFragment() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		mActivity =  (RSSManagerActivity) activity;
		super.onAttach(activity);
	}
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		menu = new ArrayList<String>();
		menu.add("我的订阅");
		menu.add("发现更多");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getActivity(),
				R.layout.list_item_1,
				menu);
		setListAdapter(adapter);
		
		
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				if(position == 0){
					
					
				}else{
					
				}
				
			}
			
			
			
		});
	}
	
	

}
