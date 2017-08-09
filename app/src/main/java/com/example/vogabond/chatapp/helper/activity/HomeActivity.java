package com.example.vogabond.chatapp.helper.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RadioGroup;

import com.example.vogabond.chatapp.R;

import com.example.vogabond.chatapp.helper.contact.AddFriendActivity;
import com.example.vogabond.chatapp.helper.team.AdvancedTeamSearchActivity;
import com.netease.nim.uikit.NimUIKit;
import com.netease.nim.uikit.common.activity.UI;
import com.netease.nim.uikit.contact_selector.activity.ContactSelectActivity;
import com.netease.nim.uikit.team.helper.TeamHelper;

import java.lang.reflect.Method;

/**
 * Created by Selet on 2017/8/4 0004.
 */

public class HomeActivity extends UI implements RadioGroup.OnCheckedChangeListener {
    private ViewPager view_pager;
    private RadioGroup Radio_Group;
    private static final int REQUEST_CODE_NORMAL = 1;
    private static final int REQUEST_CODE_ADVANCED = 2;
    private static final String EXTRA_APP_QUIT = "APP_QUIT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Log.e("创建","111");


        getView();
    }



    public void getView() {
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        Radio_Group = (RadioGroup) findViewById(R.id.Radio_Group_Main);
        Radio_Group.setOnCheckedChangeListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.e("菜单","111");
        MenuInflater inflater = getMenuInflater();
//        setIconsVisible(menu,true);
        inflater.inflate(R.menu.main_activity_menu, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }
//    /**
//     * 解决menu不显示图标问题
//     * @param menu
//     * @param flag
//     */
//    private void setIconsVisible(Menu menu, boolean flag) {
//        //判断menu是否为空
//        if(menu != null) {
//            try {
//                //如果不为空,就反射拿到menu的setOptionalIconsVisible方法
//                Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
//                //暴力访问该方法
//                method.setAccessible(true);
//                //调用该方法显示icon
//                method.invoke(menu, flag);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
                break;
            case R.id.create_normal_team:
                ContactSelectActivity.Option option = TeamHelper.getCreateContactSelectOption(null, 50);
                NimUIKit.startContactSelect(HomeActivity.this, option, REQUEST_CODE_NORMAL);
                break;
            case R.id.create_regular_team:
                ContactSelectActivity.Option advancedOption = TeamHelper.getCreateContactSelectOption(null, 50);
                NimUIKit.startContactSelect(HomeActivity.this, advancedOption, REQUEST_CODE_ADVANCED);
                break;
            case R.id.search_advanced_team:
                AdvancedTeamSearchActivity.start(HomeActivity.this);
                break;
//            case R.id.add_buddy:
//                AddFriendActivity.start(HomeActivity.this);
//                break;4
//            case R.id.search_btn:
//                GlobalSearchActivity.start(HomeActivity.this);
//                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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
    // 注销
    public static void logout(Context context, boolean quit) {
        Intent extra = new Intent();
        extra.putExtra(EXTRA_APP_QUIT, quit);
        start(context, extra);
    }

}
