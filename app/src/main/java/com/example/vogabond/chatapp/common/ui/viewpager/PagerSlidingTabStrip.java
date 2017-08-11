package com.example.vogabond.chatapp.common.ui.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import com.example.vogabond.chatapp.chatroom.adapter.ChatRoomTabPagerAdapter;

/**
 * Created by yang。先森 on 2017/8/9 0009.
 */

public class PagerSlidingTabStrip extends HorizontalScrollView {

    private OnCustomTabListener onCustomTabListener = null;
    private ViewPager pager;
    private ChatRoomTabPagerAdapter onTabClickListener = null;
    private ChatRoomTabPagerAdapter onTabDoubleTapListener  = null;


    public PagerSlidingTabStrip(Context context) {
        super(context);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setOnCustomTabListener(OnCustomTabListener onCustomTabListener) {
        this.onCustomTabListener = onCustomTabListener;
    }

    public void setViewPager(ViewPager pager) {
        this.pager = pager;
    }

    public void setOnTabClickListener(ChatRoomTabPagerAdapter onTabClickListener) {
        this.onTabClickListener = onTabClickListener;
    }

    public void setOnTabDoubleTapListener(ChatRoomTabPagerAdapter onTabDoubleTapListener) {
        this.onTabDoubleTapListener = onTabDoubleTapListener;
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        
    }

    public void onPageScrolled(int position) {
        
    }

    public void onPageScrollStateChanged(int state) {
    }


    public static abstract class OnCustomTabListener {

        public abstract int getTabLayoutResId(int position);

        public abstract boolean screenAdaptation();
    }
}
