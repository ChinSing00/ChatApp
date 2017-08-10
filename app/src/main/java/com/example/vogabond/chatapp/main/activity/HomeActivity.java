package com.example.vogabond.chatapp.main.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.example.vogabond.chatapp.R;
import com.example.vogabond.chatapp.helper.Login.LoginActivity;

/**
 * Created by Selet on 2017/8/4 0004.
 */

public class HomeActivity extends Activity implements RadioGroup.OnCheckedChangeListener {
    private ViewPager view_pager;
    private RadioGroup Radio_Group;
    public static void start(Context context) {
        start(context, null);
    }

    public static void start(Context context, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getView();
    }

    public void getView() {
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        Radio_Group = (RadioGroup) findViewById(R.id.Radio_Group_Main);
        Radio_Group.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
