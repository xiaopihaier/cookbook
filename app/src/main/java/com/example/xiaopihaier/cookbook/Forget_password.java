package com.example.xiaopihaier.cookbook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.xiaopihaier.cookbook.Login.SHOW_RESPONSE;

public class Forget_password extends AppCompatActivity implements View.OnClickListener {

    ImageView black;
    EditText username_input, password_input, Confirmation_password;
    Button Confirmation_modification;
    String Result;

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
        username_input = (EditText) findViewById(R.id.username_input);
        password_input = (EditText) findViewById(R.id.password_input);
        Confirmation_password = (EditText) findViewById(R.id.Confirmation_password);
        Confirmation_modification = (Button) findViewById(R.id.Confirmation_modification);
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
                setHttpURLConnection();
                break;
        }
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    Json(response);
                    if (Result.equals("登陆成功")) {
                        Toast.makeText(Forget_password.this, "修改成功", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Forget_password.this, "修改失败", Toast.LENGTH_LONG).show();
                    }
            }
        }
    };

    private void setHttpURLConnection() {
        //开启线程发送网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection;
                try {
                    URL url = new URL("http://111.231.112.120:8080/AndroidApi/login.jsp?username=" + username_input.getText().toString() + "&password=" + password_input.getText().toString());
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
                        response.append(line);
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

    private void Json(String jsonData) {
        JSONObject object = null;
        try {
            object = new JSONObject(jsonData);
            Result = object.getString("Result");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
