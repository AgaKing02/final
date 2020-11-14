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
    <title>Users</title>
    <jsp:include page="blocks/links.jsp"/>
</head>
<body>
<jsp:include page="blocks/header.jsp"/>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Username</th>
        <th scope="col">Name</th>
        <th scope="col">Surname</th>
        <th scope="col">Role</th>
        <c:if test="${cookie.role.value=='ADMIN'}">
            <th scope="col">Action</th>
        </c:if>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr id="${user.getId()}">
            <th scope="row">${user.getId()}</th>
            <td>${user.getUsername()}</td>
            <td>${user.getName()}</td>
            <td>${user.getSurname()}</td>
            <td>${user.getRole()}</td>
            <c:if test="${cookie.role.value=='ADMIN'}">
                <td>
                    <button type="button" data-toggle="modal" data-target="#exampleModal" id="${user.getId()}"
                            class="btn btn-danger" onclick="removeGroup(this.id)">Remove
                    </button>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Unknown</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body text-center">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $('#myModal').modal(options)

    function ajaxRemove(idd) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/finalProjectAdvancedJava_war/users",
            data: {
                idd: idd
            },
            cache: false,
            timeout: 600000,
            success: function (data) {

            },
            error: function (response, error, errorThrown) {

            }
        });
    }

    function removeGroup(idd) {
        ajaxRemove(idd);
        setDetail("Success", "The users with id " + idd + " removed ");
        removeView(idd);

    }

    function setDetail(status, message) {
        $('#exampleModalLabel').text(status);
        $('.modal-body ').text(message);
    }

    function removeView(idd) {
        $('tr#' + idd).addClass("d-none");
    }
</script>
</html>
