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
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title text-center">统计数据</h3>
		</div>
		<div class="panel-body">
			<div id="toolbar" class="btn-group">
				<div class="form-inline">
					
					<input type="datetime" value="键入时间" 
							name="start_time" id="starttime" class="datetimepicker" readonly>
					<input type="datetime" value="键入时间" 
							name="end_time" id="endtime" class="datetimepicker" readonly>
					<select class="form-control" id="selectForm" >
						<option value="-1">选择...</option>
						<option value="1">教练</option>
						<option value="2">课程</option>
					</select>
					<select  class="form-control" id="search">
							<option value="-1">请选择...</option>
					</select> 
					<button class="btn btn-info" id="searchBtn" >查询</button>
					
										
				</div>
			</div>

		<div id="chartmain" style="width:600px; height: 400px;"></div>
		</div>
	</div>

</body>

	<script type="text/javascript">	
	$(document).ready(function() {
		$('.datetimepicker').datetimepicker({
			bootcssVer:3,
			language:'zh-CN',
			startDate:"2019-01",
			format: 'yyyy-mm',
	        autoclose: true,
	        todayBtn: true,
	        startView: 'year',
	        minView:'year',
	        maxView:'decade',
		});
		

	})
	
	$("#selectForm").on('change',function(){
	//	alert("删除成功！");
	//判断是否选取prompt属性，无返回值；
	    if($("#selectForm option:selected").val() == 1){
	    	 $.ajax({
	     			url : "getInsInfo",
	     			data : {
	     				//type : $("#selectForm option:selected").val(),
	     			},
	     			success : function(data) {
	     				$('#search option').not(":first").remove();
	    				$.each(data, function(index, instructorList) {
	    		            $("#search").append(  //此处向select中循环绑定数据
	    		    				"<option value="+instructorList.jobnumber+">" + instructorList.name+ "</option>");
	    		        });
	     			}
	     		});
	    	
	    }else if($("#selectForm option:selected").val() == 2){
	    	 $.ajax({
	     			url : "getCouInfo",
	     			data : {
	     				//type : $("#selectForm option:selected").val(),
	     			},
	     			success : function(data) {
	     				$('#search option').not(":first").remove();
	    				$.each(data, function(index, courseList) {
	    		            $("#search").append(  //此处向select中循环绑定数据
	    		    				"<option value="+courseList.number+">" + courseList.name+ "</option>");
	    		        });
	     			}
	     		});
	    }
	    else{
	    	$('#search option').not(":first").remove();
	    }
	})
	
	
	$("#searchBtn").click(function() {
		console.log($("#endtime").val())
		//初始化用户信息
		$.ajax({
			url : 'getChartInfo',
			dataType: 'json',
			data : {
				'starttime':$("#starttime").val(),
				'endtime':$("#endtime").val(),
				'texttype' : $("#selectForm option:selected").val(), //选中的
				'search':$("#search option:selected").val()	
			},
			success : function(data) {
				
			    // 指定图表的配置项和数据
			    var option = {
			        title: {
			            text: '统计表'
			        },
			        tooltip: {},
			        legend: {
			            data:['销量']
			        },
			        xAxis: {
			            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
			        },
			        yAxis: {},
			        series: [{
			            name: '收入',
			            type: 'bar',
			            data: [5, 20, 36, 10, 10, 20]
			        }]
			    };
				
				console.log(data);
				var myChart1 = echarts.init(document.getElementById('chartmain'));
				if($("#selectForm option:selected").val() == -1){	
					myChart1.setOption(option);
				//	console.log($("#selectForm option:selected").val());
					 myChart1.setOption({        //加载数据图表
	                     xAxis: {
	                         data:data.monthList
	                     },
	                     legend: {
	 			            data:['收入']
	 			        },
	                     series: [{
	                         // 根据名字对应到相应的系列
	                         name: '收入',
	                         type: 'bar',
	                         data:data.amountList
	                     }]
	                 });
					
				}
				else if($("#selectForm option:selected").val() == 1){
					myChart1.setOption(option);	
					myChart1.setOption({        //加载数据图表
	                     xAxis: {
	                         data:data.monthList
	                     },
	                     legend: {
	 			            data:['预订人次']
	 			        },
	                     series: [{
	                         // 根据名字对应到相应的系列
	                         name: '预订人次',
	                         type: 'line',
	                         data:data.amountList
	                     }]
	                });	 
			
				}
				else if($("#selectForm option:selected").val() == 2){
					myChart1.setOption(option);	
					myChart1.setOption({        //加载数据图表
	                     xAxis: {
	                         data:data.monthList
	                     },
	                     legend: {
	 			            data:['排课节数','预定人数']
	 			        },
	                     series: [{
	                         // 根据名字对应到相应的系列
	                         name: '排课节数',
	                         type: 'line',
	                         data:data.amountList1
	                     },
	                     {
	                         // 根据名字对应到相应的系列
	                         name: '预定人数',
	                         type: 'line',
	                         data:data.amountList
	                     }]
	                });	 
			
				}
				  
			}//success : function(data) end
		});	
	})

	$(function(){
		

	    // 使用刚指定的配置项和数据显示图表。
	 //   myChart.setOption(option);
	});


	</script>


</html>