<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
  <head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  <meta charset="utf-8">
	  <!-- Title and other stuffs -->
	  <title>Bootstrap响应式后台管理系统</title> 
	  <meta name="keywords" content="HealthClub后台管理系统" />
	  <meta name="description" content="HealthClub后台管理系统" />
	  <meta name="viewport" content="width=device-width, initial-scale=1.0">
	  <meta name="author" content="">
	  <!-- Stylesheets -->
	  <link href="jsp/style/bootstrap.css" rel="stylesheet">
	  <!-- Font awesome icon -->
	  <link rel="stylesheet" href="jsp/style/font-awesome.css"> 
	  <!-- jQuery UI -->
	  <link rel="stylesheet" href="jsp/style/jquery-ui.css"> 
	  <!-- Calendar -->
	  <link rel="stylesheet" href="jsp/style/fullcalendar.css">
	  <!-- CLEditor -->
	  <link rel="stylesheet" href="jsp/style/jquery.cleditor.css"> 
	  <!-- Bootstrap toggle -->
	  <link rel="stylesheet" href="jsp/style/bootstrap-switch.css">
	  <!-- Main stylesheet -->
	  <link href="jsp/style/style.css" rel="stylesheet">
	  <!-- Widgets stylesheet -->
	  <link href="jsp/style/widgets.css" rel="stylesheet">   
	  
	  <!-- HTML5 Support for IE -->
	  <!--[if lt IE 9]>
	  <script src="js/html5shim.js"></script>
	  <![endif]-->
	
	  <!-- Favicon -->
	  <link rel="shortcut icon" href="img/favicon/favicon.png">
  </head>
  
  <body>

<div class="navbar navbar-fixed-top bs-docs-nav" role="banner">
  
    <div class="conjtainer">
      <!-- Menu button for smallar screens -->
      <div class="navbar-header">
		  <button class="navbar-toggle btn-navbar" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
			<span>菜单</span>
		  </button>
		  <!-- Site name for smallar screens -->
		  <a href="index.html" class="navbar-brand hidden-lg">首页</a>
		</div>
      
      

      <!-- Navigation starts -->
      <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">         

        <ul class="nav navbar-nav">  

          <!-- Upload to server link. Class "dropdown-big" creates big dropdown -->
          <li class="dropdown dropdown-big">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="label label-success"><i class="icon-cloud-upload"></i></span> 上传到云服务器</a>
            <!-- Dropdown -->
            <ul class="dropdown-menu">
              <li>
                <!-- Progress bar -->
                <p>图片上传进度</p>
                <!-- Bootstrap progress bar -->
                <div class="progress progress-striped active">
					<div class="progress-bar progress-bar-info"  role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
						<span class="sr-only">完成40%</span>
					</div>
			    </div>

                <hr />

                <!-- Progress bar -->
                <p>视频上传进度</p>
                <!-- Bootstrap progress bar -->
                <div class="progress progress-striped active">
					<div class="progress-bar progress-bar-success"  role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
						<span class="sr-only">完成80%</span>
					</div>
			    </div> 

                <hr />             

                <!-- Dropdown menu footer -->
                <div class="drop-foot">
                  <a href="#">查看所有</a>
                </div>

              </li>
            </ul>
          </li>

          <!-- Sync to server link -->
          <li class="dropdown dropdown-big">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="label label-danger"><i class="icon-refresh"></i></span> 同步到服务器</a>
            <!-- Dropdown -->
            <ul class="dropdown-menu">
              <li>
                <!-- Using "icon-spin" class to rotate icon. -->
                <p><span class="label label-info"><i class="icon-cloud"></i></span>同步会员到服务器</p>
                <hr />
                <p><span class="label label-warning"><i class="icon-cloud"></i></span>同步书签到云端</p>

                <hr />

                <!-- Dropdown menu footer -->
                <div class="drop-foot">
                  <a href="#">查看所有</a>
                </div>

              </li>
            </ul>
          </li>

        </ul>

        <!-- Search form -->
        <form class="navbar-form navbar-left" role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
		</form>
        <!-- Links -->
        <ul class="nav navbar-nav pull-right">
          <li class="dropdown pull-right">            
            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
              <i class="icon-user"></i> 管理员 <b class="caret"></b>              
            </a>
            
            <!-- Dropdown menu -->
            <ul class="dropdown-menu">
              <li><a href="update"><i class="icon-user"></i> 修改密码</a></li>
              <li><a href="login"><i class="icon-off"></i> 退出</a></li>
            </ul>
          </li>
          
        </ul>
      </nav>

    </div>
  </div>


<!-- Header starts -->
  <header>
    <div class="container">
      <div class="row">

        <!-- Logo section -->
        <div class="col-md-4 col-md-offset-4">
          <!-- Logo. -->
          <div class="logo">
            <h1><a href="#">HealthClub<span class="bold"></span></a></h1>
            <p class="meta">后台管理系统</p>
          </div>
          <!-- Logo ends -->
        </div>

        <!-- Button section -->
        <!-- Data section -->

        <div class="col-md-4">
          <div class="header-data">

            <!-- Traffic data -->
            <div class="hdata">
              <div class="mcol-left">
                <!-- Icon with red background -->
                <i class="icon-signal bred"></i> 
              </div>
              <div class="mcol-right">
                <!-- Number of visitors -->
                <p><a href="#">7000</a> <em>访问</em></p>
              </div>
              <div class="clearfix"></div>
            </div>

            <!-- Members data -->
            <div class="hdata">
              <div class="mcol-left">
                <!-- Icon with blue background -->
                <i class="icon-user bblue"></i> 
              </div>
              <div class="mcol-right">
                <!-- Number of visitors -->
                <p><a href="#">3000</a> <em>用户</em></p>
              </div>
              <div class="clearfix"></div>
            </div>

            <!-- revenue data -->
            <div class="hdata">
              <div class="mcol-left">
                <!-- Icon with green background -->
                <i class="icon-money bgreen"></i> 
              </div>
              <div class="mcol-right">
                <!-- Number of visitors -->
                <p><a href="#">5000</a><em>订单</em></p>
              </div>
              <div class="clearfix"></div>
            </div>                        

          </div>
        </div>

      </div>
    </div>
  </header>

<!-- Header ends -->

<!-- Main content starts -->

<div class="content">

  	<!-- Sidebar -->
    <div class="sidebar">
        <div class="sidebar-dropdown"><a href="#">导航</a></div>

        <!--- Sidebar navigation -->
        <!-- If the main navigation has sub navigation, then add the class "has_sub" to "li" of main navigation. -->
        <ul id="nav">
          <!-- Main menu with font awesome icon -->
          <li> <!-- Sub menu markup 
            <ul>
              <li><a href="#">Submenu #1</a></li>
              <li><a href="#">Submenu #2</a></li>
              <li><a href="#">Submenu #3</a></li>
            </ul>-->
          </li>
          <li><a href="#" onclick="menuClick('${base}toindex')"><i class="icon-home"></i>首页</a></li>
          <li class="has_sub"><a href="#"><i class="icon-user"></i> 信息管理 <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
              <li><a href="#" onclick="menuClick('${base}toCustomerList')"><i class="glyphicon glyphicon-th-list"></i>客户管理</a></li>
			  <li><a href="#" onclick="menuClick('${base}toInstructorList')"><i class="glyphicon glyphicon-th-list"></i>教练管理</a></li>
              <li><a href="#" onclick="menuClick('${base}toCourseList')"><i class="glyphicon glyphicon-th-list"></i>课程类型管理</a></li>
			  <li><a href="#" onclick="menuClick('${base}toRoomList')"><i class="glyphicon glyphicon-th-list"></i>教室管理</a></li>
			  <li><a href="#" onclick="menuClick('${base}toRoomtypeList')"><i class="glyphicon glyphicon-th-list"></i>教室类型管理</a></li>
			  <li><a href="#" onclick="menuClick('${base}toProfessionRankList')"><i class="glyphicon glyphicon-th-list"></i>专职管理</a></li>
			  <li><a href="#" onclick="menuClick('${base}toCardTypeList')"><i class="glyphicon glyphicon-th-list"></i>会员卡管理</a></li>
            </ul>
          </li>  
          <li class="has_sub"><a href="#"><i class="icon-usd"></i> 消费系统 <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
              <li><a href="#" onclick="menuClick('${base}toRecharge')"><i class="glyphicon glyphicon-th-list"></i>充值</a></li>
              <li><a href="#" onclick="menuClick('${base}toPurchaseCard')"><i class="glyphicon glyphicon-credit-card"></i>购卡</a></li>
			  <li><a href="#" onclick="menuClick('${base}toReserveLesson')"><i class="glyphicon glyphicon-th-list"></i>课程预定</a></li>
   
            </ul>
          </li> 
          <li class="has_sub"><a href="#"><i class="icon-search"></i> 查询系统  <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
              <li><a href="#" onclick="menuClick('${base}toRecord')"><i class="glyphicon glyphicon-th-list"></i>充消查询</a></li>
            </ul>
          </li>  
          <li><a href="#" onclick="menuClick('${base}toSchedulLesson')"><i class="icon-tasks"></i>排课管理</a></li>                           
          <li><a href="#" onclick="menuClick('${base}toWage')"><i class="icon-money"></i>工资结算</a></li> 
          <li><a href="#" onclick="menuClick('${base}toChart')"><i class="icon-bar-chart"></i>统计分析</a></li>
        </ul>
    </div>

    <!-- Sidebar ends -->

  	  	<!-- Main bar -->
  	<div class="mainbar">
      
	   <div>
				<iframe id="iframe-page-content" src="${base}jsp/myindex.jsp" width="100%"  frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
			</div>				
		</div><!-- /.main-content -->
		</div><!-- /.main-container -->
	</div>
</div>
	    <!-- Page heading ends -->



	    <!-- Matter -->

	    <div class="matter"> </div>

		<!-- Matter ends -->

    </div>

   <!-- Mainbar ends -->
   <div class="clearfix"></div>

</div>
<!-- Content ends -->

<!-- Footer starts -->
<footer>
  <div class="container">
    <div class="row">
      <div class="col-md-12">
            <!-- Copyright info -->
            <p class="copy">Copyright &copy; 2012 | <a href="#">Your Site</a> </p>
      </div>
    </div>
  </div>
</footer> 	

<!-- Footer ends -->

<!-- Scroll to top -->
<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span> 

<!-- JS -->
<script src="jsp/js/jquery.js"></script> <!-- jQuery -->
<script src="jsp/js/bootstrap.js"></script> <!-- Bootstrap -->
<script src="jsp/js/jquery-ui-1.9.2.custom.min.js"></script> <!-- jQuery UI -->
<script src="jsp/js/fullcalendar.min.js"></script> <!-- Full Google Calendar - Calendar -->
<script src="jsp/js/jquery.rateit.min.js"></script> <!-- RateIt - Star rating -->
<script src="jsp/js/jquery.prettyPhoto.js"></script> <!-- prettyPhoto -->

<!-- jQuery -->
<script src="jsp/js/excanvas.min.js"></script>
<script src="jsp/js/jquery.flot.js"></script>
<script src="jsp/js/jquery.flot.resize.js"></script>
<script src="jsp/js/jquery.flot.pie.js"></script>
<script src="jsp/js/jquery.flot.stack.js"></script>

<!-- jQuery Notification - Noty -->
<script src="jsp/js/jquery.noty.js"></script> <!-- jQuery Notify -->
<script src="jsp/js/themes/default.js"></script> <!-- jQuery Notify -->
<script src="jsp/js/layouts/bottom.js"></script> <!-- jQuery Notify -->
<script src="jsp/js/layouts/topRight.js"></script> <!-- jQuery Notify -->
<script src="jsp/js/layouts/top.js"></script> <!-- jQuery Notify -->
<!-- jQuery Notification ends -->

<script src="jsp/js/sparklines.js"></script> <!-- Sparklines -->
<script src="jsp/js/jquery.cleditor.min.js"></script> <!-- CLEditor -->
<script src="jsp/js/bootstrap-datetimepicker.min.js"></script> <!-- Date picker -->
<script src="jsp/js/bootstrap-switch.min.js"></script> <!-- Bootstrap Toggle -->
<script src="jsp/js/filter.js"></script> <!-- Filter for support page -->
<script src="jsp/js/custom.js"></script> <!-- Custom codes -->

<!-- Script for this page -->
<script type="text/javascript">
			$(function() {
				var height=document.documentElement.clientHeight;
				document.getElementById('iframe-page-content').style.height=height+'px';
			});
			
			var menuClick = function(menuUrl) {
				$("#iframe-page-content").attr('src',menuUrl);
			};
		</script>


</body>
</html>
