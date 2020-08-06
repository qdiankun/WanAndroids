package com.me.slone.wan.contract;

import com.me.slone.wan.base.presenter.AbstractPresenter;
import com.me.slone.wan.base.view.AbstractView;

/**
 * Author：diankun
 * Time：20-8-6 下午6:38
 * Description:
 */
public interface ContentContract {

    interface View extends AbstractView{

        boolean onFinishLoad();

    }

    interface Presenter extends AbstractPresenter<View>{

        /**
         * 刷新内容数据
         */
        void getContentData(int page);

        /**
         * 获取个呢更多内容数据
         */
        void getMoreContent();

    }

}
