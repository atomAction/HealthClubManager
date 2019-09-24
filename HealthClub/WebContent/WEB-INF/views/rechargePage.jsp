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

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title text-center">余  额  充  值</h3>
		</div>
		<div class="panel-body">

			<div id="toolbar" class="btn-group">
				<div class="form-inline">
					<select class="form-control" id="selectForm"  >
						<option value="name">姓名</option>
						<option value="phone">电话</option>
					</select>
					<input class="form-control" id="searchText"  type="text" placeholder="请输入搜索内容"></input>
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

	<!-- 充值模态框（Modal） -->
	<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">余额充值</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label>当前余额 :&nbsp;&nbsp; </label><b id="nowBa"></b> 
						</div>
						<div class="form-group">
							<label>输入充值金额</label> <input type="number" class="form-control"
								id="addrecharge" style="width:250px">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" id="rechargeConfirmBtn">充值</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
</body>


<script type="text/javascript">

	$(document).ready(function() {
		$("button[name='toggle']").height(20);
		$("button[name='refresh']").height(20);
		$("#updateModal").modal('hide');
		$("#saveModel").modal('hide');
	});

	//充值按钮点击事件
	var idGlobal = 0;
	function recharge(id) {
		$("#updateModal").modal('show');
		idGlobal = id;
		//初始化用户信息
		$.ajax({
			url : 'queryCustomerById',
			data : {
				'id' : id
			},
			success : function(ret) {
				$("#nowBa").text(ret.balance);
			}
		});		
	}
	
	//充值模态框下确认按钮的点击事件
	$("#rechargeConfirmBtn").click(function() {
		$("#updateModal").modal('hide');
		$.ajax({
			url : "rechargeBalance",
			method : 'post',
			//contentType:'application/json;charset=utf-8',
			data : {
				c_id : idGlobal,
				balance : $("#addrecharge").val(),
			},
			success : function() {
				alert("充值成功！");
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
						url : "getCustomerInfo",
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
							//在ajax获取到数据，渲染表格之前，修改数据源
							return res;
						},
						columns : [
								{
									field : 'state',
								},
								{
									field : 'c_id',
									title : 'ID',
									align : 'center'
								},
								{
									field : 'name',
									title : '姓名',
									align : 'center'
								},
								{
									field : 'phone',
									title : '电话',
									align : 'center'
								},
								{
									field : 'balance',
									title : '余额',
									align : 'center'
								},
								{
									field : 'effectivedeadline',
									title : '到期时间',
									align : 'center'
								},
								{
									field : 'cardtype.typename',
									title : '卡类型',
									align : 'center'
								},
								{
									title : '操作',
									field : 'c_id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<button type="button" class="btn btn-success" onclick="recharge(\''
											+ row.c_id + '\')">充值</button> ';				
										return e ;
									}

								} ]
					});
</script>
</html>

