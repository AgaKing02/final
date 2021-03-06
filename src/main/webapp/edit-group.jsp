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
    <title>Edit Group</title>
    <jsp:include page="blocks/links.jsp"/>
</head>
<body>
<jsp:include page="blocks/header.jsp"/>
<div class="jumbotron jumbotron-fluid">
    <div class="container">
        <h1 class="display-4">Edit Group</h1>
        <p class="lead">Groups improves the quality of friendship and teamwork  among students</p>
    </div>
</div>
<form method="post" action="<%=request.getContextPath()+"/edit/group"%>">
    <div class="form-group">
        <input type="text" name="group-name" class="form-control" placeholder="Name" value="${group.getName()}">
    </div>
    <div class="form-group">
        <input type="number" name="group-year" class="form-control" placeholder="Year" value="${group.getYear()}">
    </div>
    <div class="form-group">
        <input type="text" name="idd" class="form-control d-none" placeholder="Name" value="${group.getId()}">
    </div>
    <div class="container text-center">
        <input type="submit" class="btn btn-outline-primary" value="Edit">
    </div>
</form>
</body>
</html>
