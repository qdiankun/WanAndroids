package com.me.slone.wan.base.presenter;

import com.me.slone.wan.base.view.AbstractView;

/**
 * Author：diankun
 * Time：20-7-17 下午4:21
 * Description:
 */
public class BasePresenter<T extends AbstractView> implements AbstractPresenter<T>{

    protected T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if(mView!=null){
            mView = null;
        }
    }

    @Override
    public int getCurrentPage() {
        return 0;
    }
}
