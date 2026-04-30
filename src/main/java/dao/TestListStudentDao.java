// 前田春太
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

// 学生ごとの成績一覧用
public class TestListStudentDao extends Dao {

    // 科目名も一緒に取るSQL
    private String baseSql = 
        "SELECT sj.name AS subject_name, sj.cd AS subject_cd, t.no, t.point " +
        "FROM test t " +
        "JOIN subject sj ON t.subject_cd = sj.cd AND t.school_cd = sj.school_cd " + 
        "WHERE t.student_no = ? " +
        "ORDER BY sj.cd ASC, t.no ASC";

    // 学生を指定して検索
    public List<TestListStudent> filter(Student student) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(baseSql)) {
            st.setString(1, student.getNo());
            try (ResultSet rs = st.executeQuery()) {
                list = postFilter(rs);
            }
        }
        return list;
    }

    // Listへの変換処理
    private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        while (rSet.next()) {
            TestListStudent bean = new TestListStudent();
            bean.setSubjectName(rSet.getString("subject_name"));
            bean.setSubjectCd(rSet.getString("subject_cd"));
            bean.setNum(rSet.getInt("no"));
            bean.setPoint(rSet.getInt("point"));
            list.add(bean);
        }
        return list;
    }
}