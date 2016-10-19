/**
 * 学生登录
 */
package com.usaydemo.a7_frame_demo1;

import android.content.Intent;
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
import java.util.List;

public class StulogActivity extends AppCompatActivity {

    private static String URL_ = "http://ne.ngrok.cc/seven/login.action?";
    private String URL;
    private Button StulogBtn;
    private EditText studentNo, studentPassword;
    private String mstudentNo, mstudentPassword;

    private static final String TAG = "StulogActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stulog);

        StulogBtn = (Button) findViewById(R.id.StulogBtn);
        studentNo = (EditText) findViewById(R.id.studentNo);
        studentPassword = (EditText) findViewById(R.id.studentPassword);



        StulogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                URL = URL_ + "studentNo=" + mstudentNo + "&" + "studentPassword=" +mstudentPassword;

                {
                    Log.i(TAG, URL);
                    Log.i(TAG, mstudentNo);
                }


                new stuAsyncTask().execute(URL);
            }
        });
    }

    private void getData() {
        mstudentNo = studentNo.getText().toString();
        mstudentPassword = studentPassword.getText().toString();
    }

    //参数：String是目的网址==params
    class stuAsyncTask extends AsyncTask<String,Void,Student> {

        @Override
        protected Student doInBackground(String... params) {
            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(Student student) {
            if (student.getStudentSex()!=null){

                if (student.getResultNum() == null){
                    Log.d(TAG, "onPostExecute() called with: " + "student = [" + student + "]");
                }else{
                    if(student.getResultNum().equals("1")){
                        Toast.makeText(getApplicationContext(),"登陆成功",Toast.LENGTH_SHORT).show();
                        Intent StuMain = new Intent(getApplicationContext(),StuMainActivity.class);
                        startActivity(StuMain);

                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(),"登陆失败",Toast.LENGTH_SHORT).show();
                    }
                }
            }else{
                Toast.makeText(getApplicationContext(),"该用户不存在",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Student getJsonData(String url) {
        Student student = new Student();
        try {
            String jsonString = readStream(new URL(url).openStream());

            Log.i(TAG, jsonString);

            JSONObject jsonObject;

            jsonObject = new JSONObject(jsonString);
            String resultNum = jsonObject.getString("result");
            student.setResultNum(jsonObject.getString("result"));
            //
            student.setStudentAge(jsonObject.getInt("studentAge"));
            student.setStudentId(jsonObject.getInt("studentId"));
            student.setStudentName(jsonObject.getString("studentName"));
            student.setStudentSex(jsonObject.getString("studentSex"));
            student.setStudentNo(jsonObject.getString("studentNo"));
            Log.i(TAG, resultNum);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return student;
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
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
