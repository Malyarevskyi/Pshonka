<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <title>Spring MVC Form Handling Example</title>

    <spring:url value="/resources/core/css/hello.css" var="coreCss"/>
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${coreCss}" rel="stylesheet"/>
</head>

<spring:url value="/" var="urlHome"/>
<spring:url value="/client/dishes" var="urlDishes"/>
<spring:url value="/client/menu" var="urlMenu"/>
<spring:url value="/client/personal" var="urlPersonal"/>
<spring:url value="/client/booking" var="urlBooking"/>
<spring:url value="/client/contacts" var="urlContacts"/>
<spring:url value="/login" var="urlLogin"/>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="${urlHome}">Home</a>
        </div>
        <div class="navbar-header">
            <a class="navbar-brand" href="${urlMenu}">Menus</a>
        </div>
        <div class="navbar-header">
            <a class="navbar-brand" href="${urlDishes}">Dishes</a>
        </div>
        <div class="navbar-header">
            <a class="navbar-brand" href="${urlPersonal}">Personal</a>
        </div>
        <div class="navbar-header">
            <a class="navbar-brand" href="${urlBooking}">Booking</a>
        </div>
        <div class="navbar-header">
            <a class="navbar-brand" href="${urlContacts}">Contacts</a>
        </div>
        <div class="navbar-header navbar-right">
            <a class="navbar-brand" href="${urlLogin}">LogIn</a>
        </div>


    </div>
</nav>

<style>
    .round1 {
        border-radius: 85px; /* Радиус скругления */
        border: 1px solid; /* Параметры рамки */
        box-shadow: 0 0 7px #666; /* Параметры тени */
    }
</style>