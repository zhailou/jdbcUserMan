package cn.edu.nyist.jdbc.ex06;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class MyConnectionPool implements DataSource{
	//�������ӳ�
	//Collections.synchronizedList()����̲߳���ȫ����
	private static List<Connection> pool=Collections.synchronizedList(new ArrayList<>());
	//Ԥ�Ƚ����������ӣ�static������ص�ʱ�����
	static {
		for(int i=1;i<=4;i++) {
			try {
				pool.add(JDBCUtil.getConn());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setLogWriter(PrintWriter arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setLoginTimeout(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Connection getConnection() throws SQLException {
		//�ӳ���������
		if(pool.size()>0) {
			System.out.println("�ӳ��л�ȡ��һ������");
		return 	new ConnectionWrapper(pool, pool.remove(0));
		}
		return null;
	}
	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
