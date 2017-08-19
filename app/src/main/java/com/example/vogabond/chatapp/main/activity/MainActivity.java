package com.example.vogabond.chatapp.main.activity;

import android.Manifest;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vogabond.chatapp.Login.LoginActivity;
import com.example.vogabond.chatapp.Login.LogoutHelper;
import com.example.vogabond.chatapp.MyCache;
import com.example.vogabond.chatapp.R;
import com.example.vogabond.chatapp.activity.AboutActivity;
import com.example.vogabond.chatapp.activity.FeedbackActivity;
import com.example.vogabond.chatapp.chatroom.fragment.ChatRoomFragment;
import com.example.vogabond.chatapp.chatroom.helper.ChatRoomHelper;
import com.example.vogabond.chatapp.contact.activity.AddFriendActivity;
import com.example.vogabond.chatapp.contact.activity.UserProfileActivity;
import com.example.vogabond.chatapp.main.fragment.NewFragment;
import com.example.vogabond.chatapp.team.AdvancedTeamSearchActivity;
import com.netease.nim.uikit.NimUIKit;
import com.netease.nim.uikit.common.activity.UI;
import com.netease.nim.uikit.contact.ContactsFragment;
import com.netease.nim.uikit.contact_selector.activity.ContactSelectActivity;
import com.netease.nim.uikit.permission.MPermission;
import com.netease.nim.uikit.permission.annotation.OnMPermissionDenied;
import com.netease.nim.uikit.permission.annotation.OnMPermissionGranted;
import com.netease.nim.uikit.permission.annotation.OnMPermissionNeverAskAgain;
import com.netease.nim.uikit.recent.RecentContactsFragment;
import com.netease.nim.uikit.team.helper.TeamHelper;

/**
 * Created by Selet on 2017/8/4 0004.
 */

public class MainActivity extends UI {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private RadioGroup RG;
    private FragmentManager fragmentManager = getSupportFragmentManager();
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
        requestBasicPermission();
        ChatRoomHelper.init();
        initView();
        initNavigationView();
        initRadioGroup();
        initFragment();
        //头部点击事件
        initheadView();

    }

    private final String[] BASIC_PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    private void requestBasicPermission() {
        MPermission.printMPermissionResult(true, this, BASIC_PERMISSIONS);
        MPermission.with(MainActivity.this)
                .setRequestCode(100)
                .permissions(BASIC_PERMISSIONS)
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @OnMPermissionGranted(100)
    public void onBasicPermissionSuccess() {
        Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
        MPermission.printMPermissionResult(false, this, BASIC_PERMISSIONS);
    }

    @OnMPermissionDenied(100)
    @OnMPermissionNeverAskAgain(100)
    public void onBasicPermissionFailed() {
        Toast.makeText(this, "未全部授权，部分功能可能无法正常运行！", Toast.LENGTH_SHORT).show();
        MPermission.printMPermissionResult(false, this, BASIC_PERMISSIONS);
    }

    private void initheadView() {
        View headView = navigationView.inflateHeaderView(R.layout.menu_layout);
        ImageView mHeadPic = (ImageView) headView.findViewById(R.id.imageView);
        mHeadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Log:", "头部被点击了！");
            }
        });
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("消息");
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
                        UserProfileActivity.start(MainActivity.this, MyCache.getAccount());
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
                        Intent itt = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(itt);
                        break;
                    case R.id.nav_feedback:
                        FeedbackActivity.start(MainActivity.this);
                        break;
                    case R.id.nav_about:
                        AboutActivity.start(MainActivity.this);
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
                        toolbar.setTitle("消息");
                        setIndexSelected(0);
                        break;
                    case R.id.friends_buttom:
                        toolbar.setTitle("联系人");
                        setIndexSelected(1);
                        break;
                    case R.id.find_buttom:
                        toolbar.setTitle("发现");
                        setIndexSelected(2);
                        break;
                }
            }
        });
    }

    private void initFragment() {
        ContactsFragment contactsFragment = new ContactsFragment();
        NewFragment nf = new NewFragment();
        RecentContactsFragment recentContactsFragment = new RecentContactsFragment();
        mFragments = new Fragment[]{recentContactsFragment, contactsFragment, nf};
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.frame_content, recentContactsFragment).commit();
        setIndexSelected(0);
    }

    private void setIndexSelected(int i) {
        if (mIndex == i) {
            return;
        }

        FragmentTransaction ftt = fragmentManager.beginTransaction();
        //隐藏
        ftt.hide(mFragments[mIndex]);
        //判断是否添加
        if (!mFragments[i].isAdded()) {
            ftt.add(R.id.frame_content, mFragments[i]).show(mFragments[i]);
        } else {
            ftt.show(mFragments[i]);
        }
        ftt.commit();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create_normal_team:
                ContactSelectActivity.Option option = TeamHelper.getCreateContactSelectOption(null, 50);
                NimUIKit.startContactSelect(MainActivity.this, option, 1);
                break;
            case R.id.create_regular_team:
                ContactSelectActivity.Option advancedOption = TeamHelper.getCreateContactSelectOption(null, 50);
                NimUIKit.startContactSelect(MainActivity.this, advancedOption, 2);
                break;
            case R.id.search_advanced_team:
                AdvancedTeamSearchActivity.start(MainActivity.this);
                break;
            case R.id.add_buddy:
                AddFriendActivity.start(MainActivity.this);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public static void logout(Context context, boolean quit) {
        Intent extra = new Intent();
        extra.putExtra("qiut", quit);
        start(context, extra);
        LogoutHelper.logout();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("KICK_OUT", quit);
        context.startActivity(intent);

    }

}
