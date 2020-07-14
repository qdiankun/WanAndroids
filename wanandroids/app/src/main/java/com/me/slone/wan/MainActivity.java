package com.me.slone.wan;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bottom.NavigationController;
import com.bottom.PageNavigationView;
import com.bottom.item.BaseTabItem;
import com.bottom.listener.OnTabItemSelectedListener;
import com.me.slone.wan.bean.Banner;
import com.me.slone.wan.network.NetworkManager;
import com.me.slone.wan.network.observer.SilenceSubscriber;
import com.me.slone.wan.network.response.ResponseTransformer;
import com.me.slone.wan.network.schedulers.RxSchedulersHelper;
import com.me.slone.wan.ui.FragmentAdapter;
import com.me.slone.wan.ui.HomeFragment;
import com.me.slone.wan.ui.ProjectFragment;
import com.me.slone.wan.ui.SystemFragment;
import com.me.slone.wan.ui.UserFragment;
import com.me.slone.wan.utils.KLog;
import com.me.slone.wan.view.OnlyIconView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.main_bottom_bar)
    PageNavigationView mBottomBar;

    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentPagerAdapter mFragmentAdapter;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initFragments();
        initBottomBar();
    }

    private void initFragments() {
        HomeFragment homeFragment = new HomeFragment();
        SystemFragment systemFragment = new SystemFragment();
        ProjectFragment projectFragment = new ProjectFragment();
        UserFragment userFragment = new UserFragment();
        mFragments.add(homeFragment);
        mFragments.add(systemFragment);
        mFragments.add(projectFragment);
        mFragments.add(userFragment);
    }

    private void initBottomBar() {
        final PageNavigationView.CustomBuilder custom = mBottomBar.custom();
        NavigationController build = custom
                .addItem(newItem(R.mipmap.home_no, R.mipmap.home))
                .addItem(newItem(R.mipmap.tree_no, R.mipmap.tree))
                .addItem(newItem(R.mipmap.project_no, R.mipmap.project_yes))
                .addItem(newItem(R.mipmap.user_no, R.mipmap.user))
                .build();
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(mFragmentAdapter);
        build.setupWithViewPager(mViewPager);
        build.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {

            }

            @Override
            public void onRepeat(int index) {

            }
        });
    }


    //创建一个Item
    private BaseTabItem newItem(int drawable, int checkedDrawable) {
        OnlyIconView onlyIconItemView = new OnlyIconView(this);
        onlyIconItemView.initialize(drawable, checkedDrawable);
        return onlyIconItemView;
    }

    @OnClick({R.id.btn_baner})
    public void bannerClick(View view) {
        NetworkManager.getInstance()
                .getRequest()
                .getBanner()
                .compose(ResponseTransformer.handleResult())
                .compose(RxSchedulersHelper.applySchedulers())
                .subscribe(new SilenceSubscriber<List<Banner>>() {
                    @Override
                    public void success(List<Banner> banners) {
                        KLog.i(banners);
                    }
                });

    }
}