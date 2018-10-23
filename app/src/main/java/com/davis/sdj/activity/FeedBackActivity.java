package com.davis.sdj.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.davis.lottery.R;
import com.davis.sdj.activity.base.BaseActivity;
import com.davis.sdj.api.ApiCallback;
import com.davis.sdj.api.ApiInstant;
import com.davis.sdj.model.basemodel.BaseModel;
import com.davis.sdj.util.ToastUitl;

import retrofit2.Call;

public class FeedBackActivity extends BaseActivity {

    private EditText feedback_et;
    private TextView feedback_tv;


    public static void jumpFeedBackActivity(Context cot){
        Intent it=new Intent(cot,FeedBackActivity.class);
        cot.startActivity(it);
    }
    @Override
    protected int setLayoutView() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void initVariable() {


    }

    @Override
    protected void findViews() {

        showTopBar();
        setTitle("问题反馈");
        feedback_et=$(R.id.feedback_et);
        feedback_tv=$(R.id.feedback_tv);
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
            case R.id.feedback_tv:
                String content=feedback_et.getText().toString().trim();
                if(TextUtils.isEmpty(content)){
                    ToastUitl.showToast("请填写反馈内容");
                    return;
                }
                break;
        }
    }
}
