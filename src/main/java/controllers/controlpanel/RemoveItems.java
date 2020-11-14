package controllers.controlpanel;

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

@WebServlet(name = "RemoveItems")
public class RemoveItems extends HttpServlet {
    private final EventService eventService = new EventServiceImpl();
    private final ClubService clubService = new ClubServiceImpl();
    private final GroupService groupService = new GroupServiceImpl();
    private final UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("eventid") != null) {
            long eventid = Long.parseLong(request.getParameter("eventid"));
            eventService.remove(eventService.getEventById(eventid));
        } else if (request.getParameter("clubid") != null) {
            long clubid = Long.parseLong(request.getParameter("clubid"));
            clubService.remove(clubService.getClubById(clubid));
        } else if (request.getParameter("groupid") != null) {
            long groupid = Long.parseLong(request.getParameter("groupid"));
            groupService.remove(groupService.getGroupById(groupid));
        } else if (request.getParameter("studentid") != null) {
            long studentid = Long.parseLong(request.getParameter("studentid"));
            userService.remove(userService.getUserByID(studentid));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
