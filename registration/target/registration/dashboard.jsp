<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<c:if test='${sessionScope.user == null}'>
	<c:set var="message" value="Please Login" scope="request"/>
	<jsp:forward page="index.jsp"></jsp:forward>
</c:if>
<html>
<head>
<title>Admin Panel</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body class="dashboard-page">
	<jsp:include page="header.jsp" />
	
	<section class="wrapper scrollable">
		
		<div class="rightside">
				<div class="row info leftside">
					<a href="operation?action=get&&userid=<c:out value='${sessionScope.user.user_id }' />">
						<div class="bg-info mb-2 text-white well well-lg col-sm-3 col-sm-offset-1">My Details</div>
					</a>
					<a href="useraddress?userid=<c:out value='${sessionScope.user.user_id }' />">
						<div class="bg-info mb-2 text-white well well-lg col-sm-3 col-sm-offset-1">My Address</div>
					</a>
					<a href="userfile?userid=<c:out value='${sessionScope.user.user_id }' />">
						<div class="bg-info mb-2 text-white well well-lg col-sm-3 col-sm-offset-1">My Files</div>
					</a>
				</div>
			<c:if test='${sessionScope.user.role_id == 1}'>
				<div class="social grid">
					<div class="grid-info">
						<div class="col-md-3 top-comment-grid">
							<a href="save?action=users">
							<div class="comments likes">	
								<div class="comments-info likes-info">
									<h3>Users</h3>	
								</div>
								<div class="clearfix"> </div>
							</div>
							</a>
						</div>
						<div class="col-md-3 top-comment-grid">
							<a href="save?action=addresses">
							<div class="comments">
								<div class="comments-info">
									<h3>Addresses</h3>
								</div>
								<div class="clearfix"> </div>
							</div>
							</a>
						</div>
						<div class="col-md-3 top-comment-grid">
							<a href="save?action=files">
							<div class="comments tweets">
								<div class="comments-info tweets-info">
									<h3>Files</h3>
								</div>
								<div class="clearfix"> </div>
							</div>
							</a>
						</div>
						<div class="clearfix"> </div>
					</div>
				</div>	
			</c:if>
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
			});
		</script>
		<!-- //input-forms -->
		<!--validator js-->
		<script src="js/validator.min.js"></script>
		<script src="js/customvalidate.js"></script>
		<!--//validator js-->

		<script src="js/proton.js"></script>
</body>
</html>
