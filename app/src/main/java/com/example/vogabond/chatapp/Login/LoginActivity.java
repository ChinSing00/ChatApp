package com.example.vogabond.chatapp.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vogabond.chatapp.R;
import com.example.vogabond.chatapp.main.activity.HomeActivity;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;

/**
 * Created by Vogabond on 2017/8/4.
 */

public class LoginActivity extends Activity implements View.OnClickListener{
    private EditText edit_login_account,edit_login_password;
    private Button button_login;
    private TextView register_login_tip;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        getView();
    }

    public void getView() {
        edit_login_account = findViewById(R.id.edit_login_account);
        edit_login_password = findViewById(R.id.edit_login_password);
        button_login = findViewById(R.id.button_login);
        register_login_tip = findViewById(R.id.register_login_tip);
        button_login.setOnClickListener(this);
        register_login_tip.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_login:
                LoginInfo info = new LoginInfo(edit_login_account.getText().toString().toLowerCase(),edit_login_password.getText().toString()); // config...
                RequestCallback<LoginInfo> callback =
                        new RequestCallback<LoginInfo>() {
                            @Override
                            public void onSuccess(LoginInfo loginInfo) {
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                finish();
                            }
                            @Override
                            public void onFailed(int i) {

                            }

                            @Override
                            public void onException(Throwable throwable) {

                            }
                            // 可以在此保存LoginInfo到本地，下次启动APP做自动登录用
                        };
                NIMClient.getService(AuthService.class).login(info)
                        .setCallback(callback);


            break;
            case R.id.register_login_tip:
                break;
        }
    }
}
