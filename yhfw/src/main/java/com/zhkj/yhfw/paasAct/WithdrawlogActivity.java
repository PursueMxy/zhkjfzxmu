package com.zhkj.yhfw.paasAct;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhkj.yhfw.R;
import com.zhkj.yhfw.Utlis.AppRequestURL;
import com.zhouyou.recyclerview.XRecyclerView;

public class WithdrawlogActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView slt_one;
    private TextView slt_two;
    private TextView slt_three;
    private TextView slt_four;
    private TextView slt_five;
    private XRecyclerView withdrawlog_recyclerView;
    private int page=1;
    private int status=0;
    private String token;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawlog);
        SharedPreferences sp = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        mContext = getApplicationContext();
        InitUI();
        InitData();
    }

    private void InitData() {
        OkGo.<String>get(AppRequestURL.URL.withdrawlog)
                .params("type","1")
                .params("token", token)
                .params("page",page)
                .params("status",status)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }
                });
    }

    private void InitUI() {
        slt_one = findViewById(R.id.Withdrawlog_slt_one);
        slt_two = findViewById(R.id.Withdrawlog_slt_two);
        slt_three = findViewById(R.id.Withdrawlog_slt_three);
        slt_four = findViewById(R.id.Withdrawlog_slt_four);
        slt_five = findViewById(R.id.Withdrawlog_slt_five);
        findViewById(R.id.Withdrawlog_img_back).setOnClickListener(this);
        slt_one.setOnClickListener(this);
        slt_two.setOnClickListener(this);
        slt_three.setOnClickListener(this);
        slt_four.setOnClickListener(this);
        slt_five.setOnClickListener(this);
        withdrawlog_recyclerView = findViewById(R.id.Withdrawlog_recyclerView);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.Withdrawlog_img_back:
             startActivity(new Intent(mContext,TeamActivity.class));
             finish();
             break;
         case R.id.Withdrawlog_slt_one:
             slt_one.setTextColor(getResources().getColor(R.color.color_ffd700));
             slt_two.setTextColor(getResources().getColor(R.color.color_black));
             slt_three.setTextColor(getResources().getColor(R.color.color_black));
             slt_four.setTextColor(getResources().getColor(R.color.color_black));
             slt_five.setTextColor(getResources().getColor(R.color.color_black));
             status=0;
             InitData();
             break;
         case R.id.Withdrawlog_slt_two:
             slt_one.setTextColor(getResources().getColor(R.color.color_black));
             slt_two.setTextColor(getResources().getColor(R.color.color_ffd700));
             slt_three.setTextColor(getResources().getColor(R.color.color_black));
             slt_four.setTextColor(getResources().getColor(R.color.color_black));
             slt_five.setTextColor(getResources().getColor(R.color.color_black));
             status=1;
             InitData();
             break;
         case R.id.Withdrawlog_slt_three:
             slt_one.setTextColor(getResources().getColor(R.color.color_black));
             slt_two.setTextColor(getResources().getColor(R.color.color_black));
             slt_three.setTextColor(getResources().getColor(R.color.color_ffd700));
             slt_four.setTextColor(getResources().getColor(R.color.color_black));
             slt_five.setTextColor(getResources().getColor(R.color.color_black));
             status=2;
             InitData();
             break;
         case R.id.Withdrawlog_slt_four:
             slt_one.setTextColor(getResources().getColor(R.color.color_black));
             slt_two.setTextColor(getResources().getColor(R.color.color_black));
             slt_three.setTextColor(getResources().getColor(R.color.color_black));
             slt_four.setTextColor(getResources().getColor(R.color.color_ffd700));
             slt_five.setTextColor(getResources().getColor(R.color.color_black));
             status=3;
             InitData();
             break;
         case R.id.Withdrawlog_slt_five:
             slt_one.setTextColor(getResources().getColor(R.color.color_black));
             slt_two.setTextColor(getResources().getColor(R.color.color_black));
             slt_three.setTextColor(getResources().getColor(R.color.color_black));
             slt_four.setTextColor(getResources().getColor(R.color.color_black));
             slt_five.setTextColor(getResources().getColor(R.color.color_ffd700));
             status=4;
             InitData();
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
