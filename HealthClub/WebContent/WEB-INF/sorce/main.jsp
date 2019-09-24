<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="0" width="900px">
<tr>
<td align="center" style="font-size:24px; color:#666"> 客户管理</td>
</tr>
<tr>
<td align="right" > <a href="${pageContext.request.contextPath}/addCustomer">添加</a></td>
<td align="right" > <a href="${pageContext.request.contextPath}/addCustomer">查找</a></td>
</tr>
</table>
<br/>
<table cellspacing="0" border="1" class="table1">
<thead>
   <tr>
    	<th width="300">姓名</th>
    	<th width="300">电话</th>
    	<th width="300">余额</th>
   		<th  width="300">有效期</th>
   		<th  width="300">删除和编辑</th>
   		<th  width="300">编辑和添加基本信息</th>
   </tr>
</thead>
<tbody>

<c:forEach var="p" items="${requestScope.customerlist }">
	<tr>
		<td align="center">${p.name }</td>
		<td align="center">${p.phone }</td>
		<td align="center">${p.balance }</td>
		<td align="center">${p.effectivedeadline }</td>
		<td align="center">
			<a href="${pageContext.request.contextPath}/deleteCustomerById?id=${p.c_id}"
			 onclick='return confirm("确定要删除吗")'>删除</a>
			 <a href="${pageContext.request.contextPath}/deleteCustomerById?id=${p.c_id}"
			 onclick='return confirm("确定要删除吗")'>删除</a>
		</td>
		<td align="center">
			<a href="${pageContext.request.contextPath}/editCustomerFile?id=${p.c_id}">编辑</a>
		</td>
		
	</tr>
</c:forEach>
</tbody>
</table>
<br/>


</body>
</html>