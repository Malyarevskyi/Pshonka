<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">

	<title>Spring Security</title>

	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body>
<div class="container">
	<div class="row">
		<div class="col-sm-6 col-md-4 col-md-offset-4">
			<p align="center"><img class="profile-img" src="/image/logo_main.png" width="150" height="150"/></p>
			<h2 class="text-center login-title">Log in to Pshonka administrations</h2>
			<div class="account-wall">
				<form name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'>

					<input type="text" name="username" class="form-control" placeholder="Username">

					<input type="password" name="password" class="form-control" placeholder="Password" required>
					<br />
					<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
					<br />

					<a align="center" href="/" class="text-center new-account">To home page </a>

					<input type="hidden" name="${_csrf.parameterName}"
						   value="${_csrf.token}" />

				</form>
			</div>

		</div>
	</div>
</div>
</body>
</html>
