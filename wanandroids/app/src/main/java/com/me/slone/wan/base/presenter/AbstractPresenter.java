package com.me.slone.wan.base.presenter;

import com.me.slone.wan.base.view.AbstractView;

import io.reactivex.disposables.Disposable;

/**
 * Author：diankun
 * Time：20-7-17 下午4:16
 * Description:
 */
public interface AbstractPresenter<T extends AbstractView> {


    /**
     * 注入View
     *
     * @param view view
     */
    void attachView(T view);

    /**
     * 回收View
     */
    void detachView();

    /**
     * Get current page
     *
     * @return current page
     */
    int getCurrentPage();



}
