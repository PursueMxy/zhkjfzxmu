package com.zhkj.yhfw;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhkj.yhfw.Bean.BaoTimesBean;
import com.zhkj.yhfw.Bean.OrderListBean;
import com.zhkj.yhfw.Utlis.StringFormatUtil;

import java.util.ArrayList;
import java.util.List;

public class BaoTimesActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView list_bao_times;
    private Context mContext;
    private String token;
    private List<BaoTimesBean.DataBean.MealBean> meal=new ArrayList<>();
    private MyAdapter myAdapter;
    private String address;
    private int BAO_TIMES_CODE = 9988;
    private int PACKET_TIMES_CODE = 9989;
    private String positionE;
    private String positionN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_times);
        Intent intent = getIntent();
        address = intent.getStringExtra("address");
        positionE = intent.getStringExtra("positionE");
        positionN = intent.getStringExtra("positionN");
        mContext = getApplicationContext();
        SharedPreferences sp = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        InitUI();
        InitData();
    }

    private void InitData() {
        OkGo.<String>get("http://fztestc.xmhavefun.com/api/order/meal")
                .params("type",1)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Log.e("列表",body);
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        BaoTimesBean baoTimesBean= gson.fromJson(body, BaoTimesBean.class);
                        if (baoTimesBean.getCode()==200){
                            BaoTimesBean.DataBean data = baoTimesBean.getData();
                            if (data!=null){
                                meal = data.getMeal();
                            }
                        }
                        myAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void InitUI() {
       findViewById(R.id.bao_times_back).setOnClickListener(this);
        list_bao_times = findViewById(R.id.list_bao_times);
        myAdapter = new MyAdapter();
        list_bao_times.setAdapter(myAdapter);
        list_bao_times.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(mContext,PacketTimeActivity.class);
                intent.putExtra("address",address);
                intent.putExtra("meal_id",meal.get(position).getId()+"");
                intent.putExtra("price",meal.get(position).getPrice());
                intent.putExtra("meal_name",meal.get(position).getDuration_text()+meal.get(position).getPrice()+"");
                intent.putExtra("positionE",positionE);
                intent.putExtra("positionN",positionN);
                startActivityForResult(intent,PACKET_TIMES_CODE);
                Log.e("service_type",positionE+""+positionN);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Intent intent1 = new Intent(this,HomeActivity.class);
            intent1.putExtra("codes","0");
            setResult(BAO_TIMES_CODE,intent1);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PACKET_TIMES_CODE){
            String codes = data.getStringExtra("codes");
            if (codes.equals("1")) {
                String service_type = data.getStringExtra("service_type");
                String meal_id = data.getStringExtra("meal_id");
                String position = data.getStringExtra("position");
                String positionE = data.getStringExtra("positionE");
                String positionN = data.getStringExtra("positionN");
                String estimatedeparturetime = data.getStringExtra("estimatedeparturetime");
                Intent intent1 = new Intent(this, HomeActivity.class);
                intent1.putExtra("service_type", service_type);
                intent1.putExtra("meal_id", meal_id + "");
                intent1.putExtra("codes","1");
                intent1.putExtra("position", position);
                intent1.putExtra("positionE", positionE);
                intent1.putExtra("positionN", positionN);
                intent1.putExtra("estimatedeparturetime", estimatedeparturetime);
                setResult(PACKET_TIMES_CODE, intent1);
                finish();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvs_times:
//                finish();
                break;
                default:
                    break;
        }
    }

    public class  MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return meal.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_baotimes, null);
                holder = new ViewHolder();
                holder.tv_set_meal = convertView.findViewById(R.id.tv_set_meal);
                holder.tvs_times = convertView.findViewById(R.id.tvs_times);
                holder.tv_max_mileage = convertView.findViewById(R.id.tv_max_mileage);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            StringFormatUtil spanStr = new StringFormatUtil(mContext, meal.get(position).getDuration_text()+meal.get(position).getPrice()+"元起", meal.get(position).getPrice()+"元", R.color.red).fillColor();
            holder.tv_set_meal.setText(spanStr.getResult());
            holder.tvs_times.setText(meal.get(position).getDuration()+"");
            holder.tv_max_mileage.setText("含"+meal.get(position).getMax_mileage()+"公里");
            return convertView;
        }
        class ViewHolder {
            TextView tv_set_meal;
            TextView tvs_times;
            TextView tv_max_mileage;
        }
    }
}
