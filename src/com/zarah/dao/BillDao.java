package com.zarah.dao;

import java.util.List;

import com.zarah.bean.Bill;

public interface BillDao {
	
	public int saveBill(Bill bill);
	
	public int deleteBill(Integer id);
	
	public int updateBill(Bill bill);
	
	public Bill queryBillById(Integer id);
	
	public List<Bill> queryBills(Integer userId,String start,String end,String parent,String name);
}
