<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 13.11.2020
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Club</title>
    <jsp:include page="blocks/links.jsp"/>

</head>
<body>
<jsp:include page="blocks/header.jsp"/>
<div class="jumbotron">
    <div class="container">
        <h1 class="display-3">${club.getName()}</h1>
        <p>${club.getDescription()}</p>
        <c:if test="${cookie.role.value!='ADMIN'}"><p><a class="btn btn-primary btn-lg" href="#" role="button">Learn
            more »</a></p>
        </c:if>
    </div>
</div>
<div class="container">
    <c:if test="${club.getVolunteers()!=null}">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">Surname</th>
                <th scope="col">Username</th>
                <th scope="col">Link</th>
                <c:if test="${cookie.role.value=='ADMIN'}">
                    <th scope="col">Action</th>
                </c:if>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${club.getVolunteers()}" var="student">
                <tr>
                    <th scope="row">${student.getId()}</th>
                    <td>${student.getName()}</td>
                    <td>${student.getSurname()}</td>
                    <td>${student.getUsername()}</td>
                    <td><a href="<%=request.getContextPath()+"/user?id="%>${student.getId()}">See the user</a></td>
                    <c:if test="${cookie.role.value=='ADMIN'}">
                        <td>
                            <button id="${student.getId()}" onclick="removeUserfromClub(this.id)" class="btn btn-danger">Remove</button>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${cookie.role.value=='ADMIN'}">
        <button id="${club.getId()}" onclick="getUnsignedUsers(this.id)" type="button" class="btn btn-info text-center" data-toggle="modal" data-target="#exampleModal1"
                data-whatever="@mdo">Add Student
        </button>

        <div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">New message</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Send message</button>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</div>
<script>
    $('#myModal').modal(options)

    function getUnsignedUsers(idd) {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/finalProjectAdvancedJava_war/getusers?clubid="+idd,
            cache: false,
            timeout: 600000,
            success: function (data) {
                console.log(data);

                // alert(users)
                for (let i = 0; i < data.length; i++) {
                    addUser(data[i],idd)
                }
            },
            error: function (response, error, errorThrown) {

            }
        });
    }

    function addUser(user,club) {
        $('.modal-body').append('<div><p>' + user.username + '<p><button onclick="addUsertoClub('+club+','+user.id+')" class="btn btn-success">Add</button></div>');

    }

    function addUsertoClub(idd,studentid) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/finalProjectAdvancedJava_war/getusers?clubid="+idd+"&studentid="+studentid,
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
    function removeUserfromClub(studentid) {
        let idd=parseInt(getParameterByName("id"));
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/finalProjectAdvancedJava_war/remove?clubid="+idd+"&studentid="+studentid,
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
    function getParameterByName(name, url = window.location.href) {
        name = name.replace(/[\[\]]/g, '\\$&');
        var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, ' '));
    }

</script>
</body>
</html>
