package cn.edu.nyist.jdbc.ex03;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
	private static Properties pro;
	static {
		/*
		 * �˷���ע����2������
		try {
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//�÷��似������װ����
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//��ȡ�ļ���ֻ��Ҫ��ȡһ�μ��ɣ����� ����static��
	static {
		pro=new Properties();
		try {
			pro.load(JDBCUtil.class.getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static Connection getConn() throws SQLException {

		// 1����װjar
		// 2��ע����������װ������
		//DriverManager.registerDriver(new Driver());
		// 3�����ӵ����ݿ�
		String url = pro.getProperty("url");
		String user = pro.getProperty("user");
		String password =pro.getProperty("password");
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	public static void free(Statement stmt,Connection conn) {
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	public static void free(ResultSet rs,Statement stmt,Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}

