/**
 * 修改教师资料
 */

package com.usaydemo.a7_frame_demo1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

public class PChgTchActivity extends AppCompatActivity {
    private Button btn;
    private EditText No, Name, Job, Salary;

    private static final String TAG = "PChgTchActivity";
    private String no, name, job,salary;
    private String URL = "http://localhost:8080/seven/edit_teacher.action" +
            "?teacherNo=" + no +
            "&teacherName=" + name +
            "&teacherJob=" + job +
            "&teacherSalary=" + salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pchg_tch);

        btn = (Button) findViewById(R.id.chgtch_btn);
        No = (EditText) findViewById(R.id.chgtch_number);
        Name = (EditText) findViewById(R.id.chgtch_name);
        Job = (EditText) findViewById(R.id.chgtch_job);
        Salary = (EditText) findViewById(R.id.chgtch_salary);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getmText();
                new chgtchAsynctask().execute(URL);
            }
        });
    }

    private void getmText() {
        no = No.getText().toString();
        name = Name.getText().toString();
        job = Job.getText().toString();
        salary = Salary.getText().toString();
    }

    class chgtchAsynctask extends AsyncTask<String, Void, String>{

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("1")){
                Toast.makeText(getApplicationContext(), "成功修改教师", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(), "成功修改教师", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            return getJsonData(params[0]);
        }
    }

    private String getJsonData(String url) {
        String chginforesult = "";
        String jsonString = null;

        try {
            jsonString = readStream(new URL(url).openStream());
            Log.i(TAG, jsonString);
            JSONObject jsonObject;
            jsonObject = new JSONObject(jsonString);
            chginforesult = jsonObject.getString("result");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return  chginforesult;
    }

    public String readStream(InputStream is){
        InputStreamReader isr;
        String result = "";

        try {
            String line = "";
            isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            while((line = br.readLine())!= null){
                result+= line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
