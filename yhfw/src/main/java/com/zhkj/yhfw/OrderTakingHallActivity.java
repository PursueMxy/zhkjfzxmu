package com.zhkj.yhfw;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhkj.yhfw.Bean.LoginPublicBean;
import com.zhkj.yhfw.Bean.OrderHallBean;
import com.zhkj.yhfw.Bean.PublicResultBean;
import com.zhkj.yhfw.Utlis.AppRequestURL;
import com.zhkj.yhfw.Utlis.StringFormatUtil;

import java.util.ArrayList;
import java.util.List;


public class OrderTakingHallActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView list_driving_order;
    private int ORDER_TAKINGHALL_CODE = 1122;
    private String positionE;
    private String positionN;
    private int drive_type=0;
    private SharedPreferences sp;
    private String token;
    private Context mContext;
    private TextView tv_drive_one;
    private TextView tv_drive_zero;
    private MyAdapter myAdapter;
    private List<OrderHallBean.DataBean.ListBean> dataList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_taking_hall);
        sp = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        mContext = getApplicationContext();
        Intent intent = getIntent();
        positionE = intent.getStringExtra("positionE");
        positionN = intent.getStringExtra("positionN");
        InitUI();
        InitData();
    }

    public void InitData(){
        OkGo.<String>get(AppRequestURL.URL.HOST+"/api/order/hall")
                .params("type",1)
                .params("token",token)
                .params("drive_type",drive_type)
                .params("positionE",positionE)
                .params("positionN",positionN)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dataList.clear();
                        String body = response.body();
                        Log.e("订单信息", body);
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        PublicResultBean publicResultBean = gson.fromJson(body, PublicResultBean.class);
                        if (publicResultBean.getCode() == 200) {
                            OrderHallBean orderHallBean = gson.fromJson(body, OrderHallBean.class);
                            if (orderHallBean.getCode() == 200) {
                                OrderHallBean.DataBean data = orderHallBean.getData();
                                dataList = data.getList();
                            } else {
                                Toast.makeText(mContext, orderHallBean.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                            myAdapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(mContext,publicResultBean.getMsg(),Toast.LENGTH_SHORT).show();
                            myAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void InitUI() {
        tv_drive_one = findViewById(R.id.order_hall_tv_drive_one);
        tv_drive_zero = findViewById(R.id.order_hall_tv_drive_zero);
        tv_drive_zero.setOnClickListener(this);
        tv_drive_one.setOnClickListener(this);
        findViewById(R.id.order_hall_img_freshen).setOnClickListener(this);
        findViewById(R.id.order_hall_img_back).setOnClickListener(this);
        list_driving_order = findViewById(R.id.list_driving_order);
        myAdapter = new MyAdapter();
        list_driving_order.setAdapter(myAdapter);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.order_hall_tv_drive_zero:
                tv_drive_zero.setTextColor(Color.parseColor("#ffd700"));
                tv_drive_one.setTextColor(Color.parseColor("#000000"));
                drive_type=0;
                InitData();
                break;
            case R.id.order_hall_tv_drive_one:
                tv_drive_zero.setTextColor(Color.parseColor("#000000"));
                tv_drive_one.setTextColor(Color.parseColor("#ffd700"));
                tv_drive_one.setEnabled(true);
                drive_type=1;
                InitData();
                break;
            case R.id.order_hall_img_freshen:
                InitData();
                break;
            case R.id.order_hall_img_back:
                Intent intent = new Intent(this,HomeActivity.class);
                intent.putExtra("order_id","0");
                setResult(ORDER_TAKINGHALL_CODE,intent);
                finish();
                break;
                default:
                    break;
        }
    }

    public class MyAdapter  extends BaseAdapter{
        @Override
        public int getCount() {
            return dataList.size();
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
        public View getView(final int position, View view, ViewGroup parent) {
            ViewHolder holder = null;
            View convertView=null;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_order_taking, null);
                holder = new ViewHolder();
                holder.tv_drivingType = convertView.findViewById(R.id.list_order_taking_tv_drivingType);
                holder.tv_startaddress = convertView.findViewById(R.id.list_order_taking_tv_startaddress);
                holder.tv_stopaddress = convertView.findViewById(R.id.list_order_taking_tv_stopaddress);
                holder.tv_estimated_mileage = convertView.findViewById(R.id.list_order_taking_tv_estimated_mileage);
                holder.btn_grab_sheet = convertView.findViewById(R.id.list_order_taking_btn_grab_sheet);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (dataList.get(position).getType().equals("0")){
                holder.tv_drivingType.setText("酒后代驾");
            }else {
                holder.tv_drivingType.setText("包时代驾");
            }
            holder.tv_startaddress.setText(dataList.get(position).getStartingpoint());
            holder.tv_stopaddress.setText(dataList.get(position).getDestination());
            holder.tv_estimated_mileage.setText("预计里程："+dataList.get(position).getEstimated_mileage()+"公里");
            holder.btn_grab_sheet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(OrderTakingHallActivity.this,HomeActivity.class);
                    intent.putExtra("order_id",dataList.get(position).getId()+"");
                    setResult(ORDER_TAKINGHALL_CODE,intent);
                    finish();
                }
            });
            return convertView;
        }
        class ViewHolder {
            TextView tv_drivingType;
            TextView tv_startaddress;
            TextView tv_stopaddress;
            TextView tv_estimated_mileage;
            Button btn_grab_sheet;

        }
    }
}
