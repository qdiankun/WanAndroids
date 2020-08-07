package com.me.slone.wan.network.request;

import com.me.slone.wan.bean.Article;
import com.me.slone.wan.bean.BannerData;
import com.me.slone.wan.bean.Content;
import com.me.slone.wan.bean.MoreArticle;
import com.me.slone.wan.bean.Navi;
import com.me.slone.wan.bean.Tree;
import com.me.slone.wan.network.response.Response;
import com.me.slone.wan.network.response.ResponseCode;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Author：diankun
 * Time：20-7-13 下午3:38
 * Description: 封装请求接口
 */
public interface Request {

    String HOST = "https://www.wanandroid.com/";

    @GET("/banner/json")
    Observable<Response<List<BannerData>>> getBanner();


    /**
     * 置顶文章
     *
     * @return
     */
    @GET("/article/top/json")
    Observable<Response<List<Article>>> getTopArticle();


    /**
     * 更多文章
     *
     * @return
     */
    @GET("/article/list/{page}/json")
    Observable<Response<MoreArticle>> getArticle(@Path("page") int page);


    /**
     * 导航
     */
    @GET("/navi/json")
    Observable<Response<List<Navi>>> getNavi();


    /**
     * 体系数据
     */
    @GET("/tree/json")
    Observable<Response<List<Tree>>> getTrees();


    /**
     * 获取体系具体内容
     *
     * https://www.wanandroid.com/article/list/0/json?cid=60
     */
    @GET("article/list/{page}/json")
    Observable<Response<Content>> getContent(@Path("page") int page, @Query("cid") int childId);

}
