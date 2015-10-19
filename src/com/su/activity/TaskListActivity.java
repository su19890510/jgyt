package com.su.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.com.cctest.view.XListView;
import org.com.cctest.view.XListView.IXListViewListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
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
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import com.jgzs.lsw.R;
import com.su.ImageLoad.ImageLoader;
import com.su.activity.ProjectActivity.HoldView;
import com.su.activity.ProjectActivity.NearAdapter;
import com.su.database.DataAccess;
import com.su.model.ProjectListModel;
import com.su.model.TaskModel;
import com.su.util.HttpMethod;
import com.su.util.NetManager;

public class TaskListActivity extends Activity implements IXListViewListener{
	
	public NearAdapter nAdapter;
			
	private Handler mHandler;
	
	public XListView showList;
	
	public TaskListActivity _task;
	public float viewWidth;
	
	private ArrayList<TaskModel> items = new ArrayList<TaskModel>();
	
	private int page = 1;
	HoldView hold = new HoldView();
	
	
	private ArrayAdapter<String> mAdapter;
	private ArrayList<String> itemsa = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.task_activity);
		_task = this;
		initResourceRefs();
		initSetting();
		String name = getIntent().getStringExtra("MER_NAME");
		mHandler = new Handler();
		
 	}
	
	public void initResourceRefs() {
	
		showList = (XListView) findViewById(R.id.task_activity_list);
		showList.setPullLoadEnable(true);
        viewWidth = showList.getWidth();
	    showList.setXListViewListener(this);
	   
		nAdapter = new NearAdapter(_task);
		
	
	}
	public void initSetting() {
		
		showList.setAdapter(nAdapter);
		showList.setDivider(null);
		showList.setDividerHeight(20);
	
		
		showList.setOnItemClickListener(new OnItemClickListener() {
	
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent picIntent = new Intent(TaskListActivity.this,
						ProjectDetailActivity.class);
				
				TaskModel model = items.get(arg2 -1);
				picIntent.putExtra("id", model.getId());
				picIntent.putExtra("tittle", model.getType());
				
				TaskListActivity.this.startActivity(picIntent);
			}
		});
	
	}
	
	/**
	 * ���� ����ѡ� �е� ���� 
	 * 
	 * ���� �ǵ� ����̫������
	 * 
	 * @author yuhaiyang
	 *
	 */
	public class NearAdapter extends BaseAdapter {
	
		private com.su.ImageLoad.ImageLoader mImageLoader;
		public NearAdapter(Context context)
		{
			mImageLoader = new ImageLoader(context);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
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
				LayoutInflater inflater = LayoutInflater
						.from(TaskListActivity.this);
				convertView = inflater.inflate(R.layout.task_list_item, null);
				holdview = new HoldView();
				holdview.title = (TextView) convertView
						.findViewById(R.id.task_list_item_title);
				holdview.money = (TextView)convertView
						.findViewById(R.id.task_list_item_money);
				holdview.compant = (TextView)convertView.findViewById(R.id.task_list_item_money);
				holdview.uptime = (TextView)convertView.findViewById(R.id.task_list_item_time);

				
				convertView.setTag(holdview);
				
			
			}
			else
			{
				holdview = (HoldView) convertView.getTag();
			}
			
			if(position < items.size())
			{
				Log.v("position",String.valueOf(position));
				TaskModel model = items.get(position);
				holdview.title.setText(model.getTitle());
				holdview.money.setText(model.getReward());
				holdview.uptime.setText(model.getGmt_created());
				holdview.compant.setText(model.getUser_id());
			}
	
			return convertView;
		}
	
		public void init(View convertView) {
			
		}
	}
	
	class HoldView {
		TextView title, money, compant,uptime;
	}

	private void onLoad() {
		showList.stopRefresh();
		showList.stopLoadMore();
		SimpleDateFormat    sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");       
		String    date    =    sDateFormat.format(new    java.util.Date());  
		showList.setRefreshTime(date);
	}
	private void geneItems(int size) {
		for (int i = 0; i != size; ++i) {
//			items.add("refresh cnt " + (i));
		}
	}
	@Override
	public void onRefresh() {
		items.clear();
	
		page = 1;
		getPlantList();
		nAdapter = new NearAdapter(_task);
		showList.setAdapter(nAdapter);
		onLoad();
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				
			}
		}, 2);
	}

	@Override
	public void onLoadMore() {
		page++;
		int ret = getPlantList();
		if(ret == 2)
		{
			dandler.sendEmptyMessage(2);
			return;
		}
		nAdapter.notifyDataSetChanged();
		onLoad();
//		mHandler.postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				
//			}
//		}, 2000);
	}
	
//	private void getPlantList(String page , String )
	private int getPlantList()
	{
		List<NameValuePair>  tt = new ArrayList<NameValuePair>();
		NetManager bb=		NetManager.getInstance() ; //.toString();
		tt.add(new BasicNameValuePair("page", String.valueOf(page))); 
		 tt.add(new BasicNameValuePair("app_id", DataAccess.getAppId()));
         tt.add(new BasicNameValuePair("open_id", DataAccess.getOpenId()));
         tt.add(new BasicNameValuePair("access_token", DataAccess.getAccessToken()));
		JSONObject get = bb.sendHttpRequest("task/apply", tt, HttpMethod.GET);
		try {
			if(get != null)
			{  
				
				
				if(get.isNull("error") == false)
				{
					String error = get.getString("error");
					if(error == "timeOut")
					{
						dandler.sendEmptyMessage(0);
						return 1;
					}
					else
					{
						dandler.sendEmptyMessage(1);
						return 1;
					}
				}
				int code = get.getInt("code");
				if(code != 200 )
				{
					 Toast.makeText(_task, get.getString("message"), Toast.LENGTH_SHORT).show();
    					return -9000;
				}
				
				JSONObject data = get.getJSONObject("data");
				JSONArray plantList = data.getJSONArray("taskList");
				Log.v("suzhaohui",String.valueOf(plantList.length()));
				if(plantList.length() == 0)
				{
					return 2;
				}
				else if(plantList.length() < 10)
				{
					dandler.sendEmptyMessage(9);
				}
				else if (plantList.length() >= 10)
				{
					dandler.sendEmptyMessage(10);
				}
				for(int i = 0; i < plantList.length();i++)
				{
					JSONObject item = (JSONObject)plantList.opt(i);
					Log.v("getJSON",String.valueOf(item.getInt("id")) );
					TaskModel mode = new TaskModel();
					mode.setId(item.getInt("id"));
					mode.setTitle(item.getString("title"));
					mode.setDescripteion(item.getString("description"));
					mode.setStart_date(item.getString("start_date"));
					mode.setEnd_date(item.getString("end_date"));
					mode.setReward(item.getString("reward"));
					mode.setUser_id(item.getInt("user_id"));
					mode.setGmt_created(item.getString("gmt_created"));

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
						Toast.makeText(_task, "网络连接超时",
								Toast.LENGTH_SHORT).show();
		                break;
		            case 1:
						Toast.makeText(_task, "网络错误",
								Toast.LENGTH_SHORT).show();
		                break;
		            case -9000:
						Toast.makeText(_task, "没有查到数据",
								Toast.LENGTH_SHORT).show();
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

//			}
		};
}
