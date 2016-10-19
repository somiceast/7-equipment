/**
 * 学生
 * 选课
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
import java.util.ArrayList;
import java.util.List;

public class SelectcourseActivity extends AppCompatActivity {

    private Button selectCourse_btn;
    private EditText courseNo_edit;

    private static final String TAG = "SelectcourseActivity";
    //学生id
    private String Id = "1";
    //课程码
    private String courseNo ="";
    //接口

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectcourse);

        selectCourse_btn = (Button) findViewById(R.id.id_selcourse_btn);
        courseNo_edit = (EditText) findViewById(R.id.id_courseNo_edittext);

        selectCourse_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getmString();
                String URL = "http://ne.ngrok.cc/seven/select_course.action?studentId=" + Id + "&courseNo=" + courseNo;
                Log.d(TAG, URL);
                new selcourseAsyncTask().execute(URL);
            }
        });

    }

    private void getmString() {
         courseNo = courseNo_edit.getText().toString();
    }

    class selcourseAsyncTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            return getJsonData(params[0]);
        }

        //选课回馈
        @Override
        protected void onPostExecute(String s) {
            if(s.equals("1")){
                Toast.makeText(getApplicationContext(),"选上课了",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"没选上课",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getJsonData(String url) {
        String courseresult = "";

        try {
            String jsonString = readStream(new URL(url).openStream());
            Log.i(TAG, jsonString);
            JSONObject jsonObject;
            jsonObject = new JSONObject(jsonString);
            courseresult = jsonObject.getString("result");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  courseresult;
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
