package com.me.slone.wan.contract;

import com.me.slone.wan.base.presenter.AbstractPresenter;
import com.me.slone.wan.base.view.AbstractView;
import com.me.slone.wan.bean.Tree;

import java.util.List;

/**
 * Author：diankun
 * Time：20-7-23 下午4:56
 * Description:
 */
public interface TreeContract {

    interface View extends AbstractView {

        boolean onFinishLoad();

        /**
         * 刷新体系数据
         */
        void refreshTree(List<Tree> trees);
    }


    interface Presenter extends AbstractPresenter<View> {

        /**
         * 获取体系数据
         */
        void getTreeData();
    }
}
