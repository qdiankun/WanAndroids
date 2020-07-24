package com.me.slone.wan.ui.fragment;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.me.slone.wan.R;
import com.me.slone.wan.base.MyFragment;
import com.me.slone.wan.bean.Article;
import com.me.slone.wan.bean.Navi;
import com.me.slone.wan.common.Constants;
import com.me.slone.wan.contract.NaviContract;
import com.me.slone.wan.presenter.NaviPresenter;
import com.me.slone.wan.ui.activity.WebActivity;
import com.me.slone.wan.ui.adapter.Nav2Adapter;
import com.me.slone.wan.ui.adapter.NaviAdapter;
import com.me.slone.wan.ui.inter.TagClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author：diankun
 * Time：20-7-20 下午4:47
 * Description: 导航
 */
public class NaviFragment extends MyFragment implements NaviContract.View {

    @BindView(R.id.tree_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.tree_refresh)
    SmartRefreshLayout mRefreshView;

    private NaviAdapter mAdapter;
    private Nav2Adapter mNav2Adapter;
    private List<Navi> mList;
    private NaviPresenter mNaviPresenter;

    public static NaviFragment newInstance() {
        NaviFragment naviFragment = new NaviFragment();
        return naviFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navi;
    }

    @Override
    protected void initView() {
        mAdapter = new NaviAdapter(mActivity);
        mList = new ArrayList<>();
        mAdapter.setData(mList);
        mAdapter.setTagClickListener(link -> {
        Intent intent = new Intent(mActivity, WebActivity.class);
        intent.putExtra(Constants.ARG_URL,link);
        mActivity.startActivity(intent);
        });

        mNav2Adapter = new Nav2Adapter(R.layout.rv_tree_item,mList);
        mRecyclerView.setAdapter(mAdapter);

        mRefreshView.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
        mRefreshView.setReboundDuration(600);//回弹动画时长（毫秒）
        mRefreshView.setEnableRefresh(true);//是否启用下拉刷新功能
        mRefreshView.setEnableLoadMore(false);
        mRefreshView.setOnRefreshListener(refreshLayout -> {
            mNaviPresenter.getNaviData();
            if (onFinishLoad()) {
                mRefreshView.finishRefresh();
            }
        });
    }


    @Override
    public boolean onFinishLoad() {
        return true;
    }

    @Override
    protected void initData() {
        mNaviPresenter = new NaviPresenter();
        mNaviPresenter.attachView(this);
        mNaviPresenter.getNaviData();
    }

    @Override
    public void refreshNavi(List<Navi> naviList) {
        mList.clear();
        if (naviList != null && !naviList.isEmpty()) {
            mList.addAll(naviList);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        hideDialog();
    }

    @Override
    public void showNormal() {
        hideDialog();
    }

    @Override
    public void showError() {
        hideDialog();
    }

    @Override
    public void showLoading() {
        hideDialog();
    }

    @Override
    public void reload() {

    }
}
