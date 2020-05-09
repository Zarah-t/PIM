package com.zarah.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.RequestGroupInfo;

import com.zarah.bean.Bill;
import com.zarah.bean.BillPage;
import com.zarah.bean.User;
import com.zarah.service.BillService;
import com.zarah.service.impl.BillServiceImpl;
import com.zarah.util.BUtils;

/**
 * Servlet implementation class BillServlet
 */
public class BillServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private BillService billService=new BillServiceImpl(); 
		


	//获取账单明细
	protected void getBillPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户id
		User user = (User)request.getSession().getAttribute("user");
		//若用户未登录，则跳转到登录页面
		if(user==null) {
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
			return;
		}
		Integer userId = user.getId();
		//获取请求参数
		String start = request.getParameter("startTime");
		String end = request.getParameter("endTime");
		String parent = request.getParameter("parent");
		String name = request.getParameter("name");
		//调用billService.getBillPage(userId, start, end, parent, name);获取账单页面数据
		BillPage<Bill> billPage = billService.getBillPage(userId, start, end, parent, name);
		//将billPage放入请求域中
		request.setAttribute("billPage", billPage);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("parent", parent);
		request.setAttribute("name", name);
		//转发到账单明细管理页面
		request.getRequestDispatcher("/pages/access/bill/bill_manage.jsp").forward(request, response);
		
	}
	
	
	//删除明细
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求参数明细id
		Integer id = BUtils.parseInt(request.getParameter("id"),0);
		//调用billService.deleteBill(id);删除某条明细
		billService.deleteBill(id);
		//转发到账单明细管理页面
		request.getRequestDispatcher("/billServlet?action=getBillPage").forward(request, response);
		
	}
	
	//添加账单明细
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求参数并封装为bill对象
		Bill bill = BUtils.copyParamToBean(request.getParameterMap(), new Bill());
		//调用billService.saveBill(bill);添加账户明细
		billService.saveBill(bill);
		//获取添加的账单明细的日期，转发后转到该日期的账单明细
		String date = request.getParameter("date");
		//转发到账单明细管理页面
		/*request.getRequestDispatcher("/billServlet?action=getBillPage").forward(request, response);	*/
		response.sendRedirect(request.getContextPath()+"/billServlet?action=getBillPage&startTime="+date+"&endTime="+date);
	}
	
	//根据id查询账户明细
	protected void getBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求参数账户id
		Integer id = BUtils.parseInt(request.getParameter("id"), 0);
		//调用billService.saveBill(bill);添加账户明细
		Bill bill = billService.queryBillById(id);
		//将查询到的bill对象保存到request域中
		request.setAttribute("bill", bill);
		//转发到账单明细管理页面
		request.getRequestDispatcher("/pages/access/bill/bill_edit.jsp").forward(request, response);
		
	}
	
	//根据id更新账户明细信息
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求参数并封装为bill对象
		Bill bill = BUtils.copyParamToBean(request.getParameterMap(), new Bill());
		//调用billService.update(bill);更改账户明细
		billService.update(bill);
		//获取添加的账单明细的日期，转发后转到该日期的账单明细
		String date = request.getParameter("date");
		//转发到账单明细管理页面
		/*request.getRequestDispatcher("billServlet?action=getPageBill").forward(request, response);*/
		response.sendRedirect(request.getContextPath()+"/billServlet?action=getBillPage&startTime="+date+"&endTime="+date);
	}
	
}
