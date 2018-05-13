package cn.edu.nyist.jdbc.ex05;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreTest03 {
	// 调用存储过程
	public static void main(String[] args) {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConn();
			String sql = "{call xx()}";
			stmt = conn.prepareCall(sql);
			//stmt.setFloat(1, -10);
		//	stmt.registerOutParameter(2, Types.FLOAT);
			boolean hasResult=stmt.execute();
			while (hasResult) {
				rs=stmt.getResultSet();
				while(rs.next()) {
					System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getString("pwd")+"\t");
				}
				System.out.println("===========================");
				hasResult=stmt.getMoreResults();
			}
		

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.free(rs, stmt, conn);
		}

	}

}
