package com.zhkj.yhfw;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhkj.yhfw.Bean.UserInfoBean;
import com.zhkj.yhfw.Bean.loginbean;
import com.zhkj.yhfw.Utlis.AppRequestURL;
import com.zhkj.yhfw.adapter.GridAdapter;
import com.zhkj.yhfw.customview.CircleImageView;
import com.zhkj.yhfw.paasAct.CommonRecordingActivity;
import com.zhkj.yhfw.paasAct.ContactActivity;
import com.zhkj.yhfw.paasAct.CustonServiceActivity;
import com.zhkj.yhfw.paasAct.PeccancyActivity;
import com.zhkj.yhfw.paasAct.PurseActivity;
import com.zhkj.yhfw.paasAct.SafeActivity;
import com.zhkj.yhfw.paasAct.YffwWebActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PaaSActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_mobile;
    private TextView tv_driving_num;
    private TextView tv_driving_age;
    private TextView tv_name;
    private CircleImageView img_head;
    private GridView paas_grid_one;
    private Context mContext;
    private ArrayList<Map<String, Object>> dataList=new ArrayList<>();
    private SharedPreferences sp;
    private String token;
    private String user_id;
    private String mobile;
    private TextView tv_onlinetime;
    private int PASS_CODE=5200;
    private int PURSE_ONE=2000;
    private int INTENT_ONE=2001;
    private int ORDER_ONE=2002;
    private int CONTACT_CODE=2004;
    private int COMMON_FIVE=2005;
    private int YFFWWEB_CODE=2010;
    private int YFFWWEB_SAFE=2008;
    private int CUSTON_CODE=2007;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paa_s);
        mContext = getApplicationContext();
        sp = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        user_id = sp.getString("user_id", "");
        mobile = sp.getString("mobile", "");
        mobile = sp.getString("nickname", "");
        mobile = sp.getString("nickname", "");
        InitUI();
        //获取个人信息
        OkGo.<String>get(AppRequestURL.URL.getUserInfo)
                .params("type","1")
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        UserInfoBean userInfoBean = gson.fromJson(body, UserInfoBean.class);
                        if (userInfoBean.getCode()==200){
                            UserInfoBean.DataBean data = userInfoBean.getData();
                            if (data!=null){
                                UserInfoBean.DataBean.UserinfoBean userinfo = data.getUserinfo();
                                if (userinfo!=null){
                                    tv_name.setText(userinfo.getNickname());
                                    tv_driving_age.setText("驾龄："+userinfo.getDrivingage()+"年");
                                    tv_driving_num.setText("代驾次数："+userinfo.getCount()+"次");
                                    tv_mobile.setText("电话："+userinfo.getMobile());
                                    tv_onlinetime.setText("在线时长："+userinfo.getOnlinetime()/60 +"分钟");
                                    Glide.with(PaaSActivity.this).load(AppRequestURL.URL.HOST+userinfo.getAvatar()).into(img_head);
                                }
                            }
                        }
                    }
                });
    }

    private void InitUI() {
        findViewById(R.id.paas_img_home).setOnClickListener(this);
        findViewById(R.id.paas_btn_log_off).setOnClickListener(this);
        tv_name = findViewById(R.id.paas_tv_name);
        tv_driving_age = findViewById(R.id.paas_tv_driving_age);
        tv_driving_num = findViewById(R.id.paas_tv_driving_num);
        tv_mobile = findViewById(R.id.paas_tv_mobile);
        tv_onlinetime = findViewById(R.id.paas_tv_onlinetime);
        img_head = findViewById(R.id.paas_img_head);
        paas_grid_one = findViewById(R.id.paas_grid_one);
        dataList = new ArrayList<Map<String, Object>>();
        //图标下的文字
        String name[]={"我的钱包","违章记录","我的订单","我的团队","紧急联系人","录音管理","联系客服","保险流程","用户协议","计费规则"};
        int icno[] = {R.mipmap.paas_icon_purse,R.mipmap.pass_icon_peccancy,R.mipmap.paas_icon_order,R.mipmap.pass_icon_team,R.mipmap.paas_icon_customer,
                R.mipmap.paas_icon_tape,R.mipmap.paas_icon_custon_service,R.mipmap.paas_icon_safe,R.mipmap.pass_icon_user_protocol,R.mipmap.paas_icon_nav_billing};
        for (int i = 0; i <name.length; i++) {
            Map<String, Object> map=new HashMap<>();
            map.put("img", icno[i]);
            map.put("text",name[i]);
            dataList.add(map);

        }
        GridAdapter gridAdapter = new GridAdapter(mContext, dataList);
        paas_grid_one.setAdapter(gridAdapter);
        paas_grid_one.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent=new Intent(mContext, PurseActivity.class);
                        startActivityForResult(intent, PURSE_ONE);
                    break;
                    case 1:
                        Intent intent2 = new Intent(mContext, PeccancyActivity.class);
                        startActivityForResult(intent2, INTENT_ONE);
                    break;
                    case 2:
                        Intent intent3 = new Intent(mContext,OrderListActivity.class);
                        startActivityForResult(intent3, ORDER_ONE);
                        break;
                    case 4:
                        Intent intent4 = new Intent(mContext, ContactActivity.class);
                        startActivityForResult(intent4,CONTACT_CODE);
                        break;
                    case 5:
                        Intent intent5 = new Intent(mContext, CommonRecordingActivity.class);
                        startActivityForResult(intent5,COMMON_FIVE);
                        break;
                    case 6:
                        Intent intent7 = new Intent(mContext, CustonServiceActivity.class);
                        startActivityForResult(intent7, CUSTON_CODE);
                        break;
                    case 7:
                        Intent intent8 = new Intent(mContext, SafeActivity.class);
                        startActivityForResult(intent8, YFFWWEB_SAFE);
                        break;
                    case 8:
                        Intent intent9 = new Intent(mContext, YffwWebActivity.class);
                        intent9.putExtra("name","用户协议");
                        intent9.putExtra("url","http://fztestc.xmhavefun.com/index/index/driver#/CommonRichText?type=driver");
                        startActivityForResult(intent9, YFFWWEB_CODE);
                        break;
                    case 9:
                        Intent intent10 = new Intent(mContext, YffwWebActivity.class);
                        intent10.putExtra("name","用户协议");
                        intent10.putExtra("url","http://fztestc.xmhavefun.com/index/index/driver#/CommonRichText?type=jifei");
                        startActivityForResult(intent10, YFFWWEB_CODE);
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.paas_img_home:
                Intent intent1 = new Intent(this,HomeActivity.class);
                intent1.putExtra("type","home");
                setResult(PASS_CODE,intent1);
                finish();
                break;
            case R.id.paas_btn_log_off:
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("token","");
                editor.putString("avatar","");
                editor.putString("user_id","");
                editor.putString("mobile","");
                editor.putString("nickname","");
                editor.putString("username","");
                editor.putString("password", "");
                editor.commit();
                Intent intent = new Intent(this,HomeActivity.class);
                intent.putExtra("type","login");
                setResult(PASS_CODE,intent);
                finish();
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
            Intent intent1 = new Intent(this,HomeActivity.class);
            intent1.putExtra("type","home");
            setResult(PASS_CODE,intent1);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
