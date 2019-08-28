package com.zhkj.yhfw.paasAct;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhkj.yhfw.Bean.CustomerBean;
import com.zhkj.yhfw.Bean.PublicResultBean;
import com.zhkj.yhfw.R;
import com.zhkj.yhfw.Utlis.AppRequestURL;
import com.zhkj.yhfw.Utlis.ToastUtils;

import java.util.ArrayList;
import java.util.List;


public class ContactActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView contact_list;
    private String token;
    private Context mContext;
    private List<CustomerBean.DataBean> data=new ArrayList<>();
    private MyAdapter myAdapter;
    private View dialog_delete_customer;
    private TextView dialog_customer_tv_name;
    private TextView dialog_customer_tv_mobile;
    private AlertDialog show;
    private int customer_id;
    private View dialog_customer_add;
    private TextView add_edt_mobile;
    private TextView add_edt_name;
    private AlertDialog add_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        SharedPreferences sp = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        mContext = getApplicationContext();
        InitUI();
        InitData();
    }

    private void InitData() {
        OkGo.<String>get(AppRequestURL.URL.emergencycontact)
                .params("type","1")
                .params("token",token)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("联系人",response.body());
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        CustomerBean customerBean = gson.fromJson(response.body(), CustomerBean.class);
                        if (customerBean!=null){
                            if (customerBean.getCode()==200){
                                data = customerBean.getData();
                            }
                           myAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void InitUI() {
        findViewById(R.id.Contact_img_back).setOnClickListener(this);
        findViewById(R.id.contact_btn_add).setOnClickListener(this);
        contact_list = findViewById(R.id.Contact_list);
        myAdapter = new MyAdapter();
        contact_list.setAdapter(myAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Contact_img_back:
                break;
            case R.id.contact_btn_add:
                dialog_customer_add = LayoutInflater.from(mContext).inflate(R.layout.dialog_customer_add, null);
                add_edt_mobile = dialog_customer_add.findViewById(R.id.dialog_customer_add_edt_mobile);
                add_edt_name = dialog_customer_add.findViewById(R.id.dialog_customer_add_edt_name);
                dialog_customer_add.findViewById(R.id.dialog_customer_add_tv_back).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        add_dialog.dismiss();
                    }
                });
                dialog_customer_add.findViewById(R.id.dialog_customer_add_tv_confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = add_edt_name.getText().toString();
                        String mobile = add_edt_mobile.getText().toString();
                        if (!name.equals("")){
                            if (!mobile.equals("")) {
                                add_dialog.dismiss();
                                OkGo.<String>get(AppRequestURL.URL.emergencycontact_add)
                                        .params("type","1")
                                        .params("token",token)
                                        .params("name",name)
                                        .params("mobile",mobile)
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onSuccess(Response<String> response) {
                                                GsonBuilder gsonBuilder = new GsonBuilder();
                                                Gson gson = gsonBuilder.create();
                                                PublicResultBean publicResultBean = gson.fromJson(response.body(), PublicResultBean.class);
                                                if (publicResultBean!=null){
                                                    if (publicResultBean.getCode()==201){
                                                        InitData();
                                                    }
                                                }
                                            }
                                        });
                            }else {
                                ToastUtils.showToast(mContext,"电话不能为空");
                            }
                        }else {
                            ToastUtils.showToast(mContext,"姓名不能为空");
                        }
                    }
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(ContactActivity.this)
                        .setView(dialog_customer_add);
                add_dialog = builder.show();
                break;
                default:
                    break;
        }
    }
    public class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return data.size();
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
        public View getView(final int i, View convertView, ViewGroup viewGroup) {
            View view;
            if (convertView == null) {
                view = getLayoutInflater().inflate(R.layout.list_contact_item, null);
            } else{
                view = convertView;
            }
            view.findViewById(R.id.list_contract_img_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    customer_id = data.get(i).getId();
                    dialog_delete_customer = LayoutInflater.from(mContext).inflate(R.layout.dialog_delete_customer, null);
                    dialog_delete_customer.findViewById(R.id.dialog_customer_tv_back).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            show.dismiss();
                        }
                    });
                    dialog_delete_customer.findViewById(R.id.dialog_customer_tv_confirm).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            show.dismiss();
                            OkGo.<String>get(AppRequestURL.URL.emergencycontact_delete)
                                    .params("type","1")
                                    .params("token",token)
                                    .params("id",customer_id)
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onSuccess(Response<String> response) {
                                            GsonBuilder gsonBuilder = new GsonBuilder();
                                            Gson gson = gsonBuilder.create();
                                            PublicResultBean publicResultBean = gson.fromJson(response.body(), PublicResultBean.class);
                                            if (publicResultBean!=null){
                                                if (publicResultBean.getCode()==200){
                                                    InitData();
                                                }
                                            }
                                        }
                                    });
                        }
                    });
                    dialog_customer_tv_name = dialog_delete_customer.findViewById(R.id.dialog_customer_tv_name);
                    dialog_customer_tv_mobile = dialog_delete_customer.findViewById(R.id.dialog_customer_tv_mobile);
                    dialog_customer_tv_mobile.setText("姓名："+data.get(i).getMobile());
                    dialog_customer_tv_name.setText("电话："+data.get(i).getName());
                    AlertDialog.Builder builder = new AlertDialog.Builder(ContactActivity.this)
                            .setView(dialog_delete_customer);
                    show = builder.show();

                }
            });
           TextView tv_customerName= view.findViewById(R.id.list_contract_tv_customerName);
           TextView tv_mobile= view.findViewById(R.id.list_contract_tv_mobile);
           tv_customerName.setText(data.get(i).getName());
           tv_mobile.setText(data.get(i).getMobile());
            return view;
        }
    }
}
