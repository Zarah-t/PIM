package com.zarah.junit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.zarah.bean.Bill;
import com.zarah.dao.BillDao;
import com.zarah.dao.impl.BillDaoImpl;
import com.zarah.util.JDBCUtils;

public class BillDaoTest {
	
	static BillDao billDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		billDao=new BillDaoImpl();
	}

	@Test
	public void testSaveBill() {
		//INSERT INTO t_bill(description,money,source,sid,parent,ctime,uid) VALUES(?,?,?,?,?,?,?)
		billDao.saveBill(new Bill(null,"朋友聚餐",100,"现金",2,"支出","2020-02-25",1,null));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testDeleteBill() {
		billDao.deleteBill(64);
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testUpdateBill() {
		billDao.updateBill(new Bill(67,"去优衣库买衣服",500,"微信",null,"支出","2020-02-26",null,"服装支出"));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testQueryBillById() {
		Bill bill = billDao.queryBillById(61);
		System.out.println(bill);
	}

	@Test
	public void testQueryBills() {
		List<Bill> bills = billDao.queryBills(1, "2020-02-25", "2020-02-25", "支出", "请选择");
		bills.forEach(System.out::println);
	}

}
