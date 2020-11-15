package controllers.controlpanel;

import com.google.gson.Gson;
import models.User;
import security.implementation.AuthorityProviderImpl;
import security.interfaces.AuthorityProvider;
import services.implementations.GroupServiceImpl;
import services.implementations.UserServiceImpl;
import services.interfaces.GroupService;
import services.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchUsers")
public class SearchUsers extends HttpServlet {
    private final GroupService groupService = new GroupServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final AuthorityProvider authorityProvider = new AuthorityProviderImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAdministrator(request, response)) {
            String json = null;
            String type = request.getParameter("type");
            String content = request.getParameter("content");
            List<User> searched = new ArrayList<>();
            switch (type) {
                case "group":
                    searched = groupService.getStudentsByGroup(groupService.getGroupByName(content));
                    json = new Gson().toJson(searched);
                    break;
                case "name":
                    searched = userService.getUsersByName(content);
                    json = new Gson().toJson(searched);
                    break;
                case "surname":
                    searched = userService.getUsersBySurname(content);
                    json = new Gson().toJson(searched);
                    break;
                default:
                    searched = null;
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            if (json != null) {
                response.getWriter().write(json);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAdministrator(request, response)) {
            request.getRequestDispatcher("/search-users.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }
}
