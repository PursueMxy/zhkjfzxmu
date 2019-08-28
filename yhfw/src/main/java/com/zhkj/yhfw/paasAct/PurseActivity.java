package com.zhkj.yhfw.paasAct;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhkj.yhfw.Bean.PurseBean;
import com.zhkj.yhfw.OrderListActivity;
import com.zhkj.yhfw.PaaSActivity;
import com.zhkj.yhfw.R;
import com.zhkj.yhfw.Utlis.AppRequestURL;
import com.zhkj.yhfw.Utlis.TimeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PurseActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_date;
    private int monthsNum=0;
    private int current_dt=0;
    private int select_dt=0;
    private long start_dt;
    private long stop_dt;
    private Context mContext;
    private String token;
    private int PURSE_ONE=2000;
    private TextView tv_balance;
    private List<PurseBean.DataBean.MoneyLogBean> money_log=new ArrayList<>();
    private ListView purse_list;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purse);
        SharedPreferences sp = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        mContext = getApplicationContext();
        InitUI();
        IninDate();
    }

    private void InitUI() {
        findViewById(R.id.purse_img_back).setOnClickListener(this);
        findViewById(R.id.purse_img_last).setOnClickListener(this);
        findViewById(R.id.purse_img_next).setOnClickListener(this);
        tv_balance = findViewById(R.id.purse_tv_balance);
        tv_date = findViewById(R.id.purse_tv_date);
        purse_list = findViewById(R.id.purse_list);
        myAdapter = new MyAdapter();
        purse_list.setAdapter(myAdapter);
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

    private void InitData() {
        OkGo.<String>get(AppRequestURL.URL.index)
                .params("type",1)
                .params("token", token)
                .params("start",start_dt)
                .params("end",stop_dt)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        PurseBean purseBean = gson.fromJson(body, PurseBean.class);
                        if (purseBean!=null){
                            if (purseBean.getCode()==200){
                                if (purseBean.getData()!=null){
                                    PurseBean.DataBean data = purseBean.getData();
                                    tv_balance.setText(data.getMoney()+" 元");
                                    if (data.getMoney_log()!=null){
                                        money_log = data.getMoney_log();
                                    }
                                }
                                myAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.purse_img_back:
              Intent intent = new Intent(getApplicationContext(), PaaSActivity.class);
              setResult(PURSE_ONE,intent);
              finish();
              break;
          case R.id.purse_img_last:
              monthsNum=monthsNum-1;
              IninDate();
              break;
          case R.id.purse_img_next:
              monthsNum = monthsNum + 1;
              IninDate();
              break;
      }
    }

    public class MyAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return (money_log.size()-1);
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
             View view;
            if (convertView == null) {
                view = getLayoutInflater().inflate(R.layout.list_purse_item, null);
            } else{
                view = convertView;
            }
            TextView tv_content = view.findViewById(R.id.list_purse_content);
            TextView  tv_cut_money = view.findViewById(R.id.list_purse_cut_money);
            TextView tv_purse_date = view.findViewById(R.id.list_purse_date);
            tv_content.setText(money_log.get(i).getEvent());
            tv_cut_money.setText(money_log.get(i).getMoney()+"");
            tv_purse_date.setText(TimeUtils.getDayToString(money_log.get(i).getCreatetime()));
            return  view;
        }
    }
}
