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

    <h1>Warehouse Detail</h1>
    <br />

    <div class="row">
        <label class="col-sm-2">ID</label>
        <div class="col-sm-10">${warehouse.id}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">INGREDIENT</label>
        <div class="col-sm-10">${warehouse.ingredient.ingredientTitle}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">AMOUNT</label>
        <div class="col-sm-10">${warehouse.amount}</div>
    </div>

    <spring:url value="/admin/warehouses/create" var="urlCreateWarehouse" />
    <button class="btn btn-toolbar" onclick="location.href='${urlCreateWarehouse}'">Create Warehouse</button>

</div>

<jsp:include page="../headerfooter/admin_footer.jsp" />

</body>
</html>
