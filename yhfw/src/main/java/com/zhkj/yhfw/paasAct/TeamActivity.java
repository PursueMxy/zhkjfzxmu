package com.zhkj.yhfw.paasAct;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhkj.yhfw.Bean.TeamBean;
import com.zhkj.yhfw.PaaSActivity;
import com.zhkj.yhfw.R;
import com.zhkj.yhfw.Utlis.AppRequestURL;

public class TeamActivity extends AppCompatActivity implements View.OnClickListener {

    private String token;
    private Context mContext;
    private TextView tv_fname;
    private TextView tv_name;
    private TextView tv_money;
    private String money;
    private String user_id;
    private String mobile;
    private String nickname;
    private String avatar;
    private ImageView img_head;
    private int CUSTON_CODE=2003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        SharedPreferences sp = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        user_id = sp.getString("user_id", "");
        mobile = sp.getString("mobile", "");
        nickname = sp.getString("nickname", "");
        avatar = sp.getString("avatar", "");
        mContext = getApplicationContext();
        InitUI();
        InitData();
    }

    private void InitUI() {
        img_head = findViewById(R.id.Team_img_head);
        tv_fname = findViewById(R.id.Team_tv_fname);
        tv_name = findViewById(R.id.Team_tv_name);
        tv_money = findViewById(R.id.Team_tv_money);
        tv_name.setText("姓名："+nickname);
        Glide.with(this).load(AppRequestURL.URL.HOST+avatar).into(img_head);
        findViewById(R.id.Team_tv_my_team).setOnClickListener(this);
        findViewById(R.id.Team_tv_withdrawlog).setOnClickListener(this);
        findViewById(R.id.Team_tv_qrcode).setOnClickListener(this);
        findViewById(R.id.Team_tv_connission).setOnClickListener(this);
        findViewById(R.id.Team_tv_withdraw).setOnClickListener(this);
        findViewById(R.id.Team_img_back).setOnClickListener(this);
    }

    private void InitData() {
        OkGo.<String>get(AppRequestURL.URL.team)
                .params("type","1")
                .params("token", token)
                .execute(new StringCallback() {
                    private String fname;
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Log.e("team",body);
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        TeamBean teamBean = gson.fromJson(body, TeamBean.class);
                        if (teamBean!=null){
                            TeamBean.DataBean data = teamBean.getData();
                            if (data!=null){
                                fname = data.getFname();
                                money = data.getMoney();
                                tv_fname.setText("推荐人："+fname);
                                tv_money.setText("可提现佣金："+money+"元");
                            }
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Team_tv_my_team:
                startActivity(new Intent(mContext,MyTeamActivity.class));
                finish();
                break;
            case R.id.Team_tv_withdrawlog:
                startActivity(new Intent(mContext,WithdrawlogActivity.class));
                finish();
                break;
            case R.id.Team_tv_qrcode:
                startActivity(new Intent(mContext,QrCodeActivity.class));
                finish();
                break;
            case
            R.id.Team_tv_connission:
                startActivity(new Intent(mContext,ConnissionActivity.class));
                finish();
                break;
            case  R.id.Team_tv_withdraw:
                Intent intent = new Intent(mContext, WithdrawalDefaultActivity.class);
                intent.putExtra("money",money);
                startActivity(intent);
                finish();
            break;
            case R.id.Team_img_back:
                Intent intents = new Intent(getApplicationContext(), PaaSActivity.class);
                setResult(CUSTON_CODE,intents);
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
            Intent intent = new Intent(getApplicationContext(), PaaSActivity.class);
            setResult(CUSTON_CODE,intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
