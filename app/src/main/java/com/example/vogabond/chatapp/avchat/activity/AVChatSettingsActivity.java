package com.example.vogabond.chatapp.avchat.activity;

import android.os.Bundle;

import com.example.vogabond.chatapp.R;
import com.netease.nim.uikit.common.activity.UI;
import com.netease.nim.uikit.model.ToolBarOptions;

/**
 * Created by MSI on 2017/8/8.
 */
//全局配置，不区分
public class AVChatSettingsActivity extends UI{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.avchat_settings_layout);
        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.nrtc_settings;
        setToolBar(R.id.toolbar, options);


    }
}
