package kz.team.se1908.controllers.controlpanel;

import kz.team.se1908.security.implementation.AuthorityProviderImpl;
import kz.team.se1908.security.interfaces.AuthorityProvider;
import kz.team.se1908.services.implementations.EventServiceImpl;
import kz.team.se1908.services.interfaces.EventService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EventLogger")
public class EventLogger extends HttpServlet {
    private final EventService eventService = new EventServiceImpl();
    private final AuthorityProvider authorityProvider = new AuthorityProviderImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        authorityProvider.isAdministrator(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        authorityProvider.isAdministrator(request, response);
    }
}
