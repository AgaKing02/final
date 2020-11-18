package controllers.editItems;

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

@WebServlet(name = "EditEvent")
public class EditEvent extends HttpServlet {
    private final AuthorityProvider authorityProvider = new AuthorityProviderImpl();
    private final EventService eventService = new EventServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAdministrator(request, response)) {
            Event event = new Event(Long.parseLong(request.getParameter("idd")), request.getParameter("event-name"), request.getParameter("event-description"));
            eventService.update(event);
            response.sendRedirect(request.getContextPath() + "/events");
        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAuthenticated(request, response)) {
            Event event = eventService.getEventByIdForEdit(Long.parseLong(request.getParameter("id")));
            request.setAttribute("event", event);
            request.getRequestDispatcher("/edit-event.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }
}
