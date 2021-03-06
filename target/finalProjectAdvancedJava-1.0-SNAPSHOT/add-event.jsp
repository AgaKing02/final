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
    <title>Add Event</title>
    <jsp:include page="blocks/links.jsp"/>
</head>
<body>
<jsp:include page="blocks/header.jsp"/>
<div class="container">
    <div class="jumbotron jumbotron-fluid">
        <div class="container">
            <h1 class="display-4">Add Event</h1>
            <p class="lead">Events improves the quality of university life among students</p>
        </div>
    </div>
    <form method="post" action="<%=request.getContextPath()+"/add/event"%>">
        <div class="form-group">
            <input type="text" name="event-name" class="form-control" placeholder="Name">
        </div>
        <div class="form-group">
            <input type="text" name="event-description" class="form-control" placeholder="Description">
        </div>
        <div class="container text-center">
            <input type="submit" class="btn btn-outline-primary" value="Add">
        </div>
    </form>
</div>
</body>
</html>
