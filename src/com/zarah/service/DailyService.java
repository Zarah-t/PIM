package com.zarah.service;

import com.zarah.bean.Daily;
import com.zarah.bean.Page;

public interface DailyService {
	
	public void saveDaily(Daily daily);
	
	public void deleteDaily(Integer id);
	
	public void updateDaily(Daily daily);
	
	public Daily queryDailyById(Integer id);
	
	public Page<Daily> page(Integer userId,Integer pageNo,Integer pageSize);
	
	public Page<Daily> pageByDate(Integer userId,String arrDate,Integer pageNo,Integer pageSize);
	
	
}
