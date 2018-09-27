<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<c:if test='${sessionScope.user == null}'>
	<c:set var="message" value="Please Login" scope="request"/>
	<jsp:forward page="index.jsp"></jsp:forward>
</c:if>
<html>
<head>
<title>Dash Board</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body class="dashboard-page">
	<jsp:include page="header.jsp" />
	
	<section class="wrapper scrollable">
		<div class="rightside">
			<div class="text-center"><c:out value="${requestScope.message }"></c:out></div>
			<jsp:include page="dashboardContent.jsp" />
			
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

	<!--validator js-->
	<script src="js/customvalidate.js"></script>
	<!--//validator js-->

	<script src="js/proton.js"></script>
</body>
</html>
