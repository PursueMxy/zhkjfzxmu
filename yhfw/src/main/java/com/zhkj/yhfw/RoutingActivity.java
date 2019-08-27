package com.zhkj.yhfw;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Poi;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
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
import com.amap.api.navi.model.NaviPoi;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DistanceItem;
import com.amap.api.services.route.DistanceResult;
import com.amap.api.services.route.DistanceSearch;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.DriveStep;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.autonavi.tbt.TrafficFacilityInfo;

import java.util.ArrayList;
import java.util.List;

//路线规划
public class RoutingActivity extends AppCompatActivity implements LocationSource, AMapLocationListener, View.OnClickListener, RouteSearch.OnRouteSearchListener, DistanceSearch.OnDistanceSearchListener {

    private TextureMapView mapView;
    private Context mContext;
    private String poiID;
    private AMap aMap;
    OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    private RouteSearch routeSearch;
    private DistanceSearch distanceSearch;
    private DistanceSearch.DistanceQuery distanceQuery;
    private Handler handler;
    private BitmapDescriptor bitmapDescriptor1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routing);
        Resources res = getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.driver_icon);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bmp);
        Bitmap bmp1 = BitmapFactory.decodeResource(res, R.mipmap.score);
        bitmapDescriptor1 = BitmapDescriptorFactory.fromBitmap(bmp1);
        handler = new Handler();
        mContext = getApplicationContext();
        InitUI();
        mapView = findViewById(R.id.Routing_TextureMapView);
        mapView.getMap().moveCamera(CameraUpdateFactory.zoomTo(15));
        mapView.onCreate(savedInstanceState);
        aMap = mapView.getMap();
        // 设置定位监听
        aMap.setLocationSource(this);
        // 启用地图内置定位
        aMap.setMyLocationEnabled(true);
        aMap.setMyLocationStyle(
                new MyLocationStyle()
                        .interval(1000)
                        .myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE)
                        .myLocationIcon(bitmapDescriptor)
                        .strokeColor(Color.argb(0, 0, 0, 0))//设置定位蓝点精度圆圈的边框颜色的方法。
                        .radiusFillColor(Color.argb(0, 0, 0, 0))//设置定位蓝点精度圆圈的填充颜色的方法。
                        .strokeWidth(0)
        );

    }

    private void InitUI() {
        routeSearch = new RouteSearch(this);
        routeSearch.setRouteSearchListener(this);
        distanceSearch = new DistanceSearch(this);
        distanceSearch.setDistanceSearchListener(this);
        distanceQuery = new DistanceSearch.DistanceQuery();
        findViewById(R.id.tv_put_navigation).setOnClickListener(this);
    }


    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(this);
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null&&amapLocation != null) {
            if (amapLocation != null
                    &&amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点

            } else {
                String errText = "定位失败," + amapLocation.getErrorCode()+ ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr",errText);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_put_navigation:
                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(new LatLng(24.636125, 118.074093));
                markerOption.title("厦门北站").snippet("厦门北站");
                markerOption.draggable(false);//设置Marker可拖动
                markerOption.icon( bitmapDescriptor1);
                markerOption.visible(true);
                // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                markerOption.setFlat(false);//设置marker平贴地图效果


                LatLonPoint start = new LatLonPoint(24.475652, 118.118988);
                LatLonPoint dest = new LatLonPoint(24.636125, 118.074093);

           //设置起点和终点，其中起点支持多个
                List<LatLonPoint> latLonPoints = new ArrayList<LatLonPoint>();
                latLonPoints.add(start);
                distanceQuery.setOrigins(latLonPoints);
                distanceQuery.setDestination(dest);
          //设置测量方式，支持直线和驾车
                distanceQuery.setType(DistanceSearch.TYPE_DRIVING_DISTANCE);
                distanceSearch.calculateRouteDistanceAsyn(distanceQuery);

                //构建开始点与终止点的经纬度数据
                RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                        new LatLonPoint(24.475652, 118.118988),
                        new LatLonPoint(24.636125, 118.074093));
                RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo,1, null, null, "");
                routeSearch.calculateDriveRouteAsyn(query);
                break;
                default:
                    break;
        }
    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {
        Toast.makeText(mContext,"获取成功",Toast.LENGTH_SHORT).show();
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
        aMap.clear();
        aMap.addPolyline(new PolylineOptions()
                .addAll(driverPath)
                .width(20)
                //是否开启纹理贴图
                .setUseTexture(true)
                //绘制成大地线
                .geodesic(false)
                //设置纹理样式
//                .setCustomTexture(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.custtexture)))
                //设置画线的颜色
                .color(Color.argb(200, 0, 225, 12)));

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }

    @Override
    public void onDistanceSearched(DistanceResult distanceResult, int i) {
        List<DistanceItem> distanceResults = distanceResult.getDistanceResults();
        Log.e("返回路程",distanceResults.get(0).getDistance()+"米和"+distanceResults.get(0).getDuration()+"秒加"+distanceResults.size());
    }

    Runnable runnable =new Runnable() {
        @Override
        public void run() {

        }
    };

}
