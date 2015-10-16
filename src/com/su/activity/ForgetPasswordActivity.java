package com.su.activity;

import com.jgzs.lsw.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class ForgetPasswordActivity extends Activity  {
	private Button mLogin;
    private InputMethodManager manager ;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.forgetpassword);
		manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		LinearLayout activity_main = (LinearLayout) findViewById(R.id.forget_activity_main);
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
		mLogin = (Button) findViewById(R.id.forget_activity_confirm);
	}

	/**
	 * UI事件监听
	 */
	private void setListener() {
		// 登录按钮监听
		mLogin.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// 跳转到功能引导页
//				startActivity(new Intent(LoginActivity.this,
//						GuideActivity.class));
//				finish();
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

