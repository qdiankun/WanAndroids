package com.me.slone.wan.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.hjq.base.action.BundleAction;
import com.me.slone.wan.R;
import com.me.slone.wan.base.MyFragment;
import com.me.slone.wan.bean.Tree;
import com.me.slone.wan.contract.TreeContract;
import com.me.slone.wan.presenter.TreePresenter;
import com.me.slone.wan.ui.activity.ArticleActivity;
import com.me.slone.wan.ui.adapter.TreeAdapter;
import com.me.slone.wan.ui.inter.TagTreeClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author：diankun
 * Time：20-7-20 下午4:54
 * Description:
 */
public class TreeFragment extends MyFragment implements TreeContract.View {

    @BindView(R.id.tree_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.tree_refresh)
    SmartRefreshLayout mRefreshView;

    private TreePresenter mTreePresenter;
    private List<Tree> mTreeList;
    private TreeAdapter mTreeAdapter;

    public static TreeFragment newInstance() {
        TreeFragment treeFragment = new TreeFragment();
        return treeFragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tree;
    }

    @Override
    protected void initView() {
        mTreeAdapter = new TreeAdapter(mActivity);
        mTreeList = new ArrayList<>();
        mTreeAdapter.setData(mTreeList);
        mTreeAdapter.setTagTreeClickListener(children -> {
            Intent intent = new Intent(mActivity,ArticleActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("childre",children);
            intent.putExtras(bundle);
            startActivity(intent);
        });
        mRecyclerView.setAdapter(mTreeAdapter);

        mRefreshView.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
        mRefreshView.setReboundDuration(600);//回弹动画时长（毫秒）
        mRefreshView.setEnableRefresh(true);//是否启用下拉刷新功能
        mRefreshView.setEnableLoadMore(false);
        mRefreshView.setOnRefreshListener(refreshLayout -> {
            mTreePresenter.getTreeData();
            if (onFinishLoad()) {
                mRefreshView.finishRefresh();
            }
        });
    }

    @Override
    protected void initData() {
        mTreePresenter = new TreePresenter();
        mTreePresenter.attachView(this);
        mTreePresenter.getTreeData();
    }

    @Override
    public boolean onFinishLoad() {
        return true;
    }

    @Override
    public void refreshTree(List<Tree> trees) {
        mTreeList.clear();
        if (trees != null && !trees.isEmpty()) {
            mTreeList.clear();
            mTreeList.addAll(trees);
        }
        mTreeAdapter.notifyDataSetChanged();
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
        showLoading();
    }

    @Override
    public void reload() {

    }
}
