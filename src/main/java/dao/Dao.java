// 前田春太
package dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

// 共通の接続用
public class Dao {
	static DataSource ds;

	public Connection getConnection() throws Exception {
		if (ds == null) {
            // 初回のみ実行
			InitialContext ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:/comp/env/jdbc/scoremanager");
		}
		return ds.getConnection();
	}
}