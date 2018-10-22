package com.davis.sdj.gps;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.davis.sdj.R;
import com.davis.sdj.activity.base.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main2Activity extends BaseActivity {

    LatLng center;
    BitmapDescriptor bdC = BitmapDescriptorFactory.fromResource(R.mipmap.logo_icon);
    private DrawerLayout drawer_layout;
    private Button btn_start, btn_end;
    private MapView mMapView;

    private BaiduMap mBaiduMap;

    private boolean mEnableCustomStyle = true;


    //用于设置个性化地图的样式文件
    private static final String CUSTOM_FILE_NAME = "custom_config_dark.json";

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

        MapStatus.Builder builder = new MapStatus.Builder();
         center = new LatLng(31.081981, 121.528337); // 默认  智慧园
        float zoom = 17.0f; // 默认 8级

        /* 该Intent是OfflineDemo中查看离线地图调起的 */
//        Intent intent = getIntent();
//        if (null != intent) {
//            mEnableCustomStyle = intent.getBooleanExtra("customStyle", true);
//            center = new LatLng(intent.getDoubleExtra("y", 39.915071),
//                    intent.getDoubleExtra("x", 116.403907));
//            zoom = intent.getFloatExtra("level", 11.0f);
//        }
        builder.target(center).zoom(zoom);
        MapStatusUpdate msu = MapStatusUpdateFactory.newMapStatus(builder.build());
        mBaiduMap.animateMapStatus(msu);

        /**
         * MapView (TextureMapView)的
         * {@link MapView.setCustomMapStylePath(String customMapStylePath)}
         * 方法一定要在MapView(TextureMapView)创建之前调用。
         * 如果是setContentView方法通过布局加载MapView(TextureMapView), 那么一定要放置在
         * MapView.setCustomMapStylePath方法之后执行，否则个性化地图不会显示
         */
//        setMapCustomFile(this, CUSTOM_FILE_NAME);

//        mMapView = new MapView(this, new BaiduMapOptions());
//        MapView.setMapCustomEnable(false);

        MapView.setMapCustomEnable(false);
    }

    @Override
    protected void setListener() {
    }

    @Override
    public void doClick(View view) {

        switch (view.getId()) {
            case R.id.btn_start:
//                drawer_layout.openDrawer(Gravity.START);
                MarkerOptions ooC = new MarkerOptions()
                        .position(center)
                        .icon(bdC)
                        .perspective(false)
                        .anchor(0.5f, 0.5f)
                        .zIndex(7);
                    // 生长动画
                    ooC.animateType(MarkerOptions.MarkerAnimateType.grow);
               Marker mMarkerC = (Marker) (mBaiduMap.addOverlay(ooC));

                break;
            case R.id.btn_end:
//                drawer_layout.openDrawer(Gravity.END);
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
        }
    }

    // 设置个性化地图config文件路径
    private void setMapCustomFile(Context context, String PATH) {
        FileOutputStream out = null;
        InputStream inputStream = null;
        String moduleName = null;

        try {
            inputStream = context.getAssets().open("customConfigdir/" + PATH);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);

            moduleName = context.getFilesDir().getAbsolutePath();
            File f = new File(moduleName + "/" + PATH);
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();

            out = new FileOutputStream(f);
            out.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        MapView.setCustomMapStylePath(moduleName + "/" + PATH);

    }

    /**
     * 设置个性化icon
     *
     * @param context
     * @param icon_themeId
     */
    private void setIconCustom(Context context, int icon_themeId) {

        MapView.setIconCustom(icon_themeId);
    }

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
        // 销毁地图前线关闭个性化地图，否则会出现资源无法释放
        MapView.setMapCustomEnable(false);
        // activity 销毁时同时销毁地图控件
        mMapView.onDestroy();
    }
}
