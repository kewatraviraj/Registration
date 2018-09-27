<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<head>
<title>Register</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- bootstrap-css -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/datepicker.css"/>		
<!-- //bootstrap-css -->
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link href="css/font-awesome.css" rel="stylesheet"> 
<link href="css/customstyle.css" rel="stylesheet">

<SCRIPT type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</SCRIPT>
</head>

<body class="dashboard-page" onload="noBack();" onpageshow="if (event.persisted) noBack();"  onunload="">
	<jsp:include page="header.jsp" />
	
	<section class="wrapper scrollable">
		<div class="rightside">
			<div class="main-grid">
				<div class="agile-grids">	
					<!-- validation -->
					<div class="grids">
						<div class="progressbar-heading grids-heading text-center">
							<h3 id="notchanges"></h3>
						</div>
						<c:if test='${sessionScope.user != null }'>
						<div><a href="dashboard.jsp" class="btn btn-large btn-warning">Cancel</a></div>
						</c:if>
						<br>
						<div class="w3agile-validation w3ls-validation">
							<div class="panel panel-widget agile-validation">
								<div class="validation-grids widget-shadow" data-example-id="basic-forms">
									<div class="text-center"><h5 id="nochanges"></h5></div> 
									<div class="input-info">
										<h3>Fill the Details :</h3>
									</div>
									<div class="form-body form-body-info">
										<form data-toggle="validator" id="registration-form" novalidate="true" action="save" method="post" enctype="multipart/form-data" onsubmit="return validchange()">
											<c:if test='${requestScope.files == null }'>
											 	<div class="form-group register-image text-center">
													<img src="images/avatar_2x.png" class="avatar img-circle img-thumbnail" alt="avatar" accept="image/*"><br>
													<h6>Upload Image...*</h6><br>		
													<input type="file" name="file" class="text-center file-upload" value="" required="">
												</div>
											</c:if>
											
											<c:if test='${requestScope.user == null }'>
												<input type="hidden" class="form-control" value="insert" name="operation" required="">
											</c:if>
											<c:if test='${requestScope.user ne null }'>
												<input type="hidden" class="form-control" value="update" name="operation" required="">
												<input type="hidden" class="form-control" value="<c:out value='${requestScope.user.user_id }' />" name="user_id" required="">
											</c:if>
											
											 First Name* :
											<div class="form-group valid-form">
												<input type="text" value="<c:out value='${requestScope.user.firstname }' />" class="form-control" name="firstName" id="inputfirstName" placeholder="First name" required="">
											</div>
											Last Name* :
											<div class="form-group valid-form">
												<input type="text" value="<c:out value='${requestScope.user.lastname }' />" class="form-control" name="lastName" id="inputlastName" placeholder="Last name" required="">
											</div>
											Email* :
											<div class="form-group has-feedback">
												<input type="email" value="<c:out value='${requestScope.user.email }' />" class="form-control" name="email" id="inputEmail" placeholder="Email" data-error="Invalid email address" required="">
												<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
												<span class="help-block with-errors"></span>
											</div>
											Mobile No.* :
											<div class="form-group has-feedback">
												<input type="number" value="<c:out value='${requestScope.user.mobile_no }' />" data-toggle="validator" min="0" data-minlength="10" class="form-control" name="mobileNo" id="inputMobile" placeholder="Mobile No" data-error="Invalid mobile No" required="">
												<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
												<span class="help-block with-errors"></span>
											</div>
											
											<c:if test='${sessionScope.user == null || sessionScope.role_id == 1 && requestScope.user == null }'>
											Password* :
											<div class="form-group">
											  <input type="password" value="<c:out value='${requestScope.user.password }' />" data-toggle="validator" data-minlength="6" class="form-control" name="passWord" id="inputPassword" placeholder="Password" required="">
											  <span class="help-block">Minimum of 6 characters</span>
											</div>Confirm Password* :
											<div class="form-group">
											  <input type="password" value="<c:out value='${requestScope.user.password }' />" class="form-control" id="inputPasswordConfirm" data-match="#inputPassword" data-match-error="Whoops, these don't match" placeholder="Confirm password" required="">
											  <div class="help-block with-errors"></div>
											</div>
											</c:if>
											
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
												<input type="date" value="<c:out value='${requestScope.user.date_of_birth }' />" class="form-control" name="dateofBirth" id="inputDate" placeholder="MM/DD/YYY" required="">
											</div>
											
											<div class="form-group valid-form">
												<input type="hidden" class="form-control" name="deleteFlag" id="deleteFlag" value="no"  required="">
												<input type="hidden" class="form-control" name="deleteaddressIds" id="addressId"  required="">
											 </div>
											<!-- Address -->
											<div id="address" class="content" data-mfield-options='{"section": ".group","btnAdd":"#btnAdd-1","btnRemove":".btnRemove"}'>
												<div class="row">
													<div class="col-md-12"><button type="button" id="btnAdd-1" class="btn btn-primary">Add New Address</button></div>
												</div>
												<c:if test='${requestScope.addresses == null }'>
												<div class="row group address">
													<div class="form-group">
														<input  value="insertAddress" class="form-control" type="hidden" name="operationAddress" required="">
													</div>
													<div class="form-group col-md-6">
														Address line1* :<input class="form-control" type="text" name="address_line1" required="">
													</div>
													<div class="form-group col-md-6">
														AddressLine2* :<input class="form-control" type="text" name="address_line2" required="">
													</div>
													<div class="form-group col-md-3">
														City* :<input class="form-control" type="text" name="city" required="">
													</div>
													<div class="form-group col-md-3">
														State* :<input class="form-control" type="text" name="state" required="">
													</div>
													<div class="form-group col-md-3">
														Country* :<input class="form-control" type="text" name="country" required="">
													</div>
													<div class="form-group col-md-3">
														Pincode* :<input class="form-control" data-minlength="6" type="number" min="1" name="pincode" required="">
													</div>
													
													<div class="col-md-3">
														<button type="button" class="btn btn-danger btnRemove">Remove</button>
													</div>
												</div>
												</c:if>
												<c:if test='${requestScope.addresses ne null }'>
													<c:forEach items="${requestScope.addresses }" var="addressdetail">
													<div class="row group address">
														<div class="form-group">
															<input  value="<c:out value='${addressdetail.address_id }' />" class="form-control" type="hidden" name="address_id" required="">
														</div>
														<div class="form-group">
															<input  value="updateAddress" class="form-control" type="hidden" name="operationAddress" required="">
														</div>
														<div class="form-group col-md-6">
															Address line1* :<input  value="<c:out value='${addressdetail.address_line1 }' />" class="form-control" type="text" name="address_line1" required="">
														</div>
														<div class="form-group col-md-6">
															AddressLine2* :<input  value="<c:out value='${addressdetail.address_line2 }' />" class="form-control" type="text" name="address_line2" required="">
														</div>
														<div class="form-group col-md-3">
															City* :<input  value="<c:out value='${addressdetail.city }' />" class="form-control" type="text" name="city" required="">
														</div>
														<div class="form-group col-md-3">
															State* :<input  value="<c:out value='${addressdetail.state }' />" class="form-control" type="text" name="state" required="">
														</div>
														<div class="form-group col-md-3">
															Country* :<input  value="<c:out value='${addressdetail.country }' />" class="form-control" type="text" name="country" required="">
														</div>
														<div class="form-group col-md-3">
															Pincode* :<input  value="<c:out value='${addressdetail.pincode }' />" class="form-control" data-minlength="6" type="number" min="1" name="pincode" required="">
														</div>
														
														<div class="col-md-3">
															<button type="button" data-target="<c:out value='${addressdetail.address_id }' />" class="btn btn-danger btnRemove">Remove</button>
														</div>
													</div>
													</c:forEach>
												</c:if>
												
											</div>					
											<!--//Address -->
																						
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
												<button type="submit" class="btn btn-primary disabled">Submit</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- //validation -->
				</div>
			</div>
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
		<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
		<script src="js/bootstrap.js"></script>
	<!-- input-forms -->
		<script type="text/javascript" src="js/valida.2.1.6.min.js"></script>
		<script type="text/javascript" >
			$(document).ready(function() {
				 $('#inputDate').datepicker({
					 format: 'mm-dd-yyyy'
			        });
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
				
				if( "<c:out value='${requestScope.user.gender }' />" == "male"){
					$("#male").prop("checked", true);
				}else{
					$("#female").prop("checked", true);
				}
				
				var deleteaddressIds = [];
				$(".btnRemove").click(function(){
					if($(this).attr("data-target") !=null && $(this).attr("data-target") !=""){
						deleteaddressIds.push($(this).attr("data-target"));
						$("#deleteFlag").val("yes");
					}
					
					$("#addressId").val(deleteaddressIds.toString());
				});
				
				//image upload set
				var readURL = function(input) {
					if (input.files && input.files[0]) {
						var reader = new FileReader();
						reader.onload = function (e) {
							$('.avatar').attr('src', e.target.result);
						}
						reader.readAsDataURL(input.files[0]);
					}else{
						$('.avatar').attr('src','images/avatar_2x.png');
					}
				}
				$(".file-upload").on('change', function(){
					readURL(this); // set image url to show
				});				
			})
		</script>
		<!-- //input-forms -->
		<!--validator js-->
		<script src="js/validator.min.js"></script>
		<script src="js/customvalidate.js"></script>
		<!--//validator js-->
		
		<!-- jQuery Multifield -->
		<script src="js/jquery.multifield.min.js"></script>
		<script>
			$('#address').multifield({section: '.section',
					btnAdd:'.btnAdd',
					btnRemove:'.btnRemove'
			});
			
			$("#registration-form :input[type='text'], :input[type='email'], :input[type='number'], :input[type='radio'], :input[type='date']").change(function() {
				$("#registration-form").data("changed",true);
			});
			
			function validchange(){
				 if($("#registration-form").data("changed")){
					return true;
				}else{
					$("#nochanges").text("You haven't changed any Detail");
					window.location.hash = '#notchanges';
					return false;
				 } 
			}
		</script>

		<script src="js/proton.js"></script>
</body>
</html>
