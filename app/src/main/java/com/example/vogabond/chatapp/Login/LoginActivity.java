package com.example.vogabond.chatapp.Login;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vogabond.chatapp.MyCache;
import com.example.vogabond.chatapp.R;
import com.example.vogabond.chatapp.activity.UI;
import com.example.vogabond.chatapp.main.activity.HomeActivity;
import com.example.vogabond.chatapp.preference.Preferences;
import com.example.vogabond.chatapp.preference.UserPreferences;
import com.netease.nim.uikit.NimUIKit;
import com.netease.nim.uikit.common.ui.dialog.DialogMaker;
import com.netease.nim.uikit.common.util.string.MD5;
import com.netease.nim.uikit.permission.MPermission;
import com.netease.nim.uikit.permission.annotation.OnMPermissionDenied;
import com.netease.nim.uikit.permission.annotation.OnMPermissionGranted;
import com.netease.nim.uikit.permission.annotation.OnMPermissionNeverAskAgain;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.ResponseCode;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;

/**
 * Created by Vogabond on 2017/8/4.
 */

public class LoginActivity extends UI implements View.OnClickListener{
    private EditText edit_login_account,edit_login_password;
    private Button button_login;
    private static final String KICK_OUT = "KICK_OUT";
    private final int BASIC_PERMISSION_REQUEST_CODE = 110;
    private AbortableFuture<LoginInfo> loginRequest;
    private TextView register_login_tip;
    public static void start(Context context) {
        start(context, false);
    }

    public static void start(Context context, boolean kickOut) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(KICK_OUT, kickOut);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        getView();
        requestBasicPermission();
    }

    /**
     * 基本权限管理
     */

    private final String[] BASIC_PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private void requestBasicPermission() {
        MPermission.with(LoginActivity.this)
                .setRequestCode(BASIC_PERMISSION_REQUEST_CODE)
                .permissions(BASIC_PERMISSIONS)
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @OnMPermissionGranted(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionSuccess() {
        Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
    }

    @OnMPermissionDenied(BASIC_PERMISSION_REQUEST_CODE)
    @OnMPermissionNeverAskAgain(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionFailed() {
        Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
    }

    public void getView() {
        edit_login_account = (EditText) findViewById(R.id.edit_login_account);
        edit_login_password = (EditText) findViewById(R.id.edit_login_password);
        button_login = (Button) findViewById(R.id.button_login);
        register_login_tip = (TextView) findViewById(R.id.register_login_tip);
        button_login.setOnClickListener(this);
        register_login_tip.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_login:
                login();
            break;
            case R.id.register_login_tip:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void login(){
        DialogMaker.showProgressDialog(this, null, getString(R.string.logining), true, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (loginRequest != null) {
                    loginRequest.abort();
                    onLoginDone();
                }
            }
        }).setCanceledOnTouchOutside(false);

        final String account = edit_login_account.getEditableText().toString().toLowerCase();
        final String token = tokenFromPassword(edit_login_password.getEditableText().toString());
        Log.e("TOKEN",token);
        // 登录
        loginRequest = NimUIKit.doLogin(new LoginInfo(account, token), new RequestCallback<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo param) {
                onLoginDone();
                Log.e("OnSuccess","" +
                        "======================");
                MyCache.setAccount(account);
                saveLoginInfo(account, token);

                // 初始化消息提醒配置
                initNotificationConfig();

                // 进入主界面
                HomeActivity.start(LoginActivity.this, null);
                finish();
            }

            @Override
            public void onFailed(int code) {
                onLoginDone();
                if (code == 302 || code == 404) {
                    Toast.makeText(LoginActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败: " + code, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onException(Throwable exception) {
                Toast.makeText(LoginActivity.this, R.string.login_exception, Toast.LENGTH_LONG).show();
                onLoginDone();
            }
        });
    }
    private void initNotificationConfig() {
        // 初始化消息提醒
        NIMClient.toggleNotification(UserPreferences.getNotificationToggle());

        // 加载状态栏配置
        StatusBarNotificationConfig statusBarNotificationConfig = UserPreferences.getStatusConfig();
        if (statusBarNotificationConfig == null) {
            statusBarNotificationConfig = MyCache.getNotificationConfig();
            UserPreferences.setStatusConfig(statusBarNotificationConfig);
        }
        // 更新配置
        NIMClient.updateStatusBarNotificationConfig(statusBarNotificationConfig);
    }

    private void onLoginDone() {
        loginRequest = null;
        DialogMaker.dismissProgressDialog();
    }

    private void saveLoginInfo(final String account, final String token) {
        Preferences.saveUserAccount(account);
        Preferences.saveUserToken(token);
    }

    //DEMO中使用 username 作为 NIM 的account ，md5(password) 作为 token
    //开发者需要根据自己的实际情况配置自身用户系统和 NIM 用户系统的关系
    private String tokenFromPassword(String password) {
        String appKey = readAppKey(this);
        Log.e("appKey",appKey);
        boolean isDemo = "45c6af3c98409b18a84451215d0bdd6e".equals(appKey)
                || "fe416640c8e8a72734219e1847ad2547".equals(appKey);

        return isDemo ? MD5.getStringMD5(password) : password;
    }

    private static String readAppKey(Context context) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo != null) {
                return appInfo.metaData.getString("com.netease.nim.appKey");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * *********** 假登录示例：假登录后，可以查看该用户数据，但向云信发送数据会失败；随后手动登录后可以发数据 **************
     */
    private void fakeLoginTest() {
        // 获取账号、密码；账号用于假登录，密码在手动登录时需要
        final String account = edit_login_account.getEditableText().toString().toLowerCase();
        final String token = tokenFromPassword(edit_login_password.getEditableText().toString());

        // 执行假登录
        boolean res = NIMClient.getService(AuthService.class).openLocalCache(account); // SDK会将DB打开，支持查询。
        Log.i("test", "fake login " + (res ? "success" : "failed"));

        if (!res) {
            return;
        }

        // Demo缓存当前假登录的账号
        MyCache.setAccount(account);

        // 初始化消息提醒配置
        initNotificationConfig();

        // 构建缓存
        //DataCacheManager.buildDataCacheAsync();
        NimUIKit.getImageLoaderKit().buildImageCache();

        // 进入主界面，此时可以查询数据（最近联系人列表、本地消息历史、群资料等都可以查询，但当云信服务器发起请求会返回408超时）
        HomeActivity.start(LoginActivity.this, null);

        // 演示15s后手动登录，登录成功后，可以正常收发数据
        getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loginRequest = NIMClient.getService(AuthService.class).login(new LoginInfo(account, token));
                loginRequest.setCallback(new RequestCallbackWrapper() {
                    @Override
                    public void onResult(int code, Object result, Throwable exception) {
                        Log.i("test", "real login, code=" + code);
                        if (code == ResponseCode.RES_SUCCESS) {
                            saveLoginInfo(account, token);
                            finish();
                        }
                    }
                });
            }
        }, 15 * 1000);
    }
}
