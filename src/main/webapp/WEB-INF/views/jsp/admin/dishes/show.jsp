<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../headerfooter/admin_header.jsp" />

<div class="container">

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <h1>Dish Detail</h1>
    <br />

    <c:choose>
        <c:when test="${not empty picture}">
            <td style="vertical-align: middle" width="150px"> <img src="data:image/jpeg;base64,${picture}" width="170" height="170" class="round1"/> </td>
        </c:when>
        <c:otherwise>
            <td style="vertical-align: middle" width="150px">  <img src="<c:url value="/image/imageNotFound.gif"/>" width="170" height="170" class="round1"/> </td>
        </c:otherwise>
    </c:choose>
    <br />
    <br />

    <div class="row">
        <label class="col-sm-2">ID</label>
        <div class="col-sm-10">${dish.id}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">TITLE</label>
        <div class="col-sm-10">${dish.dishTitle}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">INGREDIENTS</label>
        <div class="col-sm-10">
            [
            <c:forEach items="${dish.ingredients}" var="ingredient" varStatus="stat">
                ${ingredient.ingredientTitle}
                <c:if test="${!stat.last}">|</c:if>
            </c:forEach>
             ]
        </div>
    </div>

    <div class="row">
        <label class="col-sm-2">CATEGORY</label>
        <div class="col-sm-10">${dish.category.categoryTitle}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">PRICE</label>
        <div class="col-sm-10">${dish.price}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">WEIGHT</label>
        <div class="col-sm-10">${dish.weight}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">DESCRIPTION</label>
        <div class="col-sm-10">${dish.description}</div>
    </div>

    <spring:url value="/admin/dishes/create" var="urlCreateDish" />
    <button class="btn btn-toolbar" onclick="location.href='${urlCreateDish}'">Create Dish</button>

</div>

<jsp:include page="../headerfooter/admin_footer.jsp" />

</body>
</html>
