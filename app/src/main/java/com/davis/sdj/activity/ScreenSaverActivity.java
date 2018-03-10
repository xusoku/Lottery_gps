package com.davis.sdj.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.davis.sdj.AppApplication;
import com.davis.sdj.R;
import com.davis.sdj.adapter.base.ViewHolder;
import com.davis.sdj.api.ApiCallback;
import com.davis.sdj.api.ApiInstant;
import com.davis.sdj.model.BigPictrue;
import com.davis.sdj.model.basemodel.BaseModel;
import com.davis.sdj.views.loopbanner.LoopBanner;
import com.davis.sdj.views.loopbanner.LoopPageAdapter;

import java.util.ArrayList;

import retrofit2.Call;

public class ScreenSaverActivity extends Activity {

    private LoopBanner loopBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Random random=new Random();
//        int i=random.nextInt(2);
//        if(i==0){
//            Intent it=new Intent(this,VideoPlayerActivity.class);
//            startActivity(it);
//            finish();
//        }else{
//
//        }

        loopBanner = new LoopBanner(this);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT);
        loopBanner.setLayoutParams(params);
        setContentView(loopBanner);



    }

    private void bindView(ArrayList<String> str){

        loopBanner.setPageAdapter(new LoopPageAdapter<String>(this, str, R.layout.activity_screen_saver) {

            @Override
            public void convert(ViewHolder holder, final String itemData, final int position) {
                // TODO Auto-generated method stub
                ImageView imageView = (ImageView) holder.getConvertView();
                Glide.with(ScreenSaverActivity.this).load(itemData)
//                        .placeholder(R.mipmap.img_defualt_bg)
//                        .error(R.mipmap.img_defualt_bg)
                        .into(imageView);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
            }
        });
        loopBanner.startTurning(15000);
    }


    @Override
    public void onResume()
    {
        // TODO Auto-generated method stub
        super.onResume();
        loopBanner.startTurning(15000);
    }

    @Override
    public void onStop()
    {
        // TODO Auto-generated method stub
        super.onStop();
        loopBanner.stopTurning();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        loopBanner.stopTurning();
    }

}
