package com.example.vogabond.chatapp.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vogabond.chatapp.R;
import com.netease.nim.uikit.model.ToolBarOptions;


public class FeedbackActivity extends UI {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ToolBarOptions options = new ToolBarOptions();
        setToolBar(R.id.toolbar, options);

        button = (Button) findViewById(R.id.feedback_but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"发送成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
