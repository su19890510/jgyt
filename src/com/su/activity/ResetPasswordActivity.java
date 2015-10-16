package com.su.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jgzs.lsw.R;
import com.su.database.DataAccess;
import com.su.util.AppData;
import com.su.util.HttpMethod;
import com.su.util.NetManager;

public class ResetPasswordActivity extends Activity  {
	private Button mLogin;
    private InputMethodManager manager ;
    private ResetPasswordActivity reset;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.resetpassword);
		reset = this;
		manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		LinearLayout activity_main = (LinearLayout) findViewById(R.id.resetpassword_activity_main);
		activity_main.setOnTouchListener(new OnTouchListener()  
		{  
		              
		    public boolean onTouch(View arg0, MotionEvent arg1)  
		    {  
		    	Log.v("suzhaohui","listener is enter");
		        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);  
		        return imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);  
		    }  
		});  
		findViewById();
		setListener();
	}

	/**
	 * 绑定界面UI
	 */
	private void findViewById() {
		mLogin = (Button) findViewById(R.id.resetpassword_activity_confirmbutton);
		
	}

	/**
	 * UI事件监听
	 */
	private void setListener() {
		// 登录按钮监听
		mLogin.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Log.v("suzhaohui","onclicked");
				String oldpassword = String.valueOf(((TextView)findViewById(R.id.resetpassword_activity_oldpassword)).getText());
				String newpassword = String.valueOf(((TextView)findViewById(R.id.resetpassword_activity_newpassword)).getText());
				
				if(oldpassword.trim().length()<=0)
				{
					Toast.makeText(reset, "旧密码不能为空",
							Toast.LENGTH_SHORT).show();
					return;
				}
				
				
				
				if(newpassword.trim().length()<=0)
				{
					Toast.makeText(reset, "新密码不能为空",
							Toast.LENGTH_SHORT).show();
					return;
				}
				
				List<NameValuePair>  tt = new ArrayList<NameValuePair>();
				NetManager bb=		NetManager.getInstance() ; //.toString();
				tt.add(new BasicNameValuePair("old_passwd",oldpassword)); 
				tt.add(new BasicNameValuePair("new_passwd",newpassword));
				if(AppData.userInfo != null)
				 {
					 tt.add(new BasicNameValuePair("app_id", DataAccess.getAppId()));
		                tt.add(new BasicNameValuePair("open_id", DataAccess.getOpenId()));
		                tt.add(new BasicNameValuePair("access_token", DataAccess.getAccessToken()));
		                
					
				 }
				JSONObject get = bb.sendHttpRequest("account/reset_passwd", tt, HttpMethod.GET);
				if(get == null)
				{
					Log.v("suzhaohui","login get is null");
					return;
				}
				try {
					int code = get.getInt("code");
					if(code != 200)
					{
						Toast.makeText(reset, get.getString("message"),
								Toast.LENGTH_SHORT).show();
						return;
					}
					AppData.userInfo = get.getJSONObject("data").getJSONObject("user");
					finish();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
	});
	}

	@Override  
	 public boolean onTouchEvent(MotionEvent event) {  
	  // TODO Auto-generated method stub  
	  if(event.getAction() == MotionEvent.ACTION_DOWN){  
	     if(getCurrentFocus()!=null && getCurrentFocus().getWindowToken()!=null){  
	       manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);  
	     }  
	  }  
	  return super.onTouchEvent(event);  
	 }


}

