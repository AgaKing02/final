<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 15.11.2020
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Event</title>
    <jsp:include page="blocks/links.jsp"/>
</head>
<body>
<jsp:include page="blocks/header.jsp"/>
<div class="jumbotron jumbotron-fluid">
    <div class="container">
        <h1 class="display-4">Edit Event</h1>
        <p class="lead">Events improves the quality of university life among students</p>
    </div>
</div>
<form method="post" action="<%=request.getContextPath()+"/edit/event"%>">
    <div class="form-group">
        <input type="text" name="event-name" class="form-control" placeholder="Name" value="${event.getEvent()}">
    </div>
    <div class="form-group">
        <input type="text" name="event-description" class="form-control" placeholder="Description" value="${event.getDescription()}">
    </div>
    <div class="form-group">
        <input type="text" name="idd" class="form-control d-none" placeholder="Description" value="${event.getId()}">
    </div>
    <div class="container text-center">
        <input type="submit" class="btn btn-outline-primary" value="Edit">
    </div>
</form>
</body>
</html>
