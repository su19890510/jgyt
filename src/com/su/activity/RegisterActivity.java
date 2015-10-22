package com.su.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.jgzs.lsw.R;
import com.su.util.HttpMethod;
import com.su.util.NetManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
    private Button confirm;
    private Button code;
    private Timer timer;
    private RegisterActivity register;
    private InputMethodManager manager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register);
        register = this;
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        LinearLayout activity_main = (LinearLayout) findViewById(R.id.login_activity_main);
        activity_main.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View arg0, MotionEvent arg1) {
                Log.v("suzhaohui", "listener is enter");
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                return imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });
        findViewById();
        setListener();
    }

    /**
     * 绑定界面UI
     */
    private void findViewById() {
        confirm = (Button) findViewById(R.id.register_activity_confirm);
        // code = (Button) findViewById(R.id.register_code);
        // mc = new MyCount(120000, 1000);
    }

    /**
     * UI事件监听
     */
    private void setListener() {
        // 登录按钮监听
        confirm.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Log.v("suzhaohui", "onclicked");
                String name = String.valueOf(((TextView) findViewById(R.id.register_activity_name)).getText());
                String phone = String.valueOf(((TextView) findViewById(R.id.register_activity_phone)).getText());
                String password = String.valueOf(((TextView) findViewById(R.id.register_activity_password)).getText());

                if (name.trim().length() <= 0) {
                    Toast.makeText(register, "名字不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (name.trim().length() < 4 || name.trim().length() > 12) {
                    Toast.makeText(register, "用户名长度不符合", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (phone.trim().length() <= 0) {
                    Toast.makeText(register, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (phone.trim().length() != 11) {
                    Toast.makeText(register, "请输入11位手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.trim().length() <= 0) {
                    Toast.makeText(register, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.trim().length() < 4 || password.trim().length() > 6) {
                    Toast.makeText(register, "密码长度不符合要求", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<NameValuePair> tt = new ArrayList<NameValuePair>();
                tt.add(new BasicNameValuePair("name", name));
                tt.add(new BasicNameValuePair("password", password));
                tt.add(new BasicNameValuePair("mobile", phone));
                JSONObject get = NetManager.getInstance().sendHttpRequest("account/register", tt, HttpMethod.POST);
                if (get == null) {
                    Log.v("suzhaohui", "get is null");
                    return;
                }
                try {
                    int code = get.getInt("code");
                    if (code != 200) {
                        Toast.makeText(register, get.getString("message"), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    finish();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }

    // class MyCount extends CountDownTimer{
    //
    // public MyCount(long millisInFuture, long countDownInterval) {
    // super(millisInFuture, countDownInterval);
    // System.out.println("你好");
    // }
    // @Override
    // public void onTick(long millisUntilFinished) {
    // String text =String.valueOf( code.getText());
    // if(text.endsWith("验证码") )
    // {
    // code.setText("120");
    // }
    // else
    // {
    // int num = Integer.parseInt(text);
    // if(--num <= 0)
    // {
    // code.setText("验证码");
    // }
    // else
    // {
    // code.setText(String.valueOf(num));
    // }
    //
    // }
    //
    // }
    // @Override
    // public void onFinish() {
    // }
    // }
    // @Override
    // protected void onDestroy() {
    // super.onDestroy();
    //
    // }
}
