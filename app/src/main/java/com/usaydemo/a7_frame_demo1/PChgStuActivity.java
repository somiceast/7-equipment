/**
 * 修改学生信息
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

public class PChgStuActivity extends AppCompatActivity {

    private String pw, name, sex, age, no;
    private EditText Pw, Name, Sex, Age, No;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pchg_stu);

        Pw = (EditText) findViewById(R.id.chgstu_pw);
        Name = (EditText) findViewById(R.id.chgstu_name);
        Sex = (EditText) findViewById(R.id.chgstu_sex);
        Age = (EditText) findViewById(R.id.chgstu_age);
        No = (EditText) findViewById(R.id.chgstu_no);

        btn = (Button) findViewById(R.id.chgstu_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getmText();
                String URL = "http://ne.ngrok.cc/seven/edit_student.action" +
                        "?studentNo=" + no +
                        "&studentName=" + name +
                        "&studentSex=" + sex +
                        "&studentAge=" + age +
                        "&studentPassword" + pw;
                new chgstuAsynctask().execute(URL);
            }
        });
    }

    private void getmText() {
        pw = Pw.getText().toString();
        name = Name.getText().toString();
        sex = Sex.getText().toString();
        age = Age.getText().toString();
        no = No.getText().toString();
    }

    class chgstuAsynctask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("1")){
                Toast.makeText(getApplicationContext(), "成功修改学生", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(), "未成功修改学生", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private static final String TAG = "PChgStuActivity";
    private String getJsonData(String url) {
        String chginforesult = "";
        String jsonString = null;

        try {
            jsonString = readStream(new URL(url).openStream());
            Log.i(TAG, jsonString);
            JSONObject jsonObject;
            jsonObject = new JSONObject(jsonString);
            chginforesult = jsonObject.getString("result");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
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
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
