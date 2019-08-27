package com.zhkj.yhfw.paasAct;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.zhkj.yhfw.PaaSActivity;
import com.zhkj.yhfw.R;

public class CommonRecordingActivity extends AppCompatActivity {
    private int COMMON_FIVE=2005;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_recording);
        findViewById(R.id.CommonRecording_img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommonRecordingActivity.this, PaaSActivity.class);
                setResult(COMMON_FIVE,intent);
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
            setResult(COMMON_FIVE,intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
