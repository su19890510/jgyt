package com.su.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.jgzs.lsw.R;
import com.su.database.DataAccess;
import com.su.ui.TabShow;
import com.su.util.AppData;
import com.su.util.HttpMethod;
import com.su.util.NetManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class MissPreferentialActivity extends Activity {
    
    public static final String TAG = "nian";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        DataAccess.init(this);
        fetchUserDetail();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MissPreferentialActivity.this, TabShow.class);
                MissPreferentialActivity.this.startActivity(intent);
                MissPreferentialActivity.this.finish();
            }
        }, 2000);
    }
    
    private void fetchUserDetail() {
        try {
            String appId = DataAccess.getAppId();
            String openId = DataAccess.getOpenId();
            String accessToken = DataAccess.getAccessToken();
            if (appId == null || openId == null || accessToken == null) {
                return;
            }
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("app_id", appId));
            pairs.add(new BasicNameValuePair("open_id", openId));
            pairs.add(new BasicNameValuePair("access_token", accessToken));
            JSONObject response = NetManager.getInstance().sendHttpRequest("account/iprofile", pairs, HttpMethod.GET);
            int code = response.getInt("code");
            if (code != 200) {
               return;
            }
            AppData.userInfo = response.getJSONObject("data").getJSONObject("user");
        } catch (Throwable ingore) {}
    }
}
