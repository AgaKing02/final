package controllers.controlpanel;

import DTOS.ClubStudent;
import DTOS.EventStudent;
import DTOS.GroupStudent;
import com.google.gson.Gson;
import models.User;
import security.implementation.AuthorityProviderImpl;
import security.interfaces.AuthorityProvider;
import services.implementations.ClubServiceImpl;
import services.implementations.EventServiceImpl;
import services.implementations.GroupServiceImpl;
import services.implementations.UserServiceImpl;
import services.interfaces.ClubService;
import services.interfaces.EventService;
import services.interfaces.GroupService;
import services.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "GetUsers")
public class GetUsers extends HttpServlet {
    private final AuthorityProvider authorityProvider = new AuthorityProviderImpl();
    private final UserService userService = new UserServiceImpl();
    private final GroupService groupService = new GroupServiceImpl();
    private final EventService eventService = new EventServiceImpl();
    private final ClubService clubService = new ClubServiceImpl();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentid = 0;
        studentid = Integer.parseInt(request.getParameter("studentid"));
        if (request.getParameter("eventid") != null && studentid > 0) {
            long eventid = Long.parseLong(request.getParameter("eventid"));
            eventService.addUserToEvent(new EventStudent(eventid, studentid));
        } else if (request.getParameter("clubid") != null && studentid > 0) {
            long clubid = Long.parseLong(request.getParameter("clubid"));
            clubService.addUserToClub(new ClubStudent(clubid, studentid));
        } else {
            long groupid = Long.parseLong(request.getParameter("groupid"));
            groupService.addUserToGroup(new GroupStudent(groupid, studentid));

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if (authorityProvider.isAdministrator(request, response)) {
        String json = null;
        if (request.getParameter("clubid") != null) {
            List<User> users = userService.getAllUsers();
            List<User> clubUsers = clubService.getStudentsByClub(
                    clubService.getClubById(
                            Long.parseLong(request.getParameter("clubid"))));

            List<User> nonClubUsers = null;
            nonClubUsers = users.stream().filter(e -> !clubUsers.contains(e)).collect(Collectors.toList());

            json = new Gson().toJson(nonClubUsers);

        } else if (request.getParameter("eventid") != null) {
            List<User> users = userService.getAllUsers();
            List<User> eventUsers = eventService.getStudentsByEvent(
                    eventService.getEventById(
                            Long.parseLong(request.getParameter("eventid"))));

            List<User> nonEventUsers = null;
            nonEventUsers = users.stream().filter(e -> !eventUsers.contains(e)).collect(Collectors.toList());

            json = new Gson().toJson(nonEventUsers);

        } else {
            List<User> users = userService.getAllUsers();
            List<User> groupUsers = groupService.getStudentsByGroup(
                    groupService.getGroupById(
                            Long.parseLong(request.getParameter("groupid"))));

            List<User> nonEventUsers = null;
            nonEventUsers = users.stream().filter(e -> !groupUsers.contains(e)).collect(Collectors.toList());

            json = new Gson().toJson(nonEventUsers);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (json != null) {
            response.getWriter().write(json);
        }
    }
//    }
}
