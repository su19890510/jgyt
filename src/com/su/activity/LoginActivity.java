package com.su.activity;

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
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class LoginActivity extends Activity {

    private Button mLogin;
    private TextView forgetPassword;
    private TextView register;
    private InputMethodManager manager;
    private LoginActivity login;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        login = this;
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        LinearLayout activity_main = (LinearLayout) findViewById(R.id.login_activity_main);
        activity_main.setOnTouchListener(new OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View arg0, MotionEvent arg1) {
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
        mLogin = (Button) findViewById(R.id.login_activity_login);
        forgetPassword = (TextView) findViewById(R.id.login_activity_forget);
        register = (TextView) findViewById(R.id.login_activity_register);
        forgetPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
                finish();
            }
        });
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });
    }

    /**
     * UI事件监听
     */
    private void setListener() {
        // 登录按钮监听
        mLogin.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String name = String.valueOf(((TextView) findViewById(R.id.login_activity_name)).getText());
                String password = String.valueOf(((TextView) findViewById(R.id.login_activity_password)).getText());
                if (name.trim().length() <= 0) {
                    Toast.makeText(login, "名字不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.trim().length() <= 0) {
                    Toast.makeText(login, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<NameValuePair> tt = new ArrayList<NameValuePair>();
                tt.add(new BasicNameValuePair("name", name));
                tt.add(new BasicNameValuePair("password", password));
                JSONObject get = NetManager.getInstance().sendHttpRequest("account/login", tt, HttpMethod.POST);
                if (get == null) {
                    Log.v("suzhaohui", "login get is null");
                    return;
                }
                try {
                    int code = get.getInt("code");
                    if (code != 200) {
                        Toast.makeText(login, get.getString("message"), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    JSONObject userJson = get.getJSONObject("data").getJSONObject("user");
                    AppData.userInfo = userJson;
                    DataAccess.saveSecurity(userJson.getString("open_id"), userJson.getString("app_id"), userJson.getString("access_token"));
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }
}
