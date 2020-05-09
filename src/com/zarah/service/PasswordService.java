package com.zarah.service;

import java.util.List;

import com.zarah.bean.Password;
import com.zarah.dao.PasswordDao;
import com.zarah.dao.impl.PasswordDaoImpl;

public interface PasswordService {
	
	
	public void addPassword(Password password);
	
	public void deletePassword(Integer id);
	
	public void updatePassword(Password password);
	
	public Password queryPasswordById(Integer id);
	
	public List<Password> queryPasswords(Integer userId);

}
