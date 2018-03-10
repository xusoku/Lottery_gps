package com.davis.sdj.lottery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.davis.sdj.R;
import com.davis.sdj.activity.base.BaseActivity;
import com.davis.sdj.util.ToastUitl;
import com.davis.sdj.views.lotteryview.SelectBallsView;
import com.google.zxing.client.android.CaptureActivity;

public class LotteryHallActivity extends BaseActivity {
    private SelectBallsView view1;
    private SelectBallsView view2;
    private SelectBallsView view3;
    private SelectBallsView view4;
    private SelectBallsView view5;

    @Override
    protected int setLayoutView() {
        return R.layout.activity_lottery_hall;
    }

    @Override
    protected void initVariable() {
    }

    @Override
    protected void findViews() {
        view1 = $(R.id.view_1);

        view2 = $(R.id.view_2);

        view3 = $(R.id.view_3);

        view4 = $(R.id.view_4);

        view5 =$(R.id.view_5);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {



    }
    public  int combination(int m,int n){
        int k = 1;
        int j = 1;
        for(int i = n; i>=1;i--){
            k = k*m;
            j = j*n;
            m--;
            n--;
        }
        return k/j;
    }
    @Override
    public void doClick(View view) {

        switch (view.getId()){
           case  R.id.btn:
               view1.setShowMissValue(!view1.getShowMissValue());
               view2.setShowMissValue(!view2.getShowMissValue());
               view3.setShowMissValue(!view3.getShowMissValue());
               view4.setShowMissValue(!view4.getShowMissValue());
               view5.setShowMissValue(!view5.getShowMissValue());
            break;
           case  R.id.btn_confrim:
               String str1 = view1.getSelectBallsString();
               String str2 = view2.getSelectBallsString();
               String str3 = view3.getSelectBallsString();
               String str4 = view4.getSelectBallsString();
               String str5 = view5.getSelectBallsString();
               ToastUitl.showToast("选中的号码是:\n" + str1 + "\n" + str2 + "\n" + str3 + "\n" + str4 + "\n" + str5);
               break;
            case R.id.btn_Random:
//                view1.setBallRandomNumber(1);
                view2.setBallRandomNumber(6);
                break;
        }
    }
}
