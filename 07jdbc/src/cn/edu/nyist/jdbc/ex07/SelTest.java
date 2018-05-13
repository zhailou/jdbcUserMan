package cn.edu.nyist.jdbc.ex07;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelTest {

	public static void main(String[] args) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=new MyPool().getConnection();
			String sql="select * from tb02";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getString("pwd")+"\t"+rs.getDate("birthday"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.free(rs, stmt, conn);
		}

	}

}
