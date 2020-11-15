package controllers.addItems;

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

@WebServlet(name = "AddClub")
public class AddClub extends HttpServlet {
    private final AuthorityProvider authorityProvider = new AuthorityProviderImpl();
    private final ClubService clubService = new ClubServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAdministrator(request, response)) {
            Club club = new Club(request.getParameter("club-name"), request.getParameter("club-description"));
            if (clubService.getClubByName(request.getParameter("club-name")) == null) {
                clubService.add(club);
                response.sendRedirect(request.getContextPath()+"/clubs");
            } else {
                response.sendRedirect(request.getContextPath() + "/clubs?error=duplicate");
            }

        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAdministrator(request, response)) {
            request.getRequestDispatcher("/add-club.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }
}
