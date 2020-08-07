package com.me.slone.wan.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.me.slone.wan.R;
import com.me.slone.wan.base.MyFragment;
import com.me.slone.wan.bean.Article;
import com.me.slone.wan.bean.Children;
import com.me.slone.wan.bean.Content;
import com.me.slone.wan.common.Constants;
import com.me.slone.wan.contract.ContentContract;
import com.me.slone.wan.presenter.ContentPresenter;
import com.me.slone.wan.ui.activity.WebActivity;
import com.me.slone.wan.ui.adapter.ContentAdapter;
import com.me.slone.wan.utils.KLog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.content.ContentValues.TAG;

/**
 * Author：diankun
 * Time：20-7-24 下午4:58
 * Description:
 */
public class ContentFragment extends MyFragment implements ContentContract.View {

    @BindView(R.id.rv_content)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_content)
    SmartRefreshLayout mRefreshView;

    private ContentPresenter contentPresenter;
    private int mPage;
    private Children mChildren;
    private Content mContent;
    private List<Article> mArticleList;
    private ContentAdapter contentAdapter;
    public static String ARG_CHILD = "child";


    public static ContentFragment newInstance(Children children) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_CHILD, children);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_content;
    }

    @Override
    protected void initView() {
        mArticleList = new ArrayList<>();
        contentAdapter = new ContentAdapter(getContext());
        contentAdapter.setData(mArticleList);
        contentAdapter.setOnItemClickListener((recyclerView, itemView, position) -> {
            Article article = mArticleList.get(position);
            Intent intent = new Intent(mActivity, WebActivity.class);
            intent.putExtra(Constants.ARG_URL, article.getLink());
            mActivity.startActivity(intent);
        });
        mRecyclerView.setAdapter(contentAdapter);


        initRefreshView();
    }

    private void initRefreshView() {
        mRefreshView.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
        mRefreshView.setReboundDuration(600);//回弹动画时长（毫秒）
        mRefreshView.setEnableRefresh(true);//是否启用下拉刷新功能
        mRefreshView.setEnableLoadMore(true);
        mRefreshView.setOnRefreshListener(refreshLayout -> {
            mArticleList.clear();
            mPage = 0;
            contentPresenter.getContentData(mPage, mChildren.getId());
            if (onFinishLoad()) {
                mRefreshView.finishRefresh();
            }
        });
        mRefreshView.setOnLoadMoreListener(refreshLayout -> {
            mPage = mPage + 1;
            if (mPage > mContent.getPageCount()){
                Toast.makeText(mActivity, "已获完数据", Toast.LENGTH_SHORT).show();
                mRefreshView.finishLoadMore();
                return;
            }
            contentPresenter.getContentData(mPage, mChildren.getId());
            if (onFinishLoad()) {
                mRefreshView.finishLoadMore();
            }
        });
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            mChildren = (Children) arguments.getSerializable(ARG_CHILD);
        }
        if (mChildren == null) {
            return;
        }
        contentPresenter = new ContentPresenter();
        contentPresenter.attachView(this);
        contentPresenter.getContentData(mPage, mChildren.getId());
    }

    @Override
    public boolean onFinishLoad() {
        return true;
    }

    @Override
    public void refreshContent(Content content) {
        KLog.i(TAG, "content " + content);
        mContent = content;
        if (content == null
                || content.getDatas() == null
                || content.getDatas().isEmpty()) {
            return;
        }
        mArticleList.addAll(content.getDatas());
        contentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMsg(String errorMsg) {

    }

    @Override
    public void showNormal() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void reload() {

    }

    @Override
    public Context getViewContext() {
        return getContext();
    }
}
