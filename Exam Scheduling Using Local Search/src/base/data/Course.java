package base.data;

public class Course {
    private int courseCode;
    private int totalStudents;

    public Course(int courseCode, int totalStudents) {
        this.courseCode = courseCode;
        this.totalStudents = totalStudents;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }
}
