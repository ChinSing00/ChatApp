package com.example.vogabond.chatapp.helper.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vogabond.chatapp.R;
import com.example.vogabond.chatapp.helper.ContactHttpClient;
import com.example.vogabond.chatapp.helper.activity.UI;
import com.netease.nim.uikit.common.ui.dialog.DialogMaker;
import com.netease.nim.uikit.common.util.sys.NetworkUtil;

/**
 * Created by Vogabond on 2017/8/9.
 */

public class RegisterActivity extends UI implements View.OnKeyListener,View.OnClickListener{
    private EditText edit_register_account,edit_register_password,edit_register_nickname;
    private Button button_register;

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        getView();
    }

    public void getView() {
        edit_register_account = (EditText) findViewById(R.id.edit_register_account);
        edit_register_password = (EditText) findViewById(R.id.edit_register_password);
        edit_register_nickname = (EditText) findViewById(R.id.edit_register_nickname);
        button_register = (Button) findViewById(R.id.button_register);
        button_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        register();
        }

        private void register(){

            if (!checkRegisterContentValid()) {
                return;
            }
            if (!NetworkUtil.isNetAvailable(RegisterActivity.this)) {
                Toast.makeText(RegisterActivity.this, R.string.network_is_not_available, Toast.LENGTH_SHORT).show();
                return;
            }

            DialogMaker.showProgressDialog(this, getString(R.string.registering), false);

            final String account = edit_register_account.getText().toString();
            final String nickName = edit_register_nickname.getText().toString();
            final String password = edit_register_password.getText().toString();

            ContactHttpClient.getInstance().register(account, nickName, password, new ContactHttpClient.ContactHttpCallback<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(RegisterActivity.this, R.string.register_success, Toast.LENGTH_SHORT).show();

                    edit_register_account.setText(account);
                    edit_register_password.setText(password);
                    edit_register_account.setText("");
                    edit_register_nickname.setText("");
                    edit_register_password.setText("");

                    DialogMaker.dismissProgressDialog();
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailed(int code, String errorMsg) {
                    Toast.makeText(RegisterActivity.this, getString(R.string.register_failed, String.valueOf(code), errorMsg), Toast.LENGTH_SHORT)
                            .show();

                    DialogMaker.dismissProgressDialog();
                }
            });
        }
    private boolean checkRegisterContentValid() {

        // 帐号检查
        String account2 = edit_register_account.getText().toString().trim();
        if (account2.length() <= 0 || account2.length() > 20) {
            Toast.makeText(this, R.string.register_account_tip, Toast.LENGTH_SHORT).show();

            return false;
        }

        // 昵称检查
        String nick = edit_register_nickname.getText().toString().trim();
        if (nick.length() <= 0 || nick.length() > 10) {
            Toast.makeText(this, R.string.register_nick_name_tip, Toast.LENGTH_SHORT).show();

            return false;
        }

        // 密码检查
        String password2 = edit_register_password.getText().toString().trim();
        if (password2.length() < 6 || password2.length() > 20) {
            Toast.makeText(this, R.string.register_password_tip, Toast.LENGTH_SHORT).show();

            return false;
        }

        return true;
    }
}
