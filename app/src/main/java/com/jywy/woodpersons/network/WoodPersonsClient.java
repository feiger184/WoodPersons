package com.jywy.woodpersons.network;

import com.jywy.woodpersons.network.core.WoodPersonsApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 高 on 2017/3/21.
 */

// 网络请求的客户端
public class WoodPersonsClient {

    public static final String BASE_URL = "http://dev.sxuav.com/index.php/Mcrapp/Index/";


    private static WoodPersonsClient woodPersonsClient;
    private final OkHttpClient mOkHttpClient;
    private final Retrofit retrofit;
    private WoodPersonsApi woodPersonsApi;

    public static synchronized WoodPersonsClient getInstance(){
        if (woodPersonsClient==null){
            woodPersonsClient = new WoodPersonsClient();
        }
        return woodPersonsClient;
    }

    private WoodPersonsClient() {

        // 日志拦截器的创建
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // OkHttpClient 的初始化
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        //初始化retrofit
          retrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public WoodPersonsApi getWoodPersonsApi() {
        if (woodPersonsApi == null) {
            woodPersonsApi = retrofit.create(WoodPersonsApi.class);
        }
        return woodPersonsApi;
    }



}
