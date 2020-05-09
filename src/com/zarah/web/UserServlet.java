package com.zarah.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.jasper.tagplugins.jstl.core.Remove;

import com.alibaba.druid.Constants;
import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zarah.bean.SendEmail;
import com.zarah.bean.User;
import com.zarah.service.impl.UserServiceImpl;
import com.zarah.util.BUtils;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserServiceImpl userService = new UserServiceImpl();
	
	//修改密码
	protected void updatePassword(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//获取用户id和要修改的密码
		String password = request.getParameter("password");
		Integer id = BUtils.parseInt(request.getParameter("id"), 0);
		//调用userService.updatePassword(id, password)修改密码
		boolean flag = userService.updatePassword(id, password);
		//把结果放入map中
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("flag", flag);
		//将Map转换为jsonString
		Gson gson = new Gson();
		String mapJsonString = gson.toJson(map);
		response.getWriter().write(mapJsonString);
		System.out.println(flag);
	}
	
	
	
	//ajax验证用户名是否存在
	protected void existsUsername(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//1、获取用户名
		String username = request.getParameter("username");
		//2、调用userService.existsUsername(username)方法检验用户名是否存在
		boolean flag = userService.existsUsername(username);
		//3、把验证的结果返回给客户端
		//把flag存入Map中
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("flag", flag);
		Gson gson = new Gson();
		String mapJsonString = gson.toJson(map);
		response.getWriter().write(mapJsonString);
		
	}
	
	//ajax验证邮箱是否存在
	protected void existsEmail(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//1、获取用户名
		String email = request.getParameter("email");
		//2、调用userService.existsEmail(username)方法检验用户名是否存在
		User user = userService.existsEmail(email);
		boolean flag=true;
		if(user==null) {
			//邮箱不存在
			flag=false;
		}
		//3、把验证的结果返回给客户端
		//把flag存入Map中
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("flag", flag);
		Gson gson = new Gson();
		String mapJsonString = gson.toJson(map);
		response.getWriter().write(mapJsonString);
		//System.out.println(flag);
	}
	
	//这是登录的方法
	protected void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//1、获取请求参数
		String username = request.getParameter("username");
		//String password = request.getParameter("password");
		
		//使用BeanUtils
		User user=BUtils.copyParamToBean(request.getParameterMap(), new User());
		
		//调用userService.login()方法
		User loginUser = userService.login(user);
		
				
		//2、调用userService.login()方法，根据返回的结果判断用户是否登录成功
		if(loginUser!=null) {
			//成功，跳转到登录成功页面
			
			//将用户的信息保存到Session域
			request.getSession().setAttribute("user", loginUser);
			request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
		}else {
			//失败
			//把错误的信息保存到request域中，以及需要回显的信息保存到request域中
			request.setAttribute("msg","用户名或密码错误");
			request.setAttribute("username", username);
			//跳回登录页面
			request.getRequestDispatcher("pages/user/login.jsp").forward(request, response);
		}
	}
	
	//这是注册的方法
	protected void regist(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//1、获取请求的参数
		String username = request.getParameter("username");
		//String password = request.getParameter("password");
		String email = request.getParameter("email");
		String code = request.getParameter("code");
		
		
		//使用BeanUtils
		User user=BUtils.copyParamToBean(request.getParameterMap(), new User());
		
		//获取Session中的验证码
		String token = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//删除Session中的验证码
		request.getSession().removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		
/*		try {
			System.out.println("注入之前："+user);
			*//**
			 * populate把map的值注入到User对象中
			 * Map的值是请求的参数
			 * key=value
			 * 刚好是
			 * name=value
			 * 要求请求的参数名称必须和javaBean的属性名一致<br />
			 * 前面用EL表达式取bean对象的属性值的时候，走的是读方法getXxx<br>
			 * 现在使用BeanUtils工具类给bean对象赋值的时候，走的是setXxx方法
			 *//*
			BeanUtils.populate(user, request.getParameterMap());
			System.out.println("注入之后："+user);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}*/
		
		
		
		//2、检验验证码是否正确
		if(token!=null&&token.equalsIgnoreCase(code)) {
			//验证码正确
			userService.register(user);
			//跳到注册成功页面
			request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);
		}else {
			//验证码错误
			//System.out.println("验证码错误:"+code);
			//把错误的信息保存到request域中，以及需要回显的信息保存到request域中
			request.setAttribute("msg","验证码不正确");
			request.setAttribute("username", username);
			request.setAttribute("email", email);
			
			//请求转发
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
		}
	}
	
	//3、注销功能的实现
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//注销登录功能，只需要销毁session
		request.getSession().invalidate();//就会马上超时，session中的数据全部没有
		//重定向到首页  request.getContextPath()======>工程名
		response.sendRedirect(request.getContextPath());
	}
	
	//ajax根据用户邮箱发送邮件找回密码
	protected void sendEmail(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//1、获取邮箱
		String email = request.getParameter("email");
		//2、向客户端响应的数据
		String tip;
		//3、根据邮箱获取用户
		User user = userService.existsEmail(email);
		if(user==null) {
			//如果用户不存在
			tip="邮箱尚未注册，请重新输入";
		}else {
			//4、获取用户密码
			String password = user.getPassword();
			//4、向用户邮箱发送用户密码
			SendEmail sendEmail = new SendEmail();
			sendEmail.sendMail(email, "尊敬的用户，您的密码为："+password);
			//3、把验证的结果返回给客户端
			tip="恭喜你，找回密码成功，请前往邮箱查看";
			
		}
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("tip", tip);
		Gson gson = new Gson();
		String mapJsonString = gson.toJson(map);
		response.getWriter().write(mapJsonString);
	}
	
	

}
