package com.zarah.service;

import java.util.List;

import com.zarah.bean.Bill;
import com.zarah.bean.BillPage;

public interface BillService {
	
	public void saveBill(Bill bill);
	
	public void deleteBill(Integer id);
	
	public void update(Bill bill);
	
	public Bill queryBillById(Integer Id);
	
/*	public List<Bill> queryBills(Integer userId,String start,String end,String parent,String name);*/
	
	public BillPage<Bill> getBillPage(Integer userId,String start,String end,String parent,String name);

}
