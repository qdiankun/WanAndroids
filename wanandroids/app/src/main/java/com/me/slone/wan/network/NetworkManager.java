package com.me.slone.wan.network;

import com.me.slone.wan.network.interceptor.LogInterceptor;
import com.me.slone.wan.network.request.Request;

import java.util.logging.Level;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author：diankun
 * Time：20-7-13 下午3:24
 * Description:
 */
public class NetworkManager {

    private static NetworkManager mInstance;
    private Request request;
    private Retrofit retrofit;

    private NetworkManager() {
    }

    public static NetworkManager getInstance() {
        if (mInstance == null) {
            synchronized (NetworkManager.class) {
                if (mInstance == null) {
                    mInstance = new NetworkManager();
                }
            }
        }
        return mInstance;
    }

    public void init() {
        //init okhttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LogInterceptor())
                .build();

        //init retrofity
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Request.HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public Request getRequest() {
        if (request == null) {
            request = retrofit.create(Request.class);
        }
        return request;
    }
}
