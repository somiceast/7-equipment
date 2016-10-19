package com.usaydemo.a7_frame_demo1;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hasee on 2016/5/19.
 * 学生类别：学生id,学号名字，性别，年龄，密码
 */
public class Student {

    public Integer getStudentId() {
        return studentId;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public Integer getStudentAge() {
        return studentAge;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    private String resultNum;

    public String getResultNum() {
        return resultNum;
    }

    private Integer studentId;

    public void setResultNum(String resultNum) {
        this.resultNum = resultNum;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public void setStudentAge(Integer studentAge) {
        this.studentAge = studentAge;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    private String studentNo;
    private String studentName;
    private String studentSex;
    private Integer studentAge;
    private String studentPassword;
    private Set learnings = new HashSet(0);



}
