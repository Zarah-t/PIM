<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% String path=request.getScheme()+"://"
               +request.getServerName()
               +":"
               +request.getServerPort()
               +request.getContextPath()
               +"/";

	pageContext.setAttribute("path", path);
%>
<base href="<%=path %>"/>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jQuery_3.4.1.js"></script>