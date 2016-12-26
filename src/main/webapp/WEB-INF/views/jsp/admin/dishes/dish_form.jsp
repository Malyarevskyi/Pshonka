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
        <c:when test="${dish_form['new']}">
            <h1>Add Dish</h1>
        </c:when>
        <c:otherwise>
            <h1>Update Dish</h1>
        </c:otherwise>
    </c:choose>
    <br />

    <spring:url value="/admin/dishes/addOrUpdateDish" var="dishActionUrl" />

    <form:form class="form-horizontal" method="post" modelAttribute="dish_form"
               action="${dishActionUrl}?${_csrf.parameterName}=${_csrf.token}"
               enctype="multipart/form-data">

        <form:hidden path="id" />

        <spring:bind path="dishTitle">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Title</label>
                <div class="col-sm-10">
                    <form:input path="dishTitle" type="text" class="form-control " id="dishTitle" placeholder="Title" />
                    <form:errors path="dishTitle" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="ingredients">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Ingredients</label>
                <div class="col-sm-10">
                    <form:select path="ingredients" items="${ingredientList}"
                                 multiple="true" size="10" class="form-control"
                                 itemLabel="ingredientTitle" itemValue="ingredientTitle"/>
                    <form:errors path="ingredients" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="category">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Category</label>
                <div class="col-sm-10">
                    <form:select path="category.categoryTitle" class="form-control selcls">
                        <form:option value="NONE" label="--- Select Category ---" />
                        <form:options items="${categoryList}" itemValue="categoryTitle" itemLabel="categoryTitle"/>
                    </form:select>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="price">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Price</label>
                <div class="col-sm-10">
                    <form:input path="price" type="number" min="0" class="form-control " id="price" placeholder="Price" />
                    <form:errors path="price" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="weight">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Weight</label>
                <div class="col-sm-10">
                    <form:input path="weight" type="number" min="0" class="form-control " id="weight" placeholder="Weight" />
                    <form:errors path="weight" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="description">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Description</label>
                <div class="col-sm-10">
                    <form:input multiple="true" size="10" path="description" type="text"
                                class="form-control " id="description" placeholder="Description" />
                    <form:errors path="description" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="picture">
            <label class="col-sm-2 control-label">Picture</label>
            <div class="col-sm-10">
                <td><input type="file" name="file" id="file"/></td>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${dish_form['new']}">
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