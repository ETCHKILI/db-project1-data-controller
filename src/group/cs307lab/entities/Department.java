package group.cs307lab.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AnGuangyan
 * @date 2021/3/11
 */
public class Department {
    private static final List<Department> DEPARTMENTS = new ArrayList<>();
    static {
        DEPARTMENTS.add(new Department("体育中心"));
        DEPARTMENTS.add(new Department("电子与电气工程系"));
        DEPARTMENTS.add(new Department("化学系"));
        DEPARTMENTS.add(new Department("生物系"));
        DEPARTMENTS.add(new Department("环境科学与工程学院"));
        DEPARTMENTS.add(new Department("计算机科学与工程系"));
        DEPARTMENTS.add(new Department("机械与能源工程系"));
        DEPARTMENTS.add(new Department("材料科学与工程系"));
        DEPARTMENTS.add(new Department("医学院"));
        DEPARTMENTS.add(new Department("思想政治教育与研究中心"));
        DEPARTMENTS.add(new Department("地球与空间科学系"));
        DEPARTMENTS.add(new Department("海洋科学与工程系"));
        DEPARTMENTS.add(new Department("数学系"));
        DEPARTMENTS.add(new Department("生物医学工程系"));
        DEPARTMENTS.add(new Department("力学与航空航天工程系"));
        DEPARTMENTS.add(new Department("物理系"));
        DEPARTMENTS.add(new Department("金融系"));
        DEPARTMENTS.add(new Department("社会科学中心"));
        DEPARTMENTS.add(new Department("语言中心"));
        DEPARTMENTS.add(new Department("艺术中心"));
        DEPARTMENTS.add(new Department("人文科学中心"));
        DEPARTMENTS.add(new Department("创新创业学院"));
        DEPARTMENTS.add(new Department("高等教育研究中心"));
    }

    public static Department getInstance(String name) {
        name = name.trim();
        for (Department d : DEPARTMENTS) {
            if (d.name.equals(name)) {
                return d;
            }
        }
        throw NoSuchInstanceException.getInstance("Department");
    }

    public static Department getInstance(String name, Course course) {
        return getInstance(name).addCourse(course);
    }

    public static boolean addDepartment(String name) {
        name = name.trim();
        for (Department d : DEPARTMENTS) {
            if (d.name.equals(name)) {
                return false;
            }
        }
        DEPARTMENTS.add(new Department(name));
        return true;
    }

    private Department(String name) {
        this.name = name;
    }

    private Department addCourse(Course course) {
        courses.add(course);
        return this;
    }

    private final String name;
    private final List<Course> courses = new ArrayList<>();

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
