package com.me.slone.wan.base.view;

import android.content.Context;

/**
 * Author：diankun
 * Time：20-7-17 下午4:15
 * Description:
 */

public interface AbstractView {
    /**
     * Show error message
     *
     * @param errorMsg error message
     */
    void showErrorMsg(String errorMsg);

    /**
     * showNormal
     */
    void showNormal();

    /**
     * Show error
     */
    void showError();

    /**
     * Show loading
     */
    void showLoading();

    /**
     * Reload
     */
    void reload();

    /**
     * return context
     */
    Context getContext();
}
