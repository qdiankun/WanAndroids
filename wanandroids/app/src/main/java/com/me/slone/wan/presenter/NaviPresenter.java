package com.me.slone.wan.presenter;

import com.me.slone.wan.base.presenter.BasePresenter;
import com.me.slone.wan.bean.Navi;
import com.me.slone.wan.contract.NaviContract;
import com.me.slone.wan.network.NetworkManager;
import com.me.slone.wan.network.observer.ProgressSubscriber;
import com.me.slone.wan.network.response.ResponseTransformer;
import com.me.slone.wan.network.schedulers.RxSchedulersHelper;
import com.me.slone.wan.utils.KLog;

import java.util.List;

/**
 * Author：diankun
 * Time：20-7-21 上午10:50
 * Description:
 */
public class NaviPresenter extends BasePresenter<NaviContract.View> implements NaviContract.Presenter {

    @Override
    public void getNaviData() {
        NetworkManager.getInstance()
                .getRequest()
                .getNavi()
                .compose(ResponseTransformer.handleResult())
                .compose(RxSchedulersHelper.applySchedulers())
                .subscribe(new ProgressSubscriber<List<Navi>>(mView.getViewContext(), false) {
                    @Override
                    public void success(List<Navi> naviList) {
                        KLog.i("naviList " + naviList.size());
                        mView.refreshNavi(naviList);
                    }
                });
    }

}
