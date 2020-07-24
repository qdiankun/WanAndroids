package com.me.slone.wan.presenter;

import com.me.slone.wan.base.presenter.BasePresenter;
import com.me.slone.wan.bean.Tree;
import com.me.slone.wan.contract.TreeContract;
import com.me.slone.wan.network.NetworkManager;
import com.me.slone.wan.network.observer.ProgressSubscriber;
import com.me.slone.wan.network.response.ResponseTransformer;
import com.me.slone.wan.network.schedulers.RxSchedulersHelper;
import com.me.slone.wan.utils.KLog;

import java.util.List;

/**
 * Author：diankun
 * Time：20-7-23 下午5:06
 * Description:
 */
public class TreePresenter extends BasePresenter<TreeContract.View> implements TreeContract.Presenter {


    @Override
    public void getTreeData() {
        NetworkManager.getInstance()
                .getRequest()
                .getTrees()
                .compose(ResponseTransformer.handleResult())
                .compose(RxSchedulersHelper.applySchedulers())
                .subscribe(new ProgressSubscriber<List<Tree>>(mView.getContext(), false) {
                    @Override
                    public void success(List<Tree> treeList) {
                        KLog.i("treeList " + treeList.size());
                        mView.refreshTree(treeList);
                    }
                });
    }

}
