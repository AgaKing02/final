package kz.team.se1908.controllers;

import kz.team.se1908.models.Role;
import kz.team.se1908.models.User;
import kz.team.se1908.services.implementations.UserServiceImpl;
import kz.team.se1908.services.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Authentication")
public class Authentication extends HttpServlet {
    private final UserService userService=new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();

        user.setName(request.getParameter("txtName"));
        user.setUsername(request.getParameter("txtUsername"));
        user.setSurname(request.getParameter("txtSurname"));
        user.setPassword(request.getParameter("txtPassword"));
        user.setRole(Role.STUDENT.name());

        if(userService.getUserByUsername(user.getUsername())!=null){
            response.sendRedirect(request.getContextPath()+"/login?error=duplicate");
        }else{
            userService.addUser(user);
            response.sendRedirect(request.getContextPath()+"/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
