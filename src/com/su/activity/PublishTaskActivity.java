package com.su.activity;

import com.jgzs.lsw.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PublishTaskActivity  extends Activity implements OnClickListener {

	private TextView back;
	private TextView publish;
	private TextView title;
	private TextView type;
	private TextView reward;
	private TextView starttime;
	private TextView endtime;
	private TextView dec;
	private LinearLayout titlelayout;
	private LinearLayout typelayout;
	private LinearLayout rewardlayout;
	private LinearLayout starttimelayout;
	private LinearLayout endtimelayout;
	private LinearLayout declayout;
	int select ;
	 String item[] = {"建筑设计","室内设计","景观设计","家具设计","平面设计","工业设计"};
	@Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publishtask);
        findViewByXmlId();
    }
	private void findViewByXmlId()
	{
		back = (TextView) findViewById(R.id.publishtask_activity_back);
		publish = (TextView) findViewById(R.id.publishtask_activity_apply);
		title = (TextView) findViewById(R.id.publish_task_title);
		type = (TextView) findViewById(R.id.publishtask_activity_type);
		reward = (TextView) findViewById(R.id.publishtask_activity_reward);
		starttime = (TextView) findViewById(R.id.publishtask_activity_start_time);
		endtime = (TextView) findViewById(R.id.publishtask_activity_endTime);
		dec = (TextView) findViewById(R.id.publishtask_activity_dec);
		
		titlelayout = (LinearLayout) findViewById(R.id.publish_task_title_layout);
		typelayout = (LinearLayout) findViewById(R.id.publishtask_activity_type_layout);
		rewardlayout = (LinearLayout) findViewById(R.id.publishtask_activity_reward_layou);
		starttimelayout = (LinearLayout) findViewById(R.id.publishtask_activity_start_time_layout);
		endtimelayout = (LinearLayout) findViewById(R.id.publishtask_activity_endtime_layout);
		declayout = (LinearLayout) findViewById(R.id.publishtask_activity_dec_layout);
		
		titlelayout.setOnClickListener(this);
		typelayout.setOnClickListener(this);
		rewardlayout.setOnClickListener(this);
		starttimelayout.setOnClickListener(this);
		endtimelayout.setOnClickListener(this);
		declayout.setOnClickListener(this);
		back.setOnClickListener(this);
		publish.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		getContentResolver();
		String mnick = "";
		Intent intent1 =null;
		Bundle bundle1 = null;
		if(v == null)
		{
			return;
		}
		switch (v.getId()) {
        case R.id.publishtask_activity_back:
            this.finish();
            break;
        case R.id.publish_task_title_layout:
            mnick = title.getText().toString().trim();
            intent1 = new Intent(PublishTaskActivity.this, UpdateSignatureActivity.class);
            bundle1 = new Bundle();
            bundle1.putInt("resultcode", 9);
            bundle1.putString("title", "任务标题");
            bundle1.putString("mimecontent", mnick);
            bundle1.putInt("num", 15);
            intent1.putExtras(bundle1);
            startActivityForResult(intent1, 9);
            break;
        case R.id.publishtask_activity_reward_layou:
            mnick = reward.getText().toString().trim();
            intent1 = new Intent(PublishTaskActivity.this, UpdateSignatureActivity.class);
            bundle1 = new Bundle();
            bundle1.putInt("resultcode", 10);
            bundle1.putString("title", "任务奖励");
            bundle1.putString("mimecontent", mnick);
            bundle1.putInt("num", 15);
            intent1.putExtras(bundle1);
            startActivityForResult(intent1, 10);
            break;
        case R.id.publishtask_activity_dec_layout:
            mnick = dec.getText().toString().trim();
            intent1 = new Intent(PublishTaskActivity.this, UpdateSignatureActivity.class);
            bundle1 = new Bundle();
            bundle1.putInt("resultcode", 11);
            bundle1.putString("title", "任务描述");
            bundle1.putString("mimecontent", mnick);
            bundle1.putInt("num", 600);
            intent1.putExtras(bundle1);
            startActivityForResult(intent1, 11);
            break;
        case R.id.publishtask_activity_start_time_layout:
        	 mnick = starttime.getText().toString().trim();
        	 int y  = 2015;
        	 int m = 10;
        	 int d = 10;
        	String[] timelist =  mnick.split("-");
        	y = Integer.parseInt(timelist[0]);
        	m = Integer.parseInt(timelist[1]) - 1;
        	d = Integer.parseInt(timelist[2]);
            DatePickerDialog datePicker=new DatePickerDialog(PublishTaskActivity.this, new OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					// TODO Auto-generated method stub
					String timestr = String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1)+"-"+String.valueOf(dayOfMonth);
					starttime.setText(timestr);
				}
			}, y, m, d);
			datePicker.show();
        	break;
        case R.id.publishtask_activity_endtime_layout:
       	 mnick = endtime.getText().toString().trim();
       	 int y1  = 2015;
       	 int m1 = 10;
       	 int d1 = 10;
       	String[] timelist1 =  mnick.split("-");
       	y1 = Integer.parseInt(timelist1[0]);
       	m1 = Integer.parseInt(timelist1[1]) - 1;
       	d1 = Integer.parseInt(timelist1[2]);
           DatePickerDialog datePicker1=new DatePickerDialog(PublishTaskActivity.this, new OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					// TODO Auto-generated method stub
					String timestr = String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1)+"-"+String.valueOf(dayOfMonth);
					endtime.setText(timestr);
				}
			}, y1, m1, d1);
			datePicker1.show();
       	break;
        case R.id.publishtask_activity_type_layout:
        	 
        	AlertDialog mDialog = new AlertDialog.Builder(PublishTaskActivity.this)  
			.setTitle("日程类型")  
               .setIcon(android.R.drawable.ic_dialog_alert)  
                .setSingleChoiceItems(item, 0,  
                        new DialogInterface.OnClickListener() {  

                           public void onClick(DialogInterface dialog,  
                                   int which) {  
                        	   select = which;
                          } 
                      }) 
                .setPositiveButton("确定",  
                        new DialogInterface.OnClickListener() {  
                            public void onClick(DialogInterface dialog,  
                                    int which) {  
                            	type.setText(item[select]);
                            }  
                        })  
               .setNegativeButton("取消",  
                        new DialogInterface.OnClickListener() {  

                           public void onClick(DialogInterface dialog,  
                                   int which) {  
                            }  
                        }).create(); 
			mDialog.show();
		
        	break;
        case R.id.publishtask_activity_apply:
        	
        	break;
        	default:
        		break;
		};
		
	}
	
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 getContentResolver();
	        if (resultCode == 0)
	            return;
		 if (requestCode == 9) {
	            String intro;
	            intro = data.getStringExtra("result");
	            title.setText(intro);
	        }
		 if (requestCode == 10) {
	            String intro;
	            intro = data.getStringExtra("result");
	            reward.setText(intro);
	        }
		 if (requestCode == 11) {
	            String intro;
	            intro = data.getStringExtra("result");
	            dec.setText(intro);
	        }
	 }

}
