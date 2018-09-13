<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<title>jQuery Multifield Example</title>

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

	<style type="text/css">
		body{background-color: #ECF0F1;}
		.main-content{background: #fff;padding-top:40px;padding-bottom: 40px;}
		div.content div.border{border:1px solid black}
		.row{margin:10px 0px;}
		.footer{margin-top:40px;padding-top:20px;text-align: center;border-top:1px solid #BDC3C7;}
	</style>
</head>
<body>
	<h2>welcome to Register</h2>
	<a href="get">Get All</a>
	<form action="save" method="post" enctype="multipart/form-data">
		<select name="role">
			<option value="1">Admin</option>
			<option value="2">User</option>
		</select> <br> Enter First Name :<input type="text" name="firstName"
			id="firstName"><br> Enter Last Name :<input type="text"
			name="lastName" id="lastName"><br> Enter Email :<input
			type="email" name="email" id="email"><br> Enter Mobile
		No :<input type="text" name="mobileNo" id="mobileNo"><br>
		Enter Password :<input type="password" name="password" id="password"><br>

		Select Gender:<br> <input type="radio" value="male" name="gender">Male
		<input type="radio" value="female" name="gender">Female <br>
		Enter Date of Birth :<input type="date" name="dateOfBirth"
			id="dateOfBirth"><br>
		
		
	<div id="address" class="content" data-mfield-options='{"section": ".group","btnAdd":"#btnAdd-1","btnRemove":".btnRemove"}'>
		<div class="row">
			<div class="col-md-12"><button type="button" id="btnAdd-1" class="btn btn-primary">Add section</button></div>
		</div>
		<div class="row group border">
			<div class="col-md-8">
				Address line1<input class="form-control" type="text" name="address_line1">
			</div>
			<div class="col-md-8">
				AddressLine2<input class="form-control" type="text" name="address_line2">
			</div>
			<div class="col-md-8">
				City<input class="form-control" type="text" name="city">
			</div>
			<div class="col-md-8">
				State<input class="form-control" type="text" name="state">
			</div>
			<div class="col-md-8">
				Country<input class="form-control" type="text" name="country">
			</div>
			<div class="col-md-8">
				Pincode<input class="form-control" type="text"  name="pincode">
			</div>
			
			<div class="col-md-3">
				<button type="button" class="btn btn-danger btnRemove">Remove</button>
			</div>
		</div>
	</div>
		
		<table>
			<tr><th colspan="2">Address</th></tr>
			<tr>
				<td>AddressLine1</td><td><input type="text" name="address_line1" id="address_line1"></td>
			</tr>
			<tr>
				<td>AddressLine2</td><td><input type="text" name="address_line2" id="address_line2"></td>
			</tr>
			
			<tr>
				<td>City</td><td><input type="text" name="city" id="city"></td>
			</tr>
			<tr>
				<td>State</td><td><input type="text" name="state" id="state"></td>
			</tr>
			<tr>
				<td>Country</td><td><input type="text" name="country" id="country"></td>
			</tr>
			<tr>
				<td>Pincode</td><td><input type="number" name="pincode" id="pincode"></td>
			</tr>
		</table><br><br>
		
		<table>
			<tr><th colspan="2">Files</th></tr>
			<tr>
				<td>FileType</td>
				<td><select name="file_type">
						<option value="image">Image</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>File</td><td><input type="file" name="file"></td>
			</tr>
		</table>

			<input type="submit" value="submit"><br>
	</form>
	
	
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- jQuery Multifield -->
<script src="js/jquery.multifield.min.js"></script>
<script>
	$('#address').multifield({section: '.section',
			btnAdd:'.btnAdd',
			btnRemove:'.btnRemove'
	});
</script>

<script async defer id="github-bjs" src="https://buttons.github.io/buttons.js"></script>
</body>
</html>
