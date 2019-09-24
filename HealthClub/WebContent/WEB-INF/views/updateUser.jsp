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
	  <title></title> 
	  <meta name="keywords" content="HealthClub后台管理系统" />
	  <meta name="description" content="HealthClub后台管理系统" />
	  <meta name="viewport" content="width=device-width, initial-scale=1.0">
	  <meta name="author" content="">
	  <!-- Stylesheets -->
	  <link href="jsp/style/bootstrap.css" rel="stylesheet">
	  <link href="jsp/style/font-awesome.css" rel="stylesheet" >
	  <link href="jsp/style/style.css" rel="stylesheet">
	  
	  <!-- HTML5 Support for IE -->
	  <!--[if lt IE 9]>
	  <script src="js/html5shim.js"></script>
	  <![endif]-->
	
	  <!-- Favicon -->
	  <link rel="shortcut icon" href="img/favicon/favicon.png">
	  

  </head>
  
  <body>
	    <!-- Form area -->
	<div class="admin-form">
	  <div class="container">
	
	    <div class="row">
	      <div class="col-md-12">
	        <!-- Widget starts -->
	            <div class="widget worange">
	              <!-- Widget head -->
	              <div class="widget-head">
	                <i class="icon-lock"></i> 修改密码
	              </div>
	
	              <div class="widget-content">
	                <div class="padd">
	                  <!-- Login form -->
	                  <form class="form-horizontal" method="post" action="updateUser">
	                    <!-- Email -->
	                    <div class="form-group">
	                      <label class="control-label col-lg-3" for="inputUserName">用户名</label>
	                      <div class="col-lg-9">
	                        <input type="text" class="form-control" id="inputUserName" placeholder="UserName" name="Username">
	                      </div>
	                    </div>
	                    <!-- Password -->
	                    <div class="form-group">
	                      <label class="control-label col-lg-3" for="inputPassword">密码</label>
	                      <div class="col-lg-9">
	                        <input type="password" class="form-control" id="inputPassword" placeholder="Password" name="Password">
	                      </div>
	                    </div>
	                    <!-- Remember me checkbox and sign in button -->
	                    <div class="form-group">
						<div class="col-lg-9 col-lg-offset-3">
	                      <div class="checkbox">
	                        <label>
	                          <input type="checkbox"> Remember me
	                        </label>
							</div>
						</div>
						</div>
	                        <div class="col-lg-9 col-lg-offset-2">
								<button type="submit" class="btn btn-danger">修改密码</button>
								<button type="reset" class="btn btn-default">Reset</button>
							</div>
	                    <br />
	                  </form>
					  
					</div>
	                </div>
	              
	                <div class="widget-foot">
	                  Not Registred? <a href="#">Register here</a>
	                </div>
	            </div>  
	      </div>
	    </div>
	  </div> 
	</div>
		
			
	
	<!-- JS -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
  </body>
</html>
