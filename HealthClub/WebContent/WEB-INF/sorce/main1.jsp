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
	
	<!-- bootstrap-table.min.js -->
	<script src="js/bootstrap-table.min.js"></script>
	<!-- 引入中文语言包 -->
	<script src="js/bootstrap-table-zh-CN.min.js"></script>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title text-center">Bootstrap-table样例演示</h3>
		</div>
		<div class="panel-body">

			<div id="toolbar" class="btn-group">
				<button id="btn_save" type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>新增
				</button>
				<button id="btn_delete" type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
				</button>
				<div class="form-inline">
					<select class="form-control" id="selectForm"  style="margin-left: 20px;">
						<option value="id">ID</option>
						<option value="name">用户名</option>
						<option value="age">年龄</option>
						<option value="address">地址</option>
					</select>
					<input class="form-control" id="searchText" style="margin-top: -70px;margin-left: 250px;" type="text" placeholder="请输入搜索内容"></input>
					<button class="btn btn-info" id="searchBtn" style="margin-top: -70px;">搜索</button>					
				</div>
			</div>

			<table data-toggle="table" id="table" data-height="400"
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
$(function () { 

		$("button[name='toggle']").height(20);
		$("button[name='refresh']").height(20);
 
 		$("#superBtn").click(function () {
			$.get("getPageInfo?limit=5&offset=0", function (data, status) {
				alert(status);
				alert(data.userList[0].name);
			});
		}); 
 	})
		$("#table").bootstrapTable({
				url: "getCustomerInfo",    //数据请求路径
				clickToSelect: true,  //点击表格项即可选择
				dataType: "json",   //后端数据传递类型
				pageSize: 5,
				pageList: [5, 10, 20],
				showRefresh: true,
				//contentType : "application/x-www-form-urlencoded",
				method: 'get',      //请求类型
				dataField: "data",  //很重要，这是后端返回的实体数据！					
				//是否显示详细视图和列表视图的切换按钮
				queryParams: function (params) {//自定义参数，这里的参数是传给后台的，我这是是分页用的  
					return {//这里的params是table提供的  
						offset: params.offset,//从数据库第几条记录开始  
						limit: params.limit
						//找多少条  
					};
				},
				responseHandler: function (res) {
					//在ajax获取到数据，渲染表格之前，修改数据源
					return res;
				},
				columns: [
					{
						field: 'state',
					},

					{
						field: 'name',
						title: '姓名',
						align: 'center'
					},
					{
						field: 'phone',
						title: '电话',
						align: 'center'
					},
					{
						field: 'balance',
						title: '余额',
						align: 'center'
					},
					{
						field: 'effectivedeadline',
						title: '到期时间',
						align: 'center'
					},
					{
						field: 'cardtype.typename',
						title: '卡类型',
						align: 'center'
					},
					{
						title: '操作',
						field: 'id',
						align: 'center',
						formatter: function (value, row, index) {
							var e = '<a href="#" mce_href="#" onclick="edit(\''
								+ row.id + '\')">编辑</a> ';
							var d = '<a href="#" mce_href="#" onclick="del(\''
								+ row.id + '\')">删除</a> ';
							return e + d;
						}

					}]
			});

</script>

</html>