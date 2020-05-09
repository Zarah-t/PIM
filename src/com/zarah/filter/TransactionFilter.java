package com.zarah.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.zarah.util.JDBCUtils;

public class TransactionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO 自动生成的方法存根

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			chain.doFilter(request, response);//等价于调用所有的XxxService.Xxx()方法
			JDBCUtils.commitAndClose();
		}catch(Exception e){
			e.printStackTrace();
			//response.setContentType("text/html;charset=utf-8");
			//PrintWriter out = response.getWriter();
			//out.println("订单发生异常，请重新提交");
			JDBCUtils.rollbackAndClose();
			throw new RuntimeException();
			
		}

	}

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根

	}

}
