package com.example.vogabond.chatapp.helper.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vogabond.chatapp.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText User, Password;
    private Button Login;
    private TextView Rest_password, Register_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initid();
    }



    private void initid() {
        User = (EditText) findViewById(R.id.user);
        Password = (EditText) findViewById(R.id.password);
        Login = (Button) findViewById(R.id.login);
        Register_user = (TextView) findViewById(R.id.register_user);
        Rest_password = (TextView) findViewById(R.id.rest_password);
        Login.setOnClickListener(this);
        Register_user.setOnClickListener(this);
        Rest_password.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                String user = User.getText().toString().trim();
                String password = Password.getText().toString().trim();
                if (User.getText() == null || Password.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (user.equals("123") && (password.equals("123"))) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                break;
//            case R.id.rest_password:
//                Intent intent = new Intent(this, ResetActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.register_user:
//                Intent intent1 = new Intent(this, RegisterActivity.class);
//                startActivity(intent1);
//                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
