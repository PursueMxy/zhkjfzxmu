package com.zhkj.yhfw.paasAct;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhkj.yhfw.Bean.WithdrawlogBean;
import com.zhkj.yhfw.R;
import com.zhkj.yhfw.Utlis.AppRequestURL;
import com.zhkj.yhfw.adapter.MyTeamAdapter;
import com.zhkj.yhfw.adapter.WithdrawlogAdapter;
import com.zhouyou.recyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WithdrawlogActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView slt_one;
    private TextView slt_two;
    private TextView slt_three;
    private TextView slt_four;
    private TextView slt_five;
    private XRecyclerView mRecyclerView;
    private int page=1;
    private int status=0;
    private String token;
    private Context mContext;
    private LinearLayoutManager mLayoutManager;
    private WithdrawlogAdapter myTeamAdapter;
    private List<WithdrawlogBean.DataBean.ListBean> list=new ArrayList<>();

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
                        Gson gson = new GsonBuilder().create();
                        try {
                            WithdrawlogBean withdrawlogBean = gson.fromJson(response.body(), WithdrawlogBean.class);
                            if (withdrawlogBean != null) {
                                if (withdrawlogBean.getCode() == 200) {
                                    list = withdrawlogBean.getData().getList();
                                    myTeamAdapter.setListAll(list);
                                    mRecyclerView.setAdapter(myTeamAdapter);
                                }
                            }
                        }catch (Exception e){
                           list.clear();
                            myTeamAdapter.setListAll(list);
                            mRecyclerView.setAdapter(myTeamAdapter);
                        }
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
        mRecyclerView = findViewById(R.id.Withdrawlog_recyclerView);
        mLayoutManager = new LinearLayoutManager(mContext);
        myTeamAdapter = new WithdrawlogAdapter(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(myTeamAdapter);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.refreshComplete();//刷新动画完成
            }

            @Override
            public void onLoadMore() {
                //加载更多
                mRecyclerView.loadMoreComplete();//加载动画完成
                mRecyclerView.setNoMore(true);//数据加载完成
            }
        });


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
