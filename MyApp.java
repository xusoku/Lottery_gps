package com.davis.activitylifecyclecallbacks;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by xushengfu on 2017/12/15.
 */

public class MyApp extends android.app.Application {
    private int activityCount;//activity的count数
    private boolean isForeground;//是否在前台

    @Override
    public void onCreate() {
        super.onCreate();

//        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
//            @Override
//            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
//                Log.e("aaxxoo", "onActivityCreated="+isForeground);
//            }
//
//            @Override
//            public void onActivityStarted(Activity activity) {
//                activityCount++;
//                Log.e("aaxxoo", "onActivityStarted="+isForeground);
//            }
//
//            @Override
//            public void onActivityResumed(Activity activity) {
//                Log.e("aaxxoo", "onActivityResumed="+isForeground);
//            }
//
//            @Override
//            public void onActivityPaused(Activity activity) {
//                Log.e("aaxxoo", "onActivityPaused="+isForeground);
//            }
//
//            @Override
//            public void onActivityStopped(Activity activity) {
//                activityCount--;
//                if (0 == activityCount) {
//                    isForeground = false;
//                }
//
//                Log.e("aaxxoo", "onActivityStopped="+isForeground);
//            }
//
//            @Override
//            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
//                Log.e("aaxxoo", "onActivitySaveInstanceState="+isForeground);
//            }
//
//            @Override
//            public void onActivityDestroyed(Activity activity) {
//                Log.e("aaxxoo", "onActivityDestroyed="+isForeground);
//            }
//        });
    }
}