package com.su.framgment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jgzs.lsw.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;





public class MimeStartFragment extends Fragment implements OnClickListener{
	
	

	private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
	
	public static MimeStartFragment newInstance() {
		MimeStartFragment fragment = new MimeStartFragment();			
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

	    View startView=inflater.inflate(R.layout.abstractactivity, null);
		
	   
		return startView;
	}

	
	
	@Override
	public void onStart() {
		super.onStart();
	
	}

	private void initData(){
		
	 for (int i = 0; i < 10; i++) {
		 
		HashMap<String, Object> temp= new HashMap<String, Object>();
		temp.put("key_name", "�ھӵĶ���");
		mlist.add(temp);
	}
		
		
		
	}
	
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	

	
	
	    
	    
	    
}
