package com.usaydemo.a7_frame_demo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminlogActivity extends AppCompatActivity {

    private String id, pw;
    private EditText adminId, adminPw;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlog);

        btn = (Button) findViewById(R.id.adminlogBtn);
        adminId = (EditText) findViewById(R.id.adminId);
        adminPw = (EditText) findViewById(R.id.adminPassword);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getmText();
                //
                Intent a = new Intent(getApplicationContext(), AdmMainActivity.class);
                startActivity(a);
            }
        });
    }

    private void getmText() {
        id = adminId.getText().toString();
        pw = adminPw.getText().toString();
    }
}
