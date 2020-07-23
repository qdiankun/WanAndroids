package com.me.slone.wan.ui.fragment;

import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hjq.base.BaseFragmentAdapter;
import com.me.slone.wan.R;
import com.me.slone.wan.base.MyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author：diankun
 * Time：20-7-14 下午3:52
 * Description: 体系
 */
public class SystemFragment extends MyFragment {

    @BindView(R.id.system_tab)
    TabLayout mTabLayout;
    @BindView(R.id.system_viewpager)
    ViewPager mViewPager;

    private BaseFragmentAdapter<MyFragment> mPagerAdapter;

    private String[] titleName = {"导航", "知识体系"};
    private int[] imgTab = {R.mipmap.navi, R.mipmap.tree_knowledge};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_system;
    }

    @Override
    protected void initView() {
        LinearLayout linearLayout = (LinearLayout) mTabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getContext(), R.drawable.tab_divider));
        linearLayout.setDividerPadding(48);
        for (int i = 0; i < titleName.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab());
        }
        mTabLayout.setupWithViewPager(mViewPager);
//        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                mTitleBar.setLeftTitle(titleName[tab.getPosition()]);
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

        mPagerAdapter = new BaseFragmentAdapter<>(this);
        mPagerAdapter.addFragment(NaviFragment.newInstance());
        mPagerAdapter.addFragment(TreeFragment.newInstance());
        mViewPager.setAdapter(mPagerAdapter);
        //update icon
        for (int i = 0; i < titleName.length; i++) {
            mTabLayout.getTabAt(i).setIcon(imgTab[i]);
        }
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
