package com.fzzhkj.spgg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alivc.player.AliVcMediaPlayer;
import com.alivc.player.MediaPlayer;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

public class ALYVodplayerActivity extends AppCompatActivity {

    private Context mContext;
    private SharedPreferences sp;
    private String mcid;
    private SurfaceView mSurfaceView;
    private Handler mHandler;
    private String MyUrl="null";
    private AliVcMediaPlayer mPlayer;
    private boolean IsPlay=true;
    private Handler myhandler;
    private ImageView img_one;
    private ImageView img_two;
    private TextView tv_money_one;
    private TextView tv_price1_one;
    private TextView tv_money_two;
    private TextView tv_price1_two;
    private ImageView img_qrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alyvodplayer);
        mContext = getApplicationContext();
        sp = getSharedPreferences("binding", Context.MODE_PRIVATE);
        mcid = sp.getString("mcid", "");
        InitUI();
        mSurfaceView = findViewById(R.id.video_view);
        mHandler = new Handler();
        myhandler = new Handler();
        XXPermissions.with(this)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {

                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {

                    }
                });
        myhandler.postDelayed(myrunnable,3000);
        mHandler.postDelayed(PlayRunnable,1000);
    }

    private void InitUI() {
        img_one = findViewById(R.id.alyvodplay_img_one);
        img_two = findViewById(R.id.alyvodplay_img_two);
        img_qrcode = findViewById(R.id.alyvodplay_img_qrcode);
        tv_money_one = findViewById(R.id.alyvodplay_tv_money_one);
        tv_price1_one = findViewById(R.id.alyvodplay_tv_price1_one);
        tv_money_two = findViewById(R.id.alyvodplay_tv_money_two);
        tv_price1_two = findViewById(R.id.alyvodplay_tv_price1_two);
        tv_price1_one.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        tv_price1_two.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
    }

    Runnable PlayRunnable=new Runnable() {
        @Override
        public void run() {
            OkGo.<String>get("http://csh.0598qq.com/Api/led/LiveUrl")
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            String body = response.body();
                            GsonBuilder gsonBuilder = new GsonBuilder();
                            Gson gson = gsonBuilder.create();
                            LiveUrlBean liveUrlBean = gson.fromJson(body, LiveUrlBean.class);
                            if (liveUrlBean!=null){
                                if (liveUrlBean.getStatus()==1){
                                    InitJDUI(liveUrlBean.getUrl());
                                }
                            }

                        }
                    });
            OkGo.<String>get("http://csh.0598qq.com/Api/Led/GetDetailBymchid")
                    .params("mcid",mcid)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            String body = response.body();
                            GsonBuilder gsonBuilder = new GsonBuilder();
                            Gson gson = gsonBuilder.create();
                            DetailBymchidBean detailBymchidBean = gson.fromJson(body, DetailBymchidBean.class);
                            if (detailBymchidBean!=null){
                                if (detailBymchidBean.getStatus()==1){
                                    DetailBymchidBean.DataBean data = detailBymchidBean.getData();
                                    String right = data.getRight();
                                    Glide.with(ALYVodplayerActivity.this).load(right).into(img_qrcode);
                                    if (data!=null){
                                        List<DetailBymchidBean.DataBean.ThegoodsBean> thegoods = data.getThegoods();
                                        if (thegoods!=null){
                                            for (int a=0;a<thegoods.size();a++){
                                                if (a==0){
                                                    String topimg = thegoods.get(a).getTopimg();
                                                    String price = thegoods.get(a).getPrice();
                                                    String money = thegoods.get(a).getMoney();
                                                    String title = thegoods.get(a).getTitle();
                                                    tv_price1_one.setText("日常价 ¥ "+price);
                                                    tv_money_one.setText(""+money);
                                                    Glide.with(ALYVodplayerActivity.this).load(topimg).into(img_one);
                                                }else if (a==1){
                                                    String topimg = thegoods.get(a).getTopimg();
                                                    String price = thegoods.get(a).getPrice();
                                                    String money = thegoods.get(a).getMoney();
                                                    String title = thegoods.get(a).getTitle();
                                                    tv_price1_two.setText("日常价 ¥ "+price);
                                                    tv_money_two.setText(""+money);
                                                    Glide.with(ALYVodplayerActivity.this).load(topimg).into(img_two);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    });

            mHandler.postDelayed(PlayRunnable,30000);
        }
    };

    private void InitJDUI(String url) {
        if (url.equals(MyUrl)) {
        } else {
            MyUrl=url;
            mPlayer = new AliVcMediaPlayer(this, mSurfaceView);
//            mPlayer.setVideoScalingMode(MediaPlayer.VideoScalingMode.VIDEO_SCALING_MODE_SCALE_TO_FIT);
      mPlayer.setVideoScalingMode(MediaPlayer.VideoScalingMode.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
            mPlayer.setDefaultDecoder(0);
            mPlayer.setCirclePlay(true);
            //准备开始播放
            mPlayer.prepareAndPlay(url);
            mPlayer.setPreparedListener(new MediaPlayer.MediaPlayerPreparedListener() {
                @Override
                public void onPrepared() {
                    //准备完成时触发
                    Log.e("直播中",  "哈哈哈错误发生时触发1");
                    IsPlay = false;
                }
            });
            mPlayer.setPcmDataListener(new MediaPlayer.MediaPlayerPcmDataListener() {
                @Override
                public void onPcmData(byte[] bytes, int i) {
                    Log.e("直播中",  "哈哈哈错误发生时触发2");
                    //音频数据回调接口，在需要处理音频时使用，如拿到视频音频，然后绘制音柱。
                }
            });
            mPlayer.setFrameInfoListener(new MediaPlayer.MediaPlayerFrameInfoListener() {
                @Override
                public void onFrameInfoListener() {
                    //首帧显示时触发
//                IsPlay=false;
                    Log.e("直播中",  "哈哈哈错误发生时触发3");
                }
            });
            mPlayer.setErrorListener(new MediaPlayer.MediaPlayerErrorListener() {
                @Override
                public void onError(int i, String msg) {
                    //错误发生时触发，错误码见接口文档
                    Log.e("直播中", i + "哈哈哈错误发生时触发" + msg+ MyUrl);
                    if (i == 4003) {
//                        OkGo.<String>get("http://csh.0598qq.com/Api/led/Liveflag/status/0")
//                                .execute(new StringCallback() {
//                                    @Override
//                                    public void onSuccess(Response<String> response) {
//
//                                    }
//                                });
//                        startActivity(new Intent(mContext,MainActivity.class));
//                        finish();
                    }
                }
            });
            mPlayer.setCompletedListener(new MediaPlayer.MediaPlayerCompletedListener() {
                @Override
                public void onCompleted() {
                    //视频正常播放完成时触发
                    Log.e("直播中",  "哈哈哈错误发生时触发4");
                }
            });
            mPlayer.setSeekCompleteListener(new MediaPlayer.MediaPlayerSeekCompleteListener() {
                @Override
                public void onSeekCompleted() {
                    //视频seek完成时触发
                    Log.e("直播中",  "哈哈哈错误发生时触发5");
                }
            });
            mPlayer.setStoppedListener(new MediaPlayer.MediaPlayerStoppedListener() {
                @Override
                public void onStopped() {
                    //使用stop接口时触发
                    IsPlay = false;
                    Log.e("直播中",  "哈哈哈错误发生时触发6");
                }
            });
            mPlayer.setCircleStartListener(new MediaPlayer.MediaPlayerCircleStartListener() {
                @Override
                public void onCircleStart() {
                    Log.e("直播中",  "哈哈哈错误发生时触发7");
                    //循环播放开始
                }
            });
            //SEI数据回调
            mPlayer.setSEIDataListener(new MediaPlayer.MediaPlayerSEIDataListener() {
                @Override
                public void onSeiUserUnregisteredData(String data) {
                    //解析SEI数据，在这里可以展示题目信息或答案信息
                    Log.e("直播中",  "哈哈哈错误发生时触发8");
                }
            });
        }
    }

    //获取是否开启直播
    Runnable myrunnable=new Runnable() {
        @Override
        public void run() {
            OkGo.<String>get("http://csh.0598qq.com/Api/led/Livenow")
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            String body = response.body();
                            GsonBuilder builder = new GsonBuilder();
                            Gson gson = builder.create();
                            TypeResult typeResult = gson.fromJson(body, TypeResult.class);
                            if (typeResult.getStatus().equals("0")){
                                startActivity(new Intent(mContext,MainActivity.class));
                                finish();
                            }
                        }
                    });
            myhandler.postDelayed(myrunnable,3000);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myhandler.removeCallbacks(myrunnable);
        mHandler.removeCallbacks(PlayRunnable);

    }
}
