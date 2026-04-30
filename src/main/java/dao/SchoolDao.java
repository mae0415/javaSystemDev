// 上村豪
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.School;

// 学校DB用
public class SchoolDao extends Dao {

    // データ取得
    public School get(String cd) throws Exception 
    {
        School school = new School();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            // SQL準備
            statement = connection.prepareStatement("select * from school where cd = ?");
            statement.setString(1, cd);
            ResultSet rSet = statement.executeQuery();

            if (rSet.next()) {
                // 取得成功
                school.setCd(rSet.getString("cd"));
                school.setName(rSet.getString("name"));
            } else {
                // 該当なし
                school = null;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            // 接続解除
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) { throw sqle; }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return school;
    }
}