package com.example.xiaopihaier.cookbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

public class Forget_password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
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
}
