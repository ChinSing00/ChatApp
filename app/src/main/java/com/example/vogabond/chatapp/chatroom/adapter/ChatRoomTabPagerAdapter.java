package com.example.vogabond.chatapp.chatroom.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.vogabond.chatapp.chatroom.constant.ChatRoomTab;

/**
 * Created by yang。先森 on 2017/8/8 0008.
 */

public class ChatRoomTabPagerAdapter extends PagerAdapter {

    public ChatRoomTabPagerAdapter(FragmentManager fragmentManager, FragmentActivity activity, ViewPager viewPager) {
    }

    public int getCacheCount() {
        return ChatRoomTab.values().length;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    public void onPageScrolled(int position) {
    }
}
