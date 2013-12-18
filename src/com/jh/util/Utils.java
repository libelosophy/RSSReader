package com.jh.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;



/**
 * Utils for some operations
 * 
 * @author jh
 * @version 1.0
 */
public class Utils {

	
	
	
	/**
	 * 显示Toast tips
	 * @param context 调用的上下文
	 * @param msg     The message to be shown 
	 */
	public static void showToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
			
	}
	
	/**
	 * 统计程序被打开的次数，获取，并加1
	 * @param context
	 * @return 应用程序被打开的次数， 如果没有被打开过，则返回0
	 */
	public static int useTimeCount(Context context){
		final String SHAREPREFERENCE = "TIMES"; 
		final String TIMES = "TIMES";
	    SharedPreferences prefTimes = context.getSharedPreferences(SHAREPREFERENCE, context.MODE_PRIVATE);
		
	    Editor editor = prefTimes.edit();
	    
	    int time =  prefTimes.getInt(TIMES,0);
	    showToast(context, " times: " + time );
	    editor.putInt(TIMES, ++time);
	    editor.commit(); // commit to sharepreference
	    return time; 	
	}
	
	/**
	 * 检查网络
	 * 
	 */
	
	public static final int  WIFI_AVAILABLE = 0;
	public static final int  WIFI_MOBLE_AVAILABLE = 1;
	public static final int  MOBLE_AVAILABLE = 2;
	public static final int  NO_NETWORK_AVAILABLE = 3;
	
	public static int checkNetworkState(Context context) {
		
		ConnectivityManager connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI); 
		boolean isWifiConn = networkInfo.isConnected();
		networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		boolean isMobileConn = networkInfo.isConnected();
		if( isWifiConn & (!isMobileConn)){
			return WIFI_AVAILABLE;
		}else if( isMobileConn & (!isWifiConn) ){
			return MOBLE_AVAILABLE;
		}else if( isWifiConn & !isMobileConn ){
			return WIFI_MOBLE_AVAILABLE;
		}else {
			return NO_NETWORK_AVAILABLE;
		}
	}
	
	public static boolean hasNetWork(Context context) {
		
		ConnectivityManager connMgr = (ConnectivityManager)context.
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netWorkInfo = connMgr.getActiveNetworkInfo();
		return ( netWorkInfo != null && netWorkInfo.isConnected());
		
	} 
	
	
//	public static boolean hasNetWork(Context context){
//		int status = Utils.checkNetworkState(context);
//		if( status == NO_NETWORK_AVAILABLE ){
//			return false;
//		}else {
//			return  true;
//		}
//			
//	}
			
	
	
}
