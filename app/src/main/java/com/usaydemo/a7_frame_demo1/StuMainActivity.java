package com.usaydemo.a7_frame_demo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StuMainActivity extends AppCompatActivity implements View.OnClickListener {

    //初始化按钮 选课，更改个人信息，查询选课，查询总分
    private Button selectcourse, changeinfo, showcourse, showscores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_main);

        selectcourse = (Button) findViewById(R.id.id_selectcourse_btn);
        changeinfo = (Button) findViewById(R.id.id_showstudent_btn);
        showcourse = (Button) findViewById(R.id.id_showmycourses_btn);
        showscores = (Button) findViewById(R.id.id_showmyscores_btn);

        selectcourse.setOnClickListener(this);
        changeinfo.setOnClickListener(this);
        showcourse.setOnClickListener(this);
        showscores.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_selectcourse_btn:
                mIntent(SelectcourseActivity.class);
                break;
            case R.id.id_showmycourses_btn:
                mIntent(ShowcourseActivity.class);
                break;
            case R.id.id_showmyscores_btn:
                mIntent(ShowscoresActivity.class);
                break;
            case R.id.id_showstudent_btn:
                mIntent(ChangeinfoActivity.class);
                break;
        }
    }

    private void mIntent(Class b) {
        Intent a = new Intent(getApplicationContext(),b);
        startActivity(a);
    }
}
