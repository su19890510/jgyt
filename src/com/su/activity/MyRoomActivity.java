package com.su.activity;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jgzs.lsw.R;
import com.su.util.AppData;

public class MyRoomActivity extends Activity  implements OnClickListener{

	private Button button;
	private RelativeLayout detail;
	private RelativeLayout updatePassword;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myroom);
		findViewById();
		setListener();
		
	}

	/**
	 * 绑定界面UI
	 */
	private void findViewById() {
		button = (Button) findViewById(R.id.myroom_button);
		detail = (RelativeLayout)findViewById(R.id.myroom_detail);
		updatePassword = (RelativeLayout)findViewById(R.id.myroom_update_password);
	}

	/**
	 * UI事件监听
	 */
	private void setListener() {
		detail.setOnClickListener(this);
		updatePassword.setOnClickListener(this);
		// 登录按钮监听
//		mLogin.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//				// 跳转到功能引导页
//				startActivity(new Intent(LoginActivity.this,
//						GuideActivity.class));
////				finish();
//			}
//		});
	}

	protected void onResume()
	{
		super.onResume();
		if(AppData.userInfo == null)
		{
			
			button.setText("登陆");
			new AlertDialog.Builder(this).setTitle("提示").setMessage("您尚未登陆，是否登陆？")
			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				startActivity(new Intent(MyRoomActivity.this,
						LoginActivity.class));
			}})
			.setNegativeButton("取消",null)
			.show();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	switch (v.getId()) {
	case R.id.myroom_detail:
		if(AppData.userInfo == null)
		{
			new AlertDialog.Builder(this).setTitle("提示").setMessage("您尚未登陆，是否登陆？")
			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				startActivity(new Intent(MyRoomActivity.this,
						LoginActivity.class));
			}})
			.setNegativeButton("取消",null)
			.show();
		}
		Log.v("suzhaohui",AppData.userInfo.toString());
		startActivity(new Intent(MyRoomActivity.this,UpdateDetailActivity.class));
		
		break;
		
	default:
		break;
	}	
		
		
		
	}

}
