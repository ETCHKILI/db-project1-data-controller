package group.cs307lab.entities;

/**
 * @author AnGuangyan
 * @date 2021/3/11
 */
public class ClassInfo {
    private DetailedCourse course;
    private String location;
    private String classTime;
    private int weekday;
    private WeekList weekList;

    public DetailedCourse getCourse() {
        return course;
    }

    public void setCourse(DetailedCourse course) {
        this.course = course;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public WeekList getWeekList() {
        return weekList;
    }

    public void setWeekList(WeekList weekList) {
        this.weekList = weekList;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "DetailedCourse='" + course.getCourse().getCourseId() +
                "--" + course.getClassName() + '\'' +
                "location='" + location + '\'' +
                ", classTime='" + classTime + '\'' +
                ", weekday=" + weekday +
                ", weekList=" + weekList.getTimeTable() +
                '}';
    }
}
