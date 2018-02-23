<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="style.css" />
<link type="text/css" rel="stylesheet" href="bootstrap.min.css" />
<title>Register</title>
</head>
<body>
<div id="main-page">
	<div id="content" class="rounded">
		<form method="post" action="<c:url value = "register"/>">
			<fieldset>
			
				<legend>Register</legend>
			
				<div class="form-row">
					<div class="form-group col-md-3">
						<label for="un">Username</label>
						<input type="text" class="<c:out value="form-control ${ erreurs['username'] != NULL ? 'is-invalid' : '' }" />" name="username" id="un" value="<c:out value="${ user.username }" />" >
					     <div class="invalid-feedback">
					       <c:out value="${ erreurs['username'] }" />
					     </div>
					</div>
				</div>			
				
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="em">Email</label>
					  	<input type="text" class="<c:out value="form-control ${ erreurs['email'] != NULL ? 'is-invalid' : '' }" />" name="email" id="em" value="<c:out value="${ user.email }" />" >
					  	<div class="invalid-feedback">
					       <c:out value="${ erreurs['email'] }" />
					     </div>
				  	</div>			  	
				</div>
				
				<div class="form-row">
					<div class="form-group col-md-3">
						<label for="pwd">Password</label>
						<input type="password" class="<c:out value="form-control ${ erreurs['password'] != NULL ? 'is-invalid' : '' }" />" name="password" id="pwd" >
						<div class="invalid-feedback">
							<c:out value="${ erreurs['password'] }" />
						</div>
					</div>
					<div class="form-group col-md-3">
						<label for="cpwd">Confirm password</label>
						<input type="password"  class="<c:out value="form-control ${ erreurs['password'] != NULL ? 'is-invalid' : '' }" />" name="confirm_password" id="cpwd" >
					</div>
				</div>
			  	
			  	<div class="form-row">
			  		<button type="submit" class="btn btn-dark">Register</button>
			  		<a href="<c:url value = "index"/>" class="btn btn-outline-dark">Cancel</a>
				</div>
			</fieldset>
		</form>
	</div>
</div>
</body>
</html>