package com.zarah.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.zarah.bean.Bill;
import com.zarah.bean.BillPage;
import com.zarah.bean.Sort;
import com.zarah.dao.BillDao;
import com.zarah.dao.SortDao;
import com.zarah.dao.impl.BillDaoImpl;
import com.zarah.dao.impl.SortDaoImpl;
import com.zarah.service.BillService;
import com.zarah.service.SortService;

public class BillServiceImpl implements BillService {
	
	private BillDao billDao=new BillDaoImpl();
	private SortDao sortDao=new SortDaoImpl();

	@Override
	public void saveBill(Bill bill) {
		Sort sort = sortDao.querySortByName(bill.getName(), bill.getUid());
		Integer id = sort.getId();
		bill.setSid(id);
		billDao.saveBill(bill);
		
	}

	@Override
	public void deleteBill(Integer id) {
		billDao.deleteBill(id);
		
	}

	@Override
	public void update(Bill bill) {
		Sort sort = sortDao.querySortByName(bill.getName(), bill.getUid());
		Integer sid = sort.getId();
		bill.setSid(sid);
		billDao.updateBill(bill);
		
	}

	@Override
	public Bill queryBillById(Integer id) {
		Bill bill = billDao.queryBillById(id);
		Sort sort = sortDao.querySortById(bill.getSid());
		bill.setName(sort.getName());
		return bill;
	}

	@Override
	public BillPage<Bill> getBillPage(Integer userId, String start, String end, String parent, String name) {
		BillPage<Bill> billPage=new BillPage<Bill>();
		double in=0;
		double out=0;
		//获取账单数据项并放入billPage中
		//判断检索条件并赋默认值
		//获取当天的日期
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today=sdf.format(date);
		if(start==null||start=="") {
			start=today;
		}
		if(end==null||end=="") {
			end=today;
		}
		if(parent==null||parent=="") {
			parent="请选择";
		}
		if(name==null||name=="") {
			name="请选择";
		}
		List<Bill> bills = billDao.queryBills(userId, start, end, parent, name);
		
		for(Bill bill:bills) {
			//根据分类编号获取对应分类名称	
			Sort sort = sortDao.querySortById(bill.getSid());
			bill.setName(sort.getName());		
			//求对应的收入和支出
			if("支出".equals(bill.getParent())) {
				out+=bill.getMoney();
			}
			if("收入".equals(bill.getParent())) {
				in+=bill.getMoney();
			};
		}
		billPage.setItems(bills);
		billPage.setIn(in);
		billPage.setOut(out);
	
		return billPage;
	}


}
