package com.example.vogabond.chatapp.discovery.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.example.vogabond.chatapp.R;
import com.example.vogabond.chatapp.discovery.activity.NewContentActivity;
import com.example.vogabond.chatapp.discovery.adapter.NewListAdapter;
import com.example.vogabond.chatapp.discovery.model.Datum;
import com.example.vogabond.chatapp.discovery.model.NewBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Selet on 2017/8/19 0019.
 */

public  class BaseNewFragment extends Fragment implements AdapterView.OnItemClickListener {
    private View view;
    private ListView listView;
    private String NEW_TYPE;
    private String url;
    private List<Datum> dataList = new ArrayList<>();
    private NewListAdapter adapter;

    public BaseNewFragment(String new_type) {
        NEW_TYPE = new_type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.new_list,null);
        url ="http://v.juhe.cn/toutiao/index?type="+NEW_TYPE+"&key=50271a1b243438cf50346f735755e378";


        getData();
        return view ;
    }

    private void getData() {
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    private void findView() {

        Log.e("ssssssssssssssss",dataList.size()+"");
        listView = view.findViewById(R.id.new_list);
        adapter = new NewListAdapter(dataList,getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }
    Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call = okHttpClient.newCall(request);
            try{
                Response response = call.execute();
                emitter.onNext(response.body().string().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
    Consumer<String> consumer = new Consumer<String>() {
        @Override
        public void accept(String s) throws Exception {
            Log.e("ssssssssssssssss",s);
            NewBean bean = JSON.parseObject(s,NewBean.class);
            dataList = bean.getResult().getData();
            findView();
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        NewContentActivity.start(dataList.get(i).getUrl(),getActivity());
        Log.e("======|url|======",dataList.get(i).getUrl());
    }
}
