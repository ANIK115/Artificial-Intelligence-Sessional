package base.data;

import java.util.List;

public class Student {
    private int studentId;
    private List<Course> courses;

    public Student(int studentId, List<Course> courses) {
        this.studentId = studentId;
        this.courses = courses;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
