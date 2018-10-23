package com.davis.sdj.lottery;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.davis.lottery.R;
import com.davis.sdj.activity.base.BaseActivity;
import com.davis.sdj.adapter.recycleradapter.CommonRecyclerAdapter;
import com.davis.sdj.util.CombineUtil;

import java.util.ArrayList;
import java.util.List;

public class LotteryOrderActivity extends BaseActivity {

    private RecyclerView order_recycler;
    private CommonRecyclerAdapter adapter;
    private List<String> list;

    @Override
    protected int setLayoutView() {
        return R.layout.activity_lottery_order;
    }

    @Override
    protected void initVariable() {
        showTopBar();
        setTitle("双色球");
        getRightTextButton().setText("返回大厅");
    }

    @Override
    protected void findViews() {

        order_recycler=$(R.id.order_recycler);

    }

    @Override
    protected void initData() {

        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
        order_recycler.setLayoutManager(gridLayoutManager);
        list=new ArrayList<>();
        list.add("单式 03 04 07 08 09 10 11 [1注 2元]");
        list.add("胆拖 (10 22 24 ) 08 09 13 25 27 [5注 10元]");
        list.add("胆拖(10 22 24 ) 08 09 13 25 27 [5注 10元]");
        list.add("单式 03 04 07 08 09 10 11 [1注 2元]");
        list.add("单式 03 04 07 08 09 10 11 [1注 2元]");
        list.add("胆拖(10 22 24 ) 08 09 13 25 27 [5注 10元]");
        list.add("单式 03 04 07 08 09 10 11 [1注 2元]");
        list.add("胆拖(10 22 24 ) 08 09 13 25 27 [5注 10元]");
        list.add("单式 03 04 07 08 09 10 11 [1注 2元]");
        adapter=new CommonRecyclerAdapter<String>(this, list, R.layout.item_order) {
            @Override
            public void convert(BaseViewHolder holder, String itemData, final int position) {

                holder.setText(R.id.item_order_tv,itemData);

                holder.getView(R.id.item_order_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.remove(position);
                        notifyDataSetChanged();
                    }
                });
            }
        };
        order_recycler.setAdapter(adapter);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.order_one_random:

                ArrayList<Integer> addlistR=CombineUtil.getRandomList(6,33);
                ArrayList<Integer> addlistB=CombineUtil.getRandomList(1,16);

                String str=addlistR.toString()+ "  "+addlistB.toString();
                adapter.insert(0,str);
                order_recycler.smoothScrollBy(0,-100);

                break;
            case R.id.order_five_random:
                for (int i = 0; i < 5; i++) {
                    ArrayList<Integer> addlistRR=CombineUtil.getRandomList(6,33);
                    ArrayList<Integer> addlistBB=CombineUtil.getRandomList(1,16);

                    String strR=addlistRR.toString()+ "  "+addlistBB.toString();
                    adapter.insert(0,strR);
                    order_recycler.smoothScrollBy(0,-500);
                }
                break;
            case R.id.order_add_hand:
                break;
            case R.id.order_clear:
                break;
        }
    }
}
