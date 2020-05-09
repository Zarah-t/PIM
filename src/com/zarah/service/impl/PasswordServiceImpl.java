package com.zarah.service.impl;

import java.util.List;

import com.zarah.bean.Password;
import com.zarah.dao.PasswordDao;
import com.zarah.dao.impl.PasswordDaoImpl;
import com.zarah.service.PasswordService;

public class PasswordServiceImpl implements PasswordService {
	
	private PasswordDao passwordDao=new PasswordDaoImpl();

	@Override
	public void addPassword(Password password) {
		passwordDao.addPassword(password);

	}

	@Override
	public void deletePassword(Integer id) {
		passwordDao.deletePassword(id);

	}

	@Override
	public void updatePassword(Password password) {
		passwordDao.updatePassword(password);

	}

	@Override
	public Password queryPasswordById(Integer id) {
		return passwordDao.queryPasswordById(id);
	}

	@Override
	public List<Password> queryPasswords(Integer userId) {
		return passwordDao.queryPasswords(userId);
	}

}
