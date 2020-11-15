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
                    <c:if test="${cookie.role.value=='STUDENT'}">
                        <a class="dropdown-item" href="<%=request.getContextPath()+"/my/news"%>">My News</a>
                        <a class="dropdown-item" href="<%=request.getContextPath()+"/my/group"%>">My Group</a>
                        <a class="dropdown-item" href="<%=request.getContextPath()+"/my/clubs"%>">My Clubs</a>
                        <a class="dropdown-item" href="<%=request.getContextPath()+"/my/events"%>">My Events</a>

                    </c:if>
                    <a class="dropdown-item" href="<%=request.getContextPath()+"/add/news"%>">Add News</a>
                    <c:if test="${cookie.role.value=='ADMIN'}">
                        <a class="dropdown-item" href="<%=request.getContextPath()+"/add/event"%>">Add Event</a>
                        <a class="dropdown-item" href="<%=request.getContextPath()+"/add/club"%>">Add Club</a>
                        <a class="dropdown-item" href="<%=request.getContextPath()+"/add/group"%>">Add Group</a>
                    </c:if>
                </div>
            </li>
        </ul>
        <form class="form-inline my-2 my-md-0">
            <input class="form-control" type="text" placeholder="Search">
        </form>
    </div>
</nav>