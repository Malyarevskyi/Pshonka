<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>
	
		<h1 id="banner">Unauthorized Access !</h1>
	
		<hr />
		<p class="message">Access denied!</p>

		<c:if test="${not empty error}">
			<div style="color:black">
				Reason : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</div>
		</c:if>

		<br>
		<a href="/login">To login page</a>
	</body>
</html>