package cn.edu.nyist.jdbc.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class SelData {

	public static void main(String[] args) throws SQLException {
		// 2、安装驱动
		DriverManager.registerDriver(new Driver());
		// 3、连接到数据库
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "root";
		Connection conn = DriverManager.getConnection(url, user, password);
		// 4、输入SQL语句，执行SQL语句
		String sql = "select * from tb02";
		Statement stmt = conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		// 5、看结果
		while (rs.next()) {
			System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getDate("birthday"));
		}
		// 6、关闭连接,是有顺序的，先断开语句，再断开连接
		rs.close();
		stmt.close();
		conn.close();

	}

}
