package com.me.slone.wan.network.request;

import com.me.slone.wan.bean.Banner;
import com.me.slone.wan.network.response.Response;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Author：diankun
 * Time：20-7-13 下午3:38
 * Description: 封装请求接口
 */
public interface Request {

    public static String HOST = "https://www.wanandroid.com/";

    @GET("/banner/json")
    Observable<Response<List<Banner>>> getBanner();

}
