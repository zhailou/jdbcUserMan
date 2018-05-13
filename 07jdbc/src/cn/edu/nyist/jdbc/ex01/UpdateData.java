package cn.edu.nyist.jdbc.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class UpdateData {

	public static void main(String[] args) throws SQLException {
		// 2����װ����
		DriverManager.registerDriver(new Driver());
		// 3�����ӵ����ݿ�
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "root";
		Connection conn = DriverManager.getConnection(url, user, password);
		// 4������SQL��䣬ִ��SQL���
		String sql = "update tb02 set name='dasb' where id=2";
		// ��������
		Statement stmt = conn.createStatement();
		int ret = stmt.executeUpdate(sql);
		// 5�������
		System.out.println(ret + "����Ӱ��");
		// 6���ر�����,����˳��ģ��ȶϿ���䣬�ٶϿ�����
		stmt.close();
		conn.close();

	}

}
