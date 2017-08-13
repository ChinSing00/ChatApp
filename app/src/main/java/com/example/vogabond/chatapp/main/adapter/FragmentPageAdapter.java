package com.example.vogabond.chatapp.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Selet on 2017/8/11 0011.
 */

public class FragmentPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> dataList;

    public FragmentPageAdapter(FragmentManager fm, List<Fragment> dataList) {
        super(fm);
        this.dataList = dataList;
    }

    @Override
    public Fragment getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }
}
