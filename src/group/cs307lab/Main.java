package group.cs307lab;

import group.cs307lab.entities.Course;
import group.cs307lab.entities.SelectCourse;
import group.cs307lab.entities.Student;
import group.cs307lab.manipulator.DataManipulation;
import group.cs307lab.manipulator.DatabaseManipulation;
import group.cs307lab.manipulator.FileManipulation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author AnGuangyan
 * @date 2021/3/11
 */
public class Main {
    public static void main(String[] args) throws IOException {

        long startTime = System.currentTimeMillis();
        FileManipulation manipulation = new FileManipulation("E:\\Project\\Database\\CS307_spring_2021\\projects\\01\\myfile\\select_course.csv");
        var scs = manipulation.getSelectCourses();
        long endTime = System.currentTimeMillis();
        long time1 = endTime - startTime;

        startTime = System.currentTimeMillis();
        var scs2 = new ArrayList<SelectCourse>();
        for (var sc:scs) {
            if (sc.getMainCourseID() < 10 && sc.getMainCourseID() > 2) {
                scs2.add(sc);
            }
        }
        for (int i = 0; i < 500; ++i) {
            System.out.println(String.valueOf(scs2.get(i).getStudentID()) + "|" + String.valueOf(scs2.get(i).getMainCourseID()));
        }
        endTime = System.currentTimeMillis();
        long time2 = endTime - startTime;

        startTime = System.currentTimeMillis();
        scs.sort(new Comparator<SelectCourse>() {
            @Override
            public int compare(SelectCourse o1, SelectCourse o2) {
                if (o1.getMainCourseID() == o2.getMainCourseID()) {
                    return 0;
                } else {
                    return o1.getMainCourseID() > o2.getMainCourseID() ? 1 : -1;
                }
            }
        });
        for (int i = 0; i < 500; ++i) {
            System.out.println(String.valueOf(scs.get(i).getStudentID()) + "|" + String.valueOf(scs.get(i).getMainCourseID()));
        }
        endTime = System.currentTimeMillis();

        System.out.println("read-files-time：" + time1 + "ms");
        System.out.println("select-time：" + time2 + "ms");
        System.out.println("sort-time：" + (endTime - startTime) + "ms");
    }
}
