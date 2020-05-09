package com.zarah.dao.impl;

import java.util.List;

import com.zarah.bean.Password;
import com.zarah.bean.Sort;
import com.zarah.dao.PasswordDao;

public class PasswordDaoImpl extends BaseDaoImpl implements PasswordDao {

	@Override
	public int addPassword(Password password) {
		String sql="insert into t_password(pname,pass,uid) values(?,?,?)";
		return update(sql, password.getPname(),password.getPass(),password.getUid());
	}

	@Override
	public int deletePassword(Integer id) {
		String sql="delete from t_password where id=?";
		return update(sql, id);
	}

	@Override
	public int updatePassword(Password password) {
		String sql="update t_password set pname=?,pass=? where id=?";
		return update(sql, password.getPname(),password.getPass(),password.getId());
	}

	@Override
	public Password queryPasswordById(Integer id) {
		String sql="select id,pname,pass,uid from t_password where id=?";
		return queryOne(sql, Password.class, id);
	}

	@Override
	public List<Password> queryPasswords(Integer userId) {
		String sql="select id,pname,pass, uid from t_password where uid=?";
		return queryList(sql, Password.class, userId);
	}

}
