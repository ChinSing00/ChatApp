package com.example.vogabond.chatapp.main.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioGroup;

import com.example.vogabond.chatapp.R;
import com.example.vogabond.chatapp.activity.AboutActivity;
import com.netease.nim.uikit.common.activity.UI;
import com.netease.nim.uikit.contact.ContactsFragment;
import com.netease.nim.uikit.recent.RecentContactsFragment;

/**
 * Created by Selet on 2017/8/4 0004.
 */

public class MainActivity extends UI {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private RadioGroup RG;
    private Fragment[] mFragments;
    private int mIndex;

    public static void start(Context context) {
        start(context, null);
    }

    public static void start(Context context, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        initView();
        initNavigationView();
        initRadioGroup();
        initFragment();
    }

//    private void getFragPageData() {
//        dataList.add(0,new RecentContactsFragment());
//        dataList.add(1,new ContactsFragment());
//    }
    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //定义toolbar左上角的弹出左侧菜单按钮
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        //同步状态
        toggle.syncState();
        //侧滑透明
        drawerLayout.setScrimColor(Color.TRANSPARENT);

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = drawerLayout.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                //改变DrawLayout侧栏透明度，若不需要效果可以不设置
                int offset = (int) (drawerView.getWidth() * slideOffset);
                mContent.setTranslationX(offset);
////        isDrawer = true;
////        //获取屏幕的宽高
////        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
////        Display display = manager.getDefaultDisplay();
////        //设置右面的布局位置  根据左面菜单的right作为右面布局的left   左面的right+屏幕的宽度（或者right的宽度这里是相等的）为右面布局的right
////        right.layout(navigationView.getRight(), 0, navigationView.getRight() + display.getWidth(), display.getHeight());
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }

    private void initNavigationView() {
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_me:
                        break;
                    case R.id.nav_collection:
                        break;
                    case R.id.nav_album:
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivity(intent);
                        break;
                    case R.id.nav_file:
                        intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("*/*");
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        startActivityForResult(intent, 1);
                        break;
                    case R.id.nav_theme:
                        break;
                    case R.id.nav_night:
                        break;
                    case R.id.nav_setting:
                        break;
                    case R.id.nav_suggestion:
                        break;
                    case R.id.nav_about:
                        intent=new Intent();
                        intent.setClass(getApplicationContext(),AboutActivity.class);
                        startActivity(intent);
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private void initRadioGroup() {
        RG = (RadioGroup) findViewById(R.id.Radio_Group_Main);
        RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.news_buttom:
                        setIndexSelected(0);
                        break;
                    case R.id.friends_buttom:
                        setIndexSelected(1);
                        ;
                        break;
                    case R.id.find_buttom:
//                        setIndexSelected(2);
                        break;
//                    case R.id.we_buttom:
//                        myViewPager.setCurrentItem(3);
//                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initFragment() {
        ContactsFragment contactsFragment = new ContactsFragment();
        RecentContactsFragment recentContactsFragment=new RecentContactsFragment();
        mFragments = new Fragment[]{recentContactsFragment, contactsFragment };

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frame_content, recentContactsFragment).commit();
        setIndexSelected(0);
    }

    private void setIndexSelected(int i) {
        if (mIndex == i) {
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //隐藏
        ft.hide(mFragments[mIndex]);
        //判断是否添加
        if (!mFragments[i].isAdded()) {
            ft.add(R.id.frame_content, mFragments[i]).show(mFragments[i]);
        } else {
            ft.show(mFragments[i]);
        }
        ft.commit();
        //再次赋值
        mIndex = i;

    }

    //右上角菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }
}
