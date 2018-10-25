package com.davis.sdj.gps;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.davis.lottery.R;
import com.davis.sdj.activity.base.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xushengfu on 2018/10/24.
 */

public abstract class BaiduBaseActivity extends BaseActivity {

    LatLng center;
    BitmapDescriptor bdC = BitmapDescriptorFactory.fromResource(R.mipmap.icon_gcoding);
    public MapView mMapView;

    public BaiduMap mBaiduMap;

    public Marker mMarkerC;


    protected abstract int setLayoutView();

    public void markOverly(String text) {
        InfoWindow mInfoWindow;
        //将marker所在的经纬度的信息转化成屏幕上的坐标
        final LatLng ll = mMarkerC.getPosition();
        //为弹出的InfoWindow添加点击事件
        InfoWindow.OnInfoWindowClickListener listener = new InfoWindow.OnInfoWindowClickListener() {
            public void onInfoWindowClick() {
                LatLng llNew = new LatLng(ll.latitude + 0.005,
                        ll.longitude + 0.005);
                mMarkerC.setPosition(llNew);
                mBaiduMap.hideInfoWindow();
            }
        };

        View view= LayoutInflater.from(this).inflate(R.layout.item_location_pop,null);
        mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(view), ll, -50, listener);
        mBaiduMap.showInfoWindow(mInfoWindow);
    }

    public void LocationFun(LatLng latLng) {
        MapStatus.Builder builder = new MapStatus.Builder();
        center = latLng;
        float zoom = 17.0f; // 默认 8级
        builder.target(center).zoom(zoom);
        MapStatusUpdate msu = MapStatusUpdateFactory.newMapStatus(builder.build());
        mBaiduMap.animateMapStatus(msu);

        MarkerOptions ooC = new MarkerOptions()
                .position(center)
                .icon(bdC)
                .scaleX(1.4f)
                .scaleY(1.4f)
                .perspective(false)
                .zIndex(7);
        // 生长动画
        ooC.animateType(MarkerOptions.MarkerAnimateType.grow);
        mMarkerC = (Marker) (mBaiduMap.addOverlay(ooC));

        markOverly("asdf");
    }

    protected abstract void findViews();

    protected abstract void initData();

    protected abstract void setListener();

    public abstract void doClick(View view);

    @Override
    public void onPause() {
        super.onPause();
        // activity 暂停时同时暂停地图控件
        mMapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // activity 恢复时同时恢复地图控件
        mMapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bdC.recycle();
        // 销毁地图前线关闭个性化地图，否则会出现资源无法释放
        MapView.setMapCustomEnable(false);
        // activity 销毁时同时销毁地图控件
        mMapView.onDestroy();
    }
}
