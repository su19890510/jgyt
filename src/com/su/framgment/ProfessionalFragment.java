package com.su.framgment;

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
import android.support.v4.app.Fragment;
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
import com.su.model.ProfessionModel;
import com.su.util.NetManager;

public class ProfessionalFragment extends Fragment implements IXListViewListener{
	
	
	
	public NearAdapter nAdapter;		
	public XListView showList;
	public Activity profession;	
	private ArrayList<ProfessionModel> items = new ArrayList<ProfessionModel>();
	View startView;
	public ProfessionalFragment(Activity _matchActivity)
	{
		profession = _matchActivity;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

	    startView=inflater.inflate(R.layout.profession_acitivy, null);
		super.onCreate(savedInstanceState);
		findView();
		initSetting();
		onRefresh();
		return startView;
 	}
	
	public void findView() {
	
		showList = (XListView) startView.findViewById(R.id.pro_activity_list);
		showList.setPullLoadEnable(false);
		showList.setPullRefreshEnable(true);
	    showList.setXListViewListener(this);
		nAdapter = new NearAdapter(profession);
	
	}
	
	public void initSetting() {	
		showList.setAdapter(nAdapter);
		showList.setDivider(null);
		showList.setDividerHeight(5);	
	}
	
	
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
						.from(profession);
				convertView = inflater.inflate(R.layout.pro_item, null);
				holdview = new HoldView();
				holdview.name = (TextView) convertView
						.findViewById(R.id.pro_activity_item_name);
				holdview.title = (TextView)convertView
						.findViewById(R.id.pro_activity_item_tittle);
				holdview.pro = (TextView)convertView
						.findViewById(R.id.pro_activity_item_pro);
				holdview.headimg = (ImageView)convertView
						.findViewById(R.id.pro_item_head);
				convertView.setTag(holdview);
				
			
			}
			else
			{
				holdview = (HoldView) convertView.getTag();
			}
			
			if(position < items.size())
			{
				Log.v("position",String.valueOf(position));
				ProfessionModel model = items.get(position);
				holdview.name.setText(model.getName());
				holdview.title.setText(model.getTitle());
				holdview.pro.setText(model.getPro());
				Log.v("cover",model.getImgurl());
				mImageLoader.DisplayImage(model.getImgurl(), holdview.headimg, false);
			}
	
			return convertView;
		}
	
	}
	
	class HoldView {
		TextView name, title, pro;
		ImageView headimg;
	}

	private void onLoad() {
		showList.stopRefresh();
		showList.stopLoadMore();
		SimpleDateFormat    sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");       
		String    date    =    sDateFormat.format(new    java.util.Date());  
		showList.setRefreshTime(date);
	}
	@Override
	public void onRefresh() {
		items.clear();
		getProList();
		nAdapter = new NearAdapter(profession);
		showList.setAdapter(nAdapter);
		onLoad();
		
	}

	@Override
	public void onLoadMore() {
		
	}
	
	private int getProList()
	{
		List<NameValuePair>  tt = new ArrayList<NameValuePair>();
		NetManager bb=NetManager.getInstance() ; //.toString();	
		int matchID = 0;
		try {
			matchID = AbstractFragment.match.getJSONObject("data").getJSONObject("contest").getInt("id");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONObject get = bb.sendHttpRequest("contest/judges/"+String.valueOf(matchID), tt, 0);
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
					dandler.sendEmptyMessage(-9000);
					return -9000;
				}
				
				JSONObject data = get.getJSONObject("data");
				JSONArray plantList = data.getJSONArray("judgesList");
				for(int i = 0; i < plantList.length();i++)
				{
					JSONObject item = (JSONObject)plantList.opt(i);
					ProfessionModel mode = new ProfessionModel();				
					mode.setName(item.getString("name"));
					mode.setTitle(item.getString("title"));
					mode.setPro(item.getString("profile"));
					mode.setImgurl(item.getString("avatar"));
					items.add(mode);
			}
			
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return 0;
	}
	

	    @SuppressLint("HandlerLeak")
		Handler dandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				 switch (msg.what) {
		            case 0:
						Toast.makeText(profession, "网络连接超时",
								Toast.LENGTH_SHORT).show();
		                break;
		            case 1:
						Toast.makeText(profession, "网络错误",
								Toast.LENGTH_SHORT).show();
		                break;
		        
		            }
		            super.handleMessage(msg);
		        }
		};
}
