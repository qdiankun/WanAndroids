package com.me.slone.wan.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：diankun
 * Time：20-7-14 下午3:59
 * Description:
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    public FragmentAdapter(@NonNull FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
