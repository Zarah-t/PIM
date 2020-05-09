package com.zarah.dao.impl;

import com.zarah.bean.User;
import com.zarah.dao.UserDao;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
	



	@Override
	public User queryUserByUsernameAndPassword(String username, String password) {
		String sql="select id,username,password,email from t_user where username=? and password=?";
		return queryOne(sql,User.class,username,password);	
	}

	@Override
	public int saveUser(User user) {
		String sql="insert into t_user(username,password,email) values(?,?,?)";
		return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
	}

	@Override
	public User queryUserByUsername(String username) {
		String sql="select id,username,password,email from t_user where username=?";
		return queryOne(sql,User.class,username);
	}
	
	@Override
	public User queryUserByEmail(String email) {
		String sql="select id,username,password,email from t_user where email=?";
		return queryOne(sql,User.class,email);
	}

	@Override
	public int updatePassword(Integer id,String password) {
		String sql="update t_user set password=? where id=?";
		return update(sql,password,id);
		
	}
	
}
