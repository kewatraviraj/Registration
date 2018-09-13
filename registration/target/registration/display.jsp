<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<title>Insert title here</title>
</head>
<body>
<c:if test="${sessionScope.user ne null }">
	<div style="width:100%"><h3>Welcome <c:out value="${sessionScope.user.firstname }"></c:out></h3><a href="logout">Logout</a></div>
</c:if>
	<table  align="center" width=70%>
		<tr><td>Users</td></tr>
		<tr>
			<td>User ID</td>
			<td>First Name</td>
			<td>Last Name</td>
			<td>email</td>
			<td>Mobile No</td>
			<td>Gender</td>
			<td>Date of Birth</td>
			<td>Created Time</td>
		</tr>
		<c:forEach items="${requestScope.users }" var="user">
			<tr>
				<td><c:out value="${user.user_id }"></c:out>
				<td><c:out value="${user.firstname }"></c:out>
				<td><c:out value="${user.lastname }"></c:out>
				<td><c:out value="${user.email }"></c:out>
				<td><c:out value="${user.mobile_no }"></c:out>	
				<td><c:out value="${user.gender }"></c:out>
				<td><c:out value="${user.date_of_birth }"></c:out>
				<td><c:out value="${user.created_time }"></c:out>
			</tr>
		</c:forEach>
		</table>
		<table align="center" width=70%>
		<tr><td>Address</td></tr>
		<tr>
			<td>User ID</td>
			<td>Address Line1</td>
			<td>Adress Line2</td>
			<td>City</td>
			<td>State</td>
			<td>Country</td>
			<td>Pincode</td>
			<td>Created Time</td>
		</tr>
		<c:forEach items="${requestScope.address}" var="address">
			<tr>
				<td><c:out value="${address.user_id }"></c:out>
				<td><c:out value="${address.address_line1 }"></c:out>
				<td><c:out value="${address.address_line2 }"></c:out>
				<td><c:out value="${address.city }"></c:out>
				<td><c:out value="${address.state }"></c:out>
				<td><c:out value="${address.country }"></c:out>		
				<td><c:out value="${address.pincode }"></c:out>
				<td><c:out value="${address.created_time }"></c:out>
			</tr>
		</c:forEach>
		</table>
		<table align="center" width=70%>
		<tr><td>Files</td></tr>
		<tr>
			<td>File ID</td>
			<td>File</td>
			<td>Created Time</td>
		</tr>
		<c:forEach items="${requestScope.files}" var="file">
			<tr>
				<td><c:out value="${file.file_id }"></c:out></td>
				<c:if test="${file.file_type == 'image' }">
					<td><img src="data:image/jpeg;base64,${file.filestring }" height:='100px'; width='100px'></td>
				</c:if>
				<td><c:out value="${file.created_time }"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	
	<c:out value="${requestScope.registermessage }"></c:out>
</body>
</html>