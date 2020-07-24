package com.me.slone.wan.ui.fragment;

import androidx.recyclerview.widget.RecyclerView;

import com.me.slone.wan.R;
import com.me.slone.wan.base.MyFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;

/**
 * Author：diankun
 * Time：20-7-24 下午4:58
 * Description:
 */
public class ContentFragment extends MyFragment {

    @BindView(R.id.rv_content)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_content)
    SmartRefreshLayout mRefreshView;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_content;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
