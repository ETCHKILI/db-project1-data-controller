package group.cs307lab.manipulator;

import group.cs307lab.entities.*;
import group.cs307lab.manipulator.reader.file.ObjectReader;
import me.xdrop.fuzzywuzzy.*;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author AnGuangyan
 * @date 2021/3/11
 */
public class FileManipulation implements DataManipulation{

    private final Scanner scanner;
    private final Map<Integer, Student> studentMap = new HashMap<>();
    private final Map<String, Course> courseMap = new HashMap<>();
    private final List<Student> students = new ArrayList<>();

    private final List<Course> courses = new ArrayList<>();
    private final List<DetailedCourse> detailedCourses = new ArrayList<>();
    private final List<ClassInfo> classInfos = new ArrayList<>();

    public List<SelectCourse> selectCourses = new ArrayList<>();

    private boolean indexedCourses = false;
    private boolean indexedStudents = false;

    public FileManipulation(String name) throws IOException{
        scanner = new Scanner(FileUtils.openInputStream(FileUtils.getFile(name)));
        readFileCSV();
    }

    private void readFile() {
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            strHandler(line);
        }
    }

    private void readFileCSV() {
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            strHandlerCSV(line);
        }
    }

    private void strHandler(String line) {
        if ("".equals(line.trim())) {
            return;
        }
        String[] parts = line.split("--");
        storeObject(ObjectReader.getReader(parts).read(parts, this), parts);
    }

    private void strHandlerCSV(String line) {
        // Guo Yubin, use for java file system test.
        if ("".equals(line.trim())) {
            return;
        }
        String[] parts = line.split(",");
        SelectCourse sc = new SelectCourse(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
        selectCourses.add(sc);
    }

    private void storeObject(Object o, String[] name) {
        Class<?> clazz = ObjectReader.getClass(name);
        if (Course.class.equals(clazz)) {
            courses.add((Course) o);
        } else if (Student.class.equals(clazz)) {
            students.add((Student) o);
        } else if (DetailedCourse.class.equals(clazz)) {
            detailedCourses.add((DetailedCourse) o);
        } else if (ClassInfo.class.equals(clazz)) {
            classInfos.add((ClassInfo) o);
        }  else {
            throw NoSuchInstanceException.getInstance("Class");
        }
    }

    public void indexCourses() {
        if (!indexedCourses) {
            for (Course c : courses) {
                courseMap.put(c.getCourseId(), c);
            }
            indexedCourses = true;
        }
    }

    public void indexStudents() {
        if (!indexedStudents) {
            for (Student s : students) {
                studentMap.put(s.getStudentId(), s);
            }
            indexedStudents = true;
        }
    }

    @Override
    public Student getOneStudent(int studentId) {
        return studentMap.get(studentId);
    }

    @Override
    public List<Student> fuzzyFindStudent(String name) {
        return fuzzyFindT(name, students, Student::getName);
    }

    @Override
    public Course getOneCourseById(String courseId) {
        return courseMap.get(courseId.trim());
    }

    @Override
    public List<Course> fuzzyFindCourse(String name) {
        return fuzzyFindT(name, courses, Course::getCourseId);
    }

    private <T> List<T> fuzzyFindT(String name, List<T> list, ToStringFunction<T> func) {
        name = name.trim();
        List<T> varList = new ArrayList<>();
        var res = FuzzySearch.extractSorted(name, list, func);
        for (var v : res) {
            varList.add(v.getReferent());
        }
        return varList;
    }

    @Override
    public List<Course> getCourseByName(String courseName) {
        courseName = courseName.trim();
        List<Course> list = new ArrayList<>();
        for (Course c : courses) {
            if (c.getCourseName().equals(courseName)) {
                list.add(c);
            }
        }
        return list;
    }

    @Override
    public List<DetailedCourse> getAllDetailedCourseByCourse(Course course) {
        return course.getDetailedCourses();
    }

    @Override
    public List<Course> getAllCourseByStudent(Student student) {
        return student.getCourses();
    }

    @Override
    public List<Student> getAllStudentByCourse(Course course) {
        return course.getStudents();
    }

    @Override
    public Course getCourseByDetailedCourse(DetailedCourse detailedCourse) {
        return null;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<DetailedCourse> getDetailedCourses() {
        return detailedCourses;
    }

    public List<ClassInfo> getClassInfos() {
        return classInfos;
    }

    public List<SelectCourse> getSelectCourses() {
        return selectCourses;
    }

    public void setSelectCourses(List<SelectCourse> selectCourses) {
        this.selectCourses = selectCourses;
    }
}
