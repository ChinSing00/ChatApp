package com.example.vogabond.chatapp.discovery.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.vogabond.chatapp.R;
import com.example.vogabond.chatapp.activity.UI;
import com.example.vogabond.chatapp.main.model.Extras;

/**
 * Created by Selet on 2017/8/19 0019.
 */

public class NewContentActivity extends UI {
    private String url;
    private WebView webview;
    public static void start(String url, Context context){
        Intent itt = new Intent(context,NewContentActivity.class);
        itt.putExtra(Extras.NEWS,url);
        itt.setClass(context,NewContentActivity.class);
        context.startActivity(itt);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_news_details);
            initView();
    }

    private void initView() {
        url = getIntent().getStringExtra(Extras.NEWS);
        Log.e("======url======",url);
        webview = new WebView(this);
        WebSettings webSettings = webview.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        webview.loadUrl(url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
