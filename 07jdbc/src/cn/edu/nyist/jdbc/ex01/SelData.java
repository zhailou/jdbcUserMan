package cn.edu.nyist.jdbc.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class SelData {

	public static void main(String[] args) throws SQLException {
		// 2����װ����
		DriverManager.registerDriver(new Driver());
		// 3�����ӵ����ݿ�
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "root";
		Connection conn = DriverManager.getConnection(url, user, password);
		// 4������SQL��䣬ִ��SQL���
		String sql = "select * from tb02";
		Statement stmt = conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		// 5�������
		while (rs.next()) {
			System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getDate("birthday"));
		}
		// 6���ر�����,����˳��ģ��ȶϿ���䣬�ٶϿ�����
		rs.close();
		stmt.close();
		conn.close();

	}

}
