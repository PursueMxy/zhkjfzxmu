package com.zhkj.yhfw.paasAct;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhkj.yhfw.Bean.QrBean;
import com.zhkj.yhfw.R;
import com.zhkj.yhfw.Utlis.AppRequestURL;
import com.zhkj.yhfw.Utlis.ToastUtils;

public class QrCodeActivity extends AppCompatActivity implements View.OnClickListener {

    private String token;
    private String user_id;
    private String mobile;
    private String nickname;
    private String avatar;
    private ImageView img_qrcode;
    private TextView tv_nikenema;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        mContext = getApplicationContext();
        SharedPreferences sp = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        user_id = sp.getString("user_id", "");
        mobile = sp.getString("mobile", "");
        nickname = sp.getString("nickname", "");
        avatar = sp.getString("avatar", "");
        InitUI();
        InitData();
    }

    private void InitData() {
        OkGo.<String>get(AppRequestURL.URL.getQR)
                .params("type","1")
                .params("token", token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        QrBean qrBean = gson.fromJson(response.body(), QrBean.class);
                        if (qrBean!=null){
                            if (qrBean.getCode()==200){

                            }else {
                                ToastUtils.showToast(mContext,qrBean.getMsg());
                            }
                        }
                    }
                });
    }

    private void InitUI() {
        ImageView img_head = findViewById(R.id.qrcode_img_head);
        img_qrcode = findViewById(R.id.qrcode_img_qrcode);
        tv_nikenema = findViewById(R.id.qrcode_tv_nikenema);
        findViewById(R.id.QrCode_img_back).setOnClickListener(this);
        tv_nikenema.setText(nickname);
        Glide.with(this).load(AppRequestURL.URL.HOST+avatar).into(img_head);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.QrCode_img_back:
                startActivity(new Intent(mContext, TeamActivity.class));
                finish();
                break;
                default:
                    break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            startActivity(new Intent(mContext, TeamActivity.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
