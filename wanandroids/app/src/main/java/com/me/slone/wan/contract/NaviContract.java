package com.me.slone.wan.contract;

import com.me.slone.wan.base.presenter.AbstractPresenter;
import com.me.slone.wan.base.view.AbstractView;
import com.me.slone.wan.bean.Navi;

import java.util.List;

/**
 * Author：diankun
 * Time：20-7-21 上午10:36
 * Description:
 */
public interface NaviContract {

    interface View extends AbstractView{

        boolean onFinishLoad();

        /**
         * 刷新导航数据
         * @param naviList
         */
        void refreshNavi(List<Navi> naviList);

    }

    interface Presenter extends AbstractPresenter<View>{

        /**
         * 获取导航数据
         */
        void getNaviData();

    }
}
