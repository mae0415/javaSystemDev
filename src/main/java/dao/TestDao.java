// 上村豪
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

// 成績DB用
public class TestDao extends Dao {

    // 成績一覧の取得
    public List<Test> filter(int entYear, String classNum, Subject subject, int testNo, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            // 学生と成績を結合して取得
            String sql = "SELECT s.no, s.name, s.ent_year, s.class_num, t.point " +
                         "FROM student s " +
                         "LEFT JOIN test t ON s.no = t.student_no AND t.subject_cd = ? AND t.no = ? AND t.school_cd = ? " +
                         "WHERE s.ent_year = ? AND s.class_num = ? AND s.school_cd = ? " +
                         "ORDER BY s.no ASC";

            statement = connection.prepareStatement(sql);
            statement.setString(1, subject.getCd());
            statement.setInt(2, testNo);
            statement.setString(3, school.getCd());
            statement.setInt(4, entYear);
            statement.setString(5, classNum);
            statement.setString(6, school.getCd());

            ResultSet rSet = statement.executeQuery();

            while (rSet.next()) {
                // 学生情報セット
                Student student = new Student();
                student.setNo(rSet.getString("no"));
                student.setName(rSet.getString("name"));
                student.setEntYear(rSet.getInt("ent_year"));
                student.setClassNum(rSet.getString("class_num"));

                // 成績情報セット
                Test test = new Test();
                test.setStudent(student);
                test.setStudentNo(student.getNo());
                test.setNo(testNo);
                
                int point = rSet.getInt("point");
                if (rSet.wasNull()) {
                    // 未受験は-1
                    test.setPoint(-1);
                } else {
                    test.setPoint(point);
                }
                
                test.setSubject(subject);
                test.setSchool(school);
                list.add(test);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            // 接続解除
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return list;
    }

    // リストまとめて保存
    public void save(List<Test> tests) throws Exception {
        Connection connection = getConnection();
        try {
            for (Test test : tests) {
                save(test, connection);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (connection != null) connection.close();
        }
    }

    // 1件保存
    private void save(Test test, Connection connection) throws Exception {
        PreparedStatement statement = null;
        try {
            // あれば更新、なければ追加
            String sql = "MERGE INTO test (student_no, subject_cd, school_cd, no, point, class_num) " +
                         "KEY (student_no, subject_cd, school_cd, no) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            
            statement = connection.prepareStatement(sql);
            statement.setString(1, test.getStudentNo());
            statement.setString(2, test.getSubject().getCd());
            statement.setString(3, test.getSchool().getCd());
            statement.setInt(4, test.getNo());
            statement.setInt(5, test.getPoint());
            statement.setString(6, test.getClassNum());

            statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
        }
    }
}