package controllers.my;

import models.Event;
import models.Group;
import models.News;
import security.implementation.AuthorityProviderImpl;
import security.interfaces.AuthorityProvider;
import services.implementations.ClubServiceImpl;
import services.implementations.EventServiceImpl;
import services.implementations.GroupServiceImpl;
import services.implementations.NewsServiceImpl;
import services.interfaces.ClubService;
import services.interfaces.EventService;
import services.interfaces.GroupService;
import services.interfaces.NewsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MyArea")
public class MyArea extends HttpServlet {
    private final AuthorityProvider authorityProvider = new AuthorityProviderImpl();
    private final GroupService groupService = new GroupServiceImpl();
    private final EventService eventService = new EventServiceImpl();
    private final ClubService clubService = new ClubServiceImpl();
    private final NewsService newsService = new NewsServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authorityProvider.isAuthenticated(request, response)) {
            List<Event> myEvents = eventService.getMyEvents(authorityProvider.authenticatedPrincipal(request));
            Group group = groupService.getGroupByStudent(authorityProvider.authenticatedPrincipal(request));
            List<News> news = newsService.getNewsByUser(authorityProvider.authenticatedPrincipal(request));
            if(group!=null){
                request.setAttribute("group", group);

            }
            if(myEvents.size()>0){
                request.setAttribute("events", myEvents);

            }
            if(news.size()>0){
                request.setAttribute("news", news);
            }
            request.getRequestDispatcher("/my-area.jsp").forward(request, response);

        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }
}
