package cn.edu.nyist.jdbc.ex04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class NoSqllnject {
	//已修复SQL注入漏洞
	//1、输入数据库存在用户，成功登录
	//2、输入数据库不存在用户，如' or 2=2 or ',也能登录成功，存在漏洞，叫做SQL注入
	
	public static void main(String[] args) {
		//1、接受用户输入
		String name=JOptionPane.showInputDialog("请输入用户名");
		String pwd=JOptionPane.showInputDialog("请输入密码");
		//2、到数据库查询
		Connection conn=null;
		//修改一：换类
		//Statement stmt=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		boolean ret=false;
		try {
			conn=JDBCUtil.getConn();
			//String sql="select * from tb02 where name='"+name+"'+'"+pwd+"'";
			//修改二：不能使用拼接字符串，要用占位符？
			String sql="select * from tb02 where name=? and pwd=?";
			//stmt=conn.createStatement();
			stmt=conn.prepareStatement(sql);
			//对特殊字符进行转义
			stmt.setString(1,name);
			stmt.setString(2,pwd);
			//rs=stmt.executeQuery(sql);
			//修改三：上面已经传入并给占位符赋值了，因此不要再传sql了
			rs=stmt.executeQuery();
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
			System.out.println("合法用户");
		}
		else {
			System.out.println("非法用户");
		}
		
	}

}
