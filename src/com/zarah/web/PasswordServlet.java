package com.zarah.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zarah.bean.Password;
import com.zarah.bean.User;
import com.zarah.service.PasswordService;
import com.zarah.service.impl.PasswordServiceImpl;
import com.zarah.util.BUtils;
import com.zarah.util.JDBCUtils;

/**
 * Servlet implementation class PasswordServlet
 */
public class PasswordServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private PasswordService passwordService=new PasswordServiceImpl();

	//查找各类密码
	protected void queryPasswords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取用户id
		User user =(User) request.getSession().getAttribute("user");
		//若用户未登录，则跳转到登录页面
		if(user==null) {
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
			return;
		}
		Integer userId = user.getId();
		//2、调用passwordService.queryPasswords(userId);获取各类应用及密码信息
		List<Password> passwords = passwordService.queryPasswords(userId);
		//3、将passwords放入请求域中
		request.setAttribute("passwords", passwords);
		//转发到密码管理页面
		request.getRequestDispatcher("/pages/access/password/password_manage.jsp").forward(request, response);
		

	}
	
	//删除密码
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取请求参数，密码对应的id
		Integer id = BUtils.parseInt(request.getParameter("id"), 0);
		//2、调用ppasswordService.deletePassword(id);删除密码
		passwordService.deletePassword(id);
		//转发到密码管理页面
		request.getRequestDispatcher("/passwordServlet?action=queryPasswords").forward(request, response);
		

	}
	
	//添加密码
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取请求参数，并封装为password对象
		Password password = BUtils.copyParamToBean(request.getParameterMap(), new Password());
		//2、调用passwordService.addPassword(password);添加密码
		passwordService.addPassword(password);
		//转发到密码管理页面
		request.getRequestDispatcher("/passwordServlet?action=queryPasswords").forward(request, response);
		
	}
	
	//根据id查询密码项
	protected void getPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取请求参数id
		Integer id = BUtils.parseInt(request.getParameter("id"), 0);
		//2、调用passwordService.queryPasswordById(id);查询用户信息
		Password password = passwordService.queryPasswordById(id);
		//3、将password放入request域中
		request.setAttribute("password", password);
		//转发到密码管理页面
		request.getRequestDispatcher("/pages/access/password/password_edit.jsp").forward(request, response);
		
	}
	
	//修改密码项
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取请求参数并封装为password对象
		Password password = BUtils.copyParamToBean(request.getParameterMap(), new Password());
		//2、调用passwordService.updatePassword(password);修改密码信息
		passwordService.updatePassword(password);
		//转发到密码管理页面
		request.getRequestDispatcher("/passwordServlet?action=queryPasswords").forward(request, response);
		
	}
	
	
	


}
