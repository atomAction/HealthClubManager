<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h4>客户原始信息编辑修改页面</h4>



    <form action="${pageContext.request.contextPath}/editCustomer" method="post">
    <input type="hidden"  value="${customerFile_form.c_id }"name="c_id" />
    	<table style="font-size: 16px">
    		<tr>
    			<td>姓名：</td><td><input type="text" value="${customerFile_form.job }" name="job"/></td>
    		</tr>
    		<tr>
    			<td>电话：</td>	<td> <input type="radio"value="${customerFile_form.sex }" name="sex" > 女<br></td>
    		</tr>
    		<tr>
    			<td>余额：</td><td><input type="date" value="${customerFile_form.birthday }" name="birthday"/></td>
    		</tr>
    		<tr>
    			<td>卡类型：</td><td><input type="text" value="${customerFile_form.aim }" name="aim"/></td>
    		</tr>
    		<tr>
    			<td>有效期至：</td><td><input type="text" value="${customerFile_form.aim }" name="aim"/></td>
    		</tr>
    		<tr><input type="submit" value="xiugai " /> &nbsp;&nbsp;</tr>
    	</table>
    </form>
</body>
</html>