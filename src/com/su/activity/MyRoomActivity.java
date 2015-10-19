package com.su.activity;

import org.json.JSONException;

import com.jgzs.lsw.R;
import com.su.ImageLoad.ImageLoader;
import com.su.util.AppData;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MyRoomActivity extends Activity implements OnClickListener {

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
        detail = (RelativeLayout) findViewById(R.id.myroom_detail);
        updatePassword = (RelativeLayout) findViewById(R.id.myroom_update_password);
        head = (ImageView) findViewById(R.id.myroom_head);
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
                AppData.userInfo = null;
                startActivity(new Intent(MyRoomActivity.this, LoginActivity.class));
            }
        });
    }

    protected void onResume() {
        super.onResume();
        if (AppData.userInfo == null) {
            button.setText("登录");
            // new AlertDialog.Builder(this).setTitle("提示").setMessage("您尚未登录，是否登录？")
            // .setPositiveButton("确定", new DialogInterface.OnClickListener() {
            // public void onClick(DialogInterface dialog, int which) {
            // startActivity(new Intent(MyRoomActivity.this,
            // LoginActivity.class));
            // }})
            // .setNegativeButton("取消",null)
            // .show();
        } else {
            button.setText("退出");
            try {
                if (AppData.userInfo.getString("avatar").length() > 0) {
                    mImageLoader.DisplayImage(AppData.userInfo.getString("avatar"), head, false);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myroom_detail:
                if (AppData.userInfo == null) {
                    new AlertDialog.Builder(this).setTitle("提示").setMessage("您尚未登录，是否登录？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(MyRoomActivity.this, LoginActivity.class));
                        }
                    }).setNegativeButton("取消", null).show();
                    return;
                }
                startActivity(new Intent(MyRoomActivity.this, UpdateDetailActivity.class));

                break;
            case R.id.myroom_update_password:
                if (AppData.userInfo == null) {
                    new AlertDialog.Builder(this).setTitle("提示").setMessage("您尚未登录，是否登录？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(MyRoomActivity.this, LoginActivity.class));
                        }
                    }).setNegativeButton("取消", null).show();
                    return;
                }
                Log.v("suzhaohui", AppData.userInfo.toString());
                startActivity(new Intent(MyRoomActivity.this, ResetPasswordActivity.class));
                break;

            default:
                break;
        }

    }
}
