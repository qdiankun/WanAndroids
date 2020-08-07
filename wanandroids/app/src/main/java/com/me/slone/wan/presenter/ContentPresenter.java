package com.me.slone.wan.presenter;

import com.me.slone.wan.base.presenter.BasePresenter;
import com.me.slone.wan.bean.Content;
import com.me.slone.wan.contract.ContentContract;
import com.me.slone.wan.network.NetworkManager;
import com.me.slone.wan.network.observer.ProgressSubscriber;
import com.me.slone.wan.network.response.ResponseTransformer;
import com.me.slone.wan.network.schedulers.RxSchedulersHelper;

/**
 * Author：diankun
 * Time：20-8-6 下午6:40
 * Description:
 */
public class ContentPresenter extends BasePresenter<ContentContract.View> implements ContentContract.Presenter {

    @Override
    public void getContentData(int page, int childId) {
        NetworkManager.getInstance()
                .getRequest()
                .getContent(page, childId)
                .compose(ResponseTransformer.handleResult())
                .compose(RxSchedulersHelper.applySchedulers())
                .subscribe(new ProgressSubscriber<Content>(mView.getViewContext(), false) {
                    @Override
                    public void success(Content content) {
                        mView.refreshContent(content);
                    }
                });
    }

    @Override
    public void getMoreContent() {

    }

    @Override
    public int getCurrentPage() {
        return 0;
    }
}
