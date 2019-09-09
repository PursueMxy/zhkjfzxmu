package com.fzzhkj.spgg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.alivc.player.AliVcMediaPlayer;
import com.alivc.player.MediaPlayer;
import com.aliyun.vodplayer.media.AliyunVodPlayer;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;


public class ALiVodPlayerActivity extends AppCompatActivity implements View.OnClickListener {

    //创建播放器的实例
    private AliyunVodPlayer aliyunVodPlayer;
    private AliVcMediaPlayer mPlayer;
    private SurfaceView mSurfaceView;
    private AliVcMediaPlayer mediaPlayer;
    private TableLayout mHudView;
    private Context mContext;
    private Handler mHandler;
    private DanmakuView play_danmakuview;
    private DanmakuContext danmakuContext;
    private boolean showDanmaku;
    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };
    private Handler handler;
    private int delayMillis = 500;
    private Handler myhandler;
    private boolean IsPlay=true;
    private View inflate_cancel;
    private EditText barrage_edt;
    private AlertDialog showDialog;
    private List<GoodsBean.DataBean> goodsList=new ArrayList<>();
    private String MyUrl="null";
    private ImageView img_left;
    private ImageView img_qrcode;
    private ImageView img_top1;
    private ImageView img_top2;
    private TextView tv_name1;
    private TextView tv__money1;
    private TextView tv_price1;
    private TextView tv_name2;
    private TextView tv__money2;
    private TextView tv_price2;
    private SharedPreferences sp;
    private String mcid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_vod_player);
        mContext = getApplicationContext();
        sp = getSharedPreferences("binding", Context.MODE_PRIVATE);
        mcid = sp.getString("mcid", "");
        InitUI();
        findViewById(R.id.alivod_img_barrage).setOnClickListener(this);
        mSurfaceView = findViewById(R.id.video_view);
        img_left = findViewById(R.id.alivo_img_left);
        img_qrcode = findViewById(R.id.alivo_img__qrcode);
        tv_name1 = findViewById(R.id.alivo_tv_name1);
        img_top1 = findViewById(R.id.alivo_img_top1);
        img_top2 = findViewById(R.id.alivo_img_top2);
        tv__money1 = findViewById(R.id.alivo_tv_money1);
        tv__money1.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG); ;
        tv_price1 = findViewById(R.id.alivo_tv_price1);
        tv_name2 = findViewById(R.id.alivo_tv_name2);
        tv__money2 = findViewById(R.id.alivo_tv_money2);
        tv__money2 .getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG); ;
        tv_price2 = findViewById(R.id.alivo_tv_price2);
        handler = new Handler();
        mHandler = new Handler();
        myhandler = new Handler();
        mHandler.postDelayed(PlayRunnable,1000);
        this.handler.postDelayed(runnable,delayMillis);
        myhandler.postDelayed(myrunnable,3000);

    }


    private void InitUI() {
        play_danmakuview = findViewById(R.id.Play_danmakuview);
        play_danmakuview.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                showDanmaku = true;
                play_danmakuview.start();
                generateSomeDanmaku();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });
        danmakuContext = DanmakuContext.create();
        // 设置最大显示行数
        HashMap<Integer, Integer> maxLinesPair = new HashMap<Integer, Integer>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 5); // 滚动弹幕最大显示5行
        // 设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<Integer, Boolean>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);
        danmakuContext.setDuplicateMergingEnabled(false) //设置不合并相同内容弹幕
                .setScrollSpeedFactor(1.2f) //设置弹幕滚动速度缩放比例，越大速度越慢
                .setScaleTextSize(1.8f) //设置字体缩放比例
                .setMaximumLines(maxLinesPair) //设置最大行数策略
                .preventOverlapping(overlappingEnablePair); //设置禁止重叠策略
        play_danmakuview.prepare(parser, danmakuContext);


    }


    private void InitJDUI(String url) {
        if (url.equals(MyUrl)) {
        } else {
            MyUrl=url;
            mPlayer = new AliVcMediaPlayer(this, mSurfaceView);
            mPlayer.setVideoScalingMode(MediaPlayer.VideoScalingMode.VIDEO_SCALING_MODE_SCALE_TO_FIT);
//      mPlayer.setVideoScalingMode(MediaPlayer.VideoScalingMode.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
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
//                        OkGo.<String>get(RequstURIUtils.URI.Liveflag)
//                                .execute(new StringCallback() {
//                                    @Override
//                                    public void onSuccess(Response<String> response) {
//
//                                    }
//                                });
//                    startActivity(new Intent(mContext,MainActivity.class));
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


    @Override
    protected void onResume() {
        super.onResume();
        if (play_danmakuview != null && play_danmakuview.isPrepared() && play_danmakuview.isPaused()) {
            play_danmakuview.resume();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.alivod_img_barrage:
                inflate_cancel = LayoutInflater.from(mContext).inflate(R.layout.layout_barrage, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(inflate_cancel)
                       .setCancelable(true);
                inflate_cancel.findViewById(R.id.dialog_barrage_sendout).setOnClickListener(this);
                barrage_edt = inflate_cancel.findViewById(R.id.dialog_barrage_edt);
                showDialog = builder.show();
                break;
            case R.id.dialog_barrage_sendout:
                String barrage = barrage_edt.getText().toString();
                if (!barrage.equals("")){
                    addDanmaku(barrage, true,"#FFFF00" );
                    showDialog.dismiss();
                }else {
                    ToastUtils.showToast(mContext,"请输入弹幕");
                }
                break;
            default:
                break;
        }
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
                        List<com.fzzhkj.spgg.subtitle.DataBean> data = subtitle.getData();
                        if (data!=null) {
                            for (int a = 0; a < data.size(); a++) {
                                String title = data.get(a).getTitle();
                                String color = data.get(a).getColor();
                                addDanmaku(title, false,color );
                            }
                        }
                        handler.postDelayed(runnable,delayMillis);
                    }
                });
    }

    /**
     * 向弹幕View中添加一条弹幕
     * @param content
     *          弹幕的具体内容
     * @param  withBorder
     *          弹幕是否有边框
     */
    private void addDanmaku(String content, boolean withBorder, String colors) {
        if (play_danmakuview!=null) {
            BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
            danmaku.text = content;
            danmaku.padding = 35;
            danmaku.textSize = sp2px(
                    30);
            if (colors!=null) {
                danmaku.textColor = Color.parseColor(colors);
                ;
            }
            danmaku.setTime(play_danmakuview.getCurrentTime());
            if (withBorder) {
                danmaku.borderColor = Color.GREEN;
            }
            play_danmakuview.addDanmaku(danmaku);
        }else {
            play_danmakuview = findViewById(R.id.Play_danmakuview);
        }
    }

    /**
     * sp转px的方法。
     */
    public int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (play_danmakuview != null && play_danmakuview.isPrepared()) {
            play_danmakuview.pause();
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showDanmaku = false;
        if (play_danmakuview != null) {
            play_danmakuview.release();
            play_danmakuview = null;
        }
        handler.removeCallbacks(runnable);
        myhandler.removeCallbacks(myrunnable);
        mHandler.removeCallbacks(PlayRunnable);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK ) {
            handler.removeCallbacks(runnable);
            myhandler.removeCallbacks(myrunnable);
            mHandler.removeCallbacks(PlayRunnable);
            System.exit(0);
        }
        return super.onKeyDown(keyCode, event);
    }


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
                                startActivity(new Intent(mContext,MainActivity.class));
                                finish();
                            }
                        }
                    });
            mHandler.postDelayed(myrunnable,3000);
        }
    };

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
                                    if (data!=null){
                                        String leftmoney = data.getLeftmoney();
                                        String right = data.getRight();
                                        Glide.with(ALiVodPlayerActivity.this).load(leftmoney).into(img_left);
                                        Glide.with(ALiVodPlayerActivity.this).load(right).into(img_qrcode);
                                        List<DetailBymchidBean.DataBean.ThegoodsBean> thegoods = data.getThegoods();
                                        if (thegoods!=null){
                                            for (int a=0;a<thegoods.size();a++){
                                                if (a==0){
                                                    String topimg = thegoods.get(a).getTopimg();
                                                    String price = thegoods.get(a).getPrice();
                                                    String money = thegoods.get(a).getMoney();
                                                    String title = thegoods.get(a).getTitle();
                                                    tv_name1.setText(title);
                                                    tv__money1.setText("¥ "+price);
                                                    tv_price1.setText("¥ "+money);
                                                    Glide.with(ALiVodPlayerActivity.this).load(topimg).into(img_top1);
                                                }else if (a==1){
                                                    String topimg = thegoods.get(a).getTopimg();
                                                    String price = thegoods.get(a).getPrice();
                                                    String money = thegoods.get(a).getMoney();
                                                    String title = thegoods.get(a).getTitle();
                                                    tv_name2.setText(title);
                                                    tv__money2.setText("¥ "+price);
                                                    tv_price2.setText("¥ "+money);
                                                    Glide.with(ALiVodPlayerActivity.this).load(topimg).into(img_top2);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    });
            mHandler.postDelayed(PlayRunnable,30000);
//            if (IsPlay){
//            }else {
//                mHandler.postDelayed(PlayRunnable,60000);
//            }
        }
    };

}