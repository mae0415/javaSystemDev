package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    public List<TestListSubject> filter(int entYear, String classNum, String subjectCd, School school) throws Exception {
        Map<String, TestListSubject> map = new HashMap<>();
        
        String sql = "select s.no as student_no, s.name as student_name, t.no as test_no, t.point, sub.name as subject_name " +
                     "from student s " +
                     "left join test t on s.no = t.student_no and t.subject_cd = ? " +
                     "left join subject sub on t.subject_cd = sub.cd and t.school_cd = sub.school_cd " +
                     "where s.ent_year=? and s.class_num=? and s.school_cd=? " +
                     "order by s.no asc";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            
            st.setString(1, subjectCd);
            st.setInt(2, entYear);
            st.setString(3, classNum);
            st.setString(4, school.getCd());
            
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    String sNo = rs.getString("student_no");
                    TestListSubject tls = map.getOrDefault(sNo, new TestListSubject());
                    
                    if (tls.getStudent() == null) {
                        tls.setEntYear(entYear);
                        tls.setClassNum(classNum);
                        tls.setSubjectCd(subjectCd);
                        tls.setSubjectName(rs.getString("subject_name"));
                        
                        Student s = new Student();
                        s.setNo(sNo);
                        s.setName(rs.getString("student_name"));
                        tls.setStudent(s);
                        
                        tls.setPoint1(-1);
                        tls.setPoint2(-1);
                    }
                    
                    int count = rs.getInt("test_no");
                    int point = rs.getInt("point");
                    if (count == 1) {
                        tls.setPoint1(point);
                    } else if (count == 2) {
                        tls.setPoint2(point);
                    }
                    map.put(sNo, tls);
                }
            }
        }
        return new ArrayList<>(map.values());
    }
}