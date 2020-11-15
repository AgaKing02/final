package controllers.editItems;

import models.Group;
import security.implementation.AuthorityProviderImpl;
import security.interfaces.AuthorityProvider;
import services.implementations.GroupServiceImpl;
import services.interfaces.GroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditGroup")
public class EditGroup extends HttpServlet {
    private final AuthorityProvider authorityProvider = new AuthorityProviderImpl();
    private final GroupService groupService = new GroupServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAdministrator(request, response)) {
            Group group = new Group(Long.parseLong(request.getParameter("idd")), request.getParameter("group-name"), Integer.parseInt(request.getParameter("group-year")));
            groupService.update(group);
            response.sendRedirect(request.getContextPath() + "/groups");
        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAuthenticated(request, response)) {
            Group group = groupService.getGroupById(Long.parseLong(request.getParameter("id")));
            request.setAttribute("group", group);
            request.getRequestDispatcher("/edit-group.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }
}
