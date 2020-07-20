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
import com.me.slone.wan.utils.KLog;

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
    public void getRefreshData(int page) {

        mView.showLoading();

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
                                KLog.i("moreArticle :"+object);
                                mView.refreshMoreArticle((MoreArticle) object);
                                return;
                            }
                            if (object instanceof List && ((List) object).size() > 0) {
                                if ((((List) object).get(0) instanceof BannerData)) {
                                    KLog.i("banner::"+object);
                                    mView.refreshBanner((List<BannerData>) object);
                                } else if ((((List) object).get(0) instanceof Article)) {
                                    KLog.i("article::"+object);
                                    mView.refreshTopArticle((List<Article>) object);
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError();
                    }

                    @Override
                    public void onComplete() {
                        mView.showNormal();
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

}
