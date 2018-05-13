package cn.edu.nyist.jdbc.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DelData {

	public static void main(String[] args) throws SQLException {
		// 2、安装驱动
		DriverManager.registerDriver(new Driver());
		// 3、连接到数据库
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "root";
		Connection conn = DriverManager.getConnection(url, user, password);
		// 4、输入SQL语句，执行SQL语句
		String sql = "delete from tb02 where id=3";
		Statement stmt = conn.createStatement();
		int ret = stmt.executeUpdate(sql);
		// 5、看结果
		System.out.println(ret + "行受影响");
		// 6、关闭连接,是有顺序的，先断开语句，再断开连接
		stmt.close();
		conn.close();
	}

}
