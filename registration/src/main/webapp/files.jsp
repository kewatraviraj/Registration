<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test='${sessionScope.user == null}'>
	<c:set var="message" value="Please Login" scope="request"/>
	<jsp:forward page="index.jsp"></jsp:forward>
</c:if>
<html>
<head>
<title>Files</title>
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
			
			<div class="table-wrapper table-responsive">
			<table id="myTable" class="table table-bordered table-striped table-responsive-md text-center">
				<thead>
					<tr>
						<th>FileId</th>
						<th>File</th>
						<th>Created Time</th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.files}" var="filedetail">
					<tr>
						<td><c:out value="${filedetail.file_id }"></c:out></td>
						<c:if test="${filedetail.file_type == 'image' }">
							<td><img src="data:image/jpeg;base64,${filedetail.filestring }" height:='100px'; width='100px'></td>
						</c:if>
						<td><c:out value="${filedetail.created_time }"></c:out></td>
						
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
	
		<script src="js/jquery3.1.1.min.js"></script>
		<script src="js/modernizr.js"></script>
		<script src="js/jquery.cookie.js"></script>
		<script src="js/bootstrap.js"></script>
		
		<!--validator js-->
		<script src="js/customvalidate.js"></script>
		<!--//validator js-->
		
		<script src="js/proton.js"></script>
</body>
</html>
			