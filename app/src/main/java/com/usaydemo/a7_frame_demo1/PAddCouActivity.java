/**
 * 添加课程
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

public class PAddCouActivity extends AppCompatActivity {

    private Button btn;
    private EditText couId, teaId, couName, Score;
    private String couid, teaid, couname, score;

    //http://ne.ngrok.cc/seven/add_course.action?courseNo=1&teacherNo=3&courseName=%E8%8B%B1%E8%AF%AD&courseScore=2
    private static final String TAG = "PAddCouActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padd_cou);

        btn = (Button) findViewById(R.id.addcou_btn);
        
        couId = (EditText) findViewById(R.id.addcou_couid);
        teaId = (EditText) findViewById(R.id.addcou_teaid);
        couName = (EditText) findViewById(R.id.addcou_couname);
        Score = (EditText) findViewById(R.id.addcou_score);

        Log.i(TAG, "onCreate: ");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "777");
                getmText();
                Log.i(TAG, couid);
                String URL = "http://ne.ngrok.cc/seven/add_course.action" +
                        "?courseNo=" + couid +
                        "&teacherNo=" + teaid +
                        "&courseName=" + couname +
                        "&courseScore=" + score;
                Log.i(TAG, URL);
                new addcouAsynctask().execute(URL);
            }
        });
    }

    private void getmText() {
        couid = couId.getText().toString();
        teaid = teaId.getText().toString();
        couname = couName.getText().toString();
        score = Score.getText().toString();
    }
    
    class addcouAsynctask extends AsyncTask<String, Void, String>{

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("1")){
                Toast.makeText(getApplicationContext(), "成功添加课程", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(), "成功添加课程", Toast.LENGTH_SHORT).show();
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
