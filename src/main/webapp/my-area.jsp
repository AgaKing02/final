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
<input type="text" class="d-none" id="student_id" value="${user.getId()}">
<c:if test="${news==null}">
    <h1 class="text-center">No yet news added</h1>
</c:if>
<c:if test="${news!=null}">
    <h1 class="text-center">News</h1>
    <div class="container">
        <div class="row">
            <c:forEach items="${news}" var="nw">
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">${nw.getTitle()}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">id--> ${nw.getId()}</h6>
                        <p class="card-text">${nw.getDescription()}</p>
                        <button type="button" data-toggle="modal" data-target="#exampleModal" id="${nw.getId()}"
                                class="btn btn-danger" onclick="removeNews(this.id)">Remove
                        </button>
                        <a class="btn btn-warning" href="<%=request.getContextPath()+"/edit/news?id="%>${nw.getId()}">Edit</a>

                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>
<c:if test="${events==null}">
    <h1 class="text-center">No yet events you assigned</h1>
</c:if>
<c:if test="${events!=null}">
    <h1 class="text-center">Events</h1>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Event</th>
            <th scope="col">Description</th>
            <th scope="col">Link</th>
            <th scope="col">Action</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${events}" var="event">
            <tr id="${event.getId()}">
                <th scope="row">${event.getId()}</th>
                <td>${event.getEvent()}</td>
                <td>${event.getDescription()}</td>
                <td><a href="<%=request.getContextPath()+"/event?id="%>${event.getId()}">See the event</a></td>
                <c:if test="${cookie.role.value=='STUDENT'}">
                    <td>
                        <button type="button" data-toggle="modal" data-target="#exampleModal" id="${event.getId()}"
                                class="btn btn-danger" onclick="removeUserfromEvent(this.id)">Sign out
                        </button>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${group==null}">
    <h1 class="text-center">No yet group  assigned</h1>
</c:if>
<c:if test="${group!=null}">
    <h1 class="text-center">Group</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Year</th>
            <th scope="col">Link</th>
            <c:if test="${cookie.role.value=='STUDENT'}">
                <th scope="col">Action</th>
            </c:if>

        </tr>
        </thead>
        <tbody>

        <tr id="${group.getId()}">
            <th scope="row">${group.getId()}</th>
            <td>${group.getName()}</td>
            <td>${group.getYear()}</td>
            <td><a href="<%=request.getContextPath()+"/group?id="%>${group.getId()}">See the group</a></td>
            <c:if test="${cookie.role.value=='STUDENT'}">
                <td>
                    <button type="button" data-toggle="modal" data-target="#exampleModal" id="${group.getId()}"
                            class="btn btn-danger" onclick="removeUserfromGroup(this.id)">Sign out
                    </button>

                </td>

            </c:if>
        </tr>

        </tbody>
    </table>
</c:if>
</body>

<script>
    // $('#myModal').modal(options)

    function ajaxRemove(idd) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/finalProjectAdvancedJava_war/news",
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

    function removeNews(idd) {
        ajaxRemove(idd);
        alert("success")

    }

    function removeUserfromClub(clubid) {
        let studentid = parseInt($('#student_id').val());
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/finalProjectAdvancedJava_war/remove?clubid=" + clubid + "&studentid=" + studentid,
            cache: false,
            timeout: 600000,
            success: function (data) {
                alert("success")
            },
            error: function (response, error, errorThrown) {
                alert("error")
            }
        });
    }

    function removeUserfromEvent(eventid) {
        let studentid = parseInt($('#student_id').val());
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/finalProjectAdvancedJava_war/remove?eventid=" + eventid + "&studentid=" + studentid,
            cache: false,
            timeout: 600000,
            success: function (data) {
                alert("success")
            },
            error: function (response, error, errorThrown) {
                alert("error")
            }
        });
    }

    function removeUserfromGroup(groupid) {
        let studentid = parseInt($('#student_id').val());
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/finalProjectAdvancedJava_war/remove?groupid=" + groupid + "&studentid=" + studentid,
            cache: false,
            timeout: 600000,
            success: function (data) {
                alert("success")
            },
            error: function (response, error, errorThrown) {
                alert("error")
            }
        });
    }

    function setDetail(status, message) {
        $('#exampleModalLabel').text(status);
        $('.modal-body ').text(message);
    }

    function removeView(idd) {
        $('div#' + idd).addClass("d-none");
    }
</script>
</html>
