package com.zarah.junit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.zarah.bean.Sort;
import com.zarah.dao.SortDao;
import com.zarah.dao.impl.SortDaoImpl;
import com.zarah.util.JDBCUtils;

public class SortDaoTest {
	static SortDao sortDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sortDao=new SortDaoImpl();
	}

	@Test
	public void testAddSort() {
		sortDao.addSort(new Sort(null,"旅游支出","支出",2));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testDeleteSortById() {
		sortDao.deleteSortById(11);
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testUpdateSort() {
		sortDao.updateSort(new Sort(11,"游戏支出","支出",2));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testQuerySortById() {
		Sort querySortById = sortDao.querySortById(12);
		System.out.println(querySortById);
	}

	@Test
	public void testQuerySorts() {
		List<Sort> querySorts = sortDao.querySorts(2);
		querySorts.forEach(System.out::println);
	}
	
	@Test
	public void testQuerySortsByParent() {
		List<Sort> sorts = sortDao.querySortsByParent("支出", 2);
		sorts.forEach(System.out::println);
	}
	
	@Test
	public void testQuerySortByName() {
		Sort sort = sortDao.querySortByName("旅游支出", 2);
		System.out.println(sort);
	}
	

}
