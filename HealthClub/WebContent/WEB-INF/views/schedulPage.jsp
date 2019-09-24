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


<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title text-center">排  课 管 理</h3>
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
			<div id="toolbar" class="btn-group">
				<div class="form-inline">
					<select class="form-control" id="selectForm"  >
						<option value="number">课程编号</option>
						<option value="name">课程名称</option>	
					</select>
					<input class="form-control" id="searchText" type="text" placeholder="请输入搜索内容"></input>
					<button class="btn btn-info" id="searchBtn" >搜索</button>					
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
							<label>选择课程</label> 
							<select  class="form-control" id="lessonNumber">
								  <option value="-2">请选择...</option>
								</select> 
						</div>
						<div class="form-group">
							<label>课程开始时间</label>
							<input type="datetime" value="键入时间" 
							name="start_time" id="starttime" class="datetimepicker" readonly>
						</div>	
						<a href="#" id="btn_query2">&nbsp;&nbsp;查询 </a>
						<div class="form-group">
							<label>可用教练</label> 
							<select  class="form-control" id="lessonInstructor">
								  <option value="-1">请选择...</option>
								</select> 
						</div>
						<div class="form-group">
							<label>可用教室</label> 
							<select  class="form-control" id="lessonRoom">
								  <option value="-1">请选择...</option>
								</select> 
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
					<h4 class="modal-title" id="myModalLabel">新建一门在线课程</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label>选择课程</label> 
							<select  class="form-control" id="savalessonNumber">
								  <option value="-2">请选择...</option>
								</select> 
						</div>
						
						<div class="form-group">
							<label>课程开始时间</label>
							<input type="datetime" value="键入时间" 
							name="start_time" id="savestarttime" class="datetimepicker" readonly>
						</div>	
						
						<!--  
							<label>选择时间</label> 
							<div class="input-append date form_datetime" style="float: left; padding-right:20px">
							<input id = "startTime" name="startTime" size="16"type="text" value="${obj.startTimeStr}" readonly>
								<span class="add-on"><i class="icon-remove"></i></span>
								<span class="add-on"><i class="icon-th"></i></span>
						</div>
						-->	
							<a href="#" id="btn_query">&nbsp;&nbsp;&nbsp;查询 </a>
						<div class="form-group">
							<label>可用教练</label> 
							<select  class="form-control" id="savalessonInstructor">
								  <option value="-1">请选择...</option>
								</select> 
						</div>
						<div class="form-group">
							<label>可用教室</label> 
							<select  class="form-control" id="savalessonRoom">
								  <option value="-1">请选择...</option>
								</select> 
						</div>
						
						<!--  
						<div class="form-group">
							<label>课程结束时间</label> <input type="datetime" class="form-control"
								id="savelessonEnd" placeholder="Readonly input here…"  readonly="">
						</div>		
						-->				
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" id="saveConfirmBtn">添加</button>
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
		
		
		$('.datetimepicker').datetimepicker({
			bootcssVer:3,
			startDate:"2019-06-01",
			language:  'zh-CN',
		    format: 'yyyy-mm-dd hh:ii:ss',
		    autoclose: true,
		    clearBtn: true,//清除按钮
            todayBtn: true,//今日按钮
            showDropdowns: true,
            autoUpdateInput: true,
		    minView: 0,
		    minuteStep:5
		});

	});

		//单个删除
	function del(id) {
		if (confirm("是否删除?")) {
			$.ajax({
				url : "deleteLessonById",
				data : {
					"number" : id
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
		$("select option:gt(0)").remove();
		//初始化用户信息
		$.ajax({
			url : 'queryCourse',
			type : "post",
			success : function(data, status) {
				$.each(data, function(index, courseList) {
		            $("#lessonNumber").append(  //此处向select中循环绑定数据
		    				"<option value="+courseList.number+">" + courseList.name+ "</option>");
		        });
				$("#lessonNumber option[value='"+id+"']").attr("selected","selected"); 
			}
		});
	}
	//查询点击事件
	$("#btn_query2").click(function() {
	//	$("#updateModal").modal('show');
		//初始化用户信息
		$.ajax({
			url : 'queryLessonIns',
			data : {
				'number' : $("#lessonNumber option:selected").val(), //已有课程类型ID
				'starttime':$("#starttime").val()
			},
			success : function(data) {
				$("#lessonInstructor").empty();
				$.each(data, function(index, instructorList) {
		            $("#lessonInstructor").append(  //此处向select中循环绑定数据
		    				"<option value="+instructorList.jobnumber+">" + instructorList.name+ "</option>");
		            console.log($("#lessonInstructor").val)
		        });

			}
		});	
		
		$.ajax({
			url : 'queryLessonRoom',
			data : {
				'number' : $("#lessonNumber option:selected").val(),
				'starttime':$("#starttime").val()
			},
			success : function(data) {
				$("#lessonRoom").empty();
				$.each(data, function(index, roomList) {
		            $("#lessonRoom").append(  //此处向select中循环绑定数据
		    				"<option value="+roomList.number+">" + roomList.roomname+ "</option>");
		        });
		
			}
		});	
	})
	
	//编辑模态框下确认按钮的点击事件
	$("#updateConfirmBtn").click(function() {
		$("#updateModal").modal('hide');
		$.ajax({
			url : "updateLesson",
			method : 'post',
		//	contentType:'application/json;charset=utf-8',
			data : {
				number : $('#lessonNumber option:selected').val(),
				jobnumber : $('#lessonInstructor option:selected').val(),
				room_number : $('#lessonRoom option:selected').val(),
				starttime : $("#starttime").val(),			
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
		//清除除第一个外的所有option
		$("select option:gt(0)").remove();
		$.ajax({
			url : 'queryCourse',
			type : "post",
			success : function(data, status) {
				$.each(data, function(index, courseList) {
		            $("#savalessonNumber").append(  //此处向select中循环绑定数据
		    				"<option value="+courseList.number+">" + courseList.name+ "</option>");
		        });
			}
		});
	});

	//查询点击事件
//	var queryId = 0;
	$("#btn_query").click(function() {
		$("#saveModal").modal('show');
		//初始化用户信息
		console.log($("#savestarttime").val());
		$.ajax({
			url : 'queryLessonIns',
			data : {
				'number' : $("#savalessonNumber option:selected").val(), //已有课程类型ID
				'starttime':$("#savestarttime").val()
			},
			success : function(data) {
				$("#savalessonInstructor").empty();
				$.each(data, function(index, instructorList) {
		            $("#savalessonInstructor").append(  //此处向select中循环绑定数据
		    				"<option value="+instructorList.jobnumber+">" + instructorList.name+ "</option>");
		            console.log($("#savalessonInstructor").val)
		        });

			}
		});	
		
		$.ajax({
			url : 'queryLessonRoom',
			data : {
				'number' : $("#savalessonNumber option:selected").val(),
				'starttime':$("#savestarttime").val()
			},
			success : function(data) {
				$("#savalessonRoom").empty();
				$.each(data, function(index, roomList) {
		            $("#savalessonRoom").append(  //此处向select中循环绑定数据
		    				"<option value="+roomList.number+">" + roomList.roomname+ "</option>");
		        });
		
			}
		});	
	})
	
	//添加用户模态框下的确认按钮点击事件
	$("#saveConfirmBtn").click(function() {
		$("#saveModal").modal('hide');
		$.ajax({
			url : "saveLesson",
			method : 'post',
			data : {
			//	id : idGlobal,
				number : $('#savalessonNumber option:selected').val(),
				jobnumber : $('#savalessonInstructor option:selected').val(),
				room_number : $('#savalessonRoom option:selected').val(),
				starttime : $("#savestarttime").val(),
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
						url : "getLessonInfo",
						clickToSelect : true,
						dataType : "json",
						pageSize : 10,
						pageList : [ 5, 10, 20 ],
						detailView: true,
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
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<button type="button" class="btn btn-default" onclick="edit(\''
													+ row.number + '\')">编辑</button> ';
										var d = '<button type="button" class="btn btn-danger" onclick="del(\''
												+ row.number + '\')">删除</button> ';
										return e + d;
									}

								} ],
						
								 onExpandRow: function (index, row, $detail) {
					                    InitSubTable(index, row, $detail);
					                }
					});
								InitSubTable = function (index, row, $detail) {
							        var parentid = row.id;
							        var cur_table = $detail.html('<table></table>').find('table');
							        $(cur_table).bootstrapTable({
							            url: 'getLessonStudentInfo',
							            method: 'get',
							          	queryParams: {parentid: parentid},
							            ajaxOptions: {parentid: parentid},
							            clickToSelect: true,
							           // detailView: true,//父子表
							            uniqueId: "MENU_ID",
							            async: false,
							            pageSize: 10,
							            pageList: [10, 25],
							            columns: [{
							                field: 'c_id',
							                title: 'Id'
							
							            }, {
							                field: 'name',
							                title: '姓名'
							            },
							            {
							                field: 'phone',
							                title: '电话'
							
							            }],
							            //无线循环取子表，直到子表里面没有记录
							            onExpandRow: function (index, row, $Subdetail) {
							                InitSubTable(index, row, $Subdetail);
							            }
							        });
							    };
							 
</script>
</html>

