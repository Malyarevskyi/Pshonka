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
        <c:when test="${desk_form['new']}">
            <h1>Add Desk</h1>
        </c:when>
        <c:otherwise>
            <h1>Update Desk</h1>
        </c:otherwise>
    </c:choose>
    <br />

    <spring:url value="/admin/desks" var="deskActionUrl" />

    <form:form class="form-horizontal" method="post" modelAttribute="desk_form" action="${deskActionUrl}">

        <form:hidden path="id" />

        <spring:bind path="deskTitle">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Title</label>
                <div class="col-sm-10">
                    <form:input path="deskTitle" type="text" class="form-control " id="deskTitle" placeholder="Title" />
                    <form:errors path="deskTitle" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="deskStatus">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Status</label>
                <div class="col-sm-10">
                    <form:select path="deskStatus" class="form-control selcls">
                        <form:option value="NONE" label="--- Select Status ---" />
                        <form:options path="${statusList}" itemValue="status" itemLabel="status"/>
                    </form:select>
                </div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${desk_form['new']}">
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