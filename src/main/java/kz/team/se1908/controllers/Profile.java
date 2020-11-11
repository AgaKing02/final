package kz.team.se1908.controllers;

import kz.team.se1908.security.implementation.AuthorityProviderImpl;
import kz.team.se1908.security.interfaces.AuthorityProvider;

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
        authorityProvider.isAuthenticated(request, response);

    }
}
