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
<%--    <c:forEach var="entry" items="${last}">--%>
<%--        <div>Last Searched</div>--%>
<%--        Key: <c:out value="${entry.key}"/>--%>
<%--        Value: <c:out value="${entry.value}"/>--%>
<%--    </c:forEach>--%>
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
            <%--            <option value="year">By group-year</option>--%>
        </select>
    </div>
    <button class="btn btn-outline-info" onclick="getUsers()">Search</button>

</div>
<div class="results">

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
        $('div.results').append('<div class="text-center border border-dark"><button class="btn btn-warning">' + user.username + ' ' + user.surname + ' ' + user.role +
            '</button></div>');
    }
</script>
</html>
