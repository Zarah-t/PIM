package com.zarah.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zarah.bean.Sort;
import com.zarah.bean.User;
import com.zarah.dao.SortDao;
import com.zarah.service.SortService;
import com.zarah.service.impl.SortServiceImpl;
import com.zarah.util.BUtils;

public class SortServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private SortService sortService=new SortServiceImpl();
	
	//获取分类信息
	protected void querySorts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取用户id
		User user =(User) request.getSession().getAttribute("user");
		//若用户未登录，则跳转到登录页面
		if(user==null) {
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
			return;
		}
		Integer id = user.getId();
		//1、调用sortService.querySorts();获取所有的分类信息
		List<Sort> sorts = sortService.querySorts(id);
		//2、将分类信息放入到request域中
		request.setAttribute("sorts", sorts);
		//3、转发到/pages/access/bill/sort_manage.jsp
		request.getRequestDispatcher("/pages/access/bill/sort_manage.jsp").forward(request, response);
	}
	
	//删除分类信息
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取请求参数
		Integer id = BUtils.parseInt(request.getParameter("id"), 0);
		//2、调用sortService.deleteSort(id);删除分类信息
		sortService.deleteSort(id);
		//3、转发到/pages/access/bill/sort_manage.jsp
		request.getRequestDispatcher("/sortServlet?action=querySorts").forward(request, response);
	}
	
	//获取要修改的分类信息并保存到request域中
	protected void getSort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取请求参数
		Integer id = BUtils.parseInt(request.getParameter("id"), 0);
		//2、调用sortService.querySortById(id);查找分类信息，并保存到request域中
		Sort sort = sortService.querySortById(id);
		request.setAttribute("sort", sort);
		//3、转发到/pages/access/bill/sort_edit.jsp
		request.getRequestDispatcher("/pages/access/bill/sort_edit.jsp").forward(request, response);
	}
	
	
	//修改分类信息
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的参数并封装为Sort对象
		Sort sort = BUtils.copyParamToBean(request.getParameterMap(), new Sort());
		//调用sortService.updateSort(sort);方法修改分类信息
		sortService.updateSort(sort);
		//重定向回分类页面
		/**
		 * request.getContextPath()  获取工程路径=====>>>>/PIM2
		 */
		response.sendRedirect(request.getContextPath()+"/sortServlet?action=querySorts");
	}
	
	//添加分类信息
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取请求参数并封装为sort对象
		Sort sort = BUtils.copyParamToBean(request.getParameterMap(), new Sort());
		//2、调用
		sortService.addSort(sort);
		//3、重定向回分类页面
		/**
		 * request.getContextPath()  获取工程路径=====>>>>/PIM2
		 */
		response.sendRedirect(request.getContextPath()+"/sortServlet?action=querySorts");
	}
	
	//ajax获取收入或支出的分类信息
	protected void ajaxSort(HttpServletRequest request,HttpServletResponse response) throws IOException {	
		//获取请求参数
		User user =(User) request.getSession().getAttribute("user");
		
		Integer userId = user.getId();
		String parent = request.getParameter("parent");
		//调用sortService.querySorts(userId)
		List<Sort> sorts = sortService.querySortsByParent(parent, userId);
		//将List转换为jsonString
		Gson gson = new Gson();
		String listJsonString = gson.toJson(sorts);
		response.getWriter().write(listJsonString);
		System.out.println(listJsonString);
	}
	
	//ajax验证分类是否存在
	protected void ajaxTestSort(HttpServletRequest request,HttpServletResponse response) throws IOException {	
		//获取请求参数
		User user =(User) request.getSession().getAttribute("user");
		Integer userId = user.getId();
		String name = request.getParameter("name");
		//调用sortService.querySorts(userId)
		Sort sort = sortService.querySortByName(name, userId);
		//将List转换为jsonString
		Gson gson = new Gson();
		String JsonString = gson.toJson(sort);
		response.getWriter().write(JsonString);
		System.out.println(JsonString);
	}
	
	
	
	
	
	

}
