package com.me.slone.wan.contract;

import com.me.slone.wan.base.presenter.AbstractPresenter;
import com.me.slone.wan.base.view.AbstractView;
import com.me.slone.wan.bean.Article;
import com.me.slone.wan.bean.BannerData;
import com.me.slone.wan.bean.MoreArticle;

import java.util.List;

/**
 * Author：diankun
 * Time：20-7-17 下午4:23
 * Description:
 */
public interface HomeContract  {

    interface  View extends AbstractView{


        boolean onFinishLoad();

        /**
         * refresh banner
         * @param bannerData
         */
        void refreshBanner(List<BannerData> bannerData);

        /**
         * 刷新置顶文章
         * @param topArticles
         */
        void refreshTopArticle(List<Article> topArticles);

        /**
         * 刷新文章
         * @param moreArticle
         */
        void refreshMoreArticle(MoreArticle moreArticle);

    }

    interface Presenter extends AbstractPresenter<View> {


        void getData(int page);

        /**
         * 获取更多文章列表o
         * @param page
         */
        void getMoreArticle(int page);

        /**
         * 获取置顶文章数据
         */
        void getHomeTopArticle();

        /**
         * 获取banner数据
         */
        void getHomeTopImgBanner();

    }

}
