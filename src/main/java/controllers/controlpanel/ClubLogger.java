package controllers.controlpanel;

import models.Club;
import security.implementation.AuthorityProviderImpl;
import security.interfaces.AuthorityProvider;
import services.implementations.ClubServiceImpl;
import services.interfaces.ClubService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ClubLogger")
public class ClubLogger extends HttpServlet {
    private final ClubService clubService = new ClubServiceImpl();
    private final AuthorityProvider authorityProvider = new AuthorityProviderImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        authorityProvider.isAdministrator(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAuthenticated(request, response)) {
            List<Club> clubList = clubService.getAllClubs();
            request.setAttribute("clubs",clubList);
            request.getRequestDispatcher("/clubs.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }
}
