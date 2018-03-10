package com.davis.sdj.api;


import com.davis.sdj.model.BigPictrue;
import com.davis.sdj.model.WeixinInfo;
import com.davis.sdj.model.basemodel.BaseModel;
import com.davis.sdj.model.basemodel.Page;
import com.davis.sdj.util.DownLoadSoftUpdate;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

//import retrofit.Call;
//import retrofit.http.Query;
//import retrofit.http.FormUrlEncoded;
//import retrofit.http.GET;
//import retrofit.http.POST;
//import retrofit.http.Query;

/**
 * Created by xusoku on 2016/4/5.
 */
public interface ApiService {


    public static String baseurl="http://";
    public static String picurl="http://";

    //http://www.tngou.net/tnfs/api/list?page=1&rows=10
//        @GET("tnfs/api/list")
//        Call<Grils> listGrils(@Query("id") int id,@Query("page") int page,@Query("rows") int rows);

//    @FormUrlEncoded
//    @POST("user/edit")
//    Call<User> getUser(@Query("name") String name, @Query("password") String password);

    //1、用户登陆
    @POST("user/login.do")
    Call<BaseModel> userLogin(
            @Query("apptype") String apptype,
            @Query("phone") String phone,
            @Query("password") String password);

    //2、用户 注册
    @POST("user/register.do")
    Call<BaseModel> userRegister(
            @Query("apptype") String apptype,
            @Query("phone") String phone,
            @Query("password") String password,
            @Query("code") String code);




}

