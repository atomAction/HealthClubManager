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

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title text-center">教 练 管 理</h3>
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
						<option value="courses">可授课程</option>
						<option value="professionranks">专职</option>
					</select>
					<input class="form-control" id="searchText" style="margin-top: -70px;margin-left: 270px;" type="text" placeholder="请输入搜索内容"></input>
					<button class="btn btn-info" id="searchBtn" style="margin-top: -70px;">搜索</button>					
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

	<!-- 编辑用户模态框（Modal） -->
	<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">修改教练信息</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label>姓名</label> <input type="text" class="form-control"
								id="name">
						</div>
						<div class="form-group">
							<label>性别</label> <input type="text" class="form-control"
								id="sex">
						</div>
						<div class="form-group">
							<label>照片</label> <input type="text" class="form-control"
								id="photo">
						</div>
						<div class="form-group">
							<label>可授课程</label> 
							<select  class="form-control" id="courses">
								  <option value="-1">请选择...</option>
								</select> 
						</div>
						<div class="form-group">
							<label>专业职级</label> 
							<select  class="form-control" id="professionranks">
								  <option value="-1">请选择...</option>
								</select> 
						</div>
						<div class="form-group">
							<label>底薪</label> <input type="text" class="form-control"
								id="basicsalary">
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
	<!-- 添加教练模态框（Modal） -->
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
							<label>姓名</label> <input type="text" class="form-control"
								id="savename">
						</div>
						<div class="form-group">
							<label>性别</label> <input type="text" class="form-control"
								id="savesex">
						</div>
						<div class="form-group">
							<label>照片</label> <input type="text" class="form-control"
								id="savephoto">
						</div>
						<div class="form-group">
							<label>可授课程</label> 
							<select  class="form-control" id="savacourses">
								  <option value="-1">请选择...</option>
								</select> 
						</div>
						<div class="form-group">
							<label>专业职级</label> 
							<select  class="form-control" id="saveprofessionranks">
								  <option value="-1">请选择...</option>
								</select> 
						</div>
						<div class="form-group">
							<label>底薪</label> <input type="text" class="form-control"
								id="savebasicsalary">
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

	function del(id) {
		if (confirm("是否删除?")) {
			$.ajax({
				url : "deleteInstructorById",
				data : {
					"jobnumber" : id
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
		$("#sex").val("");
		$("#photo").val("");
		$("#courses").empty();
		$("#professionranks").empty();
		$("#basicsalary").val("");
		//初始化用户信息
		$.ajax({
			url : 'queryInstructorById',
			data : {
				'jobnumber':idGlobal
			},
			success : function(data) {
				console.log(data);
				$("#name").val(data.instructor.name);
				$("#sex").val(data.instructor.sex);
				$("#photo").val(data.instructor.photo);
				$("#basicsalary").val(data.instructor.basicsalary);
				$.each(data.courseList, function(index, courseList) {
		            $("#courses").append(  //此处向select中循环绑定数据
		    				"<option value="+courseList.number+">" + courseList.name+ "</option>");
		        });
				$("#courses").val(data.instructor.courses.number);
				$.each(data.PRList, function(index, PRList) {
		            $("#professionranks").append(  //此处向select中循环绑定数据
		    				"<option value="+PRList.profession_number+">" + PRList.rank_name+ "</option>");
		        });
				$("#professionranks").val(data.instructor.professionranks.profession_number);
			}
		});		
	}
	
	//编辑模态框下确认按钮的点击事件
	$("#updateConfirmBtn").click(function() {
		$("#updateModal").modal('hide');
		$.ajax({
			url : "updateInstructor",
			method : 'post',
		//	contentType:'application/json;charset=utf-8',
			data : {
				jobnumber : idGlobal,
				name : $("#name").val(),
				sex : $("#sex").val(),
				photo : $("#photo").val(),
				coursesId :$("#courses option:selected").val(),
				professionranksId :$("#professionranks option:selected").val(),
				basicsalary : $("#basicsalary").val(),
				
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
		$("#savesex").val("");
		$("#savephoto").val("");
		$("#savacourses").empty();
		$("#saveprofessionranks").empty();
		$("#savebasicsalary").val("");
		//初始化用户信息
		$.ajax({
			url : 'queryInstructorForInfoById',
			data : {
				
			},
			success : function(data) {
				console.log(data);
				$.each(data.courseList, function(index, courseList) {
		            $("#savacourses").append(  //此处向select中循环绑定数据
		    				"<option value="+courseList.number+">" + courseList.name+ "</option>");
		        });
				$.each(data.PRList, function(index, PRList) {
		            $("#saveprofessionranks").append(  //此处向select中循环绑定数据
		    				"<option value="+PRList.profession_number+">" + PRList.rank_name+ "</option>");
		        });
			}
		});		
	});
	
	//添加用户模态框下的确认按钮点击事件
	$("#saveConfirmBtn").click(function() {
		$("#saveModal").modal('hide');
		$.ajax({
			url : "saveInstructor",
			method : 'post',
			datatype:'json',
			data : {
				jobnumber : 10,
				name : $("#savename").val(),
				sex : $("#savesex").val(),
				photo : $("#savephoto").val(),
				coursesId :$("#savacourses option:selected").val(),
				professionranksId :$("#saveprofessionranks option:selected").val(),
				basicsalary : $("#savebasicsalary").val(),
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
				idList[i] = list[i].jobnumber;
			}
			if (confirm("是否删除选中的数据?")) {
				$.ajax({
					url : "deleteInstructorByList",
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
						url : "getInstructorInfo",
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
									field : 'jobnumber',
									title : 'ID',
									align : 'center'
								},
								{
									field : 'name',
									title : '姓名',
									align : 'center'
								},
								{
									field : 'sex',
									title : '性别',
									align : 'center'
								},
								{
									field : 'courses.name',
									title : '可授课程',
									align : 'center'
								},
								{
									field : 'professionranks.rank_name',
									title : '专业职级',
									align : 'center'
								},
								{
									field : 'basicsalary',
									title : '底薪',
									align : 'center'
								},
								{
									title : '操作',
									field : 'jobnumber',
									align : 'center',
									formatter : function(value, row, index) {
									
										var e = '<button type="button" class="btn btn-default" onclick="edit(\''
												+ row.jobnumber + '\')">编辑</button> ';
										var d = '<button type="button" class="btn btn-danger" onclick="del(\''
												+ row.jobnumber + '\')">删除</button> ';
										
										return e + d;
									}

								} ]
					});
</script>
</html>

