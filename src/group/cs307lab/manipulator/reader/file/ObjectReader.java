package group.cs307lab.manipulator.reader.file;

import group.cs307lab.entities.*;
import group.cs307lab.manipulator.FileManipulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author AnGuangyan
 * @date 2021/3/11
 */
public class ObjectReader {
    public static final String COURSE = "Course";
    public static final String STUDENT = "Student";
    public static final String DETAILED_COURSE = "DetailedCourse";
    public static final String CLASS_INFO = "ClassInfo";

    private static final Map<String, FileReader> READER_MAP = new HashMap<>();
    private static final Map<String, Class<?>> CLASS_MAP = new HashMap<>();
    static {
        READER_MAP.put(COURSE, ObjectReader::courseReader);
        CLASS_MAP.put(COURSE, Course.class);
        READER_MAP.put(STUDENT, ObjectReader::studentReader);
        CLASS_MAP.put(STUDENT, Student.class);
        READER_MAP.put(DETAILED_COURSE, ObjectReader::detailedCourseReader);
        CLASS_MAP.put(DETAILED_COURSE, DetailedCourse.class);
        READER_MAP.put(CLASS_INFO, ObjectReader::classInfoReader);
        CLASS_MAP.put(CLASS_INFO, ClassInfo.class);
    }

    public static FileReader getReader(String[] name) {
        return READER_MAP.get(name[0]);
    }

    public static Class<?> getClass(String[] name) {
        return CLASS_MAP.get(name[0]);
    }

    private ObjectReader() {}

    private static Course courseReader(String[] arr, FileManipulation fileManipulation) {
        Course course = new Course();
        course.setCourseId(arr[1].trim());
        course.setCourseName(arr[2].trim());
        course.setCourseCredit(Integer.parseInt(arr[3]));
        course.setCourseHour(Integer.parseInt(arr[4]));
        course.setPrerequisite(arr[5]);
        course.setDepartment(Department.getInstance(arr[6]));
        return course;
    }

    private static Student studentReader(String[] arr, FileManipulation fileManipulation) {
        fileManipulation.indexCourses();
        Student student = new Student();
        student.setStudentId(Integer.parseInt(arr[1]));
        student.setName(arr[2].trim());
        student.setGender(Gender.getInstance(arr[3]));
        student.setCollege(College.getInstance(arr[4]));
        List<Course> courses = new ArrayList<>();
        Course course;
        int len = arr.length;
        for (int i = 5; i < len; i++) {
            course = fileManipulation.getOneCourseById(arr[i].trim());
            course.getStudents().add(student);
            courses.add(course);
        }
        student.setCourses(courses);
        return student;
    }

    private static DetailedCourse detailedCourseReader(String[] arr, FileManipulation fileManipulation) {
        fileManipulation.indexStudents();
        DetailedCourse detailedCourse = new DetailedCourse();
        Course course = fileManipulation.getOneCourseById(arr[1]);
        course.getDetailedCourses().add(detailedCourse);
        detailedCourse.setCourse(course);
        detailedCourse.setCapacity(Integer.parseInt(arr[2]));
        detailedCourse.setTeacher(arr[3]);
        detailedCourse.setClassName(arr[4].trim());
        return detailedCourse;
    }

    private static ClassInfo classInfoReader(String[] arr, FileManipulation fileManipulation) {
        ClassInfo classInfo = new ClassInfo();
        List<DetailedCourse> courses = fileManipulation.getOneCourseById(arr[1]).getDetailedCourses();
        for (DetailedCourse c : courses) {
            if (c.getClassName().equals(arr[2].trim())) {
                c.getClasses().add(classInfo);
                classInfo.setCourse(c);
                break;
            }
        }
        classInfo.setWeekList(WeekList.getInstance(arr[3]));
        classInfo.setLocation(arr[4]);
        classInfo.setClassTime(arr[5]);
        classInfo.setWeekday(Integer.parseInt(arr[6]));
        return classInfo;
    }
}
