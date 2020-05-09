package com.zarah.junit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.zarah.bean.User;
import com.zarah.dao.UserDao;
import com.zarah.dao.impl.UserDaoImpl;
import com.zarah.service.UserService;
import com.zarah.service.impl.UserServiceImpl;
import com.zarah.util.JDBCUtils;

public class UserServiceTest {
	private static UserService userService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userService=new UserServiceImpl();
	}

	@Test
	public void testLogin() {
		System.out.println(userService.login(new User(null,"admin","admin",null)));
		System.out.println(userService.login(new User(null,"admin","admin2",null)));
	}

	@Test
	public void testRegister() {
		userService.register(new User(null,"lina","123","lina@126.com"));
		userService.register(new User(null,"john","123","lina@126.com"));
	}

	@Test
	public void testExistsUsername() {
		if(userService.existsUsername(null)){
			System.out.println("用户名已存在");
		}else {
			System.out.println("用户名可用");
		}
		
	}
	
	@Test
	public void testUpdatePassword() {
		if(userService.updatePassword(9, "zzzzz")){
			System.out.println("修改成功");
		}else {
			System.out.println("修改失败");
		}
		JDBCUtils.commitAndClose();
		
	}
	
	@Test
	public void testUpdateEmail() {
		if(userService.existsEmail("1666172268@qq.com")==null){
			System.out.println("邮箱不存在");
		}else {
			System.out.println("邮箱存在");
		}
		JDBCUtils.commitAndClose();
		
	}

}
