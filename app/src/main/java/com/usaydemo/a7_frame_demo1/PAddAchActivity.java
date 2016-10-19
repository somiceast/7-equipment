/**
 * 添加成绩
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

public class PAddAchActivity extends AppCompatActivity {

    /**
     * courseId
     * StudentId
     * CourseScore
     */
    private EditText padd_ach_couid_edit, padd_ach_stuid_edit, padd_ach_couach_edit;

    private Button cpadd_ach;

    private String courseNo = "1";
    private String studentNo = "1308200047";
    private String courseAchieve ="60";
    private String URL = "http://localhost:8080/seven/add_achieve.action" +
            "?courseNo=" + courseNo +
            "&studentNo=" + studentNo +
            "&courseAchieve=" + courseAchieve;
    private static final String TAG = "PAddAchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padd_ach);

        padd_ach_couid_edit = (EditText) findViewById(R.id.id_courseNo_edit);
        padd_ach_stuid_edit = (EditText) findViewById(R.id.id_stuid_edit);
        padd_ach_couach_edit = (EditText) findViewById(R.id.id_courseach_edit);

        cpadd_ach = (Button) findViewById(R.id.id_cpadd_ach_btn);

        cpadd_ach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getmText();
                new addachAsynctask().execute(URL);
            }
        });
    }

    private void getmText() {
        courseNo = padd_ach_couid_edit.getText().toString();
        courseNo = padd_ach_stuid_edit.getText().toString();
        courseAchieve = padd_ach_couach_edit.getText().toString();
    }

    class addachAsynctask extends AsyncTask<String, Void,String>{
        @Override
        protected String doInBackground(String... params) {
            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("1")){
                Toast.makeText(getApplicationContext(), "成功添加成绩", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(), "成功添加成绩", Toast.LENGTH_SHORT).show();
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
