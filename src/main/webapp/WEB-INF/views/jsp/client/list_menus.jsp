<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../headerfooter/header.jsp" />

<body>

<div class="container">

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>p
    </c:if>

    <h1>All Menus</h1>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>#ID</th>
            <th>Title</th>
            <th>Dishes</th>
            <%--<th>Action</th>--%>
        </tr>
        </thead>

        <c:set var="idCounter" value="1"/>
        <c:forEach var="menu" items="${menus}">
            <tr>
                <td><c:out value="${idCounter}"/></td>
                <td>${menu.menuTitle}</td>
                <td>
                    [
                    <c:forEach items="${menu.dishesInMenu}" var="dish" varStatus="stat">
                        ${dish.dishTitle}
                        <c:if test="${!stat.last}">|</c:if>
                    </c:forEach>
                    ]
                </td>
                <td>
                    <spring:url value="/client/menu/${menu.id}" var="menuUrl" />
                    <button class="btn btn-info" onclick="location.href='${menuUrl}'">Select</button>
                </td>
            </tr>
            <c:set var="idCounter" value="${idCounter+1}"/>
        </c:forEach>
    </table>

</div>

<jsp:include page="../headerfooter/footer.jsp" />

</body>
</html>
