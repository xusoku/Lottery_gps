package com.davis.sdj.api;

import android.util.Base64;

import com.davis.sdj.util.LogUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Observable;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by davis on 16/5/17.
 */
public class ApiInstant {


    private static volatile ApiService service = null;
    //APP_KEY
    private static final String USER_NAME = "1MKttJVCxzWlILIK";
    //API_SECRET
    private static final String PASSWORD = "RDMySJwHP2wzc61PfQw8wLa6C12bClBVxKIt5dY5pnjaN5c4qNO0UmWWax9zJtj9";
    private static String base64EncodedCredentials = null;

    /**
     * 编码凭证
     */
    private static void getBase64EncodedCredentials() {
        String usernameAndPassword = USER_NAME + ":" + PASSWORD;
        base64EncodedCredentials = Base64.encodeToString(usernameAndPassword.getBytes(), Base64.NO_WRAP);
    }


    public static ApiService getInstant() {
        getBase64EncodedCredentials();
        if (service == null) {
            synchronized (ApiInstant.class) {
                if (service == null) {
                    Gson gson = new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                            .create();


//            OkHttpClient httpClient = new OkHttpClient();
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//            httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();

                    OkHttpClient httpClient = new OkHttpClient.Builder()
                            .addNetworkInterceptor(new Interceptor() {
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                    Request request = chain.request();//.newBuilder().addHeader("Authorization",base64EncodedCredentials).build();

                                    HttpUrl httpUrl = request.url()
                                            .newBuilder()
                                            // add common parameter
//                                            .addQueryParameter("token", "123")
//                                            .addQueryParameter("username", "xiaocai")
                                            .build();
                                    Request build = request.newBuilder()
                                            // add common header
                                            .addHeader("Authorization", "Basic "+base64EncodedCredentials)
                                            .url(httpUrl)
                                            .build();
                                    Response response = chain.proceed(build);

                                    LogUtils.e("url", response.request().url().toString());
                                    return response;
                                }
                            })
                            .build();


                    Retrofit retrofit = new Retrofit.Builder()
//                    .client(httpClient)
//                    .baseUrl(ApiService.baseurl)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build();

                            .baseUrl(ApiService.baseurl)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .client(httpClient)
                            .build();


                    service = retrofit.create(ApiService.class);
                }
            }
        }
        return service;
    };


}
