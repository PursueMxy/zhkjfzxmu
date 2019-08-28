package com.zhkj.yhfw.paasAct;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.zhkj.yhfw.HomeActivity;
import com.zhkj.yhfw.PaaSActivity;
import com.zhkj.yhfw.R;

public class YffwWebActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView yffw_web;
    private Context mContext;
    private String url;
    private TextView tv_title;
    private int YFFWWEB_CODE=2010;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yffw_web);
        mContext = getApplicationContext();
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        url = intent.getStringExtra("url");
        InitUI();
    }

    private void InitUI() {
        yffw_web = findViewById(R.id.webview);
        yffw_web.loadUrl(url);
        //设置可自由缩放网页、JS生效
        WebSettings webSettings = yffw_web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        yffw_web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
               yffw_web.loadUrl(url);
               return true;

            }
        });
        findViewById(R.id.YffwWeb_img_back).setOnClickListener(this);
        tv_title = findViewById(R.id.YffwWeb_title);
        tv_title.setText(name);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && yffw_web.canGoBack())
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
        yffw_web.stopLoading();
        yffw_web.removeAllViews();
        yffw_web.destroy();
        yffw_web = null;
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
