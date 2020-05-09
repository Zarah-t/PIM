package com.zarah.dao;

import java.util.List;

import com.zarah.bean.Daily;

public interface DailyDao {
	
	public int saveDaily(Daily daily);
	
	public int updateDaily(Daily daily);
	
	public int deleteDaily(Integer id);
	
	public Daily queryDailyById(Integer id);
	
	public Integer queryForPageTotalCount(Integer userId);

	public List<Daily> queryForPageItems(Integer userId,Integer begin, Integer pageSize);
	
	public Integer queryForPageTotalCountByDate(Integer userId,String arrDate );

	public List<Daily> queryForPageItemsByDate(Integer userId,String arrDate,Integer begin, Integer pageSize);

}
