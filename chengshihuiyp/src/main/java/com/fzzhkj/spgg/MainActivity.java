package com.fzzhkj.spgg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alivc.player.AliVcMediaPlayer;
import com.bumptech.glide.Glide;
import com.danikula.videocache.HttpProxyCacheServer;
import com.fzzhkj.widget.AliyunVodPlayerView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.litepal.LitePal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * The type Main activity.
 *
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomVideoView videoPlay;
    private HttpProxyCacheServer proxy;
    private String proxyUrl;
    private PackageManager packageManager;
    private int versionCode;
    private String packageName;
    private Context mContext;
    private AlertDialog mAlertDialog;
    private ImageView img_advert;
    private int Number = 0;
    private List<ledadbean.DataBean> datas = new ArrayList<>();
    private int delayMillis = 0;
    private LinearLayout layout_main;
    private int apnType;
    private List<LiteDataBean> dataBean = new ArrayList<>();
    private boolean IsSelect=false;
    private SurfaceView mSurfaceView;
    private AliVcMediaPlayer mPlayer;
    private Handler mHandler;
    private RelativeLayout rl_binding;
    private SharedPreferences sp;
    private EditText edt_xxh;
    private String imei="";
    private String xxh;
    private String usn="";
    private ImageView img_left;
    private TextView tv_name1;
    private TextView tv_money1;
    private TextView tv_price1;
    private TextView tv_name2;
    private TextView tv_money2;
    private TextView tv_price2;
    private ImageView img_top1;
    private ImageView img_top2;
    private ImageView main_img_qrcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        apnType = NetWorkUtils.getAPNType(mContext);
        XXPermissions.with(this)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {

                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {

                    }
                });
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowtime = df.format(new Date());
        SharedPreferences share = getSharedPreferences("industryInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString("retime", nowtime);
        editor.commit();//提交
        sp = getSharedPreferences("binding", Context.MODE_PRIVATE);
        usn = sp.getString("usn", "");
        imei = sp.getString("mcid", "");
        if (imei.equals("")){
            imei =DervicesUtils.getMac(mContext);
        }
        InitUI();
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

    private void InitData() {
        try {
            //获取包管理器
            packageManager = getPackageManager();
            //获得包的信息
            PackageInfo info = packageManager.getPackageInfo(getPackageName(), 0);
            //获得版本名称
            versionCode = info.versionCode;
            packageName = info.packageName;
            apnType = NetWorkUtils.getAPNType(mContext);
            if (apnType > 0) {
                InitApkVersion();
            } else {
                ToastUtils.showToast(mContext, "请检查网络是否正常");
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        DataList();
    }

    private void DataList() {
        apnType = NetWorkUtils.getAPNType(mContext);
        if (apnType > 0) {
            handler.removeCallbacks(runnable);
            //获取播放列表
            OkGo.<String>get("http://csh.0598qq.com/Api/Led/lists")
                    .params("mcid",imei)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String>
                                                      response) {
                            datas.clear();
                            String body = response.body();
                            GsonBuilder builder = new GsonBuilder();
                            Gson gson = builder.create();
                            ledadbean ledadbean = gson.fromJson(body,ledadbean.class);
                            List<com.fzzhkj.spgg.ledadbean.DataBean> data = ledadbean.getData();
                            try {
                                if (data != null) {
                                    if (data.size() > 0) {
                                        datas.addAll(data);
                                        Number = 0;
                                        layout_main.setVisibility(View.VISIBLE);
                                        if (!data.get(0).getVideo().equals("0")) {
                                            proxyUrl = proxy.getProxyUrl(data.get(0).getVideo());
                                            videoPlay.setVideoPath(proxyUrl);
                                            videoPlay.requestFocus();//让VideiView获取焦点
                                            videoPlay.start();//开始播放
                                        } else {
                                            layout_main.setVisibility(View.GONE);
                                        }
                                        if (!data.get(0).getImg().equals("0")) {
                                            img_advert.setVisibility(View.VISIBLE);
//                                            Glide.with(mContext).load(data.get(0).getImg()).centerCrop().into(img_advert);
                                            Glide.with(mContext).load(data.get(0).getImg()).transform(new RotateTransformation(360)).into(img_advert);
                                        } else {
                                            img_advert.setVisibility(View.GONE);
                                        }
                                        delayMillis = Integer.parseInt(data.get(0).getSecond()) * 1000;
                                        handler.postDelayed(runnable,delayMillis);
                                        if (data.size() > 1) {
                                            Number = Number + 1;
                                        }
                                        LitePal.deleteAll(LiteDataBean.class);
                                        for (int a = 0; a < data.size(); a++) {
                                            LiteDataBean liteDataBean = new LiteDataBean();
                                            liteDataBean.setdbid(data.get(a).getId());
                                            liteDataBean.setTitle(data.get(a).getTitle());
                                            liteDataBean.setStart(data.get(a).getStart());
                                            liteDataBean.setEnd(data.get(a).getEnd());
                                            liteDataBean.setImg(data.get(a).getImg());
                                            liteDataBean.setVideo(data.get(a).getVideo());
                                            liteDataBean.setStatus(data.get(a).getStatus());
                                            liteDataBean.setOrd(data.get(a).getOrd());
                                            liteDataBean.setCate(data.get(a).getCate());
                                            liteDataBean.setSecond(data.get(a).getSecond());
                                            liteDataBean.save();
                                        }
                                    }
                                } else {
                                    layout_main.setVisibility(View.GONE);
                                    img_advert.setVisibility(View.VISIBLE);
                                }
                            } catch (Exception e) {
                                layout_main.setVisibility(View.GONE);
                                img_advert.setVisibility(View.VISIBLE);
                            }
                        }


                    });
        }else {
            ToastUtils.showToast(mContext,"没有网络");
            handler.postDelayed(runnable, delayMillis);
        }
    }

    private void InitApkVersion() {
        OkGo.<String>get("http://csh.0598qq.com/Api/Led/version")
                .execute(new StringCallback() {


                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        final version version = gson.fromJson(body,
                                version.class);
                        if (Integer.parseInt(version.getCode())
                                > versionCode) {
                            String cate = version.getCate();
                            AlertDialog.Builder diabuilder = new
                                    AlertDialog.Builder(MainActivity.this);
                            diabuilder.setTitle(version.getTitle
                                    () + "");
                            diabuilder.setMessage("请选择升级");
                            diabuilder.setPositiveButton("确定", new
                                    DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick
                                                (DialogInterface dialogInterface, int i) {
                                            new AppDownloadManager
                                                    (MainActivity.this).downloadApk("http://zmy.0598qq.com/Led/app-release.apk", "版本更新", "版本更新");
                                        }
                                    });
                            diabuilder.setNeutralButton("取消", new
                                    DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface
                                                                    dialogInterface, int i) {
                                            if (version.getCate().equals("1")) {
                                                System.exit(0);
                                            }
                                        }
                                    });
                            mAlertDialog = diabuilder.create();
                            mAlertDialog.show();
                            mAlertDialog.setCanceledOnTouchOutside
                                    (false);
                        }
                    }
                });
    }

    private void InitUI() {
        img_left = findViewById(R.id.main_img_left);
        tv_name1 = findViewById(R.id.main_tv_name1);
        tv_money1 = findViewById(R.id.main_tv_money1);
        tv_price1 = findViewById(R.id.main_tv_price1);
        tv_name2 = findViewById(R.id.main_tv_name2);
        tv_money2 = findViewById(R.id.main_tv_money2);
        tv_money1.getPaint().setAntiAlias(true);
        tv_money1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        tv_money2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        tv_money2.getPaint().setAntiAlias(true);
        tv_price2 = findViewById(R.id.main_tv_price2);
        img_top1 = findViewById(R.id.main_img_top1);
        img_top2 = findViewById(R.id.main_img_top2);
        main_img_qrcode = findViewById(R.id.main_img_qrcode);


        rl_binding = findViewById(R.id.main_rl_binding);
        edt_xxh = findViewById(R.id.main_edt_xxh);
        findViewById(R.id.main_tv_binding).setOnClickListener(this);
        layout_main = findViewById(R.id.layout_main);
        videoPlay = findViewById(R.id.videoPlayView);
        proxy = MyApplication.getProxy(getApplicationContext());
        img_advert = findViewById(R.id.main_img_advert);
        //设置视频控制器,组件可以控制视频的播放，暂停，快进，组件，不需要你实现
        videoPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                      @Override
                                                      public void onCompletion(MediaPlayer mp) {

                                                      }
                                                  });
        videoPlay.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int
                    extra) {
                videoPlay.stopPlayback(); //播放异常，则停止播放，防止弹窗使界面阻塞
                videoPlay.setVisibility(View.GONE);
                return true;
            }
        });
            videoPlay.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                    if(what==MediaPlayer.MEDIA_INFO_BUFFERING_START ){
    //                   加载中
                    }else{
                        //播放中
                    }
                    return true;

                }
            });

        if (!usn.equals("")){
            rl_binding.setVisibility(View.GONE);
            AdvertHander.postDelayed(advertRunable,100);
            InitDatas();
        }else {
            Log.e("usn",usn+"空码");
        }
    }

    public void InitGgDatas(){
        OkGo.<String>get("http://csh.0598qq.com/Api/led/AdGoodsQrcode")
                .params("mcid",imei)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new GsonBuilder().create();
                        AdvertBean advertBean = gson.fromJson(response.body(), AdvertBean.class);
                        if (advertBean!=null){
                            if (advertBean.getStatus()==1){
                                AdvertBean.DataBean data = advertBean.getData();
                                if (data!=null){
                                    String leftmoney = data.getLeftmoney();
                                    String right = data.getRight();
                                    Glide.with(MainActivity.this).load(leftmoney).into(img_left);
                                    Glide.with(MainActivity.this).load(right).into(main_img_qrcode);
                                    List<AdvertBean.DataBean.ThegoodsBean> thegoods = data.getThegoods();
                                    if (thegoods!=null){
                                        for (int a=0;a<thegoods.size();a++){
                                            if (a==0){
                                                String topimg = thegoods.get(a).getTopimg();
                                                String price = thegoods.get(a).getPrice();
                                                String money = thegoods.get(a).getMoney();
                                                String title = thegoods.get(a).getTitle();
                                                tv_name1.setText(title);
                                                tv_money1.setText("¥ "+price);
                                                tv_price1.setText("¥ "+money);
                                                Glide.with(MainActivity.this).load(topimg).into(img_top1);
                                            }else if (a==1){
                                                String topimg = thegoods.get(a).getTopimg();
                                                String price = thegoods.get(a).getPrice();
                                                String money = thegoods.get(a).getMoney();
                                                String title = thegoods.get(a).getTitle();
                                                tv_name2.setText(title);
                                                tv_money2.setText("¥ "+price);
                                                tv_price2.setText("¥ "+money);
                                                Glide.with(MainActivity.this).load(topimg).into(img_top2);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });

    }

   public void InitDatas(){
       mHandler = new Handler();
       mHandler.postDelayed(myrunnable,5);
       if (apnType > 0) {
           InitData();
       } else {
           dataBean = LitePal.findAll(LiteDataBean.class);
           if (dataBean.size() > 0) {
               for (int a = 0; a < dataBean.size(); a++) {
                   datas.add(new ledadbean.DataBean(dataBean.get(a).getdbid(), dataBean.get(a).getTitle(), dataBean.get(a).getStart(),
                           dataBean.get(a).getEnd(), dataBean.get(a).getImg(), dataBean.get(a).getVideo(), dataBean.get(a).getStatus(),
                           dataBean.get(a).getOrd(), dataBean.get(a).getCate(), dataBean.get(a).getSecond()));
               }
               if (!datas.get(0).getVideo().equals("0")) {
                   layout_main.setVisibility(View.VISIBLE);
                   proxyUrl = proxy.getProxyUrl(datas.get(0).getVideo());
                   videoPlay.setVideoPath(proxyUrl);
                   videoPlay.requestFocus();//让VideiView获取焦点
                   videoPlay.start();//开始播放
               } else {
                   layout_main.setVisibility(View.GONE);
               }
               if (!datas.get(0).getImg().equals("0")) {
                   img_advert.setVisibility(View.VISIBLE);
//                    Glide.with(mContext).load(datas.get(0).getImg()).centerCrop().into(img_advert);
                   Glide.with(mContext).load(datas.get(0).getImg()).transform(new RotateTransformation(360)).into(img_advert);
               } else {
                   img_advert.setVisibility(View.GONE);
               }
               delayMillis = Integer.parseInt(datas.get(0).getSecond()) * 1000;
               handler.postDelayed(runnable, delayMillis);
               if (datas.size() > 1) {
                   Number = Number + 1;
               }
           } else {
               layout_main.setVisibility(View.GONE);
               img_advert.setVisibility(View.VISIBLE);
               img_advert.setImageResource(R.drawable.zmy_bg);
           }
       }

   }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        mHandler.removeCallbacks(myrunnable);
        AdvertHander.removeCallbacks(advertRunable);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK ) {
            handler.removeCallbacks(runnable);
            mHandler.removeCallbacks(myrunnable);
            System.exit(0);
        }
        return super.onKeyDown(keyCode, event);
    }



    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!datas.get(Number).getVideo().equals("0")) {
                layout_main.setVisibility(View.VISIBLE);
                proxyUrl = proxy.getProxyUrl(datas.get(Number).getVideo());
                videoPlay.setVideoPath(proxyUrl);
                videoPlay.requestFocus();//让VideiView获取焦点
                videoPlay.start();
            } else {
                videoPlay.stopPlayback(); //播放异常，则停止播放,防止弹窗使界面阻塞
                layout_main.setVisibility(View.GONE);
            }
            if (!datas.get(Number).getImg().equals("0")) {
                img_advert.setVisibility(View.VISIBLE);
//                Glide.with(mContext).load(datas.get(Number).getImg()).centerCrop().into(img_advert);
                Glide.with(mContext).load(datas.get(Number).getImg()).transform(new RotateTransformation(360)).into(img_advert);
            } else {
                img_advert.setVisibility(View.GONE);
            }
            delayMillis = Integer.parseInt(datas.get(Number).getSecond()) * 1000;
            if (Number < datas.size() - 1) {
                Number = Number + 1;
            } else {
                Number = 0;
            }
            SharedPreferences share = getSharedPreferences
                    ("industryInfo", Activity.MODE_PRIVATE);
            String industryOne = share.getString("retime", "");
            SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str2 = dfs.format(new Date());
            int distanceTimemin = getDistanceTimemin(industryOne, str2);
            if (distanceTimemin > 2) {
                SharedPreferences sharedPreferences = getSharedPreferences("industryInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("retime", str2);
                editor.commit();//提交
                DataList();
                InitApkVersion();
            }else {
                handler.postDelayed(runnable, delayMillis);
            }
        }
    };

    public static int getDistanceTimemin(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;//天数差
        long hour = 0;//小时数差
        long min = 0;//分钟数差
        long second = 0;//秒数差
        try {
            final Calendar c = Calendar.getInstance();
            c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
            one = df.parse(str1);
            c.setTime(one);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            diff = time2 - time1;

            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            second = diff / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int) min;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_tv_binding:
                imei = DervicesUtils.getMac(mContext);
                xxh = edt_xxh.getText().toString();
                if (!xxh.equals("")) {
                    OkGo.<String>get("http://csh.0598qq.com/Api/Led/linkusn")
                            .params("usn", xxh)
                            .params("mcid",imei)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    String body = response.body();
                                    GsonBuilder gsonBuilder = new GsonBuilder();
                                    Gson gson = gsonBuilder.create();
                                    DevicesBean devicesBean = gson.fromJson(body, DevicesBean.class);
                                    if (devicesBean != null) {
                                        if (devicesBean.getStatus().equals("1")) {
                                            SharedPreferences.Editor editor = sp.edit();
                                            editor.putString("usn", xxh);
                                            editor.putString("mcid",imei);
                                            editor.commit();//提交
                                            rl_binding.setVisibility(View.GONE);
                                            AdvertHander.postDelayed(advertRunable,1000);
                                            InitDatas();
                                        } else {
                                            ToastUtils.showToast(mContext,devicesBean.getMsg()+"" );
                                        }
                                    }
                                }
                                });

                }else {
                    ToastUtils.showToast(mContext,"请输入序号号");
                }
                break;
                default:
                    break;
        }
    }


    //Android5.0以上
//        if (Build.VERSION.SDK_INT >=21) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }else {
//            Window window =getWindow();
//            WindowManager.LayoutParams attributes =
//window.getAttributes();
//            int flagTranslucentStatus =
//    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//            int flagTranslucentNavigation =
//    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
//            attributes.flags |= flagTranslucentStatus;
////                attributes.flags |= flagTranslucentNavigation;
//            window.setAttributes(attributes);
//        }
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
                            if (typeResult.getStatus().equals("1")){
                                startActivity(new Intent(mContext,ALYVodplayerActivity.class));
                                finish();
                            }
                        }
                    });
            mHandler.postDelayed(myrunnable,3000);
        }
    };

    //一分钟更新广告
    Handler AdvertHander=new Handler();
    Runnable advertRunable=new Runnable() {
        @Override
        public void run() {
         InitGgDatas();
         AdvertHander.postDelayed(advertRunable,600000);
        }
    };
}
