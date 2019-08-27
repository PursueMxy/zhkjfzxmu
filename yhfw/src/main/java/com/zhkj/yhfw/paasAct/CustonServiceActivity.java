package com.zhkj.yhfw.paasAct;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.zhkj.yhfw.PaaSActivity;
import com.zhkj.yhfw.R;

public class CustonServiceActivity extends AppCompatActivity {

    private int CUSTON_CODE=2007;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custon_service);
        InitUI();
    }

    private void InitUI() {
        findViewById(R.id.custom_service_img_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentcall = new Intent();
                //设置拨打电话的动作
                intentcall.setAction(Intent.ACTION_CALL);
                //设置拨打电话的号码
                intentcall.setData(Uri.parse("tel:" + "05925711628"));
                //开启打电话的意图
                startActivity(intentcall);
            }
        });
        findViewById(R.id.CustonService_img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PaaSActivity.class);
                setResult(CUSTON_CODE,intent);
                finish();
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Intent intent = new Intent(this, PaaSActivity.class);
            setResult(CUSTON_CODE,intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
