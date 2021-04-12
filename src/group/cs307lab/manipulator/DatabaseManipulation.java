package group.cs307lab.manipulator;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import group.cs307lab.entities.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author AnGuangyan
 * @date 2021/3/15
 */
public class DatabaseManipulation implements DataManipulation {

    private static final String HOST = "127.0.0.1";
    private static final String DB_NAME = "pro_test1";
    private static final String USER = "postgres";
    private static final String PASSWD = "11912726";
    private static final String PORT = "5432";

    private HikariConfig config;
    private DataSource dataSource;

    public DatabaseManipulation() {
        initDataSource();
    }

    private void initDataSource() {
        String url = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DB_NAME;
        config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setDriverClassName("org.postgresql.Driver");
        config.setUsername(USER);
        config.setPassword(PASSWD);
        config.addDataSourceProperty("connectionTimeout", 1000);
        config.addDataSourceProperty("idleTimeout", 60000);
        config.addDataSourceProperty("maximumPoolSize", 20);
        dataSource = new HikariDataSource(config);
    }

    @Override
    public Student getOneStudent(int studentId) {
        Student student = new Student();
        String sql = """
            SELECT              s.name, g.gender_name,
                                c.name_zh_cn 
            FROM student s 
            JOIN college c 
                on c.id = s.college_id 
            JOIN gender g 
                on g.id = s.gender_id 
            WHERE s.student_id = ?;
            """;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            student.setStudentId(resultSet.getInt(studentId));
            student.setName(resultSet.getString(1));
            student.setGender(Gender.getInstance(resultSet.getString(2)));
            student.setCollege(College.getInstance(resultSet.getString(3)));
        } catch (SQLException e) {
            throw new SqlConnectionException(e);
        }
        return student;
    }

    @Override
    public List<Student> fuzzyFindStudent(String name) {
        //TODO implement this method
        return new ArrayList<>();
    }

    @Override
    public Course getOneCourseById(String courseId) {
        Course course = new Course();
        String sql = """
                SELECT          m.course_name, d.name, 
                                m.course_credit, m.course_hour
                FROM main_course m 
                JOIN departments d 
                    on d.id = m.dept_id
                WHERE m.course_id = ?;
                """;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, courseId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            course.setCourseId(courseId);
            course.setCourseName(resultSet.getString(1));
            course.setDepartment(Department.getInstance(resultSet.getString(2)));
            course.setCourseCredit(resultSet.getInt(3));
            course.setCourseHour(resultSet.getInt(4));
        } catch (SQLException e) {
            throw new SqlConnectionException(e);
        }
        return course;
    }

    @Override
    public List<Course> fuzzyFindCourse(String name) {
        //TODO implement this method
        return new ArrayList<>();
    }

    @Override
    public List<Course> getCourseByName(String courseName) {
        List<Course> courses = new ArrayList<>();
        String sql = """
                SELECT          m.course_id, d.name, 
                                m.course_credit, m.course_hour,
                                m.prerequisite
                FROM main_course m 
                JOIN departments d 
                    on d.id = m.dept_id
                WHERE m.course_name = ?;
                """;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, courseName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Course course = new Course();
                course.setCourseName(courseName);
                course.setCourseId(resultSet.getString(1));
                course.setDepartment(Department.getInstance(resultSet.getString(2)));
                course.setCourseCredit(resultSet.getInt(3));
                course.setCourseHour(resultSet.getInt(4));
                course.setPrerequisite(resultSet.getString(5));
                courses.add(course);
            }
        } catch (SQLException e) {
            throw new SqlConnectionException(e);
        }
        return courses;
    }

    @Override
    public List<DetailedCourse> getAllDetailedCourseByCourse(Course course) {
        List<DetailedCourse> courses = new ArrayList<>();
        String sql = """
                SELECT          total_capacity, teacher, class_name
                FROM class_detail c
                JOIN main_course mc
                    on mc.id = c.main_course_id
                WHERE main_course_id = ?;
                """;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, course.getCourseId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DetailedCourse co = new DetailedCourse();
                co.setCourse(course);
                co.setCapacity(resultSet.getInt(1));
                co.setTeacher(resultSet.getString(2));
                co.setClassName(resultSet.getString(3));
                courses.add(co);
            }
        } catch (SQLException e) {
            throw new SqlConnectionException(e);
        }
        return courses;
    }

    @Override
    public List<Course> getAllCourseByStudent(Student student) {
        List<Course> courses = new ArrayList<>();
        String sql = """
                SELECT          course_id, course_name,
                                course_credit, course_hour, 
                                prerequisite, d.name
                FROM main_course mc
                JOIN select_course sc 
                    on mc.id = sc.main_course_id
                JOIN student s 
                    on s.student_id = sc.student_id
                JOIN departments d 
                    on d.id = mc.dept_id
                WHERE s.student_id = ?;
                """;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, student.getStudentId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Course course = new Course();
                course.setCourseId(resultSet.getString(1));
                course.setCourseName(resultSet.getString(2));
                course.setCourseCredit(resultSet.getInt(3));
                course.setCourseHour(resultSet.getInt(4));
                course.setPrerequisite(resultSet.getString(5));
                course.setDepartment(Department.getInstance(resultSet.getString(6)));
                courses.add(course);
            }
        } catch (SQLException e) {
            throw new SqlConnectionException(e);
        }
        return courses;
    }

    @Override
    public List<Student> getAllStudentByCourse(Course course) {
        List<Student> students = new ArrayList<>();
        String sql = """
                SELECT          s.student_id, s.name,
                                g.gender_name, c.name_zh_cn
                FROM student s 
                JOIN college c 
                    on c.id = s.college_id
                JOIN gender g 
                    on g.id = s.gender_id
                JOIN select_course sc 
                    on s.student_id = sc.student_id
                JOIN main_course mc 
                    on mc.id = sc.main_course_id
                WHERE mc.course_id = ?;
                """;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, course.getCourseId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getInt(1));
                student.setName(resultSet.getString(2));
                student.setGender(Gender.getInstance(resultSet.getString(3)));
                student.setCollege(College.getInstance(resultSet.getString(4)));
                students.add(student);
            }
        } catch (SQLException e) {
            throw new SqlConnectionException(e);
        }
        return students;
    }

    @Override
    public Course getCourseByDetailedCourse(DetailedCourse detailedCourse) {
        Course course = new Course();
        String sql = """
                SELECT          m.course_name, d.name, 
                                m.course_credit, m.course_hour
                FROM main_course m 
                JOIN departments d 
                    on d.id = m.dept_id
                join class_detail cd 
                    on m.id = cd.main_course_id
                WHERE m.course_id = ? AND cd.class_name = ?;
                """;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, detailedCourse.getCourse().getCourseId());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            course.setCourseId(detailedCourse.getCourse().getCourseId());
            course.setCourseName(resultSet.getString(1));
            course.setDepartment(Department.getInstance(resultSet.getString(2)));
            course.setCourseCredit(resultSet.getInt(3));
            course.setCourseHour(resultSet.getInt(4));
            course.getDetailedCourses().add(detailedCourse);
            detailedCourse.setCourse(course);
        } catch (SQLException e) {
            throw new SqlConnectionException(e);
        }
        return course;
    }

    public static class SqlConnectionException extends RuntimeException {
        private final SQLException exception;

        public SqlConnectionException(SQLException e) {
            super("Sql Connection Failed: " + e.getSQLState());
            exception = e;
        }

        public SQLException getException() {
            return exception;
        }
    }
}
