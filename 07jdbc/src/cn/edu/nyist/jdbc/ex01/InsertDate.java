package cn.edu.nyist.jdbc.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class InsertDate {

	public static void main(String[] args) throws SQLException {
		//1����װjar
		//2����װ����
		DriverManager.registerDriver(new Driver());
		//3�����ӵ����ݿ�
		String url="jdbc:mysql://localhost:3306/test";
		String user="root";
		String password="root";
		Connection conn=DriverManager.getConnection(url, user, password);
		//4������SQL��䣬ִ��SQL
		String sql="insert into tb02(name,pwd,birthday) values('sb2','123','1998-8-8')";
		//�����������ж���,Statement������˼
		Statement stmt=conn.createStatement();
		int ret=stmt.executeUpdate(sql);//ִ��SQL
		//5�������
		System.out.println(ret+"����Ӱ��");
		//6���ر�����,����˳��ģ��ȶϿ���䣬�ٶϿ�����
		stmt.close();
		conn.close();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
