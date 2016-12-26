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
        <c:when test="${dishes_preparation_form['new']}">
            <h1>Add DishesPreparation</h1>
        </c:when>
        <c:otherwise>
            <h1>Update DishesPreparation</h1>
        </c:otherwise>
    </c:choose>
    <br />

    <spring:url value="/admin/dishes_preparations" var="dishes_preparationActionUrl" />

    <form:form class="form-horizontal" method="post" modelAttribute="dishes_preparation_form" action="${dishes_preparationActionUrl}">

        <form:hidden path="id" />

        <spring:bind path="dish">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Dish</label>
                <div class="col-sm-10">
                    <form:select path="dish" class="form-control selcls">
                        <form:options items="${dishList}" itemValue="id" itemLabel="dishTitle"/>
                    </form:select>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="cook">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Waiter</label>
                <div class="col-sm-10">
                    <form:select path="cook" class="form-control selcls">
                        <form:options items="${cookList}" itemValue="id" itemLabel="lastName"/>
                    </form:select>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="order">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Order</label>
                <div class="col-sm-10">
                    <form:select path="order" class="form-control selcls">
                        <form:options items="${orderList}" itemValue="id" itemLabel="id"/>
                    </form:select>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="datePreparation">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">DishesPreparationDate</label>
                <div class="col-sm-10">
                    <form:input path="datePreparation" type="date" class="form-control " id="datePreparation"/>
                    <form:errors path="datePreparation" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${dishes_preparation_form['new']}">
                        <button type="submit" class="btn-lg btn-primary pull-right">Add</button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="btn-lg btn-primary pull-right">Update</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>

</div>

<jsp:include page="../headerfooter/admin_footer.jsp" />

</body>
</html>