package com.zhkj.yhfw.paasAct;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhkj.yhfw.Bean.WithdrawBean;
import com.zhkj.yhfw.Bean.WithdrawalBean;
import com.zhkj.yhfw.R;
import com.zhkj.yhfw.Utlis.AppRequestURL;
import com.zhkj.yhfw.Utlis.ToastUtils;

public class WithdrawalDefaultActivity extends AppCompatActivity implements View.OnClickListener {

    private String token;
    private TextView tv_cardID;
    private TextView tv_bankName;
    private TextView edt_money;
    private String money;
    private TextView tv_setmoney;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal_default);
        mContext = getApplicationContext();
        Intent intent = getIntent();
        money = intent.getStringExtra("money");
        SharedPreferences sp = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        InitUI();
        InitData();
    }

    private void InitUI() {
        tv_cardID = findViewById(R.id.Withdrawal_tv_cardID);
        tv_bankName = findViewById(R.id.Withdrawal_tv_bankName);
        edt_money = findViewById(R.id.Withdrawal_edt_money);
        tv_setmoney = findViewById(R.id.Withdrawal_tv_setmoney);
        tv_setmoney.setText("可提现的佣金："+money);
        findViewById(R.id.Withdrawal_btn_ok).setOnClickListener(this);
    }

    private void InitData() {
        OkGo.<String>get(AppRequestURL.URL.withdrawal_default)
                .params("type","1")
                .params("token", token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        WithdrawalBean withdrawalBean = gson.fromJson(response.body(), WithdrawalBean.class);
                        if (withdrawalBean!=null){
                            if (withdrawalBean.getCode()==200){
                                WithdrawalBean.DataBean data = withdrawalBean.getData();
                                if (data!=null){
                                    String cardID = data.getCardID();
                                    tv_bankName.setText( data.getBankName());
                                    tv_cardID.setText(cardID.substring(0,3)+"****"+cardID.substring(cardID.length()-3,cardID.length()));
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Withdrawal_btn_ok:
                double v1 = Double.parseDouble(money);
                String s = edt_money.getText().toString();
                if (!s.equals("")){
                    double i1 = Double.parseDouble(s);
                    if (i1<v1){
                      OkGo.<String>get(AppRequestURL.URL.withdraw)
                              .params("type","1")
                              .params("token", token)
                              .params("money",i1+"")
                              .execute(new StringCallback() {
                                  @Override
                                  public void onSuccess(Response<String> response) {
                                      GsonBuilder gsonBuilder = new GsonBuilder();
                                      Gson gson = gsonBuilder.create();
                                      WithdrawBean withdrawBean = gson.fromJson(response.body(), WithdrawBean.class);
                                      if (withdrawBean!=null){
                                          ToastUtils.showToast(mContext,withdrawBean.getMsg());
                                      }
                                  }
                              });
                    }else {
                        ToastUtils.showToast(mContext,"不能大于可提现金额");
                    }
                }else {
                    ToastUtils.showToast(mContext,"输入不能为空");
                }
                break;
                default:
                    break;
        }
    }
}
