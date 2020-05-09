package com.zarah.dao;

import java.util.List;

import com.zarah.bean.Contact;

public interface ContactDao {
	public int addContact(Contact contact);
	
	public int updateContact(Contact contact);
	
	public int deleteContact(Integer id);
	
	public Contact queryContactById(Integer id);
	
	public Integer queryForPageTotalCount(Integer userId);

	public List<Contact> queryForPageItems(Integer userId,Integer begin, Integer pageSize);
	
	public Integer queryForPageTotalCountByContact(Integer userId,String contact );

	public List<Contact> queryForPageItemsByContact(Integer userId,String contact,Integer begin, Integer pageSize);
	
	
}
