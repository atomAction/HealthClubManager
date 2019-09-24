<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<
<h3>用户添加页面</h3>

 <form id="saveForm" action="${pageContext.request.contextPath}/addCustomer" method="post">
		<table style="font-size: :16px">
			<tr>
				<td>姓名：</td>
				<td><input type="text" value="${Custoemr_form.name }" name="name" placeholder="First name"/></td></tr>
			<tr>
				<td>电话：</td>
				<td><input type="text" value="${Custoemr_form.phone }" name="phone" /></td>
			</tr>
			<tr>
			<tr>
				<td>充值金额：</td>
				<td><input type="text" value="${Custoemr_form.balance }" name="balance" /></td></tr><tr>
				<td>过期时间：</td>
				<td><input type="date" value="${Custoemr_form.effectivedeadline }" name="effectivedeadline" /></td></tr>
			<tr>
				<td align="right">
				<input type="submit" value="添加" /> &nbsp;&nbsp;
			</tr>
		</table>
	</form>
</body>
</html>