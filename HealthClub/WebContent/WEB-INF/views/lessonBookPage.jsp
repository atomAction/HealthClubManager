<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 引入bootstrap样式 -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<!-- 引入bootstrap-table样式 -->
	<link href="css/bootstrap-table.min.css" rel="stylesheet">
	
	<link rel="stylesheet"href="css/blue.css" />
	
	<!-- jquery -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/icheck.js"></script>
	
	<!-- bootstrap-table.min.js -->
	<script src="js/bootstrap-table.min.js"></script>
	<!-- 引入中文语言包 -->
	<script src="js/bootstrap-table-zh-CN.min.js"></script>

<meta charset="utf-8"/>
<title>Insert title here</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title text-center">课程预定</h3>
		</div>
		<div class="panel-body">

			<div id="toolbar" class="btn-group">
				<div class="form-inline">
					<select class="form-control" id="selectForm"  style="margin-left: 20px;">
						<option value="2">请选择...</option>
						<option value="id">ID</option>
						<option value="name">姓名</option>	
					</select>
					<input class="form-control" id="searchText"  type="text" placeholder="请按条件搜索可预订的课程"></input>
					<button class="btn btn-info" id="searchBtn" >搜索</button>
					<b>当前客户:</b><span  class="label label-success" id ="now_customer" style="font-size: 18px"></span>
										
				</div>
			</div>

			<table data-toggle="table" id="table" data-height="500"
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

	

	//搜索按钮点击事件
	$("#searchBtn").click(function(){
		
		if($("#selectForm").val() == "name"){
			console.log($("#searchText").val());
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
						url : "getLessonsForCoustomer",
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
								searchText :$("#searchText").val()
							};
						},
						responseHandler : function(res) {
							//在ajax获取到数据，渲染表格之前，修改数据源
							return res;
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
								field : 'courseType.name',
								title : '课程名称',
								align : 'center'
							},
							{
								field : 'instructor.name',
								title : '授课老师',
								align : 'center'
							},
							{
								field : 'room.roomname',
								title : '授课房间号',
								align : 'center'
							},
							{
								field : 'starttime',
								title : '开始时间',
								align : 'center'
							},
							{
								field : 'endtime',
								title : '结束时间',
								align : 'center'
							},
							
							{
								field : 'status',
								title : '当前状态',
								align : 'center'
							},
								{
									title : '操作',
									field : 'c_id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<button type="button" class="btn btn-default" onclick="book(\''
												+ row.id + '\')">预订</button> ';		
										return e ;
									}

								} ]
					});
</script>
</html>

