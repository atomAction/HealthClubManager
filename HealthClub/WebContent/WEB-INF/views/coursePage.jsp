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
			<h3 class="panel-title text-center">课 程 管 理</h3>
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
					<select class="form-control" id="selectForm"  >
						<option value="number">课程编号</option>
						<option value="name">课程名称</option>	
					</select>
					<input class="form-control" id="searchText" style="margin-top: -68px;margin-left: 250px;" type="text" placeholder="请输入搜索内容"></input>
					<button class="btn btn-info" id="searchBtn" style="margin-top: -68px;">搜索</button>					
				</div>
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
					<h4 class="modal-title" id="myModalLabel">修改课程信息</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label>课程名称</label> <input type="text" class="form-control"
								id="name">
						</div>
						<div class="form-group">
							<label>授课资格职级</label> 
							<select  class="form-control" id="profession">
									<option value="1">瑜伽教练初级</option>
									<option value="2">瑜伽教练中级</option>
									<option value="3">瑜伽教练高级</option>
									<option value="4">初级健身教练</option>
									<option value="5">中级健身教练</option>
									<option value="6">高级健身教练</option>
									<option value="7">健身指导师</option>
								</select> 
						</div>
						<div class="form-group">
							<label>所需房间类型</label> 
							<select  class="form-control" id="roomtype">
									<option value="1">小房间</option>
									<option value="2">中房间</option>
									<option value="3">大房间</option>
								</select> 
						
						</div>
						<div class="form-group">
							<label>单次授课时间</label> <input type="text" class="form-control"
								id="coursetime">
						</div>
						<div class="form-group">
							<label>单次授课费用</label> <input type="text" class="form-control"
								id="coursefee">
						</div>
						<div class="form-group">
							<label>可免费客户卡</label>
							<select  class="form-control" id="freecardtype">
									<option value="次卡">次卡</option>
									<option value="次卡">月卡</option>
									<option value="季卡">季卡</option>
									<option value="年卡">年卡</option>
								</select> 
						</div>	
						<div class="form-group">
							<label>是否私教</label> <input type="text" class="form-control"
								id="personal">
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
					<h4 class="modal-title" id="myModalLabel">添加课程类型信息</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label>课程名称</label> <input type="text" class="form-control"
								id="savename">
						</div>
						<div class="form-group">
							<label>授课资格职级</label> 
							<select  class="form-control" id="saveprofession">
									<option value="1">瑜伽教练初级</option>
									<option value="2">瑜伽教练中级</option>
									<option value="3">瑜伽教练高级</option>
									<option value="4">初级健身教练</option>
									<option value="5">中级健身教练</option>
									<option value="6">高级健身教练</option>
									<option value="7">健身指导师</option>
								</select> 
						</div>
						<div class="form-group">
							<label>所需房间类型</label> 
							<select  class="form-control" id="saveroomtype">
									<option value="1">小房间</option>
									<option value="2">中房间</option>
									<option value="3">大房间</option>
								</select> 
						
						</div>
						<div class="form-group">
							<label>单次授课时间</label> <input type="text" class="form-control"
								id="savecoursetime">
						</div>
						<div class="form-group">
							<label>单次授课费用</label> <input type="text" class="form-control"
								id="savecoursefee">
						</div>
						<div class="form-group">
							<label>可免费客户卡</label>
							<select  class="form-control" id="savefreecardtype">
									<option value="次卡">次卡</option>
									<option value="次卡">月卡</option>
									<option value="季卡">季卡</option>
									<option value="年卡">年卡</option>
								</select> 
						</div>	
						<div class="form-group">
							<label>是否私教</label> <input type="text" class="form-control"
								id="savepersonal">
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
		$('input').iCheck({
			  radioClass: 'iradio_square-blue',
			  increaseArea: '20%' // optional
		});
	});

		//单个删除
	function del(id) {
		if (confirm("是否删除?")) {
			$.ajax({
				url : "deleteCourseById",
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
		$("#name").val("");
		$("#profession").val("");
		$("#roomtype").val("");
		$("#coursetime").val("");
		$("#coursefee").val("");
		$("#freecardtype").val("");
		$("#personal").val("");
		//初始化用户信息
		$.ajax({
			url : 'queryCourseById',
			data : {
				'jobnumber' : id
			},
			success : function(ret) {
				console.log(ret);
				$("#name").val(ret.name);
				$("#profession").val(ret.profession_number);
				$("#roomtype").val(ret.roomtype_number);
				$("#coursetime").val(ret.coursetime);
				$("#coursefee").val(ret.coursefee);
				$("#freecardtype").val(ret.freecardtype	);
				$("#personal").val(ret.ispersonal); 
			}
		});		
	}
	
	//编辑模态框下确认按钮的点击事件
	$("#updateConfirmBtn").click(function() {
		$("#updateModal").modal('hide');
		$.ajax({
			url : "updateCourse",
			method : 'post',
		//	contentType:'application/json;charset=utf-8',
			data : {
				number : idGlobal,
				name : $("#name").val(),
				profession_number : $("#profession").val(),
				roomtype_number : $("#roomtype").val(),
				coursetime : $("#coursetime").val(),
				coursefee : $("#coursefee").val(),
				freecardtype : $("#freecardtype").val(),
				ispersonal:$("#personal").val()
				
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
		$("#savename").val("");
		$("#saveprofession").val("");
		$("#saveroomtype").val("");
		$("#savecoursetime").val("");
		$("#savecoursefee").val("");
		$("#savefreecardtype").val("");
		$("#savepersonal").val("");
	});
	
	//添加用户模态框下的确认按钮点击事件
	$("#saveConfirmBtn").click(function() {
		console.log($("#savepersonal").val());
		$("#saveModal").modal('hide');
		$.ajax({
			url : "saveCourse",
			method : 'post',
			datatype:'json',
			data : {
				number : idGlobal,
				name : $("#savename").val(),
				profession_number : $("#saveprofession").val(),
				roomtype_number : $("#saveroomtype").val(),
				coursetime : $("#savecoursetime").val(),
				coursefee : $("#savecoursefee").val(),
				freecardtype : $("#savefreecardtype").val(),
				ispersonal:$("#savepersonal").val()
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
						url : "getCourseInfo",
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
							
							return res;
						},
						columns : [
								{
									field : 'state',
								},
								{
									field : 'number',
									title : 'ID',
									align : 'center'
								},
								{
									field : 'name',
									title : '课程名称',
									align : 'center'
								},
								{
									field : 'profession_number',
									title : '资格编号',
									align : 'center'
								},
								{
									field : 'roomtype_number',
									title : '房间编号',
									align : 'center'
								},
								{
									field : 'coursetime',
									title : '单次授课时间',
									align : 'center'
								},
								{
									field : 'coursefee',
									title : '单次授课费用',
									align : 'center'
								},
								{
									field : 'ispersonal',
									title : '是否私教',
									align : 'center'
								},
								{
									field : 'freecardtype',
									title : '最低免费卡型',
									align : 'center'
								},
								{
									title : '操作',
									field : 'number',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a href="#" mce_href="#" onclick="edit(\''
												+ row.number + '\')">编辑</a> ';
										var d = '<a href="#" mce_href="#" onclick="del(\''
												+ row.number + '\')">删除</a> ';
										
										return e + d;
									}

								} ]
					});
</script>
</html>

