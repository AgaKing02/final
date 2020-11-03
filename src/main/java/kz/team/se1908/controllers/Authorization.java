package kz.team.se1908.controllers;

import kz.team.se1908.DTOS.LoginData;
import kz.team.se1908.models.User;
import kz.team.se1908.services.implementations.UserServiceImpl;
import kz.team.se1908.services.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Authorization")
public class Authorization extends HttpServlet {
    private final UserService userService=new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginData userLoginData = new LoginData(request.getParameter("username"), request.getParameter("password"));
        if (checkUserExistence(userLoginData)) {
            Cookie cookie = new Cookie("username", userLoginData.getUsername());
            Cookie cookie1=new Cookie("role",userService.getUserByUsername(userLoginData.getUsername()).getRole());
            response.addCookie(cookie);
            response.addCookie(cookie1);
//
//            response.setContentType("text/html");
//            PrintWriter printWriter = response.getWriter();
//            printWriter.print("<h1><a href='" + request.getContextPath() + "/profile'>Confirm " + userLoginData.getUsername() + " user </a></h1>");
            response.sendRedirect(request.getContextPath() + "/profile");

        } else {
            response.sendRedirect(request.getContextPath() + "/login?auth_error=true");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public boolean checkUserExistence(LoginData userLoginData) {
        User user = userService.checkUserExistence(userLoginData);
        return user != null;
    }
}
