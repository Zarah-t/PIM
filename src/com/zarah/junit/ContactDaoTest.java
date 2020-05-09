package com.zarah.junit;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alibaba.druid.sql.visitor.functions.Insert;
import com.zarah.bean.Contact;
import com.zarah.dao.ContactDao;
import com.zarah.dao.impl.ContactDaoImpl;
import com.zarah.util.JDBCUtils;

public class ContactDaoTest {
	
	static ContactDao contactDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		contactDao=new ContactDaoImpl();
	}

	@Test
	public void testAddContact() {
		contactDao.addContact(new Contact(null,"dualipa","13555770987","dua_109","182782947","1999-01-01","英国伦敦",1));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testUpdateContact() {
		contactDao.updateContact(new Contact(24,"dualipa","17819008765","dualia_oo","189082231","1999-01-31","英国伦敦",null));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testDeleteContact() {
		contactDao.deleteContact(1);
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testQueryContactById() {
		System.out.println(contactDao.queryContactById(15));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testQueryForPageTotalCount() {
		System.out.println(contactDao.queryForPageTotalCount(2));
	}

	@Test
	public void testQueryForPageItems() {
		List<Contact> page = contactDao.queryForPageItems(2, 5, 5);
		page.forEach(System.out::println);
		
	}

	@Test
	public void testQueryForPageTotalCountByContact() {
		System.out.println(contactDao.queryForPageTotalCountByContact(1, "lisa"));
	}

	@Test
	public void testQueryForPageItemsByContact() {
		List<Contact> contact = contactDao.queryForPageItemsByContact(1, "lisa", 0, 5);
		contact.forEach(System.out::println);
	}

}
