<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../headerfooter/header.jsp" />

<div class="container">
    <div class="text-center">
        <img src='<c:url value="/image/menu.png"/>' width="442" height="295">
    </div>
</div>

<div class="container">

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <h1>Menu Detail</h1>
    <br />

    <div class="row">
        <label class="col-sm-2">TITLE</label>
        <div class="col-sm-10">${menu.menuTitle}</div>
    </div>

    <h3>All Dishes in Menu</h3>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>#ID</th>
            <th>Picture</th>
            <th style="min-width:150px">Title</th>
            <th>Price</th>
            <th>Weight</th>
            <th style="min-width:250px">Ingredients</th>
            <th>Description</th>
        </tr>
        </thead>

        <c:set var="idCounter" value="1"/>
        <c:forEach items="${dishesMap}" var="dishItem">
            <tr>
                <td><c:out value="${idCounter}"/></td>

                <c:choose>
                    <c:when test="${not empty dishItem.value}">
                        <td style="vertical-align: middle" width="150px"> <img src="data:image/jpeg;base64,${dishItem.value}" width="170" height="170" class="round1" class="round1"/> </td>
                    </c:when>
                    <c:otherwise>
                        <td style="vertical-align: middle" width="150px">  <img src="<c:url value="/image/imageNotFound.gif"/>" width="170" height="170" class="round1" class="round1"/> </td>
                    </c:otherwise>
                </c:choose>

                <td>${dishItem.key.dishTitle}</td>
                <td>${dishItem.key.price}</td>
                <td>${dishItem.key.weight}</td>
                <td>
                    [
                    <c:forEach items="${dishItem.key.ingredients}" var="ingredient" varStatus="stat">
                        ${ingredient.ingredientTitle}
                        <c:if test="${!stat.last}">|</c:if>
                    </c:forEach>
                    ]
                </td>
                <td>${dishItem.key.description}</td>

            </tr>
            <c:set var="idCounter" value="${idCounter+1}"/>
        </c:forEach>
    </table>

</div>

<jsp:include page="../headerfooter/footer.jsp" />

</body>
</html>
