package com.example.vogabond.chatapp.discovery.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.vogabond.chatapp.R;
import com.example.vogabond.chatapp.discovery.adapter.NewListAdapter;

/**
 * Created by Selet on 2017/8/19 0019.
 */

public  class BaseNewFragment extends Fragment {
    private View view;
    private ListView listView;
    private String NEW_TYPE;
    private NewListAdapter adapter;

    public BaseNewFragment(String new_type) {
        NEW_TYPE = new_type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.new_list,null);
        findView();
       return view ;
    }

    private void findView() {
        NEW_TYPE ="http://v.juhe.cn/toutiao/index?type="+NEW_TYPE+"&key=50271a1b243438cf50346f735755e378";
        listView = view.findViewById(R.id.new_list);
    }

}
