<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 11.11.2020
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Groups</title>
    <jsp:include page="blocks/links.jsp"/>
</head>
<body>
<jsp:include page="blocks/header.jsp"/>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">Year</th>
        <th scope="col">Link</th>
        <c:if test="${cookie.role.value=='ADMIN'}">
            <th scope="col">Action</th>
            <th scope="col">Action 2</th>
        </c:if>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${clubs}" var="club">
        <tr>
            <th scope="row">${club.getId()}</th>
            <td>${club.getName()}</td>
            <td>${club.getDescription()}</td>
            <td><a href="<%=request.getContextPath()+"/club?id="%>${club.getId()}">See the club</a></td>
            <c:if test="${cookie.role.value=='ADMIN'}">
                <td>
                    <button class="btn btn-danger">Remove</button>
                </td>
                <td>
                    <a class="btn btn-warning"
                       href="<%=request.getContextPath()+"/edit/club?id="%>${club.getId()}">Edit</a>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
