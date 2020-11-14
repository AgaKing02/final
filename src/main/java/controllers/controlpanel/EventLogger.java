package controllers.controlpanel;

import exceptions.ResourceNotFoundException;
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
import java.util.List;

@WebServlet(name = "EventLogger")
public class EventLogger extends HttpServlet {
    private final EventService eventService = new EventServiceImpl();
    private final AuthorityProvider authorityProvider = new AuthorityProviderImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAdministrator(request, response)) {
            int id = 0;
            id = Integer.parseInt(request.getParameter("idd"));
            if (id <= 0) {
                try {
                    throw new ResourceNotFoundException("Id is null");
                } catch (ResourceNotFoundException e) {
                    e.printStackTrace();
                }
            }
            eventService.remove(eventService.getEventById(id));

        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAuthenticated(request, response)) {
            List<Event> events = eventService.getAllEvents();
            request.setAttribute("events", events);
            request.getRequestDispatcher("/events.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }
}
