package com.zhkj.yhfw.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.autonavi.ae.pos.LocGSVData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.zhkj.yhfw.Bean.ArriveBean;
import com.zhkj.yhfw.Bean.CrossedPathsOrderBean;
import com.zhkj.yhfw.Bean.DriverArriveBean;
import com.zhkj.yhfw.Bean.EventBean;
import com.zhkj.yhfw.Bean.LoginDemoBean;
import com.zhkj.yhfw.Bean.OrderDetailBean;
import com.zhkj.yhfw.Bean.OrderInfoBean;
import com.zhkj.yhfw.Bean.OrdersBean;
import com.zhkj.yhfw.Bean.PassengerBoardingBean;
import com.zhkj.yhfw.Bean.PlaceOrderBean;
import com.zhkj.yhfw.Bean.PointsBean;
import com.zhkj.yhfw.Bean.PositionBean;
import com.zhkj.yhfw.Bean.RefuseOrderBean;
import com.zhkj.yhfw.Bean.TypeBean;
import com.zhkj.yhfw.Bean.loginbean;
import com.zhkj.yhfw.Bean.points;
import com.zhkj.yhfw.HomeActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;

public class BackService extends Service {
    private final IBinder binder = new LocalBinder(); // 服务绑定器
    private String token;
    private AMapLocationClient locationClient;
    private AMapLocationClientOption locationOption;
    private SharedPreferences sp;
    private float locate_accuracy;
    private boolean IsPoint=false;
    private String pointsBeanLog;
    public String JSONS="";
    private float speed;
    private String user_id;

    @Override
    public void onCreate() {
        super.onCreate();
        sp = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        user_id =sp.getString("user_id","");
        //实时定位
        initLocation();
        try {
            initSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String appVersionName = getAppVersionName(getApplicationContext());
        Log.e("版本号",appVersionName+"哈哈哈"+token);
    }
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
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


    private String CityName;
    private double start_lati;
    private double start_Longi;
    private String Start_address;
    private int order_trip_id;
    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {


        private long locate_time;

        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {
                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if(location.getErrorCode() == 0){
                    //解析定位结果，
                    CityName =location.getCity();
                    start_lati =location.getLatitude();
                    start_Longi =location.getLongitude();
                    Start_address = location.getCity() + location.getDistrict() + location.getStreet() + location.getStreetNum();
                    locate_accuracy = location.getAccuracy();
                    speed = location.getSpeed();
                    locate_time = location.getTime();
                   if (IsPoint) {
                       if (order_trip_id != 0) {
                           String locations = start_Longi + "," + start_lati;
                           final String s1 = new Gson().toJson(new points(locations, locate_time, (int)speed, 0, 0, (int)locate_accuracy));
                           if (!JSONS.equals("")) {
                               JSONS = JSONS + "," + s1;
                           } else {
                               JSONS = s1;
                           }
                       }
                   }
                }
                } else {
                }

        }
    };

    /**
     * 默认的定位参数
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    private AMapLocationClientOption getDefaultOption(){
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    /**
     * 配置服务连接监听器类
     */
    public class LocalBinder extends Binder {
        public BackService getService() {
            return BackService.this;

        }
    }

    class InitSocketThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                initSocket();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    /**
     * 心跳检测时间
     */
    private static final long HEART_BEAT_RATE = 2 * 1000;//每隔15秒进行一次对长连接的心跳检测
    private static final String WEBSOCKET_HOST_AND_PORT = "wss://hduxh.xmhavefun.com/websocket";//可替换为自己的主机名和端口号
    private WebSocket mWebSocket;


    // 初始化socket
    private void initSocket() throws UnknownHostException, IOException {
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(0, TimeUnit.MILLISECONDS).build();
        final Request request = new Request.Builder().url(WEBSOCKET_HOST_AND_PORT).build();
        client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {//开启长连接成功的回调
                super.onOpen(webSocket, response);
                Log.e("BackService","进来了");
                mWebSocket = webSocket;
                String s = new Gson().toJson(new LoginDemoBean(token, "1", user_id,"login"));
                mWebSocket.send(s);
            }

            @Override
            public void onMessage(WebSocket webSocket, final String text) {//接收消息的回调
                super.onMessage(webSocket, text);
                //收到服务器端传过来的消息text
                Log.e("BackService1",text);
                Log.e("tokens",token);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                if (text!=null){
                    String logins = new Gson().toJson(new LoginDemoBean(token, "1", user_id,"login"));
                    mWebSocket.send(logins);
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    TypeBean typeBean = gson.fromJson(text, TypeBean.class);
                    if (typeBean!=null){
                        String type = typeBean.getType();
                        if (type!=null){
                        }else {
                            OrdersBean ordersBean = gson.fromJson(text, OrdersBean.class);
                            if (ordersBean!=null){
                                int code = ordersBean.getCode();
                                if (code ==200) {
                                    String event = ordersBean.getEvent();
                                    if (!event.equals("")) {
                                        if (event.equals("order_info")) {
                                            OrderInfoBean orderInfoBean = gson.fromJson(text, OrderInfoBean.class);
                                            OrderInfoBean.DataBean data = orderInfoBean.getData();
                                            if (data!=null){
                                                //获取到订单详情
                                             HomeActivity.OrderInfo(data);
                                             OrderInfoBean.DataBean.OrderBean order = data.getOrder();
                                                order_trip_id = order.getOrder_trip_id();
                                            }else {
                                            }
                                        }else if (event.equals("crossed_paths_order")){
                                            //获取订单详情
                                            orderInfo();
                                        }else if (event.equals("driver_arrive")){
                                             //到达上车地点
                                            HomeActivity.DriverArrive();
                                        }else if (event.equals("passenger_boarding")){
                                            //开始行驶
                                            HomeActivity.PassengerBoarding();
                                            orderInfo();
                                        }else if (event.equals("arrive")){
                                            //到达目的地
                                            try {
                                                OrderDetailBean orderDetailBean = gson.fromJson(text, OrderDetailBean.class);
                                                OrderDetailBean.DataBean data = orderDetailBean.getData();
                                                if (data != null) {
                                                    HomeActivity.Arrive(data);
                                                    gjHandler.removeCallbacks(gjrunbale);
                                                } else {
                                                }
                                            }catch (Exception e){
                                                try {
                                                    EventBean eventBean = gson.fromJson(text, EventBean.class);
                                                    int data = eventBean.getData();
                                                    HomeActivity.Arrive(data);
                                                    gjHandler.removeCallbacks(gjrunbale);
                                                }catch (Exception es){

                                                }

                                            }
                                        }else if (event.equals("settlement_driver")){
                                            //订单结算
                                            HomeActivity.settlementDriver();
//                                            orderInfo();
                                            HomeActivity.OrderDialog();
                                        }else if (event.equals("position")){

                                        }else if (event.equals("refuse_order")){
                                            //订单取消
//                                            orderInfo();
                                            HomeActivity.refuseOrder();
                                        }else if (event.equals("login")){
                                            //查询是否存在订单
                                            orderInfo();
                                        }else if (event.equals("receipt")){
                                            orderInfo();
                                        }else if (event.equals("crossed_paths_order")){
                                            orderInfo();
                                        }

                                    }
                                }else if (code==100){
                                    String event = ordersBean.getEvent();
                                    if (event.equals("logindfff")){
                                        String username = sp.getString("username", "");
                                        String password = sp.getString("password", "");
                                        if (!username.equals("")) {
                                            OkGo.<String>post("https://www.yihu16888.com/api/user/login")
                                                    .params("account", username)
                                                    .params("password", password)
                                                    .params("type", "1")
                                                    .execute(new StringCallback() {
                                                        @Override
                                                        public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                                                            String body = response.body();
                                                            GsonBuilder builder = new GsonBuilder();
                                                            Gson gson = builder.create();
                                                            loginbean loginbean = gson.fromJson(body, loginbean.class);
                                                            if (loginbean.getCode() == 200) {
                                                                SharedPreferences.Editor editor = sp.edit();
                                                                com.zhkj.yhfw.Bean.loginbean.DataBean data = loginbean.getData();
                                                                if (data != null) {
                                                                    com.zhkj.yhfw.Bean.loginbean.DataBean.UserinfoBean userinfo = data.getUserinfo();
                                                                    String tokens = userinfo.getToken();
                                                                    String avatar = userinfo.getAvatar();
                                                                    editor.putString("token", tokens);
                                                                    editor.putString("avatar", avatar);
                                                                    editor.putString("user_id", userinfo.getUser_id()+ "");
                                                                    editor.putString("mobile", userinfo.getMobile());
                                                                    editor.putString("nickname", userinfo.getNickname());
                                                                    editor.commit();
                                                                    token=tokens;
                                                                }
                                                            }
                                                        }
                                                    });
                                        }
                                        token = sp.getString("token", "");
                                        String s = new Gson().toJson(new LoginDemoBean(token, "1", user_id,"login"));
                                        mWebSocket.send(s);
                                    }
                                }
                            }else {
                            }
                        }
                    }else {

                    }
                }
                    }
                });
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                super.onMessage(webSocket, bytes);
//                Log.e("BackService2",bytes.toString());
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
//                Log.e("BackService3",reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
//                Log.e("BackService4",reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {//长连接连接失败的回调
                super.onFailure(webSocket, t, response);
                Log.e("BackService5","发发发");
            }
        });
        client.dispatcher().executorService().shutdown();
        mHandler.postDelayed(heartBeatRunnable, HEART_BEAT_RATE);//开启心跳检测
    }

    private long sendTime = 0L;
    // 发送心跳包
    private Handler mHandler = new Handler();
    private Runnable heartBeatRunnable = new Runnable() {
        @Override
        public void run() {
                try {
                    if (!token.equals("")) {
                        boolean isSuccess = mWebSocket.send("");//发送一个空消息给服务器，通过发送消息的成功失败来判断长连接的连接状态
                        String s = new Gson().toJson(new PositionBean("position", token, "1", user_id, new PositionBean.data(CityName, Start_address, start_lati + "", start_Longi + "")));
                        if (!isSuccess) {//长连接已断开
                            mHandler.removeCallbacks(heartBeatRunnable);
                            mWebSocket.cancel();//取消掉以前的长连接
                            new InitSocketThread().start();//创建一个新的连接
                            mWebSocket.send(s);
                        } else {//长连接处于连接状态
                            mWebSocket.send(s);
                        }
                    }else {

                    }
                }catch (Exception e){

                }
            mHandler.postDelayed(this, HEART_BEAT_RATE);//每隔一定的时间，对长连接进行一次心跳检测
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(heartBeatRunnable);
//        gjHandler.removeCallbacks(gjrunbale);
        if (mWebSocket != null) {
            mWebSocket.close(1000, null);
            mWebSocket.cancel();//取消掉以前的长连接
        }
    }

    //路边接单
    public void  PlaceOrder(String mobile,String aboart_lati,String aboart_longi,String aboartAddress,String stop_lati,String stop_Longi,String Stop_address) {
        IsPoint=false;
        String s1 = new Gson().toJson(new PlaceOrderBean("crossed_paths_order", token, "1", user_id, new PlaceOrderBean.data("0", mobile, aboart_lati + "", aboart_longi + "",
                aboartAddress, stop_lati + "", stop_Longi + "", Stop_address, "0")));
        boolean isSuccess = mWebSocket.send("");//发送一个空消息给服务器，通过发送消息的成功失败来判断长连接的连接状态
        if (!isSuccess) {//长连接已断开
            mHandler.removeCallbacks(heartBeatRunnable);
            mWebSocket.cancel();//取消掉以前的长连接
            new InitSocketThread().start();//创建一个新的连接
            mWebSocket.send(s1);
        } else {//长连接处于连接状态
            mWebSocket.send(s1);
        }
    }

    //获取订单详情
    public void  orderInfo(){
        boolean isSuccess = mWebSocket.send("");//发送一个空消息给服务器，通过发送消息的成功失败来判断长连接的连接状态
        String s = new Gson().toJson(new LoginDemoBean(token, "1", user_id,"order_info"));
        if (!isSuccess) {//长连接已断开
            mHandler.removeCallbacks(heartBeatRunnable);
            mWebSocket.cancel();//取消掉以前的长连接
            new InitSocketThread().start();//创建一个新的连接
            mWebSocket.send(s);
        } else {//长连接处于连接状态
            mWebSocket.send(s);
        }
    }

    //到达上车点
    public void driverArrive(int order_id){
        boolean isSuccess = mWebSocket.send("");//发送一个空消息给服务器，通过发送消息的成功失败来判断长连接的连接状态
        String s = new Gson().toJson(new DriverArriveBean(token, "1", user_id,"driver_arrive",new DriverArriveBean.data(CityName, order_id, Start_address, start_lati+"", start_Longi+"")));
        if (!isSuccess) {//长连接已断开
            mHandler.removeCallbacks(heartBeatRunnable);
            mWebSocket.cancel();//取消掉以前的长连接
            new InitSocketThread().start();//创建一个新的连接
            mWebSocket.send(s);
        } else {//长连接处于连接状态
            mWebSocket.send(s);
        }
    }

    //开始行驶
    public void passengerBoarding(int order_id){
        OkGo.<String>get("https://www.yihu16888.com/api/order/trip_add_trace")
                .params("type", "1")
                .params("token", token)
                .params("trip_id",order_trip_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                    }
                });
        IsPoint=true;
        gjHandler.postDelayed(gjrunbale,1000);
        boolean isSuccess = mWebSocket.send("");//发送一个空消息给服务器，通过发送消息的成功失败来判断长连接的连接状态
        String s = new Gson().toJson(new PassengerBoardingBean("passenger_boarding",token, "1", user_id,new PassengerBoardingBean.data(order_id)));
        if (!isSuccess) {//长连接已断开
            mHandler.removeCallbacks(heartBeatRunnable);
            mWebSocket.cancel();//取消掉以前的长连接
            new InitSocketThread().start();//创建一个新的连接
            mWebSocket.send(s);
        } else {//长连接处于连接状态
            mWebSocket.send(s);
        }
    }

    //到达终点
    public void Arrive(String mileage,int order_id,String position,String positionE,String positionN){
        IsPoint=false;
        order_trip_id=0;

        boolean isSuccess = mWebSocket.send("");//发送一个空消息给服务器，通过发送消息的成功失败来判断长连接的连接状态
        String s = new Gson().toJson(new ArriveBean("arrive",token, "1", user_id,new ArriveBean.data(CityName,Start_address,start_lati+"",start_Longi+"",mileage,order_id,position,positionE,positionN)));
        if (!isSuccess) {//长连接已断开
            mHandler.removeCallbacks(heartBeatRunnable);
            mWebSocket.cancel();//取消掉以前的长连接
            new InitSocketThread().start();//创建一个新的连接
            mWebSocket.send(s);
        } else {//长连接处于连接状态
            mWebSocket.send(s);
        }
    }

    //订单结算
    public void OrderMoney(int order_id){
        boolean isSuccess = mWebSocket.send("");//发送一个空消息给服务器，通过发送消息的成功失败来判断长连接的连接状态
        String s = new Gson().toJson(new DriverArriveBean(token, "1", user_id,"settlement_driver",new DriverArriveBean.data(CityName, order_id, Start_address, start_lati+"", start_Longi+"")));
        if (!isSuccess) {//长连接已断开
            mHandler.removeCallbacks(heartBeatRunnable);
            mWebSocket.cancel();//取消掉以前的长连接
            new InitSocketThread().start();//创建一个新的连接
            mWebSocket.send(s);
        } else {//长连接处于连接状态
            mWebSocket.send(s);
        }

    }

    //猎鹰返回路程
    public void refuse_order(int order_id,String reaton){
        boolean isSuccess = mWebSocket.send("");//发送一个空消息给服务器，通过发送消息的成功失败来判断长连接的连接状态
        String s = new Gson().toJson(new RefuseOrderBean("refuse_order",token, "1", user_id,new RefuseOrderBean.data(CityName,order_id,Start_address,start_lati+"",start_Longi+"",reaton)));
        if (!isSuccess) {//长连接已断开
            mHandler.removeCallbacks(heartBeatRunnable);
            mWebSocket.cancel();//取消掉以前的长连接
            new InitSocketThread().start();//创建一个新的连接
            mWebSocket.send(s);
        } else {//长连接处于连接状态
            mWebSocket.send(s);
        }
    }

    //抢单功能
    public void Receipt(int order_id){
        IsPoint=false;
        String s1 = new Gson().toJson(new DriverArriveBean(token, "1", user_id,"receipt",new DriverArriveBean.data(CityName, order_id, Start_address, start_lati+"", start_Longi+"")));
        boolean isSuccess = mWebSocket.send("");//发送一个空消息给服务器，通过发送消息的成功失败来判断长连接的连接状态
        if (!isSuccess) {//长连接已断开
            mHandler.removeCallbacks(heartBeatRunnable);
            mWebSocket.cancel();//取消掉以前的长连接
            new InitSocketThread().start();//创建一个新的连接
            mWebSocket.send(s1);
        } else {//长连接处于连接状态
            mWebSocket.send(s1);
        }
    }

    //包时订单
    public void crossdOrder(int meal_id,String posintion,String positionE,String positionN,String service_type,String estimatedeparturetime){
        IsPoint=false;

        String s1 = new Gson().toJson(new CrossedPathsOrderBean("crossed_paths_order", token, "1", user_id,new CrossedPathsOrderBean.data(CityName,1,estimatedeparturetime+"",
                meal_id+"",posintion,positionE,positionN,service_type,posintion,positionE,positionN,"91")));
        boolean isSuccess = mWebSocket.send("");//发送一个空消息给服务器，通过发送消息的成功失败来判断长连接的连接状态
        if (!isSuccess) {//长连接已断开
            mHandler.removeCallbacks(heartBeatRunnable);
            mWebSocket.cancel();//取消掉以前的长连接
            new InitSocketThread().start();//创建一个新的连接
            mWebSocket.send(s1);
        } else {//长连接处于连接状态
            mWebSocket.send(s1);
        }
    }

   //上传轨迹点
    Handler gjHandler=new Handler();
    Runnable gjrunbale=new Runnable() {
        @Override
        public void run() {
            OkGo.<String>get("https://www.yihu16888.com/api/order/up_trace")
                    .params("type", "1")
                    .params("token", token)
                    .params("order_trip_id",order_trip_id)
                    .params("points", "[" + JSONS+ "]")
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                            Log.e("鹰眼", response.body()+JSONS);
                            JSONS="";
                        }
                    });
            gjHandler.postDelayed(gjrunbale,5000);

        }
    };
}
