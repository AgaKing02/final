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
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
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
                case "year":
                    List<User> finalSearched = searched;
                    groupService
                            .getGroupsByYear(Integer.parseInt(content))
                            .forEach(group -> finalSearched.addAll(groupService.getStudentsByGroup(group)));
                    json = new Gson().toJson(finalSearched);
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
            HttpSession session = request.getSession();
            ArrayDeque<String> lastSearched = new ArrayDeque<>();
            if (session.getAttribute("last-searched") != null) {
                lastSearched = (ArrayDeque<String>) session.getAttribute("last-searched");
            }
            if (Objects.requireNonNull(lastSearched).size() >= 5) {
                Objects.requireNonNull(lastSearched).removeFirst();
            }
            Objects.requireNonNull(lastSearched).addLast(content);

            session.setAttribute("last-searched", lastSearched);
            session.setMaxInactiveInterval(60 * 60);

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
            HttpSession session = request.getSession();
            ArrayDeque<String> last_accessed = new ArrayDeque<>();
            if (session.getAttribute("last-searched") != null) {
                last_accessed = (ArrayDeque<String>) session.getAttribute("last-searched");
            }
            request.setAttribute("last", last_accessed);
            request.getRequestDispatcher("/search-users.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }
}
