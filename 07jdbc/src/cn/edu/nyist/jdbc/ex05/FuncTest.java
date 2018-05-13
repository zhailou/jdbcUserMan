package cn.edu.nyist.jdbc.ex05;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class FuncTest {
	// 调用方法
	public static void main(String[] args) {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConn();
			String sql = "{?=call upper(?)}";
			stmt = conn.prepareCall(sql);
			//stmt.setFloat(1, -10);
			//注册所有 OUT 参数的类型
			stmt.registerOutParameter(1, Types.VARCHAR);
			//stmt.registerOutParameter(2, Types.FLOAT);
			//IN 参数值是使用继承自 PreparedStatement 的 set 方法设置的
			stmt.setString(2, "xiaosb");
			stmt.execute();
			//System.out.println(stmt.getFloat(2));
			System.out.println(stmt.getString(1));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.free(rs, stmt, conn);
		}

	}

}
