package com.zarah.junit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.zarah.bean.Password;
import com.zarah.dao.PasswordDao;
import com.zarah.dao.impl.PasswordDaoImpl;
import com.zarah.util.JDBCUtils;

public class PasswordDaoTest {
	static PasswordDao passwordDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		passwordDao=new PasswordDaoImpl();
	}

	@Test
	public void testAddPassword() {
		passwordDao.addPassword(new Password(null,"b站","b123",1));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testDeletePassword() {
		passwordDao.deletePassword(1);
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testUpdatePassword() {
		passwordDao.updatePassword(new Password(5,"微博","12345678",1));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testQueryPasswordById() {
		Password password = passwordDao.queryPasswordById(1);
		System.out.println(password);
	}

	@Test
	public void testQueryPasswords() {
		List<Password> passwords = passwordDao.queryPasswords(2);
		passwords.forEach(System.out::println);
	}

}
