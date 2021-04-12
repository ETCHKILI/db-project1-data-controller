package group.cs307lab.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AnGuangyan
 * @date 2021/3/11
 */
public class College {
    private static final List<College> COLLEGE_LIST = new ArrayList<>();
    static {
        COLLEGE_LIST.add(new College("阿兹卡班", "Azkaban"));
        COLLEGE_LIST.add(new College("斯莱特林", "Slytherin"));
        COLLEGE_LIST.add(new College("赫奇帕奇", "Hufflepuff"));
        COLLEGE_LIST.add(new College("拉文克劳", "Ravenclaw"));
        COLLEGE_LIST.add(new College("格兰芬多", "Gryffindor"));
    }

    public static College getInstance(String name) {
        name = name.trim();
        for (College c : COLLEGE_LIST) {
            if (c.collegeNameCh.equals(name) || c.collegeNameEn.equals(name)) {
                return c;
            }
        }
        throw NoSuchInstanceException.getInstance("College");
    }
    public static College getInstance(String name, Student student) {
        return getInstance(name).addStudent(student);
    }

    public static boolean addCollege(String collegeNameCh, String collegeNameEn) {
        collegeNameCh = collegeNameCh.trim();
        collegeNameEn = collegeNameEn.trim();
        for (College c : COLLEGE_LIST) {
            if (c.collegeNameCh.equals(collegeNameCh) || c.collegeNameEn.equals(collegeNameEn)) {
                return false;
            }
        }
        COLLEGE_LIST.add(new College(collegeNameCh, collegeNameEn));
        return true;
    }

    private College(String collegeNameCh, String collegeNameEn) {
        this.collegeNameCh = collegeNameCh;
        this.collegeNameEn = collegeNameEn;
    }

    private College addStudent(Student student) {
        students.add(student);
        return this;
    }

    private final String collegeNameCh;
    private final String collegeNameEn;
    private final List<Student> students = new ArrayList<>();

    public String getCollegeNameCh() {
        return collegeNameCh;
    }

    public String getCollegeNameEn() {
        return collegeNameEn;
    }

    public List<Student> getStudents() {
        return students;
    }
}
