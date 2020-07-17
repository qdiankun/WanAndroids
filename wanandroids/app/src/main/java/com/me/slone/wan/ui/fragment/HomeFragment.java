package com.me.slone.wan.ui.fragment;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.me.slone.wan.R;
import com.me.slone.wan.base.MyFragment;
import com.me.slone.wan.bean.Article;
import com.me.slone.wan.bean.BannerData;
import com.me.slone.wan.bean.MoreArticle;
import com.me.slone.wan.common.Constants;
import com.me.slone.wan.contract.HomeContract;
import com.me.slone.wan.presenter.HomePresenter;
import com.me.slone.wan.ui.activity.WebActivity;
import com.me.slone.wan.ui.adapter.ArticleAdapter;
import com.me.slone.wan.utils.GlideImageLoader;
import com.ms.banner.Banner;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author：diankun
 * Time：20-7-14 下午3:51
 * Description: 首页
 */
public class HomeFragment extends MyFragment implements HomeContract.View {

    @BindView(R.id.main_banner)
    Banner mBanner;
    @BindView(R.id.main_rv_top_article)
    RecyclerView mTopRecyclerView;
    @BindView(R.id.main_rv_more_article)
    RecyclerView mMoreRecyclerView;
    @BindView(R.id.refresh_home)
    SmartRefreshLayout mRefreshView;

    private int page;
    private ArticleAdapter mTopAdapter;
    private List<Article> mTopList;
    private ArticleAdapter mMoreAdapter;
    private List<Article> mMoreList;
    private List<BannerData> mBannerData;

    private HomePresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        mBanner.setOnBannerClickListener(position -> {
            BannerData banner = mBannerData.get(position);
            Intent intent = new Intent(mActivity, WebActivity.class);
            intent.putExtra(Constants.ARG_URL,banner.getUrl());
            mActivity.startActivity(intent);
        });

        mTopAdapter = new ArticleAdapter(mActivity);
        mTopList = new ArrayList<>();
        mTopAdapter.setData(mTopList);
        mTopAdapter.setOnItemClickListener((recyclerView, itemView, position) -> {
            Article article = mTopList.get(position);
            Intent intent = new Intent(mActivity, WebActivity.class);
            intent.putExtra(Constants.ARG_URL,article.getLink());
            mActivity.startActivity(intent);
        });
        mTopRecyclerView.setNestedScrollingEnabled(false);
        mTopRecyclerView.setAdapter(mTopAdapter);

        mMoreAdapter = new ArticleAdapter(mActivity);
        mMoreList = new ArrayList<>();
        mMoreAdapter.setData(mMoreList);
        mMoreAdapter.setOnItemClickListener((recyclerView, itemView, position) -> {
            Article article = mMoreList.get(position);
            Intent intent = new Intent(mActivity, WebActivity.class);
            intent.putExtra(Constants.ARG_URL,article.getLink());
            mActivity.startActivity(intent);
        });
        mMoreRecyclerView.setAdapter(mMoreAdapter);

        mRefreshView.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
        mRefreshView.setReboundDuration(600);//回弹动画时长（毫秒）
        mRefreshView.setEnableRefresh(true);//是否启用下拉刷新功能
        mRefreshView.setEnableLoadMore(true);
        mRefreshView.setOnRefreshListener(refreshLayout -> {
            mMoreList.clear();
            mTopList.clear();
            mPresenter.getHomeTopArticle();
            mPresenter.getHomeTopImgBanner();
            page = 0;
            mPresenter.getMoreArticle(page);
            if (onFinishLoad()) {
                mRefreshView.finishRefresh();
            }
        });
        mRefreshView.setOnLoadMoreListener(refreshLayout -> {
            page = page + 1;
            mPresenter.getMoreArticle(page);
            if (onFinishLoad()) {
                mRefreshView.finishLoadMore();
            }
        });

    }

    @Override
    protected void initData() {
        mBannerData = new ArrayList<>();
        mPresenter = new HomePresenter();
        mPresenter.attachView(this);
        //get data
        mPresenter.getHomeTopImgBanner();
        mPresenter.getHomeTopArticle();
        mPresenter.getMoreArticle(page);
    }

    @Override
    public boolean onFinishLoad() {
        return true;
    }

    @Override
    public void refreshBanner(List<BannerData> bannerData) {
        mBannerData = bannerData;
        mBanner.setPages(bannerData, new GlideImageLoader())
                .setAutoPlay(true)
                .setCurrentPage(0)
                .setDelayTime(3000)
                .start();
    }

    @Override
    public void refreshTopArticle(List<Article> topArticles) {
        if(topArticles!=null && !topArticles.isEmpty()){
            mTopList.addAll(topArticles);
            mTopAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void refreshMoreArticle(MoreArticle moreArticle) {
        if(moreArticle!=null
                && moreArticle.getDatas()!=null
                && !moreArticle.getDatas().isEmpty()){
            mMoreList.addAll(moreArticle.getDatas());
            mMoreAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
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

    @Nullable
    @Override
    public Context getContext() {
        return mActivity;
    }
}
