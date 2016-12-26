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
        </div>p
    </c:if>

    <h1>All Menus</h1>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>#ID</th>
            <th>Title</th>
            <th>Action</th>
        </tr>
        </thead>

        <c:set var="idCounter" value="1"/>
        <c:forEach var="menu" items="${menus}">
            <tr>
                <td><c:out value="${idCounter}"/></td>
                <td>${menu.menuTitle}</td>
                <td>
                    <spring:url value="/admin/menus/${menu.id}" var="menuUrl" />
                    <spring:url value="/admin/menus/${menu.id}/delete" var="deleteUrl" />
                    <spring:url value="/admin/menus/${menu.id}/update" var="updateUrl" />

                    <button class="btn btn-info"    onclick="location.href='${menuUrl}'">Select</button>
                    <button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
                    <button class="btn btn-danger"  onclick="location.href='${deleteUrl}'">Delete</button></td>
            </tr>
            <c:set var="idCounter" value="${idCounter+1}"/>
        </c:forEach>
    </table>

    <spring:url value="/admin/menus/create" var="urlCreateMenu" />
    <button class="btn btn-toolbar" onclick="location.href='${urlCreateMenu}'">Create Menu</button>

</div>

<jsp:include page="../headerfooter/admin_footer.jsp" />

</body>
</html>
