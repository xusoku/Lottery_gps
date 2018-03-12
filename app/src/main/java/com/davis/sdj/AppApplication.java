package com.davis.sdj;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.amap.api.services.core.PoiItem;
import com.davis.sdj.activity.LoginActivity;
import com.davis.sdj.util.SharePreferenceUtils;
import com.davis.sdj.util.WeiXinUtil;
import com.tencent.mm.sdk.openapi.IWXAPI;

import java.util.ArrayList;

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


    public static PoiItem poiItem;


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
