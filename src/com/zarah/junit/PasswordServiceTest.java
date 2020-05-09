package com.zarah.junit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.zarah.bean.Password;
import com.zarah.service.PasswordService;
import com.zarah.service.impl.PasswordServiceImpl;
import com.zarah.util.JDBCUtils;

public class PasswordServiceTest {
	static PasswordService passwordService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		passwordService=new PasswordServiceImpl();
	}

	@Test
	public void testAddPassword() {
		passwordService.addPassword(new Password(null,"qq","123456",1));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testDeletePassword() {
		passwordService.deletePassword(11);
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testUpdatePassword() {
		passwordService.updatePassword(new Password(11,"QQ","qqwer",null));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testQueryPasswordById() {
		Password password = passwordService.queryPasswordById(11);
		System.out.println(password);
		
	}

	@Test
	public void testQueryPasswords() {
		List<Password> passwords = passwordService.queryPasswords(2);
		passwords.forEach(System.out::println);
	}

}
