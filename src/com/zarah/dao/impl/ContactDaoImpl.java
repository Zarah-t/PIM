package com.zarah.dao.impl;

import java.util.List;

import com.zarah.bean.Contact;
import com.zarah.bean.Daily;
import com.zarah.dao.ContactDao;

public class ContactDaoImpl extends BaseDaoImpl implements ContactDao {

	@Override
	public int addContact(Contact contact) {
		String sql="INSERT INTO t_contact(`contact`,`phone` , `wechat` , `qq` , `birth`,`address`,`user_id`) VALUES(?,?,?,?,?,?,?)";
		return update(sql, contact.getContact(),contact.getPhone(),contact.getWechat(),contact.getQq(),contact.getBirth(),contact.getAddress(),contact.getUserId());
	}

	@Override
	public int updateContact(Contact contact) {
		String sql="update t_contact set `contact`=?,`phone`=?,`wechat`=?,`qq`=?,`birth`=?,`address`=? where `id`=?";
		return update(sql, contact.getContact(),contact.getPhone(),contact.getWechat(),contact.getQq(),contact.getBirth(),contact.getAddress(),contact.getId());
	}

	@Override
	public int deleteContact(Integer id) {
		String sql="delete from t_contact where id=?";
		return update(sql, id);
	}

	@Override
	public Contact queryContactById(Integer id) {
		String sql="select `id` , `contact`, `phone` ,`wechat`, `qq`,`birth`,`address`,`user_id` userId from t_contact where id=?";
		return queryOne(sql, Contact.class, id);
	}

	@Override
	public Integer queryForPageTotalCount(Integer userId) {
		String sql="select count(*) from t_contact where user_id=?";
		return new Integer(queryForSingleValue(sql,userId).toString()) ;
	}

	@Override
	public List<Contact> queryForPageItems(Integer userId, Integer begin, Integer pageSize) {
		String sql="select `id` , `contact`, `phone` ,`wechat`, `qq`,`birth`,`address`,`user_id` userId from t_contact where user_id=? limit ?,?";
		return queryList(sql,Contact.class, userId,begin, pageSize);
	}

	@Override
	public Integer queryForPageTotalCountByContact(Integer userId, String contact) {
		String sql="select count(*) from t_contact where user_id=? and `contact`=?";
		Object count = queryForSingleValue(sql,userId,contact);		
		return new Integer(count.toString());
	}

	@Override
	public List<Contact> queryForPageItemsByContact(Integer userId, String contact, Integer begin, Integer pageSize) {
		String sql="select `id` , `contact`, `phone` ,`wechat`, `qq`,`birth`,`address`,`user_id` userId from t_contact where user_id=? and `contact`=? limit ?,?";
		return queryList(sql,Contact.class,userId, contact,begin,pageSize);
	}

}
