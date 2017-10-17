package com.example.xiaopihaier.cookbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Forget_password extends AppCompatActivity implements View.OnClickListener {

    ImageView black;
    EditText username_input,password_input,Confirmation_password;
    Button Confirmation_modification;

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
        setContentView(R.layout.activity_forget_password);
        InitView();
    }

    private void InitView() {
        username_input= (EditText) findViewById(R.id.username_input);
        password_input= (EditText) findViewById(R.id.password_input);
        Confirmation_password= (EditText) findViewById(R.id.Confirmation_password);
        Confirmation_modification= (Button) findViewById(R.id.Confirmation_modification);
        Confirmation_modification.setOnClickListener(this);
        black = (ImageView) findViewById(R.id.black);
        black.setOnClickListener(this);
    }

    //点击物理返回按钮
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Intent login = new Intent(Forget_password.this, Login.class);
                startActivity(login);
                this.finish();
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.black:
                Intent login = new Intent(Forget_password.this, Login.class);
                startActivity(login);
                this.finish();
                break;
            case R.id.Confirmation_modification:
                username_input.getText().toString();
                Toast.makeText(Forget_password.this,"修改成功",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
