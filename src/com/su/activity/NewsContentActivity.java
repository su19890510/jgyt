package com.su.activity;

import com.jgzs.lsw.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.widget.TextView;

public class NewsContentActivity extends Activity {
	private TextView title;
	private WebView webview;
	private String titlestr;
	private String content;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.news_content_activity);
		Intent intent1 = this.getIntent(); 
		titlestr = intent1.getStringExtra("title");
		content = intent1.getStringExtra("content");
        findView();
	}
	private void findView()
	{
		webview = (WebView)findViewById(R.id.news_content_activity_webview);
		title = (TextView)findViewById(R.id.news_content_activity_title);
		title.setText(titlestr);
		webview.loadDataWithBaseURL("http://www.jcodecraeer.com", content, "text/html", "utf-8",null);

	}
}
