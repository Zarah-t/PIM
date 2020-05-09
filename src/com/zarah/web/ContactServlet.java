package com.zarah.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zarah.bean.Contact;
import com.zarah.bean.Daily;
import com.zarah.bean.Page;
import com.zarah.bean.User;
import com.zarah.dao.impl.ContactDaoImpl;
import com.zarah.service.ContactService;
import com.zarah.service.DailyService;
import com.zarah.service.impl.ContactServiceImpl;
import com.zarah.service.impl.DailyServiceImpl;
import com.zarah.util.BUtils;

/**
 * Servlet implementation class ContactServlet
 */
public class ContactServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private ContactService contactService=new ContactServiceImpl();
    
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
		//2、调用contactService.page(userId,pageNo,pageSize);
		Page<Contact> page=contactService.page(userId,pageNo,pageSize);
		//分页的地址
		page.setUrl("contactServlet?action=page");
		//3、保存分页的数据到request域中
		request.setAttribute("page", page);
		//4、转发到联系人列表管理页面
		request.getRequestDispatcher("/pages/access/contact/contact_manage.jsp").forward(request, response);
	}
	
	//添加联系人
		protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//获取请求参数并将其封装为contact对象
			 Contact contact = BUtils.copyParamToBean(request.getParameterMap(), new Contact());
			//调用dailyService.saveDaily()方法添加事务
			contactService.addContact(contact);
			//重定向跳转到联系人列表功能模块
			/**
			 * request.getContextPath()  获取工程路径=====>>>>/PIM2
			 */
			response.sendRedirect(request.getContextPath()+"/contactServlet?action=page&pageNo="+Integer.MAX_VALUE);
			
		}
		
		//删除联系人
		protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//1、获取请求的参数，联系人编号
			Integer id = BUtils.parseInt(request.getParameter("id"), 0);
			//2、调用dailyService.deleteDaily(联系人编号);联系人编号
			contactService.deleteContact(id);
			System.out.println(request.getHeader("Referer"));
			//3、重定向回联系人列表
			/*response.sendRedirect(request.getContextPath()+"/contactServlet?action=page&pageNo="+request.getParameter("pageNo"));*/
			response.sendRedirect(request.getHeader("Referer"));
			
		}
		
		//修改联系人信息
		protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//获取请求的参数并封装为Contact对象	
			Contact contact = BUtils.copyParamToBean(request.getParameterMap(), new Contact());
			//调用dailyService.updateDaily(daily);方法修改联系人信息
			contactService.updateContact(contact);
			//重定向回联系人列表管理页面
			/**
			 * request.getContextPath()  获取工程路径=====>>>>/PIM2
			 */
			response.sendRedirect((String) request.getSession().getAttribute("url"));
		}
		
		//获取要修改的事务信息并保存到request域中
		public void getDaily(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
			//获取请求的参数
			Integer id = BUtils.parseInt(request.getParameter("id"), 0);
			//调用contactService.queryContactById(id)查找事务信息，并保存到request域中
			Contact contact = contactService.queryContactById(id);
			request.setAttribute("contact", contact);
			//将地址保存到session域中
			HttpSession session = request.getSession();
			String url = request.getHeader("Referer");
			session.setAttribute("url", url);
			
			//转发到联系人编辑页面
			request.getRequestDispatcher("/pages/access/contact/contact_edit.jsp").forward(request, response);
		}
		
		//根据联系人求分页相关的信息
		protected void pageByContact(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
			//1、获取请求的参数
			Integer pageNo = BUtils.parseInt(request.getParameter("pageNo"),1);
			Integer pageSize = BUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
			String contact = request.getParameter("searchContact");
			//2、获取用户id
			User user =(User) request.getSession().getAttribute("user");
			//若用户未登录，则跳转到登录页面
			if(user==null) {
				request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
				return;
			}
			Integer userId = user.getId();
			//3、调用contactService.pageByContact(userId, contact, pageNo, pageSize);
			Page<Contact> page = contactService.pageByContact(userId, contact, pageNo, pageSize);
			//分页的地址
			StringBuilder sb=new StringBuilder("contactServlet?action=pageByContact");
			sb.append("&contact=").append(contact);
			
			page.setUrl(sb.toString());
			//3、保存分页的数据到request域中
			request.setAttribute("page", page);
			//4、转发到联系人展示页面
			request.getRequestDispatcher("/pages/access/contact/contact_manage.jsp").forward(request, response);
		}

}
