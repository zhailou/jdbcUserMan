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
			// 4������SQL��䣬ִ��SQL���
			String sql = "select * from tb02";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			// 5�������
			while (rs.next()) {
				System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getDate("birthday"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			// 6���ر�����,����˳��ģ��ȶϿ���䣬�ٶϿ�����
			
			JDBCUtil.free(rs, stmt, conn);
		}
	}

}




