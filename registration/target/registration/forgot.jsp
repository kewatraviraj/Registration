<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<head>
<title>Forgot</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- bootstrap-css -->
<link rel="stylesheet" href="css/bootstrap.css">
<!-- //bootstrap-css -->
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link href="css/font-awesome.css" rel="stylesheet"> 
<link href="css/customstyle.css" rel="stylesheet">

</head>

<body class="dashboard-page">
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
			<li><a href="#"></a></li>
			<li style="padding-top:2%"><a href="register.jsp" class="btn btn-primary">Sign Up</a></li>
			<li style="padding-top:2%"><a href="index.jsp" class="btn btn-primary">Login</a><li>
		  </ul> 
		</div>
	  </div>
	</nav>
	<section class="wrapper scrollable">
		<div class="rightside">
			
			<div class="panel panel-widget login-div agile-validation">
				<div class="text-center"><c:out value="${requestScope.message }" /><c:out value="${requestScope.password }" /></div>
				<div class="validation-grids validation-grids-right">
					<div class="widget-shadow login-form-shadow" data-example-id="basic-forms"> 
						<div class="input-info">
							<h3>Enter Details:</h3>
						</div>
						
						<div class="form-body form-body-info">
							<form data-toggle="validator" id="registration-form" novalidate="true" action="forgot" method="post">
								Email* :
								<div class="form-group has-feedback">
									<input type="email" class="form-control" name="email" id="inputEmail" placeholder="Email" data-error="Invalid email address" required="">
									<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
									<span class="help-block with-errors"></span>
								</div>
								Mobile No.* :
								<div class="form-group has-feedback">
									<input type="number" data-toggle="validator" min="0" data-minlength="10" class="form-control" name="mobileNo" id="inputMobile" placeholder="Mobile No" data-error="Invalid mobile No" required="">
									<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
									<span class="help-block with-errors"></span>
								</div>
								<div class="form-group">
									<button type="submit" class="btn btn-primary disabled">Submit</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				</div>
			<!-- footer -->
			<div class="footer">
				<p>© 2018 . All Rights Reserved . Design by</p>
			</div>
			<!-- //footer -->
		</div>
	</section>
	
	<script src="js/jquery2.0.3.min.js"></script>
	<script src="js/modernizr.js"></script>
	<script src="js/jquery.cookie.js"></script>
	<script src="js/bootstrap.js"></script>
	<!-- input-forms -->
		<script type="text/javascript" src="js/valida.2.1.6.min.js"></script>
		<script type="text/javascript" >
			$(document).ready(function() {

				// show Valida's version.
				$('#version').valida( 'version' );

				// Exemple 1
				$('.valida').valida();
				
				// setup the partial validation
				$('#partial-1').on('click', function( ev ) {
					ev.preventDefault();
					$('#res-1').click(); // clear form error msgs
					$('form').valida('partial', '#field-1'); // validate only field-1
					$('form').valida('partial', '#field-1-3'); // validate only field-1-3
				});			
			})
		</script>
		<!-- //input-forms -->
		<!--validator js-->
		<script src="js/validator.min.js"></script>
		<script src="js/customvalidate.js"></script>
		<!--//validator js-->

		<script src="js/proton.js"></script>
</body>
</html>
