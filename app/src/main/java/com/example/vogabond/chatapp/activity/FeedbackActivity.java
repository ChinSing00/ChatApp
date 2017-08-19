package com.example.vogabond.chatapp.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vogabond.chatapp.R;
import com.example.vogabond.chatapp.main.activity.MainActivity;
import com.netease.nim.uikit.model.ToolBarOptions;


public class FeedbackActivity extends UI {
    private Button button;
    private EditText editText;
    public static void start(Context c){
        Intent itt = new Intent(c,FeedbackActivity.class);
        c.startActivity(itt);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ToolBarOptions options = new ToolBarOptions();
        setToolBar(R.id.toolbar, options);

        button = (Button) findViewById(R.id.feedback_but);
        editText= (EditText) findViewById(R.id.feedback_editText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().length()==0){
                    Toast.makeText(getApplicationContext(), "请输入反馈信息", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "发送成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    ///1111

                }
            }
        });
    }
}
