package com.zarah.dao.impl;

import java.util.List;


import com.zarah.bean.Daily;
import com.zarah.dao.DailyDao;

public class DailyDaoImpl extends BaseDaoImpl implements DailyDao {

	@Override
	public int saveDaily(Daily daily) {
		String sql="INSERT INTO t_daily(`date` , `place` , `description` , `user_id`) VALUES(?,?,?,?)";
		return update(sql, daily.getArrDate(),daily.getPlace(),daily.getDescription(),daily.getUserId());
	}

	@Override
	public int updateDaily(Daily daily) {
		String sql="update t_daily set `date`=?,`place`=?,`description`=? where `id`=?";
		return update(sql, daily.getArrDate(),daily.getPlace(),daily.getDescription(),daily.getId());
	}

	@Override
	public int deleteDaily(Integer id) {
		String sql="delete from t_daily where id=?";
		return update(sql, id);
	}
	
	@Override
	public Daily queryDailyById(Integer id) {
		String sql="select `id` , `date` arrDate, `place` , `description`,user_id userId from t_daily where id=?";
		return queryOne(sql, Daily.class, id);
	}

	@Override
	public Integer queryForPageTotalCount(Integer userId) {
		String sql="select count(*) from t_daily where user_id=?";
		return new Integer(queryForSingleValue(sql,userId).toString()) ;
	}

	@Override
	public List<Daily> queryForPageItems(Integer userId,Integer begin, Integer pageSize) {
		String sql="select `id` , `date` arrDate , `place` , `description`,`user_id` userId from t_daily where user_id=? order by `date` DESC limit ?,?";
		return queryList(sql,Daily.class, userId,begin, pageSize);
	}

	@Override
	public Integer queryForPageTotalCountByDate(Integer userId,String arrDate) {
		String sql="select count(*) from t_daily where user_id=? and `date`=?";
		Object count = queryForSingleValue(sql,userId,arrDate);		
		return new Integer(count.toString());
	}

	@Override
	public List<Daily> queryForPageItemsByDate(Integer userId,String arrDate, Integer begin, Integer pageSize) {
		String sql="select `id` , `date` arrDate , `place` , `description`,user_id userId from t_daily where user_id=? and `date`=? limit ?,?";
		return queryList(sql,Daily.class,userId, arrDate,begin,pageSize);
	}



}
