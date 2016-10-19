/**
 * 学生 模型
 */
package com.usaydemo.a7_frame_demo1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class ShowscoresActivity extends AppCompatActivity {

    private Button fresh;
    private TextView score;
    private String Id = "01";
    private static final String TAG = "ShowscoresActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showscores);

        fresh = (Button) findViewById(R.id.id_freshscore_btn);
        score = (TextView) findViewById(R.id.id_score);
        String URL = "http://ne.ngrok.cc/seven/show_my_scores.action?studentId=" + Id;
        new ShowscoreAsynctask().execute(URL);

        fresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://ne.ngrok.cc/seven/show_my_scores.action?studentId=" + Id;
                new ShowscoreAsynctask().execute(URL);
            }
        });
    }

    class ShowscoreAsynctask extends AsyncTask<String, Void, List<String>> {

        @Override
        protected List<String> doInBackground(String... params) {
            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            Log.i(TAG, "onPostExecute: "+strings.get(0));
            if(strings.get(0).equals("1")){
                Toast.makeText(getApplicationContext(),"刷新成功",Toast.LENGTH_SHORT).show();
                score.setText(strings.get(1));
            }else {
                Toast.makeText(getApplicationContext(),"刷新失败",Toast.LENGTH_LONG).show();
            }
        }
    }

    private List<String> getJsonData(String url) {
        List<String> jsonlist = new ArrayList<String>();
        String jsonString = null;

        try {
            jsonString = readStream(new URL(url).openStream());
            Log.i(TAG, jsonString);
            JSONObject jsonObject;
            jsonObject = new JSONObject(jsonString);
            String result= jsonObject.getString("result");
            String score = jsonObject.getString("totalScore");
            jsonlist.add(result);
            jsonlist.add(score);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonlist;
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
