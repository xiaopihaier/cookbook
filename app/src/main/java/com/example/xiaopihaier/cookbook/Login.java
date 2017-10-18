package com.example.xiaopihaier.cookbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AppCompatActivity implements View.OnClickListener {
    ImageView black;
    TextView register, forget_password;
    EditText username_input, password_input;
    Button login;
    //记录用户首次点击返回键的时间
    private long firstTime = 0;
    static final int SHOW_RESPONSE = 0;


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
        InitView();
    }

    private void InitView() {
        black = (ImageView) findViewById(R.id.black);
        register = (TextView) findViewById(R.id.register);
        username_input = (EditText) findViewById(R.id.username_input);
        password_input = (EditText) findViewById(R.id.password_input);
        login = (Button) findViewById(R.id.login);
        forget_password = (TextView) findViewById(R.id.forget_password);
        black.setOnClickListener(this);
        register.setOnClickListener(this);
        login.setOnClickListener(this);
        forget_password.setOnClickListener(this);
        password_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            //回车键事件
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    password_input.setCursorVisible(false);
                    // 关闭软键盘
                    InputMethodManager imm_down = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    // 得到InputMethodManager的实例
                    if (imm_down.isActive()) {
                        // 如果开启
                        imm_down.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                                InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                return false;
            }
        });
    }

    //点击物理返回按钮
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(Login.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                    return true;
                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //退出按钮
            case R.id.black:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(Login.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                } else {
                    System.exit(0);
                }
                break;
            //注册按钮
            case R.id.register:
                Intent register = new Intent(Login.this, Register.class);
                startActivity(register);
                this.finish();
                break;
            //登陆按钮
            case R.id.login:
                if (username_input.getText().toString().trim().equals("")) {
                    Toast.makeText(Login.this, "请输入用户名", Toast.LENGTH_LONG).show();
                } else if (password_input.getText().toString().trim().equals("")) {
                    Toast.makeText(Login.this, "请输入密码", Toast.LENGTH_LONG).show();
                } else {
                    setHttpURLConnection();
                }

                break;
            //忘记密码
            case R.id.forget_password:
                Intent forget_password = new Intent(Login.this, Forget_password.class);
                startActivity(forget_password);
                this.finish();
                break;
            default:
                break;
        }
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    Toast.makeText(Login.this, response, Toast.LENGTH_LONG).show();
            }
        }
    };

    private void setHttpURLConnection() {
        //开启线程发送网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://164e1o5218.51mypc.cn/AndroidApi/login.jsp?username=123&password=12");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    //对获得的数据进行读取
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        //response.append(line);
                        Log.i("msg1", response.append(line).toString());
                    }
                    Message message = new Message();
                    message.what = SHOW_RESPONSE;
                    //将服务器返回的值放入message中
                    message.obj = response.toString();
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
