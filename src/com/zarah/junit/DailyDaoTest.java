package com.zarah.junit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.zarah.bean.Daily;
import com.zarah.dao.DailyDao;
import com.zarah.dao.impl.DailyDaoImpl;
import com.zarah.util.JDBCUtils;

public class DailyDaoTest {
	static DailyDao dailyDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dailyDao=new DailyDaoImpl();
	}  
	
	@Test
	public void testSaveDaily() {
		dailyDao.saveDaily(new Daily(null,"2019-3-2","公元前","逛街",1));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testUpdateDaily() {
		dailyDao.updateDaily(new Daily(14,"2019-3-2","公元前","下午三点逛街",1));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testDeleteDaily() {
		dailyDao.deleteDaily(1);
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testQueryDailyById() {
		System.out.println(dailyDao.queryDailyById(2));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testQueryForPageTotalCount() {
		System.out.println(dailyDao.queryForPageTotalCount(3));
	}

	@Test
	public void testQueryForPageItems() {
		dailyDao.queryForPageItems(2,1, 4).forEach(System.out::println);;
	}

	@Test
	public void testQueryForPageTotalCountByDate() {
		System.out.println(dailyDao.queryForPageTotalCountByDate(3, "2020-08-07"));
	}

	@Test
	public void testQueryForPageItemsByDate() {
		dailyDao.queryForPageItemsByDate(2, "2020-01-02", 0, 5).forEach(System.out::println);
	}

}
