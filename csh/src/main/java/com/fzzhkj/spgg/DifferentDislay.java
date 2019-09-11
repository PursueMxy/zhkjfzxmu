package com.fzzhkj.spgg;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

public class DifferentDislay extends Presentation {

    private ImageView img_qrcode;
    private Context mContext;
    private List<ledadbean.DataBean> screen_data;
    private int ScreenNum=0;

    public DifferentDislay(Context outerContext, Display display) {
        super(outerContext, display);
        this.mContext=outerContext;
        AdvertHander.postDelayed(advertRunable,100);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_screen);
        img_qrcode = findViewById(R.id.second_screen_img_qrcode);
    }


    public ImageView getImg_qrcode(String qrcoUrl){
        if (img_qrcode!=null) {
            Glide.with(mContext).load(qrcoUrl).into(img_qrcode);
        }
        return img_qrcode;
    }

    public void getdata(){
        OkGo.<String>get(RequstURIUtils.URI.Screen)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new GsonBuilder().create();
                        ledadbean ledadbean = gson.fromJson(response.body(), ledadbean.class);
                        if (ledadbean!=null){
                            if (ledadbean.getStatus()==1){
                                screen_data = ledadbean.getData();
                                screenHander.postDelayed(screenRunable,10);
                            }
                        }
                    }
                });
    }

    public void OutHander(){
        try {
            AdvertHander.removeCallbacks(advertRunable);
            screenHander.removeCallbacks(screenRunable);
        }catch (Exception e){

        }
    }


    //一分钟更新广告
    Handler AdvertHander=new Handler();
    Runnable advertRunable=new Runnable() {
        @Override
        public void run() {
            getdata();
            AdvertHander.postDelayed(advertRunable,600000);
        }
    };

    //五秒更新一次广告
    Handler screenHander=new Handler();
    Runnable screenRunable=new Runnable() {
        @Override
        public void run() {
            getImg_qrcode(screen_data.get(ScreenNum).getImg());
            if (ScreenNum<screen_data.size()-1){
                ScreenNum++;
            }else {
                ScreenNum=0;
            }
            screenHander.postDelayed(screenRunable,5000);
        }
    };
}
