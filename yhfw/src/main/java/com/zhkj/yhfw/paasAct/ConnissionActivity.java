package com.zhkj.yhfw.paasAct;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhkj.yhfw.Bean.CommissionBean;
import com.zhkj.yhfw.R;
import com.zhkj.yhfw.Utlis.AppRequestURL;
import com.zhkj.yhfw.Utlis.TimeUtils;
import com.zhkj.yhfw.Utlis.ToastUtils;
import com.zhkj.yhfw.adapter.CommissionAdapter;
import com.zhkj.yhfw.adapter.WithdrawlogAdapter;
import com.zhouyou.recyclerview.XRecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ConnissionActivity extends AppCompatActivity implements View.OnClickListener {

    private String token;
    private Context mContext;
    private int monthsNum=0;
    private int current_dt=0;
    private int select_dt=0;
    private long start_dt;
    private long stop_dt;
    private TextView tv_date;
    private TextView tv_message;
    private TextView tv_total_income;
    private XRecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private CommissionAdapter myTeamAdapter;
    private List<CommissionBean.DataBean.ListBean> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connission);
        SharedPreferences sp = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        mContext = getApplicationContext();
        InitUI();
        IninDate();
    }

    private void InitData() {
        OkGo.<String>get(AppRequestURL.URL.commission)
                .params("type","1")
                .params("token", token)
                .params("start",start_dt)
                .params("end",stop_dt)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        try {
                            CommissionBean commissionBean = gson.fromJson(response.body(), CommissionBean.class);
                            if (commissionBean != null) {
                                if (commissionBean.getCode() == 200) {
                                    tv_message.setVisibility(View.GONE);
                                    tv_total_income.setText("总收入：¥ " + commissionBean.getData().getSum());
                                    list = commissionBean.getData().getList();
                                    myTeamAdapter.setListAll(list);
                                    mRecyclerView.setAdapter(myTeamAdapter);
                                } else {
                                    tv_message.setVisibility(View.VISIBLE);
                                    tv_total_income.setText("总收入：¥ 0.00");
                                }
                            }
                        }catch (Exception e){
                            ToastUtils.showToast(mContext,"没有更多");
                            list.clear();
                            myTeamAdapter.setListAll(list);
                            mRecyclerView.setAdapter(myTeamAdapter);
                            tv_total_income.setText("总收入：¥ 0.00");
                        }
                    }
                });
    }

    private void IninDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Calendar calendar = sdf.getCalendar();
        Date date = TimeUtils.nextMonth(monthsNum);
        calendar.setTime(date);
        int year =   calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        if (month<10){
            if (current_dt == 0) {
                current_dt = Integer.parseInt(year + "0" + month);
            } else {
                select_dt = Integer.parseInt(year + "0" + month);
            }
        }else {
            if (current_dt == 0) {
                current_dt = Integer.parseInt(year + "" + month);
            } else {
                select_dt = Integer.parseInt(year + "" + month);
            }
        }
        if (select_dt<current_dt||select_dt==current_dt) {
            int daysOfMonth = TimeUtils.getDaysOfMonth(year, month+1);
            try {
                if (month<10) {
                    start_dt = TimeUtils.dateToStamp("" + year + "0" + month + "01000000") / 1000;
                    stop_dt = TimeUtils.dateToStamp("" + year + "0" + month + "" + daysOfMonth + "235959") / 1000;
                }else {
                    start_dt = TimeUtils.dateToStamp("" + year + "" + month + "01000000") / 1000;
                    stop_dt = TimeUtils.dateToStamp("" + year + "" + month + "" + daysOfMonth + "235959") / 1000;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tv_date.setText(year + "年" + month + "月");
        } else {
            monthsNum=monthsNum-1;
            Toast.makeText(mContext,"不能大于当前日期",Toast.LENGTH_SHORT).show();
        }
        InitData();
    }

    private void InitUI() {
        tv_date = findViewById(R.id.commission_tv_date);
        tv_message = findViewById(R.id.commission_tv_message);
        tv_total_income = findViewById(R.id.commission_tv_total_income);
        findViewById(R.id.commission_img_last).setOnClickListener(this);
        findViewById(R.id.commission_img_next).setOnClickListener(this);
        findViewById(R.id.commission_img_back).setOnClickListener(this);
        mRecyclerView = findViewById(R.id.Commission_recyclerView);
        mLayoutManager = new LinearLayoutManager(mContext);
        myTeamAdapter = new CommissionAdapter(mContext);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.commission_img_last:
                monthsNum=monthsNum-1;
                IninDate();
                break;
            case R.id.commission_img_next:
                monthsNum=monthsNum+1;
                IninDate();
                break;
            case R.id.commission_img_back:
                startActivity(new Intent(mContext, TeamActivity.class));
                finish();
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
