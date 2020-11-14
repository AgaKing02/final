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
    <title>Events</title>
    <jsp:include page="blocks/links.jsp"/>
</head>
<body>
<jsp:include page="blocks/header.jsp"/>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Event</th>
        <th scope="col">Description</th>
        <th scope="col">Link</th>
        <c:if test="${cookie.role.value=='ADMIN'}">
            <th scope="col">Action</th>
            <th scope="col">Action 2</th>
        </c:if>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${events}" var="event">
        <tr id="${event.getId()}">
            <th scope="row">${event.getId()}</th>
            <td>${event.getEvent()}</td>
            <td>${event.getDescription()}</td>
            <td><a href="<%=request.getContextPath()+"/event?id="%>${event.getId()}">See the event</a></td>
            <c:if test="${cookie.role.value=='ADMIN'}">
                <td>
                    <button type="button" data-toggle="modal" data-target="#exampleModal" id="${event.getId()}"
                            class="btn btn-danger" onclick="removeEvent(this.id)">Remove
                    </button>
                </td>
                <td>
                    <a class="btn btn-warning"
                       href="<%=request.getContextPath()+"/edit/event?id="%>${event.getId()}">Edit</a>
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
            url: "http://localhost:8080/finalProjectAdvancedJava_war/events",
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

    function removeEvent(idd) {
        ajaxRemove(idd);
        setDetail("Success", "The event with id " + idd + " removed ");
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
