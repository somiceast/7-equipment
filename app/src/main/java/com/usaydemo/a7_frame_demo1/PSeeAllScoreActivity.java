/**
 * 查看成绩
 */

package com.usaydemo.a7_frame_demo1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
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

public class PSeeAllScoreActivity extends AppCompatActivity {

    private ListView allscolistview;
    private TextView demo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psee_all_score);

        allscolistview = (ListView) findViewById(R.id.seeall_listview);
        demo = (TextView) findViewById(R.id.seeall_tv);
        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getmText();
                String URL = "http://ne.ngrok.cc/seven/add_achieve.action?courseNo=1"
                         + "&studentNo=1" + "&courseAchieve=1";
                new seeallAsynctask().execute(URL);
            }
        });
    }

    private void getmText() {

    }

    class seeallAsynctask extends AsyncTask<String , Void, List<Learning> >{
        @Override
        protected void onPostExecute(List<Learning> s) {
            if(s.equals("1")){
                Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
            }else {
            Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
            }
            SeeallAdapter seeallAdapter = new SeeallAdapter(getApplicationContext(), s);
            allscolistview.setAdapter(seeallAdapter);
        }

        @Override
        protected List<Learning> doInBackground(String... params) {
            return getJsonData(params[0]);
        }
    }
    private static final String TAG = "PChgCouActivity";
    private List<Learning> getJsonData(String url) {
        List<Learning> learnings = new ArrayList<Learning>();
        String jsonString = null;

        try {
            jsonString = readStream(new URL(url).openStream());
            Log.i(TAG, jsonString);
            JSONObject jsonObject;
            Learning learning;
            jsonObject = new JSONObject(jsonString);
            String result = jsonObject.getString("result");
            //TODO 提示
            JSONArray jsonArray = jsonObject.getJSONArray("studentNos");
            for (int i = 0; i<jsonArray.length(); i++) {

                learning = new Learning();

                Student a = new Student();
                a.setStudentNo(jsonArray.getString(i));
                learning.setStudent(a);

                JSONArray ajsonArray = jsonObject.getJSONArray("studentNames");
                a.setStudentName(ajsonArray.getString(i));
                learning.setStudent(a);

                JSONArray bjsonArray = jsonObject.getJSONArray("totalScores");
                learning.setCourseAchieve(Integer.valueOf(bjsonArray.getString(i)));

                learnings.add(learning);
            }
        } catch (IOException | JSONException e) {
                e.printStackTrace();
        }
        return learnings;
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
