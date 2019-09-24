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
			<h3 class="panel-title text-center">专 业 职 级 管 理</h3>
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
					<h4 class="modal-title" id="myModalLabel">修改专业职级信息</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label>专业名称</label> <input type="text" class="form-control"
								id="profession_name">
						</div>
						<div class="form-group">
							<label>职级名称</label> <input type="text" class="form-control"
								id="rank_name">
						</div>
						<div class="form-group">
							<label>团课课时费</label> <input type="text" class="form-control"
								id="group_classfee">
						</div>
						<div class="form-group">
							<label>私教课时费</label> <input type="text" class="form-control"
								id="personal_calssfee">
						</div>
						<div class="form-group">
							<label>提成比例</label> <input type="text" class="form-control"
								id="commission">
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
					<h4 class="modal-title" id="myModalLabel">添加专业职级信息</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label>专业名称</label> <input type="text" class="form-control"
								id="saveprofession_name">
						</div>
						<div class="form-group">
							<label>职级名称</label> <input type="text" class="form-control"
								id="saverank_name">
						</div>

						<div class="form-group">
							<label>团课课时费</label> <input type="text" class="form-control"
								id="savegroup_classfee">
						</div>
						<div class="form-group">
							<label>私教课时费</label> <input type="text" class="form-control"
								id="savepersonal_calssfee">
						</div>
						<div class="form-group">
							<label>提成比例</label> <input type="text" class="form-control"
								id="savecommission">
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
				url : "deleteProfessionRankById",
				data : {
					"profession_number" : id
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
		$("#profession_number").val("");
		$("#profession_name").val("");
		$("#rank_name").val("");
		$("#group_classfee").val("");
		$("#personal_calssfee").val("");
		$("#commission").val("");
		//初始化用户信息
		$.ajax({
			url : 'queryProfessionRankById',
			data : {
				'profession_number' : id
			},
			success : function(ret) {
				$("#profession_name").val(ret.profession_name);
			
				$("#rank_name").val(ret.rank_name);
				
				$("#group_classfee").val(ret.group_classfee);
				$("#personal_calssfee").val(ret.personal_calssfee);
				$("#commission").val(ret.personal_calssfee_commission	); 
			}
		});		
	}
	
	//编辑模态框下确认按钮的点击事件
	$("#updateConfirmBtn").click(function() {
		$("#updateModal").modal('hide');
		$.ajax({
			url : "updateProfessionRank",
			method : 'post',
		//	contentType:'application/json;charset=utf-8',
			data : {
				profession_number : idGlobal,
				profession_name : $("#profession_name").val(),
				roomtype : $("#roomtype").val(),
				rank_name : $("#rank_name").val(),
				group_classfee : $("#group_classfee").val(),
				personal_calssfee : $("#personal_calssfee").val(),
				personal_calssfee_commission : $("#commission").val()
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
		//清楚输入框信息
		$("#profession_number").val("");
		$("#profession_name").val("");
		$("#rank_name").val("");
		$("#group_classfee").val("");
		$("#personal_calssfee").val("");
		$("#commission").val("");
	});
	
	//添加用户模态框下的确认按钮点击事件
	$("#saveConfirmBtn").click(function() {
		$("#saveModal").modal('hide');
		$.ajax({
			url : "saveProfessionRank",
			method : 'post',
			datatype:'json',
			data : {
				profession_number : idGlobal,
				profession_name : $("#saveprofession_name").val(),
				roomtype : $("#saveroomtype").val(),
				rank_name : $("#saverank_name").val(),
				group_classfee : $("#savegroup_classfee").val(),
				personal_calssfee : $("#savepersonal_calssfee").val(),
				personal_calssfee_commission : $("#savecommission").val()
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
						url : "getProfessionRankInfo",
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
								//searchType : $("#selectForm").val(),								
								//searchText :$("#searchText").val()
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
									field : 'profession_number',
									title : 'ID',
									align : 'center'
								},
								{
									field : 'profession_name',
									title : '专业名称',
									align : 'center'
								},
								{
									field : 'rank_name',
									title : '职级名称',
									align : 'center'
								},
								{
									field : 'group_classfee',
									title : '团课课时费',
									align : 'center'
								},
								{
									field : 'personal_calssfee',
									title : '私教课时费',
									align : 'center'
								},
								{
									field : 'personal_calssfee_commission',
									title : '提成比例',
									align : 'center'
								},
								{
									title : '操作',
									field : 'number',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a href="#" mce_href="#" onclick="edit(\''
												+ row.profession_number + '\')">编辑</a> ';
										var d = '<a href="#" mce_href="#" onclick="del(\''
												+ row.profession_number + '\')">删除</a> ';
										
										return e + d;
									}

								} ]
					});
</script>
</html>

