package com.zarah.service.impl;

import java.util.List;

import com.zarah.bean.Contact;
import com.zarah.bean.Daily;
import com.zarah.bean.Page;
import com.zarah.dao.ContactDao;
import com.zarah.dao.impl.ContactDaoImpl;
import com.zarah.service.ContactService;

public class ContactServiceImpl implements ContactService {
	private ContactDao contactDao=new ContactDaoImpl();

	@Override
	public void addContact(Contact contact) {
		contactDao.addContact(contact);
		
	}

	@Override
	public void deleteContact(Integer id) {
		contactDao.deleteContact(id);
		
	}

	@Override
	public void updateContact(Contact contact) {
		contactDao.updateContact(contact);
		
	}

	@Override
	public Contact queryContactById(Integer id) {
		return contactDao.queryContactById(id);
	}

	@Override
	public Page<Contact> page(Integer userId, Integer pageNo, Integer pageSize) {
		Page<Contact> page = new Page<Contact>();
		//设置每页显示数量
		page.setPageSize(pageSize);	
		//求总记录数
		Integer pageTotalCount=contactDao.queryForPageTotalCount(userId);
		//设置总记录数
		page.setPageTotalCount(pageTotalCount);
		//求总页码
		Integer pageTotal=pageTotalCount/pageSize;
		if(pageTotalCount%pageSize>0) {
			pageTotal++;
		}
		//设置总页码
		page.setPageTotal(pageTotal);
		//设置当前页码
		page.setPageNo(pageNo);
		//求begin的值
		Integer begin=(page.getPageNo()-1)*page.getPageSize();
		//获取当前页数据
		List<Contact> items=contactDao.queryForPageItems(userId,begin,page.getPageSize());
		//设置当前也数据
		page.setItems(items);
		
		return page;
	}

	@Override
	public Page<Contact> pageByContact(Integer userId, String contact, Integer pageNo, Integer pageSize) {
		Page<Contact> page = new Page<Contact>();
		//设置每页显示数量
		page.setPageSize(pageSize);	
		//求总记录数
		Integer pageTotalCount=contactDao.queryForPageTotalCountByContact(userId,contact);
		//设置总记录数
		page.setPageTotalCount(pageTotalCount);
		//求总页码
		Integer pageTotal=pageTotalCount/pageSize;
		if(pageTotalCount%pageSize>0) {
			pageTotal++;
		}
		//设置总页码
		page.setPageTotal(pageTotal);
		//设置当前页码
		page.setPageNo(pageNo);
		//求begin的值
		Integer begin=(page.getPageNo()-1)*page.getPageSize();
		//获取当前页数据
		List<Contact> items=contactDao.queryForPageItemsByContact(userId,contact, begin, pageSize);
		//设置当前页数据
		page.setItems(items);
		
		return page;
	}
		


}
