package cn.edu.nyist.jdbc.ex04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class NoSqllnject {
	//���޸�SQLע��©��
	//1���������ݿ�����û����ɹ���¼
	//2���������ݿⲻ�����û�����' or 2=2 or ',Ҳ�ܵ�¼�ɹ�������©��������SQLע��
	
	public static void main(String[] args) {
		//1�������û�����
		String name=JOptionPane.showInputDialog("�������û���");
		String pwd=JOptionPane.showInputDialog("����������");
		//2�������ݿ��ѯ
		Connection conn=null;
		//�޸�һ������
		//Statement stmt=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		boolean ret=false;
		try {
			conn=JDBCUtil.getConn();
			//String sql="select * from tb02 where name='"+name+"'+'"+pwd+"'";
			//�޸Ķ�������ʹ��ƴ���ַ�����Ҫ��ռλ����
			String sql="select * from tb02 where name=? and pwd=?";
			//stmt=conn.createStatement();
			stmt=conn.prepareStatement(sql);
			//�������ַ�����ת��
			stmt.setString(1,name);
			stmt.setString(2,pwd);
			//rs=stmt.executeQuery(sql);
			//�޸����������Ѿ����벢��ռλ����ֵ�ˣ���˲�Ҫ�ٴ�sql��
			rs=stmt.executeQuery();
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
			System.out.println("�Ϸ��û�");
		}
		else {
			System.out.println("�Ƿ��û�");
		}
		
	}

}
