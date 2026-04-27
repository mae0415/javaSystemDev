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
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    public TestListStudent filter(Student student, School school) throws Exception {
        TestListStudent tls = new TestListStudent();
        tls.setStudent(student);

        List<Test> tests = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "select t.no, t.point, sub.cd as subject_cd, sub.name as subject_name " +
            "from test t " +
            "join subject sub on t.subject_cd = sub.cd and t.school_cd = sub.school_cd " +
            "where t.student_no=? and t.school_cd=? order by sub.cd asc, t.no asc"
        );
        st.setString(1, student.getNo());
        st.setString(2, school.getCd());

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Test test = new Test();
            test.setNo(rs.getInt("no"));
            test.setPoint(rs.getInt("point"));

            Subject sub = new Subject();
            sub.setCd(rs.getString("subject_cd"));
            sub.setName(rs.getString("subject_name"));
            test.setSubject(sub);

            tests.add(test);
        }
        tls.setTests(tests);

        st.close();
        con.close();
        return tls;
    }
}