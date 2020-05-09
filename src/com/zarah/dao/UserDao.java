package com.zarah.dao;

import com.zarah.bean.User;

public interface UserDao {
	/**
	 * 
	 * @Description 根据用户名和密码查询用户
	 * @author zarah
	 * @date 2020年2月15日上午2:05:14
	 * @param username
	 * @param password
	 * @return
	 */
	public User queryUserByUsernameAndPassword(String username,String password);
	
	/**
	 * 
	 * @Description 保存用户
	 * @author zarah
	 * @date 2020年2月15日上午2:06:15
	 * @param user
	 * @return
	 */
	public int saveUser(User user);
	
	/**
	 * 根据用户名查询用户
	 * @Description
	 * @author zarah
	 * @date 2020年2月15日上午2:06:52
	 * @param username
	 * @return
	 */
	public User queryUserByUsername(String username);
	
	public User queryUserByEmail(String email);
	
	public int updatePassword(Integer id,String password);
	

}
