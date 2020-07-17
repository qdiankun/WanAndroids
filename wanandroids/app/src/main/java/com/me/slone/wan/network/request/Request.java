package com.me.slone.wan.network.request;

import com.me.slone.wan.bean.BannerData;
import com.me.slone.wan.network.response.Response;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Author：diankun
 * Time：20-7-13 下午3:38
 * Description: 封装请求接口
 */
public interface Request {

    public static String HOST = "https://www.wanandroid.com/";

    @GET("/banner/json")
    Observable<Response<List<BannerData>>> getBanner();

}
