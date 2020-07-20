package com.me.slone.wan.ui.fragment;

import androidx.recyclerview.widget.RecyclerView;

import com.me.slone.wan.R;
import com.me.slone.wan.base.MyFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;

/**
 * Author：diankun
 * Time：20-7-20 下午4:47
 * Description: 导航
 */
public class NaviFragment extends MyFragment {

    @BindView(R.id.tree_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.tree_refresh)
    SmartRefreshLayout mRefreshView;

    public static NaviFragment newInstance(){
        NaviFragment naviFragment = new NaviFragment();
        return naviFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navi;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
