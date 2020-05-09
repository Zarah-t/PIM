package com.zarah.web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.management.RuntimeMBeanException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 通过反射可以省去后面大量的if - else if代码<br/>
		 * 而且在后续添加的功能当中，我们只需要关心功能方法即可。<br/> 
		 */	
		
		//post请求解决中文乱码问题
		request.setCharacterEncoding("UTF-8");
		
		//解决响应中文乱码问题
		response.setContentType("text/html;charset=UTF-8");
		
		String action = request.getParameter("action");
		/**
		 * getDeclareMethod通过反射的方式获取action业务鉴别字符串 所对应的业务方法对象<br/>
		 * 第一个参数是方法名<br/>
		 * 第二个参数是方法的参数类型列表<br/>
		 */
		try {
			Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class,HttpServletResponse.class);
			//通过反射调用业务方法
			method.invoke(this, request,response);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new RuntimeException();
		}
		/*if("login".equals(action)) {
			login(request, response);
		}else if("regist".equals(action)) {
			regist(request, response);		
		}*/
	}
}
