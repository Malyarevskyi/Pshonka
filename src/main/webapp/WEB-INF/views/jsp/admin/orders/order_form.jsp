<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../headerfooter/admin_header.jsp" />

<div class="container">

    <c:choose>
        <c:when test="${order_form['new']}">
            <h1>Add Order</h1>
        </c:when>
        <c:otherwise>
            <h1>Update Order</h1>
        </c:otherwise>
    </c:choose>
    <br />

    <spring:url value="/admin/orders/addOrUpdateOrders" var="orderActionUrl" />

    <%--
    <form:form class="form-horizontal" method="post" modelAttribute="dish_form"
               action="${dishActionUrl}?${_csrf.parameterName}=${_csrf.token}"
               enctype="multipart/form-data">
    --%>

    <form:form class="form-horizontal" method="post" modelAttribute="order_form"
        action="${orderActionUrl}?${_csrf.parameterName}=${_csrf.token}"
        enctype="multipart/form-data">

        <form:hidden path="id" />

        <spring:bind path="waiter">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Waiter</label>
                <div class="col-sm-10">
                    <form:select path="waiter" class="form-control selcls">
                        <form:options items="${waiterList}" itemValue="id" itemLabel="lastName" />
                    </form:select>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="desk">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Desk</label>
                <div class="col-sm-10">
                    <form:select path="desk" class="form-control selcls">
                        <%--<form:option value="NONE" label="--- Select Desk ---" />--%>
                        <form:options items="${deskList}" itemValue="deskTitle" itemLabel="deskTitle"/>
                    </form:select>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="orderDate">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">OrderDate</label>
                <div class="col-sm-10">
                    <form:input path="orderDate" type="date" class="form-control " id="orderDate"/>
                    <form:errors path="orderDate" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <%--<spring:bind path="dishesInOrder">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Dishes</label>
                <div class="col-sm-10">
                    <form:select path="dishesInOrder" items="${dishesAll}"
                                 multiple="true" size="5" class="form-control"
                                 itemLabel="dishTitle" itemValue="dishTitle"/>
                    <form:errors path="dishesInOrder" class="control-label" />
                </div>
            </div>
        </spring:bind>--%>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${order_form['new']}">
                        <button type="submit" class="btn-lg btn-primary pull-right">Add</button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="btn-lg btn-primary pull-right">Update</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

    </form:form>

</div>

<jsp:include page="../headerfooter/admin_footer.jsp" />

</body>
</html>