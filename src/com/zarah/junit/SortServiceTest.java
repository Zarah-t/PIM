package com.zarah.junit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.zarah.bean.Sort;
import com.zarah.service.SortService;
import com.zarah.service.impl.SortServiceImpl;
import com.zarah.util.JDBCUtils;

public class SortServiceTest {
	static SortService sortService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sortService=new SortServiceImpl();
	}

	@Test
	public void testAddSort() {
		sortService.addSort(new Sort(null,"游戏支出","支出",1));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testDeleteSort() {
		sortService.deleteSort(13);
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testUpdateSort() {
		sortService.updateSort(new Sort(14,"游戏收入","收入",null));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testQuerySortById() {
		System.out.println(sortService.querySortById(13));
	}

	@Test
	public void testQuerySorts() {
		List<Sort> sorts = sortService.querySorts(2);
		sorts.forEach(System.out::println);
	}
	
	@Test
	public void testQuerySortsByParent() {
		List<Sort> sorts = sortService.querySortsByParent("支出", 2);
		sorts.forEach(System.out::println);
	}
	
	@Test
	public void testQuerySortsByName() {
		Sort sort = sortService.querySortByName("服装支出", 2);
		System.out.println(sort);
	}
	
	


}
