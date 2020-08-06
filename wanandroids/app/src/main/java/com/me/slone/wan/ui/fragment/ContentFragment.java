package com.me.slone.wan.ui.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.me.slone.wan.R;
import com.me.slone.wan.base.MyFragment;
import com.me.slone.wan.contract.ContentContract;
import com.me.slone.wan.ui.adapter.ContentAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.lang.reflect.Array;

import butterknife.BindView;

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

    private ContentAdapter contentAdapter;
    public static String ARG_CID = "cid";


    public static ContentFragment newInstance(int childredId){
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_CID,childredId);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_content;
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        if(arguments!=null){
            int anInt = arguments.getInt(ARG_CID);

        }
    }

    @Override
    public boolean onFinishLoad() {
        return false;
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
}
