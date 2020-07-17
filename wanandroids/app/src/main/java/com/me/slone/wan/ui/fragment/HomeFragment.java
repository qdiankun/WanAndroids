package com.me.slone.wan.ui.fragment;

import com.me.slone.wan.R;
import com.me.slone.wan.bean.BannerData;
import com.me.slone.wan.common.MyFragment;
import com.me.slone.wan.network.NetworkManager;
import com.me.slone.wan.network.observer.ProgressSubscriber;
import com.me.slone.wan.network.response.ResponseTransformer;
import com.me.slone.wan.network.schedulers.RxSchedulersHelper;
import com.me.slone.wan.utils.GlideImageLoader;
import com.ms.banner.Banner;

import java.util.List;

import butterknife.BindView;

/**
 * Author：diankun
 * Time：20-7-14 下午3:51
 * Description: 首页
 */
public class HomeFragment extends MyFragment {

    @BindView(R.id.main_banner)
    Banner mBanner;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        NetworkManager.getInstance()
                .getRequest()
                .getBanner()
                .compose(ResponseTransformer.handleResult())
                .compose(RxSchedulersHelper.applySchedulers())
                .subscribe(new ProgressSubscriber<List<BannerData>>(mActivity,true){

                    @Override
                    public void success(List<BannerData> bannerData) {
                        refreshBanner(bannerData);
                    }

                    @Override
                    public void onHandledNetError(Throwable throwable) {
                        super.onHandledNetError(throwable);
                    }
                });
    }

    private void refreshBanner(List<BannerData> bannerData) {
        mBanner.setPages(bannerData, new GlideImageLoader())
                .setAutoPlay(true)
                .setCurrentPage(0)
                .setDelayTime(3000)
                .start();
    }


    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }
}
