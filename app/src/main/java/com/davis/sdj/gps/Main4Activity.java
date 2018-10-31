package com.davis.sdj.gps;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.davis.lottery.R;
import com.davis.sdj.activity.LoginActivity;
import com.davis.sdj.util.ToastUitl;

public class Main4Activity extends BaiduBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawer;
    FloatingActionButton fab;
    NavigationView nav_view,nav_end_view;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main4, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            LoginActivity.jumpLoginActivity(this);
        } else if (id == R.id.nav_gallery) {
            ToastUitl.showToast("nav_gallery");
        } else if (id == R.id.nav_slideshow) {
            ToastUitl.showToast("nav_slideshow");
        } else if (id == R.id.nav_manage) {
            ToastUitl.showToast("nav_manage");
        } else if (id == R.id.nav_share) {
            ToastUitl.showToast("nav_share");
        } else if (id == R.id.nav_send) {
            ToastUitl.showToast("nav_send");
        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected int setLayoutView() {
        return R.layout.activity_main4;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void findViews() {
        super.findViews();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        nav_view = (NavigationView) findViewById(R.id.nav_view);


        nav_end_view = (NavigationView) findViewById(R.id.nav_end_view);


    }

    @Override
    protected void initData() {
        super.initData();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


    }

    @Override
    protected void setListener() {
        nav_end_view.setNavigationItemSelectedListener(this);
        nav_view.setNavigationItemSelectedListener(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public void doClick(View view) {

    }
}
