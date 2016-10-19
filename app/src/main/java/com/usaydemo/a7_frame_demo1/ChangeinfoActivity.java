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

public class ChangeinfoActivity extends AppCompatActivity {

    private Button chgInfo;
    private EditText pw_ed, name_ed, sex_ed, age_ed;
    private String Id = "1", pw, name, sex, age;
    private String URL = "airing.tunnel.qydev.com/seven/update_student.action?" +
            "studentId=" + Id +
            "&studentPassword=" + pw +
            "&studentName=" + name +
            "&studentSex=" + sex +
            "&studentAge=" + age;
    private static final String TAG = "ChangeinfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeinfo);

        chgInfo = (Button) findViewById(R.id.id_chginfo_btn);
        pw_ed = (EditText) findViewById(R.id.id_pw_editText);
        name_ed = (EditText) findViewById(R.id.id_name_editText);
        sex_ed = (EditText) findViewById(R.id.id_sex_editText);
        age_ed = (EditText) findViewById(R.id.id_age_editText);

        chgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getmText();
                String URL = "http://ne.ngrok.cc/seven/update_student.action?" +
                        "studentId=" + Id +
                        "&studentPassword=" + pw +
                        "&studentName=" + name +
                        "&studentSex=" + sex +
                        "&studentAge=" + age;
                new ChgInfoAsynctask().execute(URL);
            }
        });
    }

    private void getmText() {
        pw = pw_ed.getText().toString();
        name = name_ed.getText().toString();
        sex = sex_ed.getText().toString();
        age = age_ed.getText().toString();
    }

    class ChgInfoAsynctask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("1")){
                Toast.makeText(getApplicationContext(),"更改成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"未更改成功",Toast.LENGTH_SHORT).show();
            }
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
