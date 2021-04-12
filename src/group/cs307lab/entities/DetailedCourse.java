package group.cs307lab.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AnGuangyan
 * @date 2021/3/11
 */
public class DetailedCourse {
    private Course course;
    private int capacity;
    private String teacher;
    private String className;
    private List<ClassInfo> classInfos = new ArrayList<>();

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ClassInfo> getClasses() {
        return classInfos;
    }

    public void setClasses(List<ClassInfo> classInfos) {
        this.classInfos = classInfos;
    }

    @Override
    public String toString() {
        return "DetailedCourse{" +
                "course=" + course.getCourseId() +
                ", capacity=" + capacity +
                ", teacher='" + teacher + '\'' +
                ", className='" + className + '\'' +
                ", classInfos=" + classInfos +
                '}';
    }
}
