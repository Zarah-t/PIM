package com.zarah.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.zarah.bean.User;

public class ManagerFilter implements Filter {

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		User user = (User)httpRequest.getSession().getAttribute("user");
		
		if(user==null) {
			//说明用户还未已登录
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else {
			//说明用户已登录
			chain.doFilter(httpRequest, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自动生成的方法存根

	}

}
