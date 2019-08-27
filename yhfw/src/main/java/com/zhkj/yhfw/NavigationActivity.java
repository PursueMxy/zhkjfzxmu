package com.zhkj.yhfw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.AmapPageType;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;

public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        InitUI();
    }

    private void InitUI() {
        findViewById(R.id.btn_navigation_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Poi start = new Poi("三元桥", new LatLng(39.96087,116.45798), "");
/**终点传入的是北京站坐标,但是POI的ID "B000A83M61"对应的是北京西站，所以实际算路以北京西站作为终点**/
/**Poi支持传入经纬度和PoiID，PoiiD优先级更高，使用Poiid算路，导航终点会更合理**/
                Poi end = new Poi("北京站", new LatLng(39.904556, 116.427231), "B000A83M61");
                AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), new AmapNaviParams(start, null, end, AmapNaviType.DRIVER, AmapPageType.NAVI), naviInfoCallback);
            }
        });
    }
   INaviInfoCallback naviInfoCallback=new INaviInfoCallback() {
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
}
