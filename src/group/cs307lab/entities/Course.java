package group.cs307lab.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author AnGuangyan
 * @date 2021/3/11
 */
public class Course {
    private String courseId;
    private String courseName;
    private int courseCredit;
    private int courseHour;
    private String prerequisite;
    private Department department;
    private List<DetailedCourse> detailedCourses = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(int courseCredit) {
        this.courseCredit = courseCredit;
    }

    public int getCourseHour() {
        return courseHour;
    }

    public void setCourseHour(int courseHour) {
        this.courseHour = courseHour;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<DetailedCourse> getDetailedCourses() {
        return detailedCourses;
    }

    public void setDetailedCourses(List<DetailedCourse> detailedCourses) {
        this.detailedCourses = detailedCourses;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        return courseId.equals(course.courseId) && courseName.equals(course.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseCredit=" + courseCredit +
                ", courseHour=" + courseHour +
                ", prerequisite='" + prerequisite + '\'' +
                ", department=" + department.getName() +
                ", detailedCourses=" + detailedCourses +
                '}';
    }
}
