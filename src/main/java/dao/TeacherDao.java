// 小垣幸流
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;

// 先生用DB
public class TeacherDao extends Dao {
    
    // ログイン処理
    public Teacher login(String id, String password) throws Exception {
        Teacher teacher = null;
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("select * from teacher where id=? and password=?")) {
            st.setString(1, id);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                // ログイン成功
                teacher = new Teacher();
                teacher.setId(rs.getString("id"));
                teacher.setName(rs.getString("name"));
                School school = new School();
                school.setCd(rs.getString("school_cd"));
                teacher.setSchool(school);
            }
        }
        return teacher;
    }
}