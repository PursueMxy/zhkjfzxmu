package com.zhkj.yhfw.paasAct;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.zhkj.yhfw.PaaSActivity;
import com.zhkj.yhfw.R;

public class SafeActivity extends AppCompatActivity {
    private int YFFWWEB_SAFE=2008;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe);
        findViewById(R.id.Safe_img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PaaSActivity.class);
                setResult(YFFWWEB_SAFE,intent);
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
            setResult(YFFWWEB_SAFE,intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
