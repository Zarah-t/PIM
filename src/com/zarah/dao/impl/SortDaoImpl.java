package com.zarah.dao.impl;

import java.util.List;

import com.zarah.bean.Sort;
import com.zarah.dao.SortDao;

public class SortDaoImpl extends BaseDaoImpl implements SortDao {

	@Override
	public int addSort(Sort sort) {
		String sql="insert into t_sort(sname,parent,uid) values(?,?,?)";
		return update(sql,sort.getName(),sort.getParent(),sort.getUserId());
	}

	@Override
	public int deleteSortById(Integer id) {
		String sql="delete from t_sort where id=?";
		return update(sql, id);
	}

	@Override
	public int updateSort(Sort sort) {
		String sql="update t_sort set sname=?,parent=? where id=?";
		return update(sql, sort.getName(),sort.getParent(),sort.getId());
	}

	@Override
	public Sort querySortById(Integer id) {
		String sql="select id,sname name,parent parent,uid userId from t_sort where id=?";
		return queryOne(sql, Sort.class, id);
	}

	@Override
	public List<Sort> querySorts(Integer userId) {
		String sql="select id,sname name,parent,uid userId from t_sort where uid=?";
		return queryList(sql, Sort.class,userId);
	}

	@Override
	public List<Sort> querySortsByParent(String parent, Integer userId) {
		String sql="select id,sname name,parent,uid userId from t_sort where uid=? and parent=?";
		return queryList(sql, Sort.class, userId,parent);
		
	}

	@Override
	public Sort querySortByName(String name, Integer userId) {
		String sql="select id,sname name,parent,uid userId from t_sort where uid=? and sname=?";
		return queryOne(sql, Sort.class, userId,name);
	}

	
	

}
