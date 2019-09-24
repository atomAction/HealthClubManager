<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- 引入bootstrap样式 -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<!-- 引入bootstrap-table样式 -->
	<link href="css/bootstrap-table.min.css" rel="stylesheet">
	
	
	<!-- jquery -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/icheck.js"></script>
	
	<!-- bootstrap-table.min.js -->
	<script src="js/bootstrap-table.min.js"></script>
	<!-- 引入中文语言包 -->
	<script src="js/bootstrap-table-zh-CN.min.js"></script>


<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title text-center">工 资 结 算</h3>
		</div>
		<div class="panel-body">

			<div id="toolbar" class="btn-group">
				<div class="form-inline">
					<select class="form-control" id="selectForm"  style="margin-left: 20px;">
						<option value="id">ID</option>
						<option value="month">月份</option>	
					</select>
					<input class="form-control" id="searchText"  type="text" placeholder="请按条件搜索"></input>
					<button class="btn btn-info" id="searchBtn" >搜索</button>
					
										
				</div>
			</div>

			<table data-toggle="table" id="table" data-height="450"
				data-classes="table table-hover" data-striped="true"
				data-pagination="true" data-side-pagination="server"
				data-show-refresh="true" data-show-toggle="true"
				data-show-columns="true" data-toolbar="#toolbar">
				<thead>
					<tr>
						<th data-field="state" data-checkbox='ture'></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	
</body>


<script type="text/javascript">

	$(document).ready(function() {
		$("button[name='toggle']").height(20);
		$("button[name='refresh']").height(20);
		$("#updateModal").modal('hide');
		$("#saveModel").modal('hide');
	});


	function book(id) {
		if (confirm("是否预定?")) {
			$.ajax({
				url : "bookLessonById",
				data : {
					"id" : id,
					"c_id":$("#searchText").val()
				},
				success : function(data) {
					alert("预定成功！");
					//重新加载表格
					$("#table").bootstrapTable("refresh");
				}
			});
		} else {
		}
	}

	//预定按钮点击事件
	var idGlobal = 0;
	function boo(id) {
		$("#updateModal").modal('show');
		idGlobal = id;
		//清楚输入框信息
		$("#name").val("");
		$("#phone").val("");
		$("#balance").val("");
		//初始化用户信息
		$.ajax({
			url : 'queryCustomerById',
			data : {
				'id' : id
				
			},
			success : function(ret) {
				$("#name").val(ret.name);
				$("#phone").val(ret.phone);
				$("#balance").val(ret.balance);
				temp = ret;
			}
		});		
	}
	
	//编辑模态框下确认按钮的点击事件
	$("#updateConfirmBtn").click(function() {
		$("#updateModal").modal('hide');
		$.ajax({
			url : "updateCustomer",
			method : 'post',
			contentType:'application/json;charset=utf-8',
			data : JSON.stringify({
				c_id : idGlobal,
				name : $("#name").val(),
				phone : $("#phone").val(),
				balance : $("#balance").val(),
				effectivedeadline:temp.effectivedeadline,
				cardtype:temp.cardtype
			}),
			success : function() {
				alert("修改成功！");
				//重新加载表格
				$("#table").bootstrapTable("refresh");
			}
		});
	});
	

	//搜索按钮点击事件
	$("#searchBtn").click(function(){
		
		$("#table").bootstrapTable("refresh");
		//清空搜索内容
// 		$("#selectForm").val('');
// 		$("#searchText").val('');
	});
	
	$("#table")
			.bootstrapTable(
					{
						url : "getWageInFo",
						clickToSelect : true,
						dataType : "json",
						pageSize : 5,
						pageList : [ 5, 10, 20 ],
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						method : 'get',
						dataField : "data",
						//是否显示详细视图和列表视图的切换按钮
						queryParams : function(params) {//自定义参数，这里的参数是传给后台的，我这是是分页用的  
							return {//这里的params是table提供的  
								offset : params.offset,//从数据库第几条记录开始  
								limit : params.limit,//找多少条
								searchType : $("#selectForm").val(),								
								searchText :$("#searchText").val()
							};
						},
						responseHandler : function(res) {
							return res;
						},
						columns : [
							{
								field : 'state',
							},
							{
								field : 'instructor.jobnumber',
								title : '教练ID',
								align : 'center'
							},
							{
								field : 'instructor.name',
								title : '教练姓名',
								align : 'center'
							},
							{
								field : 'month',
								title : '月份',
								align : 'center'
							},
							{
								field : 'monthWage',
								title : '工资',
								align : 'center'
							} ]
					});
</script>
</html>

