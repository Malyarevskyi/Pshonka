<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../headerfooter/header.jsp" />

<body>
<div class="container">
    <div class="text-center">
        <img src='<c:url value="/image/waiter.jpg"/>' width="960" height="640">
    </div>
</div>

<div class="container">

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>p
    </c:if>

    <h1>All Waiters</h1>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>#ID</th>
            <th>Photo</th>
            <th>LastName</th>
            <th>FirstName</th>
        </tr>
        </thead>

        <c:set var="idCounter" value="1"/>
        <c:forEach items="${waitersMap}" var="employeeItem">
            <tr>
                <td><c:out value="${idCounter}"/></td>

                <c:choose>
                    <c:when test="${not empty employeeItem.value}">
                        <td style="vertical-align: middle" width="150px"> <img src="data:image/jpeg;base64,${employeeItem.value}" width="170" height="170" class="round1"/> </td>
                    </c:when>
                    <c:otherwise>
                        <td style="vertical-align: middle" width="150px">  <img src="<c:url value="/image/imageNotFound.gif"/>" width="170" height="170" class="round1"/> </td>
                    </c:otherwise>
                </c:choose>

                <td>${employeeItem.key.lastName}</td>
                <td>${employeeItem.key.firstName}</td>

            </tr>
            <c:set var="idCounter" value="${idCounter+1}"/>
        </c:forEach>
    </table>

</div>

<jsp:include page="../headerfooter/footer.jsp" />

</body>
</html>
