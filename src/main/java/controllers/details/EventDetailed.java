package controllers.details;

import models.Club;
import models.Event;
import security.implementation.AuthorityProviderImpl;
import security.interfaces.AuthorityProvider;
import services.implementations.EventServiceImpl;
import services.interfaces.EventService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EventDetailed")
public class EventDetailed extends HttpServlet {
    private final AuthorityProvider authorityProvider = new AuthorityProviderImpl();
    private final EventService eventService=new EventServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAuthenticated(request, response)) {
            Event event=eventService.getEventById(Long.parseLong(request.getParameter("id")));
            request.setAttribute("event",event);
            request.getRequestDispatcher("/event-detailed-view.jsp").forward(request,response);
        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }
}
