<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<head>
<title>Test</title>
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
<style>
#myInput {
  width: 100%;
  font-size: 16px;
  padding: 12px 20px 12px 40px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
}

#myTable {
  border-collapse: collapse;
  width: 100%;
  border: 1px solid #ddd;
  font-size: 12px;
}

#myTable th, #myTable td {
  text-align: left;
  padding: 8px;
}

#myTable tr {
  border-bottom: 1px solid #ddd;
}

#myTable tr.header, #myTable tr:hover {
  background-color: #f1f1f1;
}

</style>
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
	<section class="wrapper scrollable">
		<div class="rightside">
			<div class="table-responsive">
			Search here :
			<input type="text" id="myInput" class="center-block" onkeyup="myFunction()" placeholder="Search for users.." title="Type in a name">
			<table id="myTable" class="table table-responsive-md table-bordered table-striped text-center" >
				<thead>
						<th>UserId</th>
						<th>AddressLine1</th>
						<th>AddressLine2</th>
						<th>City</th>
						<th>State</th>
						<th>Country</th>
						<th>Pincode</th>
						<th>CreatedTime</th>
						<th>Action</th>
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
				<p>© 2018 . All Rights Reserved . Design by</p>
			</div>
			<!-- //footer -->
		</div>
	</section>
	
	<script src="js/jquery2.0.3.min.js"></script>
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
			