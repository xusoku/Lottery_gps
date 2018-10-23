package com.davis.sdj.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.davis.lottery.R;
import com.davis.sdj.AppApplication;
import com.davis.sdj.activity.base.BaseActivity;
import com.davis.sdj.api.ApiService;
import com.davis.sdj.util.DownLoadSoftUpdate;
import com.davis.sdj.util.ShareManager;
import com.davis.sdj.util.SharePreferenceUtils;
import com.davis.sdj.views.CustomDialog;

import de.greenrobot.event.EventBus;

public class SettingActivity extends BaseActivity {


    private CustomDialog dialog;

    public static void jumpSettingActivity(Context cot) {
        Intent it = new Intent(cot, SettingActivity.class);
        cot.startActivity(it);
    }

    @Override
    protected int setLayoutView() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void findViews() {

        showTopBar();
        setTitle("设置");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {


    }

    @Override
    public void doClick(View view) {

        if (view.getId() == R.id.setting_logout) {

            AppApplication.token = "";
            SharePreferenceUtils.getSharedPreferences().putString("nickname", "");
            SharePreferenceUtils.getSharedPreferences().putString("token", "");
            LoginActivity.jumpLoginActivity(this);
            EventBus.getDefault().post("loginout");
            finish();


        } else if (view.getId() == R.id.setting_about)

        {
            AboutActivity.jumpAboutActivity(this, ApiService.baseurl + "/common/html.do?type=about&apptype=android", "关于我们");
        } else if (view.getId() == R.id.fix_pass)

        {
            AboutActivity.jumpAboutActivity(this, ApiService.baseurl + "/common/html.do?type=updatepwd&apptype=android&token=", "修改密码");

        } else if (view.getId() == R.id.forget_pass)

        {
            AboutActivity.jumpAboutActivity(this, ApiService.baseurl + "/common/html.do?type=forgetpwd&apptype=android", "忘记密码");

        } else if (view.getId() == R.id.setting_share)

        {
            ShareManager mShareManager = new ShareManager(mActivity);
            mShareManager.setTitle("食当家-家门口的生鲜店");
            mShareManager.setText("家门口的食当家又有好赞的生鲜啦，我猜你肯定喜欢，快来戳我啊~");
            mShareManager.setWebUrl(ApiService.baseurl+"/common/html.do?type=shareurl");
            mShareManager.setTitleUrl(ApiService.baseurl+"/common/html.do?type=shareurl");
            mShareManager.setImageUrl("http://img.shidangjia.com/images/logo.jpg");
            mShareManager.showShareDialog(this);

        } else if (view.getId() == R.id.setting_update)

        {
            new DownLoadSoftUpdate(this).checkVersionThread(true);
        }
    }
}
