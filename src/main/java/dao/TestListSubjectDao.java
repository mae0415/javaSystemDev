// 野村啓仁
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bean.School;
import bean.TestListSubject;

// 科目別の成績一覧用
public class TestListSubjectDao extends Dao {

    // メインのSQL
    private String baseSql = 
        "SELECT s.ent_year, s.class_num, s.no AS student_no, s.name AS student_name, t.no AS test_no, t.point " +
        "FROM student s " +
        "LEFT JOIN test t ON s.no = t.student_no AND t.subject_cd = ? " +
        "WHERE s.ent_year = ? AND s.class_num = ? AND s.school_cd = ? " +
        "ORDER BY s.no ASC, t.no ASC";

    // 条件を指定して検索
    public List<TestListSubject> filter(int entYear, String classNum, String subjectCd, School school) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(baseSql);
            statement.setString(1, subjectCd);
            statement.setInt(2, entYear);
            statement.setString(3, classNum);
            statement.setString(4, school.getCd());

            ResultSet rSet = statement.executeQuery();
            
            // リストにまとめる
            list = postFilter(rSet);

        } catch (Exception e) {
            throw e;
        } finally {
            // 接続解除
            if (statement != null) {
                try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException sqle) { throw sqle; }
            }
        }
        return list;
    }

    // ResultSetをBeanのリストに変換
    private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        String lastStudentNo = "";
        TestListSubject currentBean = null;

        while (rSet.next()) {
            String studentNo = rSet.getString("student_no");

            // 学生が変わったら新しい箱を作る
            if (!studentNo.equals(lastStudentNo)) {
                currentBean = new TestListSubject();
                currentBean.setEntYear(rSet.getInt("ent_year"));
                currentBean.setClassNum(rSet.getString("class_num"));
                currentBean.setStudentNo(studentNo);
                currentBean.setStudentName(rSet.getString("student_name"));
                currentBean.setPoints(new HashMap<Integer, Integer>());
                list.add(currentBean);
                lastStudentNo = studentNo;
            }

            // 点数があれば追加
            int testNo = rSet.getInt("test_no");
            int point = rSet.getInt("point");
            if (!rSet.wasNull()) {
                currentBean.putPoint(testNo, point);
            }
        }
        return list;
    }
}