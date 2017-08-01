package com.example.vogabond.chatapp;

import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

public class NewsActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    private ViewPager view_pager;
    private RadioGroup Radio_Group;
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
}
