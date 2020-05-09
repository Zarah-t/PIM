package com.zarah.util;

import java.io.CharConversionException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JDBCUtils {
	//创建一个数据库连接池
	static DruidDataSource dataSource;
	//创建一个threadLocal对象
	static ThreadLocal<Connection> threadLocal=new ThreadLocal<>();
	static {
		InputStream is=null;
		try {
			Properties properties = new Properties();
			is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
			properties.load(is);
			dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(is!=null) {
				is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//获取连接
	public static Connection getConnection() throws SQLException{
		//先从ThreadLocal中获取
		Connection connection = threadLocal.get();
		try {
			if(connection==null) {
				//从数据库连接池中获取数据库连接
				connection=dataSource.getConnection();
				//保存到Threadlocal中，供后面操作使用
				threadLocal.set(connection);
				//设置手动管理事务
				connection.setAutoCommit(false);
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
		return connection;
	}
	
	//提交事务并关闭连接
	public static void commitAndClose() {
		Connection connection = threadLocal.get();
		if(connection!=null) {
			try {
				connection.commit();
				connection.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}			
		}
		//因为Tomcat底层使用了线程池，不写此代码就会报错
		threadLocal.remove();
	}
	
	//回滚事务并关闭连接
	public static void rollbackAndClose() {
		Connection connection = threadLocal.get();
		if(connection!=null) {
			try {
				connection.rollback();
				connection.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}			
		}
	}
	
	
/*	//关闭连接
	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}
