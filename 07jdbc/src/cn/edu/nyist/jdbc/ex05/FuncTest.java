package cn.edu.nyist.jdbc.ex05;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class FuncTest {
	// ���÷���
	public static void main(String[] args) {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConn();
			String sql = "{?=call upper(?)}";
			stmt = conn.prepareCall(sql);
			//stmt.setFloat(1, -10);
			//ע������ OUT ����������
			stmt.registerOutParameter(1, Types.VARCHAR);
			//stmt.registerOutParameter(2, Types.FLOAT);
			//IN ����ֵ��ʹ�ü̳��� PreparedStatement �� set �������õ�
			stmt.setString(2, "xiaosb");
			stmt.execute();
			//System.out.println(stmt.getFloat(2));
			System.out.println(stmt.getString(1));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.free(rs, stmt, conn);
		}

	}

}
