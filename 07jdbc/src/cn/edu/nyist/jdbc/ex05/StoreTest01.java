package cn.edu.nyist.jdbc.ex05;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class StoreTest01 {
	// 调用存储过程
	public static void main(String[] args) {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConn();
			String sql = "{call abs1(?,?)}";
			stmt = conn.prepareCall(sql);
			stmt.setFloat(1, -10);
			stmt.registerOutParameter(2, Types.FLOAT);
			stmt.execute();
			System.out.println(stmt.getFloat(2));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.free(rs, stmt, conn);
		}

	}

}
