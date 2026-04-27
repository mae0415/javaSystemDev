package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

    private String baseSql = "select * from student where school_cd=?";

    public Student get(String no) throws Exception {
        Student student = new Student();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("select * from student where no=?");
            statement.setString(1, no);
            ResultSet rSet = statement.executeQuery();

            SchoolDao schoolDao = new SchoolDao();

            if (rSet.next()) {
                student.setNo(rSet.getString("no"));
                student.setName(rSet.getString("name"));
                student.setEntYear(rSet.getInt("ent_year"));
                student.setClassNum(rSet.getString("class_num"));
                student.setAttend(rSet.getBoolean("is_attend"));
                student.setSchool(schoolDao.get(rSet.getString("school_cd")));
            } else {
                student = null;
            }
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return student;
    }

    private List<Student> postFilter(ResultSet rSet, School school) throws Exception {
        List<Student> list = new ArrayList<>();

        while (rSet.next()) {
            Student student = new Student();
            student.setNo(rSet.getString("no"));
            student.setName(rSet.getString("name"));
            student.setEntYear(rSet.getInt("ent_year"));
            student.setClassNum(rSet.getString("class_num"));
            student.setAttend(rSet.getBoolean("is_attend"));
            student.setSchool(school);
            list.add(student);
        }
        return list;
    }

    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        List<Student> list;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        String condition = " and ent_year=? and class_num=?";
        String order = " order by no asc";
        String conditionIsAttend = isAttend ? " and is_attend=true" : "";

        try {
            statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);
            statement.setString(3, classNum);

            ResultSet rSet = statement.executeQuery();
            list = postFilter(rSet, school);
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }

    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
        List<Student> list;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        String condition = " and ent_year=?";
        String order = " order by no asc";
        String conditionIsAttend = isAttend ? " and is_attend=true" : "";

        try {
            statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);

            ResultSet rSet = statement.executeQuery();
            list = postFilter(rSet, school);
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }

    public List<Student> filter(School school, boolean isAttend) throws Exception {
        List<Student> list;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        String order = " order by no asc";
        String conditionIsAttend = isAttend ? " and is_attend=true" : "";

        try {
            statement = connection.prepareStatement(baseSql + conditionIsAttend + order);
            statement.setString(1, school.getCd());

            ResultSet rSet = statement.executeQuery();
            list = postFilter(rSet, school);
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }

    public boolean save(Student student) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count;

        try {
            Student old = get(student.getNo());

            if (old == null) {
                statement = connection.prepareStatement(
                    "INSERT INTO student(no, name, ent_year, class_num, is_attend, school_cd) VALUES(?, ?, ?, ?, ?, ?)"
                );
                statement.setString(1, student.getNo());
                statement.setString(2, student.getName());
                statement.setInt(3, student.getEntYear());
                statement.setString(4, student.getClassNum());
                statement.setBoolean(5, student.isAttend());
                statement.setString(6, student.getSchool() != null ? student.getSchool().getCd() : null);
            } else {
                statement = connection.prepareStatement(
                    "UPDATE student SET name=?, ent_year=?, class_num=?, is_attend=?, school_cd=? WHERE no=?"
                );
                statement.setString(1, student.getName());
                statement.setInt(2, student.getEntYear());
                statement.setString(3, student.getClassNum());
                statement.setBoolean(4, student.isAttend());
                statement.setString(5, student.getSchool() != null ? student.getSchool().getCd() : null);
                statement.setString(6, student.getNo());
            }

            count = statement.executeUpdate();
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return count > 0;
    }
}