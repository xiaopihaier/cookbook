package com.example.xiaopihaier.cookbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {
    ImageView black;
    TextView register, forget_password;
    EditText username_input, password_input;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //隐藏标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得窗口对象
        Window myWindow = this.getWindow();
        //设置Flag标识
        myWindow.setFlags(flag, flag);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Initview();
    }

    private void Initview() {
        black = (ImageView) findViewById(R.id.black);
        register = (TextView) findViewById(R.id.register);
        username_input = (EditText) findViewById(R.id.username_input);
        password_input = (EditText) findViewById(R.id.password_input);
        login = (Button) findViewById(R.id.login);
        black.setOnClickListener(this);
        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //退出按钮
            case R.id.black:

                break;
            //注册按钮
            case R.id.register:

                break;
            //登陆按钮
            case R.id.login:

                break;
            default:
                break;
        }
    }
}
