package cn.edu.nyist.jdbc.ex03;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateData {

	public static void main(String[] args) {
		
		Connection conn = null;
		// 创建对象
		Statement stmt = null;
		try {
			conn = JDBCUtil.getConn();
			// 4、输入SQL语句，执行SQL语句
			String sql = "update tb02 set name='dasb' where id=2";
			stmt = conn.createStatement();
			int ret = stmt.executeUpdate(sql);
			// 5、看结果
			System.out.println(ret + "行受影响");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			// 6、关闭连接,是有顺序的，先断开语句，再断开连接
			JDBCUtil.free(stmt, conn);
		}

	}

}
