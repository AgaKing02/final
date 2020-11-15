package controllers.addItems;

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

@WebServlet(name = "AddEvent")
public class AddEvent extends HttpServlet {
    private final AuthorityProvider authorityProvider = new AuthorityProviderImpl();
    private final EventService eventService = new EventServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAdministrator(request, response)) {
            Event event = new Event(request.getParameter("event-name"), request.getParameter("event-description"));
            if (eventService.getEventByEvent(request.getParameter("event-name")) == null) {
                eventService.add(event);
                response.sendRedirect(request.getContextPath()+"/events");

            } else {
                response.sendRedirect(request.getContextPath() + "/events?error=duplicate");
            }

        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAdministrator(request, response)) {
            request.getRequestDispatcher("/add-event.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }
}
