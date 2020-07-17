package com.me.slone.wan.presenter;

import com.me.slone.wan.base.presenter.BasePresenter;
import com.me.slone.wan.bean.Article;
import com.me.slone.wan.bean.BannerData;
import com.me.slone.wan.bean.MoreArticle;
import com.me.slone.wan.contract.HomeContract;
import com.me.slone.wan.network.NetworkManager;
import com.me.slone.wan.network.observer.ProgressSubscriber;
import com.me.slone.wan.network.response.Response;
import com.me.slone.wan.network.response.ResponseTransformer;
import com.me.slone.wan.network.schedulers.RxSchedulersHelper;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author：diankun
 * Time：20-7-17 下午4:34
 * Description:
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    @Override
    public int getCurrentPage() {
        return 0;
    }

    @Override
    public void getData() {

        Observable<Response<List<Article>>> topArticle = NetworkManager.getInstance()
                .getRequest()
                .getTopArticle();

        Observable<Response<MoreArticle>> article = NetworkManager.getInstance()
                .getRequest()
                .getArticle(0);

//        Observable.merge(topArticle,article)
//                .compose(ResponseTransformer.handleResult())
//                .compose(RxSchedulersHelper.applySchedulers())
//                .subscribe(new ProgressSubscriber<List<Article>>() {
//                    @Override
//                    public void success(List<Article> articles) {
//
//                    }
//                });
    }

    public void getMoreArticle(int page){
        NetworkManager.getInstance()
                .getRequest()
                .getArticle(page)
                .compose(ResponseTransformer.handleResult())
                .compose(RxSchedulersHelper.applySchedulers())
                .subscribe(new ProgressSubscriber<MoreArticle>(mView.getContext(),false) {
                    @Override
                    public void success(MoreArticle moreArticle) {
                        mView.refreshMoreArticle(moreArticle);
                    }
                });
    }

    public void getHomeTopArticle(){
        NetworkManager.getInstance()
                .getRequest()
                .getTopArticle()
                .compose(ResponseTransformer.handleResult())
                .compose(RxSchedulersHelper.applySchedulers())
                .subscribe(new ProgressSubscriber<List<Article>>(mView.getContext(),false) {
                    @Override
                    public void success(List<Article> articles) {
                        //KLog.i("article:"+articles);
                        mView.refreshTopArticle(articles);
                    }
                });
    }

    public void getHomeTopImgBanner() {
        NetworkManager.getInstance()
                .getRequest()
                .getBanner()
                .compose(ResponseTransformer.handleResult())
                .compose(RxSchedulersHelper.applySchedulers())
                .subscribe(new ProgressSubscriber<List<BannerData>>(mView.getContext(),true){

                    @Override
                    public void success(List<BannerData> bannerData) {
                        mView.refreshBanner(bannerData);
                    }

                    @Override
                    public void onHandledNetError(Throwable throwable) {
                        super.onHandledNetError(throwable);
                    }
                });
    }
}
