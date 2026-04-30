// 野村啓仁
package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// 科目ごとの成績用
public class TestListSubject implements Serializable {

    // 入学年度
    private int entYear;
    // 学生番号
    private String studentNo;
    // 氏名
    private String studentName;
    // クラス名
    private String classNum;
    // 点数（回数と点数のセット）
    private Map<Integer, Integer> points = new HashMap<>();

    // getter・setter
    public int getEntYear() { return entYear; }
    public void setEntYear(int entYear) { this.entYear = entYear; }

    public String getStudentNo() { return studentNo; }
    public void setStudentNo(String studentNo) { this.studentNo = studentNo; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getClassNum() { return classNum; }
    public void setClassNum(String classNum) { this.classNum = classNum; }

    public Map<Integer, Integer> getPoints() { return points; }
    public void setPoints(Map<Integer, Integer> points) { this.points = points; }

    // 点数を取る
    public Integer getPoint(int key) {
        return points.get(key);
    }

    // 点数を入れる
    public void putPoint(int key, int value) {
        points.put(key, value);
    }
}