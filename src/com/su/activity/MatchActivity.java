package com.su.activity;

import java.util.ArrayList;

import com.jgzs.lsw.R;
import com.su.framgment.AbstractFragment;
import com.su.framgment.MimeStartFragment;
import com.su.framgment.NewsFragement;
import com.su.framgment.ProductionFragement;
import com.su.framgment.ProfessionalFragment;
import com.su.util.MyFragmentPagerAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MatchActivity extends FragmentActivity implements OnClickListener {

	

	private TextView mime_group_back;
    private ViewPager mPager;
	private ArrayList<Fragment> fragmentsList;
	private int currIndex = 0;
	private TextView abstractTextView;
	private TextView proTextView;
	private TextView productTextView;
	private TextView newsTextView;
	 
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.matchactivity);
		InitTextView();
		InitViewPager();
	}
	
	
	
	
	
		
		 private void InitTextView() {
			    abstractTextView = (TextView) findViewById(R.id.match_activity_abstract);
			    newsTextView = (TextView) findViewById(R.id.match_activity_news);
			    proTextView = (TextView) findViewById(R.id.match_activity_pro);
			    productTextView = (TextView) findViewById(R.id.match_activity_product);

			    abstractTextView.setOnClickListener(new MyOnClickListener(0));
			    newsTextView.setOnClickListener(new MyOnClickListener(1));
			    proTextView.setOnClickListener(new MyOnClickListener(2));
			    productTextView.setOnClickListener(new MyOnClickListener(3));
		 
			    abstractTextView.setBackgroundResource(R.drawable.navigationbar_button_send_background);	
		    }	
		
	
	
	
	
	 private void InitViewPager() {
	        mPager = (ViewPager) findViewById(R.id.match_activity_pager);
	        fragmentsList = new ArrayList<Fragment>();


	      
	       Fragment Fragment01=new AbstractFragment(this);
	       Fragment Fragment02=new NewsFragement(this);
	       Fragment Fragment03=new ProfessionalFragment(this);
	       Fragment Fragment04=new ProductionFragement(this);
	       


	     
	         fragmentsList.add(Fragment01);
	         fragmentsList.add(Fragment02);	
	         fragmentsList.add(Fragment03);
	         fragmentsList.add(Fragment04);
	        
	        
	        mPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentsList));
	        mPager.setCurrentItem(0);
	        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
	    }


	 
	 
	  public class MyOnClickListener implements View.OnClickListener {
	        private int index = 0;

	        public MyOnClickListener(int i) {
	            index = i;
	        }

	        @Override
	        public void onClick(View v) {
	            mPager.setCurrentItem(index);
	        }
	    };

	    public class MyOnPageChangeListener implements OnPageChangeListener {

	        @Override
	        public void onPageSelected(int arg0) {

	        
	        	
	            switch (arg0) {
	            case 0:
	                  	abstractTextView.setBackgroundResource(R.drawable.navigationbar_button_send_background);	
	                  	abstractTextView.setTextColor(getResources().getColor(R.color.lightwhite));	                	
	                	newsTextView.setBackgroundResource(0);
	                	newsTextView.setTextColor(getResources().getColor(R.color.tab_text));
	                	proTextView.setBackgroundResource(0);
	                	proTextView.setTextColor(getResources().getColor(R.color.tab_text));
	                	productTextView.setBackgroundResource(0);
	                	productTextView.setTextColor(getResources().getColor(R.color.tab_text));
					
	                	break;
	            case 1:
	            	newsTextView.setBackgroundResource(R.drawable.navigationbar_button_send_background);	
	            	newsTextView.setTextColor(getResources().getColor(R.color.lightwhite));	                	
	                abstractTextView.setBackgroundResource(0);
	                abstractTextView.setTextColor(getResources().getColor(R.color.tab_text));
	                proTextView.setBackgroundResource(0);
	                proTextView.setTextColor(getResources().getColor(R.color.tab_text));
	                productTextView.setBackgroundResource(0);
	                productTextView.setTextColor(getResources().getColor(R.color.tab_text));
				
	                break;
	            case 2:
	            	proTextView.setBackgroundResource(R.drawable.navigationbar_button_send_background);	
	            	proTextView.setTextColor(getResources().getColor(R.color.lightwhite));	                	
	                abstractTextView.setBackgroundResource(0);
	                abstractTextView.setTextColor(getResources().getColor(R.color.tab_text));
	                newsTextView.setBackgroundResource(0);
	                newsTextView.setTextColor(getResources().getColor(R.color.tab_text));
	                productTextView.setBackgroundResource(0);
	                productTextView.setTextColor(getResources().getColor(R.color.tab_text));
				
	                break;
	            case 3:
	            	productTextView.setBackgroundResource(R.drawable.navigationbar_button_send_background);	
	            	productTextView.setTextColor(getResources().getColor(R.color.lightwhite));	                	
	                abstractTextView.setBackgroundResource(0);
	                abstractTextView.setTextColor(getResources().getColor(R.color.tab_text));
	                proTextView.setBackgroundResource(0);
	                proTextView.setTextColor(getResources().getColor(R.color.tab_text));
	                newsTextView.setBackgroundResource(0);
	                newsTextView.setTextColor(getResources().getColor(R.color.tab_text));
				
	                break;

	       
	            }
	            currIndex = arg0;

	        }

	        @Override
	        public void onPageScrolled(int arg0, float arg1, int arg2) {
	        }

	        @Override
	        public void onPageScrollStateChanged(int arg0) {
	        }
	    }

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
     
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 



	
	

	
	

	
}
