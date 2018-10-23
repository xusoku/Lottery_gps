package com.davis.sdj.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.davis.sdj.AppApplication;
import com.davis.lottery.R;
import com.davis.sdj.activity.AboutActivity;
import com.davis.sdj.api.ApiService;
import com.davis.sdj.model.Banner;
import com.davis.sdj.util.DisplayMetricsUtils;
import com.davis.sdj.util.ToastUitl;
import com.davis.sdj.views.CustomAlterDialog;
import com.davis.sdj.views.CustomTypefaceEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ViewInitHelper {


    public static SpannableString getPrice(String str, int size1, int size2) {
        SpannableString spanString = new SpannableString(str);
        AbsoluteSizeSpan spanSzieLeft = new AbsoluteSizeSpan(
                (int) (size1 * DisplayMetricsUtils.getDensity()));
        spanString.setSpan(spanSzieLeft, 0, 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        AbsoluteSizeSpan spanSzieMiddle = new AbsoluteSizeSpan(
                (int) (size2 * DisplayMetricsUtils.getDensity()));
        spanString.setSpan(spanSzieMiddle, 1, spanString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }

//    public static SpannableString getTicketPrice(String str, int size1,
//                                                 int size2) {
//        SpannableString spanString = new SpannableString(QBaoApplication
//                .getApplication().getString(R.string.str_ticket_price, str));
//        AbsoluteSizeSpan spanSzieLeft = new AbsoluteSizeSpan(
//                (int) (size1 * DisplayMetricsUtils.getDensity()));
//        spanString.setSpan(spanSzieLeft, 0, 1,
//                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        AbsoluteSizeSpan spanSzieMiddle = new AbsoluteSizeSpan(
//                (int) (size2 * DisplayMetricsUtils.getDensity()));
//        spanString.setSpan(spanSzieMiddle, 1, str.length() + 1,
//                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        AbsoluteSizeSpan spanSzieRight = new AbsoluteSizeSpan(
//                (int) (size1 * DisplayMetricsUtils.getDensity()));
//        spanString.setSpan(spanSzieRight, spanString.length() - 1,
//                spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return spanString;
//    }

    public static SpannableString getTicketNum(String strLong, String strShort) {
        SpannableString spanString = new SpannableString(strLong);
        ForegroundColorSpan span = new ForegroundColorSpan(AppApplication
                .getApplication().getResources().getColor(R.color.white));
        spanString.setSpan(span, 1, strShort.length() + 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }

//    public static SpannableString getRebateString(String rebate) {
//        SpannableString spanString = new SpannableString(QBaoApplication
//                .getApplication()
//                .getString(R.string.str_qianbao_rebate, rebate));
//        ForegroundColorSpan span = new ForegroundColorSpan(QBaoApplication
//                .getApplication().getResources().getColor(R.color.color_ffcc18));
//        spanString.setSpan(span, 0, rebate.length(),
//                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return spanString;
//    }

    public static SpannableString getOldPrice(String str) {
        SpannableString spanString = new SpannableString(str);
        StrikethroughSpan span = new StrikethroughSpan();
        spanString.setSpan(span, 0, spanString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }

    public static SpannableString getOldPrice(String str, int size1, int size2) {
        SpannableString spanString = new SpannableString(str);
        StrikethroughSpan span = new StrikethroughSpan();
        spanString.setSpan(span, 0, spanString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        AbsoluteSizeSpan spanSzieLeft = new AbsoluteSizeSpan(
                (int) (size1 * DisplayMetricsUtils.getDensity()));
        spanString.setSpan(spanSzieLeft, 0, 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        AbsoluteSizeSpan spanSzieMiddle = new AbsoluteSizeSpan(
                (int) (size2 * DisplayMetricsUtils.getDensity()));
        spanString.setSpan(spanSzieMiddle, 1, spanString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }

    public static SpannableString getNewPrice(String str, int size1, int size2) {
        SpannableString spanString = new SpannableString(str);
        AbsoluteSizeSpan spanSzieLeft = new AbsoluteSizeSpan(
                (int) (size1 * DisplayMetricsUtils.getDensity()));
        spanString.setSpan(spanSzieLeft, 0, 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        AbsoluteSizeSpan spanSzieMiddle = new AbsoluteSizeSpan(
                (int) (size2 * DisplayMetricsUtils.getDensity()));
        spanString.setSpan(spanSzieMiddle, 1, spanString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }


    /**
     * 将一个List分组成多个list返回
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> ArrayList<ArrayList<T>> subLists(ArrayList<T> list) {
        ArrayList<ArrayList<T>> lists = new ArrayList<>();
        if (list != null && list.size() > 0) {
            int SIZE = 10;
            int page = (list.size() + SIZE - 1) / SIZE;
            ArrayList<T> newtimelist = null;
            for (int i = 0; i < page; i++) {
                newtimelist = new ArrayList<>();
                if (i == page - 1) {
                    newtimelist.addAll(list.subList(i * SIZE, list.size()));
                } else {
                    newtimelist.addAll(list.subList(i * SIZE, i * SIZE + SIZE));
                }
                lists.add(newtimelist);
            }
        }
        return lists;
    }

    /**
     * 将一个List分组成多个list返回
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> ArrayList<ArrayList<T>> subLists(ArrayList<T> list, int number) {
        ArrayList<ArrayList<T>> lists = new ArrayList<>();
        if (list != null && list.size() > 0) {
            int SIZE = number;
            int page = (list.size() + SIZE - 1) / SIZE;
            ArrayList<T> newtimelist = null;
            for (int i = 0; i < page; i++) {
                newtimelist = new ArrayList<>();
                if (i == page - 1) {
                    newtimelist.addAll(list.subList(i * SIZE, list.size()));
                } else {
                    newtimelist.addAll(list.subList(i * SIZE, i * SIZE + SIZE));
                }
                lists.add(newtimelist);
            }
        }
        return lists;
    }


    public static SpannableString getDiscountPrice(String str, int size1,
                                                   int size2) {
        SpannableString spanString = new SpannableString(str);
        AbsoluteSizeSpan spanSzieLeft = new AbsoluteSizeSpan(
                (int) (size1 * DisplayMetricsUtils.getDensity()));
        spanString.setSpan(spanSzieLeft, 0, 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        AbsoluteSizeSpan spanSzieMiddle = new AbsoluteSizeSpan(
                (int) (size2 * DisplayMetricsUtils.getDensity()));
        spanString.setSpan(spanSzieMiddle, 1, spanString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan span = new ForegroundColorSpan(AppApplication
                .getApplication().getResources().getColor(R.color.colormain));
        spanString.setSpan(span, 0, spanString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }

    public static SpannableString getCurrentPriceInDiscount(String str, int size1, int size2) {
        SpannableString spanString = new SpannableString(str);
        ForegroundColorSpan span = new ForegroundColorSpan(AppApplication
                .getApplication().getResources().getColor(R.color.colormain));
        spanString.setSpan(span, 0, spanString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        AbsoluteSizeSpan spanSzieLeft = new AbsoluteSizeSpan(
                (int) (size1 * DisplayMetricsUtils.getDensity()));
        spanString.setSpan(spanSzieLeft, 0, 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        AbsoluteSizeSpan spanSzieMiddle = new AbsoluteSizeSpan(
                (int) (size2 * DisplayMetricsUtils.getDensity()));
        spanString.setSpan(spanSzieMiddle, 1, spanString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }

    public static SpannableString getPriceInCinemaList(String str) {
        SpannableString spanString = new SpannableString(str);
        ForegroundColorSpan span = new ForegroundColorSpan(AppApplication
                .getApplication().getResources().getColor(R.color.colormain));
        spanString.setSpan(span, 4, spanString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }


    /**
     * 将指定的textview润色赋值
     *
     * @param tv
     * @param regionalStrings 要赋值的字符串数组
     * @param colors          对应的颜色数组
     * @param sizes           对应的字号数组
     */
    public static void initTextViewWithSpannableString(TextView tv,
                                                       String[] regionalStrings, String[] colors, String[] sizes) {
        tv.setText("");
        SpannableString[] spannableStrings = ViewInitHelper.getSpannableString(
                regionalStrings, colors, sizes);
        if (null == spannableStrings) {
            return;
        }
        for (SpannableString ss : spannableStrings) {
            tv.append(ss);
        }
    }

    /**
     * 获取SpannableString 注意：暂时未做边界检查，使用时请自行检查边界
     *
     * @param regionalStrings
     * @param colors
     * @return
     * @author baidonghui
     */
    public static SpannableString[] getSpannableString(
            String[] regionalStrings, String[] colors, String[] sizes) {
        if (regionalStrings.length == 0
                || sizes.length != regionalStrings.length
                || colors.length != regionalStrings.length)
            return null;

        SpannableString[] spanStrings = new SpannableString[regionalStrings.length];

        for (int i = 0; i < regionalStrings.length; i++) {
            spanStrings[i] = new SpannableString(regionalStrings[i]);
            ForegroundColorSpan fColorSpan = new ForegroundColorSpan(
                    Integer.valueOf(colors[i]));
            AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(
                    (int) (DisplayMetricsUtils.getDensity() * Integer
                            .valueOf(sizes[i])));
            spanStrings[i].setSpan(fColorSpan, 0, spanStrings[i].length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanStrings[i].setSpan(sizeSpan, 0, spanStrings[i].length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return spanStrings;
    }

    public static SpannableString getName(String cinemaName, String movie,
                                          int size1, int size2) {
        if (TextUtils.isEmpty(cinemaName)) {
            cinemaName = " ";
        }
        if (TextUtils.isEmpty(movie)) {
            movie = " ";
        }
        SpannableString spanString = new SpannableString(cinemaName + " "
                + movie);
        AbsoluteSizeSpan spanSzieLeft = new AbsoluteSizeSpan(
                (int) (size1 * DisplayMetricsUtils.getDensity()));
        spanString.setSpan(spanSzieLeft, 0, cinemaName.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        AbsoluteSizeSpan spanSzieMiddle = new AbsoluteSizeSpan(
                (int) (size2 * DisplayMetricsUtils.getDensity()));
        spanString.setSpan(spanSzieMiddle, cinemaName.length() + 1,
                spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }

    public static SpannableString getSecondHandMovieName(String movieName, String ticketAmount) {
        SpannableString spanString = new SpannableString(movieName + ticketAmount);
        AbsoluteSizeSpan spanSzieLeft = new AbsoluteSizeSpan(
                (int) (13 * DisplayMetricsUtils.getDensity()));
        spanString.setSpan(spanSzieLeft, movieName.length(), spanString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan span = new ForegroundColorSpan(AppApplication
                .getApplication().getResources().getColor(R.color.colorgray));
        spanString.setSpan(span, movieName.length(), spanString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }

    /**
     * 获取图片Spannable
     *
     * @param context
     * @param id      资源id
     * @return SpannableString
     * @author wujiajun
     */
    public static SpannableString getImageSpannable(Context context, int id) {
        String flag = "[state]";
        Drawable drawable = context.getResources().getDrawable(id);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        SpannableString spannable = new SpannableString(flag);
        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        spannable.setSpan(imageSpan, 0, flag.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    public static void callPhone(Context context, String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            ToastUitl.showToast("非法手机号");
            return;
        }
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                + phoneNumber));
        context.startActivity(intent);
    }

    public static int getIntFromString(String str, int defaultValue) {
        int value = defaultValue;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str.trim())) {
            try {
                value = Integer.valueOf(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public static long getLongFromString(String str, long defaultValue) {
        long value = defaultValue;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str.trim())) {
            try {
                value = Long.valueOf(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public static double getDoubleFromString(String str, double defaultValue) {
        double value = defaultValue;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str.trim())) {
            try {
                value = Double.valueOf(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }


    public static String getFormatMovieStartTime(String startTime) {
        StringBuffer sb = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat sf = new SimpleDateFormat("MM.dd");
        try {
            Date dateBegin = sdf.parse(startTime);// 通过日期格式的parse()方法将字符串转换成日期
            int dayOfWeek = dateBegin.getDay();
            String day = getStringDayOfWeek(dayOfWeek);
            sb.append(sf.format(dateBegin));
            sb.append("（").append(day).append("）");
        } catch (Exception e) {
        }
        return sb.toString();
    }

    private static String getStringDayOfWeek(int dayOfWeek) {
        String day = "";
        switch (dayOfWeek) {
            case 0:
                day = "日";
                break;
            case 1:
                day = "一";
                break;
            case 2:
                day = "二";
                break;
            case 3:
                day = "三";
                break;
            case 4:
                day = "四";
                break;
            case 5:
                day = "五";
                break;
            case 6:
                day = "六";
                break;
            default:
                break;
        }
        return day;
    }

//    public static String getformatConcertTime(String time) {
//        StringBuffer sb = new StringBuffer();
//        String[] dateAndTime = new String[2];
//        if (!TextUtils.isEmpty(time)) {
//            if (time.contains(" ")) {
//                dateAndTime = time.split(" ");
//            } else {
//                dateAndTime[0] = time;
//            }
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
//            try {
//                Date dateBegin = sdf.parse(dateAndTime[0]);// 通过日期格式的parse()方法将字符串转换成日期
//                int dayOfWeek = dateBegin.getDay();
//                String day = getStringDayOfWeek(dayOfWeek);
//                sb.append(dateAndTime[0]);
//                sb.append(" ").append(day).append(" ").append(dateAndTime[1]);
//            } catch (Exception e) {
//            }
//        }
//        return sb.toString();
//    }

    public static String getformatConcertTimeForSeatArea(String time) {
        StringBuffer sb = new StringBuffer();
        String[] dateAndTime = new String[2];
        if (!TextUtils.isEmpty(time)) {
            if (time.contains(" ")) {
                dateAndTime = time.split(" ");
            } else {
                dateAndTime[0] = time;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date dateBegin = sdf.parse(dateAndTime[0]);// 通过日期格式的parse()方法将字符串转换成日期
                int dayOfWeek = dateBegin.getDay();
                String day = getStringDayOfWeek(dayOfWeek);
                sb.append(dateAndTime[0]);
                sb.append(" ").append(day).append(" ").append(dateAndTime[1]);
            } catch (Exception e) {
            }
        }
        return sb.toString();
    }

    public static String getformatConcertTimeForSeatArea(long time) {
        StringBuffer sb = new StringBuffer();
        String[] dateAndTime = new String[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateBegin = new Date(time);// 通过日期格式的parse()方法将字符串转换成日期
            int dayOfWeek = dateBegin.getDay();
            String day = getStringDayOfWeek(dayOfWeek);
            sb.append(dateAndTime[0]);
            sb.append(" ").append(day).append(" ").append(dateAndTime[1]);
        } catch (Exception e) {
        }
        return sb.toString();
    }

    public static String getformatConcertTimeForPriceArea(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat dateSdf = new SimpleDateFormat("MM月dd日");
        SimpleDateFormat timeSdf = new SimpleDateFormat("HH:mm");
        StringBuffer sb = new StringBuffer();
        try {
            Date dateBegin = sdf.parse(time);// 通过日期格式的parse()方法将字符串转换成日期
            int dayOfWeek = dateBegin.getDay();
            String day = getStringDayOfWeek(dayOfWeek);
            String dateStr = dateSdf.format(dateBegin);
            String timeStr = timeSdf.format(dateBegin);
            sb.append(dateStr + "\n" + day + " " + timeStr);
        } catch (Exception e) {
        }
        return sb.toString();
    }

    public static String getformatConcertTimeForPriceArea(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat dateSdf = new SimpleDateFormat("MM月dd日");
        SimpleDateFormat timeSdf = new SimpleDateFormat("HH:mm");
        StringBuffer sb = new StringBuffer();
        try {
            Date dateBegin = new Date(time);// 通过日期格式的parse()方法将字符串转换成日期
            int dayOfWeek = dateBegin.getDay();
            String day = getStringDayOfWeek(dayOfWeek);
            String dateStr = dateSdf.format(dateBegin);
            String timeStr = timeSdf.format(dateBegin);
            sb.append(dateStr + "\n" + day + " " + timeStr);
        } catch (Exception e) {
        }
        return sb.toString();
    }


    public static String getTextFromEditText(EditText editText) {
        String str = "";
        if (!TextUtils.isEmpty(editText.getText())) {
            str = editText.getText().toString();
        }
        return str;
    }

    public static String getTextFromEditTextWithTrim(EditText editText) {
        String str = "";
        if (!TextUtils.isEmpty(editText.getText())) {
            str = editText.getText().toString().trim();
        }
        return str;
    }




}
