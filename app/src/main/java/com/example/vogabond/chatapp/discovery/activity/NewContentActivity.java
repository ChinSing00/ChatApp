package com.example.vogabond.chatapp.discovery.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.vogabond.chatapp.R;
import com.example.vogabond.chatapp.activity.UI;
import com.example.vogabond.chatapp.main.model.Extras;
import com.netease.nim.uikit.model.ToolBarOptions;

/**
 * Created by Selet on 2017/8/19 0019.
 */

public class NewContentActivity extends UI {
    private String url;
    private WebView webview;
    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        initView();
    }

    private void initView() {
        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.news;
        setToolBar(R.id.toolbar, options);
        url = getIntent().getStringExtra(Extras.NEWS);
        Log.e("======url======>",url);

        webview = (WebView) findViewById(R.id.new_web_details);
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings webSettings = webview.getSettings();
        // 设置与Js交互的权限
        //webSettings.setJavaScriptEnabled(true);
        //webview.loadUrl(url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
