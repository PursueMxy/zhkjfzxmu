package com.zhkj.yhfw;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Poi;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.AmapPageType;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapCalcRouteResult;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapModelCross;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviRouteNotifyData;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AMapServiceAreaInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.DriveStep;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.amap.api.track.AMapTrackClient;
import com.amap.api.track.ErrorCode;
import com.amap.api.track.OnTrackLifecycleListener;
import com.amap.api.track.TrackParam;
import com.amap.api.track.query.entity.LocationMode;
import com.amap.api.track.query.entity.Point;
import com.amap.api.track.query.model.AddTerminalRequest;
import com.amap.api.track.query.model.AddTerminalResponse;
import com.amap.api.track.query.model.AddTrackRequest;
import com.amap.api.track.query.model.AddTrackResponse;
import com.amap.api.track.query.model.DistanceRequest;
import com.amap.api.track.query.model.DistanceResponse;
import com.amap.api.track.query.model.LatestPointRequest;
import com.amap.api.track.query.model.LatestPointResponse;
import com.amap.api.track.query.model.QueryTerminalRequest;
import com.amap.api.track.query.model.QueryTerminalResponse;
import com.autonavi.tbt.TrafficFacilityInfo;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhkj.yhfw.Bean.OrderBean;
import com.zhkj.yhfw.Bean.OrderDetailBean;
import com.zhkj.yhfw.Bean.OrderInfoBean;
import com.zhkj.yhfw.Bean.PointsBean;
import com.zhkj.yhfw.Bean.PublicDataBean;
import com.zhkj.yhfw.Bean.PublicResultBean;
import com.zhkj.yhfw.Bean.TraceInfoBean;
import com.zhkj.yhfw.Bean.loginbean;
import com.zhkj.yhfw.Bean.points;
import com.zhkj.yhfw.Utlis.AppRequestURL;
import com.zhkj.yhfw.Utlis.Constants;
import com.zhkj.yhfw.Utlis.SimpleOnTrackLifecycleListener;
import com.zhkj.yhfw.Utlis.SimpleOnTrackListener;
import com.zhkj.yhfw.Utlis.TimeUtils;
import com.zhkj.yhfw.customview.CircleImageView;
import com.zhkj.yhfw.customview.CustomDialog;
import com.zhkj.yhfw.service.BackService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.WebSocket;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_PERMISSION = 1;
    private static final String WEBSOCKET_HOST_AND_PORT = "ws://39.98.177.238:8283";
    private static int ROUTING_INT_CODE = 1001;
    private static AMapTrackClient aMapTrackClient;
    private TextureMapView mapView;
    private static boolean isServiceRunning;
    private static boolean uploadToTrack = true;
    private static long terminalId;
    private static final String TAG = "HomeActivity";
    private static boolean isGatherRunning;
    private static long trackId;
    private Map<String, String> permissionHintMap = new HashMap<>();
    private static final String CHANNEL_ID_SERVICE_RUNNING = "CHANNEL_ID_SERVICE_RUNNING";
    private static Context mContext;
    private static int delayMillis = 2000;
    private Button btn_start;
    private static long current_time;
    private long Start_time;
    private int REQUEST_IGNORE_BATTERY_CODE = 10001;

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private static TextView tv_start_address;

    private int INOUT_TIPS_CODE = 6699;
    private int BAO_TIMES_CODE = 9988;
    private int ORDER_LIST_CODE = 1314;
    private int ORDER_TAKINGHALL_CODE = 1122;
    private static TextView tv_destination;
    private String CityName;
    private static boolean IsStart = true;
    private static double start_Longi;
    private static double start_lati;
    private static double stop_Longi;
    private static double stop_lati;
    private TextView tv_designated_driver;
    private TextView tv_bao_times_driving;
    private int DRIVING_TYPE = 0;
    private static RelativeLayout home_rl_order_taking;
    private static LinearLayout home_ll_driver;
    private static LinearLayout ll_order_geton;
    private static LinearLayout ll_order_wait;
    private static LinearLayout ll_order_in;
    private TextView home_tv_geton;

    //到达起点时间
    private String strat_arrive_dt;
    private static TextView home_tv_wait_time;
    private static TextView home_tv_driving_distance;

    private static double route_number = 0;
    private static int Travel_type = 0;
    private static TextView home_tv_travel_time;
    private static TextView home_tv_wait_sum;

    //起始位置
    private String Start_address;
    //    终点位置
    private static String Stop_address;
    //    private AMapNavi mAMapNavi;
    protected List<NaviLatLng> mWayPointList = new ArrayList<NaviLatLng>();
    private String poiID;
    private static TextView geton_startaddress;
    private static TextView geton_stopaddress;
    private static TextView tv_wait_startaddress;
    private static TextView tv_wait_stopaddress;
    private static TextView tv_in_startaddress;
    private static TextView tv_in_stopaddress;
    private static RouteSearch routeSearch;
    private static AMap aMap;
    private Marker stopmarker;
    private SharedPreferences sp;
    private String order_dt;
    private TextView tv_wait_orderDt;
    private TextView tv_geton_orderDt;
    private TextView tv_in_orderDt;
    private double aboart_lati;
    private double aboart_longi;
    private String aboartAddress;
    private String customPoiID;
    private static String user_id;
    private static BackService spservice = null;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            spservice = ((BackService.LocalBinder) service).getService();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private static String token;
    private String mobile;
    private WebSocket mWebSocket;
    private static int orderId;
    private static Resources resources;
    private static Notification notification;
    private static LinearLayout ll_order_completion;
    private static TextView tv_totalMoney;
    private static TextView tv_meetMoney;
    private static TextView tv_coupon;
    private static int waiting_price;
    private static int free_waiting_time;
    private static int order_trip_id;
    private List<PointsBean.points> pointsList=new ArrayList<>();
    private static HomeActivity homeActivity;
    private TextView dialog_confirm_tv_title;
    private View inflate_confirm;
    //自定义dialog事件判断（0没有事件，1确认开始行驶，2确认到达目的地，3确认付款）
    private int DIALOG_CONFIRM_TYPE=0;
    private AlertDialog.Builder confirm_builder;
    private AlertDialog confirm_dialog;
    private View inflate_cancel;
    private AlertDialog.Builder cancel_builder;
    private AlertDialog cancel_show;
    private String Reaton="乘客不需要乘车";
    private static int createtime;
    private static String createddatetime;
    private int PASS_CODE=5200;
    private int distance=0;
    private static  double driving_distance=0;


    //录音功能
    public static final String AUDOI_DIR = Environment
            .getExternalStorageDirectory().getAbsolutePath() + "/oschina/audio"; // 录音音频保存根路径

    private boolean mIsRecording=true;// 是否正在录音
    private boolean mIsPlaying;// 是否正在播放
    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;
    private File soundFile;
    private String mAudioPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sp = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        user_id = sp.getString("user_id", "");
        mobile = sp.getString("mobile", "");
        mContext = getApplicationContext();
        homeActivity = HomeActivity.this;
        //获取计时费用
        InitPublicData();
        //申请权限
        requestPermissionsIfAboveM();
        resources = getResources();
        notification = createNotification();
        // 不要使用Activity作为Context传入
        aMapTrackClient = new AMapTrackClient(mContext);
        aMapTrackClient.setInterval(3, 10);
        aMapTrackClient.setLocationMode(LocationMode.HIGHT_ACCURACY);
        mapView = findViewById(R.id.activity_track_service_map);
        mapView.getMap().moveCamera(CameraUpdateFactory.zoomTo(15));
        mapView.onCreate(savedInstanceState);
        InitUI();
//        //获取AMapNavi实例
//        mAMapNavi = AMapNavi.getInstance(getApplicationContext());
//        //添加监听回调，用于处理算路成功
//        mAMapNavi.addAMapNaviListener(aMapNaviListener);
//        LitePal.deleteAll(LiteDataBean.class);


        Intent intent1 = new Intent(mContext, BackService.class);
        startService(intent1);
        initService();

    }

    private void InitPublicData() {
        OkGo.<String>post(AppRequestURL.URL.public_data)
                .params("type", "1")
                .params("token", token)
                .execute(new StringCallback() {


                    @Override
                    public void onSuccess(Response<String> response) {
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        PublicDataBean publicDataBean = gson.fromJson(response.body(), PublicDataBean.class);
                        if (publicDataBean != null) {
                            if (publicDataBean.getCode() == 200) {
                                if (publicDataBean.getData() != null) {
                                    PublicDataBean.DataBean data = publicDataBean.getData();
                                    free_waiting_time = Integer.parseInt(data.getFree_waiting_time());
                                    waiting_price = Integer.parseInt(data.getWaiting_price());
                                }
                            }
                        }
                    }
                });
    }

    private void initService() {
        Intent bluetoothIntent;
        if (spservice == null) {
            bluetoothIntent = new Intent(HomeActivity.this, BackService.class);
            bindService(bluetoothIntent, serviceConnection, BIND_AUTO_CREATE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        closeService();
    }

    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
        // 启动定位
        locationClient.startLocation();
    }

    private void InitUI() {
        //录音功能
        findViewById(R.id.home_img_common).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsRecording){
                    recordAudio();
                }else {
                    stopRecord();
                    Log.e("mAudioPath",mAudioPath);
                    OkGo.<String>post(AppRequestURL.URL.recording)
                            .params("type","1")
                            .params("token",token)
                            .params("path",mAudioPath)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    String body = response.body();
                                    Log.e("录音功能",body);
                                }
                            });
                    //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //上传的图片
            //2.通过RequestBody.create 创建requestBody对象,application/octet-stream 表示文件是任意二进制数据流
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), soundFile.getName());
            // 3.创建Request对象，设置URL地址，将RequestBody作为post方法的参数传入
            Request request = new Request.Builder().url("https://www.yihu16888.com/api/recording/add?type=1&token=e69d4cc2-986f-4e6b-a68f-b275c458b90e").post(requestBody).build();
            //4.创建一个call对象,参数就是Request请求对象
            Call call = okHttpClient.newCall(request);
            //5.请求加入调度,重写回调方法
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("上传日志55", e.getMessage().toString());
                }

                @Override
                public void onResponse(Call call, okhttp3.Response response) throws IOException {
                    final String datas = parseResponse(response.body().byteStream());
                    Log.e("上传日志66", datas);
                }

            });
//                    mPlayer = new MediaPlayer();
//                    try {
//                        mPlayer.setDataSource(mAudioPath);
//                        mPlayer.prepare();
//                        mPlayer.start();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        });
        findViewById(R.id.home_img_head).setOnClickListener(this);

        //自定义确认弹出窗
        confirmDialog();
        //下单时间
        tv_geton_orderDt = findViewById(R.id.home_tv_geton_orderDt);
        tv_wait_orderDt = findViewById(R.id.home_tv_wait_orderDt);
        tv_in_orderDt = findViewById(R.id.home_tv_in_orderDt);
        //开启导航
        findViewById(R.id.hone_img_navigation).setOnClickListener(this);
        //拨打电话
        findViewById(R.id.home_tv_callPhone).setOnClickListener(this);
        findViewById(R.id.home_tv_wait_callPhone).setOnClickListener(this);
        //取消订单
        findViewById(R.id.home_tv_cancelOrder).setOnClickListener(this);
        findViewById(R.id.home_tv_wait_cancelOrder).setOnClickListener(this);
        //下一步
        findViewById(R.id.home_btn_next).setOnClickListener(this);
        //到达上车点
        ll_order_geton = findViewById(R.id.home_order_geton);
        home_tv_geton = findViewById(R.id.home_tv_geton);
        home_tv_geton.setOnClickListener(this);
        home_tv_wait_time = findViewById(R.id.home_tv_wait_time);
        geton_startaddress = findViewById(R.id.home_order_geton_startaddress);
        geton_stopaddress = findViewById(R.id.home_order_geton_stopaddress);
        //开始行驶
        ll_order_wait = findViewById(R.id.home_order_wait);
        findViewById(R.id.home_tv_start_driving).setOnClickListener(this);
        home_tv_wait_sum = findViewById(R.id.home_tv_wait_sum);
        tv_wait_startaddress = findViewById(R.id.home_tv_wait_startaddress);
        tv_wait_stopaddress = findViewById(R.id.home_tv_wait_stopaddress);
        findViewById(R.id.home_img_wait_navigation).setOnClickListener(this);


        //到达终点
        ll_order_in = findViewById(R.id.home_order_in);
        findViewById(R.id.home_tv_come_on).setOnClickListener(this);
        home_tv_driving_distance = findViewById(R.id.home_tv_driving_distance);
        home_tv_travel_time = findViewById(R.id.home_tv_travel_time);
        tv_in_startaddress = findViewById(R.id.home_tv_in_startaddress);
        tv_in_stopaddress = findViewById(R.id.home_tv_in_stopaddress);

        //订单完成（付款）
        ll_order_completion = findViewById(R.id.home_ll_order_completion);
        tv_totalMoney = findViewById(R.id.home_tv_totalMoney);
        tv_meetMoney = findViewById(R.id.home_tv_meetMoney);
        tv_coupon = findViewById(R.id.home_tv_coupon);
        findViewById(R.id.home_btn_orderOpen).setOnClickListener(this);

        home_rl_order_taking = findViewById(R.id.home_rl_order_taking);
        home_ll_driver = findViewById(R.id.home_ll_driver);
        tv_designated_driver = findViewById(R.id.designated_driver);
        tv_designated_driver.setOnClickListener(this);
        tv_bao_times_driving = findViewById(R.id.bao_times_driving);
        tv_bao_times_driving.setOnClickListener(this);
        tv_destination = findViewById(R.id.tv_destination);
        tv_destination.setOnClickListener(this);
        tv_start_address = findViewById(R.id.tv_start_address);
        tv_start_address.setOnClickListener(this);
        findViewById(R.id.btn_sala).setOnClickListener(this);
        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);
        tv_start_address.setMovementMethod(ScrollingMovementMethod.getInstance());
        current_time = System.currentTimeMillis();
        Resources res = getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.driver_icon);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bmp);
        aMap = mapView.getMap();
        // 启用地图内置定位
        mapView.getMap().setMyLocationEnabled(true);
        mapView.getMap().setMyLocationStyle(
                new MyLocationStyle()
                        .interval(5000)
                        .myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE)
                        .myLocationIcon(bitmapDescriptor)
                        .strokeColor(Color.argb(0, 0, 0, 0))//设置定位蓝点精度圆圈的边框颜色的方法。
                        .radiusFillColor(Color.argb(0, 0, 0, 0))//设置定位蓝点精度圆圈的填充颜色的方法。
                        .strokeWidth(0)
        );
        //路线规划按钮
        routeSearch = new RouteSearch(this);
        routeSearch.setRouteSearchListener(routeSearchListener);
        //用户头像
        CircleImageView geton_img_head = findViewById(R.id.home_geton_img_head);
        CircleImageView wait_img_head = findViewById(R.id.home_wait_img_head);
        CircleImageView in_img_head = findViewById(R.id.home_in_img_head);
        String avatar = sp.getString("avatar", "");
        if (avatar != null) {
            Glide.with(this).load(AppRequestURL.URL.HOST + avatar).into(geton_img_head);
            Glide.with(this).load(AppRequestURL.URL.HOST + avatar).into(wait_img_head);
            Glide.with(this).load(AppRequestURL.URL.HOST + avatar).into(in_img_head);
        }
        //初始化定位
        initLocation();
    }

    private void confirmDialog() {
        inflate_confirm = LayoutInflater.from(mContext).inflate(R.layout.dialog_confirm, null);
        inflate_confirm.findViewById(R.id.dialog_confirm_tv_back).setOnClickListener(this);
        inflate_confirm.findViewById(R.id.dialog_confirm_tv_confirm).setOnClickListener(this);
        dialog_confirm_tv_title = inflate_confirm.findViewById(R.id.dialog_confirm_tv_title);
        confirm_builder = new AlertDialog.Builder(this)
                .setView(inflate_confirm);
    }


    private static OnTrackLifecycleListener onTrackListener = new SimpleOnTrackLifecycleListener() {
        @Override
        public void onBindServiceCallback(int status, String msg) {
            Log.w(TAG, "onBindServiceCallback, status: " + status + ", msg: " + msg);
        }

        @Override
        public void onStartTrackCallback(int status, String msg) {
            if (status == ErrorCode.TrackListen.START_TRACK_SUCEE || status == ErrorCode.TrackListen.START_TRACK_SUCEE_NO_NETWORK) {
                // 成功启动
                Toast.makeText(mContext, "启动服务成功", Toast.LENGTH_SHORT).show();
                isServiceRunning = true;
                if (isGatherRunning) {
                    aMapTrackClient.stopGather(onTrackListener);
                } else {
                    aMapTrackClient.setTrackId(trackId);
                    aMapTrackClient.startGather(onTrackListener);
                }
            } else if (status == ErrorCode.TrackListen.START_TRACK_ALREADY_STARTED) {
                // 已经启动
                Toast.makeText(mContext, "服务已经启动", Toast.LENGTH_SHORT).show();
                isServiceRunning = true;
                if (isGatherRunning) {
                    aMapTrackClient.stopGather(onTrackListener);
                } else {
                    aMapTrackClient.setTrackId(trackId);
                    aMapTrackClient.startGather(onTrackListener);
                }
            } else {
                Log.w(TAG, "error onStartTrackCallback, status: " + status + ", msg: " + msg);
                Toast.makeText(mContext,
                        "error onStartTrackCallback, status: " + status + ", msg: " + msg,
                        Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onStopTrackCallback(int status, String msg) {
            if (status == ErrorCode.TrackListen.STOP_TRACK_SUCCE) {
                // 成功停止
                Toast.makeText(mContext, "停止服务成功", Toast.LENGTH_SHORT).show();
                isServiceRunning = false;
                isGatherRunning = false;
            } else {
                Log.w(TAG, "error onStopTrackCallback, status: " + status + ", msg: " + msg);
                Toast.makeText(mContext,
                        "error onStopTrackCallback, status: " + status + ", msg: " + msg,
                        Toast.LENGTH_LONG).show();

            }
        }

        @Override
        public void onStartGatherCallback(int status, String msg) {
            if (status == ErrorCode.TrackListen.START_GATHER_SUCEE) {
                Toast.makeText(mContext, "定位采集开启成功", Toast.LENGTH_SHORT).show();
                isGatherRunning = true;
            } else if (status == ErrorCode.TrackListen.START_GATHER_ALREADY_STARTED) {
                Toast.makeText(mContext, "定位采集已经开启", Toast.LENGTH_SHORT).show();
                isGatherRunning = true;
            } else {
                Log.w(TAG, "error onStartGatherCallback, status: " + status + ", msg: " + msg);
                Toast.makeText(mContext,
                        "error onStartGatherCallback, status: " + status + ", msg: " + msg,
                        Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onStopGatherCallback(int status, String msg) {
            if (status == ErrorCode.TrackListen.STOP_GATHER_SUCCE) {
                Toast.makeText(mContext, "定位采集停止成功", Toast.LENGTH_SHORT).show();
                isGatherRunning = false;
            } else {
                Log.w(TAG, "error onStopGatherCallback, status: " + status + ", msg: " + msg);
                Toast.makeText(mContext,
                        "error onStopGatherCallback, status: " + status + ", msg: " + msg,
                        Toast.LENGTH_LONG).show();
            }
        }
    };


    private static void startTrack() {
        // 先根据Terminal名称查询Terminal ID，如果Terminal还不存在，就尝试创建，拿到Terminal ID后，
        // 用Terminal ID开启轨迹服务
        aMapTrackClient.queryTerminal(new QueryTerminalRequest(Constants.SERVICE_ID, user_id), new SimpleOnTrackListener() {
            @Override
            public void onQueryTerminalCallback(QueryTerminalResponse queryTerminalResponse) {
                if (queryTerminalResponse.isSuccess()) {
                    if (queryTerminalResponse.isTerminalExist()) {
                        // 当前终端已经创建过，直接使用查询到的terminal id
                        terminalId = queryTerminalResponse.getTid();
                        if (uploadToTrack) {
                            aMapTrackClient.addTrack(new AddTrackRequest(Constants.SERVICE_ID, terminalId), new SimpleOnTrackListener() {
                                @Override
                                public void onAddTrackCallback(AddTrackResponse addTrackResponse) {
                                    if (addTrackResponse.isSuccess()) {
                                        // trackId需要在启动服务后设置才能生效，因此这里不设置，而是在startGather之前设置了track id
                                        trackId = addTrackResponse.getTrid();
                                        TrackParam trackParam = new TrackParam(Constants.SERVICE_ID, terminalId);
                                        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                            trackParam.setNotification(notification);
                                        }
                                        aMapTrackClient.startTrack(trackParam, onTrackListener);
                                        Log.e("执行轨迹点", "正常轨迹");
                                    } else {
                                        Toast.makeText(mContext, "网络请求失败，" + addTrackResponse.getErrorMsg(), Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        } else {
                            // 不指定track id，上报的轨迹点是该终端的散点轨迹
                            TrackParam trackParam = new TrackParam(Constants.SERVICE_ID, terminalId);
                            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                trackParam.setNotification(notification);
                            }
                            aMapTrackClient.startTrack(trackParam, onTrackListener);
                            Log.e("执行轨迹点", "散点轨迹");
                        }
                    } else {
                        // 当前终端是新终端，还未创建过，创建该终端并使用新生成的terminal id
                        aMapTrackClient.addTerminal(new AddTerminalRequest(user_id, Constants.SERVICE_ID), new SimpleOnTrackListener() {
                            @Override
                            public void onCreateTerminalCallback(AddTerminalResponse addTerminalResponse) {
                                if (addTerminalResponse.isSuccess()) {
                                    terminalId = addTerminalResponse.getTid();
                                    TrackParam trackParam = new TrackParam(Constants.SERVICE_ID, terminalId);
                                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                        trackParam.setNotification(notification);
                                    }
                                    aMapTrackClient.startTrack(trackParam, onTrackListener);
                                    Log.e("执行轨迹点", "散点轨迹88888");
                                } else {
                                    Toast.makeText(mContext, "网络请求失败2，" + addTerminalResponse.getErrorMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                } else {
                    Toast.makeText(mContext, "网络请求失败3，" + queryTerminalResponse.getErrorMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {


        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {
                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {
                    //解析定位结果，
                    CityName = location.getCity();
                    start_lati = location.getLatitude();
                    start_Longi = location.getLongitude();
                    if (IsStart) {
                        tv_start_address.setText(location.getCity() + location.getDistrict() + location.getStreet() + location.getStreetNum());
                        Start_address = location.getCity() + location.getDistrict() + location.getStreet() + location.getStreetNum();
                        aboartAddress = Start_address;
                    }
                } else {
                }
            } else {
                tv_start_address.setText("定位失败");
            }
        }
    };


    private void closeService() {
        if (spservice != null) {
            try {
                unbindService(serviceConnection);
                // mBluetoothLEService.Disconnect();
                spservice = null;
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_img_head:
                Intent passintent = new Intent(mContext, PaaSActivity.class);
                startActivityForResult(passintent,PASS_CODE);
                break;
            case R.id.btn_start:
                //路边接单
//                current_time = System.currentTimeMillis();
                home_rl_order_taking.setVisibility(View.GONE);
                ll_order_geton.setVisibility(View.GONE);
                ll_order_in.setVisibility(View.GONE);
                ll_order_wait.setVisibility(View.GONE);
                home_ll_driver.setVisibility(View.VISIBLE);
                locationClient.stopLocation();
                break;
            case R.id.tv_destination:
                Intent intent = new Intent(mContext, SearchPoiActivity.class);
                intent.putExtra("city", CityName);
                intent.putExtra("type", "1");
                startActivityForResult(intent, INOUT_TIPS_CODE);
                break;
            case R.id.tv_start_address:
                Intent intent1 = new Intent(mContext, SearchPoiActivity.class);
                intent1.putExtra("city", CityName);
                intent1.putExtra("type", "0");
                startActivityForResult(intent1, INOUT_TIPS_CODE);
                break;
            case R.id.bao_times_driving:
                DRIVING_TYPE = 2;
                Intent intent2 = new Intent(mContext, BaoTimesActivity.class);
                intent2.putExtra("address",Start_address);
                intent2.putExtra("positionE",start_lati+"");
                intent2.putExtra("positionN",start_Longi+"");
                startActivityForResult(intent2, BAO_TIMES_CODE);
                Log.e("service_type",start_lati+""+start_Longi);
                break;
            case R.id.designated_driver:
                DRIVING_TYPE = 1;
                tv_designated_driver.setBackgroundResource(R.drawable.btn_select);
                tv_designated_driver.setTextColor(this.getResources().getColor(R.color.color_ffd700));
                break;
            case R.id.btn_sala:
//                home_rl_order_taking.setVisibility(View.GONE);
//                home_ll_driver.setVisibility(View.GONE);
//                ll_order_geton.setVisibility(View.GONE);
//                ll_order_in.setVisibility(View.GONE);
//                ll_order_wait.setVisibility(View.GONE);
//                new CustomDialog(this)
//                        .setDialogCornersRadius(20f)
//                        .create().show();
                Intent intent3 = new Intent(mContext,OrderTakingHallActivity.class);
                intent3.putExtra("positionE",start_lati);
                intent3.putExtra("positionN",start_Longi);
                startActivityForResult(intent3, ORDER_TAKINGHALL_CODE);
                break;
            case R.id.home_btn_next:
                //下一步按钮
                if (IsStart) {
                    aboart_lati = start_lati;
                    aboart_longi = start_Longi;
                    aboartAddress = Start_address;
                }
                if (DRIVING_TYPE == 1) {
                    if (!Stop_address.equals("")) {
                        spservice.PlaceOrder(mobile, aboart_lati + "", aboart_longi + "", aboartAddress, stop_lati + "", stop_Longi + "", Stop_address);
//                          spservice.orderInfo();
                    } else {
                        Toast.makeText(mContext, "目的地不能为空", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mContext, "请选择代驾类型", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.home_tv_geton:
                //到达上车点 开始等待
                Travel_type = 1;
                spservice.driverArrive(orderId);
                break;
            case R.id.home_tv_start_driving:
                //开始行驶,计算路程   开启服务
                confirmDialog();
                dialog_confirm_tv_title.setText("确认开始行驶");
                DIALOG_CONFIRM_TYPE=1;
                confirm_dialog = confirm_builder.show();
                break;
            case R.id.home_tv_come_on:
                //到达终点  销毁线程
                confirmDialog();
                dialog_confirm_tv_title.setText("确认到达终点");
                DIALOG_CONFIRM_TYPE=2;
                confirm_dialog = confirm_builder.show();
                break;
            case R.id.hone_img_navigation:
                InputtipsQuery inputquery = new InputtipsQuery(aboartAddress, CityName);
                Inputtips inputTips = new Inputtips(getApplicationContext(), inputquery);
                inputTips.setInputtipsListener(new Inputtips.InputtipsListener() {
                    @Override
                    public void onGetInputtips(List<Tip> list, int i) {
                        if (list.size() > 0) {
                            customPoiID = list.get(0).getPoiID();
                        }
                    }
                });
                inputTips.requestInputtipsAsyn();
                /**导航到客户位置**/
                Poi start = new Poi(Start_address, new LatLng(start_lati, start_Longi), "");
                Poi end = new Poi(aboartAddress, new LatLng(aboart_lati, aboart_longi), customPoiID);
                AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), new AmapNaviParams(start, null, end, AmapNaviType.WALK, AmapPageType.NAVI), naviInfoCallback, CustomAmapRouteActivity.class);
                break;
            case R.id.home_img_wait_navigation:
                Poi start1 = new Poi(aboartAddress, new LatLng(aboart_lati, aboart_longi), "");
                /**导航到目的地**/
                Poi end1 = new Poi(Stop_address, new LatLng(stop_lati, stop_Longi), poiID);
                AmapNaviParams amapNaviParams1 = new AmapNaviParams(start1, null, end1, AmapNaviType.WALK, AmapPageType.NAVI);
                amapNaviParams1.setUseInnerVoice(true);
                AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), amapNaviParams1, naviInfoCallback, CustomAmapRouteActivity.class);
                break;
            case R.id.home_tv_callPhone:
                Intent intentcall = new Intent();
                //设置拨打电话的动作
                intentcall.setAction(Intent.ACTION_CALL);
                //设置拨打电话的号码
                intentcall.setData(Uri.parse("tel:" + "13788888888"));
                //开启打电话的意图
                startActivity(intentcall);
                break;
            case R.id.home_tv_cancelOrder:
                //取消订单
                UI_cancel_dialog();
                cancel_show = cancel_builder.show();
                break;
            case R.id.home_tv_wait_callPhone:
                Intent intentcal2 = new Intent();
                //设置拨打电话的动作
                intentcal2.setAction(Intent.ACTION_CALL);
                //设置拨打电话的号码
                intentcal2.setData(Uri.parse("tel:" + "13788888888"));
                //开启打电话的意图
                startActivity(intentcal2);
                break;
            case R.id.home_tv_wait_cancelOrder:
                //取消订单
                UI_cancel_dialog();
                cancel_show = cancel_builder.show();
                break;
            case R.id.home_btn_orderOpen:
                //订单完成.确认付款
                confirmDialog();
                dialog_confirm_tv_title.setText("请与客户确认收款");
                DIALOG_CONFIRM_TYPE=3;
                confirm_dialog = confirm_builder.show();
                break;
            case R.id.dialog_confirm_tv_back:
                //自定义dialog取消按钮
                DIALOG_CONFIRM_TYPE=0;
                confirm_dialog.dismiss();
                break;
            case R.id.dialog_confirm_tv_confirm:
                //自定义dialog确定按钮
                if (DIALOG_CONFIRM_TYPE==1){
                    Travel_type = 2;
                    spservice.passengerBoarding(orderId);

                }else if (DIALOG_CONFIRM_TYPE==2) {
                    OkGo.<String>get("https://www.yihu16888.com/api/order/get_trace_info")
                            .params("order_trip_id",order_trip_id)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    String body = response.body();
                                    GsonBuilder builder = new GsonBuilder();
                                    Gson gson = builder.create();
                                    PublicResultBean publicResultBean = gson.fromJson(body, PublicResultBean.class);
                                    if (publicResultBean != null) {
                                        if (publicResultBean.getCode()==200) {
                                            TraceInfoBean traceInfoBean = gson.fromJson(body, TraceInfoBean.class);
                                            if (traceInfoBean.getCode() == 200) {
                                                TraceInfoBean.DataBean data = traceInfoBean.getData();
                                                if (data != null) {
                                                    List<TraceInfoBean.DataBean.TracksBean> tracks = data.getTracks();
                                                    if (tracks != null) {
//                                                        distance = tracks.get(0).getDistance();
                                                    }
                                                }
                                            }
                                        }else {
                                            Toast.makeText(mContext,""+publicResultBean.getMsg(),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });
                    spservice.Arrive(""+driving_distance, orderId, aboartAddress, aboart_lati + "", aboart_longi + "");
                    handler.removeCallbacks(runnable);
                    handler_order.removeCallbacks(runnable_order);
                    locationClient.startLocation();
                }else if (DIALOG_CONFIRM_TYPE==3){
                    spservice.OrderMoney(orderId);
                }
                DIALOG_CONFIRM_TYPE=0;
                confirm_dialog.dismiss();
                 break;
            case R.id.dialog_cancel_tv_back:
                cancel_show.dismiss();
                break;
            case R.id.dialog_cancel_tv_confirm:
                //确认取消订单
                spservice.refuse_order(orderId,Reaton);
                cancel_show.dismiss();
                break;
            default:
                break;
        }

    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
// 如果是返回键,直接返回到桌面
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            closeService();
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IGNORE_BATTERY_CODE) {
                Log.d("Hello World!", "开启省电模式成功");
            }

        } else if (resultCode == RESULT_CANCELED) {
            if (requestCode == REQUEST_IGNORE_BATTERY_CODE) {
                Toast.makeText(this, "请用户开启忽略电池优化~", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == INOUT_TIPS_CODE)//通过请求码(去SActivity)和回传码（回传数据到第一个页面）判断回传的页面
        {
            String content = data.getStringExtra("tip");//字符串content得到data数据
            String type = data.getStringExtra("type");
            if (type.equals("0")) {
                IsStart = false;
                tv_start_address.setText(content);
                aboart_lati = Double.valueOf(data.getStringExtra("latitude"));
                aboart_longi = Double.valueOf(data.getStringExtra("longitude"));
                aboartAddress = content;
            } else if (type.equals("1")) {
                tv_destination.setText(content);
                stop_lati = Double.valueOf(data.getStringExtra("latitude"));
                stop_Longi = Double.valueOf(data.getStringExtra("longitude"));
                poiID = data.getStringExtra("poiID");
                Stop_address = content;
            }
        }else if (requestCode==PASS_CODE){
            String type = data.getStringExtra("type");
            if (type.equals("login")) {
                closeService();
                startActivity(new Intent(mContext, LoginActivity.class));
                finish();
            }
        }else if (requestCode==ORDER_TAKINGHALL_CODE){
            orderId = Integer.parseInt(data.getStringExtra("order_id"));
            if (orderId !=0) {
                spservice.Receipt(orderId);
            }
        }else if (requestCode==BAO_TIMES_CODE){
            String codes = data.getStringExtra("codes");
            if (codes.equals("1")) {
                String service_type = data.getStringExtra("service_type");
                String estimatedeparturetime = data.getStringExtra("estimatedeparturetime");
                int meal_id = Integer.parseInt(data.getStringExtra("meal_id"));
                String position = data.getStringExtra("position");
                String positionE = data.getStringExtra("positionE");
                String positionN = data.getStringExtra("positionN");
                spservice.crossdOrder(meal_id, position, positionE, positionN, service_type, estimatedeparturetime);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<String> failPermissions = new LinkedList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                failPermissions.add(permissions[i]);
            }
        }
        if (!failPermissions.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String permission : failPermissions) {
                sb.append(permissionHintMap.get(permission)).append("、");
            }
            sb.deleteCharAt(sb.length() - 1);
            String hint = "未授予必要权限: " +
                    sb.toString() +
                    "，请前往设置页面开启权限";
            new AlertDialog.Builder(this)
                    .setMessage(hint)
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    }).setPositiveButton("设置", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                    System.exit(0);
                }
            }).show();
        }
    }

    //    获取权限
    private void requestPermissionsIfAboveM() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Map<String, String> requiredPermissions = new HashMap<>();
            requiredPermissions.put(Manifest.permission.ACCESS_FINE_LOCATION, "定位");
            requiredPermissions.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储");
            requiredPermissions.put(Manifest.permission.READ_PHONE_STATE, "读取设备信息");
            requiredPermissions.put(Manifest.permission.CALL_PHONE, "拨打电话权限");
            requiredPermissions.put(Manifest.permission.WAKE_LOCK, "省点模式");
            for (String permission : requiredPermissions.keySet()) {
                if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionHintMap.put(permission, requiredPermissions.get(permission));
                }
            }
            if (!permissionHintMap.isEmpty()) {
                requestPermissions(permissionHintMap.keySet().toArray(new String[0]), REQUEST_CODE_PERMISSION);
            }
        }
    }


    /**
     * 在8.0以上手机，如果app切到后台，系统会限制定位相关接口调用频率
     * 可以在启动轨迹上报服务时提供一个通知，这样Service启动时会使用该通知成为前台Service，可以避免此限制
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private Notification createNotification() {
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID_SERVICE_RUNNING, "app service", NotificationManager.IMPORTANCE_LOW);
            nm.createNotificationChannel(channel);
            builder = new Notification.Builder(getApplicationContext(), CHANNEL_ID_SERVICE_RUNNING);
        } else {
            builder = new Notification.Builder(getApplicationContext());
        }
        Intent nfIntent = new Intent(HomeActivity.this, HomeActivity.class);
        nfIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        builder.setContentIntent(PendingIntent.getActivity(HomeActivity.this, 0, nfIntent, 0))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("猎鹰sdk运行中")
                .setContentText("猎鹰sdk运行中");
        Notification notification = builder.build();
        return notification;
    }

    //定时刷新订单时间
    static Handler handler_order = new Handler();
    static Runnable runnable_order = new Runnable() {
        @Override
        public void run() {

            if (Travel_type == 1) {
                home_tv_wait_time.setText(TimeUtils.GetTiem(createtime));
            } else if (Travel_type == 2) {
                home_tv_travel_time.setText(TimeUtils.GetTiem(createtime));
            }
            long currenttimes = System.currentTimeMillis();
            int times = (int) (currenttimes - (createtime*1000)) / 1000 - free_waiting_time;
            if (times > 5) {
                if (times % 30 == 0) {
                    home_tv_wait_sum.setText(times / 60 * waiting_price + "元");
                }
            }
            handler_order.postDelayed(runnable_order, 1000);
        }
    };

    //定时刷新位置信息
    static Handler handler = new Handler();
    static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            aMapTrackClient.queryTerminal(new QueryTerminalRequest(Constants.SERVICE_ID, user_id), new SimpleOnTrackListener() {
                @Override
                public void onQueryTerminalCallback(QueryTerminalResponse queryTerminalResponse) {
                    if (queryTerminalResponse.isSuccess()) {
                        final long terminalId = queryTerminalResponse.getTid();
                        if (terminalId > 0) {
                            long curr = System.currentTimeMillis();
                            DistanceRequest distanceRequest = new DistanceRequest(
                                    Constants.SERVICE_ID,
                                    terminalId,
                                    current_time, // 开始时间
                                    curr,   // 结束时间
                                    -1  // 轨迹id
                            );
                            aMapTrackClient.queryDistance(distanceRequest, new SimpleOnTrackListener() {
                                @Override
                                public void onDistanceCallback(DistanceResponse distanceResponse) {
                                    if (distanceResponse.isSuccess()) {
                                        Log.e("数据刷新中", "公里数" + distanceResponse.getDistance() + "和" + current_time + "terminalId" + terminalId);
                                        double distance = distanceResponse.getDistance();
                                        if (distance > route_number) {
                                            route_number = distance;
                                            if (route_number > 1000) {
                                                home_tv_driving_distance.setText(+route_number / 1000 + "km");
                                            } else {
                                                home_tv_driving_distance.setText(+route_number + "m");
                                            }
                                            driving_distance =route_number/1000;
                                        } else {
                                            if (route_number > 1000) {
                                                home_tv_driving_distance.setText(+route_number / 1000 + "km");
                                            } else {
                                                home_tv_driving_distance.setText(+route_number + "m");
                                            }
                                            driving_distance =route_number/1000;
                                        }

                                    } else {
//                                        tv_logText.setText("行驶里程查询失败，" + distanceResponse.getErrorMsg());
                                    }
                                }
                            });
                            aMapTrackClient.queryLatestPoint(new LatestPointRequest(Constants.SERVICE_ID, terminalId), new SimpleOnTrackListener() {

                                private Marker markers;

                                @Override
                                public void onLatestPointCallback(LatestPointResponse latestPointResponse) {
                                    if (latestPointResponse.isSuccess()) {

                                        Point point = latestPointResponse.getLatestPoint().getPoint();
//                                        //构建开始点与终止点的经纬度数据
//                                        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
//                                                new LatLonPoint(point.getLat(), point.getLng()),
//                                                new LatLonPoint(stop_lati, stop_Longi));
//                                        RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, 1, null, null, "");
//                                        routeSearch.calculateDriveRouteAsyn(query);
//                                        start_lati = point.getLat();
//                                        stop_Longi = point.getLng();

                                    } else {
//                                        tv_latlng.setText("查询实时位置失败，" + latestPointResponse.getErrorMsg());
                                    }
                                }
                            });

                        } else {
//                            tv_logText.setText("终端不存在，请先使用轨迹上报示例页面创建终端和上报轨迹");
                        }
                    } else {
//                        tv_logText.setText("终端查询失败，" + queryTerminalResponse.getErrorMsg());
                    }

                }
            });
            handler.postDelayed(runnable, delayMillis);
        }
    };


    //导航监听
    INaviInfoCallback naviInfoCallback = new INaviInfoCallback() {
        @Override
        public void onInitNaviFailure() {

        }

        @Override
        public void onGetNavigationText(String s) {

        }

        @Override
        public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

        }

        @Override
        public void onArriveDestination(boolean b) {

        }

        @Override
        public void onStartNavi(int i) {

        }

        @Override
        public void onCalculateRouteSuccess(int[] ints) {

        }

        @Override
        public void onCalculateRouteFailure(int i) {

        }

        @Override
        public void onStopSpeaking() {

        }

        @Override
        public void onReCalculateRoute(int i) {

        }

        @Override
        public void onExitPage(int i) {

        }

        @Override
        public void onStrategyChanged(int i) {

        }

        @Override
        public View getCustomNaviBottomView() {
            return null;
        }

        @Override
        public View getCustomNaviView() {
            return null;
        }

        @Override
        public void onArrivedWayPoint(int i) {

        }

        @Override
        public void onMapTypeChanged(int i) {

        }

        @Override
        public View getCustomMiddleView() {
            return null;
        }
    };


    //路线轨迹监听
    RouteSearch.OnRouteSearchListener routeSearchListener = new RouteSearch.OnRouteSearchListener() {

        private Marker stopmarkers;

        @Override
        public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

        }

        @Override
        public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {
            if (driveRouteResult != null) {
                List<DrivePath> pathList = driveRouteResult.getPaths();
                List<LatLng> driverPath = new ArrayList<>();

                for (DrivePath dp : pathList) {

                    List<DriveStep> stepList = dp.getSteps();
                    for (DriveStep ds : stepList) {

                        List<LatLonPoint> points = ds.getPolyline();
                        for (LatLonPoint llp : points) {
                            driverPath.add(new LatLng(llp.getLatitude(), llp.getLongitude()));
                        }
                    }
                }
                int size = driverPath.size();
//                aMap.clear();
//                aMap.addPolyline(new PolylineOptions()
//                        .addAll(driverPath)
//                        .width(20)
//                        //是否开启纹理贴图
//                        .setUseTexture(true)
//                        //绘制成大地线
//                        .geodesic(false)
//                        //设置纹理样式
////                .setCustomTexture(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.custtexture)))
//                        //设置画线的颜色
//                        .color(Color.argb(200, 0, 225, 12)));
                //添加终点标注
                if (stopmarker != null) {
                    stopmarker.remove();
                }

                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(new LatLng(stop_lati, stop_Longi));
                markerOption.title("目的地").snippet(Stop_address);
                markerOption.draggable(false);//设置Marker可拖动
                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(), R.mipmap.score)));
                stopmarker = aMap.addMarker(markerOption);
                if (stopmarkers != null) {
                    stopmarkers.remove();
                }

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLng(aboart_lati, aboart_longi));
                markerOptions.title("出发的").snippet(aboartAddress);
                markerOptions.draggable(false);//设置Marker可拖动
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(), R.mipmap.geo_icon)));
                stopmarkers = aMap.addMarker(markerOptions);
                MarkerOptions markerOptione = new MarkerOptions();
                markerOptione.position(new LatLng(start_lati, stop_Longi));
                markerOptione.draggable(false);//设置Marker可拖动
                markerOptione.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(resources, R.mipmap.icon_taxi)));
                aMap.addMarker(markerOptione);
            }
        }

        @Override
        public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

        }

        @Override
        public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

        }
    };

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(3000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(false); //可选，设置是否使用缓存定位，默认为true
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption;
    }

    //显示订单详情
    public static void OrderInfo(OrderInfoBean.DataBean data) {
        OrderInfoBean.DataBean.OrderBean order = data.getOrder();
        order_trip_id = order.getOrder_trip_id();
        orderId = order.getId();
        createtime = order.getUpdatetime();
        createddatetime = order.getCreateddatetime();
        switch (order.getStatus()) {
            case "1":
                break;
            case "2":
                ll_order_wait.setVisibility(View.GONE);
                ll_order_in.setVisibility(View.GONE);
                ll_order_completion.setVisibility(View.GONE);
                ll_order_in.setVisibility(View.GONE);
                ll_order_geton.setVisibility(View.VISIBLE);
                home_rl_order_taking.setVisibility(View.GONE);
                geton_startaddress.setText(order.getStartingpoint());
                geton_stopaddress.setText(order.getDestination());
                tv_wait_startaddress.setText(order.getStartingpoint());
                tv_wait_stopaddress.setText(order.getDestination());
                tv_in_startaddress.setText(order.getStartingpoint());
                tv_in_stopaddress.setText(order.getDestination());
                break;
            case "3":
                Travel_type=1;
                ll_order_in.setVisibility(View.GONE);
                ll_order_completion.setVisibility(View.GONE);
                ll_order_in.setVisibility(View.GONE);
                ll_order_geton.setVisibility(View.GONE);
                home_rl_order_taking.setVisibility(View.GONE);
                ll_order_wait.setVisibility(View.VISIBLE);
                geton_startaddress.setText(order.getStartingpoint());
                geton_stopaddress.setText(order.getDestination());
                tv_wait_startaddress.setText(order.getStartingpoint());
                tv_wait_stopaddress.setText(order.getDestination());
                tv_in_startaddress.setText(order.getStartingpoint());
                tv_in_stopaddress.setText(order.getDestination());
                handler_order.postDelayed(runnable_order, 1000);
                break;
            case "4":
                Travel_type=2;
                createtime=Integer.parseInt(order.getDeparturetime());
                ll_order_in.setVisibility(View.GONE);
                ll_order_completion.setVisibility(View.GONE);
                ll_order_geton.setVisibility(View.GONE);
                home_rl_order_taking.setVisibility(View.GONE);
                ll_order_wait.setVisibility(View.GONE);
                ll_order_in.setVisibility(View.VISIBLE);
                geton_startaddress.setText(order.getStartingpoint());
                geton_stopaddress.setText(order.getDestination());
                tv_wait_startaddress.setText(order.getStartingpoint());
                tv_wait_stopaddress.setText(order.getDestination());
                tv_in_startaddress.setText(order.getStartingpoint());
                tv_in_stopaddress.setText(order.getDestination());
                handler_order.postDelayed(runnable_order, 1000);
                break;
            case "5":
                ll_order_in.setVisibility(View.GONE);
                ll_order_geton.setVisibility(View.GONE);
                home_rl_order_taking.setVisibility(View.GONE);
                ll_order_wait.setVisibility(View.GONE);
                ll_order_in.setVisibility(View.GONE);
                ll_order_completion.setVisibility(View.VISIBLE);
                geton_startaddress.setText(order.getStartingpoint());
                geton_stopaddress.setText(order.getDestination());
                tv_wait_startaddress.setText(order.getStartingpoint());
                tv_wait_stopaddress.setText(order.getDestination());
                tv_in_startaddress.setText(order.getStartingpoint());
                tv_in_stopaddress.setText(order.getDestination());
                tv_meetMoney.setText(order.getMoney()+"");
                tv_totalMoney.setText(order.getMoney()+"");
                break;
            case "0":
                aMap.clear();
                stop_Longi = 0;
                stop_lati = 0;
                Stop_address = "";
                tv_destination.setText("");
                tv_in_stopaddress.setText("");
                tv_wait_stopaddress.setText("");
                ll_order_completion.setVisibility(View.GONE);
                home_ll_driver.setVisibility(View.GONE);
                ll_order_wait.setVisibility(View.GONE);
                ll_order_geton.setVisibility(View.GONE);
                ll_order_in.setVisibility(View.GONE);
                home_rl_order_taking.setVisibility(View.VISIBLE);
                break;
            case "6":
                aMap.clear();
                stop_Longi = 0;
                stop_lati = 0;
                Stop_address = "";
                tv_destination.setText("");
                tv_in_stopaddress.setText("");
                tv_wait_stopaddress.setText("");
                home_ll_driver.setVisibility(View.GONE);
                ll_order_wait.setVisibility(View.GONE);
                ll_order_geton.setVisibility(View.GONE);
                ll_order_in.setVisibility(View.GONE);
                home_rl_order_taking.setVisibility(View.GONE);
                ll_order_completion.setVisibility(View.VISIBLE);
                tv_meetMoney.setText(order.getMoney()+"");
                tv_totalMoney.setText(order.getMoney()+"");
                break;
        }
    }

    //到达上车地点
    public static void DriverArrive() {
        ll_order_geton.setVisibility(View.GONE);
        ll_order_in.setVisibility(View.GONE);
        home_ll_driver.setVisibility(View.GONE);
        home_rl_order_taking.setVisibility(View.GONE);
        ll_order_wait.setVisibility(View.VISIBLE);
        current_time = System.currentTimeMillis();
        handler_order.postDelayed(runnable_order, 1000);
    }

    //开始行驶
    public static void PassengerBoarding() {
        current_time = System.currentTimeMillis();
        home_ll_driver.setVisibility(View.GONE);
        home_rl_order_taking.setVisibility(View.GONE);
        ll_order_geton.setVisibility(View.GONE);
        ll_order_wait.setVisibility(View.GONE);
        ll_order_in.setVisibility(View.VISIBLE);
        if (isServiceRunning) {
            aMapTrackClient.stopTrack(new TrackParam(Constants.SERVICE_ID, terminalId), onTrackListener);
            OrderBean orderBean = new OrderBean();
            orderBean.setOrderName("订单编号:" + terminalId);
            orderBean.setStartDt(current_time + "");
            orderBean.setStopDt(current_time + "");
            orderBean.save();
        } else {
            startTrack();
        }
        handler.postDelayed(runnable, delayMillis);
    }

    //到达目的地
    public static void Arrive(OrderDetailBean.DataBean data) {
        ll_order_completion.setVisibility(View.VISIBLE);
        ll_order_wait.setVisibility(View.GONE);
        ll_order_geton.setVisibility(View.GONE);
        ll_order_in.setVisibility(View.GONE);
        home_rl_order_taking.setVisibility(View.GONE);
        home_rl_order_taking.setVisibility(View.VISIBLE);
        tv_start_address.setText("");
        Stop_address = "";
        stop_Longi = 0L;
        stop_lati = 0L;
        tv_totalMoney.setText("¥ " + data.getMoney() + "");
        tv_meetMoney.setText("¥ " + data.getMoney()+ "");
    }

    //到达目的地
    public static void Arrive(int data) {
        ll_order_completion.setVisibility(View.VISIBLE);
        ll_order_wait.setVisibility(View.GONE);
        ll_order_geton.setVisibility(View.GONE);
        ll_order_in.setVisibility(View.GONE);
        home_rl_order_taking.setVisibility(View.GONE);
        home_rl_order_taking.setVisibility(View.VISIBLE);
        tv_start_address.setText("");
        Stop_address = "";
        stop_Longi = 0L;
        stop_lati = 0L;
        tv_totalMoney.setText("¥ " + data + "");
        tv_meetMoney.setText("¥ " + data+ "");
    }

    //订单结束
    public static void settlementDriver() {
        aMap.clear();
        order_trip_id = 0;
        orderId = 0;
        stop_Longi = 0;
        stop_lati = 0;
        Stop_address = "";
        tv_destination.setText("");
        tv_in_stopaddress.setText("");
        tv_wait_stopaddress.setText("");
        ll_order_completion.setVisibility(View.GONE);
        home_ll_driver.setVisibility(View.GONE);
        ll_order_wait.setVisibility(View.GONE);
        ll_order_geton.setVisibility(View.GONE);
        ll_order_in.setVisibility(View.GONE);
        home_rl_order_taking.setVisibility(View.VISIBLE);
    }

    //取消订单
    public static void refuseOrder() {
        aMap.clear();
        order_trip_id = 0;
        orderId = 0;
        stop_Longi = 0;
        stop_lati = 0;
        Stop_address = "";
        tv_destination.setText("");
        tv_in_stopaddress.setText("");
        tv_wait_stopaddress.setText("");
        home_ll_driver.setVisibility(View.GONE);
        ll_order_wait.setVisibility(View.GONE);
        ll_order_geton.setVisibility(View.GONE);
        ll_order_in.setVisibility(View.GONE);
        home_rl_order_taking.setVisibility(View.VISIBLE);
    }

    //显示填写订单信息dialog
    public static void OrderDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(homeActivity);
        builder.setMessage("订单完成")
                .setIcon(R.mipmap.ic_launcher)
                .setNeutralButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                         OkGo.<String>get("https://www.yihu16888.com/api/order/driver_trip_delete")
                                 .params("type", "1")
                                 .params("token", token)
                                 .execute(new StringCallback() {
                                     @Override
                                     public void onSuccess(Response<String> response) {

                                     }
                                 });
                    }
                })
                .setPositiveButton("填写信息", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        OkGo.<String>get("https://www.yihu16888.com/api/order/driver_trip_delete")
                                .params("type", "1")
                                .params("token", token)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {

                                    }
                                });
                    }
                });
        builder.show();
    }

    //取消订单dialog
    public void UI_cancel_dialog(){
        //取消订单弹出窗
        inflate_cancel = LayoutInflater.from(mContext).inflate(R.layout.dialog_cancel, null);
        cancel_builder = new AlertDialog.Builder(this)
                .setView(inflate_cancel)
                .setCancelable(false);
        inflate_cancel.findViewById(R.id.dialog_cancel_tv_back).setOnClickListener(this);
        inflate_cancel.findViewById(R.id.dialog_cancel_tv_confirm).setOnClickListener(this);
        RadioGroup cancel_radioGroup = inflate_cancel.findViewById(R.id.dialog_cancel_radioGroup);
        inflate_cancel.findViewById(R.id.dialog_cancel_radio_btn_one).setOnClickListener(this);
        inflate_cancel.findViewById(R.id.dialog_cancel_radio_btn_two).setOnClickListener(this);
        inflate_cancel.findViewById(R.id.dialog_cancel_radio_btn_three).setOnClickListener(this);
        inflate_cancel.findViewById(R.id.dialog_cancel_radio_btn_four).setOnClickListener(this);
        Reaton="乘客不需要乘车";
        cancel_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.e("radioGroup","第几");
                switch (checkedId) {
                    case R.id.dialog_cancel_radio_btn_one:
                        Reaton="乘客不需要乘车";
                        break;
                    case R.id.dialog_cancel_radio_btn_two:
                        Reaton="乘客信息有误";
                        break;
                    case R.id.dialog_cancel_radio_btn_three:
                        Reaton="司机有事";
                        break;
                    case R.id.dialog_cancel_radio_btn_four:
                        Reaton="其它";
                    default:
                        break;
                }
            }
        });
    }

    // 初始化 录音器
    private void initRecorder() {
        if( mRecorder == null) {
            File dir = new File(Environment.getExternalStorageDirectory(), "sounds");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //存储到SD卡当然也可上传到服务器
            soundFile = new File(dir, System.currentTimeMillis() + ".amr");
            if (!soundFile.exists()) {
                try {
                    soundFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        mAudioPath = soundFile.getPath();
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mRecorder.setOutputFile(mAudioPath);
        mIsRecording = false;
    }

    /** 开始录音，并保存到文件中 */
    public void recordAudio() {
        initRecorder();
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mRecorder.start();
    }

    /** 停止录音 */
    public void stopRecord() {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
            mIsRecording = true;
        }
    }

    public static String parseResponse(InputStream resStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(resStream));
        StringBuffer resBuffer = new StringBuffer();
        String resTemp = "";

        try {
            while((resTemp = br.readLine()) != null) {
                resBuffer.append(resTemp);
            }

            return resBuffer.toString();
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }
}
