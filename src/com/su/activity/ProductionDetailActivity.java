package com.su.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.jgzs.lsw.R;
import com.su.ImageLoad.ImageLoader;
import com.su.database.DataAccess;
import com.su.framgment.FragmentAdapter;
import com.su.util.HttpMethod;
import com.su.util.NetManager;

public class ProductionDetailActivity extends FragmentActivity implements OnTouchListener {



    private ArrayList<String> imagelist = new ArrayList<String>();
    private com.su.ImageLoad.ImageLoader mImageLoader;
    private FragmentAdapter mAdapter;
    private ViewPager mPager;
    private int id;
    private String tittle;
    private String dec;
    private TextView tittleView;
    private TextView decView;
    private ImageView bottomView;
    private ImageView showView;
    private ScrollView decScrollView;
    private LinearLayout showlayout;
    private TextView detail_back;
    private TextView vote;
    private ProductionDetailActivity production;
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    decView.setText(dec);
                    mAdapter = new FragmentAdapter(getSupportFragmentManager(), imagelist, mImageLoader, 2);
                    mPager.setAdapter(mAdapter);
                    break;
                default:
                    break;
            }

            super.handleMessage(msg);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.production_detail_activity);
        production = this;
        mImageLoader = new ImageLoader(this);
        Intent intent1 = this.getIntent();
        id = intent1.getIntExtra("id", 1);
        tittle = intent1.getStringExtra("title");
        imagelist = intent1.getStringArrayListExtra("imagelist");
        dec = intent1.getStringExtra("content");
        initResourceRefs();
    }


    private void initResourceRefs() {
    	
    	 detail_back = (TextView) findViewById(R.id.production_detial_back);
         detail_back.setOnTouchListener(new OnTouchListener() {
             public boolean onTouch(View arg0, MotionEvent arg1) {
                 Log.v("suzhaohui", "listener is enter");
                 finish();
                 return true;
             }
         });

        tittleView = (TextView) findViewById(R.id.production_detial_title);
        decView = (TextView) findViewById(R.id.production_detail_dec);

        tittleView.setText(tittle);

        mPager = (ViewPager) findViewById(R.id.production_detail_viewpager);

        bottomView = (ImageView) findViewById(R.id.production_detail_show_button);
        showView = (ImageView) findViewById(R.id.production_show_button);
        decScrollView = (ScrollView) findViewById(R.id.production_detail_dec_scroll);

        showlayout = (LinearLayout) findViewById(R.id.production_detail_show_layout);
        showlayout.setVisibility(View.GONE);

        mPager = (ViewPager) findViewById(R.id.production_detail_viewpager);
		mAdapter = new FragmentAdapter(getSupportFragmentManager(),imagelist,mImageLoader,2);
		mPager.setAdapter(mAdapter);
		
		decView.setText(dec);
		
		vote = (TextView) findViewById(R.id.production_detail_vote);
		vote.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                Log.v("suzhaohui", "listener is enter");
                String appid = DataAccess.getAppId();
                if(appid == null)
                {
                	new AlertDialog.Builder(production).setTitle("提示").setMessage("您尚未登陆，是否登陆？")
        			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog, int which) {
        				startActivity(new Intent(ProductionDetailActivity.this,
        						LoginActivity.class));
        			}})
        			.setNegativeButton("取消",null)
        			.show();
        			return true;
                }
                voteOnclicked();
                return true;
            }
        });

    }

    public void productionhideDetail(View arg0) {
        Log.v("suzhaohui", "hideDetail");
        showlayout.setVisibility(View.GONE);
        bottomView.setVisibility(View.VISIBLE);
    }

    public void productionshowDetail(View arg0) {
        Log.v("suzhaohui", "showDetail");
        showlayout.setVisibility(View.VISIBLE);
        bottomView.setVisibility(View.GONE);
    }

    private int voteOnclicked() {
        Log.v("suzhaohui", "getDetail");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.v("suzhaohui", "getDetail");
                List<NameValuePair> tt = new ArrayList<NameValuePair>();
                tt.add(new BasicNameValuePair("app_id", DataAccess.getAppId()));
                tt.add(new BasicNameValuePair("open_id", DataAccess.getOpenId()));
                tt.add(new BasicNameValuePair("access_token", DataAccess.getAccessToken()));
                tt.add(new BasicNameValuePair("entry_id",String.valueOf(id)));

                JSONObject get = NetManager.getInstance().sendHttpRequest("contest/vote", tt, HttpMethod.POST);
                try {
                    if (get != null) {


                        if (get.isNull("error") == false) {
                            String error = get.getString("error");
                            if (error == "timeOut") {
                                handler.sendEmptyMessage(0);
                                return;
                            } else {
                                handler.sendEmptyMessage(1);
                                return;
                            }
                        }
                        int code = get.getInt("code");
                        if (code != 200) {
                            Toast.makeText(production, get.getString("message"), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Toast.makeText(production, "投票成功", Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return;
            }
        }, 1);
        return 0;
    }


    @Override
    public boolean onTouch(View arg0, MotionEvent arg1) {
        // TODO Auto-generated method stub
        return false;
    }

}

