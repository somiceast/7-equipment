package com.usaydemo.a7_frame_demo1;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hasee on 2016/5/25.
 */
public class SeeallAdapter extends BaseAdapter {

    private List<Learning> students;
    private LayoutInflater inflater;

    public SeeallAdapter(Context context, List<Learning> students1){
        students = students1;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.studentitem, null);
            viewHolder.No = (TextView) convertView.findViewById(R.id.stuitem_no);
            viewHolder.Name = (TextView) convertView.findViewById(R.id.stuitem_na);
            viewHolder.TS = (TextView) convertView.findViewById(R.id.stuitem_ts);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.Name.setText(students.get(position).getStudent().getStudentName());
        viewHolder.No.setText(students.get(position).getStudent().getStudentNo());
        viewHolder.TS.setText(students.get(position).getCourseAchieve().toString());

        return convertView;
    }

    private class ViewHolder {
        public TextView No, Name, TS;
    }
}
