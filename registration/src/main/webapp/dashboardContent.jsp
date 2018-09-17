<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="row info leftside">
		<a href="operation?action=get&&userid=<c:out value='${sessionScope.user.user_id }' />">
			<div class="bg-info mb-2 text-white well well-lg col-sm-3 col-sm-offset-1">My Details</div>
		</a>
		<a href="useraddress?userid=<c:out value='${sessionScope.user.user_id }' />">
			<div class="bg-info mb-2 text-white well well-lg col-sm-3 col-sm-offset-1">My Address</div>
		</a>
		<a href="userfile?action=get&&userid=<c:out value='${sessionScope.user.user_id }' />">
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
			<div class="col-md-3 top-comment-grid">
				<a href="">
				<div class="comments views">
					<div class="comments-info views-info">
						<h3></h3>
					</div>
					<div class="clearfix"> </div>
				</div>
				</a>
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>	
	</c:if>
