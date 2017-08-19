package com.example.vogabond.chatapp.discovery.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vogabond.chatapp.main.fragment.NewFragment;

import java.util.List;

/**
 * Created by 39275 on 2017/8/20.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public TabAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }



    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();

    }
    //设置tablayout标题
    @Override
    public CharSequence getPageTitle(int position) {
        return NewFragment.tabTitle[position];

    }
}
