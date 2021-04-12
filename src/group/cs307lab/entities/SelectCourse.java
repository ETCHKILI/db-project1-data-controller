package group.cs307lab.entities;

public class SelectCourse {
    //Guo Yubin, use for java file system test.
    int studentID;
    int mainCourseID;

    public SelectCourse(int studentID, int mainCourseID) {
        this.studentID = studentID;
        this.mainCourseID = mainCourseID;
    }

    public int getStudentID() {
        return studentID;
    }

    @Override
    public String toString() {
        return  studentID + "|" + mainCourseID ;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getMainCourseID() {
        return mainCourseID;
    }

    public void setMainCourseID(int mainCourseID) {
        this.mainCourseID = mainCourseID;
    }
}
