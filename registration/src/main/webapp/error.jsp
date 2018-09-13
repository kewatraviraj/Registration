<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<head>
<title>Colored  an Admin Panel Category Flat Bootstrap Responsive Website Template | Error :: w3layouts</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- bootstrap-css -->
<link rel="stylesheet" href="css/bootstrap.css">
<!-- //bootstrap-css -->
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="css/font.css" type="text/css"/>
<link href="css/font-awesome.css" rel="stylesheet"> 
<link href="css/customstyle.css" rel="stylesheet">
<!-- //font-awesome icons -->
</head>
<body class="error-body">
	<nav class="navbar navbar-inverse navbar-fixed-top">
	  <div class="container">
		<div class="navbar-header">
		  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>                        
		  </button>
		  <a class="navbar-brand" href="#myPage">Logo</a>
		</div>
		
		<div class="collapse navbar-collapse" id="myNavbar">
		  <ul class="nav navbar-nav navbar-right">
			  <c:choose>
				<c:when test='${sessionScope.user ne null}'>
					<li style="padding-top:2%"><h3>Welcome <c:out value="${sessionScope.user.firstname }"></c:out></h3></li>
					<li style="padding-top:2%"><a href="logout" class="btn btn-primary">Logout</a></li>
				</c:when>
				<c:otherwise> 
					<li style="padding-top:2%"><a href="register.jsp" class="btn btn-primary">Sign Up</a></li>
					<li style="padding-top:2%"><a href="index.jsp" class="btn btn-primary">Login</a><li>
			     </c:otherwise>
			 </c:choose>
		   </ul> 
		</div>
	  </div>
	</nav>
		<div class="agile-signup">	
			
			<div class="error-page">
				<img src="images/error.png" alt="">
			</div>
			
			<div class="go-back">
				<a href="index.jsp">Back To Home</a>
			</div>
			
			<!-- footer -->
			<div class="copyright">
				<p>© 2016 Colored . All Rights Reserved . Design by <a href="http://w3layouts.com/">W3layouts</a></p>
			</div>
			<!-- //footer -->
		</div>
	<script src="js/proton.js"></script>
</body>
</html>
