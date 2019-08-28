package com.zhkj.yhfw;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhkj.yhfw.Bean.OrderBean;
import com.zhkj.yhfw.Bean.OrderListBean;
import com.zhkj.yhfw.Bean.UserInfoBean;
import com.zhkj.yhfw.Utlis.AppRequestURL;
import com.zhkj.yhfw.Utlis.LiteDataBean;
import com.zhkj.yhfw.Utlis.TimeUtils;
import com.zhkj.yhfw.paasAct.OrderDetailslActivity;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OrderListActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView order_list;
    private List<OrderBean> orderBeanList=new ArrayList<>();
    private Context mContext;
    private int pageType=1;
    private String token;
    private List<OrderListBean.DataBean.OrderBean> orderList=new ArrayList<>();
    private OrderAdapter orderAdapter;
    private int ORDER_ONE=2001;
    private int ORDERDETAIL_CODE=4001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        mContext = getApplicationContext();
        SharedPreferences sp = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        orderBeanList = LitePal.findAll(OrderBean.class);
        InitUI();
        InitData();
    }

    private void InitData() {
        OkGo.<String>get(AppRequestURL.URL.HOST+"/api/order/index")
                .params("type","1")
                .params("token", token)
                .params("page",pageType)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        orderList.clear();
                        String body = response.body();
                        Log.e("订单详情",body);
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        OrderListBean orderListBean = gson.fromJson(body, OrderListBean.class);
                        if (orderListBean.getCode()==200){
                            OrderListBean.DataBean data = orderListBean.getData();
                            if (data!=null){
                                orderList = data.getOrder();
                            }

                        }
                        orderAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void InitUI() {
        order_list = findViewById(R.id.order_list);
        findViewById(R.id.tv_orderList_back).setOnClickListener(this);
        orderAdapter = new OrderAdapter();
        order_list.setAdapter(orderAdapter);
        order_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, OrderDetailslActivity.class);
                intent.putExtra("order_id",orderList.get(position).getId()+"");
                intent.putExtra("StatusType",orderList.get(position).getStatus());
                startActivityForResult(intent, ORDERDETAIL_CODE);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_orderList_back:
                Intent intent = new Intent(getApplicationContext(), PaaSActivity.class);
                setResult(ORDER_ONE,intent);
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
            setResult(ORDER_ONE,intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public class OrderAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return orderList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewHolder holder = null;
            View  convertView = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss",
                    Locale.getDefault());
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_order, null);
                holder = new ViewHolder();
                holder.tv_driverName = convertView.findViewById(R.id.orderList_driverName);
                holder.tv_order_status = convertView.findViewById(R.id.orderList_order_status);
                holder.tv_orderNo = convertView.findViewById(R.id.orderList_orderNo);
                holder.tv_startDt = convertView.findViewById(R.id.orderList_startDt);
                holder.tv_StopDt=convertView.findViewById(R.id.orderList_stopDt);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
                holder.tv_orderNo.setText(TimeUtils.getDateToString(orderList.get(position).getCreatetime()));
                if (orderList.get(position).getType().equals("0")) {
                    holder.tv_driverName.setText("酒后代驾");
                } else {
                    holder.tv_driverName.setText("包时代驾");
                }
                holder.tv_order_status.setText(orderList.get(position).getStatus());
                holder.tv_startDt.setText( orderList.get(position).getStartingpoint());
                holder.tv_StopDt.setText( orderList.get(position).getDestination());
            return convertView;
        }

        class ViewHolder {
            TextView tv_driverName;
            TextView tv_orderNo;
            TextView tv_startDt;
            TextView tv_StopDt;
            TextView tv_order_status;
        }
    }
}
