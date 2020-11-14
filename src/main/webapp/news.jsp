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
            <div id="${nw.getId()}" class="col-md-6">
                <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                    <div class="col p-4 d-flex flex-column position-static">
                        <c:if test="${cookie.role.value='ADMIN'}">
                        <button type="button" data-toggle="modal" data-target="#exampleModal" id="${nw.getId()}"
                                class="btn btn-danger" onclick="removeNews(this.id)">Remove
                        </button>
                        <a class="btn btn-light"
                           href="<%=request.getContextPath()+"/user?id="%>${nw.getPublisher().getId()}">See the
                            publisher</a>
                    </c:if>
                        <strong class="d-inline-block mb-2 text-primary">World</strong>
                        <h3 class="mb-0">${nw.getTitle()}</h3>
                        <div class="mb-1 text-muted">Nov 12</div>
                        <p class="card-text mb-auto">${nw.getDescription()}</p>
                        <a href="<%=request.getContextPath()+"/new?id="%>${nw.getId()}" class="stretched-link">Continue
                            reading</a>
                    </div>

                </div>
            </div>
        </c:forEach>
    </div>
</div>
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
        setDetail("Success", "The news with id " + idd + " removed ");
        removeView(idd);

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
