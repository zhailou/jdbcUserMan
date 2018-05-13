package cn.edu.nyist.jdbc.ex07;

import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;
//代理模式
public class MyPool implements DataSource {
//建立连接池
private static List<Connection> pool=Collections.synchronizedList(new ArrayList<>());
//预先在连接池中建立几个连接
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
	if(pool.size()>0) {
		Connection conn=pool.remove(0);
		return (Connection) Proxy.newProxyInstance(null, new Class[] {Connection.class}, new InvocationHandler() {
			
			@Override
			public Object invoke(Object arg0, Method method, Object[] arg2) throws Throwable {
				if(method.getName().equals("close")) {
					pool.add(conn);
				}
				else {
					return method.invoke(conn, arg2);
				}
				return null;
			}
		});
	}
	return null;
}

@Override
public Connection getConnection(String username, String password) throws SQLException {
	// TODO Auto-generated method stub
	return null;
}
}
