package com.zarah.dao;

import java.util.List;

import com.zarah.bean.Sort;
import com.zarah.bean.User;

public interface SortDao {
	public int addSort(Sort sort);
	
	public int deleteSortById(Integer id);
	
	public int updateSort(Sort sort);
	
	public Sort querySortById(Integer id);
	
	public List<Sort> querySorts(Integer userId);
	
	public List<Sort> querySortsByParent(String parent,Integer userId);
	
	public Sort querySortByName(String name,Integer userId);
	

}
