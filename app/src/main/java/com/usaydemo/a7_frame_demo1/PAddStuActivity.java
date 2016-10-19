/**
 * 添加学生
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

public class PAddStuActivity extends AppCompatActivity {

    private Button btn;
    private EditText No, Name, Sex, Age;
    private static final String TAG = "PAddStuActivity";
    private String no, name, sex, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padd_stu);

        btn = (Button) findViewById(R.id.addstu_btn);
        No = (EditText) findViewById(R.id.addstu_id);
        Name = (EditText) findViewById(R.id.addstu_name);
        Sex = (EditText) findViewById(R.id.addstu_sex);
        Age = (EditText) findViewById(R.id.addstu_age);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getmText();
                String URL = "http://ne.ngrok.cc/seven/add_student.action" +
                        "?studentNo=" + no +
                        "&studentName=" + name +
                        "&studentSex=" + sex +
                        "&studentAge=" + age;
                new addStuAsynctask().execute(URL);
            }
        });
    }

    private void getmText() {
        no = No.getText().toString();
        name = Name.getText().toString();
        sex = Sex.getText().toString();
        age = Age.getText().toString();
    }

    class addStuAsynctask extends AsyncTask<String, Void, String>{

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("1")){
                Toast.makeText(getApplicationContext(), "成功添加学生", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(), "未成功添加学生", Toast.LENGTH_SHORT).show();
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
