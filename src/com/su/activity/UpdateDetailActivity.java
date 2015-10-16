package com.su.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.jgzs.lsw.R;
import com.su.database.DataAccess;
import com.su.util.AppData;
import com.su.util.HttpMethod;
import com.su.util.NetManager;
import com.su.util.UpLoadImage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD
import com.su.ImageLoad.ImageLoader;
import com.su.framgment.FragmentAdapter;
import com.su.util.UploadFileTask;
import com.jgzs.lsw.R;
import com.su.util.AppData;
import com.su.util.NetManager;
import com.su.util.UpLoadImage;

=======
>>>>>>> 435cbe957b8ee079d517615a2babaee5440b35f5


public class UpdateDetailActivity extends Activity implements OnClickListener {

<<<<<<< HEAD
	
	
	private  TextView detail_back;
	private ImageView detail_user_img;
	private com.su.ImageLoad.ImageLoader mImageLoader;

=======


    private TextView detail_back;
    private ImageView detail_user_img;
>>>>>>> 435cbe957b8ee079d517615a2babaee5440b35f5

    private String id;
    private String username;
    private String userimg;


    private TextView nick;
    private TextView truename;
    private TextView phone;
    private TextView pro;
    private TextView save;
    private RelativeLayout truename_layout;
    private RelativeLayout user_pic;
    private RelativeLayout nickname;
    private RelativeLayout phone_layout;
    private RelativeLayout pro_layout;

    private String signatrue;
    private TextView write_tv;
    private String mnick;
<<<<<<< HEAD
    private static final int PHOTO_SUCCESS = 1;  
	private static final int CAMERA_SUCCESS = 2;   
	private static final int NONE=0;
	private static final int PHOTORESOULT = 3;// ���	
	public static final String IMAGE_UNSPECIFIED = "image/*";
    
	private TextView area_tv;
	private TextView sex_tv;
    
	public static String headImgPath = "empty";
	private int headImgState = 0;//0 -- 表示没有选择  1：表示选择了 2：表示已经上传服务器
	
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				try {
					headImgState = 2;
					updatedetail();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
			
			super.handleMessage(msg);
		}
         
	};
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mimedetail);
	  
		
		initView();
	    
	   
	}


	private void initView(){
		
		
		detail_back=(TextView)findViewById(R.id.mimedetail_back);
		detail_back.setOnClickListener(this);
		
		detail_user_img=(ImageView)findViewById(R.id.detail_img);
		mImageLoader = new ImageLoader(this);
		try {
			if(AppData.userInfo.getString("avatar").length() > 0)
			{
				mImageLoader.DisplayImage(AppData.userInfo.getString("avatar"), detail_user_img, false);
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 
	    nick=(TextView)findViewById(R.id.nick_name);
	    nick.setText("123");
	    
	   
	    
	    user_pic=(RelativeLayout)findViewById(R.id.layout);
	    user_pic.setOnClickListener(this);
	    
	    nickname=(RelativeLayout)findViewById(R.id.nickname_rl);
	    nickname.setOnClickListener(this);
	    
	   
	    truename_layout = (RelativeLayout)findViewById(R.id.detail_truename);
	    truename = (TextView)findViewById(R.id.detail_truename_textview);
	    truename_layout.setOnClickListener(this);
	    
	    phone_layout = (RelativeLayout)findViewById(R.id.detail_phone_layout);
	    phone = (TextView)findViewById(R.id.detail_phone_textview);
	    phone_layout.setOnClickListener(this);
	    
	    pro_layout = (RelativeLayout)findViewById(R.id.detail_pro_layout);
	    pro = (TextView)findViewById(R.id.detail_pro_textview);
	    pro_layout.setOnClickListener(this);
	    
	    
	    save = (TextView)findViewById(R.id.detail_save);
	    save.setOnClickListener(this);

	    
	   
	    if(AppData.userInfo != null)
	    {
	    	try {
	    		String nickstr = AppData.userInfo.getString("name");
	    		String fullnamestr = AppData.userInfo.getString("fullname");
	    		String contact = AppData.userInfo.getString("contact");
	    		String profile = AppData.userInfo.getString("profile");
	    		
	    		if(nickstr.equals("null")  || nickstr.length() == 0)
	    		{
	    			nickstr = "";
	    		}
	    		
	    		if(fullnamestr.equals("null")  || fullnamestr.length() == 0)
	    		{
	    			fullnamestr = "";
	    		}
	    		
	    		if(contact.equals("null"))
	    		{
	    			contact = "";
	    		}
	    		
	    		if(profile.equals("null"))
	    		{
	    			profile = "";
	    		}
				nick.setText(nickstr);
				truename.setText(fullnamestr);
				phone.setText(contact);
				pro.setText(profile);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
	}
	
	
	private void setListener()
    {
      
    }
	
	


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	switch (v.getId()) {
	case R.id.mimedetail_back:
		
		this.finish();
		
		break;
		
       
		
        case R.id.nickname_rl:
    		mnick=nick.getText().toString().trim();
            Intent intent1=new Intent(UpdateDetailActivity.this, UpdateSignatureActivity.class);
            Bundle bundle1= new Bundle();
            bundle1.putInt("resultcode", 9);
            bundle1.putString("title","昵称/公司");
    		bundle1.putString("mimecontent",mnick);
    		bundle1.putInt("num", 15);
    		intent1.putExtras(bundle1);        
    		startActivityForResult(intent1, 9);
    		
    		break;
    		
        case R.id.detail_truename:
    		mnick=truename.getText().toString().trim();
            Intent intent=new Intent(UpdateDetailActivity.this, UpdateSignatureActivity.class);
            Bundle bundle= new Bundle();
            bundle.putInt("resultcode", 10);
            bundle.putString("title","真实姓名");
    		bundle.putString("mimecontent",mnick);
    		bundle.putInt("num", 15);
    		intent.putExtras(bundle);        
    		startActivityForResult(intent, 10);
    		
    		break;
    		
    		
        case R.id.detail_phone_layout:
    		mnick=phone.getText().toString().trim();
            Intent intent2=new Intent(UpdateDetailActivity.this, UpdateSignatureActivity.class);
            Bundle bundle2= new Bundle();
            bundle2.putInt("resultcode", 11);
            bundle2.putString("title","联系方式");
    		bundle2.putString("mimecontent",mnick);
    		bundle2.putInt("num", 35);
    		intent2.putExtras(bundle2);        
    		startActivityForResult(intent2, 11);
    		
    		break;
    		
    		
        case R.id.detail_pro_layout:
    		mnick=pro.getText().toString().trim();
            Intent intent23=new Intent(UpdateDetailActivity.this, UpdateSignatureActivity.class);
            Bundle bundle23= new Bundle();
            bundle23.putInt("resultcode", 12);
            bundle23.putString("title","简介");
    		bundle23.putString("mimecontent",mnick);
    		bundle23.putInt("num", 50);
    		intent23.putExtras(bundle23);        
    		startActivityForResult(intent23, 12);
    		
    		break;
    		
        case R.id.layout:
    		
        	Intent intent0 = new Intent();
			intent0.setType("image/*");
			intent0.setAction(Intent.ACTION_GET_CONTENT);
			//回调图片类使用的
			startActivityForResult(intent0, PHOTO_SUCCESS);
    		
    		break;	
		

        case R.id.detail_save:
        	if(!headImgPath.endsWith("empty") && headImgState == 1)
        	{
        		
        		UploadFileTask uploadFileTask=new UploadFileTask(this,handler);
				uploadFileTask.execute(headImgPath);
				
				return;
        	}
		try {
			updatedetail();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        	break;

	default:
		break;
	}	
		
		
		
	}

	private void updatedetail() throws JSONException	
	{
		String nickstr =nick.getText().toString().trim();
		String truenamestr =truename.getText().toString().trim();
		String phonestr =phone.getText().toString().trim();
		String prostr =pro.getText().toString().trim();
       
		List<NameValuePair>  tt = new ArrayList<NameValuePair>();
		NetManager bb=		NetManager.getInstance() ; //.toString();
		
		String nickstr1 = AppData.userInfo.getString("name");
		String fullnamest1r = AppData.userInfo.getString("fullname");
		String contact1 = AppData.userInfo.getString("contact");
		String profile1 = AppData.userInfo.getString("profile");
		
		if(nickstr.length() > 0)
		{
			if(!nickstr.endsWith(nickstr1))
			tt.add(new BasicNameValuePair("name",nickstr));
		}
		
		if(truenamestr.length() > 0)
		{
			if(!truenamestr.endsWith(fullnamest1r))
			tt.add(new BasicNameValuePair("fullname ",truenamestr));
		}
		
		if(phonestr.length() > 0)
		{
			if(!phonestr.endsWith(contact1))
			tt.add(new BasicNameValuePair("contact",phonestr));
		}
		
		if(prostr.length() > 0)
		{
			if(!phonestr.endsWith(contact1))
			tt.add(new BasicNameValuePair("profile",prostr));
		}
		if(!headImgPath.endsWith("empty") && headImgState == 2)
		{
			try {
				JSONObject img = new JSONObject(headImgPath);
				JSONObject data = img.getJSONObject("data");
				String url = data.getString("image_path");
				tt.add(new BasicNameValuePair("avatar ",url));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
		 if(AppData.userInfo != null)
		 {
			 tt.add(new BasicNameValuePair("app_id",AppData.app_id));
			 tt.add(new BasicNameValuePair("open_id",AppData.open_id));
			 tt.add(new BasicNameValuePair("access_token",AppData.access_token));
			
		 }
		JSONObject get = bb.sendHttpRequest("account/profile", tt, 0);
		if(get == null)
		{
			Toast.makeText(this, "系统异常（用户名重复）",
					Toast.LENGTH_SHORT).show();
			return;
		}
		try {
			int code = get.getInt("code");
			if(code != 200)
			{
				if(code == -10006)
				{
					new AlertDialog.Builder(this).setTitle("提示").setMessage("账号过期，是否登陆？")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						startActivity(new Intent(UpdateDetailActivity.this,
								LoginActivity.class));
					}})
					.setNegativeButton("取消",null)
					.show();
					return;
				}
				Toast.makeText(this, get.getString("message"),
						Toast.LENGTH_SHORT).show();
				return;
			}
			AppData.userInfo = get.getJSONObject("data").getJSONObject("user");
			Toast.makeText(this, "修改成功",
					Toast.LENGTH_SHORT).show();
			headImgState = 0;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		 getContentResolver(); 
		
		
		if (resultCode == NONE)
			return;
		// ����
		if (requestCode == CAMERA_SUCCESS) {
			
			// �����ļ�����·��������ڸ�Ŀ¼��
			File picture = new File(Environment.getExternalStorageDirectory()
					+ "/myImage/newtemp.jpg");
			//System.out.println("------------------------" + picture.getPath());
			startPhotoZoom(Uri.fromFile(picture));
		}

		if (data == null)
		{				
			return;
		}
		// ��ȡ�������ͼƬ
		if (requestCode ==PHOTO_SUCCESS) {
			
			Uri uri = data.getData();
			Log.e("suzhaohui- select img", "uri = "+ uri);
			try {
				String[] pojo = {MediaStore.Images.Media.DATA};
				
				Cursor cursor = managedQuery(uri, pojo, null, null,null);
				if(cursor!=null)
				{
					ContentResolver cr = this.getContentResolver();
					int colunm_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
					cursor.moveToFirst();
					String path = cursor.getString(colunm_index);
					/***
					 * 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，你选择的文件就不一定是图片了，这样的话，我们判断文件的后缀名
					 * 如果是图片格式的话，那么才可以   
					 */
					if(path.endsWith("jpg")||path.endsWith("png"))
					{
						headImgPath = path;
						headImgState = 1;
						Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
						detail_user_img.setImageBitmap(bitmap);
					}else{
						alert();
					}
				}else{
					alert();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	          
		}
		  // ������
		  if (requestCode == PHOTORESOULT) {
			Bundle extras = data.getExtras();
			if (extras != null) {
				Log.v("suzhaohui","data getextras");
				Bitmap photo = extras.getParcelable("data");				
				savePicture(photo);				
				//���Ҫ�����ύͷ��Ĳ���
			//	xxxxxxxxxxxxxx
			}

		}
		  
		  if (requestCode ==5) {
			 String cityname;
			 String provincename;
			 provincename=data.getStringExtra("provincename");
			 cityname = data.getStringExtra("cityname");
			 if (cityname.equals("������")||cityname.equals("�����")||cityname.equals("�Ϻ���")||cityname.equals("������")||cityname.equals("̨��ʡ")||cityname.equals("����ر�������")||cityname.equals("�����ر�������")) {
				
				 area_tv.setText(cityname);
				}
				else {

					area_tv.setText(provincename+" "+cityname);	
				}
			   
				
				//�ɹ��޸�֮��Ž�ʡ�иĵ�
				
				

			}
		  
		  
		
		  
		  if (requestCode ==10) {
			  
			   String intro;				
			   intro=data.getStringExtra("result");
			   truename.setText(intro);

			}
		  if (requestCode ==11) {
			  
			   String intro;				
			   intro=data.getStringExtra("result");
			   phone.setText(intro);

			}
		  
		  
		  if (requestCode ==9) {
			  
			   String nickname;				
			   nickname=data.getStringExtra("result");
			   nick.setText(nickname);

			}
		  
		  if (requestCode ==12) {
			  
			   String intro;				
			   intro=data.getStringExtra("result");
			   pro.setText(intro);

			}
		  
		  		  
		super.onActivityResult(requestCode, resultCode, data);
		
		
	}	
	
//	     @Override
//		protected void onActivityResult(int resultCode, int re, Intent data) {
//			String cityname;
//			String provincename;
//			if (data == null) {
//
//				return;
//			}
//			cityname = data.getStringExtra("cityname");
//			provincename=data.getStringExtra("provincename");
//			area_tv.setText(provincename+"  "+cityname);
//		}
	
	
	
	/* ��ѹ��ͼƬ���浽�Զ�����ļ���
	 * Bitmap����һcompress��Ա�����԰�bitmap���浽һ��stream�С�
	 */
	@SuppressLint("SdCardPath")
	public void savePicture(Bitmap bitmap) { 
		     
		detail_user_img.setImageBitmap(bitmap);
		
	    String sdStatus = Environment.getExternalStorageState(); 
	    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // ���sd�Ƿ���� 
	      //  Log.v("TestFile", "SD card is not avaiable/writeable right now."); 
	        return; 
	    } 
	    FileOutputStream b = null; 
	    File file = new File("/sdcard/myImage/"); 
	    file.mkdirs();// �����ļ��� 
	    String fileName = "/sdcard/myImage/temp.jpg"; 
	  
	    try { 
	        b = new FileOutputStream(fileName); 
	        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// ������д���ļ� 
	    } catch (FileNotFoundException e) { 
	        e.printStackTrace(); 
         } 
	    finally { 
	        try { 
	            b.flush(); 
	            b.close();     
	            ArrayList<String> list = new ArrayList();
	            list.add(fileName);
	            UpLoadImage.upload(list, "file/upload");
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        } 
	    } 
	}
	
	 /*
	   * ����ͼƬ����
	   */
		public void startPhotoZoom(Uri uri) {
			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
			intent.putExtra("crop", "true");
			// aspectX aspectY �ǿ�ߵı���
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			// outputX outputY �ǲü�ͼƬ���
			intent.putExtra("outputX",640);
			intent.putExtra("outputY",640);
			intent.putExtra("return-data", true);
			startActivityForResult(intent, PHOTORESOULT);
		}
	
	
	
	private	void scanOldImageFile(){
		File file = new File(Environment.getExternalStorageDirectory(), "/myImage/newtemp.jpg");
		File file1 = new File(Environment.getExternalStorageDirectory(), "/myImage/temp.jpg");
		if(file.exists()||file1.exists()){
			file.delete();
			file1.delete();
		}
		
		
	}
	
	
	//��ȡ�ļ��ֻ�·��
	private String getImagePath(){
		
		
		File file=new File(Environment.getExternalStorageDirectory(), "/myImage/temp.jpg");			
		return file.getAbsolutePath();
	}

	

	
	
	  @Override
	   public void onDestroy(){
		   
		   scanOldImageFile();
		   
		   super.onDestroy();
	   }
	  private void alert()
	    {
	    	Dialog dialog = new AlertDialog.Builder(this)
			.setTitle("提示")
			.setMessage("您选择的不是有效的图片")
			.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {
							headImgPath = "empty";
							headImgState = 0;
						}
					})
			.create();
			dialog.show();
	    }
}
=======
    private static final int PHOTO_SUCCESS = 1;
    private static final int CAMERA_SUCCESS = 2;
    private static final int NONE = 0;
    private static final int PHOTORESOULT = 3;// ���
    public static final String IMAGE_UNSPECIFIED = "image/*";

    private TextView area_tv;
    private TextView sex_tv;

    private String headImgPath = null;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mimedetail);


        initView();


    }


    private void initView() {


        detail_back = (TextView) findViewById(R.id.mimedetail_back);
        detail_back.setOnClickListener(this);

        detail_user_img = (ImageView) findViewById(R.id.detail_img);

        nick = (TextView) findViewById(R.id.nick_name);
        nick.setText("123");



        user_pic = (RelativeLayout) findViewById(R.id.layout);
        user_pic.setOnClickListener(this);

        nickname = (RelativeLayout) findViewById(R.id.nickname_rl);
        nickname.setOnClickListener(this);


        truename_layout = (RelativeLayout) findViewById(R.id.detail_truename);
        truename = (TextView) findViewById(R.id.detail_truename_textview);
        truename_layout.setOnClickListener(this);

        phone_layout = (RelativeLayout) findViewById(R.id.detail_phone_layout);
        phone = (TextView) findViewById(R.id.detail_phone_textview);
        phone_layout.setOnClickListener(this);

        pro_layout = (RelativeLayout) findViewById(R.id.detail_pro_layout);
        pro = (TextView) findViewById(R.id.detail_pro_textview);
        pro_layout.setOnClickListener(this);


        save = (TextView) findViewById(R.id.detail_save);
        save.setOnClickListener(this);



        if (AppData.userInfo != null) {
            try {
                String nickstr = AppData.userInfo.getString("name");
                String fullnamestr = AppData.userInfo.getString("fullname");
                String contact = AppData.userInfo.getString("contact");
                String profile = AppData.userInfo.getString("profile");

                if (nickstr.equals("null") || nickstr.length() == 0) {
                    nickstr = "";
                }

                if (fullnamestr.equals("null") || fullnamestr.length() == 0) {
                    fullnamestr = "";
                }

                if (contact.equals("null")) {
                    contact = "";
                }

                if (profile.equals("null")) {
                    profile = "";
                }
                nick.setText(nickstr);
                truename.setText(fullnamestr);
                phone.setText(contact);
                pro.setText(profile);

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }


    private void setListener() {

    }



    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.mimedetail_back:

                this.finish();

                break;



            case R.id.nickname_rl:
                mnick = nick.getText().toString().trim();
                Intent intent1 = new Intent(UpdateDetailActivity.this, UpdateSignatureActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("resultcode", 9);
                bundle1.putString("title", "昵称/公司");
                bundle1.putString("mimecontent", mnick);
                bundle1.putInt("num", 15);
                intent1.putExtras(bundle1);
                startActivityForResult(intent1, 9);

                break;

            case R.id.detail_truename:
                mnick = truename.getText().toString().trim();
                Intent intent = new Intent(UpdateDetailActivity.this, UpdateSignatureActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("resultcode", 10);
                bundle.putString("title", "真实姓名");
                bundle.putString("mimecontent", mnick);
                bundle.putInt("num", 15);
                intent.putExtras(bundle);
                startActivityForResult(intent, 10);

                break;


            case R.id.detail_phone_layout:
                mnick = phone.getText().toString().trim();
                Intent intent2 = new Intent(UpdateDetailActivity.this, UpdateSignatureActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("resultcode", 11);
                bundle2.putString("title", "联系方式");
                bundle2.putString("mimecontent", mnick);
                bundle2.putInt("num", 35);
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, 11);

                break;


            case R.id.detail_pro_layout:
                mnick = pro.getText().toString().trim();
                Intent intent23 = new Intent(UpdateDetailActivity.this, UpdateSignatureActivity.class);
                Bundle bundle23 = new Bundle();
                bundle23.putInt("resultcode", 12);
                bundle23.putString("title", "简介");
                bundle23.putString("mimecontent", mnick);
                bundle23.putInt("num", 50);
                intent23.putExtras(bundle23);
                startActivityForResult(intent23, 12);

                break;

            case R.id.layout:

                Intent intent0 = new Intent();
                intent0.setType("image/*");
                intent0.setAction(Intent.ACTION_GET_CONTENT);
                // 回调图片类使用的
                startActivityForResult(intent0, PHOTO_SUCCESS);

                break;


            case R.id.detail_save:

                String nickstr = nick.getText().toString().trim();
                String truenamestr = truename.getText().toString().trim();
                String phonestr = phone.getText().toString().trim();
                String prostr = pro.getText().toString().trim();

                List<NameValuePair> tt = new ArrayList<NameValuePair>();
                NetManager bb = NetManager.getInstance(); // .toString();
                if (nickstr.length() > 0) {
                    tt.add(new BasicNameValuePair("name", nickstr));
                }

                if (truenamestr.length() > 0) {
                    tt.add(new BasicNameValuePair("fullname ", truenamestr));
                }

                if (phonestr.length() > 0) {
                    tt.add(new BasicNameValuePair("contact", phonestr));
                }

                if (prostr.length() > 0) {
                    tt.add(new BasicNameValuePair("profile", prostr));
                }
                /**
                if (AppData.userInfo != null) {
                    try {
                        tt.add(new BasicNameValuePair("app_id", AppData.userInfo.getString("app_id")));
                        tt.add(new BasicNameValuePair("open_id", AppData.userInfo.getString("open_id")));
                        tt.add(new BasicNameValuePair("access_token", AppData.userInfo.getString("access_token")));
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
                **/
                
                tt.add(new BasicNameValuePair("app_id", DataAccess.getAppId()));
                tt.add(new BasicNameValuePair("open_id", DataAccess.getOpenId()));
                tt.add(new BasicNameValuePair("access_token", DataAccess.getAccessToken()));
                
                JSONObject get = bb.sendHttpRequest("account/profile", tt, HttpMethod.POST);

                try {
                    int code = get.getInt("code");
                    if (code != 200) {
                        Toast.makeText(this, get.getString("message"), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    AppData.userInfo = get.getJSONObject("data").getJSONObject("user");
                    Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;

            default:
                break;
        }



    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        getContentResolver();


        if (resultCode == NONE)
            return;
        // ����
        if (requestCode == CAMERA_SUCCESS) {

            // �����ļ�����·��������ڸ�Ŀ¼��
            File picture = new File(Environment.getExternalStorageDirectory() + "/myImage/newtemp.jpg");
            // System.out.println("------------------------" + picture.getPath());
            startPhotoZoom(Uri.fromFile(picture));
        }

        if (data == null) {
            return;
        }
        // ��ȡ�������ͼƬ
        if (requestCode == PHOTO_SUCCESS) {

            Uri uri = data.getData();
            Log.e("suzhaohui- select img", "uri = " + uri);
            try {
                String[] pojo = {MediaStore.Images.Media.DATA};

                Cursor cursor = managedQuery(uri, pojo, null, null, null);
                if (cursor != null) {
                    ContentResolver cr = this.getContentResolver();
                    int colunm_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    String path = cursor.getString(colunm_index);
                    /***
                     * 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，你选择的文件就不一定是图片了，这样的话，我们判断文件的后缀名 如果是图片格式的话，那么才可以
                     */
                    if (path.endsWith("jpg") || path.endsWith("png")) {
                        headImgPath = path;
                        Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                        detail_user_img.setImageBitmap(bitmap);
                    } else {
                        alert();
                    }
                } else {
                    alert();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        // ������
        if (requestCode == PHOTORESOULT) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Log.v("suzhaohui", "data getextras");
                Bitmap photo = extras.getParcelable("data");
                savePicture(photo);
                // ���Ҫ�����ύͷ��Ĳ���
                // xxxxxxxxxxxxxx
            }

        }

        if (requestCode == 5) {
            String cityname;
            String provincename;
            provincename = data.getStringExtra("provincename");
            cityname = data.getStringExtra("cityname");
            if (cityname.equals("������") || cityname.equals("�����") || cityname.equals("�Ϻ���") || cityname.equals("������") || cityname.equals("̨��ʡ") || cityname.equals("����ر�������")
                    || cityname.equals("�����ر�������")) {

                area_tv.setText(cityname);
            } else {

                area_tv.setText(provincename + " " + cityname);
            }


            // �ɹ��޸�֮��Ž�ʡ�иĵ�



        }



        if (requestCode == 10) {

            String intro;
            intro = data.getStringExtra("result");
            truename.setText(intro);

        }
        if (requestCode == 11) {

            String intro;
            intro = data.getStringExtra("result");
            phone.setText(intro);

        }


        if (requestCode == 9) {

            String nickname;
            nickname = data.getStringExtra("result");
            nick.setText(nickname);

        }

        if (requestCode == 12) {

            String intro;
            intro = data.getStringExtra("result");
            pro.setText(intro);

        }


        super.onActivityResult(requestCode, resultCode, data);


    }

    // @Override
    // protected void onActivityResult(int resultCode, int re, Intent data) {
    // String cityname;
    // String provincename;
    // if (data == null) {
    //
    // return;
    // }
    // cityname = data.getStringExtra("cityname");
    // provincename=data.getStringExtra("provincename");
    // area_tv.setText(provincename+" "+cityname);
    // }



    /*
     * ��ѹ��ͼƬ���浽�Զ�����ļ��� Bitmap����һcompress��Ա�����԰�bitmap���浽һ��stream�С�
     */
    @SuppressLint("SdCardPath")
    public void savePicture(Bitmap bitmap) {

        detail_user_img.setImageBitmap(bitmap);

        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // ���sd�Ƿ����
            // Log.v("TestFile", "SD card is not avaiable/writeable right now.");
            return;
        }
        FileOutputStream b = null;
        File file = new File("/sdcard/myImage/");
        file.mkdirs();// �����ļ���
        String fileName = "/sdcard/myImage/temp.jpg";

        try {
            b = new FileOutputStream(fileName);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// ������д���ļ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                b.flush();
                b.close();
                ArrayList<String> list = new ArrayList();
                list.add(fileName);
                UpLoadImage.upload(list, "file/upload");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * ����ͼƬ����
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        // aspectX aspectY �ǿ�ߵı���
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY �ǲü�ͼƬ���
        intent.putExtra("outputX", 640);
        intent.putExtra("outputY", 640);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTORESOULT);
    }



    private void scanOldImageFile() {
        File file = new File(Environment.getExternalStorageDirectory(), "/myImage/newtemp.jpg");
        File file1 = new File(Environment.getExternalStorageDirectory(), "/myImage/temp.jpg");
        if (file.exists() || file1.exists()) {
            file.delete();
            file1.delete();
        }


    }


    // ��ȡ�ļ��ֻ�·��
    private String getImagePath() {


        File file = new File(Environment.getExternalStorageDirectory(), "/myImage/temp.jpg");
        return file.getAbsolutePath();
    }



    @Override
    public void onDestroy() {

        scanOldImageFile();

        super.onDestroy();
    }

    private void alert() {
        Dialog dialog = new AlertDialog.Builder(this).setTitle("提示").setMessage("您选择的不是有效的图片").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                headImgPath = null;
            }
        }).create();
        dialog.show();
    }
}
>>>>>>> 435cbe957b8ee079d517615a2babaee5440b35f5
