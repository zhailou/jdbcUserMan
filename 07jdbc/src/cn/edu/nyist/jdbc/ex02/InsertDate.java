package cn.edu.nyist.jdbc.ex02;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertDate {

	public static void main(String[] args){

		Connection conn = null;
		//�����������ж���,Statement������˼
		Statement stmt = null;
		try {
			conn = JDBCUtil.getConn();
			//4������SQL��䣬ִ��SQL
			String sql="insert into tb02(name,pwd,birthday) values('sb2','123','1998-8-8')";
			stmt = conn.createStatement();
			int ret=stmt.executeUpdate(sql);//ִ��SQL
			//5�������
			System.out.println(ret+"����Ӱ��");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			//6���ر�����,����˳��ģ��ȶϿ���䣬�ٶϿ�����
			JDBCUtil.free(stmt, conn);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
