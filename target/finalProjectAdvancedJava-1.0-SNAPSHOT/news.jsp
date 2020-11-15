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
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">${nw.getTitle()}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">id--> ${nw.getId()}</h6>
                    <p class="card-text">${nw.getDescription()}</p>
                    <a href="#" class="card-link">publisher--> ${nw.getPublisher().getUsername()}</a>
                    <c:if test="${cookie.role.value=='ADMIN'}">
                        <button type="button" data-toggle="modal" data-target="#exampleModal" id="${nw.getId()}"
                                class="btn btn-danger" onclick="removeNews(this.id)">Remove
                        </button>

                    </c:if>
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
