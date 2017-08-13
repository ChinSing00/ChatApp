package com.example.vogabond.chatapp.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.example.vogabond.chatapp.R;
import com.example.vogabond.chatapp.main.adapter.FragmentPageAdapter;
import com.netease.nim.uikit.common.activity.UI;
import com.netease.nim.uikit.recent.RecentContactsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Selet on 2017/8/4 0004.
 */

public class MainActivity extends UI implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private ViewPager view_pager;
    private RadioGroup Radio_Group;
    private List<Fragment> dataList = new ArrayList<>();
    public static void start(Context context) {
        start(context, null);
    }

    public static void start(Context context, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getView();
    }

    public void getView() {
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        Radio_Group = (RadioGroup) findViewById(R.id.Radio_Group_Main);
        Radio_Group.setOnCheckedChangeListener(this);
        dataList.add(0,new RecentContactsFragment());
        view_pager.setOnPageChangeListener(this);
        FragmentManager fm = getSupportFragmentManager();
        FragmentPagerAdapter fp  = new FragmentPageAdapter(fm,dataList);
        view_pager.setAdapter(fp);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i){
            case 0 :

                break;
            case 1 :
                break;
            case 2 :
                break;
            case 3 :
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0 :
                break;
            case 1 :
                break;
            case 2 :
                break;
            case 3 :
                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
