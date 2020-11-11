package kz.team.se1908.security.implementation;

import kz.team.se1908.models.Role;
import kz.team.se1908.models.User;
import kz.team.se1908.security.interfaces.AuthorityProvider;
import kz.team.se1908.services.implementations.UserServiceImpl;
import kz.team.se1908.services.interfaces.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AuthorityProviderImpl implements AuthorityProvider {
    private final UserService userService = new UserServiceImpl();

    @Override
    public void isAuthenticated(HttpServletRequest request, HttpServletResponse response) {
        boolean userExistence = false;
        String username = null;
        Cookie[] cookies = request.getCookies();
        //Displaying User name value from cookie
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                request.setAttribute("username", cookie.getValue());//username of user
                username = cookie.getValue();
                userExistence = true;
                break;
            }
        }
        if (!userExistence) {
            try {
                response.sendRedirect(request.getContextPath() + "/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void isAdministrator(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String role = null;
        //Displaying User name value from cookie
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                User user = userService.getUserByUsername(cookie.getValue());
                role = user.getRole();
            }
        }
        if (!Objects.equals(role, Role.ADMIN.name())) {
            try {
                response.sendRedirect(request.getContextPath() + "/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
