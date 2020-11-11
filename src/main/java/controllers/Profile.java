package controllers;

import security.implementation.AuthorityProviderImpl;
import security.interfaces.AuthorityProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Profile")
public class Profile extends HttpServlet {
    private final AuthorityProvider authorityProvider = new AuthorityProviderImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        authorityProvider.isAuthenticated(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(authorityProvider.isAuthenticated(request, response)){
            request.getRequestDispatcher("/example.jsp").forward(request, response);
        }else {
            response.sendRedirect(request.getContextPath()+"/main");
        }
    }
}
