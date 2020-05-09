<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- 分页的开始 --%>
<div id="page_nav">

	<%--如果当前页不是第一页才显示首页和上一页 --%>
	<c:if test="${requestScope.page.pageNo> 1 }">
		<a href="${requestScope.page.url }&pageNo=1">首页</a>
		<a
			href="${requestScope.page.url }&pageNo=${requestScope.page.pageNo-1 }">上一页</a>
	</c:if>
	<c:if test="${requestScope.page.pageNo<= 1 }">
		【首页】
		【上一页】
		</c:if>

	<%--页码输出的开始 --%>
	<c:choose>
		<%--第一种情况：总页码数小于或等于5，页码范围是：1 到 总页码数 --%>
		<c:when test="${requestScope.page.pageTotal<=5 }">
			<c:set var="begin" value="1" />
			<c:set var="end" value="${requestScope.page.pageTotal }" />
		</c:when>
		<%--第二种情况：总页码数大于5 --%>
		<c:when test="${requestScope.page.pageTotal>5 }">
			<c:choose>
				<%--第一种小情况，当前页码数是前三个，即1，2，3，此时页码范围：1-5 --%>
				<c:when test="${requestScope.page.pageNo<=3 }">
					<c:set var="begin" value="1" />
					<c:set var="end" value="5" />
				</c:when>
				<%--第二种小情况 ,当前页码数是后三个，即8,910,此时页码范围：总页码-4 到 总页码--%>
				<c:when
					test="${requestScope.page.pageTotal-3<requestScope.page.pageNo }">
					<c:set var="begin" value="${requestScope.page.pageTotal-4 }" />
					<c:set var="end" value="${requestScope.page.pageTotal }" />
				</c:when>
				<%--第三种小情况 剩下的中间页码，此时页码范围：当前页码-2 到 当前页码+2--%>
				<c:otherwise>
					<c:set var="begin" value="${requestScope.page.pageNo-2 }" />
					<c:set var="end" value="${requestScope.page.pageNo+2 }" />
				</c:otherwise>
			</c:choose>
		</c:when>
	</c:choose>
	<%--页码输出的结束 --%>

	<c:forEach begin="${begin }" end="${end }" var="i">
		<c:if test="${requestScope.page.pageNo==i }">
				【${i }】
			</c:if>
		<c:if test="${requestScope.page.pageNo!=i }">
			<a href="${requestScope.page.url }&pageNo=${i }">${i }</a>
		</c:if>
	</c:forEach>

	<%--如果当前页不是最后一页才显示下一页和末页 --%>
	<c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal }">
		<a
			href="${requestScope.page.url }&pageNo=${requestScope.page.pageNo+1 }">下一页</a>
		<a
			href="${requestScope.page.url }&pageNo=${requestScope.page.pageTotal }">末页</a>
	</c:if>
	<c:if test="${requestScope.page.pageNo>=requestScope.page.pageTotal }">
		【下一页】
		【末页】
		</c:if>

	共${requestScope.page.pageTotal }页，${requestScope.page.pageTotalCount }条记录
	到第<input value="${requestScope.page.pageNo }" name="pn" id="pn_input" />页
	<input type="button" value="确定">
<!-- 	<script type="text/javascript">
	$(function(){
		$(".deleteB").click(function(){
			//confirm它是JavaScript语言中提供的一个确认提示框函数
			//传递的参数就是提示框提示内容
			//它有两个按钮，一个确定，一个取消，当用户点击了确定之后返回true，点击取消之后返回false
			return confirm("你确定要删除图书【"+$(this).parent().parent().find("td:first").text()+"】吗");
		})
		
		$(":button").click(function(){
			var pageNo=$("#pn_input").val();
			//location.href="${requestScope.page.url }&pageNo="+pageNo;
			location.href="${pageScope.path}${requestScope.page.url }&pageNo="+pageNo;
			
		})
	})
</script>
</div> -->
<%--分页条的结束 --%>