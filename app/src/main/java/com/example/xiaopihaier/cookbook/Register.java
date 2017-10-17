package com.example.xiaopihaier.cookbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class Register extends AppCompatActivity implements View.OnClickListener {

    ImageView black;

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
        setContentView(R.layout.activity_register);
        InitView();
    }

    private void InitView() {
        black = (ImageView) findViewById(R.id.black);
        black.setOnClickListener(this);
    }

    //点击物理返回按钮
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Intent login = new Intent(Register.this, Login.class);
                startActivity(login);
                this.finish();
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.black:
                Intent login = new Intent(Register.this, Login.class);
                startActivity(login);
                this.finish();
                break;
        }
    }
}
