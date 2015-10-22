package com.su.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.jgzs.lsw.R;
import com.su.database.DataAccess;
import com.su.util.HttpMethod;
import com.su.util.NetManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class TaskDetailActivity extends Activity implements OnClickListener {
    private TextView back;
    private TextView apply;
    private TextView title;
    private TextView reward;
    private TextView starttime;
    private TextView endtime;
    private TextView waitapply;
    private TextView applying;
    private TextView endapply;
    private TextView dec;
    private TaskDetailActivity taskdetail;
    private int id;

    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_detail);
        taskdetail = this;
        findViewByXmlId();
        setTextViewValue();

    }

    private void findViewByXmlId() {
        back = (TextView) findViewById(R.id.taskdetail_back);
        apply = (TextView) findViewById(R.id.taskdetail_apply);
        title = (TextView) findViewById(R.id.taskdetail_title);
        reward = (TextView) findViewById(R.id.taskdetail_money);
        starttime = (TextView) findViewById(R.id.taskdetail_begintime);
        endtime = (TextView) findViewById(R.id.taskdetail_endtime);
        waitapply = (TextView) findViewById(R.id.taskdetail_waitapply);
        applying = (TextView) findViewById(R.id.taskdetail_applying);
        endapply = (TextView) findViewById(R.id.taskdetail_applyend);
        dec = (TextView) findViewById(R.id.taskdetail_dec);

        apply.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void setTextViewValue() {
        Intent intent1 = this.getIntent();
        id = intent1.getIntExtra("id", 1);
        String titlestr = intent1.getStringExtra("title");
        String rewardstr = intent1.getStringExtra("reward");
        String starttimestr = intent1.getStringExtra("starttime");
        String endtimestr = intent1.getStringExtra("endtime");
        String decstr = intent1.getStringExtra("dec");
        int entertype = intent1.getIntExtra("entertype", 1);
        if (entertype == 2) {
            apply.setVisibility(View.GONE);
        }
        int type = intent1.getIntExtra("teyp", 1);

        title.setText(titlestr);
        reward.setText("价格: " + rewardstr);
        starttime.setText(starttimestr);
        endtime.setText(endtimestr);
        dec.setText(decstr);

        if (type == 1) {
            waitapply.setTextColor(getResources().getColor(R.color.type_red));
        } else if (type == 2) {
            applying.setTextColor(getResources().getColor(R.color.type_red));
        } else if (type == 3) {
            endapply.setTextColor(getResources().getColor(R.color.type_red));
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.taskdetail_apply:
                applyTask();
                break;
            case R.id.taskdetail_back:
                finish();
                break;
        }

    }

    private void applyTask() {
        List<NameValuePair> tt = new ArrayList<NameValuePair>();

        tt.add(new BasicNameValuePair("task_id", String.valueOf(id)));
        tt.add(new BasicNameValuePair("app_id", DataAccess.getAppId()));
        tt.add(new BasicNameValuePair("open_id", DataAccess.getOpenId()));
        tt.add(new BasicNameValuePair("access_token", DataAccess.getAccessToken()));

        try {
            JSONObject response = NetManager.getInstance().sendHttpRequest("task/apply", tt, HttpMethod.POST);
            int code = response.getInt("code");
            if (code != 200) {
                Toast.makeText(this, response.getString("message"), Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "已经申请", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.v("API Error", "Update Profile Error.", e);
        }
    }

}
