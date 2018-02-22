<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="style.css" />
<link type="text/css" rel="stylesheet" href="bootstrap.min.css" />
<title>Login</title>
</head>
<body>
<div id="main-page">
	<div id="content" class="rounded">
		<form method="post" action="<c:url value = "login"/>">
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="un">Username</label>
						<input type="text" class="<c:out value="form-control ${ erreurs['username'] != NULL ? 'is-invalid' : '' }" />" name="username" id="un" value="<c:out value="${ user }" />" >			
					    <div class="invalid-feedback">
					      <c:out value="${ erreurs['username'] }" />
					    </div>
					</div>
					
					<div class="form-group col-md-6">
						<label for="pwd">Password</label>
						<input type="password" class="<c:out value="form-control ${ erreurs['password'] != NULL ? 'is-invalid' : '' }" />" name="password" id="pwd" >
						<div class="invalid-feedback">
							<c:out value="${ erreurs['password'] }" />
						</div>
					</div>
				</div>
		  
		  <div class="form-check">
		    <input type="checkbox" class="form-check-input" id="exampleCheck1">
		    <label class="form-check-label" for="exampleCheck1">Check me out</label>
		  </div>
		  <button type="submit" class="btn btn-dark">Login</button>
		  <a href="<c:url value = "index"/>" class="btn btn-outline-dark">Cancel</a>
		</form>
	</div>
</div>
</body>
</html>