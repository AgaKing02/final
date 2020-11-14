package controllers.controlpanel;

import exceptions.ResourceNotFoundException;
import models.Group;
import models.User;
import security.implementation.AuthorityProviderImpl;
import security.interfaces.AuthorityProvider;
import services.implementations.UserServiceImpl;
import services.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserLogger")
public class UserLogger extends HttpServlet {
    private final UserService userService = new UserServiceImpl();
    private final AuthorityProvider authorityProvider = new AuthorityProviderImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAdministrator(request, response)) {
            int id = 0;
            id = Integer.parseInt(request.getParameter("idd"));
            if (id <= 0) {
                try {
                    throw new ResourceNotFoundException("Id is null");
                } catch (ResourceNotFoundException e) {
                    e.printStackTrace();
                }
            }
            userService.remove(userService.getUserByID(id));

        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAuthenticated(request, response)) {
            List<User> userList=userService.getAllUsers();

            request.setAttribute("users",userList);
            request.getRequestDispatcher("/users.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }    }
}
