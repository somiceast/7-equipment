package com.usaydemo.a7_frame_demo1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hasee on 2016/5/22.
 */
public class CourselvAdapter extends BaseAdapter {

    private List<Course> courseList;
    private LayoutInflater inflater;
    private static final String TAG = "CourselvAdapter";

    public CourselvAdapter(Context context, List<Course> courses){
        courseList = courses;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        Log.i("HH", "courseList的数量：" +courseList.size());
        return courseList.size();
    }

    @Override
    public Object getItem(int position) {
        Log.d(TAG, "CC" + position + "]");
        return courseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.d(TAG, "getItemId() called with: " + "position = [" + position + "]");
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.course_item, null);
            viewHolder.courseNo = (TextView) convertView.findViewById(R.id.id_courseNo_text);
            viewHolder.courseName = (TextView) convertView.findViewById(R.id.id_courseName_text);
            viewHolder.teacherName = (TextView) convertView.findViewById(R.id.id_teacherName_text);
            viewHolder.courseScore = (TextView) convertView.findViewById(R.id.id_abccoursescore_text);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.courseNo.setText(courseList.get(position).getCourseNo());
        viewHolder.courseName.setText(courseList.get(position).getCourseName());
        viewHolder.teacherName.setText(courseList.get(position).getTeacher().getTeacherName());
        viewHolder.courseScore.setText(courseList.get(position).getCourseScore().toString());

        Log.d(TAG, "getView() called with: " + "position = [" + position + "], convertView = [" + convertView + "], parent = [" + parent + "]");
        return convertView;
    }

    private class ViewHolder {
        public TextView courseNo, courseName, teacherName, courseScore;
    }
}
