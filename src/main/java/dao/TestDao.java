package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Test;

public class TestDao extends Dao {

    public List<Test> filter(int entYear, String classNum, String subjectCd, int num, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        String sql = "select s.no, s.name, t.point " +
                     "from student s left join test t " +
                     "on s.no = t.student_no and t.subject_cd = ? and t.no = ? " +
                     "where s.ent_year = ? and s.class_num = ? and s.school_cd = ?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, subjectCd);
            st.setInt(2, num);
            st.setInt(3, entYear);
            st.setString(4, classNum);
            st.setString(5, school.getCd());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Test test = new Test();
                Student student = new Student();
                student.setNo(rs.getString("no"));
                student.setName(rs.getString("name"));
                test.setStudent(student);
                test.setPoint(rs.getInt("point"));
                list.add(test);
            }
        }
        return list;
    }

    public boolean save(List<Test> list) throws Exception {
        try (Connection con = getConnection()) {
            for (Test test : list) {
                save(test, con);
            }
        }
        return true;
    }

    private boolean save(Test test, Connection con) throws Exception {
        String sql = "merge into test key(student_no, subject_cd, school_cd, no) " +
                     "values(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, test.getStudent().getNo());
            st.setString(2, test.getSubject().getCd());
            st.setString(3, test.getSchool().getCd());
            st.setInt(4, test.getNo());
            st.setInt(5, test.getPoint());
            st.setString(6, test.getClassNum());
            return st.executeUpdate() > 0;
        }
    }
}