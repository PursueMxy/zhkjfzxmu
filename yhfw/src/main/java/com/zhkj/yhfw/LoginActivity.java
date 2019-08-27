package com.zhkj.yhfw;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhkj.yhfw.Bean.LoginPublicBean;
import com.zhkj.yhfw.Bean.loginbean;
import com.zhkj.yhfw.Utlis.AppRequestURL;
import com.zhkj.yhfw.customview.CircleImageView;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edt_password;
    private TextView edt_username;
    private String username;
    private String password;
    private SharedPreferences sp;
    private CircleImageView imghead;
    private Context mContext;
    private boolean isFinish;
    private Handler handler;
    private Button login_btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = getApplicationContext();
        XXPermissions.with(this)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {

                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {

                    }
                });
        sp = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        handler =new Handler();
        InitUI();
    }

    private void InitUI() {
        edt_password = findViewById(R.id.login_edt_password);
        edt_username = findViewById(R.id.login_edt_username);
        imghead = findViewById(R.id.login_img_head);
        login_btn_login = findViewById(R.id.login_btn_login);
        login_btn_login.setOnClickListener(this);
        edt_username.setText(sp.getString("username", ""));
        edt_password.setText(sp.getString("password", ""));
        String avatar = sp.getString("avatar", "");
        if (avatar!=null){
            Glide.with(this).load(AppRequestURL.URL.HOST+avatar).into(imghead);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn_login:
                login_btn_login.setEnabled(false);
                username = edt_username.getText().toString();
                password = edt_password.getText().toString();
                OkGo.<String>post(AppRequestURL.URL.Login)
                        .params("account",username)
                        .params("password",password)
                        .params("type","1")
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                String body = response.body();
                                GsonBuilder builder = new GsonBuilder();
                                Gson gson = builder.create();
                                LoginPublicBean loginPublicBean = gson.fromJson(body, LoginPublicBean.class);
                                if (loginPublicBean.getCode()==200) {
                                    loginbean loginbean = gson.fromJson(body, loginbean.class);
                                    if (loginbean.getCode() == 200) {
                                        SharedPreferences.Editor editor = sp.edit();
                                        com.zhkj.yhfw.Bean.loginbean.DataBean data = loginbean.getData();
                                        if (data != null) {
                                            com.zhkj.yhfw.Bean.loginbean.DataBean.UserinfoBean userinfo = data.getUserinfo();
                                            String token = userinfo.getToken();
                                            String avatar = userinfo.getAvatar();
                                            editor.putString("token",token);
                                            editor.putString("avatar", avatar);
                                            editor.putString("user_id", userinfo.getUser_id() + "");
                                            editor.putString("mobile", userinfo.getMobile());
                                            editor.putString("nickname", userinfo.getNickname());
                                            editor.putString("username", username);
                                            editor.putString("password", password);
                                            editor.commit();
                                            startActivity(new Intent(mContext, HomeActivity.class));
                                            finish();
                                        }
                                        login_btn_login.setEnabled(true);
                                    } else {
                                        login_btn_login.setEnabled(true);
                                    }
                                }else {
                                    Toast.makeText(mContext,loginPublicBean.getMsg(),Toast.LENGTH_SHORT).show();
                                    login_btn_login.setEnabled(true);
                                }
                            }
                        });
                break;
                default:
                    break;
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 如果是返回键,直接返回到桌面 || keyCode == KeyEvent.KEYCODE_HOME
        if(keyCode == KeyEvent.KEYCODE_BACK ){
            if (isFinish) {
                System.exit(0);
                return true;
            } else {
                Snackbar.make(edt_username, "再按一次退出程序", Snackbar.LENGTH_LONG)
                        .setAction("立即退出", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                System.exit(0);
                            }
                        })
                        .show();
                isFinish = true;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isFinish = false;
                    }
                }, 2000);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
