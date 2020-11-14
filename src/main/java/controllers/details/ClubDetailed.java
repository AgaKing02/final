package controllers.details;

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

@WebServlet(name = "ClubDetailed")
public class ClubDetailed extends HttpServlet {
    private final ClubService clubService = new ClubServiceImpl();
    private final AuthorityProvider authorityProvider = new AuthorityProviderImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAuthenticated(request, response)) {
            Club club = clubService.getClubById(Long.parseLong(request.getParameter("id")));
            request.setAttribute("club",club);
            request.getRequestDispatcher("/club-detailed-view.jsp").forward(request,response);
        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }
}
