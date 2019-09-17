package com.fzzhkj.spgg.Utils;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.widget.ImageView;

import com.fzzhkj.spgg.CustView.GlideImageLoader;
import com.fzzhkj.spgg.R;
import com.fzzhkj.spgg.Bean.ledadbean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.yyydjk.library.BannerLayout;

import java.util.ArrayList;
import java.util.List;

public class DifferentDislay extends Presentation {

    private ImageView img_qrcode;
    private Context mContext;
    private List<ledadbean.DataBean> screen_data;
    private int ScreenNum=0;
    private int delayMillis=10;
    private BannerLayout bannerLayout;
    //轮播图
    final List<String> urls = new ArrayList<>();

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
        bannerLayout = (BannerLayout) findViewById(R.id.banner);
        bannerLayout.setAutoPlay(true);
        getdata();
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
                                for (int a=0;a<screen_data.size();a++){
                                urls.add(screen_data.get(a).getImg());
                                }
                                bannerLayout.setImageLoader(new GlideImageLoader());
                                bannerLayout.setViewUrls(urls);
                            }
                        }
                    }
                });
    }

   public void OutHander(){
        try {
            AdvertHander.removeCallbacks(advertRunable);
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

}
