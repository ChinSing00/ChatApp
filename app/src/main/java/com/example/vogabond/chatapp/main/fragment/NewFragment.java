package com.example.vogabond.chatapp.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vogabond.chatapp.R;
import com.example.vogabond.chatapp.discovery.NewType;
import com.example.vogabond.chatapp.discovery.adapter.TabAdapter;
import com.example.vogabond.chatapp.discovery.fragment.BaseNewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Selet on 2017/8/19 0019.
 */

public class NewFragment extends Fragment {
    private View view;
    private TabLayout tabLayout;
    private ViewPager viewpager;
    private TabAdapter adapter;
    public static final String[] tabTitle = new String[]{"头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_view_news, null);
        tabLayout = view.findViewById(R.id.tab_FindFragment_title);
        viewpager = view.findViewById(R.id.new_viewpager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new BaseNewFragment(NewType.TOP));
        fragments.add(new BaseNewFragment(NewType.SHEHUI));
        fragments.add(new BaseNewFragment(NewType.GUONEI));
        fragments.add(new BaseNewFragment(NewType.GUOJI));
        fragments.add(new BaseNewFragment(NewType.YULE));
        fragments.add(new BaseNewFragment(NewType.TIYU));
        fragments.add(new BaseNewFragment(NewType.JUSHI));
        fragments.add(new BaseNewFragment(NewType.KEJI));
        fragments.add(new BaseNewFragment(NewType.CAIJING));
        fragments.add(new BaseNewFragment(NewType.SHISHANG));
        tabLayout.setupWithViewPager(viewpager);
        adapter = new TabAdapter(getFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
        return view;
    }


}
