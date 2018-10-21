package com.davis.sdj;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.davis.sdj.activity.LoginActivity;
import com.davis.sdj.util.SharePreferenceUtils;
import com.davis.sdj.util.WeiXinUtil;
import com.tencent.mm.sdk.openapi.IWXAPI;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by davis on 16/5/18.
 */
public class AppApplication extends MultiDexApplication {

    /**
     * 全局context单例
     *
     */
    private static AppApplication instance  = null;

    public static String token = "";

    public static IWXAPI wxApi;

    public static AppApplication getApplication() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        instance
        instance = (AppApplication) getApplicationContext();

        token=SharePreferenceUtils.getSharedPreferences().getString("token","");


        wxApi = WeiXinUtil.WXInit(getApplicationContext());

        ShareSDK.initSDK(this);

        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

    }

    public static boolean isLogin(Context context) {
         token=SharePreferenceUtils.getSharedPreferences().getString("token","");
        if (TextUtils.isEmpty(token)) {
            LoginActivity.jumpLoginActivity(instance);
            return false;
        } else {
            return  true;
        }
    }

}
