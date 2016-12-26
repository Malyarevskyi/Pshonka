<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../headerfooter/admin_header.jsp" />

<body>

	<div class="container">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<h1>All Positions</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Title</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:set var="idCounter" value="1"/>
			<c:forEach var="position" items="${positions}">
				<tr>
					<td><c:out value="${idCounter}"/></td>
					<td>${position.positionTitle}</td>
					<td>
						<spring:url value="/admin/positions/${position.id}" var="positionUrl" />
						<spring:url value="/admin/positions/${position.id}/delete" var="deleteUrl" />
						<spring:url value="/admin/positions/${position.id}/update" var="updateUrl" />

						<button class="btn btn-info"    onclick="location.href='${positionUrl}'">Select</button>
						<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
						<button class="btn btn-danger"  onclick="location.href='${deleteUrl}'">Delete</button></td>
				</tr>
				<c:set var="idCounter" value="${idCounter+1}"/>
			</c:forEach>
		</table>

		<spring:url value="/admin/positions/create" var="urlCreatePosition" />
		<button class="btn btn-toolbar" onclick="location.href='${urlCreatePosition}'">Create Position</button>

	</div>

<jsp:include page="../headerfooter/admin_footer.jsp" />

</body>
</html>