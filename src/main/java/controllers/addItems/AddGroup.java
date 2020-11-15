package controllers.addItems;

import models.Event;
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

@WebServlet(name = "AddGroup")
public class AddGroup extends HttpServlet {
    private final AuthorityProvider authorityProvider = new AuthorityProviderImpl();
    private final GroupService groupService=new GroupServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAdministrator(request, response)) {
            Group group = new Group(request.getParameter("group-name"), Integer.parseInt(request.getParameter("group-year")));
            if (groupService.getGroupByName(request.getParameter("group-name")) == null) {
                groupService.add(group);
                response.sendRedirect(request.getContextPath()+"/groups");

            } else {
                response.sendRedirect(request.getContextPath() + "/groups?error=duplicate");
            }

        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAdministrator(request, response)) {
            request.getRequestDispatcher("/add-group.jsp").forward(request,response);
        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }
}
