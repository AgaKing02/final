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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
//            HttpSession session = request.getSession();
//            Map<String, String> last_accessed = null;
//            if (session.getAttribute("last-searched-items") != null) {
//                last_accessed = (Map<String, String>) session.getAttribute("last-searched-items");
//            }
//
//            Objects.requireNonNull(last_accessed).put(type, content);
//
//            session.setAttribute("last-searched-items", last_accessed);
//            session.setMaxInactiveInterval(60 * 60);

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
//            HttpSession session = request.getSession();
//            Map<String, String> last_accessed = null;
//            if (session.getAttribute("last-searched-items") != null) {
//                last_accessed = (Map<String, String>) session.getAttribute("last-searched-items");
//            }
//            request.setAttribute("last",last_accessed);
            request.getRequestDispatcher("/search-users.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }
}
