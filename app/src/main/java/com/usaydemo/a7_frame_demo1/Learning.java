package com.usaydemo.a7_frame_demo1;

/**
 * Created by hasee on 2016/5/19.
 */
public class Learning {
    private Integer learningId;
    private Student student;
    private Course course;

    public Integer getCourseAchieve() {
        return courseAchieve;
    }

    public void setCourseAchieve(Integer courseAchieve) {
        this.courseAchieve = courseAchieve;
    }

    public Integer getLearningId() {
        return learningId;
    }

    public void setLearningId(Integer learningId) {
        this.learningId = learningId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    private Integer courseAchieve;
}
