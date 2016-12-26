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

    <h1>All Employees</h1>

    <form action="/admin/employees/search${name}" class="form-inline pull-right">
        <div class="form-group">
            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="employee lastName">
        </div>
        <spring:url value="/admin/employees/search${lastName}" var="urlSearchEmployees" />
        <button class="btn btn-primary" onclick="location.href='${urlSearchEmployees}'">Search by LastName</button>
    </form>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>#ID</th>
            <th>LastName</th>
            <th>FirstName</th>
            <th>Position</th>
            <th>Action</th>
        </tr>
        </thead>

        <c:set var="idCounter" value="1"/>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td><c:out value="${idCounter}"/></td>
                <td>${employee.lastName}</td>
                <td>${employee.firstName}</td>
                <td>${employee.position.positionTitle}</td>
                <td>
                    <spring:url value="/admin/employees/${employee.id}" var="employeeUrl" />
                    <spring:url value="/admin/employees/${employee.id}/delete" var="deleteUrl" />
                    <spring:url value="/admin/employees/${employee.id}/update" var="updateUrl" />

                    <button class="btn btn-info"    onclick="location.href='${employeeUrl}'">Select</button>
                    <button class="btn btn-primary" onclick="location.href='${updateUrl}'"  >Update</button>
                    <button class="btn btn-danger"  onclick="location.href='${deleteUrl}'"  >Delete</button></td>
            </tr>
            <c:set var="idCounter" value="${idCounter+1}"/>
        </c:forEach>
    </table>

    <spring:url value="/admin/employees/create" var="urlCreateEmployee" />
    <button class="btn btn-toolbar" onclick="location.href='${urlCreateEmployee}'">Create Employee</button>

</div>

<jsp:include page="../headerfooter/admin_footer.jsp" />

</body>
</html>