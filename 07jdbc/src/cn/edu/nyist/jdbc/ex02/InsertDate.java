package cn.edu.nyist.jdbc.ex02;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertDate {

	public static void main(String[] args){

		Connection conn = null;
		//发送语句得先有对象,Statement语句的意思
		Statement stmt = null;
		try {
			conn = JDBCUtil.getConn();
			//4、输入SQL语句，执行SQL
			String sql="insert into tb02(name,pwd,birthday) values('sb2','123','1998-8-8')";
			stmt = conn.createStatement();
			int ret=stmt.executeUpdate(sql);//执行SQL
			//5、看结果
			System.out.println(ret+"行受影响");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			//6、关闭连接,是有顺序的，先断开语句，再断开连接
			JDBCUtil.free(stmt, conn);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
