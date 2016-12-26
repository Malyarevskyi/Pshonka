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
        <c:when test="${menu_form['new']}">
            <h1>Add Menu</h1>
        </c:when>
        <c:otherwise>
            <h1>Update Menu</h1>
        </c:otherwise>
    </c:choose>
    <br />

    <spring:url value="/admin/menus" var="menuActionUrl" />

    <form:form class="form-horizontal" method="post" modelAttribute="menu_form" action="${menuActionUrl}">

        <form:hidden path="id" />

        <spring:bind path="menuTitle">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Title</label>
                <div class="col-sm-10">
                    <form:input path="menuTitle" type="text" class="form-control " id="menuTitle" placeholder="Title" />
                    <form:errors path="menuTitle" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="dishesInMenu">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Dishes</label>
                <div class="col-sm-10">
                    <form:select path="dishesInMenu" items="${dishesAll}"
                                 multiple="true" size="5" class="form-control"
                                 itemLabel="dishTitle" itemValue="dishTitle"/>
                    <form:errors path="dishesInMenu" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${menu_form['new']}">
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