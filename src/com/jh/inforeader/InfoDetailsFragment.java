/**
 * 
 */
package com.jh.inforeader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.jh.xml.RSSItem;

/**
 * @author jh
 * 
 */
public class InfoDetailsFragment extends Fragment {

	static final String TAG = "InfoDetailsFragment";
	private Bundle args ;

//	private String articleContent ;
	
	
	/**
	 * 
	 */
	public InfoDetailsFragment() {

	}
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "onCreate");
		args = getArguments();
		super.onCreate(savedInstanceState);

	}
	
	@Override
	public void onResume() {
		
		super.onResume();
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v(TAG, "onCreateView");
		

		// 在Activity 中还有设置 getWindow().requestFeature(Window.FEATURE_PROGRESS);
		View view = inflater.inflate(R.layout.article_webview, null);
		args = getArguments();
		
		
		return view;
		
	}



	/**
	 * @return
	 */
	private View addTextShowArticle() {
		
		ScrollView scorller = new ScrollView(getActivity());
		
		TextView article = new TextView(getActivity());
		
		int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                4, getActivity().getResources().getDisplayMetrics());
		article.setPadding(padding, padding, padding, padding);
		article.setTextSize(20);
		// super.onCreateView(inflater, container, savedInstanceState);
		article.setText("  ");
		scorller.addView(article);
		
		return scorller;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		// xuyao 
		loadArticle(getView());
		super.onActivityCreated(savedInstanceState);

	}



	/**
	 * @param view
	 */
	private void loadArticle( View view) {
		
		final Activity activity = getActivity(); 
		//activity.getWindow().requestFeature(Window.FEATURE_PROGRESS);
		WebView articleWebView = (WebView) view.findViewById(R.id.articleWv);
		
		
		if(activity != null){
			
			
			articleWebView.setWebChromeClient(new WebChromeClient() {
				public void onProgressChanged(WebView view, int progress) {
					// Activities and WebViews measure progress with different scales.
					// The progress meter will automatically disappear when we reach 100%
					activity.setProgress(progress * 1000);
				}
			});
			articleWebView.setWebViewClient(new WebViewClient() {
				public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
					Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
				}
			});
		}
		
		
		articleWebView.getSettings().setJavaScriptEnabled(true);
		articleWebView.getSettings().setBuiltInZoomControls(true);
		articleWebView.getSettings().setDefaultTextEncodingName("utf-8"); 
		
		
		
		// .toString() 可能 nullpointerException
		String articleContent = (String)args.get(RSSItem.ITEM_CONTENT);
		String link =(String)args.get(RSSItem.ITEM_LINK);
		String title = (String)args.get(RSSItem.ITEM_TITLE);
		//System.out.println(articleContent);
		if(articleContent != null){
			String myHtml = "<html><title>" 
							+ title + "</title><body><h1> "
							+ title + "</h1>"
							+ articleContent 
					+ "</body> </html>";
			System.out.println(articleContent);
			// 使用loadData 中文会出现乱码
			articleWebView.loadDataWithBaseURL(null,
					myHtml, "text/html", "utf-8", null);
			}else{
				//System.out.println(link);
				articleWebView.loadUrl(link);
		}
	}

}
