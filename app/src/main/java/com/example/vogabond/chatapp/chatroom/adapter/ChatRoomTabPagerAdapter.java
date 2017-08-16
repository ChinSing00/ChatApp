package com.example.vogabond.chatapp.chatroom.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.vogabond.chatapp.chatroom.constant.ChatRoomTab;
import com.example.vogabond.chatapp.chatroom.fragment.tab.ChatRoomTabFragment;
import com.example.vogabond.chatapp.common.ui.viewpager.SlidingTabPagerAdapter;

import java.util.List;

/**
 * Created by yang。先森 on 2017/8/8 0008.
 */

public class ChatRoomTabPagerAdapter extends SlidingTabPagerAdapter {

    public ChatRoomTabPagerAdapter(FragmentManager fm, Context context, ViewPager pager) {
        super(fm, ChatRoomTab.values().length, context.getApplicationContext(), pager);

        for (ChatRoomTab tab : ChatRoomTab.values()) {
            try {
                ChatRoomTabFragment fragment = null;

                List<Fragment> fs = fm.getFragments();
                if (fs != null) {
                    for (Fragment f : fs) {
                        if (f.getClass() == tab.clazz) {
                            fragment = (ChatRoomTabFragment) f;
                            break;
                        }
                    }
                }

                if (fragment == null) {
                    fragment = tab.clazz.newInstance();
                }

                fragment.setState(this);
                fragment.attachTabData(tab);

                fragments[tab.tabIndex] = fragment;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public int getCacheCount() {
        return ChatRoomTab.values().length;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    public void onPageScrolled(int position) {
    }
}
