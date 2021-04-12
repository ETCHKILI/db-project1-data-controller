package group.cs307lab.manipulator;

import group.cs307lab.entities.Course;
import group.cs307lab.entities.DetailedCourse;
import group.cs307lab.entities.Student;

import java.util.List;

/**
 * @author AnGuangyan
 * @date 2021/3/11
 */
public interface DataManipulation {

    /**
     * Get one student from data
     * @param studentId the student_id of the student you want to find
     * @return null if not found
     */
    Student getOneStudent(int studentId);

    /**
     * find all match to the string of student name,<br/>
     * supported by fuzzywuzzy-java
     * @param name fuzzy name
     * @return an arraylist of most likely students sorted by score from high to low
     */
    List<Student> fuzzyFindStudent(String name);

    /**
     * Get one course specified by id (ie. GE232)
     * @param courseId the course_id of the course, not name
     * @return null if not found
     */
    Course getOneCourseById(String courseId);

    /**
     * find all match to the string of course name,<br/>
     * supported by fuzzywuzzy-java
     * @param name fuzzy name
     * @return an arraylist of most likely course sorted by score from high to low
     */
    List<Course> fuzzyFindCourse(String name);

    /**
     * Get course specified by name, might be multiple (ie. 体育IV)
     * @param courseName the course_name of the course, not id
     * @return an arraylist of course that has the courseName
     */
    List<Course> getCourseByName(String courseName);

    /**
     * Find all detailed_courses specified by Course
     * @param course the course that you want to get detailed courses from
     * @return an arraylist of DetailedCourse
     */
    List<DetailedCourse> getAllDetailedCourseByCourse(Course course);

    /**
     * Find all Courses that one student take
     * @param student the student that you want to get all selected courses from
     * @return an arraylist of Course
     */
    List<Course> getAllCourseByStudent(Student student);

    /**
     * Find all Students in one course
     * @param course the course that you want to get all students from
     * @return an arraylist of Student
     */
    List<Student> getAllStudentByCourse(Course course);

    /**
     * Get the course of the given detailed_course, which should contains a Course that at least has a courseId
     * @param detailedCourse the DetailedCourse that you want to find course from
     * @return should never be null
     */
    Course getCourseByDetailedCourse(DetailedCourse detailedCourse);
}
