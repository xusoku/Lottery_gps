package com.davis.sdj.util;

import com.davis.sdj.views.lotteryview.Ball;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by davis on 2018/3/11.
 */

public class CombineUtil {


    public static int getCount(List<Ball> list,int k) {
        CombineUtil combineUtil=new CombineUtil();
        return combineUtil.combine(0,k,list);
    }
    public static ArrayList<Integer> getRandomList(int n,int numCount) {
        CombineUtil combineUtil=new CombineUtil();
        return combineUtil.randomList(n,numCount);
    }

    public CombineUtil() {
        count = 0;
        tmpArr.clear();
        randomList.clear();
    }

    public ArrayList<Integer> randomList = new ArrayList<>();
    public ArrayList<String> tmpArr = new ArrayList<>();
    private int count;

    /**
     * 组合
     * 按一定的顺序取出元素，就是组合,元素个数[C arr.size 3]
     * @param index 元素位置
     * @param k 选取的元素个数
     * @param arr 数组
     */
    public int combine(int index, int k, List<Ball> arr) {
        if (k == 1) {
            for (int i = index; i < arr.size(); i++) {
                tmpArr.add(arr.get(i).getNumber());
                count++;
//                System.out.print(tmpArr.toString() + ",");
                tmpArr.remove(arr.get(i));
            }
        } else if (k > 1) {
            for (int i = index; i <= arr.size() - k; i++) {
                tmpArr.add(arr.get(i).getNumber()); //tmpArr都是临时性存储一下
                combine(i + 1, k - 1, arr); //索引右移，内部循环，自然排除已经选择的元素
                tmpArr.remove(arr.get(i)); //tmpArr因为是临时存储的，上一个组合找出后就该释放空间，存储下一个元素继续拼接组合了
            }
        } else {
            return count;
        }
        return count;
    }

    /**
     *
     * @param n 需要随机几个数
     * @param numCount 随机数的范围
     */
    public ArrayList<Integer> randomList(int n,int numCount){
        Random random = new Random();
        // 机选红球
        while (randomList.size() < n) {// 循环6次,产生6个球，开始为0
            int num = random.nextInt(numCount);// 33个球 下标0-32
            if (randomList.contains(num)) {
                continue;
            }
            randomList.add(num);
        }

        return randomList;
    }


}
