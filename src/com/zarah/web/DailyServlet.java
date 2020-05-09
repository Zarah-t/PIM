package com.zarah.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zarah.bean.Daily;
import com.zarah.bean.Page;
import com.zarah.bean.User;
import com.zarah.service.DailyService;
import com.zarah.service.impl.DailyServiceImpl;
import com.zarah.util.BUtils;

/**
 * Servlet implementation class DailyServlet
 */
public class DailyServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private DailyService dailyService=new DailyServiceImpl();
       
	//获取分页相关的信息
	protected void page(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//1、获取请求的参数
		Integer pageNo = BUtils.parseInt(request.getParameter("pageNo"),1);
		Integer pageSize = BUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
		User user = (User)request.getSession().getAttribute("user");
		//若用户未登录，则跳转到登录页面
		if(user==null) {
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
			return;
		}
		Integer userId = user.getId();
		//2、调用dailyService.page(userId,pageNo,pageSize)
		Page<Daily> page=dailyService.page(userId,pageNo,pageSize);
		//分页的地址
		page.setUrl("DailyServlet?action=page");
		//3、保存分页的数据到request域中
		request.setAttribute("page", page);
		//4、转发到事务列表管理页面
		request.getRequestDispatcher("/pages/access/daily/daily_manager.jsp").forward(request, response);
	}
	
	//添加日常
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求参数并将其封装为Daily对象
		 Daily daily = BUtils.copyParamToBean(request.getParameterMap(), new Daily());
		//调用dailyService.saveDaily()方法添加事务
		dailyService.saveDaily(daily);
		//重定向跳转到事务列表功能模块
		/**
		 * request.getContextPath()  获取工程路径=====>>>>/PIM2
		 */
		response.sendRedirect(request.getContextPath()+"/DailyServlet?action=page&pageNo=1");
		
	}
	
	//删除日常
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取请求的参数，事务编号
		Integer id = BUtils.parseInt(request.getParameter("id"), 0);
		//2、调用dailyService.deleteDaily(事务编号);删除事务
		dailyService.deleteDaily(id);
		System.out.println(request.getHeader("Referer"));
		//3、重定向回日常事务列表
		/*response.sendRedirect(request.getContextPath()+"/DailyServlet?action=page&pageNo="+request.getParameter("pageNo"));*/
		response.sendRedirect(request.getHeader("Referer"));
		
	}
	
	//修改事务信息
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的参数并封装为Daily对象
	
		Daily daily = BUtils.copyParamToBean(request.getParameterMap(), new Daily());
		//调用dailyService.updateDaily(daily);方法修改事务信息
		dailyService.updateDaily(daily);
		//重定向回事务列表管理页面
		/**
		 * request.getContextPath()  获取工程路径=====>>>>/PIM2
		 */
		/*response.sendRedirect(request.getContextPath()+"/DailyServlet?action=page&pageNo="+request.getParameter("pageNo"));*/
		response.sendRedirect((String) request.getSession().getAttribute("url"));
	}
	
	//获取要修改的事务信息并保存到request域中
	public void getDaily(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//获取请求的参数
		Integer id = BUtils.parseInt(request.getParameter("id"), 0);
		//调用dailyService.queryDailyById(id)查找事务信息，并保存到request域中
		Daily daily = dailyService.queryDailyById(id);
		request.setAttribute("daily", daily);
		//将地址保存到session域中
		HttpSession session = request.getSession();
		String url = request.getHeader("Referer");
		session.setAttribute("url", url);
		System.out.println(url);
		//转发到事务编辑页面
		request.getRequestDispatcher("/pages/access/daily/daily_edit.jsp").forward(request, response);
	}
	
	//根据日期求分页相关的信息
	protected void pageByDate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//1、获取请求的参数
		Integer pageNo = BUtils.parseInt(request.getParameter("pageNo"),1);
		Integer pageSize = BUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
		String searchDate = request.getParameter("searchDate");
		//2、获取用户id
		User user =(User) request.getSession().getAttribute("user");
		//若用户未登录，则跳转到登录页面
		if(user==null) {
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
			return;
		}
		Integer userId = user.getId();
		//3、调用dailyService.pageByDate(userId, searchDate, pageNo, pageSize);
		Page<Daily> page = dailyService.pageByDate(userId, searchDate, pageNo, pageSize);
		//分页的地址
		StringBuilder sb=new StringBuilder("DailyServlet?action=pageByDate");
		sb.append("&searchDate=").append(searchDate);
		
		page.setUrl(sb.toString());
		//3、保存分页的数据到request域中
		request.setAttribute("page", page);
		//4、转发到事务展示页面
		request.getRequestDispatcher("/pages/access/daily/daily_manager.jsp").forward(request, response);
	}

}
