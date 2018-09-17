<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<c:if test='${sessionScope.user == null}'>
	<c:set var="message" value="Please Login" scope="request"/>
	<jsp:forward page="index.jsp"></jsp:forward>
</c:if>
<head>
<title>Address</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- bootstrap-css -->

<link rel="stylesheet" href="css/bootstrap.css">
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
			
			<div class="table-responsive">
			Search here :
			<input type="text" id="myInput" class="center-block" onkeyup="myFunction()" placeholder="Search for address.." title="Type in a name">
			<table id="myTable" class="table table-responsive-md table-bordered table-striped text-center" >
				<thead>
					<tr>
						<th>UserId</th>
						<th>AddressLine1</th>
						<th>AddressLine2</th>
						<th>City</th>
						<th>State</th>
						<th>Country</th>
						<th>Pincode</th>
						<th>CreatedTime</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.addresses}" var="addressdetail">
					<tr>
						<td><c:out value="${addressdetail.user_id }"></c:out></td>
						<td><c:out value="${addressdetail.address_line1 }"></c:out></td>
						<td><c:out value="${addressdetail.address_line2 }"></c:out></td>
						<td><c:out value="${addressdetail.city }"></c:out></td>
						<td><c:out value="${addressdetail.state }"></c:out></td>
						<td><c:out value="${addressdetail.country }"></c:out></td>
						<td><c:out value="${addressdetail.pincode }"></c:out></td>
						<td><c:out value="${addressdetail.created_time }"></c:out></td>
						<td style="text-align:center;">
							<a href="" class="btn btn-warning">Edit</a>
							<a href="" class="btn btn-warning">Delete</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
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
	<script src="js/bootstrap.js"></script>
	<script>
	function myFunction() {
		  var input, filter, table, tr, td, i, j;
		  input = document.getElementById("myInput");
		  filter = input.value.toUpperCase();
		  table = document.getElementById("myTable");
		  tr = table.getElementsByTagName("tr");
		  for (i = 0; i < tr.length; i++) {
		    for(j = 0; j < 9; j++ ){
				td = tr[i].getElementsByTagName("td")[j];
				if (td) {
			      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
			        tr[i].style.display = "";
			        break;
			      } else {
			        tr[i].style.display = "none";
			      }
			   } 
			}
		  }
		}
	</script>
	<!--validator js-->
	<script src="js/customvalidate.js"></script>
	<!--//validator js-->
	
	<script src="js/proton.js"></script>
</body>
</html>
