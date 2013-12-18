package com.jh.inforeader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class CheckableSimpleAdapter extends BaseAdapter {

	private Context mContext;
//	private Map<Integer, Boolean> mCheckedMap;
	private int mLayoutResourceId;
	private int[] mTo;
	private String[] mFrom;
	private List<HashMap<String, Object >> mData;
	private LayoutInflater mInflater;
	
	
	public CheckableSimpleAdapter(
			Context context,
			List<HashMap<String, Object >> data,
			int layoutResourceId,
			String[] from,
			int[] to
			){
		
		this.mContext = context;
		this.mLayoutResourceId =layoutResourceId;
		this.mData = data;
		this.mFrom = from;
		this.mTo = to;
		this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public boolean getCheckedState(int positon) {
		return (Boolean) mData.get(positon).get(mFrom[2]);
	}
	
	public void setCheckedState(int position, boolean isChecked){
		//getView(position, null, null).findViewById(R.id.checked_indicator).setChecked(true);
		
		if(((Boolean)mData.get(position).get(mFrom[2])) != isChecked ){
			HashMap<String, Object> tMap = new HashMap<String, Object>();
			tMap.put(mFrom[0], mData.get(position).get(mFrom[0]));
			tMap.put(mFrom[1], mData.get(position).get(mFrom[1]));
			tMap.put(mFrom[2], isChecked);
			mData.remove(position);
			mData.add(position, tMap);
		}
		
	}
	
	@Override
	public int getCount() {
		return mData.size();
	}
	
	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = mInflater.inflate(mLayoutResourceId, null);
			holder.textView1 = (TextView) convertView.findViewById(mTo[0]);
			holder.textView2 = (TextView) convertView.findViewById(mTo[1]);
			holder.checkBox =  (CheckBox) convertView.findViewById(mTo[2]);
		
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag(); 
		}
		
		holder.textView1.setText(mData.get(position)
				.get(mFrom[0]).toString());
		
		holder.textView2.setText(mData.get(position)
				.get(mFrom[1]).toString());
		holder.checkBox.setChecked((Boolean)
				mData.get(position).get(mFrom[2]));
		
		return convertView;
	}
	
	
	private class ViewHolder{
		
		TextView textView1;
		TextView textView2;
		CheckBox checkBox;

		public ViewHolder(){
			
		}
	}
	
}
