<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<c:if test='${sessionScope.user == null}'>
	<c:set var="message" value="Please Login" scope="request"/>
	<jsp:forward page="index.jsp"></jsp:forward>
</c:if>
<head>
<title>User Details</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- bootstrap-css -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<!-- //bootstrap-css -->
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link href="css/font-awesome.css" rel="stylesheet"> 
<link href="css/customstyle.css" rel="stylesheet">
<link href="css/detailstyle.css" rel='stylesheet' type='text/css' />

</head>
<body class="dashboard-page">
	
	<jsp:include page="header.jsp" />
	
	<section class="wrapper scrollable">
		<div class="rightside">
			
			<jsp:include page="dashboardContent.jsp" />
			
			<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >
				  <div class="panel panel-info" id="viewDetail">
					<div class="panel-heading">
					  <h3 class="panel-title"><c:out value="${requestScope.user.firstname }" /> <c:out value="${requestScope.user.lastname }" /></h3>
					</div>
					<div class="panel-body">
						<div class="row">
						
						<div class=" col-md-12 col-lg-12 "> 
						  <table class="table table-user-information" style="width:100%">
							<tbody>
							  <tr>
								<td>First Name:</td>
								<td><c:out value="${requestScope.user.firstname }" /></td>
							  </tr>
							  <tr>
								<td>Last Name:</td>
								<td><c:out value="${requestScope.user.lastname }" /></td>
							  </tr>
							  <tr>
								<td>Email</td>
								<td><c:out value="${requestScope.user.email }" /></td>
							  </tr>
							  <tr>
								<td>Phone Number</td>
								<td><c:out value="${requestScope.user.mobile_no }" /></td>   
							  </tr>
							  <tr>
								<td>Date of Birth</td>
								<td><c:out value="${requestScope.user.date_of_birth }" /></td>
							  </tr>
							   <tr>
								<td>Gender</td>
								<td><c:out value="${requestScope.user.gender }" /></td>
							  </tr>
							  <tr>
								<td>Created Time</td>
								<td><c:out value="${requestScope.user.created_time }" /></td>
							  </tr> 
							</tbody>
						  </table>
						  <table class="col-md-12 col-lg-12 text-center">
							<tr><td><a href="useraddress?userid=<c:out value='${requestScope.user.user_id }' />" type="submit" class="btn btn-warning">All Addresses</a>
									<a href="userfile?userid=<c:out value='${requestScope.user.user_id }' />" type="submit" class="btn btn-warning">All Files</a>
								</td>
							</tr>
						  </table>
						</div>
					  </div>
					</div>
					<div class="panel-footer">
						<button class="btn btn-lg label-warning hidden-xs edit">Edit</button>
						<button class="edit visible-xs" data-toggle="modal edit"><i class="glyphicon glyphicon-edit" title="Edit"></i></button>
						
						<c:if test='${sessionScope.user.role_id == 1 && requestScope.user.user_id != 1}'>
							<button class="btn btn-lg label-danger hidden-xs">Delete</button>
							<button class="visible-xs" data-toggle="modal"><i class="glyphicon glyphicon-remove"></i></button>
						</c:if>
					</div>
				  </div>
				  
				  <div class="validation-grids widget-shadow" data-example-id="basic-forms" id="editDetail"> 
						<div class="input-info">
							<h3>Fill the Details : </h3><a class="btn btn-sm label-warning" style="float:right; margin-bottom:1%;" id="cancel">Cancel</a>
						</div>
						<div class="form-body form-body-info">
							<form data-toggle="validator" id="" action="operation" method="post">
								<div class="form-group valid-form">
									<input type="hidden" class="form-control" name="action" id=""  value="update" required="">
									<input type="hidden" class="form-control" name="userid" id="inputid"  value="<c:out value='${requestScope.user.user_id }' />" required="">
								</div>
								 First Name* :
								<div class="form-group valid-form">
									<input type="text" class="form-control" name="firstName" id="inputfirstName" placeholder="First name" value="<c:out value='${requestScope.user.firstname }' />" required="">
								</div>
								Last Name* :
								<div class="form-group valid-form">
									<input type="text" class="form-control" name="lastName" id="inputlastName" placeholder="Last name" value="<c:out value='${requestScope.user.lastname }' />" required="">
								</div>
								Email* :
								<div class="form-group has-feedback">
									<input type="email" class="form-control" name="email" id="inputEmail" placeholder="Email" data-error="Invalid email address" value="<c:out value='${requestScope.user.email }' />" required="">
									<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
									<span class="help-block with-errors"></span>
								</div>
								Mobile No.* :
								<div class="form-group has-feedback">
									<input type="number" min="0" data-minlength="10" class="form-control" name="mobileNo" id="inputMobile" placeholder="Mobile No" data-error="Invalid mobile No" value="<c:out value='${requestScope.user.mobile_no }' />" required="">
									<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
									<span class="help-block with-errors"></span>
								</div>
								Gender* :
								<div class="form-group">
									<div class="radio">
										<label><input type="radio" name="gender" id="female" value="female" required="">Female</label>
									</div>
									<div class="radio">
										<label><input type="radio" name="gender" id="male" value="male" required="">Male</label>
									</div>
								</div>
								Date of Birth* :
								<div class="form-group">
									<input type="date" class="form-control" name="dateofBirth" id="inputDate" placeholder="Date Of Birth" value="<c:out value='${requestScope.user.date_of_birth }' />" required="">
								</div>													
								<div class="form-group">
									<div class="checkbox">
										<label>
											<input type="checkbox" id="terms" data-error="Before you wreck yourself" required="">
											I agree with all the details.
										</label>
										<div class="help-block with-errors"></div>
									</div>
								</div>
								<div class="form-group">
									<button type="submit" class="btn btn-primary disabled">Update</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div></div>
		
			<!-- footer -->
			<div class="footer">
				<p>© 2018. All Rights Reserved. Design by</p>
			</div>
			<!-- //footer -->
		</div>
	</section>
	
	
	<script src="js/jquery3.1.1.js"></script>
	<script src="js/modernizr.js"></script>
	<script src="js/jquery.cookie.js"></script>
	<script src="js/bootstrap.js"></script>
	<!-- input-forms -->
		<script type="text/javascript" src="js/valida.2.1.6.min.js"></script>
		<script type="text/javascript" >
			$(document).ready(function() {
				$("#editDetail").hide();
				$(".edit").click(function(){
					$("#viewDetail").hide();
					$("#editDetail").show();
				});
				$("#cancel").click(function(){
					$("#editDetail").hide();
					$("#viewDetail").show();
				});
				
				// show Valida's version.
				$('#version').valida( 'version' );

				// Exemple 1
				$('.valida').valida();
				
				if( "<c:out value='${requestScope.user.gender }' />" == "male"){
					$("#male").prop("checked", true);
				}else{
					$("#female").prop("checked", true);
				}
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

	