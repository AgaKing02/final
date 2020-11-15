package controllers.controlpanel;

import exceptions.ResourceNotFoundException;
import models.Group;
import models.News;
import services.implementations.NewsServiceImpl;
import services.interfaces.NewsService;
import security.implementation.AuthorityProviderImpl;
import security.interfaces.AuthorityProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "NewsLogger")
public class NewsLogger extends HttpServlet {
    private final NewsService newsService = new NewsServiceImpl();
    private final AuthorityProvider authorityProvider = new AuthorityProviderImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAdministrator(request, response)|| authorityProvider.isAuthenticated(request, response)) {
            int id = 0;
            id = Integer.parseInt(request.getParameter("idd"));
            if (id <= 0) {
                try {
                    throw new ResourceNotFoundException("Id is null");
                } catch (ResourceNotFoundException e) {
                    e.printStackTrace();
                }
            }
            newsService.remove(newsService.getNewsById(id));

        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAuthenticated(request, response)) {
            List<News> news=newsService.getAllNews();
            request.setAttribute("news",news);
            request.getRequestDispatcher("/news.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }
}
