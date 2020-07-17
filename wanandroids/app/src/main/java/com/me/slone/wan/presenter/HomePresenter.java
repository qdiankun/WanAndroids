package com.me.slone.wan.presenter;

import com.me.slone.wan.base.presenter.BasePresenter;
import com.me.slone.wan.bean.Article;
import com.me.slone.wan.bean.BannerData;
import com.me.slone.wan.bean.MoreArticle;
import com.me.slone.wan.contract.HomeContract;
import com.me.slone.wan.network.NetworkManager;
import com.me.slone.wan.network.observer.ProgressSubscriber;
import com.me.slone.wan.network.response.Response;
import com.me.slone.wan.network.response.ResponseCode;
import com.me.slone.wan.network.response.ResponseTransformer;
import com.me.slone.wan.network.schedulers.RxSchedulersHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
    public void getData(int page) {

        Observable<Response<List<Article>>> topObservable = NetworkManager.getInstance()
                .getRequest()
                .getTopArticle();

        Observable<Response<MoreArticle>> moreObservable = NetworkManager.getInstance()
                .getRequest()
                .getArticle(page);

        Observable<Response<List<BannerData>>> bannerObservable = NetworkManager.getInstance()
                .getRequest()
                .getBanner();

        Observable.merge(topObservable, moreObservable, bannerObservable)
                .compose(RxSchedulersHelper.applySchedulers())
                .subscribe(new Observer<Response<?>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<?> response) {
                        if (response.getErrorCode() == ResponseCode.OK_CODE) {
                            Object object = response.getData();
                            if (object instanceof MoreArticle) {

                            } else if (object instanceof List && ((List) object).size() > 0) {
                                if ((((List) object).get(0) instanceof BannerData)) {

                                } else if ((((List) object).get(0) instanceof Article)) {

                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getMoreArticle(int page) {
        NetworkManager.getInstance()
                .getRequest()
                .getArticle(page)
                .compose(ResponseTransformer.handleResult())
                .compose(RxSchedulersHelper.applySchedulers())
                .subscribe(new ProgressSubscriber<MoreArticle>(mView.getContext(), false) {
                    @Override
                    public void success(MoreArticle moreArticle) {
                        mView.refreshMoreArticle(moreArticle);
                    }
                });
    }

    @Override
    public void getHomeTopArticle() {
        NetworkManager.getInstance()
                .getRequest()
                .getTopArticle()
                .compose(ResponseTransformer.handleResult())
                .compose(RxSchedulersHelper.applySchedulers())
                .subscribe(new ProgressSubscriber<List<Article>>(mView.getContext(), false) {
                    @Override
                    public void success(List<Article> articles) {
                        //KLog.i("article:"+articles);
                        mView.refreshTopArticle(articles);
                    }
                });
    }

    @Override
    public void getHomeTopImgBanner() {
        NetworkManager.getInstance()
                .getRequest()
                .getBanner()
                .compose(ResponseTransformer.handleResult())
                .compose(RxSchedulersHelper.applySchedulers())
                .subscribe(new ProgressSubscriber<List<BannerData>>(mView.getContext(), true) {

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
