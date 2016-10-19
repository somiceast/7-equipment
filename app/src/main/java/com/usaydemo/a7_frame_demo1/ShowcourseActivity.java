package com.usaydemo.a7_frame_demo1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

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

public class ShowcourseActivity extends AppCompatActivity {

    private ListView courselv;
    private TextView demo;

    private String Id = "1";
    private static final String TAG = "ShowcourseActivity";
    private String URL_ = "airing.tunnel.qydev.com/seven/show_my_course.action?studentId=" + Id;
    private String URL = "http://ne.ngrok.cc/seven/show_my_course.action?studentId=1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcourse);

        courselv = (ListView) findViewById(R.id.id_courselistView);
        demo = (TextView) findViewById(R.id.textView4);
        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new showcourAsynctask().execute(URL);
                Log.i(TAG, URL);
            }
        });
        new showcourAsynctask().execute(URL);
    }

    class showcourAsynctask extends AsyncTask<String, Void, List<Course>>{

        @Override
        protected List<Course> doInBackground(String... params) {
            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(List<Course> courses) {
            super.onPostExecute(courses);
            for (int i = 0; i<courses.size();i++){
                Log.i(TAG, courses.get(i).getCourseNo()+i);
            }
            CourselvAdapter courselvadapter = new CourselvAdapter(getApplicationContext(), courses);
            courselv.setAdapter(courselvadapter);
        }
    }

    private List<Course> getJsonData(String url) {
        List<Course> courses = new ArrayList<Course>();
        String jsonstring = null;
        try {
            jsonstring = readStream(new URL(url).openStream());
            Log.i(TAG, jsonstring);
            JSONObject jsonObject;
            Course course;
            //在此增加result验证码的handle
            jsonObject = new JSONObject(jsonstring);
            //获取courseNo的序列
            JSONArray jsonArray = jsonObject.getJSONArray("courseNo");
            for (int i = 0; i<jsonArray.length(); i++){

                course = new Course();
                course.setCourseNo(jsonArray.getString(i));

                JSONArray ajsonArray = jsonObject.getJSONArray("courseName");
                course.setCourseName(ajsonArray.getString(i));

                JSONArray bjsonArray = jsonObject.getJSONArray("teacherName");
                Teacher teacher = new Teacher();
                teacher.setTeacherName(bjsonArray.getString(i));
                course.setTeacher(teacher);

                JSONArray cjsonArray = jsonObject.getJSONArray("courseScore");
                course.setCourseScore(Integer.valueOf(cjsonArray.getString(i)));

                Log.i(TAG, course.getCourseName());
                Log.i(TAG, course.getCourseNo());
                Log.i(TAG, String.valueOf(course.getCourseScore()));
                Log.i(TAG, course.getTeacher().getTeacherName());

                courses.add(course);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {

            e.printStackTrace();
        }
        Log.i(TAG, "你共选了"+String.valueOf(courses.size())+"门课");


        return courses;
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
