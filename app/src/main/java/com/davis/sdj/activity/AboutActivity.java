package com.davis.sdj.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.davis.lottery.R;
import com.davis.sdj.activity.base.BaseActivity;
import com.davis.sdj.api.ApiService;
import com.davis.sdj.views.XWebView;

public class AboutActivity extends BaseActivity {

    private XWebView about_xweb;

    String url="";
    String title="";
    public static void jumpAboutActivity(Context cot, String url,String title) {
        Intent it = new Intent(cot, AboutActivity.class);
        it.putExtra("url",url);
        it.putExtra("title",title);
        cot.startActivity(it);
    }

    @Override
    protected int setLayoutView() {
        return R.layout.activity_about;
    }

    @Override
    protected void initVariable() {

        url=getIntent().getStringExtra("url");
        title=getIntent().getStringExtra("title");

    }

    @Override
    protected void findViews() {

        showTopBar();
        about_xweb=$(R.id.about_xweb);
        setTitle(title);
        about_xweb.loadUrl(url);


        about_xweb = $(R.id.about_xweb);

    }

    @Override
    protected void initData() {


    }

    @Override
    protected void setListener() {

    }

    @Override
    public void doClick(View view) {

    }
}
