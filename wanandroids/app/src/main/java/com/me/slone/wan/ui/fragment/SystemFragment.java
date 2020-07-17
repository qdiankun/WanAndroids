package com.me.slone.wan.ui.fragment;

import com.me.slone.wan.R;
import com.me.slone.wan.base.MyFragment;

/**
 * Author：diankun
 * Time：20-7-14 下午3:52
 * Description: 体系
 */
public class SystemFragment extends MyFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_system;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }
}
