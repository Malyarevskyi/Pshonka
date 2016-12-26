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
        <c:when test="${employee_form['new']}">
            <h1>Add Employee</h1>
        </c:when>
        <c:otherwise>
            <h1>Update Employee</h1>
        </c:otherwise>
    </c:choose>
    <br />

    <spring:url value="/admin/employees/addOrUpdateEmployee" var="employeeActionUrl" />

    <form:form class="form-horizontal" method="post" modelAttribute="employee_form"
               action="${employeeActionUrl}?${_csrf.parameterName}=${_csrf.token}"
               enctype="multipart/form-data">

        <form:hidden path="id" />

        <spring:bind path="lastName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Last Name</label>
                <div class="col-sm-10">
                    <form:input path="lastName" type="text" class="form-control "
                                id="lastName" placeholder="Last Name" />
                    <form:errors path="lastName" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="firstName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">First Name</label>
                <div class="col-sm-10">
                    <form:input path="firstName" type="text" class="form-control "
                                id="firstName" placeholder="First Name"/>
                    <form:errors path="firstName" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="birthday">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Birthday</label>
                <div class="col-sm-10">
                    <form:input path="birthday" type="date" class="form-control " id="birthday"/>
                    <form:errors path="birthday" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="phone">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Phone</label>
                <div class="col-sm-10">
                    <form:input path="phone" type="tel" class="form-control " id="phone" placeholder="Phone" />
                    <form:errors path="phone" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="position">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Position</label>
                <div class="col-sm-10">
                    <form:select path="position.positionTitle" class="form-control selcls">
                        <form:option value="NONE" label="--- Select Position ---" />
                        <form:options items="${positionList}" itemValue="positionTitle" itemLabel="positionTitle"/>
                    </form:select>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="salary">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Salary</label>
                <div class="col-sm-10">
                    <form:input path="salary" type="number" min="0" class="form-control "
                                id="salary" placeholder="Salary" />
                    <form:errors path="salary" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="image">
            <label class="col-sm-2 control-label">Photo</label>
            <div class="col-sm-10">
                <td><input type="file" name="file" id="file"/></td>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${employee_form['new']}">
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