<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<head>
<title>Login</title>
<META Http-Equiv="Cache-Control" Content="no-cache">
<META Http-Equiv="Pragma" Content="no-cache">
<META Http-Equiv="Expires" Content="0">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- bootstrap-css -->
<link rel="stylesheet" href="css/bootstrap.css">
<!-- //bootstrap-css -->
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link href="css/font-awesome.css" rel="stylesheet"> 
<link href="css/customstyle.css" rel="stylesheet">
<style>
.pass-forgot{
	float:right	;
}
</style>
<!-- //font-awesome icons -->
<script src="js/jquery2.0.3.min.js"></script>
<script src="js/modernizr.js"></script>
<script src="js/jquery.cookie.js"></script>
</head>

<body class="dashboard-page">
	<jsp:include page="header.jsp" />
	<section class="wrapper scrollable">
		<div class="rightside">
			
			<div class="panel panel-widget login-div agile-validation">
				<div class="validation-grids validation-grids-right">
					<div class="widget-shadow login-form-shadow" data-example-id="basic-forms"> 
						<div class="text-center"><c:out value="${requestScope.message }"></c:out></div>
						<div class="input-info">
							<h3>Sign In:</h3>
						</div>
						<div class="form-body form-body-info">
							<form data-toggle="validator" novalidate="true" action="login" method="post">
								<div class="form-group has-feedback">
									<input type="email" class="form-control" name="userName" id="inputEmail" placeholder="Enter Your Email" data-error="Bruh, that email address is invalid" required="">
									<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
								</div>
								<div class="form-group">
									<input type="password" data-toggle="validator" data-minlength="6" class="form-control" name="passWord" id="inputPassword" placeholder="Password" required="">
								</div>
								<div class="bottom">
									<div class="form-group">
										<a href="forgot.jsp" class="pass-forgot">Forgot your password?</a>
									</div>
									<div class="form-group">
										<button type="submit" class="btn btn-primary disabled">Login</button><br>
										<p class="message">Not registered? <a href="register.jsp">Create an account</a></p>
									</div>
									<div class="clearfix"> </div>
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
