package com.zarah.service;

import com.zarah.bean.Contact;
import com.zarah.bean.Daily;
import com.zarah.bean.Page;

public interface ContactService {
	
	public void addContact(Contact contact);
	
	public void deleteContact(Integer id);
	
	public void updateContact(Contact contact);
	
	public Contact queryContactById(Integer id);
	
	public Page<Contact> page(Integer userId,Integer pageNo,Integer pageSize);
	
	public Page<Contact> pageByContact(Integer userId,String contact,Integer pageNo,Integer pageSize);
}
