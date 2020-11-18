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
    <title>Add Group</title>
    <jsp:include page="blocks/links.jsp"/>
</head>
<body>
<jsp:include page="blocks/header.jsp"/>
<div class="container">
    <div class="jumbotron jumbotron-fluid">
        <div class="container">
            <h1 class="display-4">Add Group</h1>
            <p class="lead">Groups improves the quality of friendship and teamwork among students</p>
        </div>
    </div>
    <form method="post" action="<%=request.getContextPath()+"/add/group"%>">
        <div class="form-group">
            <input type="text" name="group-name" class="form-control" placeholder="Name">
        </div>
        <div class="form-group">
            <input type="number" name="group-year" class="form-control" placeholder="Year">
        </div>
        <div class="container text-center">
            <input type="submit" class="btn btn-outline-primary" value="Add">
        </div>
    </form>
</div>
</body>
</html>
