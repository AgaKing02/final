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
    <title>Edit News</title>
    <jsp:include page="blocks/links.jsp"/>
</head>
<body>
<jsp:include page="blocks/header.jsp"/>
<div class="jumbotron jumbotron-fluid">
    <div class="container">
        <h1 class="display-4">Edit News</h1>
        <p class="lead">News shares latest things happening in university life probably you dont know</p>
    </div>
</div>
<form method="post" action="<%=request.getContextPath()+"/edit/news"%>">
    <div class="form-group">
        <input type="text" name="news-title" class="form-control" placeholder="Title" value="${news.getTitle()}">
    </div>
    <div class="form-group">
        <div class="form-group">
            <label for="exampleFormControlTextarea1">Description</label>
            <textarea class="form-control" name="news-description" id="exampleFormControlTextarea1" rows="3">${news.getDescription()}</textarea>
        </div>
    </div>
    <div class="form-group">
        <input type="text" name="idd" class="form-control d-none" placeholder="Id" value="${news.getId()}">
    </div>
    <div class="container text-center">
        <input type="submit" class="btn btn-outline-primary" value="Edit">
    </div>

</form>
</body>
</html>
