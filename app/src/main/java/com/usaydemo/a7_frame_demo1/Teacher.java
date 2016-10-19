/**
 * 老师模型
 */
package com.usaydemo.a7_frame_demo1;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hasee on 2016/5/19.
 */
public class Teacher {
    public Integer getTeacherId() {
        return teacherId;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTeacherJob() {
        return teacherJob;
    }

    public Double getTeacherSalary() {
        return teacherSalary;
    }

    private Integer teacherId;
    private String teacherNo;
    private String teacherName;
    private String teacherJob;
    private Double teacherSalary;
    private Set courses = new HashSet(0);

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setTeacherJob(String teacherJob) {
        this.teacherJob = teacherJob;
    }

    public void setTeacherSalary(Double teacherSalary) {
        this.teacherSalary = teacherSalary;
    }
}
