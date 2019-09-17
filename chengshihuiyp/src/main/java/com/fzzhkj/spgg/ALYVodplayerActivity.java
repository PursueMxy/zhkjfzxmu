package com.fzzhkj.spgg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import com.alivc.player.AliVcMediaPlayer;
import com.alivc.player.MediaPlayer;
import com.bumptech.glide.Glide;
import com.fzzhkj.spgg.Adapter.AlyVodAdapter;
import com.fzzhkj.spgg.Bean.DetailBymchidBean;
import com.fzzhkj.spgg.Bean.LiveUrlBean;
import com.fzzhkj.spgg.Bean.TypeResult;
import com.fzzhkj.spgg.Bean.subtitle;
import com.fzzhkj.spgg.Utils.MxyUtils;
import com.fzzhkj.spgg.Utils.RequstURIUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

public class ALYVodplayerActivity extends AppCompatActivity {

    private Context mContext;
    private SharedPreferences sp;
    private String mcid;
    private SurfaceView mSurfaceView;
    private String MyUrl="";
    private AliVcMediaPlayer mPlayer;
    private boolean IsPlay=true;
    private ImageView img_advertising_code;
    private int delayMillis = 2000;
    private List<subtitle.DataBean> data=new ArrayList<>();
    private RecyclerView mRecyclerView;
    private AlyVodAdapter alyVodAdapter;
    private ImageView img_goods;
    private Handler handler = new Handler();
    private Handler mHandler = new Handler();
    private Handler myhandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alyvodplayer);
        mContext = getApplicationContext();
        sp = getSharedPreferences("binding", Context.MODE_PRIVATE);
        mcid = sp.getString("mcid", "");
        InitUI();
        mSurfaceView = findViewById(R.id.video_view);
        myhandler.postDelayed(myrunnable,3000);
        mHandler.postDelayed(PlayRunnable,1000);
    }

    private void InitUI() {
        img_advertising_code = findViewById(R.id.alyvod_img_advertising_code);
        img_goods = findViewById(R.id.alyvod_img_goods);
        MyApplication.getInstance().showExternalAd(ALYVodplayerActivity.this);
        mRecyclerView = findViewById(R.id.alyvod_RecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setNestedScrollingEnabled(false);
        alyVodAdapter = new AlyVodAdapter(this, data);
        mRecyclerView.setAdapter(alyVodAdapter);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0
                        , 0
                        , 0
                        , MxyUtils.dpToPx(mContext, MxyUtils.getDimens(mContext, R.dimen.dp_5)));
            }
        });
        generateSomeDanmaku();
    }

    /**
     * 随机生成一些弹幕内容以供测试
     */
    private void generateSomeDanmaku() {
        long current_time = System.currentTimeMillis()/1000;
        OkGo.<String>get(RequstURIUtils.URI.Subtitle)
                .params("timeline",current_time)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        try {
                            subtitle subtitle = gson.fromJson(body, subtitle.class);
                            List<subtitle.DataBean> datas = subtitle.getData();
                            if (datas != null) {
                                if (data.size() >50) {
                                    for (int a = 1; a < 50; a++) {
                                        data.remove(a);
                                    }
                                }
                                data.addAll(datas);
                                alyVodAdapter = new AlyVodAdapter(ALYVodplayerActivity.this, data);
                                mRecyclerView.setAdapter(alyVodAdapter);
                                alyVodAdapter.notifyDataSetChanged();
                                mRecyclerView.scrollToPosition(alyVodAdapter.getItemCount() - 1);
                            }

                            handler.postDelayed(runnable, delayMillis);
                        }catch (Exception e){

                        }
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            myhandler.removeCallbacks(myrunnable);
            mHandler.removeCallbacks(PlayRunnable);
            handler.removeCallbacks(runnable);
            mPlayer.stop();
        }catch (Exception e){

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_ESCAPE) {
            MyApplication.getInstance().dismissExternalAd();
            try {
                myhandler.removeCallbacks(myrunnable);
                mHandler.removeCallbacks(PlayRunnable);
                handler.removeCallbacks(runnable);
            }catch (Exception e){

            }
            System.exit(0);
        }
        if(keyCode == KeyEvent.KEYCODE_BACK ) {
            try {
                myhandler.removeCallbacks(myrunnable);
                mHandler.removeCallbacks(PlayRunnable);
                handler.removeCallbacks(runnable);
            }catch (Exception e){

            }
            System.exit(0);
        }
        return super.onKeyDown(keyCode, event);
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
            InitGgDatas();
            mHandler.postDelayed(PlayRunnable,600000);
        }
    };

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            generateSomeDanmaku();
        }
    };

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

    private void InitGgDatas() {
        OkGo.<String>get(RequstURIUtils.URI.AdGoodsQrcode)
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
                                    String right = data.getRight();
                                    if (img_advertising_code!=null) {
                                        Glide.with(ALYVodplayerActivity.this).load(right).into(img_advertising_code);
                                    }
                                    List<DetailBymchidBean.DataBean.ThegoodsBean> thegoods = data.getThegoods();
                                    if (thegoods!=null){
                                        String topimg = thegoods.get(0).getTopimg();
                                        if (img_goods!=null) {
                                            Glide.with(ALYVodplayerActivity.this).load(topimg).into(img_goods);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
    }
}
