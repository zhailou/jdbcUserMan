package cn.edu.nyist.jdbc.ex04;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Sqllnject {
	//�˴������©��
	//1���������ݿ�����û����ɹ���¼
	//2���������ݿⲻ�����û�����' or 2=2 or ',Ҳ�ܵ�¼�ɹ�������©��������SQLע��
	
	public static void main(String[] args) {
		//1�������û�����
		String name=JOptionPane.showInputDialog("�������û���");
		String pwd=JOptionPane.showInputDialog("����������");
		//2�������ݿ��ѯ
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		boolean ret=false;
		try {
			conn=JDBCUtil.getConn();
			String sql="select * from tb02 where name='"+name+"'+'"+pwd+"'";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				ret=true;
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.free(rs, stmt, conn);
		}
		//3����ʾ��¼�ɹ���ʧ��
		if(ret) {
			System.out.println("��¼�ɹ�");
		}
		else {
			System.out.println("��¼ʧ��");
		}
		
		
		
		
		
		
	}

}
