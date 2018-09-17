<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<c:if test='${sessionScope.user == null}'>
	<c:set var="message" value="Please Login" scope="request"/>
	<jsp:forward page="index.jsp"></jsp:forward>
</c:if>
<head>
<title>Details</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- bootstrap-css -->
<link rel="stylesheet" href="css/bootstrap.css">
<!-- //bootstrap-css -->
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link href="css/font-awesome.css" rel="stylesheet"> 
<link href="css/customstyle.css" rel="stylesheet">

<link href="css/detailstyle.css" rel='stylesheet' type='text/css' />
<style>
</style>
</head>
<body class="dashboard-page">
	
	<jsp:include page="header.jsp" />
	
	<section class="wrapper scrollable">
	   <div class="rightside">
			
			<jsp:include page="dashboardContent.jsp" />
			  
			  <div class="table-title">
                <div class="row">
                    <div class="col-sm-8">
						<h4>All	 <b>Users</b></h4>
					</div>

					<div class="col-sm-4">
						<a href="register.jsp" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add User</span></a>						
					</div>
                </div>
            </div>
			Search here :
			<input type="text" id="myInput" class="center-block" onkeyup="myFunction()" placeholder="Search for users.." title="Type in a name">
			
			<div class="table-wrapper table-responsive">
				<table id="myTable" class="table table-bordered table-striped table-responsive-md text-center" style="width:100%; overflow:scroll; ">
				<thead>
	                    <tr>
	                        <th>Name</th>
							<th>Email</th>
							<th>Created Time</th>
	                        <th>Actions</th>
	                    </tr>
	                </thead>
	                <tbody>
	                	<c:forEach items="${requestScope.users}" var="userdetail">
	                    <tr>
	                        <td><c:out value="${userdetail.firstname }"></c:out><c:out value="${userdetail.lastname }"></c:out></td>
							<td><c:out value="${userdetail.email }"></c:out></td>
							<td><c:out value="${userdetail.created_time }"></c:out></td>
	                        <td>
								<a href="operation?action=get&&userid=<c:out value='${userdetail.user_id }' />" class="btn btn-lg label-warning hidden-xs">Edit</a>
								<a href="operation?action=get&&userid=<c:out value='${userdetail.user_id }' />" class="edit visible-xs" data-toggle="modal"><i class="glyphicon glyphicon-edit" title="Edit"></i></a>
						
								<c:if test='${sessionScope.user.user_id == 1}'>							
									<a href="operation?action=delete&&userid=<c:out value='${userdetail.user_id }'/>" type="Submit" class="btn btn-lg label-danger hidden-xs">Delete</a>
									<a href="operation?action=delete&&userid=<c:out value='${userdetail.user_id }'/>" class="delete visible-xs" data-toggle="modal"><i class="material-icons" title="Delete">&#xE872;</i></a>
								</c:if>
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
	  var input, filter, table, tr, td, i;
	  input = document.getElementById("myInput");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("myTable");
	  tr = table.getElementsByTagName("tr");
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[0];
		td1 = tr[i].getElementsByTagName("td")[1];
	    if (td || td1) {
	      if (td.innerHTML.toUpperCase().indexOf(filter) > -1 || td1.innerHTML.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
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