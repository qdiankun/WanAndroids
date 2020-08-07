package com.me.slone.wan.contract;

import com.me.slone.wan.base.presenter.AbstractPresenter;
import com.me.slone.wan.base.view.AbstractView;
import com.me.slone.wan.bean.Content;

/**
 * Author：diankun
 * Time：20-8-6 下午6:38
 * Description:
 */
public interface ContentContract {

    interface View extends AbstractView{

        boolean onFinishLoad();

        /**
         * 刷新内容
         * @param content
         */
        void refreshContent(Content content);
    }

    interface Presenter extends AbstractPresenter<View>{

        /**
         * 刷新内容数据
         */
        void getContentData(int page,int childId);

        /**
         * 获取个呢更多内容数据
         */
        void getMoreContent();

    }

}
