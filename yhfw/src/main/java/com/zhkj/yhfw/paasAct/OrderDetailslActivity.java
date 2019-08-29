package com.zhkj.yhfw.paasAct;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhkj.yhfw.Bean.OrderDetails1Bean;
import com.zhkj.yhfw.Bean.OrderDetailsBean;
import com.zhkj.yhfw.R;
import com.zhkj.yhfw.Utlis.AppRequestURL;
import com.zhkj.yhfw.Utlis.TimeUtils;

public class OrderDetailslActivity extends AppCompatActivity {

    private String order_id;
    private int ORDERDETAIL_CODE=4001;
    private String token;
    private TextView tv_startaddress;
    private TextView tv_stopaddress;
    private TextView tv_start_money;
    private TextView tv_start_mileage;
    private TextView tv_mileage_money;
    private TextView tv_return_money;
    private TextView tv_waitTime;
    private TextView tv_wait_money;
    private TextView tv_money;
    private TextView tv_pay_money;
    private TextView tv_orderTime;
    private String statusType;
    private LinearLayout ll_statusok;
    private TextView tv_backReason;
    private TextView tv_backTime;
    private LinearLayout ll_statusBack;
    private String type="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detailsil);

        SharedPreferences sp = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        statusType = intent.getStringExtra("StatusType");
        type = intent.getStringExtra("type");
        InitUI();
        InitData();
    }

    private void InitData() {
        OkGo.<String>get(AppRequestURL.URL.OrderDetail)
                .params("type","1")
                .params("token",token)
                .params("order_id",order_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        if (type.equals("0")){
                            OrderDetailsBean orderDetailsBean = gson.fromJson(body, OrderDetailsBean.class);
                            if (orderDetailsBean!=null){
                                if (orderDetailsBean.getCode()==200){
                                    OrderDetailsBean.DataBean data = orderDetailsBean.getData();
                                    if (data!=null){
                                        tv_startaddress.setText(data.getStartingpoint());
                                        tv_stopaddress.setText(data.getDestination());
                                        tv_start_money.setText("¥ "+data.getStart_money());
                                        tv_start_mileage.setText(data.getMileage()+"公里");
                                        tv_mileage_money.setText("¥ "+data.getMileage_money());
                                        tv_return_money.setText("¥ "+data.getReturn_money());
                                        tv_waitTime.setText(data.getWait_time()+"秒");
                                        tv_wait_money.setText("¥ "+data.getWait_money());
                                        tv_money.setText("¥ "+data.getMoney());
                                        tv_pay_money.setText("¥ "+data.getPaymoney());
                                        tv_orderTime.setText(TimeUtils.GetOrderTime(data.getEndtime()-data.getCreatetime()));
                                        tv_backReason.setText(data.getReason());
                                        tv_backTime.setText(TimeUtils.getDateToString(data.getUpdatetime()));
                                    }
                                }
                            }
                        }else {
                            OrderDetails1Bean orderDetailsBean = gson.fromJson(body, OrderDetails1Bean.class);
                            if (orderDetailsBean!=null){
                                if (orderDetailsBean.getCode()==200){
                                    OrderDetails1Bean.DataBean data = orderDetailsBean.getData();
                                    if (data!=null){
                                        tv_startaddress.setText(data.getStartingpoint());
                                        tv_stopaddress.setText(data.getDestination());
                                        tv_start_money.setText("¥ "+data.getStart_money());
                                        tv_start_mileage.setText(data.getMileage()+"公里");
                                        tv_mileage_money.setText("¥ "+data.getMileage_money());
                                        tv_return_money.setText("¥ "+data.getReturn_money());
                                        tv_waitTime.setText(data.getWait_time()+"秒");
                                        tv_wait_money.setText("¥ "+data.getWait_money());
                                        tv_money.setText("¥ "+data.getMoney());
                                        tv_pay_money.setText("¥ "+data.getPaymoney());
                                        tv_orderTime.setText(TimeUtils.GetOrderTime(data.getEndtime()-data.getCreatetime()));
                                        tv_backReason.setText(data.getReason());
                                        tv_backTime.setText(TimeUtils.getDateToString(data.getUpdatetime()));
                                    }
                                }
                            }
                        }

                    }
                });
    }

    private void InitUI() {
        ll_statusok = findViewById(R.id.orderdtl_ll_statusok);
        ll_statusBack = findViewById(R.id.orderdtl_ll_statusBack);
        tv_startaddress = findViewById(R.id.orderdtl_tv_startaddress);
        tv_stopaddress = findViewById(R.id.orderdtl_tv_stopaddress);
        tv_start_money = findViewById(R.id.orderdtl_tv_start_money);
        tv_start_mileage = findViewById(R.id.orderdtl_tv_start_mileage);
        tv_mileage_money = findViewById(R.id.orderdtl_tv_mileage_money);
        tv_return_money = findViewById(R.id.orderdtl_tv_return_money);
        tv_waitTime = findViewById(R.id.orderdtl_tv_waitTime);
        tv_wait_money = findViewById(R.id.orderdtl_tv_wait_money);
        tv_money = findViewById(R.id.orderdtl_tv_money);
        tv_pay_money = findViewById(R.id.orderdtl_tv_pay_money);
        tv_orderTime = findViewById(R.id.orderdtl_tv_orderTime);
        tv_backReason = findViewById(R.id.orderdtl_tv_backReason);
        tv_backTime = findViewById(R.id.orderdtl_tv_backTime);
        if (statusType.equals("已取消")){
            ll_statusok.setVisibility(View.GONE);
            ll_statusBack.setVisibility(View.VISIBLE);
        }else {
            ll_statusBack.setVisibility(View.GONE);
            ll_statusok.setVisibility(View.VISIBLE);
        }
    }
}
