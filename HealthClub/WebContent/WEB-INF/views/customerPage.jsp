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
			<h3 class="panel-title text-center">客户管理</h3>
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
						<option value="name">姓名</option>
						<option value="phone">电话</option>
		
					</select>
					<input class="form-control" id="searchText" style="margin-top: -70px;margin-left: 250px;" type="text" placeholder="请输入搜索内容"></input>
					<button class="btn btn-info" id="searchBtn" style="margin-top: -70px;">搜索</button>					
				</div>
			</div>

			<table data-toggle="table" id="table" data-height="650"
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

	<!-- 编辑用户模态框（Modal） -->
	<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">修改客户信息</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label>姓名</label> <input type="text" class="form-control"
								id="name">
						</div>
						<div class="form-group">
							<label>电话</label> <input type="number" class="form-control"
								id="phone">
						</div>
						<div class="form-group">
							<label>余额</label> <input type="number" class="form-control"
								id="balance">
						</div>
						<!-- <div class="form-group">
							<label>卡类型</label><select  class="form-control" id="cardtype">
									<option value="ci">次卡</option>
									<option value="tian">天卡</option>
									<option value="yue">月卡</option>
									<option value="ji">季卡</option>
									<option value="nian">年卡</option>
								</select> 
						</div> -->
						
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
	<!-- 添加用户模态框（Modal） -->
	<div class="modal fade" id="saveModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">添加用户信息</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label>姓名</label> <input type="text" class="form-control"
								id="savename">
						</div>
						<div class="form-group">
							<label>电话</label> <input type="number" class="form-control"
								id="savephone">
						</div>
						<div class="form-group">
							<label>余额</label> <input type="number" class="form-control"
								id="savebalance">
						</div>
						<div class="form-group">
							<label>卡类型</label><select  class="form-control" id="savecardtype">
									<option value="ci">次卡</option>
									<option value="tian">天卡</option>
									<option value="yue">月卡</option>
									<option value="ji">季卡</option>
									<option value="nian">年卡</option>
								</select> 
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
	
	<!-- 个人信息模态框（Modal） -->
	<div class="modal fade" id="FileModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog ">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">用户信息</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-inline">
							<label>Id</label> <input type="text" class="form-control"
								id="f_id">
						</div>
						
						<div class="form-group">
							<div class="form-inline">
								<label>性别</label> <input type="text" class="form-control"
										id="sex">
								<label>工作</label> <input type="text" class="form-control"
										id="job">
							</div>
						</div>
						<div class="form-group">
							<div class="form-inline">
								<label>生日</label> <input type="date" class="form-control"
										id="birthday">
								<label>目标</label> <input type="text" class="form-control"
										id="aim">
							</div>
						</div>
						<div class="form-group">
							<div class="form-inline">
								<label>健身频率(天/次)</label> <input type="number" class="form-control"
										id="frequency">
								<label>伤病史</label> <input type="text" class="form-control"
										id="injuryhistory">
							</div>
						</div>
						<div class="form-group">
							<div class="form-inline">
								<label>爱好</label> <input type="text" class="form-control"
										id="hibit">
								<label>身高</label> <input type="text" class="form-control"
										id="hight">
							</div>
						</div>
						<div class="form-group">
							<div class="form-inline">
								<label>体重</label> <input type="text" class="form-control"
										id="wight">
								<label>BMI</label> <input type="text" class="form-control"
										id="bmi">
							</div>
						</div>
						<div class="form-group">
							<div class="form-inline">
								<label>体脂率</label> <input type="text" class="form-control"
										id="fatrate">
								<label>心肺状况</label> <input type="text" class="form-control"
										id="heartfunction">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" id="fileConfirmBtn">提交更改</button>
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
		$("#FileModal").modal('hide');
	});


	function del(id) {
		if (confirm("是否删除?")) {
			$.ajax({
				url : "deleteCustomerById",
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
	
	//详情模态框
	function file(id) {
		$("#FileModal").modal('show');
		idGlobal = id;
		//清楚输入框信息
		$("#f_id").val("");
		$("#sex").val("");
		$("#job").val("");
		$("#birthday").val("");
		$("#aim").val("");
		$("#frequency").val("");
		$("#injuryhistory").val("");
		$("#hibit").val("");
		$("#hight").val("");
		$("#wight").val("");
		$("#bmi").val("");
		$("#fatrate").val("");
		$("#heartfunction").val("");
		//初始化用户信息
		$.ajax({
			url : 'queryCustomerFileById',
			data : {
				'f_id' : id
			},
			success : function(ret) {
				console.log(ret);
				$("#f_id").val(ret.f_id);
				$("#sex").val(ret.sex);
				$("#job").val(ret.job);
				$("#birthday").val(ret.birthday);
				$("#aim").val(ret.aim);
				$("#frequency").val(ret.frequency);
				$("#injuryhistory").val(ret.injuryhistory);
				$("#hibit").val(ret.hibit);
				$("#hight").val(ret.hight);
				$("#wight").val(ret.wight);
				$("#bmi").val(ret.bmi);
				$("#fatrate").val(ret.fatrate);
				$("#heartfunction").val(ret.heartfunction);
			}
		});		
	}
	
	//详情模态框下确认按钮的点击事件
	$("#fileConfirmBtn").click(function() {
		$("#FileModal").modal('hide');
		$.ajax({
			url : "updateFile",
			method : 'post',
			contentType:'application/json;charset=utf-8',
			data : JSON.stringify({
				f_id : idGlobal,
				sex:$("#sex").val(),
				job:$("#job").val(),
				birthday:$("#birthday").val(),
				aim:$("#aim").val(),
				frequency:$("#frequency").val(),
				injuryhistory:$("#injuryhistory").val(),
				hibit:$("#hibit").val(),
				hight:$("#hight").val(),
				wight:$("#wight").val(),
				bmi:$("#bmi").val(),
				fatrate:$("#fatrate").val(),
				heartfunction:$("#heartfunction").val()
			}),
			success : function() {
				alert("修改成功！");
				//重新加载表格
				$("#table").bootstrapTable("refresh");
			}
		});
	});


	//编辑按钮点击事件
	var idGlobal = 0;
	function edit(id) {
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

	//添加按钮点击事件
	$("#btn_save").click(function() {
		//借用修改的模态框
		$("#saveModal").modal('show');
		//清楚输入框信息
		$("#savename").val("");
		$("#savephone").val("");
		$("#savebalance").val("");	
		$("#savecardtype").val("");
	});
	
	//添加用户模态框下的确认按钮点击事件
	$("#saveConfirmBtn").click(function() {
		$("#saveModal").modal('hide');
		$.ajax({
			url : "saveCustomer",
			method : 'post',
			datatype:'json',
			data : {
				id : 0,
				name : $("#savename").val(),
				phone : $("#savephone").val(),
				balance : $("#savebalance").val(),
				cardtype: $("#savecardtype").val()
			},
			success : function() {
				alert("添加成功！");
				//重新加载表格
				$("#table").bootstrapTable("refresh");
			}
		});
	});

	//删除按钮点击事件（可以批量删除）
	$("#btn_delete").click(function() {
		var list = $("table").bootstrapTable('getSelections');
		if (list == null || list.length <= 0) {
			alert("未选中任何项！");
		} else {
			var idList = new Array();
			for (var i = 0; i < list.length; i++) {
				idList[i] = list[i].c_id;
			}
			if (confirm("是否删除选中的数据?")) {
				$.ajax({
					url : "deleteCustomerByList",
					type : 'post',
					dataType : 'json',
					// 				contentType:"application/json", 
					data : {
						idList : idList
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
										var e = '<button type="button" class="btn btn-default" onclick="edit(\''
												+ row.c_id + '\')">编辑</button> ';
										var d = '<button type="button" class="btn btn-danger" onclick="del(\''
												+ row.c_id + '\')">删除</button> ';
										var s = '<button type="button" class="btn btn-default" onclick="file(\''
												+ row.c_id + '\')">详情</button> ';
										return e + d + s;
									}

								} ]
					});
</script>
</html>

