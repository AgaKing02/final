<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 14.11.2020
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>News</title>
    <jsp:include page="blocks/links.jsp"/>
</head>
<body>
<jsp:include page="blocks/header.jsp"/>
<div class="container">
    <div class="row">
        <c:forEach items="${news}" var="nw">
            <div class="col-md-6">
                <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                    <div class="col p-4 d-flex flex-column position-static">
                        <strong class="d-inline-block mb-2 text-primary">World</strong>
                        <h3 class="mb-0">${nw.getTitle()}</h3>
                        <div class="mb-1 text-muted">Nov 12</div>
                        <p class="card-text mb-auto">${nw.getDescription()}</p>
                        <a href="<%=request.getContextPath()+"/new?id="%>${nw.getId()}" class="stretched-link">Continue
                            reading</a>
                    </div>
                    <c:if test="${cookie.role.value='ADMIN'}">
                        <button class="btn btn-danger">Remove</button>
                        <a class="btn btn-light"
                           href="<%=request.getContextPath()+"/user?id="%>${nw.getPublisher().getId()}">See the
                            publisher</a>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
