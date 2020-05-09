package com.zarah.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;
import com.zarah.bean.Bill;
import com.zarah.bean.Sort;
import com.zarah.dao.BillDao;
import com.zarah.dao.SortDao;

public class BillDaoImpl extends BaseDaoImpl implements BillDao {
	private SortDao sortDao=new SortDaoImpl();
	@Override
	public int saveBill(Bill bill) {
		String sql="INSERT INTO t_bill(description,money,source,sid,parent,ctime,uid) VALUES(?,?,?,?,?,?,?)";
		return update(sql, bill.getDescription(),bill.getMoney(),bill.getSource(),bill.getSid(),bill.getParent(),bill.getDate(),bill.getUid());
	}

	@Override
	public int deleteBill(Integer id) {
		String sql="delete from t_bill where id=?";
		return update(sql, id);
	}

	@Override
	public int updateBill(Bill bill) {
		String sql="update t_bill set description=?,money=?,source=?,sid=?,parent=?,ctime=? where id=?";
		return update(sql, bill.getDescription(),bill.getMoney(),bill.getSource(),bill.getSid(),bill.getParent(),bill.getDate(),bill.getId());
	}

	@Override
	public Bill queryBillById(Integer id) {
		String sql="\r\n" + 
				"SELECT id,description,money,source,sid,parent,ctime DATE,uid FROM t_bill WHERE id=?";
		return queryOne(sql, Bill.class, id);
	}

	@Override
	public List<Bill> queryBills(Integer userId, String start, String end, String parent, String name) {
		//存储sql语句
		StringBuilder sb = new StringBuilder();
		
		//存储参数
		List<String> params=new ArrayList<String>();
		
		sb.append("select id,description,money,source,sid,parent,ctime date,uid  from t_bill where uid=? and ctime between ? and ?");
		params.add(userId+"");
		params.add(start);
		params.add(end);
		if("支出".equals(parent)||"收入".equals(parent)) {
			sb.append( "and parent=?");
			params.add(parent);
		}
		if(!"请选择".equals(name)) {
			Sort sort = sortDao.querySortByName(name, userId);
			Integer sid = sort.getId();
			sb.append(" and sid=?");
			params.add(sid+"");
			
		}
		sb.append(" order by ctime desc");
		String sql=sb.toString();
		return queryList(sql, Bill.class,params.toArray());
		
	}

}
