package com.zarah.service.impl;

import java.util.List;
import com.zarah.bean.Daily;
import com.zarah.bean.Page;
import com.zarah.dao.DailyDao;
import com.zarah.dao.impl.DailyDaoImpl;
import com.zarah.service.DailyService;

public class DailyServiceImpl implements DailyService {
	private DailyDao dailyDao=new DailyDaoImpl();
	
	@Override
	public void saveDaily(Daily daily) {
		dailyDao.saveDaily(daily);

	}

	@Override
	public void deleteDaily(Integer id) {
		dailyDao.deleteDaily(id);

	}

	@Override
	public void updateDaily(Daily daily) {
		dailyDao.updateDaily(daily);

	}

	@Override
	public Daily queryDailyById(Integer id) {
		return dailyDao.queryDailyById(id);
	}

	@Override
	public Page<Daily> page(Integer userId, Integer pageNo, Integer pageSize) {
		Page<Daily> page = new Page<Daily>();
		//设置每页显示数量
		page.setPageSize(pageSize);	
		//求总记录数
		Integer pageTotalCount=dailyDao.queryForPageTotalCount(userId);
		//设置总记录数
		page.setPageTotalCount(pageTotalCount);
		//求总页码
		Integer pageTotal=pageTotalCount/pageSize;
		if(pageTotalCount%pageSize>0) {
			pageTotal++;
		}
		//设置总页码
		page.setPageTotal(pageTotal);
		//设置当前页码
		page.setPageNo(pageNo);
		//求begin的值
		Integer begin=(page.getPageNo()-1)*page.getPageSize();
		//获取当前页数据
		List<Daily> items=dailyDao.queryForPageItems(userId,begin,page.getPageSize());
		//设置当前也数据
		page.setItems(items);
		
		return page;
	}

	@Override
	public Page<Daily> pageByDate(Integer userId, String arrDate, Integer pageNo, Integer pageSize) {
		Page<Daily> page = new Page<Daily>();
		//设置每页显示数量
		page.setPageSize(pageSize);	
		//求总记录数
		Integer pageTotalCount=dailyDao.queryForPageTotalCountByDate(userId,arrDate);
		//设置总记录数
		page.setPageTotalCount(pageTotalCount);
		//求总页码
		Integer pageTotal=pageTotalCount/pageSize;
		if(pageTotalCount%pageSize>0) {
			pageTotal++;
		}
		//设置总页码
		page.setPageTotal(pageTotal);
		//设置当前页码
		page.setPageNo(pageNo);
		//求begin的值
		Integer begin=(page.getPageNo()-1)*page.getPageSize();
		//获取当前页数据
		List<Daily> items=dailyDao.queryForPageItemsByDate(userId,arrDate, begin, pageSize);
		//设置当前页数据
		page.setItems(items);
		
		return page;
	}

}
