package com.zarah.junit;

import java.sql.Connection;

import org.junit.Test;

import com.zarah.util.JDBCUtils;

public class JDBCUtilsTest {
	@Test
	public void testJDBCUtils() {
		Connection conn=null;
		try {
			conn = JDBCUtils.getConnection();
			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		
			JDBCUtils.commitAndClose();
		}
		
	}
}
