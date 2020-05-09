package com.zarah.service.impl;

import com.zarah.bean.User;
import com.zarah.dao.UserDao;
import com.zarah.dao.impl.UserDaoImpl;
import com.zarah.service.UserService;

public class UserServiceImpl implements UserService {
	
	private static UserDao userDao=new UserDaoImpl();

	@Override
	public User login(User user) {
		return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());

	}

	@Override
	public void register(User user) {
		userDao.saveUser(user);

	}

	@Override
	public boolean existsUsername(String username) {
		User user = userDao.queryUserByUsername(username);
		if(user!=null) {
			//用户名已存在
			return true;
		}
		//用户名可用
		return false;
	}

	
	@Override
	public User existsEmail(String email) {
		return userDao.queryUserByEmail(email);

	}
	
	@Override
	public boolean updatePassword(Integer id,String password) {
		int result = userDao.updatePassword(id, password);
		if(result>0) {
			//修改成功
			return true;
		}
		//修改失败
		return false;
	}

}
