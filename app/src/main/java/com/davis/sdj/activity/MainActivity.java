package com.davis.sdj.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.davis.sdj.R;
import com.davis.sdj.activity.base.BaseActivity;
import com.davis.sdj.adapter.recycleradapter.CommonRecyclerAdapter;
import com.davis.sdj.util.CombineUtil;
import com.davis.sdj.util.DisplayMetricsUtils;
import com.davis.sdj.util.LogUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private CommonRecyclerAdapter adapter;
    ImageView main_iv_refreash;
    private ArrayList<Integer> list=new ArrayList<>();
    @Override
    protected int setLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void findViews() {
        recyclerView=$(R.id.main_recycler);
        main_iv_refreash=$(R.id.main_iv_refreash);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);





        getRandomList();
        adapter=new CommonRecyclerAdapter<Integer>(this,list,R.layout.mian_item_layout) {
            @Override
            public void convert(BaseViewHolder holder, Integer itemData, int position) {

                TextView textView=holder.getView(R.id.main_item_tv);
                itemData++;
                if(itemData<10){
                    textView.setText("0"+itemData+"");
                }else{

                    textView.setText(itemData+"");
                }
                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams((int) DisplayMetricsUtils.dp2px(40), (int) DisplayMetricsUtils.dp2px(40));
                if(position==6){
                    textView.setVisibility(View.INVISIBLE);
                    params.setMarginStart(0);
                    textView.setLayoutParams(params);
                }else{
                    scale(textView);
                    textView.setVisibility(View.VISIBLE);
                    params.setMarginStart(20);
                    textView.setLayoutParams(params);
                }
                if(position==7){
                    textView.setBackgroundResource(R.mipmap.blue);
                }else{
                    textView.setBackgroundResource(R.mipmap.red);
                }
            }
        };
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void initData() {




    }

    @Override
    protected void setListener() {

    }

    @Override
    public void doClick(View view) {

        switch (view.getId()){
            case R.id.main_iv_refreash:
            case R.id.main_tv_huan:
                getRandomList();
                adapter.notifyDataSetChanged();

                rotate(main_iv_refreash);
                break;
            case R.id.main_tv_five:
                break;
            case R.id.main_tv_one:
                break;
            case R.id.mian_ssq_linear:
                break;
            case R.id.mian_7lecai_linear:
                break;
            case R.id.mian_f3d_linear:
                break;
            case R.id.mian_shiwu_linear:
                break;
            case R.id.mian_kj_linear:
                break;
            case R.id.mian_zd_linear:
                break;
            case R.id.mian_smdj_linear:
                break;
            case R.id.mian_smyw_linear:
                break;
        }
    }


    public void rotate(ImageView imageView) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        imageView.setAnimation(rotateAnimation);
        rotateAnimation.setDuration(300);
        imageView.startAnimation(rotateAnimation);
    }
    public void scale(View imageView) {
        ScaleAnimation scaleAnimation=new ScaleAnimation(1f,0f,1f,0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(300);
        imageView.setAnimation(scaleAnimation);
        imageView.startAnimation(scaleAnimation);
    }

    private void getRandomList(){
        list.clear();
        list.addAll(CombineUtil.getRandomList(6,33));
        list.add(-1);
        list.addAll(CombineUtil.getRandomList(1,16));
        LogUtils.e(TAG,list.toString());
    }
}
