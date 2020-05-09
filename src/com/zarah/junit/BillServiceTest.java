package com.zarah.junit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.zarah.bean.Bill;
import com.zarah.bean.BillPage;
import com.zarah.service.BillService;
import com.zarah.service.impl.BillServiceImpl;
import com.zarah.util.JDBCUtils;

public class BillServiceTest {
	static BillService billService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		billService=new BillServiceImpl();
	}

	@Test
	public void testSaveBill() {
		billService.saveBill(new Bill(null,"去以纯买衣服",500,"微信",null,"支出","2020-02-26",2,"服装支出"));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testDeleteBill() {
		billService.deleteBill(66);
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testUpdate() {
		billService.update(new Bill(67,"去买机票",500,"建设银行",null,"支出","2020-02-26",1,"交通支出"));
		JDBCUtils.commitAndClose();
	}

	@Test
	public void testQueryBillById() {
		Bill bill = billService.queryBillById(9);
		System.out.println(bill);
	}

	@Test
	public void testGetBillPage() {
		BillPage<Bill> billPage = billService.getBillPage(2,null,null,null,null);
		billPage.getItems().forEach(System.out::println);
		System.out.println(billPage.getIn());
		System.out.println(billPage.getOut());
		
	}

}
