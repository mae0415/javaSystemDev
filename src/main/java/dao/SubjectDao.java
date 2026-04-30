// 野村啓仁
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

// 科目DB用
public class SubjectDao extends Dao {

    // 一覧取得
    public List<Subject> filter(School school) throws Exception 
    {
        List<Subject> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = null;

        try {
            st = con.prepareStatement("SELECT * FROM SUBJECT WHERE SCHOOL_CD = ?");
            st.setString(1, school.getCd());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setCd(rs.getString("CD"));
                s.setName(rs.getString("NAME"));
                list.add(s);
            }
        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
        return list;
    }

    // 1件取得
    public Subject get(String cd, School school) throws Exception {
        Subject subject = null;
        Connection con = getConnection();
        PreparedStatement st = null;

        try {
            st = con.prepareStatement("SELECT * FROM SUBJECT WHERE CD = ? AND SCHOOL_CD = ?");
            st.setString(1, cd);
            st.setString(2, school.getCd());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                subject = new Subject();
                subject.setCd(rs.getString("CD"));
                subject.setName(rs.getString("NAME"));
                subject.setSchool(school);
            }
        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
        return subject;
    }

    // 保存
    public boolean save(Subject subject) throws Exception 
    {
        Connection con = getConnection();
        PreparedStatement st = null;
        int count = 0;

        try {
            st = con.prepareStatement("INSERT INTO SUBJECT(CD, NAME, SCHOOL_CD) VALUES(?, ?, ?)");
            st.setString(1, subject.getCd());
            st.setString(2, subject.getName());
            st.setString(3, subject.getSchool().getCd());
            count = st.executeUpdate();
        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
        return count > 0;
    }

    // 更新
    public boolean update(Subject subject) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = null;
        int count = 0;

        try {
            st = con.prepareStatement("UPDATE SUBJECT SET NAME = ? WHERE CD = ? AND SCHOOL_CD = ?");
            st.setString(1, subject.getName());
            st.setString(2, subject.getCd());
            st.setString(3, subject.getSchool().getCd());
            count = st.executeUpdate();
        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
        return count > 0;
    }

    // 削除
    public boolean delete(Subject subject) throws Exception 
    {
        Connection con = getConnection();
        PreparedStatement st = null;
        int count = 0;

        try {
            st = con.prepareStatement("DELETE FROM SUBJECT WHERE CD = ? AND SCHOOL_CD = ?");
            st.setString(1, subject.getCd());
            st.setString(2, subject.getSchool().getCd());
            count = st.executeUpdate();
        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
        return count > 0;
    }
}