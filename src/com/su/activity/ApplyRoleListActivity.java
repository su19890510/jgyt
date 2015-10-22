package com.su.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.com.cctest.view.XListView;
import org.com.cctest.view.XListView.IXListViewListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jgzs.lsw.R;
import com.su.ImageLoad.ImageLoader;
import com.su.activity.MyPublishTaskDetailActivity.HoldView;
import com.su.activity.MyPublishTaskDetailActivity.NearAdapter;
import com.su.model.ApplyPeopleModel;
import com.su.util.HttpMethod;
import com.su.util.NetManager;

public class ApplyRoleListActivity extends Activity implements OnClickListener, IXListViewListener {
    private TextView back;
    private TextView title;
    private TextView reward;
    private TextView starttime;
    private TextView endtime;
    private TextView waitapply;
    private TextView applying;
    private TextView endapply;
    private TextView dec;
    private ApplyRoleListActivity taskdetail;
    private int id;
    public XListView showList;
    public NearAdapter nAdapter;
    private ArrayList<ApplyPeopleModel> items = new ArrayList<ApplyPeopleModel>();

    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applyrole_list_activity);
        taskdetail = this;
        findViewByXmlId();
        setTextViewValue();
        onRefresh();//
    }

    private void findViewByXmlId() {
        back = (TextView) findViewById(R.id.applyrolelist_activity_back);

        back.setOnClickListener(this);

        showList = (XListView) findViewById(R.id.applyrolelist_activity_list);
        showList.setPullLoadEnable(false);
        showList.setPullRefreshEnable(false);
    }

    private void setTextViewValue() {
        Intent intent1 = this.getIntent();
        id = intent1.getIntExtra("id", 1);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.applyrolelist_activity_back:
                finish();
                break;
        }

    }

    public class NearAdapter extends BaseAdapter {

        private com.su.ImageLoad.ImageLoader mImageLoader;

        public NearAdapter(Context context) {
            mImageLoader = new ImageLoader(context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            Log.v("suzhaohui", "itemssize" + String.valueOf(items.size()));
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HoldView holdview = null;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(ApplyRoleListActivity.this);
                convertView = inflater.inflate(R.layout.applyrolelist_item, null);
                holdview = new HoldView();
                holdview.name = (TextView) convertView.findViewById(R.id.mypublishtaskdetail_list_item_name);
                holdview.contact = (TextView) convertView.findViewById(R.id.mypublishtaskdetail_list_item_phone);
                holdview.pro = (TextView) convertView.findViewById(R.id.mypublishtaskdetail_list_item_pro);


                convertView.setTag(holdview);


            } else {
                holdview = (HoldView) convertView.getTag();
            }
            Log.v("suzhaohui", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            if (position < items.size()) {
                Log.v("position", String.valueOf(position));
                ApplyPeopleModel model = items.get(position);
                holdview.name.setText("申请人:" + model.getName());
                holdview.contact.setText("联系方式：" + model.getContact());
                holdview.pro.setText(model.getPro());

            }

            return convertView;
        }

        public void init(View convertView) {

        }
    }

    class HoldView {
        TextView name, contact, pro;
    }

    private int getApplyListList() {
        List<NameValuePair> tt = new ArrayList<NameValuePair>();
        JSONObject get = NetManager.getInstance().sendHttpRequest("task/detail/" + String.valueOf(id), tt, HttpMethod.GET);
        try {
            if (get != null) {
                if (get.isNull("error") == false) {
                    String error = get.getString("error");
                    if (error == "timeOut") {
                        dandler.sendEmptyMessage(0);
                        return 1;
                    } else {
                        dandler.sendEmptyMessage(1);
                        return 1;
                    }
                }
                int code = get.getInt("code");
                if (code != 200) {
                    Toast.makeText(taskdetail, get.getString("message"), Toast.LENGTH_SHORT).show();
                    return -9000;
                }

                JSONObject data = get.getJSONObject("data");
                JSONArray plantList = data.getJSONArray("apply_user_list");
                Log.v("suzhaohui", String.valueOf(plantList.length()));

                for (int i = 0; i < plantList.length(); i++) {
                    JSONObject item = (JSONObject) plantList.opt(i);
                    ApplyPeopleModel mode = new ApplyPeopleModel();
                    mode.setName(item.getString("fullname"));
                    mode.setContact(item.getString("contact"));
                    mode.setPro(item.getString("profile"));
                    items.add(mode);
                }

            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    // 处理EmptyMessage(0)
    @SuppressLint("HandlerLeak")
    Handler dandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case 0:
                    Toast.makeText(taskdetail, "网络连接超时", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(taskdetail, "网络错误", Toast.LENGTH_SHORT).show();
                    break;
                case -9000:
                    Toast.makeText(taskdetail, "没有查到数据", Toast.LENGTH_SHORT).show();
                    showList.setPullLoadEnable(false);
                    break;
                case 9:

                    showList.setPullLoadEnable(false);
                    break;
                case 10:

                    showList.setPullLoadEnable(true);
                    break;
            }
            super.handleMessage(msg);
        }

        // }
    };

    private void onLoad() {
        showList.stopRefresh();
        showList.stopLoadMore();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        showList.setRefreshTime(date);
    }

    @Override
    public void onRefresh() {
        // TODO Auto-generated method stub
        items.clear();
        getApplyListList();
        nAdapter = new NearAdapter(taskdetail);
        showList.setAdapter(nAdapter);
        onLoad();
    }

    @Override
    public void onLoadMore() {
        // TODO Auto-generated method stub

    }
}

