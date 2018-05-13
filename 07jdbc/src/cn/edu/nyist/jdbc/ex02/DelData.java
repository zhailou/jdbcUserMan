package cn.edu.nyist.jdbc.ex02;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DelData {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = JDBCUtil.getConn();
			// 4������SQL��䣬ִ��SQL���
			String sql = "delete from tb02 where id=3";
			stmt = conn.createStatement();
			int ret = stmt.executeUpdate(sql);
			// 5�������
			System.out.println(ret + "����Ӱ��");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			// 6���ر�����,����˳��ģ��ȶϿ���䣬�ٶϿ�����
			JDBCUtil.free(stmt, conn);
		}
	}

}
