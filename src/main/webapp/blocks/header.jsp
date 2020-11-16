<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <a class="navbar-brand" href="#">AITU Event Logging</a>
    <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarsExample04"
            aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="navbar-collapse collapse" id="navbarsExample04" style="">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="<%=request.getContextPath()+"/profile"%>">Home <span
                        class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#">Disabled</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown04" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">Dropdown</a>
                <div class="dropdown-menu" aria-labelledby="dropdown04">
                    <a class="dropdown-item" href="<%=request.getContextPath()+"/add/news"%>">Add News</a>
                    <a class="dropdown-item" href="<%=request.getContextPath()+"/my"%>">My Area</a>

                    <c:if test="${cookie.role.value=='ADMIN'}">
                        <a class="dropdown-item" href="<%=request.getContextPath()+"/add/event"%>">Add Event</a>
                        <a class="dropdown-item" href="<%=request.getContextPath()+"/add/club"%>">Add Club</a>
                        <a class="dropdown-item" href="<%=request.getContextPath()+"/add/group"%>">Add Group</a>
                        <a class="dropdown-item" href="<%=request.getContextPath()+"/search"%>">Search User</a>
                        <a class="dropdown-item" href="<%=request.getContextPath()+"/users"%>"> Users</a>



                    </c:if>
                </div>
            </li>
        </ul>
        <form class="form-inline my-2 my-md-0">
            <input class="form-control" type="text" placeholder="Search">
        </form>
    </div>
</nav>
<div class="nav-scroller bg-white box-shadow">
    <nav class="nav nav-underline">
        <a class="nav-link active" href="#">Dashboard</a>
        <a class="nav-link" href="#">
            Friends
            <span class="badge badge-pill bg-light align-text-bottom">27</span>
        </a>
        <a class="nav-link" href="#">Explore</a>
        <a class="nav-link" href="#">Suggestions</a>
        <a class="nav-link" href="<%=request.getContextPath()+"/news"%>">News</a>
        <a class="nav-link" href="<%=request.getContextPath()+"/groups"%>">Groups</a>
        <a class="nav-link" href="<%=request.getContextPath()+"/clubs"%>">Clubs</a>
        <a class="nav-link" href="<%=request.getContextPath()+"/events"%>">Events</a>
        <a class="nav-link" href="#">Channel</a>

    </nav>

</div>