package group.cs307lab.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AnGuangyan
 * @date 2021/3/11
 */
public class Gender {
    private static final List<Gender> GENDER_LIST = new ArrayList<>();
    static {
        GENDER_LIST.add(new Gender("M"));
        GENDER_LIST.add(new Gender("F"));
    }

    public static Gender getInstance(String gender) {
        gender = gender.trim();
        for (Gender g : GENDER_LIST) {
            if (g.genderName.equals(gender)) {
                return g;
            }
        }
        throw NoSuchInstanceException.getInstance("Gender");
    }

    public static boolean addGender(String gender) {
        gender = gender.trim();
        for (Gender g : GENDER_LIST) {
            if (g.genderName.equals(gender)) {
                return false;
            }
        }
        GENDER_LIST.add(new Gender(gender));
        return true;
    }

    public static Gender getInstance(String gender, Student student) {
        return getInstance(gender).addStudents(student);
    }

    private Gender(String gender) {
        genderName = gender;
    }

    private Gender addStudents(Student student) {
        students.add(student);
        return this;
    }

    private final String genderName;
    private final List<Student> students = new ArrayList<>();

    public String getGenderName() {
        return genderName;
    }
}
