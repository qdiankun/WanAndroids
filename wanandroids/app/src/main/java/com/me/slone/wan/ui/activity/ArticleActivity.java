package com.me.slone.wan.ui.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hjq.bar.TitleBar;
import com.me.slone.wan.R;
import com.me.slone.wan.base.MyActivity;
import com.me.slone.wan.bean.Children;
import com.me.slone.wan.bean.Tree;
import com.me.slone.wan.ui.adapter.FragmentAdapter;

import java.io.Serializable;
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

    private Children mChildren;
    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentPagerAdapter mFragmentAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article;
    }

    @Override
    protected void initView() {

        mTabLayout.setupWithViewPager(mVPager);
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),mFragments);
        mVPager.setAdapter(mFragmentAdapter);
        mVPager.setOffscreenPageLimit(mFragments.size());
    }

    @Override
    protected void initData()
    {
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            mChildren = (Children) bundle.getSerializable("children");
        }
        if(mChildren == null){
            finish();
        }
        mTitleBar.setTitle(mChildren.getName());
    }
}