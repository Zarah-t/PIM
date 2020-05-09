package com.zarah.dao;

import java.util.List;

import com.zarah.bean.Password;
import com.zarah.bean.Sort;

public interface PasswordDao {
	public int addPassword(Password password);
	
	public int deletePassword(Integer id);
	
	public int updatePassword(Password password);
	
	public Password queryPasswordById(Integer id);
	
	public List<Password> queryPasswords(Integer userId);
	
}
