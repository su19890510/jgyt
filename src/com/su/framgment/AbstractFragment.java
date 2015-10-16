package com.su.framgment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.jgzs.lsw.R;
import com.su.util.NetManager;

public class AbstractFragment extends Fragment implements OnClickListener{
	
	private TextView abstractTextView;
	private TextView titleTextView;
	private TextView needTextView;
	private TextView scheduleTextView;
	private TextView prizeTextView;
	private TextView commTextView;
	private TextView zhubanTextView;
	private TextView xiebanTextView;
	private TextView chengbanTextView;
	View startView;
	public static JSONObject match = null;
	private Activity matchActivity;
	private WebView webview;
	

	private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
	
	public AbstractFragment(Activity _matchActivity)
	{
		matchActivity = _matchActivity;
	}
	public static MimeStartFragment newInstance() {
		MimeStartFragment fragment = new MimeStartFragment();			
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

	    startView=inflater.inflate(R.layout.abstractactivity, null);
		findView();
		getNetMessage();
		return startView;
	}

	private void findView()
	{
//		abstractTextView = (TextView)startView.findViewById(R.id.abstractactivity_abstract);
//		titleTextView  = (TextView)startView.findViewById(R.id.abstractactivity_title);
//		needTextView = (TextView)startView.findViewById(R.id.abstractactivity_need);
//		scheduleTextView= (TextView)startView.findViewById(R.id.abstractactivity_schedule);
//		prizeTextView= (TextView)startView.findViewById(R.id.abstractactivity_prize);
//		commTextView= (TextView)startView.findViewById(R.id.abstractactivity_comm);
//		zhubanTextView= (TextView)startView.findViewById(R.id.abstractactivity_zhuban);
//		xiebanTextView= (TextView)startView.findViewById(R.id.abstractactivity_xieban);
//		chengbanTextView= (TextView)startView.findViewById(R.id.abstractactivity_chengban);
//		Log.v("suzhaohui","~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		webview = (WebView)startView.findViewById(R.id.webView1);
	}
	private void getNetMessage()
	{
		Log.v("suzhaohui","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		List<NameValuePair>  tt = new ArrayList<NameValuePair>();
		NetManager bb=		NetManager.getInstance() ; //.toString();		
		JSONObject get = bb.sendHttpRequest("contest/active", tt, 0);
		if(get == null)
		{
			return;
		}
		try {
			int code = get.getInt("code");
			if(code != 200)
			{
				Toast.makeText(matchActivity, get.getString("message"),
						Toast.LENGTH_SHORT).show();
				return;
			}
			match = get;
			String content = "<center></br><div style='font-size:20px'>";
			String cc = get.getJSONObject("data").getJSONObject("contest").getString("title");
			content += cc;
			
			content += "</div></br></center></br><div style='font-size:20px;background-color:red;width:100px;text-align:center;color:white'>大赛简介</div></br>";
			cc = get.getJSONObject("data").getJSONObject("contest").getString("description");
			content +=cc;
			
			content +="</br></br><div style='font-size:20px;background-color:red;width:100px;text-align:center;color:white'>大赛题目</div></br>";
			cc = get.getJSONObject("data").getJSONObject("contest").getString("topic");
			content += cc;
			
			content +="</br></br><div style='font-size:20px;background-color:red;width:100px;text-align:center;color:white'>大赛要求</div></br>";
			cc = get.getJSONObject("data").getJSONObject("contest").getString("requirements");
			content += cc;
			
			content +="</br></br><div style='font-size:20px;background-color:red;width:100px;text-align:center;color:white'>大赛流程</div></br>";
			cc = get.getJSONObject("data").getJSONObject("contest").getString("timetable");
			content += cc;
			
			content +="</br></br><div style='font-size:20px;background-color:red;width:100px;text-align:center;color:white'>奖项设置</div></br>";
			cc = get.getJSONObject("data").getJSONObject("contest").getString("prizes");
			content += cc;
			
			
			content +="</br><div style='font-size:20px;background-color:red;width:100px;text-align:center;color:white'>组委会</div></br>";
			cc = get.getJSONObject("data").getJSONObject("contest").getString("committee");
			content += cc;
			
			
			content +="</br><div style='font-size:20px;background-color:red;width:100px;text-align:center;color:white'>主办方</div></br>";
			cc = get.getJSONObject("data").getJSONObject("contest").getString("host_organizers");
			content += cc;
			
			content +="</br><div style='font-size:20px;background-color:red;width:100px;text-align:center;color:white'>承办方</div></br>";
			cc = get.getJSONObject("data").getJSONObject("contest").getString("organizers");
			content += cc;
			
			content +="</br><div style='font-size:20px;background-color:red;width:100px;text-align:center;color:white'>协办方</div></br>";
			cc = get.getJSONObject("data").getJSONObject("contest").getString("co_organizers");
			content += cc;
//			abstractTextView.setText();
		webview.loadDataWithBaseURL("http://www.jcodecraeer.com", content, "text/html", "utf-8",null);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
