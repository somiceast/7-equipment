package com.usaydemo.a7_frame_demo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Stu_login, Tea_login, text;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Stu_login = (Button) findViewById(R.id.Stu_login);
        Tea_login = (Button) findViewById(R.id.Tea_login);
        text = (Button) findViewById(R.id.text_etc);

        Stu_login.setOnClickListener(this);
        Tea_login.setOnClickListener(this);
        text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Stu_login:
                Log.i(TAG, "onClick: 学生登录");
                Intent stulog = new Intent(getApplicationContext(),StulogActivity.class);
                startActivity(stulog);
                break;
            case R.id.Tea_login:
                Log.i(TAG, "onClick: 教师登录");
                Intent tealog = new Intent(getApplicationContext(),AdmMainActivity.class);
                startActivity(tealog);
                break;
            case R.id.text_etc:
                Toast.makeText(getApplicationContext(), "注意：此app仅为样品demo，具体功能参照视频，如真正应用请联系开发人员",Toast.LENGTH_LONG).show();
        }
    }
}
