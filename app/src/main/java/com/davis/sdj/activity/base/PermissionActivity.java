package com.davis.sdj.activity.base;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.davis.sdj.util.PermissionPool;

/**
 * Created by xushengfu on 2017/8/6.
 */

public class PermissionActivity extends AppCompatActivity{

    /**
     * android6.0权限处理
     * @param code             权限标记Code
     * @param permissionName    权限名称
     */
    public void permissionDispose(@PermissionPool.PermissionCode int code, @PermissionPool.PermissionName String permissionName){

        if(ContextCompat.checkSelfPermission(this, permissionName)!= PackageManager.PERMISSION_GRANTED){
            //没有权限,开始申请
            ActivityCompat.requestPermissions(this,new String[]{permissionName},code);
        }else{
            //有权限
            onAccreditSucceed(code);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
            //授权成功
            onAccreditSucceed(requestCode);
        }else if(grantResults[0]==PackageManager.PERMISSION_DENIED){
            //授权失败
            onAccreditFailure (requestCode);
        }
    }

    /**
     * 有授权执行的方法(子类重写)
     */
    public void onAccreditSucceed(int requestCode) {
    }

    /**
     * 没有授权执行的方法(子类重写)
     */
    public void onAccreditFailure(int requestCode) {
    }
}
