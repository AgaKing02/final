package security.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthorityProvider {
    void isAuthenticated(HttpServletRequest request, HttpServletResponse response);
    void isAdministrator(HttpServletRequest request, HttpServletResponse response);
}
