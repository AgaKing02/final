package controllers.controlpanel;

import exceptions.ResourceNotFoundException;
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
import java.util.List;

@WebServlet(name = "GroupLogger")
public class GroupLogger extends HttpServlet {
    private final AuthorityProvider authorityProvider=new AuthorityProviderImpl();
    private final GroupService groupService=new GroupServiceImpl();
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
            groupService.remove(groupService.getGroupById(id));

        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAuthenticated(request, response)) {
            List<Group> groups=groupService.getAllGroups();
            request.setAttribute("groups",groups);
            request.getRequestDispatcher("/groups.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }
}
