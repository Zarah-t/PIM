package com.zarah.junit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.zarah.bean.Contact;
import com.zarah.bean.Page;
import com.zarah.service.ContactService;
import com.zarah.service.impl.ContactServiceImpl;
import com.zarah.util.JDBCUtils;

public class ContactServiceTest {
	
	private static ContactService contactService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		contactService=new ContactServiceImpl();
	}

	@Test
	public void testAddContact() {
		contactService.addContact(new Contact(null,"dualipa","13555770987","dua_109","182782947","1999-01-01","英国伦敦",1));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testDeleteContact() {
		contactService.deleteContact(24);
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testUpdateContact() {
		contactService.updateContact(new Contact(3,"dualipa","13555770987","dua_109","182782947","1999-01-01","英国伦敦",null));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testQueryContactById() {
		System.out.println(contactService.queryContactById(4));
	}

	@Test
	public void testPage() {
		Page<Contact> page = contactService.page(2, 1, 5);
		page.getItems().forEach(System.out::println);
	}

	@Test
	public void testPageByContact() {
		Page<Contact> pageByContact = contactService.pageByContact(1, "dualipa", 1, 5);
		pageByContact.getItems().forEach(System.out::println);
	}

}
