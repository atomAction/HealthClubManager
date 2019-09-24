<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix= "form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<h4>基本信息编辑修改页面</h4>



    <form action="${pageContext.request.contextPath}/editCustomerFile" method="post">
    <input type="hidden"  value="${customerFile_form.f_id }"name="f_id" />
    	<table style="font-size: 16px">
    		<tr>
    			<td>职业：</td><td><input type="text" value="${customerFile_form.job }" name="job"/></td>
    		</tr>
    		<tr>
    			<td>性别：</td>	<td> <input type="radio"value="${customerFile_form.sex }" name="sex" >男
    								 <input type="radio"value="${customerFile_form.sex }" name="sex" > 女<br></td>
    		</tr>
    		<tr>
    			<td>出生年月：</td><td><input type="date" value="${customerFile_form.birthday }" name="birthday"/></td>
    		</tr>
    		<tr>
    			<td>健身目标：</td><td><input type="text" value="${customerFile_form.aim }" name="aim"/></td>
    		</tr>
    		<tr>
    			<td>健身频率：</td><td><input type="text" value="${customerFile_form.frequency }" name="frequency"/></td>
    		</tr>
    		<tr>
    			<td>伤病史：</td><td><input type="text" value="${customerFile_form.injuryhistory }" name="injuryhistory"/></td>
    		</tr>
    		<tr>
    			<td>生活习惯：</td><td><input type="text" value="${customerFile_form.hibit }" name="hibit"/></td>
    		</tr>
    		<tr><td align="right"/>
    		<input type="submit" value="xiugai " /> &nbsp;&nbsp;</tr>
    	</table>
    </form>

</body>
</html>