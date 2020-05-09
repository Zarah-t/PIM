package com.zarah.service;

import com.zarah.bean.User;

public interface UserService {
	
	//一个功能，一个方法
	public User login(User user);
	
	public void register(User user);
	
	public boolean existsUsername(String username);
	
	public User existsEmail(String email);

	public boolean updatePassword(Integer id, String password);

}
