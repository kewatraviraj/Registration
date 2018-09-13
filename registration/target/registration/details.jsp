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
#myInput {
  width: 100%;
  font-size: 16px;
  padding: 12px 20px 12px 40px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
}

#myTable {
  border-collapse: collapse;
  width: auto;
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
								<a href="operation?action=get&&userid=<c:out value='${userdetail.user_id }' />" class="btn btn-lg label-info hidden-xs">View</a>	
								<a href="operation?action=delete&&userid=<c:out value='${userdetail.user_id }'/>" type="Submit" class="btn btn-lg label-danger hidden-xs">Delete</a>
								<a href="operation?action=get&&userid=<c:out value='${userdetail.user_id }' />" class="view visible-xs" data-toggle="modal"><i class="material-icons" title="View">&#xE417;</i></a>
								
								<c:if test='${sessionScope.user.user_id == 1}'>
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