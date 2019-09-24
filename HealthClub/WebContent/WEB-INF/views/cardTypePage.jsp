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


<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title text-center">会 员 卡 管 理</h3>
		</div>
		<div class="panel-body">

			<div id="toolbar" class="btn-group">
				<button id="btn_save" type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>新增
				</button>
				<button id="btn_delete" type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
				</button>
				
			</div>

			<table data-toggle="table" id="table" data-height="550"
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

	<!-- 编辑课程模态框（Modal） -->
	<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">修改会员卡信息</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label>会员卡名称</label> <input type="text" class="form-control"
								id="typename">
						</div>
						<div class="form-group">
							<label>折扣系数</label> <input type="text" class="form-control"
								id="discount">
						</div>
						<div class="form-group">
							<label>有效时间（天）</label><input type="text" class="form-control"
								id="effectivetime">
						</div>
						<div class="form-group">
							<label>价格</label> <input type="text" class="form-control"
								id="price">
						</div>
								
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" id="updateConfirmBtn">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- 添加课程模态框（Modal） -->
	<div class="modal fade" id="saveModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">添加教练信息</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label>会员卡名称</label> <input type="text" class="form-control"
								id="savetypename">
						</div>
						<div class="form-group">
							<label>折扣系数</label> <input type="text" class="form-control"
								id="savediscount">
						</div>
						<div class="form-group">
							<label>有效时间（天）</label><input type="text" class="form-control"
								id="saveeffectivetime">
						</div>
						<div class="form-group">
							<label>价格</label> <input type="text" class="form-control"
								id="saveprice">
						</div>		
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" id="saveConfirmBtn">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</body>


<script type="text/javascript">

	$(document).ready(function() {
		$("button[name='toggle']").height(20);
		$("button[name='refresh']").height(20);
		$("#updateModal").modal('hide');
		$("#saveModel").modal('hide');
	
	});	
	//单个删除
	function del(id) {
		if (confirm("是否删除?")) {
			$.ajax({
				url : "deleteCardTypeById",
				data : {
					"id" : id
				},
				success : function(data) {
					alert("删除成功！");
					//重新加载表格
					$("#table").bootstrapTable("refresh");
				}
			});
		} else {
			}
	}

	//编辑按钮点击事件
	var idGlobal = 0;
	function edit(id) {
		$("#updateModal").modal('show');
		idGlobal = id;
		//清楚输入框信息
		$("#typename").val("");
		$("#discount").val("");
		$("#effectivetime").val("");
		$("#price").val("");

		//初始化用户信息
		$.ajax({
			url : 'queryCardTypeById',
			data : {
				'id' : id
			},
			success : function(ret) {
				$("#typename").val(ret.typename);
				$("#discount").val(ret.discount);
				$("#effectivetime").val(ret.effectivetime);
				$("#price").val(ret.price);
			}
		});		
	}
	
	//编辑模态框下确认按钮的点击事件
	$("#updateConfirmBtn").click(function() {
		$("#updateModal").modal('hide');
		$.ajax({
			url : "updateCardType",
			method : 'post',
		//	contentType:'application/json;charset=utf-8',
			data : {
				t_id : idGlobal,
				typename : $("#typename").val(),
				discount : $("#discount").val(),
				effectivetime : $("#effectivetime").val(),
				price : $("#price").val(),
				
			},
			success : function() {
				alert("修改成功！");
				//重新加载表格
				$("#table").bootstrapTable("refresh");
			}
		});
	});

	//添加按钮点击事件
	$("#btn_save").click(function() {
		$("#saveModal").modal('show');
		//清楚输入框信息
		$("#savetypename").val("");
		$("#savediscount").val("");
		$("#saveeffectivetime").val("");
		$("#saveprice").val("");
	});
	
	//添加用户模态框下的确认按钮点击事件
	$("#saveConfirmBtn").click(function() {
		$("#saveModal").modal('hide');
		$.ajax({
			url : "saveCardType",
			method : 'post',
			datatype:'json',
			data : {
				t_id : idGlobal,
				typename : $("#savetypename").val(),
				discount : $("#savediscount").val(),
				effectivetime : $("#saveeffectivetime").val(),
				price : $("#saveprice").val()
			},
			success : function() {
				alert("添加成功！");
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
						url : "getCardTypeInfo",
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
									field : 't_id',
									title : 'ID',
									align : 'center'
								},
								{
									field : 'typename',
									title : '客户卡',
									align : 'center'
								},
								{
									field : 'discount',
									title : '折扣',
									align : 'center'
								},
								{
									field : 'effectivetime',
									title : '有效期',
									align : 'center'
								},
								{
									field : 'price',
									title : '价格',
									align : 'center'
								},
								{
									title : '操作',
									field : 't_id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a href="#" mce_href="#" onclick="edit(\''
												+ row.t_id + '\')">编辑</a> ';
										var d = '<a href="#" mce_href="#" onclick="del(\''
												+ row.t_id + '\')">删除</a> ';
										
										return e + d;
									}

								} ]
					});
</script>
</html>

