package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    public Subject get(String cd, School school) throws Exception {
        Subject subject = null;
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from subject where cd=? and school_cd=?");
        
        st.setString(1, cd);
        st.setString(2, school.getCd());
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            subject = new Subject();
            subject.setCd(rs.getString("cd"));
            subject.setName(rs.getString("name"));
            subject.setSchool(school); 
        }

        st.close();
        con.close();
        return subject;
    }

    public List<Subject> filter(School school) throws Exception {
        List<Subject> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from subject where school_cd=? order by cd asc");
        
        st.setString(1, school.getCd());
        ResultSet rs = st.executeQuery();
        
        while (rs.next()) {
            Subject s = new Subject();
            s.setCd(rs.getString("cd"));
            s.setName(rs.getString("name"));
            list.add(s);
        }
        
        st.close();
        con.close();
        return list;
    }

    public boolean save(Subject subject) throws Exception {
        Connection con = getConnection();
        int count = 0;
        Subject check = get(subject.getCd(), subject.getSchool());
        
        if (check == null) {
            PreparedStatement st = con.prepareStatement("insert into subject (cd, name, school_cd) values (?, ?, ?)");
            st.setString(1, subject.getCd());
            st.setString(2, subject.getName());
            st.setString(3, subject.getSchool().getCd());
            count = st.executeUpdate();
            st.close();
        } else {
            PreparedStatement st = con.prepareStatement("update subject set name=? where cd=? and school_cd=?");
            st.setString(1, subject.getName());
            st.setString(2, subject.getCd());
            st.setString(3, subject.getSchool().getCd());
            count = st.executeUpdate();
            st.close();
        }
        
        con.close();
        return count > 0;
    }

    public boolean delete(Subject subject) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("delete from subject where cd=? and school_cd=?");
        
        st.setString(1, subject.getCd());
        st.setString(2, subject.getSchool().getCd());
        int count = st.executeUpdate();
        
        st.close();
        con.close();
        return count > 0;
    }
}