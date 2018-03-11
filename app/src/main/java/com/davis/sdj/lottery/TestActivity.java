package com.davis.sdj.lottery;

import android.view.View;
import android.widget.TextView;

import com.davis.sdj.R;
import com.davis.sdj.activity.base.BaseActivity;
import com.davis.sdj.util.CombineUtil;
import com.davis.sdj.util.LogUtils;
import com.davis.sdj.util.ToastUitl;
import com.davis.sdj.views.lotteryview.SelectBallsView;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends BaseActivity {
    private SelectBallsView view1;
    private SelectBallsView view2;
    private SelectBallsView view3;
    private TextView zhushu;

    @Override
    protected int setLayoutView() {
        return R.layout.activity_test;
    }

    @Override
    protected void initVariable() {
    }

    @Override
    protected void findViews() {
        view1 = $(R.id.view_1);

        view2 = $(R.id.view_2);

        view3 = $(R.id.view_3);

        zhushu = $(R.id.zhushu);

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
            case  R.id.btn_confrim:
                String str1 = view1.getSelectBallsString();
                String str2 = view2.getSelectBallsString();
                String str3 = view3.getSelectBallsString();
                ToastUitl.showToast("选中的号码是:\n" + str1 + "\n" + str2 + "\n" + str3 );

                int countD=view1.getSelectBalls().size();
                int countT=view2.getSelectBalls().size();
                int countL=view3.getSelectBalls().size();

                int temp=CombineUtil.getCount(view2.getSelectBalls(),6-countD);
                int count=temp*countL;

                zhushu.setText(count+"注");
                break;
            case R.id.btn_Random:
                ArrayList<Integer> list= CombineUtil.getRandomList(7,view1.getBallsSize());
                LogUtils.e(TAG,list.toString());

                List<Integer> list1=list.subList(0,5);
                LogUtils.e(TAG,list1.toString());
                List<Integer> list2=list.subList(5,7);
                LogUtils.e(TAG,list2.toString());
                view1.setBallNumber(list1);
                view2.setBallNumber(list2);
                view3.setBallRandomNumber(1);


                break;
        }
    }
}
