<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 引入bootstrap样式 -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<!-- 引入bootstrap-table样式 -->
	<link href="css/bootstrap-table.min.css" rel="stylesheet">
	<!-- 引入bootstrap-datetimepicker样式 -->
	<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	
	<link rel="stylesheet"href="css/blue.css" />
	
	<!-- jquery -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/icheck.js"></script>
	
	<!-- bootstrap-table.min.js -->
	<script src="js/bootstrap-table.min.js"></script>
	<!-- bootstrap-datetimepicker.min.js -->
	<script src="js/bootstrap-datetimepicker.min.js"></script>
	<!-- 引入中文语言包 -->
	<script src="js/bootstrap-table-zh-CN.min.js"></script>
	<script src="js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="js/echarts.js"></script>
	<script src="js/bootstrap-datetimepicker.zh-CN.js"></script>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title text-center">充值消费记录查询</h3>
		</div>
		<div class="panel-body">
			<div id="toolbar" class="btn-group">
				<div class="form-inline">
					<input type="datetime" value="" "
							name="start_time" id="starttime" class="datetimepicker" readonly>
					<input type="datetime" value="" 
							name="end_time" id="endtime" class="datetimepicker" readonly>
					<select class="form-control" id="selectForm" >
						<option value="-1">请选择</option>
						<option value="id">ID</option>
						<option value="name">姓名</option>	
					</select>
					<input class="form-control" id="searchText"  type="text" placeholder="请按条件搜索充消记录"></input>
					<button class="btn btn-info" id="searchBtn" >搜索</button>
					<b>当前客户:</b><span  class="label label-success" id ="now_customer" style="font-size: 18px"></span>
										
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
		
		$('.datetimepicker').datetimepicker({
			bootcssVer:3,
			startDate:"2019-01-01",
			language:  'zh-CN',
		    format: 'yyyy-mm-dd hh:ii:ss',
		    autoclose: true,
		    clearBtn: true,//清除按钮
            todayBtn: true,//今日按钮
            showDropdowns: true,
            autoUpdateInput: true,
		    minView: "hour",
		    minuteStep:30
		});

	});


	

	//搜索按钮点击事件
	$("#searchBtn").click(function(){
		if($("#selectForm").val() == "name"){
			$.ajax({
				url : 'queryCustomerByName',
				data : {
					'name' : $("#searchText").val()
				},
				success : function(ret) {
					$("#now_customer").text(ret.name);
				}
			});
			
		}else if($("#selectForm").val() == "id"){
			$.ajax({
				url : 'queryCustomerById',
				data : {
					'id' : $("#searchText").val()
				},
				success : function(ret) {
					$("#now_customer").text(ret.name);
				}
			});
		}
		$("#table").bootstrapTable("refresh");
		//清空搜索内容
// 		$("#selectForm").val('');
// 		$("#searchText").val('');
	});
	
	$("#table")
			.bootstrapTable(
					{
						url : "getRecords",
						clickToSelect : true,
						dataType : "json",
						pageSize : 10,
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
								searchText :$("#searchText").val(),
								starttime:$("#starttime").val(),
								endtime:$("#endtime").val()
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
								field : 'id',
								title : 'ID',
								align : 'center'
							},
							{
								field : 'customer.c_id',
								title : '客户Id',
								align : 'center'
							},
							{
								field : 'customer.name',
								title : '客户姓名',
								align : 'center'
							},
							{
								field : 'amount',
								title : '金额',
								align : 'center'
							},
							{
								field : 'date',
								title : '时间',
								align : 'center'
							},
							{
								field : 'recordType',
								title : '消费类型',
								align : 'center'
							} ]
					});
</script>
</html>

