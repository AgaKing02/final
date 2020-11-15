package controllers.editItems;

import models.News;
import security.implementation.AuthorityProviderImpl;
import security.interfaces.AuthorityProvider;
import services.implementations.NewsServiceImpl;
import services.interfaces.NewsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditNews")
public class EditNews extends HttpServlet {
    private final AuthorityProvider authorityProvider = new AuthorityProviderImpl();
    private final NewsService newsService = new NewsServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAuthenticated(request, response)) {
            News news = new News(Long.parseLong(request.getParameter("idd")),request.getParameter("news-title"), request.getParameter("news-description"));
            if (newsService.getNewsByTitle(request.getParameter("news-title")) == null) {
                news.setPublisher(authorityProvider.authenticatedPrincipal(request));
                newsService.update(news);
                response.sendRedirect(request.getContextPath() + "/news");

            } else {
                response.sendRedirect(request.getContextPath() + "/news?error=duplicate");
            }

        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
