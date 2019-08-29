package com.zhkj.yhfw.paasAct;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhkj.yhfw.Bean.ChargingDocBean;
import com.zhkj.yhfw.Bean.DriverAgreementBean;
import com.zhkj.yhfw.HomeActivity;
import com.zhkj.yhfw.PaaSActivity;
import com.zhkj.yhfw.R;
import com.zhkj.yhfw.Utlis.AppRequestURL;

public class YffwWebActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView yffw_web;
    private Context mContext;
    private String url;
    private TextView tv_title;
    private int YFFWWEB_CODE=2010;
    private String name;
    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yffw_web);
        mContext = getApplicationContext();
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        url = intent.getStringExtra("url");
        InitUI();
        InitData();
    }

    private void InitData() {
        OkGo.<String>get(url)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new GsonBuilder().create();
                        if (name.equals("用户协议")) {
                            DriverAgreementBean driverAgreementBean = gson.fromJson(response.body(), DriverAgreementBean.class);
                            DriverAgreementBean.DataBean data = driverAgreementBean.getData();
                            tv_content.setText(Html.fromHtml(data.getDriver_agreement()));
                        }else{
                            ChargingDocBean chargingDocBean = gson.fromJson(response.body(), ChargingDocBean.class);
                            ChargingDocBean.DataBean data = chargingDocBean.getData();
                            tv_content.setText(Html.fromHtml(data.getCharging_doc()));
                        }
                    }
                });

    }

    private void InitUI() {
        tv_content = findViewById(R.id.YffwWeb_tv_content);
        findViewById(R.id.YffwWeb_img_back).setOnClickListener(this);
        tv_title = findViewById(R.id.YffwWeb_title);
        tv_title.setText(name);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(this, PaaSActivity.class);
            setResult(YFFWWEB_CODE,intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.YffwWeb_img_back:
                Intent intent = new Intent(this, PaaSActivity.class);
                setResult(YFFWWEB_CODE,intent);
                finish();
                break;
                default:
                    break;
        }
    }
}
