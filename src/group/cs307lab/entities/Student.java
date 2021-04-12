package group.cs307lab.entities;

import java.util.List;
import java.util.Objects;

/**
 * @author AnGuangyan
 * @date 2021/3/11
 */
public class Student {
    private int studentId;
    private String name;
    private Gender gender;
    private College college;
    private List<Course> courses;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return studentId == student.studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", college=" + college +
                ", courses=" + courses +
                '}';
    }
}
