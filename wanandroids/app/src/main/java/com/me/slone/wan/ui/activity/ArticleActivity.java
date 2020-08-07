package com.me.slone.wan.ui.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hjq.bar.TitleBar;
import com.hjq.base.BaseFragmentAdapter;
import com.me.slone.wan.R;
import com.me.slone.wan.base.MyActivity;
import com.me.slone.wan.base.MyFragment;
import com.me.slone.wan.bean.Children;
import com.me.slone.wan.bean.Tree;
import com.me.slone.wan.ui.fragment.ContentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ArticleActivity extends MyActivity {

    @BindView(R.id.official_account_topbar)
    TitleBar mTitleBar;
    @BindView(R.id.official_account_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.official_account_viewpager)
    ViewPager mVPager;

    private Tree mTree;
    private List<Fragment> mFragments = new ArrayList<>();
    private BaseFragmentAdapter<MyFragment> mPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article;
    }

    @Override
    protected void initView() {

        mTabLayout.setupWithViewPager(mVPager);

        mVPager.setAdapter(mPagerAdapter);
        mVPager.setOffscreenPageLimit(mFragments.size());

    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mTree = (Tree) bundle.getSerializable("tree");
        }
        if (mTree == null) {
            finish();
        }
        mTitleBar.setTitle(mTree.getName());
        mPagerAdapter = new BaseFragmentAdapter<>(this);
        List<Children> childrens = mTree.getChildren();
        for (Children children : childrens) {
            mTabLayout.addTab(mTabLayout.newTab());
            mPagerAdapter.addFragment(ContentFragment.newInstance(children));
        }
        mVPager.setAdapter(mPagerAdapter);
        for(int i=0;i<childrens.size();i++){
            Children children = childrens.get(i);
            mTabLayout.getTabAt(i).setText(children.getName());
        }
    }
}