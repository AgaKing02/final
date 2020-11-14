package controllers.controlpanel;

import DTOS.ClubStudent;
import DTOS.EventStudent;
import services.implementations.ClubServiceImpl;
import services.implementations.EventServiceImpl;
import services.interfaces.ClubService;
import services.interfaces.EventService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveUsers")
public class RemoveUsers extends HttpServlet {
    private final EventService eventService=new EventServiceImpl();
    private final ClubService clubService=new ClubServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentid = 0;
        studentid = Integer.parseInt(request.getParameter("studentid"));
        if (request.getParameter("eventid") != null && studentid > 0) {
            long eventid = Long.parseLong(request.getParameter("eventid"));
            eventService.removeUserFromEvent(new EventStudent(eventid, studentid));
        } else if (request.getParameter("clubid") != null && studentid > 0) {
            long clubid = Long.parseLong(request.getParameter("clubid"));
            clubService.removeUserFromClub(new ClubStudent(clubid, studentid));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
