// 上村豪
package bean;

import java.io.Serializable;

// 成績
public class Test implements Serializable {
    // 学生
    private Student student;
    // 学生番号
    private String studentNo;
    // 科目
    private Subject subject;
    // 学校
    private School school;
    // クラス番号
    private String classNum;
    // 回数
    private int no;
    // 得点
    private int point;

    // 以下、出し入れ用
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public String getStudentNo() { return studentNo; }
    public void setStudentNo(String studentNo) { this.studentNo = studentNo; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    public School getSchool() { return school; }
    public void setSchool(School school) { this.school = school; }

    public String getClassNum() { return classNum; }
    public void setClassNum(String classNum) { this.classNum = classNum; }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getPoint() { return point; }
    public void setPoint(int point) { this.point = point; }
}