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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhkj.yhfw.Bean.MyTeamBean;
import com.zhkj.yhfw.HomeActivity;
import com.zhkj.yhfw.R;
import com.zhkj.yhfw.Utlis.AppRequestURL;
import com.zhkj.yhfw.adapter.MyTeamAdapter;
import com.zhouyou.recyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyTeamActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private XRecyclerView mRecyclerView;
    private String token;
    private int page=1;
    private TextView tv_message;
    private MyTeamAdapter myTeamAdapter;
    private List<MyTeamBean.DataBean.TeamMemberBean> team_member=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_team);
        SharedPreferences sp = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        mContext = getApplicationContext();
        InitUI();
        InitData();
    }

    private void InitUI() {
     findViewById(R.id.MyTeam_img_back).setOnClickListener(this);
        tv_message = findViewById(R.id.my_team_tv_message);
        mRecyclerView = findViewById(R.id.my_team_recyclerView);
        myTeamAdapter = new MyTeamAdapter(mContext);
        mRecyclerView.setAdapter(myTeamAdapter);

    }

    private void InitData() {
        OkGo.<String>get(AppRequestURL.URL.myteam)
                .params("type","1")
                .params("token", token)
                .params("page",page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        MyTeamBean myTeamBean = gson.fromJson(response.body(), MyTeamBean.class);
                        if (myTeamBean.getCode()==200){
                            tv_message.setVisibility(View.GONE);
                            myTeamAdapter.notifyDataSetChanged();
                            team_member = myTeamBean.getData().getTeam_member();
                            myTeamAdapter.setListAll(team_member);
                        }else {
                            tv_message.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.MyTeam_img_back:
                startActivity(new Intent(mContext,TeamActivity.class));
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
            startActivity(new Intent(mContext, TeamActivity.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
