package com.fzzhkj.spgg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
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

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

public class ALYVodplayerActivity extends AppCompatActivity {

    private Context mContext;
    private SharedPreferences sp;
    private String mcid;
    private SurfaceView mSurfaceView;
    private Handler mHandler;
    private String MyUrl="";
    private AliVcMediaPlayer mPlayer;
    private boolean IsPlay=true;
    private Handler myhandler;
    private ImageView img_advertising_code;
    private DanmakuContext danmakuContext;
    private boolean showDanmaku;
    private Handler handler;
    private int delayMillis = 500;
    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };
    private BaseDanmaku danmaku;
    private AlyVodAdapter alyVodAdapter;
    private List<subtitle.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alyvodplayer);
        mContext = getApplicationContext();
        sp = getSharedPreferences("binding", Context.MODE_PRIVATE);
        mcid = sp.getString("mcid", "");
        InitUI();
        mSurfaceView = findViewById(R.id.video_view);
        handler = new Handler();
        mHandler = new Handler();
        myhandler = new Handler();
        myhandler.postDelayed(myrunnable,3000);
        mHandler.postDelayed(PlayRunnable,1000);
        AdvertHander.postDelayed(advertRunable,1000);
    }

    private void InitUI() {
        img_advertising_code = findViewById(R.id.alyvod_img_advertising_code);
        MyApplication.getInstance().showExternalAd(ALYVodplayerActivity.this);
    }

    /**
     * 随机生成一些弹幕内容以供测试
     */
    private void generateSomeDanmaku() {
        OkGo.<String>get(RequstURIUtils.URI.Subtitle)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        subtitle subtitle = gson.fromJson(body, subtitle.class);
                        data = subtitle.getData();
                        if (data !=null) {
                        }
                        handler.postDelayed(runnable,delayMillis);
                    }
                });
    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }


    Runnable PlayRunnable=new Runnable() {
        @Override
        public void run() {
            OkGo.<String>get(RequstURIUtils.URI.livrURL)
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
            OkGo.<String>get(RequstURIUtils.URI.GetDetailBymchid)
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
                                }
                            }
                        }
                    });
            InitGgDatas();
            mHandler.postDelayed(PlayRunnable,600000);
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
                        OkGo.<String>get(RequstURIUtils.URI.Liveflag)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {

                                    }
                                });
                        startActivity(new Intent(mContext,MainActivity.class));
                        finish();
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
            OkGo.<String>get(RequstURIUtils.URI.livenNow)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            String body = response.body();
                            GsonBuilder builder = new GsonBuilder();
                            Gson gson = builder.create();
                            TypeResult typeResult = gson.fromJson(body, TypeResult.class);
                            if (typeResult.getStatus().equals("0")){
                                MyApplication.getInstance().dismissExternalAd();
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
        AdvertHander.removeCallbacks(advertRunable);
        mPlayer.stop();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_ESCAPE) {
            MyApplication.getInstance().dismissExternalAd();
            try {
                myhandler.removeCallbacks(myrunnable);
                mHandler.removeCallbacks(PlayRunnable);
                AdvertHander.removeCallbacks(advertRunable);
            }catch (Exception e){

            }
            System.exit(0);
        }
        if(keyCode == KeyEvent.KEYCODE_BACK ) {
            try {
                myhandler.removeCallbacks(myrunnable);
                mHandler.removeCallbacks(PlayRunnable);
                AdvertHander.removeCallbacks(advertRunable);
            }catch (Exception e){

            }
            System.exit(0);
        }
        return super.onKeyDown(keyCode, event);
    }

    //一分钟更新广告
    Handler AdvertHander=new Handler();
    Runnable advertRunable=new Runnable() {
        @Override
        public void run() {
            InitGgDatas();
            AdvertHander.postDelayed(advertRunable,600000);
        }
    };

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            generateSomeDanmaku();
        }
    };

    private void InitGgDatas() {
        OkGo.<String>get(RequstURIUtils.URI.GetDetailBymchid)
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
                                if (data!=null){
                                    String leftmoney = data.getLeftmoney();
                                    String right = data.getRight();
                                    Glide.with(ALYVodplayerActivity.this).load(right).into(img_advertising_code);
                                }
                            }
                        }
                    }
                });
    }
}
