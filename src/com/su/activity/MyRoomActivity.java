package com.su.activity;

import com.jgzs.lsw.R;
import com.su.util.AppData;

import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jgzs.lsw.R;
import com.su.ImageLoad.ImageLoader;
import com.su.util.AppData;

=======
import android.widget.RelativeLayout;

>>>>>>> 435cbe957b8ee079d517615a2babaee5440b35f5
public class MyRoomActivity extends Activity  implements OnClickListener{

	private Button button;
	private RelativeLayout detail;
	private RelativeLayout updatePassword;
	private com.su.ImageLoad.ImageLoader mImageLoader;
	private ImageView head;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myroom);
		findViewById();
		setListener();
		mImageLoader = new ImageLoader(this);
		
	}

	/**
	 * 绑定界面UI
	 */
	private void findViewById() {
		button = (Button) findViewById(R.id.myroom_button);
		detail = (RelativeLayout)findViewById(R.id.myroom_detail);
		updatePassword = (RelativeLayout)findViewById(R.id.myroom_update_password);
	    head = (ImageView)findViewById(R.id.myroom_head);
	}

	/**
	 * UI事件监听
	 */
	private void setListener() {
		detail.setOnClickListener(this);
		updatePassword.setOnClickListener(this);
		// 登录按钮监听
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// 跳转到功能引导页
				AppData.userInfo = null;
				startActivity(new Intent(MyRoomActivity.this,
						LoginActivity.class));
			}
		});
	}

	protected void onResume()
	{
		super.onResume();
		if(AppData.userInfo == null)
		{
			
			button.setText("登陆");
//			new AlertDialog.Builder(this).setTitle("提示").setMessage("您尚未登陆，是否登陆？")
//			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog, int which) {
//				startActivity(new Intent(MyRoomActivity.this,
//						LoginActivity.class));
//			}})
//			.setNegativeButton("取消",null)
//			.show();
		}
		else
		{
			button.setText("退出");
			try {
				if(AppData.userInfo.getString("avatar").length() > 0)
				{
				  mImageLoader.DisplayImage(AppData.userInfo.getString("avatar"), head, false);
				}
				} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			return;
		}
		startActivity(new Intent(MyRoomActivity.this,UpdateDetailActivity.class));
		
		break;
	case R.id.myroom_update_password:
		
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
			return;
		}
		Log.v("suzhaohui",AppData.userInfo.toString());
		startActivity(new Intent(MyRoomActivity.this,ResetPasswordActivity.class));
		break;
		
	default:
		break;
	}	
		
		
		
	}

}
