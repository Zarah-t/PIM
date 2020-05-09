package com.zarah.junit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.zarah.bean.Daily;
import com.zarah.dao.DailyDao;
import com.zarah.dao.impl.DailyDaoImpl;
import com.zarah.service.DailyService;
import com.zarah.service.impl.DailyServiceImpl;
import com.zarah.util.JDBCUtils;

public class DailyServiceTest {
	private static DailyService dailyService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dailyService=new DailyServiceImpl();
	}

	@Test
	public void testSaveDaily() {
		dailyService.saveDaily(new Daily(null,"2020-1-3","体育西","下午五点同学聚会",3));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testDeleteDaily() {
		dailyService.deleteDaily(31);
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testUpdateDaily() {
		dailyService.updateDaily(new Daily(14,"2019-03-02","公元前","下午一点去逛街",null));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testQueryDailyById() {
		System.out.println(dailyService.queryDailyById(21));
	}

	@Test
	public void testPage() {
		System.out.println(dailyService.page(1, 1, 5));
		dailyService.page(1, 1, 5).getItems().forEach(System.out::println);
	}

	@Test
	public void testPageByDate() {
		System.out.println(dailyService.pageByDate(3, "2020-8-7", 2, 5));
		dailyService.pageByDate(3, "2020-8-7", 2, 5).getItems().forEach(System.out::println);
		
	}

}
