package com.zarah.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zarah.util.JDBCUtils;

public abstract class BaseDaoImpl {
	private static QueryRunner queryRunner=new QueryRunner();
	
	/**
	 * 
	 * @Description 执行insert、delete、update
	 * @author zarah
	 * @date 2020年2月15日上午1:22:54
	 * @param sql 要执行的sql语句
	 * @param args 占位符的参数
	 * @return -1表示执行失败，其他值表示执行成功
	 */
	public int update(String sql,Object ...args) {
		Connection conn=null;
		try {
			conn = JDBCUtils.getConnection();
			/**
			 * 执行insert、update、delete语句
			 */
			return queryRunner.update(conn, sql, args);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
	
	/**
	 * 
	 * @Description 执行查询返回值是一个javabean的sql语句
	 * @author zarah
	 * @date 2020年2月15日上午1:28:59
	 * @param sql 要执行的sql语句
	 * @param type  返回javabean的具体类型
	 * @param args 占位符的参数
	 * @return 查询失败返回null，返回其它则成功
	 */
	public <T> T queryOne(String sql,Class<T> type,Object ...args) {
		Connection conn=null;
		try {
			conn = JDBCUtils.getConnection();
			return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	/**
	 * 
	 * @Description 执行查询返回值是多个javabean的sql语句
	 * @author zarah
	 * @date 2020年2月15日上午1:28:59
	 * @param sql 要执行的sql语句
	 * @param type  返回javabean的具体类型
	 * @param args 占位符的参数
	 * @return 查询失败返回null，返回其它则成功
	 */
	public <T> List<T> queryList(String sql,Class<T> type,Object ...args) {
		Connection conn=null;
		try {
			conn = JDBCUtils.getConnection();
			return queryRunner.query(conn, sql, new BeanListHandler<T>(type),args);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * @Description 执行查询返回值是单个列的sql语句
	 * @author zarah
	 * @date 2020年2月15日上午1:28:59
	 * @param sql 要执行的sql语句
	 * @param type  返回javabean的具体类型
	 * @param args 占位符的参数
	 * @return 查询失败返回null，返回其它则成功
	 */
	public Object queryForSingleValue(String sql,Object ...args) {
		Connection conn=null;
		try {
			/**
			 * BeanHandler 是将查询到的结果封装为一个javaBean返回
			 * BeanListHandler是将查询到的结果集封装成为一个list集合，并且里面每个元素都是javabean类型
			 * ScalarHandler是将查询到的某个列的结果返回
			 */
			conn = JDBCUtils.getConnection();
			return queryRunner.query(conn, sql, new ScalarHandler(), args);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new RuntimeException();
		}
	}


}