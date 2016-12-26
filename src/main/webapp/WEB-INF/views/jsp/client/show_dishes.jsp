<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../headerfooter/header.jsp" />

<div class="container">

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <div class="container">
    <div class="text-center">
        <img src='<c:url value="/image/menu.png"/>' width="442" height="295">
    </div>
</div>

    <h1>All Dishes</h1>

    <form action="/client/search${dishTitle}" class="form-inline pull-right">
        <div class="form-group">
            <input type="text" class="form-control" id="dishTitle" name="dishTitle" placeholder="Dish title">
        </div>
        <spring:url value="/client/search${dishTitle}" var="urlSearchDish" />
        <button class="btn btn-primary" onclick="location.href='${urlSearchDish}'">Search by DishTitle</button>
    </form>

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
                        <td style="vertical-align: middle" width="150px"> <img src="data:image/jpeg;base64,${dishItem.value}" width="170" height="170" class="round1"/> </td>
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
