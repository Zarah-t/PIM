package com.zarah.service.impl;

import java.util.List;

import com.zarah.bean.Sort;
import com.zarah.dao.SortDao;
import com.zarah.dao.impl.SortDaoImpl;
import com.zarah.service.SortService;

public class SortServiceImpl implements SortService{
	
	private SortDao sortDao=new SortDaoImpl();
	
	@Override
	public void addSort(Sort sort) {
		sortDao.addSort(sort);
		
	}

	@Override
	public void deleteSort(Integer id) {
		sortDao.deleteSortById(id);
		
	}

	@Override
	public void updateSort(Sort sort) {
		sortDao.updateSort(sort);
		
	}

	@Override
	public Sort querySortById(Integer id) {
		return sortDao.querySortById(id);
	}

	@Override
	public List<Sort> querySorts(Integer userId) {
		return sortDao.querySorts(userId);
	}

	@Override
	public List<Sort> querySortsByParent(String parent, Integer userId) {
		return sortDao.querySortsByParent(parent, userId);
	}

	@Override
	public Sort querySortByName(String name, Integer userId) {
		return sortDao.querySortByName(name, userId);
	}

}