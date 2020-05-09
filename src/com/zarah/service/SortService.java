package com.zarah.service;

import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.zarah.bean.Sort;

public interface SortService {
	
	public void addSort(Sort sort);
	
	public void deleteSort(Integer id);
	
	public void updateSort(Sort sort);
	
	public Sort querySortById(Integer id);
	
	public List<Sort> querySorts(Integer userId);
	
	public List<Sort> querySortsByParent(String parent,Integer userId);
	
	public Sort querySortByName(String name,Integer userId);
	
}
