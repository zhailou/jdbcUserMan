package cn.edu.nyist.jdbc.ex04;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Sqllnject {
	//此代码存在漏洞
	//1、输入数据库存在用户，成功登录
	//2、输入数据库不存在用户，如' or 2=2 or ',也能登录成功，存在漏洞，叫做SQL注入
	
	public static void main(String[] args) {
		//1、接受用户输入
		String name=JOptionPane.showInputDialog("请输入用户名");
		String pwd=JOptionPane.showInputDialog("请输入密码");
		//2、到数据库查询
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
		//3、显示登录成功或失败
		if(ret) {
			System.out.println("登录成功");
		}
		else {
			System.out.println("登录失败");
		}
		
		
		
		
		
		
	}

}
