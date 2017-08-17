package com.example.vogabond.chatapp.activity;

import android.os.Bundle;

import com.example.vogabond.chatapp.R;
import com.netease.nim.uikit.common.activity.UI;
import com.netease.nim.uikit.model.ToolBarOptions;


public class AboutActivity extends UI {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ToolBarOptions options = new ToolBarOptions();
        setToolBar(R.id.toolbar, options);
    }


}
