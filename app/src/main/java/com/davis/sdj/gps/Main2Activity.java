package com.davis.sdj.gps;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.davis.lottery.R;
import com.davis.sdj.activity.base.BaseActivity;
import com.davis.sdj.util.LogUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends BaiduBaseActivity {

    private DrawerLayout drawer_layout;
    private Button btn_start, btn_end;

    @Override
    protected int setLayoutView() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initVariable() {

    }


    @Override
    protected void findViews() {

        drawer_layout = $(R.id.drawer_layout);
        btn_start = $(R.id.btn_start);
        btn_end = $(R.id.btn_end);

        mMapView = (MapView) findViewById(R.id.map);
        mBaiduMap = mMapView.getMap();
    }

    @Override
    protected void initData() {

        LocationFun(new LatLng(31.081981, 121.528337)); // 默认  智慧园);
    }

    @Override
    protected void setListener() {
    }

    @Override
    public void doClick(View view) {

        switch (view.getId()) {
            case R.id.btn_start:
                drawer_layout.openDrawer(Gravity.LEFT);
                break;
            case R.id.btn_end:
                drawer_layout.openDrawer(Gravity.END);
                break;
        }
    }

}
