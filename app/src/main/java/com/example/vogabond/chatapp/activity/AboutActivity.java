package com.example.vogabond.chatapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import com.example.vogabond.chatapp.R;
import com.netease.nim.uikit.common.activity.UI;
import com.netease.nim.uikit.model.ToolBarOptions;


public class AboutActivity extends UI {
    public static void start(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.about;
        setToolBar(R.id.toolbar, options);




    }


}
