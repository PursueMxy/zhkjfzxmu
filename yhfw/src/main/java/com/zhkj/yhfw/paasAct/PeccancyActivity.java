package com.zhkj.yhfw.paasAct;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhkj.yhfw.PaaSActivity;
import com.zhkj.yhfw.R;
import com.zhkj.yhfw.Utlis.AppRequestURL;
import com.zhkj.yhfw.Utlis.TimeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PeccancyActivity extends AppCompatActivity implements View.OnClickListener {

    private int monthsNum=0;
    private Context mContext;
    private long start_dt;
    private long stop_dt;
    private TextView tv_peccancyname;
    private int current_dt=0;
    private int select_dt=0;
    private String token;
    private int INTENT_ONE=2001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peccancy);
        SharedPreferences sp = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        mContext = getApplicationContext();
        InitUI();
        IninDate();
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
            int daysOfMonth = TimeUtils.getDaysOfMonth(year, month);
            try {
                Log.e("yearsdasfasf",""+ year+"dfsafad"+ month );
                start_dt = TimeUtils.dateToStamp(""+ year+""+ month + "01000000")/1000;
                stop_dt = TimeUtils.dateToStamp("" + year+ "" + month+ "" + daysOfMonth + "235959")/1000;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tv_peccancyname.setText(year + "年" + month + "月");
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
                        Log.e("余额",response.body());
                    }
                });
    }

    private void InitUI() {
        findViewById(R.id.peccancy_img_back).setOnClickListener(this);
        findViewById(R.id.peccancy_img_last).setOnClickListener(this);
        findViewById(R.id.peccancy_img_next).setOnClickListener(this);
        tv_peccancyname = findViewById(R.id.peccancy_tv_peccancyname);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.peccancy_img_back:
                Intent intent = new Intent(getApplicationContext(), PaaSActivity.class);
                setResult(INTENT_ONE,intent);
                finish();
                break;
            case R.id.peccancy_img_last:
                monthsNum=monthsNum-1;
                IninDate();
                break;
            case R.id.peccancy_img_next:
                    monthsNum = monthsNum + 1;
                IninDate();
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
            Intent intent = new Intent(getApplicationContext(), PaaSActivity.class);
            setResult(INTENT_ONE,intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
