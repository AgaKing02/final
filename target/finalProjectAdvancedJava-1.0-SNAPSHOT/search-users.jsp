<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 14.11.2020
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Users</title>
    <jsp:include page="blocks/links.jsp"/>
</head>
<body>
<jsp:include page="blocks/header.jsp"/>
<div class="container">
    <div>Last Searched</div>
    <div class="row">
        <c:forEach var="item" items="${last}">
            <div class="col border border-dark rounded">${item}</div>
        </c:forEach>
    </div>
    <div class="form-group">
        <label for="exampleFormControlInput1">Input</label>
        <input type="text" name="content" class="form-control search-content" id="exampleFormControlInput1"
               placeholder="name@example.com">
    </div>
    <div class="form-group">
        <label for="exampleFormControlSelect1">Select from</label>
        <select name="type" class="form-control select-type" id="exampleFormControlSelect1">
            <option value="group">By group</option>
            <option value="name">By name</option>
            <option value="surname">By surname</option>
            <option value="year">By year</option>

            <%--            <option value="year">By group-year</option>--%>
        </select>
    </div>
    <button class="btn btn-outline-info" onclick="getUsers()">Search</button>

</div>
<div class="results">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Username</th>
            <th scope="col">Name</th>
            <th scope="col">Surname</th>
            <th scope="col">Role</th>
        </tr>
        </thead>
        <tbody id="users">

        </tbody>
    </table>
</div>
</body>
<script>
    function getUsers() {
        let selectedType = $('select.select-type').children("option:selected").val();
        let content = $('input.search-content').val();
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/finalProjectAdvancedJava_war/search?type=" + selectedType + "&content=" + content,
            cache: false,
            timeout: 600000,
            success: function (data) {
                console.log(data);
                clearResult()
                for (let i = 0; i < data.length; i++) {
                    addUser(data[i])
                }
            },
            error: function (response, error, errorThrown) {

            }
        });
    }

    function clearResult() {
        $('#div.results').text("");
    }

    function addUser(user) {
        $('tbody#users').append('<tr>' +
            '<th scope="row">' + user.id + '</th>' +
            '<td>' + user.username + '</td>' +
            '<td>' + user.name + '</td>' +
            '<td>' + user.surname + '</td>' +
            '<td>' + user.role + '</td>'
            + '</tr>');
    }
</script>
</html>
