package com.zarah.junit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.zarah.bean.User;
import com.zarah.dao.UserDao;
import com.zarah.dao.impl.UserDaoImpl;

public class UserDaoTest {
	static UserDao userDao;
	/**
	 * @BeforeClass 注解可以让被标注的方法在所有测试方法执行之前执行一次
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		userDao=new UserDaoImpl();
	}

	@Test
	public void testQueryUserByUsernameAndPassword() {
		//UserDao userDao=new UserDaoImpl();
		System.out.println(userDao.queryUserByUsernameAndPassword("admin", "admin"));
		System.out.println(userDao.queryUserByUsernameAndPassword("admin", "admin2"));
	}

	@Test
	public void testSaveUser() {
		System.out.println(3);
		UserDao userDao=new UserDaoImpl();
		User user=new User(null,"leru","123","leru@123.com");
		System.out.println(userDao.saveUser(user));
	}

	@Test
	public void testQueryUserByUsername() {
		//UserDao userDao=new UserDaoImpl();
		System.out.println(userDao.queryUserByUsername("admin"));
		System.out.println(userDao.queryUserByUsername("admin2"));
	}

}
