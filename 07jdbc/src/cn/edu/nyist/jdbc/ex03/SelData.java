package cn.edu.nyist.jdbc.ex03;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelData {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConn();
			// 4、输入SQL语句，执行SQL语句
			String sql = "select * from tb02";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			// 5、看结果
			while (rs.next()) {
				System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getDate("birthday"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			// 6、关闭连接,是有顺序的，先断开语句，再断开连接
			
			JDBCUtil.free(rs, stmt, conn);
		}
	}

}




